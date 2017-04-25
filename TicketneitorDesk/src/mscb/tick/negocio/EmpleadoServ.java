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
import mscb.tick.negocio.controladores.EmpleadosJpaController;
import mscb.tick.negocio.entidades.Empleados;

/**
 *
 * @author Administrador
 */
public class EmpleadoServ {
    private static Query q;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    EmpleadosJpaController jpa = new EmpleadosJpaController(emf);
        
    public List <Empleados> traerTodos(){
        return jpa.findEmpleadosEntities();
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