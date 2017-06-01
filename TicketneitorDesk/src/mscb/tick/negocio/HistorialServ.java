/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.HistorialTicketsJpaController;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.Usuarios;

/**
 *
 * @author Administrador
 */
public class HistorialServ {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    private HistorialTicketsJpaController jpa = new HistorialTicketsJpaController(emf);
    private static HistorialServ esto;
    
    private HistorialServ(){
        
    }
    
    public static HistorialServ getHistorialServ(){
        if(esto == null){
            esto = new HistorialServ();
        }
        return esto;
    }
    
    public List<HistorialTickets> traerTodos(){
        return jpa.findHistorialTicketsEntities();
    }
    
    public List<HistorialTickets> traerTodosResueltos(){
        List<HistorialTickets> aux = new ArrayList<>();
        List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getFkEstado().getIdEstado() == 5){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public List<HistorialTickets> traerTodosResueltosPorArea(Areas area){
        List<HistorialTickets> aux = new ArrayList<>();
        List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        
        for(int i = 0; i < miLista.size(); i++){
            if((miLista.get(i).getFkEstado().getIdEstado() == 5)&&(miLista.get(i).getFkTicket().getServicio().getPertenece().getFkArea().equals(area))){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    
    public HistorialTickets buscarUnoPorId(int id){
        return jpa.findHistorialTickets(id);
    }
    
    public HistorialTickets buscarUno(Usuarios usuario, Tickets ticket){
        List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        
        
        for(int i = 0; i < miLista.size(); i++){
            if((miLista.get(i).getFkTicket().equals(ticket))&&(miLista.get(i).getFkUsuario().equals(usuario))){
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
    
    public HistorialTickets buscarUltimo(Tickets miTick){
        //List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        //List<HistorialTickets> aux = new ArrayList<>();
        //List<HistorialTickets> aux2 = new ArrayList<>();
        HistorialTickets mayor = new HistorialTickets();
        List<HistorialTickets> miLista = miTick.getHistorialTicketsList();
        mayor = miLista.get(0);
        
        //Busco todos los historiales del ticket 
        /*for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getFkTicket().equals(miTick)){
                aux.add(miLista.get(i));
                mayor = miLista.get(i);
            }
        }*/
       
        // Ordeno para buscar el mayor
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getIdHistorial() > mayor.getIdHistorial()){
                mayor = miLista.get(i);
            }
        }
        return mayor;
    }
    
}
