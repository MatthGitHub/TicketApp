/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.asuntoPrincipal.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.AsuntoPrincipalJpaController;
import mscb.tick.entidades.AsuntoPrincipal;

/**
 *
 * @author Administrador
 */
public class AsuntoPrincipalServ {
    
    public List <AsuntoPrincipal> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoPrincipalJpaController jpa = new AsuntoPrincipalJpaController(emf);
        
        return jpa.findAsuntoPrincipalEntities();
    }
    
    public boolean nuevoAsunto(AsuntoPrincipal nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoPrincipalJpaController jpa = new AsuntoPrincipalJpaController(emf);
        
        try{
            jpa.create(nuevo);
            return true;
        }catch(Exception e){
            System.out.println(e+"- Nuevo AsuntoPrincipal");
            return false;
        }
    }
    
    public boolean eliminarAsunto(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoPrincipalJpaController jpa = new AsuntoPrincipalJpaController(emf);
        
        try{
            jpa.destroy(id);
            return true;
        }catch(Exception e){
            System.out.println(e + "- Eliminar AsuntoPrincipal");
            return false;
        }
    }
    
    
}
