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
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Servicios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.IllegalOrphanException;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Asuntos;

/**
 *
 * @author Administrador
 */
public class AsuntosJpaController implements Serializable {

    public AsuntosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asuntos asuntos) {
        if (asuntos.getServiciosList() == null) {
            asuntos.setServiciosList(new ArrayList<Servicios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areas fkArea = asuntos.getFkArea();
            if (fkArea != null) {
                fkArea = em.getReference(fkArea.getClass(), fkArea.getIdArea());
                asuntos.setFkArea(fkArea);
            }
            List<Servicios> attachedServiciosList = new ArrayList<Servicios>();
            for (Servicios serviciosListServiciosToAttach : asuntos.getServiciosList()) {
                serviciosListServiciosToAttach = em.getReference(serviciosListServiciosToAttach.getClass(), serviciosListServiciosToAttach.getIdasuntoS());
                attachedServiciosList.add(serviciosListServiciosToAttach);
            }
            asuntos.setServiciosList(attachedServiciosList);
            em.persist(asuntos);
            if (fkArea != null) {
                fkArea.getAsuntosList().add(asuntos);
                fkArea = em.merge(fkArea);
            }
            for (Servicios serviciosListServicios : asuntos.getServiciosList()) {
                Asuntos oldPerteneceOfServiciosListServicios = serviciosListServicios.getPertenece();
                serviciosListServicios.setPertenece(asuntos);
                serviciosListServicios = em.merge(serviciosListServicios);
                if (oldPerteneceOfServiciosListServicios != null) {
                    oldPerteneceOfServiciosListServicios.getServiciosList().remove(serviciosListServicios);
                    oldPerteneceOfServiciosListServicios = em.merge(oldPerteneceOfServiciosListServicios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asuntos asuntos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asuntos persistentAsuntos = em.find(Asuntos.class, asuntos.getIdasuntoP());
            Areas fkAreaOld = persistentAsuntos.getFkArea();
            Areas fkAreaNew = asuntos.getFkArea();
            List<Servicios> serviciosListOld = persistentAsuntos.getServiciosList();
            List<Servicios> serviciosListNew = asuntos.getServiciosList();
            List<String> illegalOrphanMessages = null;
            for (Servicios serviciosListOldServicios : serviciosListOld) {
                if (!serviciosListNew.contains(serviciosListOldServicios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Servicios " + serviciosListOldServicios + " since its pertenece field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkAreaNew != null) {
                fkAreaNew = em.getReference(fkAreaNew.getClass(), fkAreaNew.getIdArea());
                asuntos.setFkArea(fkAreaNew);
            }
            List<Servicios> attachedServiciosListNew = new ArrayList<Servicios>();
            for (Servicios serviciosListNewServiciosToAttach : serviciosListNew) {
                serviciosListNewServiciosToAttach = em.getReference(serviciosListNewServiciosToAttach.getClass(), serviciosListNewServiciosToAttach.getIdasuntoS());
                attachedServiciosListNew.add(serviciosListNewServiciosToAttach);
            }
            serviciosListNew = attachedServiciosListNew;
            asuntos.setServiciosList(serviciosListNew);
            asuntos = em.merge(asuntos);
            if (fkAreaOld != null && !fkAreaOld.equals(fkAreaNew)) {
                fkAreaOld.getAsuntosList().remove(asuntos);
                fkAreaOld = em.merge(fkAreaOld);
            }
            if (fkAreaNew != null && !fkAreaNew.equals(fkAreaOld)) {
                fkAreaNew.getAsuntosList().add(asuntos);
                fkAreaNew = em.merge(fkAreaNew);
            }
            for (Servicios serviciosListNewServicios : serviciosListNew) {
                if (!serviciosListOld.contains(serviciosListNewServicios)) {
                    Asuntos oldPerteneceOfServiciosListNewServicios = serviciosListNewServicios.getPertenece();
                    serviciosListNewServicios.setPertenece(asuntos);
                    serviciosListNewServicios = em.merge(serviciosListNewServicios);
                    if (oldPerteneceOfServiciosListNewServicios != null && !oldPerteneceOfServiciosListNewServicios.equals(asuntos)) {
                        oldPerteneceOfServiciosListNewServicios.getServiciosList().remove(serviciosListNewServicios);
                        oldPerteneceOfServiciosListNewServicios = em.merge(oldPerteneceOfServiciosListNewServicios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asuntos.getIdasuntoP();
                if (findAsuntos(id) == null) {
                    throw new NonexistentEntityException("The asuntos with id " + id + " no longer exists.");
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
            Asuntos asuntos;
            try {
                asuntos = em.getReference(Asuntos.class, id);
                asuntos.getIdasuntoP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asuntos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Servicios> serviciosListOrphanCheck = asuntos.getServiciosList();
            for (Servicios serviciosListOrphanCheckServicios : serviciosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asuntos (" + asuntos + ") cannot be destroyed since the Servicios " + serviciosListOrphanCheckServicios + " in its serviciosList field has a non-nullable pertenece field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Areas fkArea = asuntos.getFkArea();
            if (fkArea != null) {
                fkArea.getAsuntosList().remove(asuntos);
                fkArea = em.merge(fkArea);
            }
            em.remove(asuntos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asuntos> findAsuntosEntities() {
        return findAsuntosEntities(true, -1, -1);
    }

    public List<Asuntos> findAsuntosEntities(int maxResults, int firstResult) {
        return findAsuntosEntities(false, maxResults, firstResult);
    }

    private List<Asuntos> findAsuntosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asuntos.class));
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

    public Asuntos findAsuntos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asuntos.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsuntosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asuntos> rt = cq.from(Asuntos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
