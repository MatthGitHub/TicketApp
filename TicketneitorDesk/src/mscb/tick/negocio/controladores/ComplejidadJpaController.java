/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.controladores;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mscb.tick.negocio.entidades.Tickets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Complejidad;

/**
 *
 * @author Administrador
 */
public class ComplejidadJpaController implements Serializable {

    public ComplejidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Complejidad complejidad) {
        if (complejidad.getTicketsList() == null) {
            complejidad.setTicketsList(new ArrayList<Tickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : complejidad.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            complejidad.setTicketsList(attachedTicketsList);
            em.persist(complejidad);
            for (Tickets ticketsListTickets : complejidad.getTicketsList()) {
                Complejidad oldFkComplejidadOfTicketsListTickets = ticketsListTickets.getFkComplejidad();
                ticketsListTickets.setFkComplejidad(complejidad);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkComplejidadOfTicketsListTickets != null) {
                    oldFkComplejidadOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkComplejidadOfTicketsListTickets = em.merge(oldFkComplejidadOfTicketsListTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Complejidad complejidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Complejidad persistentComplejidad = em.find(Complejidad.class, complejidad.getIdComplejidad());
            List<Tickets> ticketsListOld = persistentComplejidad.getTicketsList();
            List<Tickets> ticketsListNew = complejidad.getTicketsList();
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            complejidad.setTicketsList(ticketsListNew);
            complejidad = em.merge(complejidad);
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    ticketsListOldTickets.setFkComplejidad(null);
                    ticketsListOldTickets = em.merge(ticketsListOldTickets);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Complejidad oldFkComplejidadOfTicketsListNewTickets = ticketsListNewTickets.getFkComplejidad();
                    ticketsListNewTickets.setFkComplejidad(complejidad);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkComplejidadOfTicketsListNewTickets != null && !oldFkComplejidadOfTicketsListNewTickets.equals(complejidad)) {
                        oldFkComplejidadOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkComplejidadOfTicketsListNewTickets = em.merge(oldFkComplejidadOfTicketsListNewTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = complejidad.getIdComplejidad();
                if (findComplejidad(id) == null) {
                    throw new NonexistentEntityException("The complejidad with id " + id + " no longer exists.");
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
            Complejidad complejidad;
            try {
                complejidad = em.getReference(Complejidad.class, id);
                complejidad.getIdComplejidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The complejidad with id " + id + " no longer exists.", enfe);
            }
            List<Tickets> ticketsList = complejidad.getTicketsList();
            for (Tickets ticketsListTickets : ticketsList) {
                ticketsListTickets.setFkComplejidad(null);
                ticketsListTickets = em.merge(ticketsListTickets);
            }
            em.remove(complejidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Complejidad> findComplejidadEntities() {
        return findComplejidadEntities(true, -1, -1);
    }

    public List<Complejidad> findComplejidadEntities(int maxResults, int firstResult) {
        return findComplejidadEntities(false, maxResults, firstResult);
    }

    private List<Complejidad> findComplejidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Complejidad.class));
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

    public Complejidad findComplejidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Complejidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getComplejidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Complejidad> rt = cq.from(Complejidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
