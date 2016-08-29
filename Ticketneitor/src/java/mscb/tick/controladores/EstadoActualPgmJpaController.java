/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.controladores;

import mscb.tick.entidades.EstadoActualPgm;
import mscb.tick.entidades.EstadosPgm;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.controladores.exceptions.PreexistingEntityException;

/**
 *
 * @author Administrador
 */
public class EstadoActualPgmJpaController implements Serializable {

    public EstadoActualPgmJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadoActualPgm estadoActualPgm) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadosPgm fkEstadoPgm = estadoActualPgm.getFkEstadoPgm();
            if (fkEstadoPgm != null) {
                fkEstadoPgm = em.getReference(fkEstadoPgm.getClass(), fkEstadoPgm.getIdEstado());
                estadoActualPgm.setFkEstadoPgm(fkEstadoPgm);
            }
            em.persist(estadoActualPgm);
            if (fkEstadoPgm != null) {
                fkEstadoPgm.getEstadoActualPgmList().add(estadoActualPgm);
                fkEstadoPgm = em.merge(fkEstadoPgm);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoActualPgm(estadoActualPgm.getId()) != null) {
                throw new PreexistingEntityException("EstadoActualPgm " + estadoActualPgm + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoActualPgm estadoActualPgm) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadoActualPgm persistentEstadoActualPgm = em.find(EstadoActualPgm.class, estadoActualPgm.getId());
            EstadosPgm fkEstadoPgmOld = persistentEstadoActualPgm.getFkEstadoPgm();
            EstadosPgm fkEstadoPgmNew = estadoActualPgm.getFkEstadoPgm();
            if (fkEstadoPgmNew != null) {
                fkEstadoPgmNew = em.getReference(fkEstadoPgmNew.getClass(), fkEstadoPgmNew.getIdEstado());
                estadoActualPgm.setFkEstadoPgm(fkEstadoPgmNew);
            }
            estadoActualPgm = em.merge(estadoActualPgm);
            if (fkEstadoPgmOld != null && !fkEstadoPgmOld.equals(fkEstadoPgmNew)) {
                fkEstadoPgmOld.getEstadoActualPgmList().remove(estadoActualPgm);
                fkEstadoPgmOld = em.merge(fkEstadoPgmOld);
            }
            if (fkEstadoPgmNew != null && !fkEstadoPgmNew.equals(fkEstadoPgmOld)) {
                fkEstadoPgmNew.getEstadoActualPgmList().add(estadoActualPgm);
                fkEstadoPgmNew = em.merge(fkEstadoPgmNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoActualPgm.getId();
                if (findEstadoActualPgm(id) == null) {
                    throw new NonexistentEntityException("The estadoActualPgm with id " + id + " no longer exists.");
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
            EstadoActualPgm estadoActualPgm;
            try {
                estadoActualPgm = em.getReference(EstadoActualPgm.class, id);
                estadoActualPgm.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoActualPgm with id " + id + " no longer exists.", enfe);
            }
            EstadosPgm fkEstadoPgm = estadoActualPgm.getFkEstadoPgm();
            if (fkEstadoPgm != null) {
                fkEstadoPgm.getEstadoActualPgmList().remove(estadoActualPgm);
                fkEstadoPgm = em.merge(fkEstadoPgm);
            }
            em.remove(estadoActualPgm);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoActualPgm> findEstadoActualPgmEntities() {
        return findEstadoActualPgmEntities(true, -1, -1);
    }

    public List<EstadoActualPgm> findEstadoActualPgmEntities(int maxResults, int firstResult) {
        return findEstadoActualPgmEntities(false, maxResults, firstResult);
    }

    private List<EstadoActualPgm> findEstadoActualPgmEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoActualPgm.class));
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

    public EstadoActualPgm findEstadoActualPgm(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoActualPgm.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoActualPgmCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoActualPgm> rt = cq.from(EstadoActualPgm.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
