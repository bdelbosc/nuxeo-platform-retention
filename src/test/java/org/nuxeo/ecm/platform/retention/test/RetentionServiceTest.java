package org.nuxeo.ecm.platform.retention.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.platform.retention.RetentionService;
import org.nuxeo.ecm.platform.retention.RuleDefinitionDescriptor;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.NXRuntimeTestCase;

public class RetentionServiceTest extends NXRuntimeTestCase {

    private static final Log log = LogFactory.getLog(RetentionServiceTest.class);

    @Override
    public void setUp() throws Exception {
        super.setUp();
        deployBundle("org.nuxeo.ecm.platform.configuration");
        deployBundle("org.nuxeo.ecm.platform.retention");
        deployBundle("org.nuxeo.ecm.platform.retention.test");
        // Set framework properties to be able to load the retention
        // properties files
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        String resourceDir = loader.getResource(".").getFile();
        Framework.getProperties().put("package.resource.dir", resourceDir);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testContrib() throws Exception {
        RetentionService service = Framework.getService(RetentionService.class);
        assertNotNull(service);
        assertFalse(service.getRuleDefinitionNames().isEmpty());
        RuleDefinitionDescriptor ruleDef = service.getRuleDefinition("updateState");
        assertNotNull(ruleDef);
    }

}
