/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.AreaSistemas;
import mscb.tick.entidades.Usuario;
import mscb.tick.entidades.Area;
import mscb.tick.entidades.AsuntoSecundario;
import mscb.tick.entidades.Ticket;

/**
 *
 * @author Administrador
 */
public class TicketJpaController implements Serializable {

    public TicketJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ticket ticket) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaSistemas fkAreaSistemas = ticket.getFkAreaSistemas();
            if (fkAreaSistemas != null) {
                fkAreaSistemas = em.getReference(fkAreaSistemas.getClass(), fkAreaSistemas.getIdAreaSistemas());
                ticket.setFkAreaSistemas(fkAreaSistemas);
            }
            Usuario usuarioReceptor = ticket.getUsuarioReceptor();
            if (usuarioReceptor != null) {
                usuarioReceptor = em.getReference(usuarioReceptor.getClass(), usuarioReceptor.getIdUsuario());
                ticket.setUsuarioReceptor(usuarioReceptor);
            }
            Usuario fkUsuarioEmisor = ticket.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor = em.getReference(fkUsuarioEmisor.getClass(), fkUsuarioEmisor.getIdUsuario());
                ticket.setFkUsuarioEmisor(fkUsuarioEmisor);
            }
            Area fkAreaEmisor = ticket.getFkAreaEmisor();
            if (fkAreaEmisor != null) {
                fkAreaEmisor = em.getReference(fkAreaEmisor.getClass(), fkAreaEmisor.getIdArea());
                ticket.setFkAreaEmisor(fkAreaEmisor);
            }
            AsuntoSecundario asunto = ticket.getAsunto();
            if (asunto != null) {
                asunto = em.getReference(asunto.getClass(), asunto.getIdasuntoS());
                ticket.setAsunto(asunto);
            }
            em.persist(ticket);
            if (fkAreaSistemas != null) {
                fkAreaSistemas.getTicketCollection().add(ticket);
                fkAreaSistemas = em.merge(fkAreaSistemas);
            }
            if (usuarioReceptor != null) {
                usuarioReceptor.getTicketCollection().add(ticket);
                usuarioReceptor = em.merge(usuarioReceptor);
            }
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getTicketCollection().add(ticket);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            if (fkAreaEmisor != null) {
                fkAreaEmisor.getTicketCollection().add(ticket);
                fkAreaEmisor = em.merge(fkAreaEmisor);
            }
            if (asunto != null) {
                asunto.getTicketCollection().add(ticket);
                asunto = em.merge(asunto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ticket ticket) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket persistentTicket = em.find(Ticket.class, ticket.getIdTicket());
            AreaSistemas fkAreaSistemasOld = persistentTicket.getFkAreaSistemas();
            AreaSistemas fkAreaSistemasNew = ticket.getFkAreaSistemas();
            Usuario usuarioReceptorOld = persistentTicket.getUsuarioReceptor();
            Usuario usuarioReceptorNew = ticket.getUsuarioReceptor();
            Usuario fkUsuarioEmisorOld = persistentTicket.getFkUsuarioEmisor();
            Usuario fkUsuarioEmisorNew = ticket.getFkUsuarioEmisor();
            Area fkAreaEmisorOld = persistentTicket.getFkAreaEmisor();
            Area fkAreaEmisorNew = ticket.getFkAreaEmisor();
            AsuntoSecundario asuntoOld = persistentTicket.getAsunto();
            AsuntoSecundario asuntoNew = ticket.getAsunto();
            if (fkAreaSistemasNew != null) {
                fkAreaSistemasNew = em.getReference(fkAreaSistemasNew.getClass(), fkAreaSistemasNew.getIdAreaSistemas());
                ticket.setFkAreaSistemas(fkAreaSistemasNew);
            }
            if (usuarioReceptorNew != null) {
                usuarioReceptorNew = em.getReference(usuarioReceptorNew.getClass(), usuarioReceptorNew.getIdUsuario());
                ticket.setUsuarioReceptor(usuarioReceptorNew);
            }
            if (fkUsuarioEmisorNew != null) {
                fkUsuarioEmisorNew = em.getReference(fkUsuarioEmisorNew.getClass(), fkUsuarioEmisorNew.getIdUsuario());
                ticket.setFkUsuarioEmisor(fkUsuarioEmisorNew);
            }
            if (fkAreaEmisorNew != null) {
                fkAreaEmisorNew = em.getReference(fkAreaEmisorNew.getClass(), fkAreaEmisorNew.getIdArea());
                ticket.setFkAreaEmisor(fkAreaEmisorNew);
            }
            if (asuntoNew != null) {
                asuntoNew = em.getReference(asuntoNew.getClass(), asuntoNew.getIdasuntoS());
                ticket.setAsunto(asuntoNew);
            }
            ticket = em.merge(ticket);
            if (fkAreaSistemasOld != null && !fkAreaSistemasOld.equals(fkAreaSistemasNew)) {
                fkAreaSistemasOld.getTicketCollection().remove(ticket);
                fkAreaSistemasOld = em.merge(fkAreaSistemasOld);
            }
            if (fkAreaSistemasNew != null && !fkAreaSistemasNew.equals(fkAreaSistemasOld)) {
                fkAreaSistemasNew.getTicketCollection().add(ticket);
                fkAreaSistemasNew = em.merge(fkAreaSistemasNew);
            }
            if (usuarioReceptorOld != null && !usuarioReceptorOld.equals(usuarioReceptorNew)) {
                usuarioReceptorOld.getTicketCollection().remove(ticket);
                usuarioReceptorOld = em.merge(usuarioReceptorOld);
            }
            if (usuarioReceptorNew != null && !usuarioReceptorNew.equals(usuarioReceptorOld)) {
                usuarioReceptorNew.getTicketCollection().add(ticket);
                usuarioReceptorNew = em.merge(usuarioReceptorNew);
            }
            if (fkUsuarioEmisorOld != null && !fkUsuarioEmisorOld.equals(fkUsuarioEmisorNew)) {
                fkUsuarioEmisorOld.getTicketCollection().remove(ticket);
                fkUsuarioEmisorOld = em.merge(fkUsuarioEmisorOld);
            }
            if (fkUsuarioEmisorNew != null && !fkUsuarioEmisorNew.equals(fkUsuarioEmisorOld)) {
                fkUsuarioEmisorNew.getTicketCollection().add(ticket);
                fkUsuarioEmisorNew = em.merge(fkUsuarioEmisorNew);
            }
            if (fkAreaEmisorOld != null && !fkAreaEmisorOld.equals(fkAreaEmisorNew)) {
                fkAreaEmisorOld.getTicketCollection().remove(ticket);
                fkAreaEmisorOld = em.merge(fkAreaEmisorOld);
            }
            if (fkAreaEmisorNew != null && !fkAreaEmisorNew.equals(fkAreaEmisorOld)) {
                fkAreaEmisorNew.getTicketCollection().add(ticket);
                fkAreaEmisorNew = em.merge(fkAreaEmisorNew);
            }
            if (asuntoOld != null && !asuntoOld.equals(asuntoNew)) {
                asuntoOld.getTicketCollection().remove(ticket);
                asuntoOld = em.merge(asuntoOld);
            }
            if (asuntoNew != null && !asuntoNew.equals(asuntoOld)) {
                asuntoNew.getTicketCollection().add(ticket);
                asuntoNew = em.merge(asuntoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ticket.getIdTicket();
                if (findTicket(id) == null) {
                    throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ticket ticket;
            try {
                ticket = em.getReference(Ticket.class, id);
                ticket.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticket with id " + id + " no longer exists.", enfe);
            }
            AreaSistemas fkAreaSistemas = ticket.getFkAreaSistemas();
            if (fkAreaSistemas != null) {
                fkAreaSistemas.getTicketCollection().remove(ticket);
                fkAreaSistemas = em.merge(fkAreaSistemas);
            }
            Usuario usuarioReceptor = ticket.getUsuarioReceptor();
            if (usuarioReceptor != null) {
                usuarioReceptor.getTicketCollection().remove(ticket);
                usuarioReceptor = em.merge(usuarioReceptor);
            }
            Usuario fkUsuarioEmisor = ticket.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getTicketCollection().remove(ticket);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            Area fkAreaEmisor = ticket.getFkAreaEmisor();
            if (fkAreaEmisor != null) {
                fkAreaEmisor.getTicketCollection().remove(ticket);
                fkAreaEmisor = em.merge(fkAreaEmisor);
            }
            AsuntoSecundario asunto = ticket.getAsunto();
            if (asunto != null) {
                asunto.getTicketCollection().remove(ticket);
                asunto = em.merge(asunto);
            }
            em.remove(ticket);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ticket> findTicketEntities() {
        return findTicketEntities(true, -1, -1);
    }

    public List<Ticket> findTicketEntities(int maxResults, int firstResult) {
        return findTicketEntities(false, maxResults, firstResult);
    }

    private List<Ticket> findTicketEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ticket.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Ticket findTicket(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ticket.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ticket> rt = cq.from(Ticket.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
