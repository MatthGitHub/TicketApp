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
import mscb.tick.controladores.TicketJpaController;
import mscb.tick.entidades.Ticket;
import mscb.tick.login.servicios.LoginEJB;

//import mscb.tick.login.Login;

/**
 *
 * @author Administrador
 */
public class TicketServ {
    private static Query q;
    
    public int nuevoTicket(Ticket nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketJpaController jpa = new TicketJpaController(emf);
        
        try{
            jpa.create(nuevo);
            return 0;
        }catch(Exception e){
            System.out.print(e+" Error en la carga de ticket");
            return 1;
        }
    }
    
    public List<Ticket> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketJpaController jpa = new TicketJpaController(emf);
        
        return jpa.findTicketEntities();
    }
    
    
    public List <Ticket> buscar(String busca){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT DISTINCT t "
                + "FROM Ticket t "
                + "JOIN t.asunto a "
                + "JOIN a.pertenece a2 "
                + "JOIN t.fkAreaEmisor ae "
                + "JOIN t.fkUsuarioEmisor ue "
                + "JOIN t.fkAreaSistemas asi "
                + "WHERE a.nombreasuntoS LIKE :patron "
                + "OR a2.nombre LIKE :patron "
                + "OR ae.nombreArea LIKE :patron "
                + "OR ue.nombreUsuario LIKE :patron "
                + "OR asi.nombreArea LIKE :patron");
        q.setParameter("patron", "%"+ busca+"%");
        return q.getResultList();
             
        
    }
    
    public List <Ticket> buscar(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        EntityManager em = emf.createEntityManager();
        
        q = em.createQuery("SELECT t FROM Ticket t WHERE t.idTicket LIKE :patron");
        q.setParameter("patron",id);
        return q.getResultList();
    }
    /**
     * 
     * @return 
     */
    public List <Ticket> buscarPorUsuarioAsunto(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketJpaController jpa = new TicketJpaController(emf);
        List<Ticket> miLista = jpa.findTicketEntities();
        List<Ticket> aux = new ArrayList<>();
        
        for(int i = 0; i < miLista.size(); i ++){
            if(LoginEJB.usuario.getAsuntoSecundarioCollection().contains(miLista.get(i).getAsunto())){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public Ticket buscarUno(int id){
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketJpaController jpa  = new TicketJpaController(emf);
        
        return jpa.findTicket(id);
    }
    
    public int modificarTicket(Ticket miTick){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        TicketJpaController jpa = new TicketJpaController(emf);
        
        try {
            jpa.edit(miTick);
            return 0;
        } catch (Exception ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, "Error al modificar: TicketServ", ex);
            return 1;
        }
    }
    
}
