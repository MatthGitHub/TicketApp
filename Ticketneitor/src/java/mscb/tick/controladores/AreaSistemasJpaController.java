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
import mscb.tick.entidades.AreaSistemas;

/**
 *
 * @author Administrador
 */
public class AreaSistemasJpaController implements Serializable {

    public AreaSistemasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaSistemas areaSistemas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(areaSistemas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaSistemas areaSistemas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            areaSistemas = em.merge(areaSistemas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areaSistemas.getIdAreaSistemas();
                if (findAreaSistemas(id) == null) {
                    throw new NonexistentEntityException("The areaSistemas with id " + id + " no longer exists.");
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
            AreaSistemas areaSistemas;
            try {
                areaSistemas = em.getReference(AreaSistemas.class, id);
                areaSistemas.getIdAreaSistemas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaSistemas with id " + id + " no longer exists.", enfe);
            }
            em.remove(areaSistemas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaSistemas> findAreaSistemasEntities() {
        return findAreaSistemasEntities(true, -1, -1);
    }

    public List<AreaSistemas> findAreaSistemasEntities(int maxResults, int firstResult) {
        return findAreaSistemasEntities(false, maxResults, firstResult);
    }

    private List<AreaSistemas> findAreaSistemasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaSistemas.class));
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

    public AreaSistemas findAreaSistemas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaSistemas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaSistemasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaSistemas> rt = cq.from(AreaSistemas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
