package org.nuxeo.ecm.platform.retention;

public abstract class DAO<T> {

    /**
     * Get an obect by id
     * 
     * @param id
     * @return
     */
    public abstract T find(String id);

    
    /**
     * List all object ids
     * 
     * @return Array of ids
     */
    public abstract String[] findAll();
    
    
    /**
     * Persist a new object
     * 
     * @param obj
     */
    public abstract T create(T obj);

    /**
     * Update an existing object
     * 
     * @param obj
     */
    public abstract T update(T obj);

    /**
     * Remove an object
     * 
     * @param obj
     */
    public abstract void delete(T obj);
}
