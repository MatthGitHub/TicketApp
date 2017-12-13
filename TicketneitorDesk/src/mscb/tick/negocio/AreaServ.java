/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.AreasJpaController;
import mscb.tick.negocio.entidades.Areas;

/**
 *
 * @author Administrador
 */
public class AreaServ {
    private String PU = Main.getServer();
    private EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private AreasJpaController jpa = new AreasJpaController(emf);
    private List<Areas> miLista;
    private static AreaServ esto;
    private static Query q;
    
    
    private AreaServ(){
        
    }
    
    public static AreaServ getAreaServ(){
        if(esto == null){
            esto = new AreaServ();
        }
        return esto;
    }
    
    public List<Areas> traerTodasconAsuntos(){
         miLista = jpa.findAreasEntities();
         List<Areas> aux = new ArrayList<>();
         
         for(int i = 0; i < miLista.size(); i++){
             if(!miLista.get(i).getAsuntosList().isEmpty()){
                 aux.add(miLista.get(i));
             }
         }
         return aux;
    }
    
    public List<Areas> traerTodas(){
        return jpa.findAreasEntities();
    }
    
    public boolean nueva(Areas miArea){
        try {
            jpa.create(miArea);
            emf.getCache().evict(Areas.class);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Error al crear nueva area");
            return false;
        }
        
    }
    
    public boolean eliminar(int id){
        try {
            jpa.destroy(id);
            return true;
        } catch (Exception e) {
            System.err.println(e+" - Error al eliminar area");
            return false;
        }
    }
    
    public boolean modificar(Areas miArea){
        try {
            jpa.edit(miArea);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Error al modificar area");
            return false;
        }
    }
    
    
    public Areas getArea(Integer id){
        return jpa.findAreas(id);
    }
    
    /**
     * Busca areas segun el patron dado, pueden ser n areas.
     * @param patron
     * @return 
     */
    public List<Areas> buscar(String patron){
        EntityManager em = emf.createEntityManager();
        Query q;
        
        q = em.createQuery("SELECT DISTINCT a "
                + "FROM Areas a "
                + "WHERE a.nombreArea LIKE :patron");
        q.setParameter("patron", "%"+ patron+"%");
        return q.getResultList();
    }
    
    /**
     * Busca un area especifica por nombre
     * @param nombre
     * @return 
     */
    public Areas getAreaPorNombre(String nombre){
        EntityManager em = EntitiesManager.getEnetityManager();
        Areas aux;
        q = em.createNativeQuery("SELECT *"
                                + " FROM areas"
                                + " WHERE nombre_area LIKE ?1",Areas.class);
        q.setParameter(1, nombre);
        try {
            aux = (Areas) q.getSingleResult();
        } catch (Exception e) {
            aux = null;
            System.err.println("Error en getSolicitante: "+e);
        }
        
        /*em.clear();
        em.close();*/
        return aux;
    }
    
    public Areas getSolicitantePorCodigo(String codigo){
        EntityManager em = EntitiesManager.getEnetityManager();
        Areas aux;
        q = em.createNativeQuery("SELECT *"
                                + " FROM areas"
                                + " WHERE codigo = ?1",Areas.class);
        q.setParameter(1, codigo);
        try {
            aux = (Areas) q.getSingleResult();
        } catch (Exception e) {
            aux = null;
            System.err.println("Error en getSolicitantePorCodigo: "+e);
        }
        
        /*em.clear();
        em.close();*/
        return aux;
    } 
}
