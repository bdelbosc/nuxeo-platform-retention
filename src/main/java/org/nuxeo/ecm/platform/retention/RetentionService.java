package org.nuxeo.ecm.platform.retention;

import java.util.List;


public interface RetentionService {

    /**
     * List the RuleDefinition names
     * @return
     */
    List<String> getRuleDefinitionNames();
    
    RuleDefinitionDescriptor getRuleDefinition(String name);
    
    
    // List<String> listRuleIds()

    // deleteRule(String id);

    // Rule saveRule(Rule rule)

    // Rule getRule(String id)

    // run(Rule rule);
    
    // List<String> listRunningRules()
    
    // cancelRule(String id)
    
   
}
