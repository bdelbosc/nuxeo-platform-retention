package org.nuxeo.ecm.platform.retention;

public interface Rule {

    void load(String id);

    void save();

    void reload();

    String getRuleDefinition();

    String getName();

    void setName(String name);

    boolean isEnable();

    void setEnable(boolean enable);

    String getStatus();

    void setStatus(String status);

    // trigger
    String getCronLine();

    void setCronLine(String cronline);

    String[] getFilterParams();

    void setFilterParams(String[] params);

    String[] getDispositionParams();

    void setDispositionParams(String[] params);

    /*
     * - batch size: the number of document to process by transaction.
     * 
     * - naptime: the time to sleep between transaction
     * 
     * - execution information
     * 
     * - start time
     * 
     * - end time
     * 
     * - eta (estimated end of completion)
     * 
     * - duration
     * 
     * - doc processed
     * 
     * - doc in error
     * 
     * - total number of document to process
     */
}
