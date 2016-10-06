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
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.RazonesTransferencias;
import mscb.tick.entidades.Usuarios;
import mscb.tick.entidades.Servicios;
import mscb.tick.entidades.HistorialTickets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Tickets;

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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areas fkAreaEmisor = tickets.getFkAreaEmisor();
            if (fkAreaEmisor != null) {
                fkAreaEmisor = em.getReference(fkAreaEmisor.getClass(), fkAreaEmisor.getIdArea());
                tickets.setFkAreaEmisor(fkAreaEmisor);
            }
            Estados fkEstado = tickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado = em.getReference(fkEstado.getClass(), fkEstado.getIdEstado());
                tickets.setFkEstado(fkEstado);
            }
            RazonesTransferencias fkRazon = tickets.getFkRazon();
            if (fkRazon != null) {
                fkRazon = em.getReference(fkRazon.getClass(), fkRazon.getIdRazon());
                tickets.setFkRazon(fkRazon);
            }
            Usuarios fkUsuarioEmisor = tickets.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor = em.getReference(fkUsuarioEmisor.getClass(), fkUsuarioEmisor.getIdUsuario());
                tickets.setFkUsuarioEmisor(fkUsuarioEmisor);
            }
            Usuarios usuarioReceptor = tickets.getUsuarioReceptor();
            if (usuarioReceptor != null) {
                usuarioReceptor = em.getReference(usuarioReceptor.getClass(), usuarioReceptor.getIdUsuario());
                tickets.setUsuarioReceptor(usuarioReceptor);
            }
            Areas fkAreaReceptor = tickets.getFkAreaReceptor();
            if (fkAreaReceptor != null) {
                fkAreaReceptor = em.getReference(fkAreaReceptor.getClass(), fkAreaReceptor.getIdArea());
                tickets.setFkAreaReceptor(fkAreaReceptor);
            }
            Servicios asunto = tickets.getAsunto();
            if (asunto != null) {
                asunto = em.getReference(asunto.getClass(), asunto.getIdasuntoS());
                tickets.setAsunto(asunto);
            }
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : tickets.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            tickets.setHistorialTicketsList(attachedHistorialTicketsList);
            em.persist(tickets);
            if (fkAreaEmisor != null) {
                fkAreaEmisor.getTicketsList().add(tickets);
                fkAreaEmisor = em.merge(fkAreaEmisor);
            }
            if (fkEstado != null) {
                fkEstado.getTicketsList().add(tickets);
                fkEstado = em.merge(fkEstado);
            }
            if (fkRazon != null) {
                fkRazon.getTicketsList().add(tickets);
                fkRazon = em.merge(fkRazon);
            }
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getTicketsList().add(tickets);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            if (usuarioReceptor != null) {
                usuarioReceptor.getTicketsList().add(tickets);
                usuarioReceptor = em.merge(usuarioReceptor);
            }
            if (fkAreaReceptor != null) {
                fkAreaReceptor.getTicketsList().add(tickets);
                fkAreaReceptor = em.merge(fkAreaReceptor);
            }
            if (asunto != null) {
                asunto.getTicketsList().add(tickets);
                asunto = em.merge(asunto);
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
            Areas fkAreaEmisorOld = persistentTickets.getFkAreaEmisor();
            Areas fkAreaEmisorNew = tickets.getFkAreaEmisor();
            Estados fkEstadoOld = persistentTickets.getFkEstado();
            Estados fkEstadoNew = tickets.getFkEstado();
            RazonesTransferencias fkRazonOld = persistentTickets.getFkRazon();
            RazonesTransferencias fkRazonNew = tickets.getFkRazon();
            Usuarios fkUsuarioEmisorOld = persistentTickets.getFkUsuarioEmisor();
            Usuarios fkUsuarioEmisorNew = tickets.getFkUsuarioEmisor();
            Usuarios usuarioReceptorOld = persistentTickets.getUsuarioReceptor();
            Usuarios usuarioReceptorNew = tickets.getUsuarioReceptor();
            Areas fkAreaReceptorOld = persistentTickets.getFkAreaReceptor();
            Areas fkAreaReceptorNew = tickets.getFkAreaReceptor();
            Servicios asuntoOld = persistentTickets.getAsunto();
            Servicios asuntoNew = tickets.getAsunto();
            List<HistorialTickets> historialTicketsListOld = persistentTickets.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = tickets.getHistorialTicketsList();
            List<String> illegalOrphanMessages = null;
            for (HistorialTickets historialTicketsListOldHistorialTickets : historialTicketsListOld) {
                if (!historialTicketsListNew.contains(historialTicketsListOldHistorialTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistorialTickets " + historialTicketsListOldHistorialTickets + " since its fkTicket field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkAreaEmisorNew != null) {
                fkAreaEmisorNew = em.getReference(fkAreaEmisorNew.getClass(), fkAreaEmisorNew.getIdArea());
                tickets.setFkAreaEmisor(fkAreaEmisorNew);
            }
            if (fkEstadoNew != null) {
                fkEstadoNew = em.getReference(fkEstadoNew.getClass(), fkEstadoNew.getIdEstado());
                tickets.setFkEstado(fkEstadoNew);
            }
            if (fkRazonNew != null) {
                fkRazonNew = em.getReference(fkRazonNew.getClass(), fkRazonNew.getIdRazon());
                tickets.setFkRazon(fkRazonNew);
            }
            if (fkUsuarioEmisorNew != null) {
                fkUsuarioEmisorNew = em.getReference(fkUsuarioEmisorNew.getClass(), fkUsuarioEmisorNew.getIdUsuario());
                tickets.setFkUsuarioEmisor(fkUsuarioEmisorNew);
            }
            if (usuarioReceptorNew != null) {
                usuarioReceptorNew = em.getReference(usuarioReceptorNew.getClass(), usuarioReceptorNew.getIdUsuario());
                tickets.setUsuarioReceptor(usuarioReceptorNew);
            }
            if (fkAreaReceptorNew != null) {
                fkAreaReceptorNew = em.getReference(fkAreaReceptorNew.getClass(), fkAreaReceptorNew.getIdArea());
                tickets.setFkAreaReceptor(fkAreaReceptorNew);
            }
            if (asuntoNew != null) {
                asuntoNew = em.getReference(asuntoNew.getClass(), asuntoNew.getIdasuntoS());
                tickets.setAsunto(asuntoNew);
            }
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            tickets.setHistorialTicketsList(historialTicketsListNew);
            tickets = em.merge(tickets);
            if (fkAreaEmisorOld != null && !fkAreaEmisorOld.equals(fkAreaEmisorNew)) {
                fkAreaEmisorOld.getTicketsList().remove(tickets);
                fkAreaEmisorOld = em.merge(fkAreaEmisorOld);
            }
            if (fkAreaEmisorNew != null && !fkAreaEmisorNew.equals(fkAreaEmisorOld)) {
                fkAreaEmisorNew.getTicketsList().add(tickets);
                fkAreaEmisorNew = em.merge(fkAreaEmisorNew);
            }
            if (fkEstadoOld != null && !fkEstadoOld.equals(fkEstadoNew)) {
                fkEstadoOld.getTicketsList().remove(tickets);
                fkEstadoOld = em.merge(fkEstadoOld);
            }
            if (fkEstadoNew != null && !fkEstadoNew.equals(fkEstadoOld)) {
                fkEstadoNew.getTicketsList().add(tickets);
                fkEstadoNew = em.merge(fkEstadoNew);
            }
            if (fkRazonOld != null && !fkRazonOld.equals(fkRazonNew)) {
                fkRazonOld.getTicketsList().remove(tickets);
                fkRazonOld = em.merge(fkRazonOld);
            }
            if (fkRazonNew != null && !fkRazonNew.equals(fkRazonOld)) {
                fkRazonNew.getTicketsList().add(tickets);
                fkRazonNew = em.merge(fkRazonNew);
            }
            if (fkUsuarioEmisorOld != null && !fkUsuarioEmisorOld.equals(fkUsuarioEmisorNew)) {
                fkUsuarioEmisorOld.getTicketsList().remove(tickets);
                fkUsuarioEmisorOld = em.merge(fkUsuarioEmisorOld);
            }
            if (fkUsuarioEmisorNew != null && !fkUsuarioEmisorNew.equals(fkUsuarioEmisorOld)) {
                fkUsuarioEmisorNew.getTicketsList().add(tickets);
                fkUsuarioEmisorNew = em.merge(fkUsuarioEmisorNew);
            }
            if (usuarioReceptorOld != null && !usuarioReceptorOld.equals(usuarioReceptorNew)) {
                usuarioReceptorOld.getTicketsList().remove(tickets);
                usuarioReceptorOld = em.merge(usuarioReceptorOld);
            }
            if (usuarioReceptorNew != null && !usuarioReceptorNew.equals(usuarioReceptorOld)) {
                usuarioReceptorNew.getTicketsList().add(tickets);
                usuarioReceptorNew = em.merge(usuarioReceptorNew);
            }
            if (fkAreaReceptorOld != null && !fkAreaReceptorOld.equals(fkAreaReceptorNew)) {
                fkAreaReceptorOld.getTicketsList().remove(tickets);
                fkAreaReceptorOld = em.merge(fkAreaReceptorOld);
            }
            if (fkAreaReceptorNew != null && !fkAreaReceptorNew.equals(fkAreaReceptorOld)) {
                fkAreaReceptorNew.getTicketsList().add(tickets);
                fkAreaReceptorNew = em.merge(fkAreaReceptorNew);
            }
            if (asuntoOld != null && !asuntoOld.equals(asuntoNew)) {
                asuntoOld.getTicketsList().remove(tickets);
                asuntoOld = em.merge(asuntoOld);
            }
            if (asuntoNew != null && !asuntoNew.equals(asuntoOld)) {
                asuntoNew.getTicketsList().add(tickets);
                asuntoNew = em.merge(asuntoNew);
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Areas fkAreaEmisor = tickets.getFkAreaEmisor();
            if (fkAreaEmisor != null) {
                fkAreaEmisor.getTicketsList().remove(tickets);
                fkAreaEmisor = em.merge(fkAreaEmisor);
            }
            Estados fkEstado = tickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado.getTicketsList().remove(tickets);
                fkEstado = em.merge(fkEstado);
            }
            RazonesTransferencias fkRazon = tickets.getFkRazon();
            if (fkRazon != null) {
                fkRazon.getTicketsList().remove(tickets);
                fkRazon = em.merge(fkRazon);
            }
            Usuarios fkUsuarioEmisor = tickets.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getTicketsList().remove(tickets);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            Usuarios usuarioReceptor = tickets.getUsuarioReceptor();
            if (usuarioReceptor != null) {
                usuarioReceptor.getTicketsList().remove(tickets);
                usuarioReceptor = em.merge(usuarioReceptor);
            }
            Areas fkAreaReceptor = tickets.getFkAreaReceptor();
            if (fkAreaReceptor != null) {
                fkAreaReceptor.getTicketsList().remove(tickets);
                fkAreaReceptor = em.merge(fkAreaReceptor);
            }
            Servicios asunto = tickets.getAsunto();
            if (asunto != null) {
                asunto.getTicketsList().remove(tickets);
                asunto = em.merge(asunto);
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
