/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.tickets.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.controladores.TicketsJpaController;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Tickets;
import mscb.tick.estados.servicios.EstadoServ;
import mscb.tick.login.servicios.LoginEJB;

//import mscb.tick.login.Login;

/**
 *
 * @author Administrador
 */
public class TicketServ {
    private static Query q;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    TicketsJpaController jpa = new TicketsJpaController(emf);
    private EstadoServ estad;
    
    public int nuevoTicket(Tickets nuevo){
        try{
            jpa.create(nuevo);
            return 0;
        }catch(Exception e){
            System.out.print(e+" Error en la carga de ticket");
            return 1;
        }
    }
    
    public List<Tickets> traerTodos(){
        return jpa.findTicketsEntities();
    }
    
    
    public List <Tickets> buscar(String busca){
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT DISTINCT t "
                + "FROM Tickets t "
                + "JOIN t.asunto a "
                + "JOIN a.pertenece a2 "
                + "JOIN t.fkAreaEmisor ae "
                + "JOIN t.fkUsuarioEmisor ue "
                + "JOIN t.fkAreaSistemas asi "
                + "WHERE a.nombreasuntoS LIKE :patron "
                + "OR a2.nombre LIKE :patron "
                + "OR ae.nombreArea LIKE :patron "
                + "OR ue.nombreUsuario LIKE :patron "
                + "OR asi.nombreArea LIKE :patron"
                + "AND t.fkestado != 7");
        q.setParameter("patron", "%"+ busca+"%");
        return q.getResultList();
    }
    
    public List <Tickets> buscar(int id){
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT t FROM Tickets t WHERE t.idTicket LIKE :patron AND t.fkEstado != 7");
        q.setParameter("patron",id);
        return q.getResultList();
    }
    
    public List <Tickets> buscarNoResueltos(int id){
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT t FROM Tickets t WHERE t.idTicket LIKE :patron AND t.fkEstado != 5 AND t.fkEstado != 7");
        q.setParameter("patron",id);
        return q.getResultList();
    }
    /**
     * 
     * @return 
     */
    public List <Tickets> buscarPorUsuarioAsunto(){
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        estad = new EstadoServ();
        
        for(int i = 0; i < miLista.size(); i ++){
            if((LoginEJB.usuario.getServiciosList().contains(miLista.get(i).getAsunto()))&&(!miLista.get(i).getFkEstado().equals(estad.traerEstado(5)))&&(!miLista.get(i).getFkEstado().equals(estad.traerEstado(7)))){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public List <Tickets> buscarPorUsuarioAsuntoNoResueltos(){
        List<Tickets> miLista = jpa.findTicketsEntities();
        List<Tickets> aux = new ArrayList<>();
        estad = new EstadoServ();
        
        for(int i = 0; i < miLista.size(); i ++){
            if(LoginEJB.usuario.getServiciosList().contains(miLista.get(i).getAsunto())){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public Tickets buscarUno(int id){
        return jpa.findTickets(id);
    }
    
    public int modificarTicket(Tickets miTick){
        try {
            jpa.edit(miTick);
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, "Error al modificar: TicketServ", ex);
            return 1;
        }
    }
    public boolean eliminarTicket(int id){
        try {
            jpa.destroy(id);
            return true;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex+" - Error al eliminar ticket (No existe el ID macho)");
            return false;
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
