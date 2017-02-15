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
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.RazonesTransferencias;

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
            Usuarios fkUsuario = historialTickets.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario = em.getReference(fkUsuario.getClass(), fkUsuario.getIdUsuario());
                historialTickets.setFkUsuario(fkUsuario);
            }
            Estados fkEstado = historialTickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado = em.getReference(fkEstado.getClass(), fkEstado.getIdEstado());
                historialTickets.setFkEstado(fkEstado);
            }
            RazonesTransferencias fkRazon = historialTickets.getFkRazon();
            if (fkRazon != null) {
                fkRazon = em.getReference(fkRazon.getClass(), fkRazon.getIdRazon());
                historialTickets.setFkRazon(fkRazon);
            }
            em.persist(historialTickets);
            if (fkTicket != null) {
                fkTicket.getHistorialTicketsList().add(historialTickets);
                fkTicket = em.merge(fkTicket);
            }
            if (fkUsuario != null) {
                fkUsuario.getHistorialTicketsList().add(historialTickets);
                fkUsuario = em.merge(fkUsuario);
            }
            if (fkEstado != null) {
                fkEstado.getHistorialTicketsList().add(historialTickets);
                fkEstado = em.merge(fkEstado);
            }
            if (fkRazon != null) {
                fkRazon.getHistorialTicketsList().add(historialTickets);
                fkRazon = em.merge(fkRazon);
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
            Usuarios fkUsuarioOld = persistentHistorialTickets.getFkUsuario();
            Usuarios fkUsuarioNew = historialTickets.getFkUsuario();
            Estados fkEstadoOld = persistentHistorialTickets.getFkEstado();
            Estados fkEstadoNew = historialTickets.getFkEstado();
            RazonesTransferencias fkRazonOld = persistentHistorialTickets.getFkRazon();
            RazonesTransferencias fkRazonNew = historialTickets.getFkRazon();
            if (fkTicketNew != null) {
                fkTicketNew = em.getReference(fkTicketNew.getClass(), fkTicketNew.getIdTicket());
                historialTickets.setFkTicket(fkTicketNew);
            }
            if (fkUsuarioNew != null) {
                fkUsuarioNew = em.getReference(fkUsuarioNew.getClass(), fkUsuarioNew.getIdUsuario());
                historialTickets.setFkUsuario(fkUsuarioNew);
            }
            if (fkEstadoNew != null) {
                fkEstadoNew = em.getReference(fkEstadoNew.getClass(), fkEstadoNew.getIdEstado());
                historialTickets.setFkEstado(fkEstadoNew);
            }
            if (fkRazonNew != null) {
                fkRazonNew = em.getReference(fkRazonNew.getClass(), fkRazonNew.getIdRazon());
                historialTickets.setFkRazon(fkRazonNew);
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
            if (fkUsuarioOld != null && !fkUsuarioOld.equals(fkUsuarioNew)) {
                fkUsuarioOld.getHistorialTicketsList().remove(historialTickets);
                fkUsuarioOld = em.merge(fkUsuarioOld);
            }
            if (fkUsuarioNew != null && !fkUsuarioNew.equals(fkUsuarioOld)) {
                fkUsuarioNew.getHistorialTicketsList().add(historialTickets);
                fkUsuarioNew = em.merge(fkUsuarioNew);
            }
            if (fkEstadoOld != null && !fkEstadoOld.equals(fkEstadoNew)) {
                fkEstadoOld.getHistorialTicketsList().remove(historialTickets);
                fkEstadoOld = em.merge(fkEstadoOld);
            }
            if (fkEstadoNew != null && !fkEstadoNew.equals(fkEstadoOld)) {
                fkEstadoNew.getHistorialTicketsList().add(historialTickets);
                fkEstadoNew = em.merge(fkEstadoNew);
            }
            if (fkRazonOld != null && !fkRazonOld.equals(fkRazonNew)) {
                fkRazonOld.getHistorialTicketsList().remove(historialTickets);
                fkRazonOld = em.merge(fkRazonOld);
            }
            if (fkRazonNew != null && !fkRazonNew.equals(fkRazonOld)) {
                fkRazonNew.getHistorialTicketsList().add(historialTickets);
                fkRazonNew = em.merge(fkRazonNew);
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
            Usuarios fkUsuario = historialTickets.getFkUsuario();
            if (fkUsuario != null) {
                fkUsuario.getHistorialTicketsList().remove(historialTickets);
                fkUsuario = em.merge(fkUsuario);
            }
            Estados fkEstado = historialTickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado.getHistorialTicketsList().remove(historialTickets);
                fkEstado = em.merge(fkEstado);
            }
            RazonesTransferencias fkRazon = historialTickets.getFkRazon();
            if (fkRazon != null) {
                fkRazon.getHistorialTicketsList().remove(historialTickets);
                fkRazon = em.merge(fkRazon);
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
