/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.historial.servicios;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.HistorialTicketsJpaController;
import mscb.tick.entidades.HistorialTickets;
import mscb.tick.entidades.Tickets;
import mscb.tick.entidades.Usuarios;

/**
 *
 * @author Administrador
 */
public class HistorialServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    HistorialTicketsJpaController jpa = new HistorialTicketsJpaController(emf);
    
    public List<HistorialTickets> traerTodos(){
        return jpa.findHistorialTicketsEntities();
    }
    
    public HistorialTickets buscarUnoPorId(int id){
        return jpa.findHistorialTickets(id);
    }
    
    public HistorialTickets buscarUno(Usuarios usuario, Tickets ticket){
        List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        
        
        for(int i = 0; i < miLista.size(); i++){
            if((miLista.get(i).getFkTicket().equals(ticket))&&(miLista.get(i).getFkUsuarioEmisor().equals(usuario))){
                return miLista.get(i);
            }
        }
        System.out.println("No encontro el historial de ticket");
        HistorialTickets ht = new HistorialTickets();
        ht.setIdHistorial(0);
        return ht;
    }
    
    public boolean modificar(HistorialTickets modif){
        try {
            jpa.edit(modif);
            System.out.println("Historial de Ticket modificado correctamente");
            return true;
        } catch (Exception ex) {
            Logger.getLogger(HistorialServ.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex+" - Error al modificar Historial de ticket");
            return false;
        }
    }
    
    public boolean nuevo(HistorialTickets nuevo){
        try {
            jpa.create(nuevo);
            System.err.println("Historial de ticket nuevo creado");
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Error al crear nuevo historial de ticket");
            return false;
        }
        
    }
}
