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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.negocio.controladores.TicketsJpaController;
import mscb.tick.negocio.controladores.exceptions.IllegalOrphanException;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Tickets;

//import mscb.tick.login.Login;

/**
 *
 * @author Administrador
 */
public class TicketServ {
    private static Query q;
    
    private EstadoServ estad;
    private static TicketServ esto;
    
    
    private TicketServ(){
        
    }
     
    public static TicketServ getTicketServ(){
        if(esto == null){
            esto = new TicketServ();
        }
        return esto;
    }
    public int nuevoTicket(Tickets nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        EntityManager em = emf.createEntityManager();
        try{
            jpa.create(nuevo);
            return 0;
            
        }catch(Exception e){
            System.out.print(e+" Error en la carga de ticket");
            return 1;
        }
    }
    
    public List<Tickets> traerTodos(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
            TicketsJpaController jpa = new TicketsJpaController(emf);
            return jpa.findTicketsEntities();
        } catch (Exception e) {
            System.out.println("No se loguearon aun o no hay conexion con el servidor: "+e);
            return null;
        }
        
    }
    
    public List<Tickets> traerTodosPorArea(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        
        for(int i = 0; i < miLista.size(); i++){
            if(LoginEJB.usuario.getFkEmpleado().getFkArea().equals(miLista.get(i).getServicio().getPertenece().getFkArea())){
                aux.add(miLista.get(i));
            }
        }
        
        
        return aux;
    }
    
    public List<Tickets> traerTodosPorArea(Areas area){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        EntityManager em = emf.createEntityManager();
        
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();

        em.getTransaction().begin();
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t \n" +
                                "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
                                "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
                                "JOIN estados e ON e.id_estado = ht.fk_estado\n" +
                                "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n"+
                                "JOIN usuarios u ON u.id_usuario = t.creador\n" +
                                "JOIN empleados em ON u.fk_empleado = em.id_empleado\n"+
                                "WHERE e.id_estado NOT IN (5,7)"+
                                " AND ht.id_historial IN "+
                                "(SELECT id_historial FROM "+
                                "(SELECT MAX(id_historial) as id_historial,fk_ticket "+
                                "FROM historial_tickets GROUP by fk_ticket ) AS ht2)"+
                                " AND es.usuario = ?1 "+
                                " AND em.fk_area = ?2 "+
                                "ORDER by t.id_ticket",Tickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        q.setParameter(2, area.getIdArea());
        aux = q.getResultList();
        return aux;
    }
    
    
     public List <Tickets> buscar(String id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        EntityManager em = emf.createEntityManager();
        
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        
        em.getTransaction().begin();
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t \n" +
                                "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
                                "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
                                "JOIN asuntos a ON a.id_asuntoP = s.pertenece\n" +
                                "JOIN estados e ON e.id_estado = ht.fk_estado\n" +
                                "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n" +
                                "JOIN usuarios u2 ON u2.id_usuario = t.creador\n" +
                                "LEFT JOIN usuarios u3 ON u3.id_usuario = ht.fk_usuario\n"+
                                "LEFT JOIN edificios ed ON t.fkEdificio = ed.id_edificio\n" +
                                "WHERE e.id_estado NOT IN (5,7)"+
                                " AND ht.id_historial IN "+
                                "(SELECT id_historial FROM "+
                                "(SELECT MAX(id_historial) as id_historial,fk_ticket "+
                                "FROM historial_tickets GROUP by fk_ticket ) AS ht2)"+
                                " AND es.usuario = ?1 "+
                                "AND ((s.nombre_asuntoS LIKE ?2\n" +
                                "OR a.nombre LIKE ?2\n"+
                                "OR ed.nombre LIKE ?2\n"+
                                "OR u2.nombre_usuario LIKE ?2\n"+
                                "OR u3.nombre_usuario LIKE ?2\n"+
                                "OR t.patrimonio LIKE ?2\n"+
                                "OR t.observacion LIKE ?2)\n"+
                                "OR t.id_ticket = ?3)\n"+
                                "ORDER by t.id_ticket",Tickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        q.setParameter(2, "%"+id+"%");
        q.setParameter(3,id);
        aux = q.getResultList();
        return aux;
    }
     
     public List <Tickets> buscarTodos(String id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        EntityManager em = emf.createEntityManager();
        
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        
        em.getTransaction().begin();
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t \n" +
                                "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
                                "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
                                "JOIN asuntos a ON a.id_asuntoP = s.pertenece\n" +
                                "JOIN estados e ON e.id_estado = ht.fk_estado\n" +
                                "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n" +
                                "JOIN usuarios u2 ON u2.id_usuario = t.creador\n" +
                                "LEFT JOIN usuarios u3 ON u3.id_usuario = ht.fk_usuario\n"+
                                "LEFT JOIN edificios ed ON t.fkEdificio = ed.id_edificio\n" +
                                "WHERE ht.id_historial IN "+
                                "(SELECT id_historial FROM "+
                                "(SELECT MAX(id_historial) as id_historial,fk_ticket "+
                                "FROM historial_tickets GROUP by fk_ticket ) AS ht2)"+
                                " AND es.usuario = ?1 "+
                                "AND ((s.nombre_asuntoS LIKE ?2\n" +
                                "OR a.nombre LIKE ?2\n"+
                                "OR ed.nombre LIKE ?2\n"+
                                "OR u2.nombre_usuario LIKE ?2\n"+
                                "OR u3.nombre_usuario LIKE ?2\n"+
                                "OR t.patrimonio LIKE ?2\n"+
                                "OR t.observacion LIKE ?2)\n"+
                                "OR t.id_ticket = ?3)\n"+
                                "ORDER by t.id_ticket",Tickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        q.setParameter(2, "%"+id+"%");
        q.setParameter(3,id);
        System.out.println();
        aux = q.getResultList();
        return aux;
    }
    
    /**
     * 
     * @return 
     */
    public List <Tickets> buscarPorUsuarioAsunto(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        EntityManager em = emf.createEntityManager();
        
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();

        em.getTransaction().begin();
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t \n" +
                                "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
                                "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
                                "JOIN estados e ON e.id_estado = ht.fk_estado\n" +
                                "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n"+
                                "WHERE e.id_estado NOT IN (5,7)"+
                                " AND ht.id_historial IN "+
                                "(SELECT id_historial FROM "+
                                "(SELECT MAX(id_historial) as id_historial,fk_ticket "+
                                "FROM historial_tickets GROUP by fk_ticket ) AS ht2)"+
                                " AND es.usuario = ?1 "+
                                "ORDER by t.id_ticket",Tickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        aux = q.getResultList();
        return aux;
    }
    
    public List <Tickets> buscarPorUsuarioAsuntoSinEnEspera(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        //TicketsJpaController jpa = new TicketsJpaController(emf);
        EntityManager em = emf.createEntityManager();
        //List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        //estad = EstadoServ.getEstadoServ();
        
        /*for(int i = 0; i < miLista.size(); i ++){
            System.out.println(miLista.get(i).getIdTicket().toString());
            if((LoginEJB.usuario.getServiciosList().contains(miLista.get(i).getServicio()))&&((!miLista.get(i).getUltimoEstado().equals(estad.traerEstado(5)))&&(!miLista.get(i).getUltimoEstado().equals(estad.traerEstado(7)))&&(!miLista.get(i).getUltimoEstado().equals(estad.traerEstado(3))))){
                aux.add(miLista.get(i));
            }
        }
        return aux;*/
        em.getTransaction().begin();
        q = em.createNativeQuery("SELECT DISTINCT * FROM tickets t \n" +
                                "JOIN historial_tickets ht ON ht.fk_ticket = t.id_ticket\n" +
                                "JOIN servicios s ON t.servicio = s.id_asuntoS\n" +
                                "JOIN estados e ON e.id_estado = ht.fk_estado\n" +
                                "JOIN encargado_servicios es ON es.asunto = s.id_asuntoS\n"+
                                "WHERE e.id_estado NOT IN (3,5,7)"+
                                " AND ht.id_historial IN "+
                                "(SELECT id_historial FROM "+
                                "(SELECT MAX(id_historial) as id_historial,fk_ticket "+
                                "FROM historial_tickets GROUP by fk_ticket ) AS ht2)"+
                                " AND es.usuario = ?1 "+
                                "ORDER by t.id_ticket",Tickets.class);
        q.setParameter(1, LoginEJB.usuario.getIdUsuario());
        aux = q.getResultList();
        return aux;
        
    }
    
    
    public List <Tickets> buscarPorUsuarioAsuntoNoResueltos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        estad = EstadoServ.getEstadoServ();
        
        for(int i = 0; i < miLista.size(); i ++){
            if(LoginEJB.usuario.getServiciosList().contains(miLista.get(i).getServicio())){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public Tickets buscarUno(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        return jpa.findTickets(id);
    }
    
    public int modificarTicket(Tickets miTick){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        try {
            jpa.edit(miTick);
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, "Error al modificar: TicketServ", ex);
            return 1;
        }
    }
    public boolean eliminarTicket(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        try {
            jpa.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public List<Tickets> buscarPorUsuarioEmisor(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketsJpaController jpa = new TicketsJpaController(emf);
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        estad = EstadoServ.getEstadoServ();
        
        for(int i = 0; i < miLista.size(); i ++){
            if((miLista.get(i).getCreador().equals(LoginEJB.usuario))&&(!miLista.get(i).getUltimoEstado().equals(estad.traerEstado(7)))){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    
}
