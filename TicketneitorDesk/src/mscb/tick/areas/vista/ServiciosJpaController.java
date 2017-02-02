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
import mscb.tick.entidades.Asuntos;
import mscb.tick.entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.areas.vista.exceptions.IllegalOrphanException;
import mscb.tick.areas.vista.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Servicios;
import mscb.tick.entidades.Tickets;

/**
 *
 * @author Administrador
 */
public class ServiciosJpaController implements Serializable {

    public ServiciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicios servicios) {
        if (servicios.getUsuariosList() == null) {
            servicios.setUsuariosList(new ArrayList<Usuarios>());
        }
        if (servicios.getTicketsList() == null) {
            servicios.setTicketsList(new ArrayList<Tickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asuntos pertenece = servicios.getPertenece();
            if (pertenece != null) {
                pertenece = em.getReference(pertenece.getClass(), pertenece.getIdasuntoP());
                servicios.setPertenece(pertenece);
            }
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : servicios.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getIdUsuario());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            servicios.setUsuariosList(attachedUsuariosList);
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : servicios.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            servicios.setTicketsList(attachedTicketsList);
            em.persist(servicios);
            if (pertenece != null) {
                pertenece.getServiciosList().add(servicios);
                pertenece = em.merge(pertenece);
            }
            for (Usuarios usuariosListUsuarios : servicios.getUsuariosList()) {
                usuariosListUsuarios.getServiciosList().add(servicios);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            for (Tickets ticketsListTickets : servicios.getTicketsList()) {
                Servicios oldAsuntoOfTicketsListTickets = ticketsListTickets.getAsunto();
                ticketsListTickets.setAsunto(servicios);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldAsuntoOfTicketsListTickets != null) {
                    oldAsuntoOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldAsuntoOfTicketsListTickets = em.merge(oldAsuntoOfTicketsListTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicios servicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios persistentServicios = em.find(Servicios.class, servicios.getIdasuntoS());
            Asuntos perteneceOld = persistentServicios.getPertenece();
            Asuntos perteneceNew = servicios.getPertenece();
            List<Usuarios> usuariosListOld = persistentServicios.getUsuariosList();
            List<Usuarios> usuariosListNew = servicios.getUsuariosList();
            List<Tickets> ticketsListOld = persistentServicios.getTicketsList();
            List<Tickets> ticketsListNew = servicios.getTicketsList();
            List<String> illegalOrphanMessages = null;
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tickets " + ticketsListOldTickets + " since its asunto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perteneceNew != null) {
                perteneceNew = em.getReference(perteneceNew.getClass(), perteneceNew.getIdasuntoP());
                servicios.setPertenece(perteneceNew);
            }
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getIdUsuario());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            servicios.setUsuariosList(usuariosListNew);
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            servicios.setTicketsList(ticketsListNew);
            servicios = em.merge(servicios);
            if (perteneceOld != null && !perteneceOld.equals(perteneceNew)) {
                perteneceOld.getServiciosList().remove(servicios);
                perteneceOld = em.merge(perteneceOld);
            }
            if (perteneceNew != null && !perteneceNew.equals(perteneceOld)) {
                perteneceNew.getServiciosList().add(servicios);
                perteneceNew = em.merge(perteneceNew);
            }
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    usuariosListOldUsuarios.getServiciosList().remove(servicios);
                    usuariosListOldUsuarios = em.merge(usuariosListOldUsuarios);
                }
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    usuariosListNewUsuarios.getServiciosList().add(servicios);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Servicios oldAsuntoOfTicketsListNewTickets = ticketsListNewTickets.getAsunto();
                    ticketsListNewTickets.setAsunto(servicios);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldAsuntoOfTicketsListNewTickets != null && !oldAsuntoOfTicketsListNewTickets.equals(servicios)) {
                        oldAsuntoOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldAsuntoOfTicketsListNewTickets = em.merge(oldAsuntoOfTicketsListNewTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicios.getIdasuntoS();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
            Servicios servicios;
            try {
                servicios = em.getReference(Servicios.class, id);
                servicios.getIdasuntoS();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tickets> ticketsListOrphanCheck = servicios.getTicketsList();
            for (Tickets ticketsListOrphanCheckTickets : ticketsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the Tickets " + ticketsListOrphanCheckTickets + " in its ticketsList field has a non-nullable asunto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Asuntos pertenece = servicios.getPertenece();
            if (pertenece != null) {
                pertenece.getServiciosList().remove(servicios);
                pertenece = em.merge(pertenece);
            }
            List<Usuarios> usuariosList = servicios.getUsuariosList();
            for (Usuarios usuariosListUsuarios : usuariosList) {
                usuariosListUsuarios.getServiciosList().remove(servicios);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicios> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicios.class));
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

    public Servicios findServicios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicios> rt = cq.from(Servicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
