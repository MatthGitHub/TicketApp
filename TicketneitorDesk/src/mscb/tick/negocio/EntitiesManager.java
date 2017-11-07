/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.gui.main.Main;

/**
 *
 * @author Administrador
 */
public class EntitiesManager {
    
    private static EntitiesManager estaClase;
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityManagerFactory emfPgm;
    private static EntityManagerFactory emfPrincipal;
    
    private EntitiesManager(){
        emf = Persistence.createEntityManagerFactory(Main.getServer());
        emfPgm = Persistence.createEntityManagerFactory("10.20.130.6");
        emfPrincipal = Persistence.createEntityManagerFactory("10.20.130.242");
        em = emf.createEntityManager();
    }
    
    public static EntitiesManager getEntitieManager(){
        if(estaClase == null){
            estaClase = new EntitiesManager();
        }
        return estaClase;
    }
    
    public static EntityManagerFactory getEntityManagerFactory(){
        if(emf == null){
            getEntitieManager();
        }
        return emf;
    }
    
     
    public static EntityManager getEnetityManager(){
        if(em == null){
            getEntitieManager();
        }
        return em;
    }
    
    public static void destruirEntityManager(){
        emf.getCache().evictAll();
        em = null;
        emf = null;
        estaClase = null;
    }
    
    public static EntityManagerFactory getEmfPgm(){
        if(emfPgm == null){
            getEntitieManager();
        }
        return emfPgm;
    }
    
    public static EntityManagerFactory getEmfPrincipal(){
        if(emfPrincipal == null){
            getEntitieManager();
        }
        return emfPrincipal;
    }
}
