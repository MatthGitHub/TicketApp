/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.empleados.servicios;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.controladores.EmpleadoJpaController;
import mscb.tick.entidades.Empleado;

/**
 *
 * @author Administrador
 */
public class EmpleadoServ {
    private static Query q;
    
    public List <Empleado> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        EmpleadoJpaController jpa = new EmpleadoJpaController(emf);
        
        return jpa.findEmpleadoEntities();
    }
    
    public Empleado traerEmpleado(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        EmpleadoJpaController jpa = new EmpleadoJpaController(emf);
        
        return jpa.findEmpleado(id);
    }
    
    public List<Empleado> traerEmpleados(String patron){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        EntityManager em = emf.createEntityManager();
        EmpleadoJpaController jpa = new EmpleadoJpaController(emf);
        
        q = em.createQuery("SELECT DISTINCT e "
                + "FROM Empleado e "
                + "WHERE e.nombre LIKE :patron "
                + "OR e.apellido LIKE :patron ");
        q.setParameter("patron", "%"+ patron+"%");
        return q.getResultList();
    }
    
    
}
