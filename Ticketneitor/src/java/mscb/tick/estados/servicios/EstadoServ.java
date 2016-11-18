/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.estados.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.EstadosJpaController;
import mscb.tick.entidades.Estados;

/**
 *
 * @author Administrador
 */
public class EstadoServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    EstadosJpaController jpa = new EstadosJpaController(emf);
   
    public Estados traerEstado(int id){
        return jpa.findEstados(id);
    }
    
    public List<Estados> traerTodos(){
        return jpa.findEstadosEntities();
    }
}
