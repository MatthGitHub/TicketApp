/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.estadosPgm.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.EstadosPgmJpaController;
import mscb.tick.entidades.EstadosPgm;

/**
 *
 * @author Administrador
 */
public class EstadosPgmServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    EstadosPgmJpaController jpa = new EstadosPgmJpaController(emf);
    
    public List<EstadosPgm> traerTodos(){
        return jpa.findEstadosPgmEntities();
    }
    
    public EstadosPgm traerUno(int id){
        return jpa.findEstadosPgm(id);
    }
    
    
}
