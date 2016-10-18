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
import mscb.tick.entidades.BaseConocimiento;
import mscb.tick.entidades.Tickets;

/**
 *
 * @author Administrador
 */
public class BaseConocimientoJpaController implements Serializable {

    public BaseConocimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BaseConocimiento baseConocimiento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets fkTicket = baseConocimiento.getFkTicket();
            if (fkTicket != null) {
                fkTicket = em.getReference(fkTicket.getClass(), fkTicket.getIdTicket());
                baseConocimiento.setFkTicket(fkTicket);
            }
            em.persist(baseConocimiento);
            if (fkTicket != null) {
                fkTicket.getBaseConocimientoList().add(baseConocimiento);
                fkTicket = em.merge(fkTicket);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BaseConocimiento baseConocimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BaseConocimiento persistentBaseConocimiento = em.find(BaseConocimiento.class, baseConocimiento.getIdResolucion());
            Tickets fkTicketOld = persistentBaseConocimiento.getFkTicket();
            Tickets fkTicketNew = baseConocimiento.getFkTicket();
            if (fkTicketNew != null) {
                fkTicketNew = em.getReference(fkTicketNew.getClass(), fkTicketNew.getIdTicket());
                baseConocimiento.setFkTicket(fkTicketNew);
            }
            baseConocimiento = em.merge(baseConocimiento);
            if (fkTicketOld != null && !fkTicketOld.equals(fkTicketNew)) {
                fkTicketOld.getBaseConocimientoList().remove(baseConocimiento);
                fkTicketOld = em.merge(fkTicketOld);
            }
            if (fkTicketNew != null && !fkTicketNew.equals(fkTicketOld)) {
                fkTicketNew.getBaseConocimientoList().add(baseConocimiento);
                fkTicketNew = em.merge(fkTicketNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = baseConocimiento.getIdResolucion();
                if (findBaseConocimiento(id) == null) {
                    throw new NonexistentEntityException("The baseConocimiento with id " + id + " no longer exists.");
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
            BaseConocimiento baseConocimiento;
            try {
                baseConocimiento = em.getReference(BaseConocimiento.class, id);
                baseConocimiento.getIdResolucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The baseConocimiento with id " + id + " no longer exists.", enfe);
            }
            Tickets fkTicket = baseConocimiento.getFkTicket();
            if (fkTicket != null) {
                fkTicket.getBaseConocimientoList().remove(baseConocimiento);
                fkTicket = em.merge(fkTicket);
            }
            em.remove(baseConocimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BaseConocimiento> findBaseConocimientoEntities() {
        return findBaseConocimientoEntities(true, -1, -1);
    }

    public List<BaseConocimiento> findBaseConocimientoEntities(int maxResults, int firstResult) {
        return findBaseConocimientoEntities(false, maxResults, firstResult);
    }

    private List<BaseConocimiento> findBaseConocimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BaseConocimiento.class));
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

    public BaseConocimiento findBaseConocimiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BaseConocimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getBaseConocimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BaseConocimiento> rt = cq.from(BaseConocimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
