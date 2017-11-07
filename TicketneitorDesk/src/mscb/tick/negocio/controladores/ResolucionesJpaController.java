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
import mscb.tick.negocio.controladores.exceptions.PreexistingEntityException;
import mscb.tick.negocio.entidades.Resoluciones;
import mscb.tick.negocio.entidades.ResolucionesPK;
import mscb.tick.negocio.entidades.ResolucionesTipos;

/**
 *
 * @author Administrador
 */
public class ResolucionesJpaController implements Serializable {

    public ResolucionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Resoluciones resoluciones) throws PreexistingEntityException, Exception {
        if (resoluciones.getResolucionesPK() == null) {
            resoluciones.setResolucionesPK(new ResolucionesPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResolucionesTipos idtiporesolucion = resoluciones.getIdtiporesolucion();
            if (idtiporesolucion != null) {
                idtiporesolucion = em.getReference(idtiporesolucion.getClass(), idtiporesolucion.getIdtiporesolucion());
                resoluciones.setIdtiporesolucion(idtiporesolucion);
            }
            em.persist(resoluciones);
            if (idtiporesolucion != null) {
                idtiporesolucion.getResolucionesList().add(resoluciones);
                idtiporesolucion = em.merge(idtiporesolucion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResoluciones(resoluciones.getResolucionesPK()) != null) {
                throw new PreexistingEntityException("Resoluciones " + resoluciones + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Resoluciones resoluciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resoluciones persistentResoluciones = em.find(Resoluciones.class, resoluciones.getResolucionesPK());
            ResolucionesTipos idtiporesolucionOld = persistentResoluciones.getIdtiporesolucion();
            ResolucionesTipos idtiporesolucionNew = resoluciones.getIdtiporesolucion();
            if (idtiporesolucionNew != null) {
                idtiporesolucionNew = em.getReference(idtiporesolucionNew.getClass(), idtiporesolucionNew.getIdtiporesolucion());
                resoluciones.setIdtiporesolucion(idtiporesolucionNew);
            }
            resoluciones = em.merge(resoluciones);
            if (idtiporesolucionOld != null && !idtiporesolucionOld.equals(idtiporesolucionNew)) {
                idtiporesolucionOld.getResolucionesList().remove(resoluciones);
                idtiporesolucionOld = em.merge(idtiporesolucionOld);
            }
            if (idtiporesolucionNew != null && !idtiporesolucionNew.equals(idtiporesolucionOld)) {
                idtiporesolucionNew.getResolucionesList().add(resoluciones);
                idtiporesolucionNew = em.merge(idtiporesolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ResolucionesPK id = resoluciones.getResolucionesPK();
                if (findResoluciones(id) == null) {
                    throw new NonexistentEntityException("The resoluciones with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ResolucionesPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Resoluciones resoluciones;
            try {
                resoluciones = em.getReference(Resoluciones.class, id);
                resoluciones.getResolucionesPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resoluciones with id " + id + " no longer exists.", enfe);
            }
            ResolucionesTipos idtiporesolucion = resoluciones.getIdtiporesolucion();
            if (idtiporesolucion != null) {
                idtiporesolucion.getResolucionesList().remove(resoluciones);
                idtiporesolucion = em.merge(idtiporesolucion);
            }
            em.remove(resoluciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Resoluciones> findResolucionesEntities() {
        return findResolucionesEntities(true, -1, -1);
    }

    public List<Resoluciones> findResolucionesEntities(int maxResults, int firstResult) {
        return findResolucionesEntities(false, maxResults, firstResult);
    }

    private List<Resoluciones> findResolucionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Resoluciones.class));
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

    public Resoluciones findResoluciones(ResolucionesPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Resoluciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Resoluciones> rt = cq.from(Resoluciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
