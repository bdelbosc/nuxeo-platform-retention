package org.nuxeo.ecm.platform.retention;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("ruleDefinition")
public class RuleDefinitionDescriptor {
    @XNode("@name")
    protected String name;

    @XNode("@enabled")
    protected boolean enabled = true;

    @XNode("@schedulable")
    protected boolean schedulable = true;

    @XNode("@filterPageProvider")
    protected String filterPageProvider;

    @XNodeList(value = "filterParams", type = String[].class, componentType = String.class)
    protected String[] filterParams = new String[0];

    @XNode("@dispositionChain")
    protected String dispositionChain;

    @XNodeList(value = "chainParams", type = String[].class, componentType = String.class)
    protected String[] chainParams = new String[0];

    public String getName() {
        return name;
    }

    public String getFilterPageProvider() {
        return filterPageProvider;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isSchedulable() {
        return schedulable;
    }

    public String[] getFilterParams() {
        return filterParams;
    }

    public String getDispositionChain() {
        return dispositionChain;
    }

    public String[] getChainParams() {
        return chainParams;
    }

}
