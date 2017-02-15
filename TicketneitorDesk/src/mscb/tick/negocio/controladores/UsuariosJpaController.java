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
import mscb.tick.negocio.entidades.Empleados;
import mscb.tick.negocio.entidades.Roles;
import mscb.tick.negocio.entidades.Servicios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.IllegalOrphanException;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Usuarios;

/**
 *
 * @author Administrador
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getServiciosList() == null) {
            usuarios.setServiciosList(new ArrayList<Servicios>());
        }
        if (usuarios.getTicketsList() == null) {
            usuarios.setTicketsList(new ArrayList<Tickets>());
        }
        if (usuarios.getHistorialTicketsList() == null) {
            usuarios.setHistorialTicketsList(new ArrayList<HistorialTickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados fkEmpleado = usuarios.getFkEmpleado();
            if (fkEmpleado != null) {
                fkEmpleado = em.getReference(fkEmpleado.getClass(), fkEmpleado.getIdEmpleado());
                usuarios.setFkEmpleado(fkEmpleado);
            }
            Roles fkRol = usuarios.getFkRol();
            if (fkRol != null) {
                fkRol = em.getReference(fkRol.getClass(), fkRol.getIdRol());
                usuarios.setFkRol(fkRol);
            }
            List<Servicios> attachedServiciosList = new ArrayList<Servicios>();
            for (Servicios serviciosListServiciosToAttach : usuarios.getServiciosList()) {
                serviciosListServiciosToAttach = em.getReference(serviciosListServiciosToAttach.getClass(), serviciosListServiciosToAttach.getIdasuntoS());
                attachedServiciosList.add(serviciosListServiciosToAttach);
            }
            usuarios.setServiciosList(attachedServiciosList);
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : usuarios.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            usuarios.setTicketsList(attachedTicketsList);
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : usuarios.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            usuarios.setHistorialTicketsList(attachedHistorialTicketsList);
            em.persist(usuarios);
            if (fkEmpleado != null) {
                fkEmpleado.getUsuariosList().add(usuarios);
                fkEmpleado = em.merge(fkEmpleado);
            }
            if (fkRol != null) {
                fkRol.getUsuariosList().add(usuarios);
                fkRol = em.merge(fkRol);
            }
            for (Servicios serviciosListServicios : usuarios.getServiciosList()) {
                serviciosListServicios.getUsuariosList().add(usuarios);
                serviciosListServicios = em.merge(serviciosListServicios);
            }
            for (Tickets ticketsListTickets : usuarios.getTicketsList()) {
                Usuarios oldCreadorOfTicketsListTickets = ticketsListTickets.getCreador();
                ticketsListTickets.setCreador(usuarios);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldCreadorOfTicketsListTickets != null) {
                    oldCreadorOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldCreadorOfTicketsListTickets = em.merge(oldCreadorOfTicketsListTickets);
                }
            }
            for (HistorialTickets historialTicketsListHistorialTickets : usuarios.getHistorialTicketsList()) {
                Usuarios oldFkUsuarioOfHistorialTicketsListHistorialTickets = historialTicketsListHistorialTickets.getFkUsuario();
                historialTicketsListHistorialTickets.setFkUsuario(usuarios);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
                if (oldFkUsuarioOfHistorialTicketsListHistorialTickets != null) {
                    oldFkUsuarioOfHistorialTicketsListHistorialTickets.getHistorialTicketsList().remove(historialTicketsListHistorialTickets);
                    oldFkUsuarioOfHistorialTicketsListHistorialTickets = em.merge(oldFkUsuarioOfHistorialTicketsListHistorialTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            Empleados fkEmpleadoOld = persistentUsuarios.getFkEmpleado();
            Empleados fkEmpleadoNew = usuarios.getFkEmpleado();
            Roles fkRolOld = persistentUsuarios.getFkRol();
            Roles fkRolNew = usuarios.getFkRol();
            List<Servicios> serviciosListOld = persistentUsuarios.getServiciosList();
            List<Servicios> serviciosListNew = usuarios.getServiciosList();
            List<Tickets> ticketsListOld = persistentUsuarios.getTicketsList();
            List<Tickets> ticketsListNew = usuarios.getTicketsList();
            List<HistorialTickets> historialTicketsListOld = persistentUsuarios.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = usuarios.getHistorialTicketsList();
            List<String> illegalOrphanMessages = null;
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tickets " + ticketsListOldTickets + " since its creador field is not nullable.");
                }
            }
            for (HistorialTickets historialTicketsListOldHistorialTickets : historialTicketsListOld) {
                if (!historialTicketsListNew.contains(historialTicketsListOldHistorialTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistorialTickets " + historialTicketsListOldHistorialTickets + " since its fkUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkEmpleadoNew != null) {
                fkEmpleadoNew = em.getReference(fkEmpleadoNew.getClass(), fkEmpleadoNew.getIdEmpleado());
                usuarios.setFkEmpleado(fkEmpleadoNew);
            }
            if (fkRolNew != null) {
                fkRolNew = em.getReference(fkRolNew.getClass(), fkRolNew.getIdRol());
                usuarios.setFkRol(fkRolNew);
            }
            List<Servicios> attachedServiciosListNew = new ArrayList<Servicios>();
            for (Servicios serviciosListNewServiciosToAttach : serviciosListNew) {
                serviciosListNewServiciosToAttach = em.getReference(serviciosListNewServiciosToAttach.getClass(), serviciosListNewServiciosToAttach.getIdasuntoS());
                attachedServiciosListNew.add(serviciosListNewServiciosToAttach);
            }
            serviciosListNew = attachedServiciosListNew;
            usuarios.setServiciosList(serviciosListNew);
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            usuarios.setTicketsList(ticketsListNew);
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            usuarios.setHistorialTicketsList(historialTicketsListNew);
            usuarios = em.merge(usuarios);
            if (fkEmpleadoOld != null && !fkEmpleadoOld.equals(fkEmpleadoNew)) {
                fkEmpleadoOld.getUsuariosList().remove(usuarios);
                fkEmpleadoOld = em.merge(fkEmpleadoOld);
            }
            if (fkEmpleadoNew != null && !fkEmpleadoNew.equals(fkEmpleadoOld)) {
                fkEmpleadoNew.getUsuariosList().add(usuarios);
                fkEmpleadoNew = em.merge(fkEmpleadoNew);
            }
            if (fkRolOld != null && !fkRolOld.equals(fkRolNew)) {
                fkRolOld.getUsuariosList().remove(usuarios);
                fkRolOld = em.merge(fkRolOld);
            }
            if (fkRolNew != null && !fkRolNew.equals(fkRolOld)) {
                fkRolNew.getUsuariosList().add(usuarios);
                fkRolNew = em.merge(fkRolNew);
            }
            for (Servicios serviciosListOldServicios : serviciosListOld) {
                if (!serviciosListNew.contains(serviciosListOldServicios)) {
                    serviciosListOldServicios.getUsuariosList().remove(usuarios);
                    serviciosListOldServicios = em.merge(serviciosListOldServicios);
                }
            }
            for (Servicios serviciosListNewServicios : serviciosListNew) {
                if (!serviciosListOld.contains(serviciosListNewServicios)) {
                    serviciosListNewServicios.getUsuariosList().add(usuarios);
                    serviciosListNewServicios = em.merge(serviciosListNewServicios);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Usuarios oldCreadorOfTicketsListNewTickets = ticketsListNewTickets.getCreador();
                    ticketsListNewTickets.setCreador(usuarios);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldCreadorOfTicketsListNewTickets != null && !oldCreadorOfTicketsListNewTickets.equals(usuarios)) {
                        oldCreadorOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldCreadorOfTicketsListNewTickets = em.merge(oldCreadorOfTicketsListNewTickets);
                    }
                }
            }
            for (HistorialTickets historialTicketsListNewHistorialTickets : historialTicketsListNew) {
                if (!historialTicketsListOld.contains(historialTicketsListNewHistorialTickets)) {
                    Usuarios oldFkUsuarioOfHistorialTicketsListNewHistorialTickets = historialTicketsListNewHistorialTickets.getFkUsuario();
                    historialTicketsListNewHistorialTickets.setFkUsuario(usuarios);
                    historialTicketsListNewHistorialTickets = em.merge(historialTicketsListNewHistorialTickets);
                    if (oldFkUsuarioOfHistorialTicketsListNewHistorialTickets != null && !oldFkUsuarioOfHistorialTicketsListNewHistorialTickets.equals(usuarios)) {
                        oldFkUsuarioOfHistorialTicketsListNewHistorialTickets.getHistorialTicketsList().remove(historialTicketsListNewHistorialTickets);
                        oldFkUsuarioOfHistorialTicketsListNewHistorialTickets = em.merge(oldFkUsuarioOfHistorialTicketsListNewHistorialTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tickets> ticketsListOrphanCheck = usuarios.getTicketsList();
            for (Tickets ticketsListOrphanCheckTickets : ticketsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Tickets " + ticketsListOrphanCheckTickets + " in its ticketsList field has a non-nullable creador field.");
            }
            List<HistorialTickets> historialTicketsListOrphanCheck = usuarios.getHistorialTicketsList();
            for (HistorialTickets historialTicketsListOrphanCheckHistorialTickets : historialTicketsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the HistorialTickets " + historialTicketsListOrphanCheckHistorialTickets + " in its historialTicketsList field has a non-nullable fkUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleados fkEmpleado = usuarios.getFkEmpleado();
            if (fkEmpleado != null) {
                fkEmpleado.getUsuariosList().remove(usuarios);
                fkEmpleado = em.merge(fkEmpleado);
            }
            Roles fkRol = usuarios.getFkRol();
            if (fkRol != null) {
                fkRol.getUsuariosList().remove(usuarios);
                fkRol = em.merge(fkRol);
            }
            List<Servicios> serviciosList = usuarios.getServiciosList();
            for (Servicios serviciosListServicios : serviciosList) {
                serviciosListServicios.getUsuariosList().remove(usuarios);
                serviciosListServicios = em.merge(serviciosListServicios);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
