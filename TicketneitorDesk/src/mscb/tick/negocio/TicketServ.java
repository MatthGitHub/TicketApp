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
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.LoginEJB;

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
                + "JOIN t.servicio s "
                + "JOIN s.pertenece a2 "
                + "JOIN t.creador c "
                + "WHERE s.nombreasuntoS LIKE :patron "
                + "OR a2.nombre LIKE :patron "
                + "OR c.nombreUsuario LIKE :patron "
                + "OR t.patrimonio LIKE :patron "
                + "AND c.nombreUsuario LIKE :patron ");
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
            if((LoginEJB.usuario.getServiciosList().contains(miLista.get(i).getServicio()))&&(!miLista.get(i).getUltimoEstado().equals(estad.traerEstado(5)))&&(!miLista.get(i).getUltimoEstado().equals(estad.traerEstado(7)))){
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
            if(LoginEJB.usuario.getServiciosList().contains(miLista.get(i).getServicio())){
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
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(TicketServ.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
