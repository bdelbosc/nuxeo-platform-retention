package org.nuxeo.ecm.platform.retention;

public abstract class AbstractDAOFactory {
    public abstract DAO<Rule> getRuleDAO() throws Exception;

}
