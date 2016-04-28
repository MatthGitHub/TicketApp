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
import mscb.tick.entidades.Permisos;
import mscb.tick.entidades.Empleado;
import mscb.tick.entidades.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Usuario;

/**
 *
 * @author Administrador
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getTicketCollection() == null) {
            usuario.setTicketCollection(new ArrayList<Ticket>());
        }
        if (usuario.getTicketCollectionUsuarioEmisor() == null) {
            usuario.setTicketCollectionUsuarioEmisor(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Permisos fkPermiso = usuario.getFkPermiso();
            if (fkPermiso != null) {
                fkPermiso = em.getReference(fkPermiso.getClass(), fkPermiso.getIdPermiso());
                usuario.setFkPermiso(fkPermiso);
            }
            Empleado fkEmpleado = usuario.getFkEmpleado();
            if (fkEmpleado != null) {
                fkEmpleado = em.getReference(fkEmpleado.getClass(), fkEmpleado.getIdEmpleado());
                usuario.setFkEmpleado(fkEmpleado);
            }
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : usuario.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            usuario.setTicketCollection(attachedTicketCollection);
            Collection<Ticket> attachedTicketCollection1 = new ArrayList<Ticket>();
            for (Ticket ticketCollection1TicketToAttach : usuario.getTicketCollectionUsuarioEmisor()) {
                ticketCollection1TicketToAttach = em.getReference(ticketCollection1TicketToAttach.getClass(), ticketCollection1TicketToAttach.getIdTicket());
                attachedTicketCollection1.add(ticketCollection1TicketToAttach);
            }
            usuario.setTicketCollectionUsuarioEmisor(attachedTicketCollection1);
            em.persist(usuario);
            if (fkPermiso != null) {
                fkPermiso.getUsuarioCollection().add(usuario);
                fkPermiso = em.merge(fkPermiso);
            }
            if (fkEmpleado != null) {
                fkEmpleado.getUsuarioCollection().add(usuario);
                fkEmpleado = em.merge(fkEmpleado);
            }
            for (Ticket ticketCollectionTicket : usuario.getTicketCollection()) {
                Usuario oldUsuarioReceptorOfTicketCollectionTicket = ticketCollectionTicket.getUsuarioReceptor();
                ticketCollectionTicket.setUsuarioReceptor(usuario);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldUsuarioReceptorOfTicketCollectionTicket != null) {
                    oldUsuarioReceptorOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldUsuarioReceptorOfTicketCollectionTicket = em.merge(oldUsuarioReceptorOfTicketCollectionTicket);
                }
            }
            for (Ticket ticketCollection1Ticket : usuario.getTicketCollectionUsuarioEmisor()) {
                Usuario oldFkUsuarioEmisorOfTicketCollection1Ticket = ticketCollection1Ticket.getFkUsuarioEmisor();
                ticketCollection1Ticket.setFkUsuarioEmisor(usuario);
                ticketCollection1Ticket = em.merge(ticketCollection1Ticket);
                if (oldFkUsuarioEmisorOfTicketCollection1Ticket != null) {
                    oldFkUsuarioEmisorOfTicketCollection1Ticket.getTicketCollectionUsuarioEmisor().remove(ticketCollection1Ticket);
                    oldFkUsuarioEmisorOfTicketCollection1Ticket = em.merge(oldFkUsuarioEmisorOfTicketCollection1Ticket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Permisos fkPermisoOld = persistentUsuario.getFkPermiso();
            Permisos fkPermisoNew = usuario.getFkPermiso();
            Empleado fkEmpleadoOld = persistentUsuario.getFkEmpleado();
            Empleado fkEmpleadoNew = usuario.getFkEmpleado();
            Collection<Ticket> ticketCollectionOld = persistentUsuario.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = usuario.getTicketCollection();
            Collection<Ticket> ticketCollection1Old = persistentUsuario.getTicketCollectionUsuarioEmisor();
            Collection<Ticket> ticketCollection1New = usuario.getTicketCollectionUsuarioEmisor();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its usuarioReceptor field is not nullable.");
                }
            }
            for (Ticket ticketCollection1OldTicket : ticketCollection1Old) {
                if (!ticketCollection1New.contains(ticketCollection1OldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollection1OldTicket + " since its fkUsuarioEmisor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkPermisoNew != null) {
                fkPermisoNew = em.getReference(fkPermisoNew.getClass(), fkPermisoNew.getIdPermiso());
                usuario.setFkPermiso(fkPermisoNew);
            }
            if (fkEmpleadoNew != null) {
                fkEmpleadoNew = em.getReference(fkEmpleadoNew.getClass(), fkEmpleadoNew.getIdEmpleado());
                usuario.setFkEmpleado(fkEmpleadoNew);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            usuario.setTicketCollection(ticketCollectionNew);
            Collection<Ticket> attachedTicketCollection1New = new ArrayList<Ticket>();
            for (Ticket ticketCollection1NewTicketToAttach : ticketCollection1New) {
                ticketCollection1NewTicketToAttach = em.getReference(ticketCollection1NewTicketToAttach.getClass(), ticketCollection1NewTicketToAttach.getIdTicket());
                attachedTicketCollection1New.add(ticketCollection1NewTicketToAttach);
            }
            ticketCollection1New = attachedTicketCollection1New;
            usuario.setTicketCollectionUsuarioEmisor(ticketCollection1New);
            usuario = em.merge(usuario);
            if (fkPermisoOld != null && !fkPermisoOld.equals(fkPermisoNew)) {
                fkPermisoOld.getUsuarioCollection().remove(usuario);
                fkPermisoOld = em.merge(fkPermisoOld);
            }
            if (fkPermisoNew != null && !fkPermisoNew.equals(fkPermisoOld)) {
                fkPermisoNew.getUsuarioCollection().add(usuario);
                fkPermisoNew = em.merge(fkPermisoNew);
            }
            if (fkEmpleadoOld != null && !fkEmpleadoOld.equals(fkEmpleadoNew)) {
                fkEmpleadoOld.getUsuarioCollection().remove(usuario);
                fkEmpleadoOld = em.merge(fkEmpleadoOld);
            }
            if (fkEmpleadoNew != null && !fkEmpleadoNew.equals(fkEmpleadoOld)) {
                fkEmpleadoNew.getUsuarioCollection().add(usuario);
                fkEmpleadoNew = em.merge(fkEmpleadoNew);
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    Usuario oldUsuarioReceptorOfTicketCollectionNewTicket = ticketCollectionNewTicket.getUsuarioReceptor();
                    ticketCollectionNewTicket.setUsuarioReceptor(usuario);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldUsuarioReceptorOfTicketCollectionNewTicket != null && !oldUsuarioReceptorOfTicketCollectionNewTicket.equals(usuario)) {
                        oldUsuarioReceptorOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldUsuarioReceptorOfTicketCollectionNewTicket = em.merge(oldUsuarioReceptorOfTicketCollectionNewTicket);
                    }
                }
            }
            for (Ticket ticketCollection1NewTicket : ticketCollection1New) {
                if (!ticketCollection1Old.contains(ticketCollection1NewTicket)) {
                    Usuario oldFkUsuarioEmisorOfTicketCollection1NewTicket = ticketCollection1NewTicket.getFkUsuarioEmisor();
                    ticketCollection1NewTicket.setFkUsuarioEmisor(usuario);
                    ticketCollection1NewTicket = em.merge(ticketCollection1NewTicket);
                    if (oldFkUsuarioEmisorOfTicketCollection1NewTicket != null && !oldFkUsuarioEmisorOfTicketCollection1NewTicket.equals(usuario)) {
                        oldFkUsuarioEmisorOfTicketCollection1NewTicket.getTicketCollectionUsuarioEmisor().remove(ticketCollection1NewTicket);
                        oldFkUsuarioEmisorOfTicketCollection1NewTicket = em.merge(oldFkUsuarioEmisorOfTicketCollection1NewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = usuario.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable usuarioReceptor field.");
            }
            Collection<Ticket> ticketCollection1OrphanCheck = usuario.getTicketCollectionUsuarioEmisor();
            for (Ticket ticketCollection1OrphanCheckTicket : ticketCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Ticket " + ticketCollection1OrphanCheckTicket + " in its ticketCollection1 field has a non-nullable fkUsuarioEmisor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Permisos fkPermiso = usuario.getFkPermiso();
            if (fkPermiso != null) {
                fkPermiso.getUsuarioCollection().remove(usuario);
                fkPermiso = em.merge(fkPermiso);
            }
            Empleado fkEmpleado = usuario.getFkEmpleado();
            if (fkEmpleado != null) {
                fkEmpleado.getUsuarioCollection().remove(usuario);
                fkEmpleado = em.merge(fkEmpleado);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
