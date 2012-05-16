package org.nuxeo.ecm.platform.retention;

public class ConfigurationDAOFactory extends AbstractDAOFactory {

    protected String configurationName;
    
    public ConfigurationDAOFactory(String configurationName) {
        super();
        this.configurationName = configurationName;
    }

    @Override
    public DAO<Rule> getRuleDAO() throws Exception {
        return new ConfigurationRuleDAO(configurationName);
    }
}
