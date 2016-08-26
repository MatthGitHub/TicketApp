/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.razones.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.RazonesTransferenciasJpaController;
import mscb.tick.entidades.RazonesTransferencias;

/**
 *
 * @author Administrador
 */
public class RazoneServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    RazonesTransferenciasJpaController jpa = new RazonesTransferenciasJpaController(emf);
    
    public List<RazonesTransferencias> traerTodos(){
        return jpa.findRazonesTransferenciasEntities();
    }
}
