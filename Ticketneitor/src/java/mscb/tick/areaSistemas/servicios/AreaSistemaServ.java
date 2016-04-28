/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.areaSistemas.servicios;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.AreaSistemasJpaController;
import mscb.tick.entidades.AreaSistemas;

/**
 *
 * @author Administrador
 */
public class AreaSistemaServ {
    
    public AreaSistemas traerUno(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AreaSistemasJpaController jpa = new AreaSistemasJpaController(emf);
        return jpa.findAreaSistemas(id);
    }
}
