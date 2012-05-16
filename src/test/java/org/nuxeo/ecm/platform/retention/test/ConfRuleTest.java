package org.nuxeo.ecm.platform.retention.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.nuxeo.ecm.platform.configuration.ConfigurationService;
import org.nuxeo.ecm.platform.retention.ConfRule;
import org.nuxeo.ecm.platform.retention.Rule;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.NXRuntimeTestCase;

public class ConfRuleTest extends NXRuntimeTestCase {

    private static final Log log = LogFactory.getLog(ConfRuleTest.class);

    private ConfigurationService service;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        deployBundle("org.nuxeo.ecm.platform.configuration");
        deployBundle("org.nuxeo.ecm.platform.retention");
        deployBundle("org.nuxeo.ecm.platform.retention.test");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String resourceDir = loader.getResource(".").getFile();
        Framework.getProperties().put("package.resource.dir", resourceDir);
        deployContrib("org.nuxeo.ecm.platform.retention.test",
                "OSGI-INF/test-configuration-contrib.xml");
        service = Framework.getService(ConfigurationService.class);
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testPersistence() throws Exception {
        AbstractConfiguration conf = service.getConfiguration("main");
        assertNotNull(conf);
        Rule rule = new ConfRule(conf);

        rule.setName("foo");
        rule.setCronLine("* * * 1");
        String[] dParams = new String[] { "value1", "value2" };
        rule.setDispositionParams(dParams);
        rule.setEnable(true);
        rule.setStatus("In progress");
        String[] fParams = new String[] { "value1", "value2", "value3" };
        rule.setFilterParams(fParams);
        rule.save();

        Rule rule2 = new ConfRule(conf);
        rule2.load("foo");
        assertEquals(rule.getStatus(), rule2.getStatus());
        assertEquals(rule.getFilterParams().length,
                rule2.getFilterParams().length);
        assertEquals(rule.getDispositionParams().length,
                rule2.getDispositionParams().length);
        assertTrue(rule2.isEnable());

    }

}
