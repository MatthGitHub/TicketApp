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
import mscb.tick.negocio.entidades.Resoluciones;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.controladores.exceptions.PreexistingEntityException;
import mscb.tick.negocio.entidades.ResolucionesProyecto;
import mscb.tick.negocio.entidades.ResolucionesTipos;

/**
 *
 * @author Administrador
 */
public class ResolucionesTiposJpaController implements Serializable {

    public ResolucionesTiposJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ResolucionesTipos resolucionesTipos) throws PreexistingEntityException, Exception {
        if (resolucionesTipos.getResolucionesList() == null) {
            resolucionesTipos.setResolucionesList(new ArrayList<Resoluciones>());
        }
        if (resolucionesTipos.getResolucionesProyectoList() == null) {
            resolucionesTipos.setResolucionesProyectoList(new ArrayList<ResolucionesProyecto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Resoluciones> attachedResolucionesList = new ArrayList<Resoluciones>();
            for (Resoluciones resolucionesListResolucionesToAttach : resolucionesTipos.getResolucionesList()) {
                resolucionesListResolucionesToAttach = em.getReference(resolucionesListResolucionesToAttach.getClass(), resolucionesListResolucionesToAttach.getResolucionesPK());
                attachedResolucionesList.add(resolucionesListResolucionesToAttach);
            }
            resolucionesTipos.setResolucionesList(attachedResolucionesList);
            List<ResolucionesProyecto> attachedResolucionesProyectoList = new ArrayList<ResolucionesProyecto>();
            for (ResolucionesProyecto resolucionesProyectoListResolucionesProyectoToAttach : resolucionesTipos.getResolucionesProyectoList()) {
                resolucionesProyectoListResolucionesProyectoToAttach = em.getReference(resolucionesProyectoListResolucionesProyectoToAttach.getClass(), resolucionesProyectoListResolucionesProyectoToAttach.getResolucionesProyectoPK());
                attachedResolucionesProyectoList.add(resolucionesProyectoListResolucionesProyectoToAttach);
            }
            resolucionesTipos.setResolucionesProyectoList(attachedResolucionesProyectoList);
            em.persist(resolucionesTipos);
            for (Resoluciones resolucionesListResoluciones : resolucionesTipos.getResolucionesList()) {
                ResolucionesTipos oldIdtiporesolucionOfResolucionesListResoluciones = resolucionesListResoluciones.getIdtiporesolucion();
                resolucionesListResoluciones.setIdtiporesolucion(resolucionesTipos);
                resolucionesListResoluciones = em.merge(resolucionesListResoluciones);
                if (oldIdtiporesolucionOfResolucionesListResoluciones != null) {
                    oldIdtiporesolucionOfResolucionesListResoluciones.getResolucionesList().remove(resolucionesListResoluciones);
                    oldIdtiporesolucionOfResolucionesListResoluciones = em.merge(oldIdtiporesolucionOfResolucionesListResoluciones);
                }
            }
            for (ResolucionesProyecto resolucionesProyectoListResolucionesProyecto : resolucionesTipos.getResolucionesProyectoList()) {
                ResolucionesTipos oldIdtiporesolucionOfResolucionesProyectoListResolucionesProyecto = resolucionesProyectoListResolucionesProyecto.getIdtiporesolucion();
                resolucionesProyectoListResolucionesProyecto.setIdtiporesolucion(resolucionesTipos);
                resolucionesProyectoListResolucionesProyecto = em.merge(resolucionesProyectoListResolucionesProyecto);
                if (oldIdtiporesolucionOfResolucionesProyectoListResolucionesProyecto != null) {
                    oldIdtiporesolucionOfResolucionesProyectoListResolucionesProyecto.getResolucionesProyectoList().remove(resolucionesProyectoListResolucionesProyecto);
                    oldIdtiporesolucionOfResolucionesProyectoListResolucionesProyecto = em.merge(oldIdtiporesolucionOfResolucionesProyectoListResolucionesProyecto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findResolucionesTipos(resolucionesTipos.getIdtiporesolucion()) != null) {
                throw new PreexistingEntityException("ResolucionesTipos " + resolucionesTipos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ResolucionesTipos resolucionesTipos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ResolucionesTipos persistentResolucionesTipos = em.find(ResolucionesTipos.class, resolucionesTipos.getIdtiporesolucion());
            List<Resoluciones> resolucionesListOld = persistentResolucionesTipos.getResolucionesList();
            List<Resoluciones> resolucionesListNew = resolucionesTipos.getResolucionesList();
            List<ResolucionesProyecto> resolucionesProyectoListOld = persistentResolucionesTipos.getResolucionesProyectoList();
            List<ResolucionesProyecto> resolucionesProyectoListNew = resolucionesTipos.getResolucionesProyectoList();
            List<Resoluciones> attachedResolucionesListNew = new ArrayList<Resoluciones>();
            for (Resoluciones resolucionesListNewResolucionesToAttach : resolucionesListNew) {
                resolucionesListNewResolucionesToAttach = em.getReference(resolucionesListNewResolucionesToAttach.getClass(), resolucionesListNewResolucionesToAttach.getResolucionesPK());
                attachedResolucionesListNew.add(resolucionesListNewResolucionesToAttach);
            }
            resolucionesListNew = attachedResolucionesListNew;
            resolucionesTipos.setResolucionesList(resolucionesListNew);
            List<ResolucionesProyecto> attachedResolucionesProyectoListNew = new ArrayList<ResolucionesProyecto>();
            for (ResolucionesProyecto resolucionesProyectoListNewResolucionesProyectoToAttach : resolucionesProyectoListNew) {
                resolucionesProyectoListNewResolucionesProyectoToAttach = em.getReference(resolucionesProyectoListNewResolucionesProyectoToAttach.getClass(), resolucionesProyectoListNewResolucionesProyectoToAttach.getResolucionesProyectoPK());
                attachedResolucionesProyectoListNew.add(resolucionesProyectoListNewResolucionesProyectoToAttach);
            }
            resolucionesProyectoListNew = attachedResolucionesProyectoListNew;
            resolucionesTipos.setResolucionesProyectoList(resolucionesProyectoListNew);
            resolucionesTipos = em.merge(resolucionesTipos);
            for (Resoluciones resolucionesListOldResoluciones : resolucionesListOld) {
                if (!resolucionesListNew.contains(resolucionesListOldResoluciones)) {
                    resolucionesListOldResoluciones.setIdtiporesolucion(null);
                    resolucionesListOldResoluciones = em.merge(resolucionesListOldResoluciones);
                }
            }
            for (Resoluciones resolucionesListNewResoluciones : resolucionesListNew) {
                if (!resolucionesListOld.contains(resolucionesListNewResoluciones)) {
                    ResolucionesTipos oldIdtiporesolucionOfResolucionesListNewResoluciones = resolucionesListNewResoluciones.getIdtiporesolucion();
                    resolucionesListNewResoluciones.setIdtiporesolucion(resolucionesTipos);
                    resolucionesListNewResoluciones = em.merge(resolucionesListNewResoluciones);
                    if (oldIdtiporesolucionOfResolucionesListNewResoluciones != null && !oldIdtiporesolucionOfResolucionesListNewResoluciones.equals(resolucionesTipos)) {
                        oldIdtiporesolucionOfResolucionesListNewResoluciones.getResolucionesList().remove(resolucionesListNewResoluciones);
                        oldIdtiporesolucionOfResolucionesListNewResoluciones = em.merge(oldIdtiporesolucionOfResolucionesListNewResoluciones);
                    }
                }
            }
            for (ResolucionesProyecto resolucionesProyectoListOldResolucionesProyecto : resolucionesProyectoListOld) {
                if (!resolucionesProyectoListNew.contains(resolucionesProyectoListOldResolucionesProyecto)) {
                    resolucionesProyectoListOldResolucionesProyecto.setIdtiporesolucion(null);
                    resolucionesProyectoListOldResolucionesProyecto = em.merge(resolucionesProyectoListOldResolucionesProyecto);
                }
            }
            for (ResolucionesProyecto resolucionesProyectoListNewResolucionesProyecto : resolucionesProyectoListNew) {
                if (!resolucionesProyectoListOld.contains(resolucionesProyectoListNewResolucionesProyecto)) {
                    ResolucionesTipos oldIdtiporesolucionOfResolucionesProyectoListNewResolucionesProyecto = resolucionesProyectoListNewResolucionesProyecto.getIdtiporesolucion();
                    resolucionesProyectoListNewResolucionesProyecto.setIdtiporesolucion(resolucionesTipos);
                    resolucionesProyectoListNewResolucionesProyecto = em.merge(resolucionesProyectoListNewResolucionesProyecto);
                    if (oldIdtiporesolucionOfResolucionesProyectoListNewResolucionesProyecto != null && !oldIdtiporesolucionOfResolucionesProyectoListNewResolucionesProyecto.equals(resolucionesTipos)) {
                        oldIdtiporesolucionOfResolucionesProyectoListNewResolucionesProyecto.getResolucionesProyectoList().remove(resolucionesProyectoListNewResolucionesProyecto);
                        oldIdtiporesolucionOfResolucionesProyectoListNewResolucionesProyecto = em.merge(oldIdtiporesolucionOfResolucionesProyectoListNewResolucionesProyecto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = resolucionesTipos.getIdtiporesolucion();
                if (findResolucionesTipos(id) == null) {
                    throw new NonexistentEntityException("The resolucionesTipos with id " + id + " no longer exists.");
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
            ResolucionesTipos resolucionesTipos;
            try {
                resolucionesTipos = em.getReference(ResolucionesTipos.class, id);
                resolucionesTipos.getIdtiporesolucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The resolucionesTipos with id " + id + " no longer exists.", enfe);
            }
            List<Resoluciones> resolucionesList = resolucionesTipos.getResolucionesList();
            for (Resoluciones resolucionesListResoluciones : resolucionesList) {
                resolucionesListResoluciones.setIdtiporesolucion(null);
                resolucionesListResoluciones = em.merge(resolucionesListResoluciones);
            }
            List<ResolucionesProyecto> resolucionesProyectoList = resolucionesTipos.getResolucionesProyectoList();
            for (ResolucionesProyecto resolucionesProyectoListResolucionesProyecto : resolucionesProyectoList) {
                resolucionesProyectoListResolucionesProyecto.setIdtiporesolucion(null);
                resolucionesProyectoListResolucionesProyecto = em.merge(resolucionesProyectoListResolucionesProyecto);
            }
            em.remove(resolucionesTipos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ResolucionesTipos> findResolucionesTiposEntities() {
        return findResolucionesTiposEntities(true, -1, -1);
    }

    public List<ResolucionesTipos> findResolucionesTiposEntities(int maxResults, int firstResult) {
        return findResolucionesTiposEntities(false, maxResults, firstResult);
    }

    private List<ResolucionesTipos> findResolucionesTiposEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ResolucionesTipos.class));
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

    public ResolucionesTipos findResolucionesTipos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ResolucionesTipos.class, id);
        } finally {
            em.close();
        }
    }

    public int getResolucionesTiposCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ResolucionesTipos> rt = cq.from(ResolucionesTipos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
