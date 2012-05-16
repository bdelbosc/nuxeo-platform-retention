package org.nuxeo.ecm.platform.retention;

import java.util.List;

public interface RetentionService {

    /**
     * List the RuleDefinition names
     * 
     * @return
     */
    List<String> getRuleDefinitionNames();

    RuleDefinitionDescriptor getRuleDefinition(String name);

    // CRUD on Rule

    Rule createRule(Rule rule);

    Rule getRule(String id);

    Rule updateRule(Rule rule);

    void deleteRule(Rule rule);

    String[] listRuleIds();

    // run(Rule rule);

    // List<String> listRunningRules()

    // cancelRule(String id)

}
