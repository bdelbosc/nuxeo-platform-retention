package org.nuxeo.ecm.platform.retention;

import org.apache.commons.configuration.AbstractConfiguration;

/**
 * Rule that is persisted using the configuration service.
 * 
 * @author ben
 * 
 */
public class ConfRule implements Rule {

    protected AbstractConfiguration conf;

    protected String name;

    protected String ruleDefinitionName;

    protected Boolean enable;

    protected String status;

    protected String cronLine;

    protected String[] filterParams;

    protected String[] dispositionParams;

    public ConfRule(AbstractConfiguration conf) {
        super();
        this.conf = conf;
    }

    protected String getPropertyName(String propName) {
        if ("name".equals(propName)) {
            // keep a prefix to be able to list rule ids;
            return "name." + name;
        }
        return name + "." + propName;
    }

    @Override
    public void load(String name) {
        this.name = name;
        setEnable(conf.getBoolean(getPropertyName("enable")));
        setCronLine(conf.getString(getPropertyName("cronLine")));
        setDispositionParams(conf.getStringArray(getPropertyName("dispositionParams")));
        setFilterParams(conf.getStringArray(getPropertyName("filterParams")));
        setCronLine(conf.getString(getPropertyName("cronLine")));
        setStatus(conf.getString(getPropertyName("status")));
    }

    @Override
    public void save() {
        conf.setProperty(getPropertyName("name"), name);
        conf.setProperty(getPropertyName("enable"), enable);
        conf.setProperty(getPropertyName("ruleDefinition"), ruleDefinitionName);
        conf.setProperty(getPropertyName("status"), status);
        conf.setProperty(getPropertyName("cronLine"), cronLine);
        conf.setProperty(getPropertyName("filterParams"), filterParams);
        conf.setProperty(getPropertyName("dispositionParams"),
                dispositionParams);
    }

    @Override
    public void reload() {
        load(getName());
    }

    @Override
    public String getRuleDefinition() {
        return ruleDefinitionName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isEnable() {
        return enable;
    }

    @Override
    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getCronLine() {
        return cronLine;
    }

    @Override
    public void setCronLine(String cronLine) {
        this.cronLine = cronLine;
    }

    @Override
    public String[] getFilterParams() {
        return filterParams;
    }

    @Override
    public void setFilterParams(String[] params) {
        this.filterParams = params;
    }

    @Override
    public String[] getDispositionParams() {
        return dispositionParams;
    }

    @Override
    public void setDispositionParams(String[] params) {
        this.dispositionParams = params;
    }

}
