/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.areas.vista;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.areas.vista.exceptions.IllegalOrphanException;
import mscb.tick.areas.vista.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Empleados;

/**
 *
 * @author Administrador
 */
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) {
        if (empleados.getUsuariosList() == null) {
            empleados.setUsuariosList(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areas fkArea = empleados.getFkArea();
            if (fkArea != null) {
                fkArea = em.getReference(fkArea.getClass(), fkArea.getIdArea());
                empleados.setFkArea(fkArea);
            }
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : empleados.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getIdUsuario());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            empleados.setUsuariosList(attachedUsuariosList);
            em.persist(empleados);
            if (fkArea != null) {
                fkArea.getEmpleadosList().add(empleados);
                fkArea = em.merge(fkArea);
            }
            for (Usuarios usuariosListUsuarios : empleados.getUsuariosList()) {
                Empleados oldFkEmpleadoOfUsuariosListUsuarios = usuariosListUsuarios.getFkEmpleado();
                usuariosListUsuarios.setFkEmpleado(empleados);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
                if (oldFkEmpleadoOfUsuariosListUsuarios != null) {
                    oldFkEmpleadoOfUsuariosListUsuarios.getUsuariosList().remove(usuariosListUsuarios);
                    oldFkEmpleadoOfUsuariosListUsuarios = em.merge(oldFkEmpleadoOfUsuariosListUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getIdEmpleado());
            Areas fkAreaOld = persistentEmpleados.getFkArea();
            Areas fkAreaNew = empleados.getFkArea();
            List<Usuarios> usuariosListOld = persistentEmpleados.getUsuariosList();
            List<Usuarios> usuariosListNew = empleados.getUsuariosList();
            List<String> illegalOrphanMessages = null;
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarios " + usuariosListOldUsuarios + " since its fkEmpleado field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkAreaNew != null) {
                fkAreaNew = em.getReference(fkAreaNew.getClass(), fkAreaNew.getIdArea());
                empleados.setFkArea(fkAreaNew);
            }
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getIdUsuario());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            empleados.setUsuariosList(usuariosListNew);
            empleados = em.merge(empleados);
            if (fkAreaOld != null && !fkAreaOld.equals(fkAreaNew)) {
                fkAreaOld.getEmpleadosList().remove(empleados);
                fkAreaOld = em.merge(fkAreaOld);
            }
            if (fkAreaNew != null && !fkAreaNew.equals(fkAreaOld)) {
                fkAreaNew.getEmpleadosList().add(empleados);
                fkAreaNew = em.merge(fkAreaNew);
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    Empleados oldFkEmpleadoOfUsuariosListNewUsuarios = usuariosListNewUsuarios.getFkEmpleado();
                    usuariosListNewUsuarios.setFkEmpleado(empleados);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                    if (oldFkEmpleadoOfUsuariosListNewUsuarios != null && !oldFkEmpleadoOfUsuariosListNewUsuarios.equals(empleados)) {
                        oldFkEmpleadoOfUsuariosListNewUsuarios.getUsuariosList().remove(usuariosListNewUsuarios);
                        oldFkEmpleadoOfUsuariosListNewUsuarios = em.merge(oldFkEmpleadoOfUsuariosListNewUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleados.getIdEmpleado();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
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
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuarios> usuariosListOrphanCheck = empleados.getUsuariosList();
            for (Usuarios usuariosListOrphanCheckUsuarios : usuariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleados (" + empleados + ") cannot be destroyed since the Usuarios " + usuariosListOrphanCheckUsuarios + " in its usuariosList field has a non-nullable fkEmpleado field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Areas fkArea = empleados.getFkArea();
            if (fkArea != null) {
                fkArea.getEmpleadosList().remove(empleados);
                fkArea = em.merge(fkArea);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
