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
import mscb.tick.negocio.entidades.ResolucionesProyecto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.controladores.exceptions.PreexistingEntityException;
import mscb.tick.negocio.entidades.InOrganigrama;

/**
 *
 * @author Administrador
 */
public class InOrganigramaJpaController implements Serializable {

    public InOrganigramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(InOrganigrama inOrganigrama) throws PreexistingEntityException, Exception {
        if (inOrganigrama.getResolucionesProyectoList() == null) {
            inOrganigrama.setResolucionesProyectoList(new ArrayList<ResolucionesProyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ResolucionesProyecto> attachedResolucionesProyectoList = new ArrayList<ResolucionesProyecto>();
            for (ResolucionesProyecto resolucionesProyectoListResolucionesProyectoToAttach : inOrganigrama.getResolucionesProyectoList()) {
                resolucionesProyectoListResolucionesProyectoToAttach = em.getReference(resolucionesProyectoListResolucionesProyectoToAttach.getClass(), resolucionesProyectoListResolucionesProyectoToAttach.getResolucionesProyectoPK());
                attachedResolucionesProyectoList.add(resolucionesProyectoListResolucionesProyectoToAttach);
            }
            inOrganigrama.setResolucionesProyectoList(attachedResolucionesProyectoList);
            em.persist(inOrganigrama);
            for (ResolucionesProyecto resolucionesProyectoListResolucionesProyecto : inOrganigrama.getResolucionesProyectoList()) {
                InOrganigrama oldAreageneraOfResolucionesProyectoListResolucionesProyecto = resolucionesProyectoListResolucionesProyecto.getAreagenera();
                resolucionesProyectoListResolucionesProyecto.setAreagenera(inOrganigrama);
                resolucionesProyectoListResolucionesProyecto = em.merge(resolucionesProyectoListResolucionesProyecto);
                if (oldAreageneraOfResolucionesProyectoListResolucionesProyecto != null) {
                    oldAreageneraOfResolucionesProyectoListResolucionesProyecto.getResolucionesProyectoList().remove(resolucionesProyectoListResolucionesProyecto);
                    oldAreageneraOfResolucionesProyectoListResolucionesProyecto = em.merge(oldAreageneraOfResolucionesProyectoListResolucionesProyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findInOrganigrama(inOrganigrama.getCodigoOrganigrama()) != null) {
                throw new PreexistingEntityException("InOrganigrama " + inOrganigrama + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(InOrganigrama inOrganigrama) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InOrganigrama persistentInOrganigrama = em.find(InOrganigrama.class, inOrganigrama.getCodigoOrganigrama());
            List<ResolucionesProyecto> resolucionesProyectoListOld = persistentInOrganigrama.getResolucionesProyectoList();
            List<ResolucionesProyecto> resolucionesProyectoListNew = inOrganigrama.getResolucionesProyectoList();
            List<ResolucionesProyecto> attachedResolucionesProyectoListNew = new ArrayList<ResolucionesProyecto>();
            for (ResolucionesProyecto resolucionesProyectoListNewResolucionesProyectoToAttach : resolucionesProyectoListNew) {
                resolucionesProyectoListNewResolucionesProyectoToAttach = em.getReference(resolucionesProyectoListNewResolucionesProyectoToAttach.getClass(), resolucionesProyectoListNewResolucionesProyectoToAttach.getResolucionesProyectoPK());
                attachedResolucionesProyectoListNew.add(resolucionesProyectoListNewResolucionesProyectoToAttach);
            }
            resolucionesProyectoListNew = attachedResolucionesProyectoListNew;
            inOrganigrama.setResolucionesProyectoList(resolucionesProyectoListNew);
            inOrganigrama = em.merge(inOrganigrama);
            for (ResolucionesProyecto resolucionesProyectoListOldResolucionesProyecto : resolucionesProyectoListOld) {
                if (!resolucionesProyectoListNew.contains(resolucionesProyectoListOldResolucionesProyecto)) {
                    resolucionesProyectoListOldResolucionesProyecto.setAreagenera(null);
                    resolucionesProyectoListOldResolucionesProyecto = em.merge(resolucionesProyectoListOldResolucionesProyecto);
                }
            }
            for (ResolucionesProyecto resolucionesProyectoListNewResolucionesProyecto : resolucionesProyectoListNew) {
                if (!resolucionesProyectoListOld.contains(resolucionesProyectoListNewResolucionesProyecto)) {
                    InOrganigrama oldAreageneraOfResolucionesProyectoListNewResolucionesProyecto = resolucionesProyectoListNewResolucionesProyecto.getAreagenera();
                    resolucionesProyectoListNewResolucionesProyecto.setAreagenera(inOrganigrama);
                    resolucionesProyectoListNewResolucionesProyecto = em.merge(resolucionesProyectoListNewResolucionesProyecto);
                    if (oldAreageneraOfResolucionesProyectoListNewResolucionesProyecto != null && !oldAreageneraOfResolucionesProyectoListNewResolucionesProyecto.equals(inOrganigrama)) {
                        oldAreageneraOfResolucionesProyectoListNewResolucionesProyecto.getResolucionesProyectoList().remove(resolucionesProyectoListNewResolucionesProyecto);
                        oldAreageneraOfResolucionesProyectoListNewResolucionesProyecto = em.merge(oldAreageneraOfResolucionesProyectoListNewResolucionesProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = inOrganigrama.getCodigoOrganigrama();
                if (findInOrganigrama(id) == null) {
                    throw new NonexistentEntityException("The inOrganigrama with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InOrganigrama inOrganigrama;
            try {
                inOrganigrama = em.getReference(InOrganigrama.class, id);
                inOrganigrama.getCodigoOrganigrama();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inOrganigrama with id " + id + " no longer exists.", enfe);
            }
            List<ResolucionesProyecto> resolucionesProyectoList = inOrganigrama.getResolucionesProyectoList();
            for (ResolucionesProyecto resolucionesProyectoListResolucionesProyecto : resolucionesProyectoList) {
                resolucionesProyectoListResolucionesProyecto.setAreagenera(null);
                resolucionesProyectoListResolucionesProyecto = em.merge(resolucionesProyectoListResolucionesProyecto);
            }
            em.remove(inOrganigrama);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<InOrganigrama> findInOrganigramaEntities() {
        return findInOrganigramaEntities(true, -1, -1);
    }

    public List<InOrganigrama> findInOrganigramaEntities(int maxResults, int firstResult) {
        return findInOrganigramaEntities(false, maxResults, firstResult);
    }

    private List<InOrganigrama> findInOrganigramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(InOrganigrama.class));
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

    public InOrganigrama findInOrganigrama(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(InOrganigrama.class, id);
        } finally {
            em.close();
        }
    }

    public int getInOrganigramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<InOrganigrama> rt = cq.from(InOrganigrama.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
