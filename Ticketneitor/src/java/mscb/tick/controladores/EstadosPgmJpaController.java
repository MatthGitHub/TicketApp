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
import mscb.tick.entidades.EstadoActualPgm;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.EstadosPgm;

/**
 *
 * @author Administrador
 */
public class EstadosPgmJpaController implements Serializable {

    public EstadosPgmJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstadosPgm estadosPgm) {
        if (estadosPgm.getEstadoActualPgmList() == null) {
            estadosPgm.setEstadoActualPgmList(new ArrayList<EstadoActualPgm>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<EstadoActualPgm> attachedEstadoActualPgmList = new ArrayList<EstadoActualPgm>();
            for (EstadoActualPgm estadoActualPgmListEstadoActualPgmToAttach : estadosPgm.getEstadoActualPgmList()) {
                estadoActualPgmListEstadoActualPgmToAttach = em.getReference(estadoActualPgmListEstadoActualPgmToAttach.getClass(), estadoActualPgmListEstadoActualPgmToAttach.getId());
                attachedEstadoActualPgmList.add(estadoActualPgmListEstadoActualPgmToAttach);
            }
            estadosPgm.setEstadoActualPgmList(attachedEstadoActualPgmList);
            em.persist(estadosPgm);
            for (EstadoActualPgm estadoActualPgmListEstadoActualPgm : estadosPgm.getEstadoActualPgmList()) {
                EstadosPgm oldFkEstadoPgmOfEstadoActualPgmListEstadoActualPgm = estadoActualPgmListEstadoActualPgm.getFkEstadoPgm();
                estadoActualPgmListEstadoActualPgm.setFkEstadoPgm(estadosPgm);
                estadoActualPgmListEstadoActualPgm = em.merge(estadoActualPgmListEstadoActualPgm);
                if (oldFkEstadoPgmOfEstadoActualPgmListEstadoActualPgm != null) {
                    oldFkEstadoPgmOfEstadoActualPgmListEstadoActualPgm.getEstadoActualPgmList().remove(estadoActualPgmListEstadoActualPgm);
                    oldFkEstadoPgmOfEstadoActualPgmListEstadoActualPgm = em.merge(oldFkEstadoPgmOfEstadoActualPgmListEstadoActualPgm);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadosPgm estadosPgm) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstadosPgm persistentEstadosPgm = em.find(EstadosPgm.class, estadosPgm.getIdEstado());
            List<EstadoActualPgm> estadoActualPgmListOld = persistentEstadosPgm.getEstadoActualPgmList();
            List<EstadoActualPgm> estadoActualPgmListNew = estadosPgm.getEstadoActualPgmList();
            List<String> illegalOrphanMessages = null;
            for (EstadoActualPgm estadoActualPgmListOldEstadoActualPgm : estadoActualPgmListOld) {
                if (!estadoActualPgmListNew.contains(estadoActualPgmListOldEstadoActualPgm)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EstadoActualPgm " + estadoActualPgmListOldEstadoActualPgm + " since its fkEstadoPgm field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<EstadoActualPgm> attachedEstadoActualPgmListNew = new ArrayList<EstadoActualPgm>();
            for (EstadoActualPgm estadoActualPgmListNewEstadoActualPgmToAttach : estadoActualPgmListNew) {
                estadoActualPgmListNewEstadoActualPgmToAttach = em.getReference(estadoActualPgmListNewEstadoActualPgmToAttach.getClass(), estadoActualPgmListNewEstadoActualPgmToAttach.getId());
                attachedEstadoActualPgmListNew.add(estadoActualPgmListNewEstadoActualPgmToAttach);
            }
            estadoActualPgmListNew = attachedEstadoActualPgmListNew;
            estadosPgm.setEstadoActualPgmList(estadoActualPgmListNew);
            estadosPgm = em.merge(estadosPgm);
            for (EstadoActualPgm estadoActualPgmListNewEstadoActualPgm : estadoActualPgmListNew) {
                if (!estadoActualPgmListOld.contains(estadoActualPgmListNewEstadoActualPgm)) {
                    EstadosPgm oldFkEstadoPgmOfEstadoActualPgmListNewEstadoActualPgm = estadoActualPgmListNewEstadoActualPgm.getFkEstadoPgm();
                    estadoActualPgmListNewEstadoActualPgm.setFkEstadoPgm(estadosPgm);
                    estadoActualPgmListNewEstadoActualPgm = em.merge(estadoActualPgmListNewEstadoActualPgm);
                    if (oldFkEstadoPgmOfEstadoActualPgmListNewEstadoActualPgm != null && !oldFkEstadoPgmOfEstadoActualPgmListNewEstadoActualPgm.equals(estadosPgm)) {
                        oldFkEstadoPgmOfEstadoActualPgmListNewEstadoActualPgm.getEstadoActualPgmList().remove(estadoActualPgmListNewEstadoActualPgm);
                        oldFkEstadoPgmOfEstadoActualPgmListNewEstadoActualPgm = em.merge(oldFkEstadoPgmOfEstadoActualPgmListNewEstadoActualPgm);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadosPgm.getIdEstado();
                if (findEstadosPgm(id) == null) {
                    throw new NonexistentEntityException("The estadosPgm with id " + id + " no longer exists.");
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
            EstadosPgm estadosPgm;
            try {
                estadosPgm = em.getReference(EstadosPgm.class, id);
                estadosPgm.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadosPgm with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<EstadoActualPgm> estadoActualPgmListOrphanCheck = estadosPgm.getEstadoActualPgmList();
            for (EstadoActualPgm estadoActualPgmListOrphanCheckEstadoActualPgm : estadoActualPgmListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstadosPgm (" + estadosPgm + ") cannot be destroyed since the EstadoActualPgm " + estadoActualPgmListOrphanCheckEstadoActualPgm + " in its estadoActualPgmList field has a non-nullable fkEstadoPgm field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadosPgm);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadosPgm> findEstadosPgmEntities() {
        return findEstadosPgmEntities(true, -1, -1);
    }

    public List<EstadosPgm> findEstadosPgmEntities(int maxResults, int firstResult) {
        return findEstadosPgmEntities(false, maxResults, firstResult);
    }

    private List<EstadosPgm> findEstadosPgmEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadosPgm.class));
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

    public EstadosPgm findEstadosPgm(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadosPgm.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadosPgmCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadosPgm> rt = cq.from(EstadosPgm.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
