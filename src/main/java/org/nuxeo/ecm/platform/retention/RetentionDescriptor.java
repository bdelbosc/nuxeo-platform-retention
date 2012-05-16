package org.nuxeo.ecm.platform.retention;

import org.nuxeo.common.xmap.annotation.XNode;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject("retention")
public class RetentionDescriptor {
    @XNode("@persistenceType")
    protected String persistenceType;

    @XNode("@persistenceName")
    protected String persistenceName;

    @XNode("@enabled")
    protected boolean enabled = true;

    public String getPersistenceType() {
        return persistenceType;
    }
    
    public String getPersistenceName() {
        return persistenceName;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
