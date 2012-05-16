package org.nuxeo.ecm.platform.retention;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.configuration.AbstractConfiguration;
import org.nuxeo.ecm.platform.configuration.ConfigurationService;
import org.nuxeo.runtime.api.Framework;

public class ConfigurationRuleDAO extends DAO<Rule> {
    protected AbstractConfiguration conf;

    protected final String NAME_PREFIX = "name";

    protected final String SEP = ".";

    private String configurationName;

    public ConfigurationRuleDAO(String configurationName) throws Exception {
        this.setConfigurationName(configurationName);
        ConfigurationService service = Framework.getService(ConfigurationService.class);
        conf = service.getConfiguration(configurationName);
    }

    protected String getPropertyName(String id, String propName) {
        if ("name".equals(propName)) {
            // keep a prefix to be able to list rule ids;
            return NAME_PREFIX + SEP + id;
        }
        return id + SEP + propName;
    }

    @Override
    public Rule find(String id) {
        Rule rule = new RuleImpl();
        rule.setName(id);
        rule.setEnable(conf.getBoolean(getPropertyName(id, "enable")));
        rule.setCronLine(conf.getString(getPropertyName(id, "cronLine")));
        rule.setDispositionParams(conf.getStringArray(getPropertyName(id,
                "dispositionParams")));
        rule.setFilterParams(conf.getStringArray(getPropertyName(id,
                "filterParams")));
        rule.setCronLine(conf.getString(getPropertyName(id, "cronLine")));
        rule.setStatus(conf.getString(getPropertyName(id, "status")));

        return rule;
    }

    @Override
    public String[] findAll() {
        Iterator<String> ids = conf.getKeys(NAME_PREFIX + SEP);
        ArrayList<String> ret = new ArrayList();
        while (ids.hasNext()) {
            ret.add(ids.next());
        }
        return (String[]) ret.toArray();
    }

    @Override
    public Rule create(Rule obj) {
        String id = obj.getName();
        conf.addProperty(getPropertyName(id, "name"), obj.getName());
        conf.addProperty(getPropertyName(id, "enable"), obj.isEnable());
        conf.addProperty(getPropertyName(id, "ruleDefinition"),
                obj.getRuleDefinition());
        conf.addProperty(getPropertyName(id, "status"), obj.getStatus());
        conf.addProperty(getPropertyName(id, "cronLine"), obj.getCronLine());
        conf.addProperty(getPropertyName(id, "filterParams"),
                obj.getFilterParams());
        conf.addProperty(getPropertyName(id, "dispositionParams"),
                obj.getDispositionParams());
        return obj;
    }

    @Override
    public Rule update(Rule obj) {
        String id = obj.getName();
        conf.setProperty(getPropertyName(id, "name"), obj.getName());
        conf.setProperty(getPropertyName(id, "enable"), obj.isEnable());
        conf.setProperty(getPropertyName(id, "ruleDefinition"),
                obj.getRuleDefinition());
        conf.setProperty(getPropertyName(id, "status"), obj.getStatus());
        conf.setProperty(getPropertyName(id, "cronLine"), obj.getCronLine());
        conf.setProperty(getPropertyName(id, "filterParams"),
                obj.getFilterParams());
        conf.setProperty(getPropertyName(id, "dispositionParams"),
                obj.getDispositionParams());
        return obj;
    }

    @Override
    public void delete(Rule obj) {
        String id = obj.getName();
        conf.clearProperty(getPropertyName(id, "name"));
        conf.clearProperty(getPropertyName(id, "enable"));
        conf.clearProperty(getPropertyName(id, "ruleDefinition"));
        conf.clearProperty(getPropertyName(id, "status"));
        conf.clearProperty(getPropertyName(id, "cronLine"));
        conf.clearProperty(getPropertyName(id, "filterParams"));
        conf.clearProperty(getPropertyName(id, "dispositionParams"));
    }

    public String getConfigurationName() {
        return configurationName;
    }

    public void setConfigurationName(String configurationName) {
        this.configurationName = configurationName;
    }

}
