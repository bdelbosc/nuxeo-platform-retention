package org.nuxeo.ecm.platform.retention.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nuxeo.ecm.platform.retention.RetentionService;
import org.nuxeo.ecm.platform.retention.Rule;
import org.nuxeo.ecm.platform.retention.RuleDefinitionDescriptor;
import org.nuxeo.ecm.platform.retention.RuleImpl;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.NXRuntimeTestCase;

public class RetentionServiceTest extends NXRuntimeTestCase {

    private static final Log log = LogFactory.getLog(RetentionServiceTest.class);

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployBundle("org.nuxeo.ecm.platform.configuration");
        deployBundle("org.nuxeo.ecm.platform.retention");
        // Set framework properties to be able to load the retention
        // properties files
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String resourceDir = loader.getResource(".").getFile();
        Framework.getProperties().put("package.resource.dir", resourceDir);
        deployBundle("org.nuxeo.ecm.platform.retention.test");
        deployContrib("org.nuxeo.ecm.platform.retention.test",
                "OSGI-INF/test-retention-contrib.xml");
        deployContrib("org.nuxeo.ecm.platform.retention.test",
                "OSGI-INF/test-configuration-contrib.xml");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testContrib() throws Exception {
        RetentionService service = Framework.getService(RetentionService.class);
        assertNotNull(service);
        assertFalse(service.getRuleDefinitionNames().isEmpty());
        RuleDefinitionDescriptor ruleDef = service.getRuleDefinition("updateState");
        assertNotNull(ruleDef);
    }

    @Test
    public void testRuleCRUD() throws Exception {
        RetentionService service = Framework.getService(RetentionService.class);
        RuleDefinitionDescriptor ruleDef = service.getRuleDefinition("updateState");
        assertNotNull(ruleDef);

        Rule rule = service.getRule("unknown");
        assertNull(rule);
        rule = service.getRule("");
        assertNull(rule);
        rule = service.getRule(null);
        assertNull(rule);

        String id = "mytestid";
        rule = new RuleImpl();
        rule.setName(id);
        rule.setCronLine("* * * 1");
        String[] dParams = new String[] { "value1", "value2" };
        rule.setDispositionParams(dParams);
        rule.setEnable(true);
        rule.setStatus("In progress");
        String[] fParams = new String[] { "value1", "value2", "value3" };
        rule.setFilterParams(fParams);

        // make sure that there is no previously saved rule with the same name
        service.deleteRule(rule);
        // check deletion of a non existing rule
        service.deleteRule(rule);
        service.deleteRule(null);

        rule = service.createRule(rule);
        assertNotNull(rule);
        Rule rule2 = service.getRule(id);
        assertEquals(rule, rule2);
        assertEquals(rule.hashCode(), rule2.hashCode());

        rule.setEnable(false);
        rule = service.updateRule(rule);
        assertNotSame(rule, rule2);

        rule2 = service.getRule(rule2.getName());
        assertEquals(rule, rule2);
    }

}
