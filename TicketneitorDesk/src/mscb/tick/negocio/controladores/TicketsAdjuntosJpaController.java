/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.controladores;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.controladores.exceptions.PreexistingEntityException;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.TicketsAdjuntos;
import mscb.tick.negocio.entidades.TicketsAdjuntosPK;

/**
 *
 * @author Administrador
 */
public class TicketsAdjuntosJpaController implements Serializable {

    public TicketsAdjuntosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TicketsAdjuntos ticketsAdjuntos) throws PreexistingEntityException, Exception {
        if (ticketsAdjuntos.getTicketsAdjuntosPK() == null) {
            ticketsAdjuntos.setTicketsAdjuntosPK(new TicketsAdjuntosPK());
        }
        ticketsAdjuntos.getTicketsAdjuntosPK().setFkTicket(ticketsAdjuntos.getTickets().getIdTicket());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets tickets = ticketsAdjuntos.getTickets();
            if (tickets != null) {
                tickets = em.getReference(tickets.getClass(), tickets.getIdTicket());
                ticketsAdjuntos.setTickets(tickets);
            }
            em.persist(ticketsAdjuntos);
            if (tickets != null) {
                tickets.getTicketsAdjuntosList().add(ticketsAdjuntos);
                tickets = em.merge(tickets);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTicketsAdjuntos(ticketsAdjuntos.getTicketsAdjuntosPK()) != null) {
                throw new PreexistingEntityException("TicketsAdjuntos " + ticketsAdjuntos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TicketsAdjuntos ticketsAdjuntos) throws NonexistentEntityException, Exception {
        ticketsAdjuntos.getTicketsAdjuntosPK().setFkTicket(ticketsAdjuntos.getTickets().getIdTicket());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TicketsAdjuntos persistentTicketsAdjuntos = em.find(TicketsAdjuntos.class, ticketsAdjuntos.getTicketsAdjuntosPK());
            Tickets ticketsOld = persistentTicketsAdjuntos.getTickets();
            Tickets ticketsNew = ticketsAdjuntos.getTickets();
            if (ticketsNew != null) {
                ticketsNew = em.getReference(ticketsNew.getClass(), ticketsNew.getIdTicket());
                ticketsAdjuntos.setTickets(ticketsNew);
            }
            ticketsAdjuntos = em.merge(ticketsAdjuntos);
            if (ticketsOld != null && !ticketsOld.equals(ticketsNew)) {
                ticketsOld.getTicketsAdjuntosList().remove(ticketsAdjuntos);
                ticketsOld = em.merge(ticketsOld);
            }
            if (ticketsNew != null && !ticketsNew.equals(ticketsOld)) {
                ticketsNew.getTicketsAdjuntosList().add(ticketsAdjuntos);
                ticketsNew = em.merge(ticketsNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                TicketsAdjuntosPK id = ticketsAdjuntos.getTicketsAdjuntosPK();
                if (findTicketsAdjuntos(id) == null) {
                    throw new NonexistentEntityException("The ticketsAdjuntos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(TicketsAdjuntosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TicketsAdjuntos ticketsAdjuntos;
            try {
                ticketsAdjuntos = em.getReference(TicketsAdjuntos.class, id);
                ticketsAdjuntos.getTicketsAdjuntosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ticketsAdjuntos with id " + id + " no longer exists.", enfe);
            }
            Tickets tickets = ticketsAdjuntos.getTickets();
            if (tickets != null) {
                tickets.getTicketsAdjuntosList().remove(ticketsAdjuntos);
                tickets = em.merge(tickets);
            }
            em.remove(ticketsAdjuntos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TicketsAdjuntos> findTicketsAdjuntosEntities() {
        return findTicketsAdjuntosEntities(true, -1, -1);
    }

    public List<TicketsAdjuntos> findTicketsAdjuntosEntities(int maxResults, int firstResult) {
        return findTicketsAdjuntosEntities(false, maxResults, firstResult);
    }

    private List<TicketsAdjuntos> findTicketsAdjuntosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TicketsAdjuntos.class));
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

    public TicketsAdjuntos findTicketsAdjuntos(TicketsAdjuntosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TicketsAdjuntos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketsAdjuntosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TicketsAdjuntos> rt = cq.from(TicketsAdjuntos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
