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
import mscb.tick.negocio.entidades.HistorialTickets;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.RazonesTransferencias;

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
        if (razonesTransferencias.getHistorialTicketsList() == null) {
            razonesTransferencias.setHistorialTicketsList(new ArrayList<HistorialTickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : razonesTransferencias.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            razonesTransferencias.setHistorialTicketsList(attachedHistorialTicketsList);
            em.persist(razonesTransferencias);
            for (HistorialTickets historialTicketsListHistorialTickets : razonesTransferencias.getHistorialTicketsList()) {
                RazonesTransferencias oldFkRazonOfHistorialTicketsListHistorialTickets = historialTicketsListHistorialTickets.getFkRazon();
                historialTicketsListHistorialTickets.setFkRazon(razonesTransferencias);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
                if (oldFkRazonOfHistorialTicketsListHistorialTickets != null) {
                    oldFkRazonOfHistorialTicketsListHistorialTickets.getHistorialTicketsList().remove(historialTicketsListHistorialTickets);
                    oldFkRazonOfHistorialTicketsListHistorialTickets = em.merge(oldFkRazonOfHistorialTicketsListHistorialTickets);
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
            List<HistorialTickets> historialTicketsListOld = persistentRazonesTransferencias.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = razonesTransferencias.getHistorialTicketsList();
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            razonesTransferencias.setHistorialTicketsList(historialTicketsListNew);
            razonesTransferencias = em.merge(razonesTransferencias);
            for (HistorialTickets historialTicketsListOldHistorialTickets : historialTicketsListOld) {
                if (!historialTicketsListNew.contains(historialTicketsListOldHistorialTickets)) {
                    historialTicketsListOldHistorialTickets.setFkRazon(null);
                    historialTicketsListOldHistorialTickets = em.merge(historialTicketsListOldHistorialTickets);
                }
            }
            for (HistorialTickets historialTicketsListNewHistorialTickets : historialTicketsListNew) {
                if (!historialTicketsListOld.contains(historialTicketsListNewHistorialTickets)) {
                    RazonesTransferencias oldFkRazonOfHistorialTicketsListNewHistorialTickets = historialTicketsListNewHistorialTickets.getFkRazon();
                    historialTicketsListNewHistorialTickets.setFkRazon(razonesTransferencias);
                    historialTicketsListNewHistorialTickets = em.merge(historialTicketsListNewHistorialTickets);
                    if (oldFkRazonOfHistorialTicketsListNewHistorialTickets != null && !oldFkRazonOfHistorialTicketsListNewHistorialTickets.equals(razonesTransferencias)) {
                        oldFkRazonOfHistorialTicketsListNewHistorialTickets.getHistorialTicketsList().remove(historialTicketsListNewHistorialTickets);
                        oldFkRazonOfHistorialTicketsListNewHistorialTickets = em.merge(oldFkRazonOfHistorialTicketsListNewHistorialTickets);
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
            List<HistorialTickets> historialTicketsList = razonesTransferencias.getHistorialTicketsList();
            for (HistorialTickets historialTicketsListHistorialTickets : historialTicketsList) {
                historialTicketsListHistorialTickets.setFkRazon(null);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
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
