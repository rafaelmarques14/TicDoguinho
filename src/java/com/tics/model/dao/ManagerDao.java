/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tics.model.dao;

import com.tics.model.negocio.Pet;
import com.tics.model.negocio.Tutor;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rafael
 */
public class ManagerDao {
    
    private static ManagerDao myself = null;
    
    public static ManagerDao getCurrentInstance(){
        if(myself == null)
            myself = new ManagerDao();
        
        return myself;
    }
    
    private EntityManagerFactory emf = null;
            
    private ManagerDao(){
        this.emf = Persistence.createEntityManagerFactory("TicDoguinhoPU");
    } 
    
    public void insert(Object o){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(o);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
    
    public void update(Object o){
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
                
        em.merge(o);
        em.getTransaction().commit();
        em.close();
    }
    
    public List read(String query,Class c){
        
        EntityManager em = emf.createEntityManager();
        
        List returnedList = em.createQuery(query,c).getResultList();
        
        em.close();
        
        return returnedList;
    }
    
    public List reads(String query,Class c, Map<String, Object> param){
        
        EntityManager em = emf.createEntityManager();
        
        TypedQuery<?> typedQuery = em.createQuery(query,c);
        
        for(Map.Entry<String, Object> entry :param.entrySet()){
             typedQuery.setParameter(entry.getKey(),entry.getValue());
        }

        List returnedList = typedQuery.getResultList();
        
        em.close();
        
        return returnedList;
    }
    
    public void delete(Object o){
        EntityManager em = emf.createEntityManager();
        
        Object oDelete = o;
        
        if(!em.contains(o)){
            oDelete = em.merge(o);
        }
        em.getTransaction().begin();
        
        em.remove(oDelete);
        em.getTransaction().commit();
        em.close();
    }

    public List<Pet> read(String jpql, Class<Pet> aClass, Map<String, Object> params) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object createQuery(String query, Class<Pet> aClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
}
