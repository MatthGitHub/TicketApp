/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.EstadoActualPgmJpaController;
import mscb.tick.negocio.entidades.EstadoActualPgm;

/**
 *
 * @author Administrador
 */
public class EstadoActualPgmServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    EstadoActualPgmJpaController jpa = new EstadoActualPgmJpaController(emf);
    
    public EstadoActualPgm traerEstadoActual(int id){
        return jpa.findEstadoActualPgm(id);
    }
    
    public boolean modificarEstadoActual(EstadoActualPgm eap){
        try {
            jpa.edit(eap);
            System.out.println("Estado actual de pgm modificado correctamente");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(EstadoActualPgmServ.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al modificar Estado actual de pgm - "+ex);
            return false;
        }
    }
}
