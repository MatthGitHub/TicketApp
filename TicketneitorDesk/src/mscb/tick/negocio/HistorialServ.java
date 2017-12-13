/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.gui.main.Main;
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
    private static String PU = Main.getServer();
    private static EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private HistorialTicketsJpaController jpa = new HistorialTicketsJpaController(emf);
    private static HistorialServ esto;
    
    private HistorialServ(){
        
    }
    
    public static HistorialServ getHistorialServ(){
        if(esto == null){
            esto = new HistorialServ();
        }
        emf.getCache().evict(HistorialTickets.class);
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
    /**
     * Trae todos los tickets resueltos de los servicios que tenga el usuario
     * @param area
     * @return 
     */
    public List<HistorialTickets> traerTodosResueltosPorServicios(){
        //List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        //List<HistorialTickets>  aux = new ArrayList<>();
        
        EntityManager em = EntitiesManager.getEnetityManager();
        Query q;
        List<HistorialTickets> aux2 = new ArrayList<>();
        
        /*for(int i = 0; i < miLista.size(); i++){
            if((miLista.get(i).getFkEstado().getIdEstado() == 5)&&(miLista.get(i).getFkTicket().getServicio().getPertenece().getFkArea().equals(area))){
                aux.add(miLista.get(i));
            }
        }*/
        
        
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t\n" +
        "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
        "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
        "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n"+
        "JOIN usuarios u ON u.id_usuario = t.creador\n" +
        "JOIN empleados em ON u.fk_empleado = em.id_empleado\n" +
        "WHERE ht.fk_estado = 5\n" +
        " AND ht.id_historial IN \n" +
        "	(SELECT id_historial \n" +
        "	FROM (SELECT MAX(id_historial) as id_historial,fk_ticket \n" +
        "			FROM historial_tickets GROUP by fk_ticket ) AS ht2)\n" +
        " AND es.usuario = ?1 \n" +
        "ORDER by t.id_ticket",HistorialTickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        aux2 = q.getResultList();
        
        //System.out.println("Aux2:"+aux2.size()+" - Aux:"+aux.size());
        
        /*for(int i = 0 ; i < aux2.size() ; i++){
            if(aux.contains(aux2.get(i))){
                aux.remove(aux2.get(i));
            }
        }
        
        for(int i = 0; i < aux.size(); i++){
            System.out.println("Aux:"+aux.get(i).getIdHistorial());
        }*/
        
        return aux2;
    }
    
    public List<HistorialTickets> buscarTodosResueltosPorServicios(String id){
        List<HistorialTickets> aux;
        EntityManager em = EntitiesManager.getEnetityManager();
        Query q;
        /*List<HistorialTickets> miLista = jpa.findHistorialTicketsEntities();
        
        for(int i = 0; i < miLista.size(); i++){
            if((miLista.get(i).getFkEstado().getIdEstado() == 5)
            &&(miLista.get(i).getFkTicket().getServicio().getPertenece().getFkArea().equals(area))
            &&((miLista.get(i).getFkTicket().getIdTicket().toString().equals(id))
                ||(miLista.get(i).getFkTicket().getCreador().getNombreUsuario().equals(id)))){
                aux.add(miLista.get(i));
            }
        }*/
        
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t\n" +
        "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
        "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
        "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n"+
        "JOIN usuarios u ON u.id_usuario = t.creador\n" +
        "JOIN empleados em ON u.fk_empleado = em.id_empleado\n" +
        "WHERE ht.fk_estado = 5\n" +
        " AND ht.id_historial IN \n" +
        "	(SELECT id_historial \n" +
        "	FROM (SELECT MAX(id_historial) as id_historial,fk_ticket \n" +
        "			FROM historial_tickets GROUP by fk_ticket ) AS ht2)\n" +
        " AND es.usuario = ?1 \n"+ 
        "AND ((s.nombre_asuntoS LIKE ?2\n" +
                                "OR u.nombre_usuario LIKE ?2\n"+
                                "OR t.patrimonio LIKE ?2\n"+
                                "OR t.nota_entrada LIKE ?2\n"+
                                "OR t.nota_salida LIKE ?2\n"+
                                "OR t.observacion LIKE ?2)\n"+
                                "OR t.id_ticket = ?3)\n"+
        "ORDER by t.id_ticket",HistorialTickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        q.setParameter(2, "%"+id+"%");
        q.setParameter(3,id);
        aux = q.getResultList();
        
        
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
         HistorialTickets miHis;
        miHis = new HistorialTickets();
        List<HistorialTickets> miLista = miTick.getHistorialTicketsList();
        miHis = miLista.get(0);
       
        // Ordeno para buscar el mayor
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getIdHistorial() > miHis.getIdHistorial()){
                miHis = miLista.get(i);
            }
        }
        //ANDA MUY LENTO EL CODIGO DE ABAJO!
        /*EntityManager em = EntitiesManager.getEnetityManager();
        Query q = em.createNativeQuery("SELECT *\n" +
                                        "FROM historial_tickets\n" +
                                        "WHERE id_historial IN (SELECT MAX(id_historial)\n" +
                                        "FROM historial_tickets \n" +
                                        "WHERE fk_ticket = ?1)",HistorialTickets.class);
        q.setParameter(1, miTick.getIdTicket());
        
        miHis = (HistorialTickets) q.getSingleResult();*/
        //System.out.println("Ticket: "+miHis.getFkTicket()+" Historial: "+miHis.getIdHistorial());
        
        
        return miHis;
    }
    
}
