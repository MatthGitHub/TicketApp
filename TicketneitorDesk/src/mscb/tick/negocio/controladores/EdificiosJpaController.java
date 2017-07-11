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
import mscb.tick.negocio.entidades.Edificios;

/**
 *
 * @author Administrador
 */
public class EdificiosJpaController implements Serializable {

    public EdificiosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Edificios edificios) {
        if (edificios.getTicketsList() == null) {
            edificios.setTicketsList(new ArrayList<Tickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : edificios.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            edificios.setTicketsList(attachedTicketsList);
            em.persist(edificios);
            for (Tickets ticketsListTickets : edificios.getTicketsList()) {
                Edificios oldFkEdificioOfTicketsListTickets = ticketsListTickets.getFkEdificio();
                ticketsListTickets.setFkEdificio(edificios);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkEdificioOfTicketsListTickets != null) {
                    oldFkEdificioOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkEdificioOfTicketsListTickets = em.merge(oldFkEdificioOfTicketsListTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Edificios edificios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Edificios persistentEdificios = em.find(Edificios.class, edificios.getIdEdificio());
            List<Tickets> ticketsListOld = persistentEdificios.getTicketsList();
            List<Tickets> ticketsListNew = edificios.getTicketsList();
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            edificios.setTicketsList(ticketsListNew);
            edificios = em.merge(edificios);
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    ticketsListOldTickets.setFkEdificio(null);
                    ticketsListOldTickets = em.merge(ticketsListOldTickets);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Edificios oldFkEdificioOfTicketsListNewTickets = ticketsListNewTickets.getFkEdificio();
                    ticketsListNewTickets.setFkEdificio(edificios);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkEdificioOfTicketsListNewTickets != null && !oldFkEdificioOfTicketsListNewTickets.equals(edificios)) {
                        oldFkEdificioOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkEdificioOfTicketsListNewTickets = em.merge(oldFkEdificioOfTicketsListNewTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = edificios.getIdEdificio();
                if (findEdificios(id) == null) {
                    throw new NonexistentEntityException("The edificios with id " + id + " no longer exists.");
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
            Edificios edificios;
            try {
                edificios = em.getReference(Edificios.class, id);
                edificios.getIdEdificio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The edificios with id " + id + " no longer exists.", enfe);
            }
            List<Tickets> ticketsList = edificios.getTicketsList();
            for (Tickets ticketsListTickets : ticketsList) {
                ticketsListTickets.setFkEdificio(null);
                ticketsListTickets = em.merge(ticketsListTickets);
            }
            em.remove(edificios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Edificios> findEdificiosEntities() {
        return findEdificiosEntities(true, -1, -1);
    }

    public List<Edificios> findEdificiosEntities(int maxResults, int firstResult) {
        return findEdificiosEntities(false, maxResults, firstResult);
    }

    private List<Edificios> findEdificiosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Edificios.class));
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

    public Edificios findEdificios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Edificios.class, id);
        } finally {
            em.close();
        }
    }

    public int getEdificiosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Edificios> rt = cq.from(Edificios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
