/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.EmpleadosJpaController;
import mscb.tick.negocio.entidades.Empleados;

/**
 *
 * @author Administrador
 */
public class EmpleadoServ {
    private static Query q;
    private String PU = Main.getServer();
    private EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private EmpleadosJpaController jpa = new EmpleadosJpaController(emf);
    private static EmpleadoServ esto;
    
    private EmpleadoServ(){
        
    }
    
    public static EmpleadoServ getEmpleadoServ(){
        if(esto == null){
            esto = new EmpleadoServ();
        }
        return esto;
    }
    
    public List <Empleados> traerTodos(){
        return jpa.findEmpleadosEntities();
    }
    
    public List <Empleados> traerTodosPorArea(){
         EntityManager em = emf.createEntityManager();
        
        q = em.createNativeQuery("SELECT * "
                + "FROM empleados "
                + "WHERE fk_area = ?1 ",Empleados.class);
        q.setParameter(1, LoginEJB.usuario.getFkEmpleado().getFkArea().getIdArea());
        return q.getResultList();
    }
    
    public Empleados traerEmpleado(int id){
        return jpa.findEmpleados(id);
    }
    
    public List<Empleados> traerEmpleados(String patron){
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT DISTINCT e "
                + "FROM Empleados e "
                + "WHERE e.nombre LIKE :patron "
                + "OR e.apellido LIKE :patron ");
        q.setParameter("patron", "%"+ patron+"%");
        return q.getResultList();
    }
    
    public List<Empleados> traerEmpleadosPorArea(String patron){
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT DISTINCT e "
                + "FROM Empleados e "
                + "WHERE fk_area = ?1 "
                + "AND (e.nombre LIKE :patron "
                + "OR e.apellido LIKE :patron) ");
        q.setParameter("patron", "%"+ patron+"%");
        q.setParameter(1, LoginEJB.usuario.getFkEmpleado().getFkArea().getIdArea());
        return q.getResultList();
    }
    
    public boolean nuevo(Empleados nuevo){
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Error al crear nuevo empleado");
            return false;
        }
    }
}
