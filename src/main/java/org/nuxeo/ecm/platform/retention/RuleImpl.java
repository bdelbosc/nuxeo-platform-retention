package org.nuxeo.ecm.platform.retention;


/**
 * Rule that is persisted using the configuration service.
 * 
 * @author ben
 * 
 */
public class RuleImpl implements Rule {

    protected String name;

    protected String ruleDefinitionName;

    protected Boolean enable;

    protected String status;

    protected String cronLine;

    protected String[] filterParams;

    protected String[] dispositionParams;

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
