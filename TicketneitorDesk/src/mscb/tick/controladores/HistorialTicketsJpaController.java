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
import mscb.tick.entidades.Tickets;
import mscb.tick.entidades.Usuarios;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.HistorialTickets;

/**
 *
 * @author Administrador
 */
public class HistorialTicketsJpaController implements Serializable {

    public HistorialTicketsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistorialTickets historialTickets) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets fkTicket = historialTickets.getFkTicket();
            if (fkTicket != null) {
                fkTicket = em.getReference(fkTicket.getClass(), fkTicket.getIdTicket());
                historialTickets.setFkTicket(fkTicket);
            }
            Usuarios fkUsuarioEmisor = historialTickets.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor = em.getReference(fkUsuarioEmisor.getClass(), fkUsuarioEmisor.getIdUsuario());
                historialTickets.setFkUsuarioEmisor(fkUsuarioEmisor);
            }
            Usuarios fkUsuarioReceptor = historialTickets.getFkUsuarioReceptor();
            if (fkUsuarioReceptor != null) {
                fkUsuarioReceptor = em.getReference(fkUsuarioReceptor.getClass(), fkUsuarioReceptor.getIdUsuario());
                historialTickets.setFkUsuarioReceptor(fkUsuarioReceptor);
            }
            Estados fkEstado = historialTickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado = em.getReference(fkEstado.getClass(), fkEstado.getIdEstado());
                historialTickets.setFkEstado(fkEstado);
            }
            em.persist(historialTickets);
            if (fkTicket != null) {
                fkTicket.getHistorialTicketsList().add(historialTickets);
                fkTicket = em.merge(fkTicket);
            }
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getHistorialTicketsList().add(historialTickets);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            if (fkUsuarioReceptor != null) {
                fkUsuarioReceptor.getHistorialTicketsList().add(historialTickets);
                fkUsuarioReceptor = em.merge(fkUsuarioReceptor);
            }
            if (fkEstado != null) {
                fkEstado.getHistorialTicketsList().add(historialTickets);
                fkEstado = em.merge(fkEstado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistorialTickets historialTickets) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistorialTickets persistentHistorialTickets = em.find(HistorialTickets.class, historialTickets.getIdHistorial());
            Tickets fkTicketOld = persistentHistorialTickets.getFkTicket();
            Tickets fkTicketNew = historialTickets.getFkTicket();
            Usuarios fkUsuarioEmisorOld = persistentHistorialTickets.getFkUsuarioEmisor();
            Usuarios fkUsuarioEmisorNew = historialTickets.getFkUsuarioEmisor();
            Usuarios fkUsuarioReceptorOld = persistentHistorialTickets.getFkUsuarioReceptor();
            Usuarios fkUsuarioReceptorNew = historialTickets.getFkUsuarioReceptor();
            Estados fkEstadoOld = persistentHistorialTickets.getFkEstado();
            Estados fkEstadoNew = historialTickets.getFkEstado();
            if (fkTicketNew != null) {
                fkTicketNew = em.getReference(fkTicketNew.getClass(), fkTicketNew.getIdTicket());
                historialTickets.setFkTicket(fkTicketNew);
            }
            if (fkUsuarioEmisorNew != null) {
                fkUsuarioEmisorNew = em.getReference(fkUsuarioEmisorNew.getClass(), fkUsuarioEmisorNew.getIdUsuario());
                historialTickets.setFkUsuarioEmisor(fkUsuarioEmisorNew);
            }
            if (fkUsuarioReceptorNew != null) {
                fkUsuarioReceptorNew = em.getReference(fkUsuarioReceptorNew.getClass(), fkUsuarioReceptorNew.getIdUsuario());
                historialTickets.setFkUsuarioReceptor(fkUsuarioReceptorNew);
            }
            if (fkEstadoNew != null) {
                fkEstadoNew = em.getReference(fkEstadoNew.getClass(), fkEstadoNew.getIdEstado());
                historialTickets.setFkEstado(fkEstadoNew);
            }
            historialTickets = em.merge(historialTickets);
            if (fkTicketOld != null && !fkTicketOld.equals(fkTicketNew)) {
                fkTicketOld.getHistorialTicketsList().remove(historialTickets);
                fkTicketOld = em.merge(fkTicketOld);
            }
            if (fkTicketNew != null && !fkTicketNew.equals(fkTicketOld)) {
                fkTicketNew.getHistorialTicketsList().add(historialTickets);
                fkTicketNew = em.merge(fkTicketNew);
            }
            if (fkUsuarioEmisorOld != null && !fkUsuarioEmisorOld.equals(fkUsuarioEmisorNew)) {
                fkUsuarioEmisorOld.getHistorialTicketsList().remove(historialTickets);
                fkUsuarioEmisorOld = em.merge(fkUsuarioEmisorOld);
            }
            if (fkUsuarioEmisorNew != null && !fkUsuarioEmisorNew.equals(fkUsuarioEmisorOld)) {
                fkUsuarioEmisorNew.getHistorialTicketsList().add(historialTickets);
                fkUsuarioEmisorNew = em.merge(fkUsuarioEmisorNew);
            }
            if (fkUsuarioReceptorOld != null && !fkUsuarioReceptorOld.equals(fkUsuarioReceptorNew)) {
                fkUsuarioReceptorOld.getHistorialTicketsList().remove(historialTickets);
                fkUsuarioReceptorOld = em.merge(fkUsuarioReceptorOld);
            }
            if (fkUsuarioReceptorNew != null && !fkUsuarioReceptorNew.equals(fkUsuarioReceptorOld)) {
                fkUsuarioReceptorNew.getHistorialTicketsList().add(historialTickets);
                fkUsuarioReceptorNew = em.merge(fkUsuarioReceptorNew);
            }
            if (fkEstadoOld != null && !fkEstadoOld.equals(fkEstadoNew)) {
                fkEstadoOld.getHistorialTicketsList().remove(historialTickets);
                fkEstadoOld = em.merge(fkEstadoOld);
            }
            if (fkEstadoNew != null && !fkEstadoNew.equals(fkEstadoOld)) {
                fkEstadoNew.getHistorialTicketsList().add(historialTickets);
                fkEstadoNew = em.merge(fkEstadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historialTickets.getIdHistorial();
                if (findHistorialTickets(id) == null) {
                    throw new NonexistentEntityException("The historialTickets with id " + id + " no longer exists.");
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
            HistorialTickets historialTickets;
            try {
                historialTickets = em.getReference(HistorialTickets.class, id);
                historialTickets.getIdHistorial();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historialTickets with id " + id + " no longer exists.", enfe);
            }
            Tickets fkTicket = historialTickets.getFkTicket();
            if (fkTicket != null) {
                fkTicket.getHistorialTicketsList().remove(historialTickets);
                fkTicket = em.merge(fkTicket);
            }
            Usuarios fkUsuarioEmisor = historialTickets.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getHistorialTicketsList().remove(historialTickets);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            Usuarios fkUsuarioReceptor = historialTickets.getFkUsuarioReceptor();
            if (fkUsuarioReceptor != null) {
                fkUsuarioReceptor.getHistorialTicketsList().remove(historialTickets);
                fkUsuarioReceptor = em.merge(fkUsuarioReceptor);
            }
            Estados fkEstado = historialTickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado.getHistorialTicketsList().remove(historialTickets);
                fkEstado = em.merge(fkEstado);
            }
            em.remove(historialTickets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistorialTickets> findHistorialTicketsEntities() {
        return findHistorialTicketsEntities(true, -1, -1);
    }

    public List<HistorialTickets> findHistorialTicketsEntities(int maxResults, int firstResult) {
        return findHistorialTicketsEntities(false, maxResults, firstResult);
    }

    private List<HistorialTickets> findHistorialTicketsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistorialTickets.class));
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

    public HistorialTickets findHistorialTickets(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistorialTickets.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistorialTicketsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistorialTickets> rt = cq.from(HistorialTickets.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
