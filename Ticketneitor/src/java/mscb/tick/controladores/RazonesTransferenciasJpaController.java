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
import mscb.tick.entidades.RazonesTransferencias;
import mscb.tick.controladores.exceptions.NonexistentEntityException;

/**
 *
 * @author Administrador
 */
public class RazonesTransferenciasJpaController implements Serializable {

    public RazonesTransferenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RazonesTransferencias razonesTransferencias) {
        if (razonesTransferencias.getTicketsList() == null) {
            razonesTransferencias.setTicketsList(new ArrayList<Tickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : razonesTransferencias.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            razonesTransferencias.setTicketsList(attachedTicketsList);
            em.persist(razonesTransferencias);
            for (Tickets ticketsListTickets : razonesTransferencias.getTicketsList()) {
                RazonesTransferencias oldFkRazonOfTicketsListTickets = ticketsListTickets.getFkRazon();
                ticketsListTickets.setFkRazon(razonesTransferencias);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkRazonOfTicketsListTickets != null) {
                    oldFkRazonOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkRazonOfTicketsListTickets = em.merge(oldFkRazonOfTicketsListTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RazonesTransferencias razonesTransferencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RazonesTransferencias persistentRazonesTransferencias = em.find(RazonesTransferencias.class, razonesTransferencias.getIdRazon());
            List<Tickets> ticketsListOld = persistentRazonesTransferencias.getTicketsList();
            List<Tickets> ticketsListNew = razonesTransferencias.getTicketsList();
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            razonesTransferencias.setTicketsList(ticketsListNew);
            razonesTransferencias = em.merge(razonesTransferencias);
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    ticketsListOldTickets.setFkRazon(null);
                    ticketsListOldTickets = em.merge(ticketsListOldTickets);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    RazonesTransferencias oldFkRazonOfTicketsListNewTickets = ticketsListNewTickets.getFkRazon();
                    ticketsListNewTickets.setFkRazon(razonesTransferencias);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkRazonOfTicketsListNewTickets != null && !oldFkRazonOfTicketsListNewTickets.equals(razonesTransferencias)) {
                        oldFkRazonOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkRazonOfTicketsListNewTickets = em.merge(oldFkRazonOfTicketsListNewTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = razonesTransferencias.getIdRazon();
                if (findRazonesTransferencias(id) == null) {
                    throw new NonexistentEntityException("The razonesTransferencias with id " + id + " no longer exists.");
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
            RazonesTransferencias razonesTransferencias;
            try {
                razonesTransferencias = em.getReference(RazonesTransferencias.class, id);
                razonesTransferencias.getIdRazon();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The razonesTransferencias with id " + id + " no longer exists.", enfe);
            }
            List<Tickets> ticketsList = razonesTransferencias.getTicketsList();
            for (Tickets ticketsListTickets : ticketsList) {
                ticketsListTickets.setFkRazon(null);
                ticketsListTickets = em.merge(ticketsListTickets);
            }
            em.remove(razonesTransferencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RazonesTransferencias> findRazonesTransferenciasEntities() {
        return findRazonesTransferenciasEntities(true, -1, -1);
    }

    public List<RazonesTransferencias> findRazonesTransferenciasEntities(int maxResults, int firstResult) {
        return findRazonesTransferenciasEntities(false, maxResults, firstResult);
    }

    private List<RazonesTransferencias> findRazonesTransferenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RazonesTransferencias.class));
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

    public RazonesTransferencias findRazonesTransferencias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RazonesTransferencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getRazonesTransferenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RazonesTransferencias> rt = cq.from(RazonesTransferencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
