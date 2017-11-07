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
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Edificios;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.HistorialTickets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.IllegalOrphanException;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.TicketsAdjuntos;

/**
 *
 * @author Administrador
 */
public class TicketsJpaController implements Serializable {

    public TicketsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tickets tickets) {
        if (tickets.getHistorialTicketsList() == null) {
            tickets.setHistorialTicketsList(new ArrayList<HistorialTickets>());
        }
        if (tickets.getTicketsAdjuntosList() == null) {
            tickets.setTicketsAdjuntosList(new ArrayList<TicketsAdjuntos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios creador = tickets.getCreador();
            if (creador != null) {
                creador = em.getReference(creador.getClass(), creador.getIdUsuario());
                tickets.setCreador(creador);
            }
            Servicios servicio = tickets.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getIdasuntoS());
                tickets.setServicio(servicio);
            }
            Edificios fkEdificio = tickets.getFkEdificio();
            if (fkEdificio != null) {
                fkEdificio = em.getReference(fkEdificio.getClass(), fkEdificio.getIdEdificio());
                tickets.setFkEdificio(fkEdificio);
            }
            Areas fkareaSolicitante = tickets.getFkareaSolicitante();
            if (fkareaSolicitante != null) {
                fkareaSolicitante = em.getReference(fkareaSolicitante.getClass(), fkareaSolicitante.getIdArea());
                tickets.setFkareaSolicitante(fkareaSolicitante);
            }
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : tickets.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            tickets.setHistorialTicketsList(attachedHistorialTicketsList);
            List<TicketsAdjuntos> attachedTicketsAdjuntosList = new ArrayList<TicketsAdjuntos>();
            for (TicketsAdjuntos ticketsAdjuntosListTicketsAdjuntosToAttach : tickets.getTicketsAdjuntosList()) {
                ticketsAdjuntosListTicketsAdjuntosToAttach = em.getReference(ticketsAdjuntosListTicketsAdjuntosToAttach.getClass(), ticketsAdjuntosListTicketsAdjuntosToAttach.getTicketsAdjuntosPK());
                attachedTicketsAdjuntosList.add(ticketsAdjuntosListTicketsAdjuntosToAttach);
            }
            tickets.setTicketsAdjuntosList(attachedTicketsAdjuntosList);
            em.persist(tickets);
            if (creador != null) {
                creador.getTicketsList().add(tickets);
                creador = em.merge(creador);
            }
            if (servicio != null) {
                servicio.getTicketsList().add(tickets);
                servicio = em.merge(servicio);
            }
            if (fkEdificio != null) {
                fkEdificio.getTicketsList().add(tickets);
                fkEdificio = em.merge(fkEdificio);
            }
            if (fkareaSolicitante != null) {
                fkareaSolicitante.getTicketsList().add(tickets);
                fkareaSolicitante = em.merge(fkareaSolicitante);
            }
            for (HistorialTickets historialTicketsListHistorialTickets : tickets.getHistorialTicketsList()) {
                Tickets oldFkTicketOfHistorialTicketsListHistorialTickets = historialTicketsListHistorialTickets.getFkTicket();
                historialTicketsListHistorialTickets.setFkTicket(tickets);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
                if (oldFkTicketOfHistorialTicketsListHistorialTickets != null) {
                    oldFkTicketOfHistorialTicketsListHistorialTickets.getHistorialTicketsList().remove(historialTicketsListHistorialTickets);
                    oldFkTicketOfHistorialTicketsListHistorialTickets = em.merge(oldFkTicketOfHistorialTicketsListHistorialTickets);
                }
            }
            for (TicketsAdjuntos ticketsAdjuntosListTicketsAdjuntos : tickets.getTicketsAdjuntosList()) {
                Tickets oldTicketsOfTicketsAdjuntosListTicketsAdjuntos = ticketsAdjuntosListTicketsAdjuntos.getTickets();
                ticketsAdjuntosListTicketsAdjuntos.setTickets(tickets);
                ticketsAdjuntosListTicketsAdjuntos = em.merge(ticketsAdjuntosListTicketsAdjuntos);
                if (oldTicketsOfTicketsAdjuntosListTicketsAdjuntos != null) {
                    oldTicketsOfTicketsAdjuntosListTicketsAdjuntos.getTicketsAdjuntosList().remove(ticketsAdjuntosListTicketsAdjuntos);
                    oldTicketsOfTicketsAdjuntosListTicketsAdjuntos = em.merge(oldTicketsOfTicketsAdjuntosListTicketsAdjuntos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tickets tickets) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets persistentTickets = em.find(Tickets.class, tickets.getIdTicket());
            Usuarios creadorOld = persistentTickets.getCreador();
            Usuarios creadorNew = tickets.getCreador();
            Servicios servicioOld = persistentTickets.getServicio();
            Servicios servicioNew = tickets.getServicio();
            Edificios fkEdificioOld = persistentTickets.getFkEdificio();
            Edificios fkEdificioNew = tickets.getFkEdificio();
            Areas fkareaSolicitanteOld = persistentTickets.getFkareaSolicitante();
            Areas fkareaSolicitanteNew = tickets.getFkareaSolicitante();
            List<HistorialTickets> historialTicketsListOld = persistentTickets.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = tickets.getHistorialTicketsList();
            List<TicketsAdjuntos> ticketsAdjuntosListOld = persistentTickets.getTicketsAdjuntosList();
            List<TicketsAdjuntos> ticketsAdjuntosListNew = tickets.getTicketsAdjuntosList();
            List<String> illegalOrphanMessages = null;
            for (HistorialTickets historialTicketsListOldHistorialTickets : historialTicketsListOld) {
                if (!historialTicketsListNew.contains(historialTicketsListOldHistorialTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistorialTickets " + historialTicketsListOldHistorialTickets + " since its fkTicket field is not nullable.");
                }
            }
            for (TicketsAdjuntos ticketsAdjuntosListOldTicketsAdjuntos : ticketsAdjuntosListOld) {
                if (!ticketsAdjuntosListNew.contains(ticketsAdjuntosListOldTicketsAdjuntos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TicketsAdjuntos " + ticketsAdjuntosListOldTicketsAdjuntos + " since its tickets field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (creadorNew != null) {
                creadorNew = em.getReference(creadorNew.getClass(), creadorNew.getIdUsuario());
                tickets.setCreador(creadorNew);
            }
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getIdasuntoS());
                tickets.setServicio(servicioNew);
            }
            if (fkEdificioNew != null) {
                fkEdificioNew = em.getReference(fkEdificioNew.getClass(), fkEdificioNew.getIdEdificio());
                tickets.setFkEdificio(fkEdificioNew);
            }
            if (fkareaSolicitanteNew != null) {
                fkareaSolicitanteNew = em.getReference(fkareaSolicitanteNew.getClass(), fkareaSolicitanteNew.getIdArea());
                tickets.setFkareaSolicitante(fkareaSolicitanteNew);
            }
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            tickets.setHistorialTicketsList(historialTicketsListNew);
            List<TicketsAdjuntos> attachedTicketsAdjuntosListNew = new ArrayList<TicketsAdjuntos>();
            for (TicketsAdjuntos ticketsAdjuntosListNewTicketsAdjuntosToAttach : ticketsAdjuntosListNew) {
                ticketsAdjuntosListNewTicketsAdjuntosToAttach = em.getReference(ticketsAdjuntosListNewTicketsAdjuntosToAttach.getClass(), ticketsAdjuntosListNewTicketsAdjuntosToAttach.getTicketsAdjuntosPK());
                attachedTicketsAdjuntosListNew.add(ticketsAdjuntosListNewTicketsAdjuntosToAttach);
            }
            ticketsAdjuntosListNew = attachedTicketsAdjuntosListNew;
            tickets.setTicketsAdjuntosList(ticketsAdjuntosListNew);
            tickets = em.merge(tickets);
            if (creadorOld != null && !creadorOld.equals(creadorNew)) {
                creadorOld.getTicketsList().remove(tickets);
                creadorOld = em.merge(creadorOld);
            }
            if (creadorNew != null && !creadorNew.equals(creadorOld)) {
                creadorNew.getTicketsList().add(tickets);
                creadorNew = em.merge(creadorNew);
            }
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getTicketsList().remove(tickets);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getTicketsList().add(tickets);
                servicioNew = em.merge(servicioNew);
            }
            if (fkEdificioOld != null && !fkEdificioOld.equals(fkEdificioNew)) {
                fkEdificioOld.getTicketsList().remove(tickets);
                fkEdificioOld = em.merge(fkEdificioOld);
            }
            if (fkEdificioNew != null && !fkEdificioNew.equals(fkEdificioOld)) {
                fkEdificioNew.getTicketsList().add(tickets);
                fkEdificioNew = em.merge(fkEdificioNew);
            }
            if (fkareaSolicitanteOld != null && !fkareaSolicitanteOld.equals(fkareaSolicitanteNew)) {
                fkareaSolicitanteOld.getTicketsList().remove(tickets);
                fkareaSolicitanteOld = em.merge(fkareaSolicitanteOld);
            }
            if (fkareaSolicitanteNew != null && !fkareaSolicitanteNew.equals(fkareaSolicitanteOld)) {
                fkareaSolicitanteNew.getTicketsList().add(tickets);
                fkareaSolicitanteNew = em.merge(fkareaSolicitanteNew);
            }
            for (HistorialTickets historialTicketsListNewHistorialTickets : historialTicketsListNew) {
                if (!historialTicketsListOld.contains(historialTicketsListNewHistorialTickets)) {
                    Tickets oldFkTicketOfHistorialTicketsListNewHistorialTickets = historialTicketsListNewHistorialTickets.getFkTicket();
                    historialTicketsListNewHistorialTickets.setFkTicket(tickets);
                    historialTicketsListNewHistorialTickets = em.merge(historialTicketsListNewHistorialTickets);
                    if (oldFkTicketOfHistorialTicketsListNewHistorialTickets != null && !oldFkTicketOfHistorialTicketsListNewHistorialTickets.equals(tickets)) {
                        oldFkTicketOfHistorialTicketsListNewHistorialTickets.getHistorialTicketsList().remove(historialTicketsListNewHistorialTickets);
                        oldFkTicketOfHistorialTicketsListNewHistorialTickets = em.merge(oldFkTicketOfHistorialTicketsListNewHistorialTickets);
                    }
                }
            }
            for (TicketsAdjuntos ticketsAdjuntosListNewTicketsAdjuntos : ticketsAdjuntosListNew) {
                if (!ticketsAdjuntosListOld.contains(ticketsAdjuntosListNewTicketsAdjuntos)) {
                    Tickets oldTicketsOfTicketsAdjuntosListNewTicketsAdjuntos = ticketsAdjuntosListNewTicketsAdjuntos.getTickets();
                    ticketsAdjuntosListNewTicketsAdjuntos.setTickets(tickets);
                    ticketsAdjuntosListNewTicketsAdjuntos = em.merge(ticketsAdjuntosListNewTicketsAdjuntos);
                    if (oldTicketsOfTicketsAdjuntosListNewTicketsAdjuntos != null && !oldTicketsOfTicketsAdjuntosListNewTicketsAdjuntos.equals(tickets)) {
                        oldTicketsOfTicketsAdjuntosListNewTicketsAdjuntos.getTicketsAdjuntosList().remove(ticketsAdjuntosListNewTicketsAdjuntos);
                        oldTicketsOfTicketsAdjuntosListNewTicketsAdjuntos = em.merge(oldTicketsOfTicketsAdjuntosListNewTicketsAdjuntos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tickets.getIdTicket();
                if (findTickets(id) == null) {
                    throw new NonexistentEntityException("The tickets with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets tickets;
            try {
                tickets = em.getReference(Tickets.class, id);
                tickets.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tickets with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<HistorialTickets> historialTicketsListOrphanCheck = tickets.getHistorialTicketsList();
            for (HistorialTickets historialTicketsListOrphanCheckHistorialTickets : historialTicketsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tickets (" + tickets + ") cannot be destroyed since the HistorialTickets " + historialTicketsListOrphanCheckHistorialTickets + " in its historialTicketsList field has a non-nullable fkTicket field.");
            }
            List<TicketsAdjuntos> ticketsAdjuntosListOrphanCheck = tickets.getTicketsAdjuntosList();
            for (TicketsAdjuntos ticketsAdjuntosListOrphanCheckTicketsAdjuntos : ticketsAdjuntosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tickets (" + tickets + ") cannot be destroyed since the TicketsAdjuntos " + ticketsAdjuntosListOrphanCheckTicketsAdjuntos + " in its ticketsAdjuntosList field has a non-nullable tickets field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuarios creador = tickets.getCreador();
            if (creador != null) {
                creador.getTicketsList().remove(tickets);
                creador = em.merge(creador);
            }
            Servicios servicio = tickets.getServicio();
            if (servicio != null) {
                servicio.getTicketsList().remove(tickets);
                servicio = em.merge(servicio);
            }
            Edificios fkEdificio = tickets.getFkEdificio();
            if (fkEdificio != null) {
                fkEdificio.getTicketsList().remove(tickets);
                fkEdificio = em.merge(fkEdificio);
            }
            Areas fkareaSolicitante = tickets.getFkareaSolicitante();
            if (fkareaSolicitante != null) {
                fkareaSolicitante.getTicketsList().remove(tickets);
                fkareaSolicitante = em.merge(fkareaSolicitante);
            }
            em.remove(tickets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tickets> findTicketsEntities() {
        return findTicketsEntities(true, -1, -1);
    }

    public List<Tickets> findTicketsEntities(int maxResults, int firstResult) {
        return findTicketsEntities(false, maxResults, firstResult);
    }

    private List<Tickets> findTicketsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tickets.class));
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

    public Tickets findTickets(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tickets.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tickets> rt = cq.from(Tickets.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
