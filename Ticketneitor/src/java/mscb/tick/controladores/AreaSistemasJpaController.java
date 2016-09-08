/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mscb.tick.entidades.Tickets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.AreaSistemas;

/**
 *
 * @author Administrador
 */
public class AreaSistemasJpaController implements Serializable {

    public AreaSistemasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaSistemas areaSistemas) {
        if (areaSistemas.getTicketsList() == null) {
            areaSistemas.setTicketsList(new ArrayList<Tickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : areaSistemas.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            areaSistemas.setTicketsList(attachedTicketsList);
            em.persist(areaSistemas);
            for (Tickets ticketsListTickets : areaSistemas.getTicketsList()) {
                AreaSistemas oldFkAreaSistemasOfTicketsListTickets = ticketsListTickets.getFkAreaSistemas();
                ticketsListTickets.setFkAreaSistemas(areaSistemas);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkAreaSistemasOfTicketsListTickets != null) {
                    oldFkAreaSistemasOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkAreaSistemasOfTicketsListTickets = em.merge(oldFkAreaSistemasOfTicketsListTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaSistemas areaSistemas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaSistemas persistentAreaSistemas = em.find(AreaSistemas.class, areaSistemas.getIdAreaSistemas());
            List<Tickets> ticketsListOld = persistentAreaSistemas.getTicketsList();
            List<Tickets> ticketsListNew = areaSistemas.getTicketsList();
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            areaSistemas.setTicketsList(ticketsListNew);
            areaSistemas = em.merge(areaSistemas);
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    ticketsListOldTickets.setFkAreaSistemas(null);
                    ticketsListOldTickets = em.merge(ticketsListOldTickets);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    AreaSistemas oldFkAreaSistemasOfTicketsListNewTickets = ticketsListNewTickets.getFkAreaSistemas();
                    ticketsListNewTickets.setFkAreaSistemas(areaSistemas);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkAreaSistemasOfTicketsListNewTickets != null && !oldFkAreaSistemasOfTicketsListNewTickets.equals(areaSistemas)) {
                        oldFkAreaSistemasOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkAreaSistemasOfTicketsListNewTickets = em.merge(oldFkAreaSistemasOfTicketsListNewTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areaSistemas.getIdAreaSistemas();
                if (findAreaSistemas(id) == null) {
                    throw new NonexistentEntityException("The areaSistemas with id " + id + " no longer exists.");
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
            AreaSistemas areaSistemas;
            try {
                areaSistemas = em.getReference(AreaSistemas.class, id);
                areaSistemas.getIdAreaSistemas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaSistemas with id " + id + " no longer exists.", enfe);
            }
            List<Tickets> ticketsList = areaSistemas.getTicketsList();
            for (Tickets ticketsListTickets : ticketsList) {
                ticketsListTickets.setFkAreaSistemas(null);
                ticketsListTickets = em.merge(ticketsListTickets);
            }
            em.remove(areaSistemas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaSistemas> findAreaSistemasEntities() {
        return findAreaSistemasEntities(true, -1, -1);
    }

    public List<AreaSistemas> findAreaSistemasEntities(int maxResults, int firstResult) {
        return findAreaSistemasEntities(false, maxResults, firstResult);
    }

    private List<AreaSistemas> findAreaSistemasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaSistemas.class));
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

    public AreaSistemas findAreaSistemas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaSistemas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaSistemasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaSistemas> rt = cq.from(AreaSistemas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
