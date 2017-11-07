/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import javax.persistence.EntityManagerFactory;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.TicketsAdjuntosJpaController;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.TicketsAdjuntos;
import mscb.tick.negocio.entidades.TicketsAdjuntosPK;

/**
 *
 * @author Administrador
 */
public class TicketsAdjuntosServ {
    private static TicketsAdjuntosServ estaClase;
    private static String PU = Main.getServer();
    private static EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private TicketsAdjuntosJpaController jpa = new TicketsAdjuntosJpaController(emf);
    
    private TicketsAdjuntosServ(){
        
    }
    
    public static TicketsAdjuntosServ getTicketsAdjuntosServ(){
        if(estaClase == null){
            estaClase = new TicketsAdjuntosServ();
        }
        return estaClase;
    }
    
    
    public void nuevoAdjuntoTicket(TicketsAdjuntos nuevo) throws Exception{
        jpa.create(nuevo);
    }
    
    public TicketsAdjuntos getAdjuntoTicket(TicketsAdjuntosPK id){
        return jpa.findTicketsAdjuntos(id);
    }
    
    public void removeAdjuntoTicket(TicketsAdjuntos id) throws NonexistentEntityException{
        jpa.destroy(id.getTicketsAdjuntosPK());
    }
    
}
