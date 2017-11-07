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
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Estados;

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
        if (estados.getHistorialTicketsList() == null) {
            estados.setHistorialTicketsList(new ArrayList<HistorialTickets>());
        }
        if (estados.getAsuntosList() == null) {
            estados.setAsuntosList(new ArrayList<Asuntos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : estados.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            estados.setHistorialTicketsList(attachedHistorialTicketsList);
            List<Asuntos> attachedAsuntosList = new ArrayList<Asuntos>();
            for (Asuntos asuntosListAsuntosToAttach : estados.getAsuntosList()) {
                asuntosListAsuntosToAttach = em.getReference(asuntosListAsuntosToAttach.getClass(), asuntosListAsuntosToAttach.getIdasuntoP());
                attachedAsuntosList.add(asuntosListAsuntosToAttach);
            }
            estados.setAsuntosList(attachedAsuntosList);
            em.persist(estados);
            for (HistorialTickets historialTicketsListHistorialTickets : estados.getHistorialTicketsList()) {
                Estados oldFkEstadoOfHistorialTicketsListHistorialTickets = historialTicketsListHistorialTickets.getFkEstado();
                historialTicketsListHistorialTickets.setFkEstado(estados);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
                if (oldFkEstadoOfHistorialTicketsListHistorialTickets != null) {
                    oldFkEstadoOfHistorialTicketsListHistorialTickets.getHistorialTicketsList().remove(historialTicketsListHistorialTickets);
                    oldFkEstadoOfHistorialTicketsListHistorialTickets = em.merge(oldFkEstadoOfHistorialTicketsListHistorialTickets);
                }
            }
            for (Asuntos asuntosListAsuntos : estados.getAsuntosList()) {
                asuntosListAsuntos.getEstadosList().add(estados);
                asuntosListAsuntos = em.merge(asuntosListAsuntos);
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
            List<HistorialTickets> historialTicketsListOld = persistentEstados.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = estados.getHistorialTicketsList();
            List<Asuntos> asuntosListOld = persistentEstados.getAsuntosList();
            List<Asuntos> asuntosListNew = estados.getAsuntosList();
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            estados.setHistorialTicketsList(historialTicketsListNew);
            List<Asuntos> attachedAsuntosListNew = new ArrayList<Asuntos>();
            for (Asuntos asuntosListNewAsuntosToAttach : asuntosListNew) {
                asuntosListNewAsuntosToAttach = em.getReference(asuntosListNewAsuntosToAttach.getClass(), asuntosListNewAsuntosToAttach.getIdasuntoP());
                attachedAsuntosListNew.add(asuntosListNewAsuntosToAttach);
            }
            asuntosListNew = attachedAsuntosListNew;
            estados.setAsuntosList(asuntosListNew);
            estados = em.merge(estados);
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
            for (Asuntos asuntosListOldAsuntos : asuntosListOld) {
                if (!asuntosListNew.contains(asuntosListOldAsuntos)) {
                    asuntosListOldAsuntos.getEstadosList().remove(estados);
                    asuntosListOldAsuntos = em.merge(asuntosListOldAsuntos);
                }
            }
            for (Asuntos asuntosListNewAsuntos : asuntosListNew) {
                if (!asuntosListOld.contains(asuntosListNewAsuntos)) {
                    asuntosListNewAsuntos.getEstadosList().add(estados);
                    asuntosListNewAsuntos = em.merge(asuntosListNewAsuntos);
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
            List<HistorialTickets> historialTicketsList = estados.getHistorialTicketsList();
            for (HistorialTickets historialTicketsListHistorialTickets : historialTicketsList) {
                historialTicketsListHistorialTickets.setFkEstado(null);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
            }
            List<Asuntos> asuntosList = estados.getAsuntosList();
            for (Asuntos asuntosListAsuntos : asuntosList) {
                asuntosListAsuntos.getEstadosList().remove(estados);
                asuntosListAsuntos = em.merge(asuntosListAsuntos);
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
