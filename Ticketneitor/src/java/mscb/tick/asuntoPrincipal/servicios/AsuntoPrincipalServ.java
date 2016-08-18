/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.asuntoPrincipal.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.AsuntosJpaController;
import mscb.tick.controladores.ServiciosJpaController;
import mscb.tick.entidades.Asuntos;

/**
 *
 * @author Administrador
 */
public class AsuntoPrincipalServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    AsuntosJpaController jpa = new AsuntosJpaController(emf);
    
    public List <Asuntos> traerTodos(){
        return jpa.findAsuntosEntities();
    }
    
    public boolean nuevoAsunto(Asuntos nuevo){
        try{
            jpa.create(nuevo);
            return true;
        }catch(Exception e){
            System.out.println(e+"- Nuevo AsuntoPrincipal");
            return false;
        }
    }
    
    public boolean eliminarAsunto(int id){
        try{
            jpa.destroy(id);
            return true;
        }catch(Exception e){
            System.out.println(e + "- Eliminar AsuntoPrincipal");
            return false;
        }
    }
    
    
}
