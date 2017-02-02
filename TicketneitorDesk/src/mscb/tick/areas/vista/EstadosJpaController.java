/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.areas.vista;

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
import mscb.tick.areas.vista.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.HistorialTickets;

/**
 *
 * @author Administrador
 */
public class EstadosJpaController implements Serializable {

    public EstadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estados estados) {
        if (estados.getTicketsList() == null) {
            estados.setTicketsList(new ArrayList<Tickets>());
        }
        if (estados.getHistorialTicketsList() == null) {
            estados.setHistorialTicketsList(new ArrayList<HistorialTickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : estados.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            estados.setTicketsList(attachedTicketsList);
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : estados.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            estados.setHistorialTicketsList(attachedHistorialTicketsList);
            em.persist(estados);
            for (Tickets ticketsListTickets : estados.getTicketsList()) {
                Estados oldFkEstadoOfTicketsListTickets = ticketsListTickets.getFkEstado();
                ticketsListTickets.setFkEstado(estados);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkEstadoOfTicketsListTickets != null) {
                    oldFkEstadoOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkEstadoOfTicketsListTickets = em.merge(oldFkEstadoOfTicketsListTickets);
                }
            }
            for (HistorialTickets historialTicketsListHistorialTickets : estados.getHistorialTicketsList()) {
                Estados oldFkEstadoOfHistorialTicketsListHistorialTickets = historialTicketsListHistorialTickets.getFkEstado();
                historialTicketsListHistorialTickets.setFkEstado(estados);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
                if (oldFkEstadoOfHistorialTicketsListHistorialTickets != null) {
                    oldFkEstadoOfHistorialTicketsListHistorialTickets.getHistorialTicketsList().remove(historialTicketsListHistorialTickets);
                    oldFkEstadoOfHistorialTicketsListHistorialTickets = em.merge(oldFkEstadoOfHistorialTicketsListHistorialTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estados estados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estados persistentEstados = em.find(Estados.class, estados.getIdEstado());
            List<Tickets> ticketsListOld = persistentEstados.getTicketsList();
            List<Tickets> ticketsListNew = estados.getTicketsList();
            List<HistorialTickets> historialTicketsListOld = persistentEstados.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = estados.getHistorialTicketsList();
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            estados.setTicketsList(ticketsListNew);
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            estados.setHistorialTicketsList(historialTicketsListNew);
            estados = em.merge(estados);
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    ticketsListOldTickets.setFkEstado(null);
                    ticketsListOldTickets = em.merge(ticketsListOldTickets);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Estados oldFkEstadoOfTicketsListNewTickets = ticketsListNewTickets.getFkEstado();
                    ticketsListNewTickets.setFkEstado(estados);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkEstadoOfTicketsListNewTickets != null && !oldFkEstadoOfTicketsListNewTickets.equals(estados)) {
                        oldFkEstadoOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkEstadoOfTicketsListNewTickets = em.merge(oldFkEstadoOfTicketsListNewTickets);
                    }
                }
            }
            for (HistorialTickets historialTicketsListOldHistorialTickets : historialTicketsListOld) {
                if (!historialTicketsListNew.contains(historialTicketsListOldHistorialTickets)) {
                    historialTicketsListOldHistorialTickets.setFkEstado(null);
                    historialTicketsListOldHistorialTickets = em.merge(historialTicketsListOldHistorialTickets);
                }
            }
            for (HistorialTickets historialTicketsListNewHistorialTickets : historialTicketsListNew) {
                if (!historialTicketsListOld.contains(historialTicketsListNewHistorialTickets)) {
                    Estados oldFkEstadoOfHistorialTicketsListNewHistorialTickets = historialTicketsListNewHistorialTickets.getFkEstado();
                    historialTicketsListNewHistorialTickets.setFkEstado(estados);
                    historialTicketsListNewHistorialTickets = em.merge(historialTicketsListNewHistorialTickets);
                    if (oldFkEstadoOfHistorialTicketsListNewHistorialTickets != null && !oldFkEstadoOfHistorialTicketsListNewHistorialTickets.equals(estados)) {
                        oldFkEstadoOfHistorialTicketsListNewHistorialTickets.getHistorialTicketsList().remove(historialTicketsListNewHistorialTickets);
                        oldFkEstadoOfHistorialTicketsListNewHistorialTickets = em.merge(oldFkEstadoOfHistorialTicketsListNewHistorialTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estados.getIdEstado();
                if (findEstados(id) == null) {
                    throw new NonexistentEntityException("The estados with id " + id + " no longer exists.");
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
            Estados estados;
            try {
                estados = em.getReference(Estados.class, id);
                estados.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estados with id " + id + " no longer exists.", enfe);
            }
            List<Tickets> ticketsList = estados.getTicketsList();
            for (Tickets ticketsListTickets : ticketsList) {
                ticketsListTickets.setFkEstado(null);
                ticketsListTickets = em.merge(ticketsListTickets);
            }
            List<HistorialTickets> historialTicketsList = estados.getHistorialTicketsList();
            for (HistorialTickets historialTicketsListHistorialTickets : historialTicketsList) {
                historialTicketsListHistorialTickets.setFkEstado(null);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
            }
            em.remove(estados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estados> findEstadosEntities() {
        return findEstadosEntities(true, -1, -1);
    }

    public List<Estados> findEstadosEntities(int maxResults, int firstResult) {
        return findEstadosEntities(false, maxResults, firstResult);
    }

    private List<Estados> findEstadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estados.class));
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

    public Estados findEstados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estados> rt = cq.from(Estados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}