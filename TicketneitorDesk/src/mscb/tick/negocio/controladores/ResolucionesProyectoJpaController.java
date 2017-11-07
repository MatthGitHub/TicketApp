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
import mscb.tick.negocio.entidades.InOrganigrama;
import mscb.tick.negocio.entidades.ResolucionesProyecto;
import mscb.tick.negocio.entidades.ResolucionesProyectoPK;
import mscb.tick.negocio.entidades.ResolucionesTipos;

/**
 *
 * @author Administrador
 */
public class ResolucionesProyectoJpaController implements Serializable {

    public ResolucionesProyectoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResolucionesProyecto resolucionesProyecto) throws PreexistingEntityException, Exception {
        if (resolucionesProyecto.getResolucionesProyectoPK() == null) {
            resolucionesProyecto.setResolucionesProyectoPK(new ResolucionesProyectoPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            InOrganigrama areagenera = resolucionesProyecto.getAreagenera();
            if (areagenera != null) {
                areagenera = em.getReference(areagenera.getClass(), areagenera.getCodigoOrganigrama());
                resolucionesProyecto.setAreagenera(areagenera);
            }
            ResolucionesTipos idtiporesolucion = resolucionesProyecto.getIdtiporesolucion();
            if (idtiporesolucion != null) {
                idtiporesolucion = em.getReference(idtiporesolucion.getClass(), idtiporesolucion.getIdtiporesolucion());
                resolucionesProyecto.setIdtiporesolucion(idtiporesolucion);
            }
            em.persist(resolucionesProyecto);
            if (areagenera != null) {
                areagenera.getResolucionesProyectoList().add(resolucionesProyecto);
                areagenera = em.merge(areagenera);
            }
            if (idtiporesolucion != null) {
                idtiporesolucion.getResolucionesProyectoList().add(resolucionesProyecto);
                idtiporesolucion = em.merge(idtiporesolucion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResolucionesProyecto(resolucionesProyecto.getResolucionesProyectoPK()) != null) {
                throw new PreexistingEntityException("ResolucionesProyecto " + resolucionesProyecto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResolucionesProyecto resolucionesProyecto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResolucionesProyecto persistentResolucionesProyecto = em.find(ResolucionesProyecto.class, resolucionesProyecto.getResolucionesProyectoPK());
            InOrganigrama areageneraOld = persistentResolucionesProyecto.getAreagenera();
            InOrganigrama areageneraNew = resolucionesProyecto.getAreagenera();
            ResolucionesTipos idtiporesolucionOld = persistentResolucionesProyecto.getIdtiporesolucion();
            ResolucionesTipos idtiporesolucionNew = resolucionesProyecto.getIdtiporesolucion();
            if (areageneraNew != null) {
                areageneraNew = em.getReference(areageneraNew.getClass(), areageneraNew.getCodigoOrganigrama());
                resolucionesProyecto.setAreagenera(areageneraNew);
            }
            if (idtiporesolucionNew != null) {
                idtiporesolucionNew = em.getReference(idtiporesolucionNew.getClass(), idtiporesolucionNew.getIdtiporesolucion());
                resolucionesProyecto.setIdtiporesolucion(idtiporesolucionNew);
            }
            resolucionesProyecto = em.merge(resolucionesProyecto);
            if (areageneraOld != null && !areageneraOld.equals(areageneraNew)) {
                areageneraOld.getResolucionesProyectoList().remove(resolucionesProyecto);
                areageneraOld = em.merge(areageneraOld);
            }
            if (areageneraNew != null && !areageneraNew.equals(areageneraOld)) {
                areageneraNew.getResolucionesProyectoList().add(resolucionesProyecto);
                areageneraNew = em.merge(areageneraNew);
            }
            if (idtiporesolucionOld != null && !idtiporesolucionOld.equals(idtiporesolucionNew)) {
                idtiporesolucionOld.getResolucionesProyectoList().remove(resolucionesProyecto);
                idtiporesolucionOld = em.merge(idtiporesolucionOld);
            }
            if (idtiporesolucionNew != null && !idtiporesolucionNew.equals(idtiporesolucionOld)) {
                idtiporesolucionNew.getResolucionesProyectoList().add(resolucionesProyecto);
                idtiporesolucionNew = em.merge(idtiporesolucionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ResolucionesProyectoPK id = resolucionesProyecto.getResolucionesProyectoPK();
                if (findResolucionesProyecto(id) == null) {
                    throw new NonexistentEntityException("The resolucionesProyecto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ResolucionesProyectoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResolucionesProyecto resolucionesProyecto;
            try {
                resolucionesProyecto = em.getReference(ResolucionesProyecto.class, id);
                resolucionesProyecto.getResolucionesProyectoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucionesProyecto with id " + id + " no longer exists.", enfe);
            }
            InOrganigrama areagenera = resolucionesProyecto.getAreagenera();
            if (areagenera != null) {
                areagenera.getResolucionesProyectoList().remove(resolucionesProyecto);
                areagenera = em.merge(areagenera);
            }
            ResolucionesTipos idtiporesolucion = resolucionesProyecto.getIdtiporesolucion();
            if (idtiporesolucion != null) {
                idtiporesolucion.getResolucionesProyectoList().remove(resolucionesProyecto);
                idtiporesolucion = em.merge(idtiporesolucion);
            }
            em.remove(resolucionesProyecto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResolucionesProyecto> findResolucionesProyectoEntities() {
        return findResolucionesProyectoEntities(true, -1, -1);
    }

    public List<ResolucionesProyecto> findResolucionesProyectoEntities(int maxResults, int firstResult) {
        return findResolucionesProyectoEntities(false, maxResults, firstResult);
    }

    private List<ResolucionesProyecto> findResolucionesProyectoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResolucionesProyecto.class));
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

    public ResolucionesProyecto findResolucionesProyecto(ResolucionesProyectoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResolucionesProyecto.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionesProyectoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResolucionesProyecto> rt = cq.from(ResolucionesProyecto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
