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
import mscb.tick.entidades.Empleados;
import mscb.tick.entidades.Servicios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Respuestas;
import mscb.tick.entidades.Tickets;
import mscb.tick.entidades.Usuarios;

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
        if (usuarios.getRespuestasList() == null) {
            usuarios.setRespuestasList(new ArrayList<Respuestas>());
        }
        if (usuarios.getTicketsList() == null) {
            usuarios.setTicketsList(new ArrayList<Tickets>());
        }
        if (usuarios.getTicketsList1() == null) {
            usuarios.setTicketsList1(new ArrayList<Tickets>());
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
            List<Servicios> attachedServiciosList = new ArrayList<Servicios>();
            for (Servicios serviciosListServiciosToAttach : usuarios.getServiciosList()) {
                serviciosListServiciosToAttach = em.getReference(serviciosListServiciosToAttach.getClass(), serviciosListServiciosToAttach.getIdasuntoS());
                attachedServiciosList.add(serviciosListServiciosToAttach);
            }
            usuarios.setServiciosList(attachedServiciosList);
            List<Respuestas> attachedRespuestasList = new ArrayList<Respuestas>();
            for (Respuestas respuestasListRespuestasToAttach : usuarios.getRespuestasList()) {
                respuestasListRespuestasToAttach = em.getReference(respuestasListRespuestasToAttach.getClass(), respuestasListRespuestasToAttach.getIdTicket());
                attachedRespuestasList.add(respuestasListRespuestasToAttach);
            }
            usuarios.setRespuestasList(attachedRespuestasList);
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : usuarios.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            usuarios.setTicketsList(attachedTicketsList);
            List<Tickets> attachedTicketsList1 = new ArrayList<Tickets>();
            for (Tickets ticketsList1TicketsToAttach : usuarios.getTicketsList1()) {
                ticketsList1TicketsToAttach = em.getReference(ticketsList1TicketsToAttach.getClass(), ticketsList1TicketsToAttach.getIdTicket());
                attachedTicketsList1.add(ticketsList1TicketsToAttach);
            }
            usuarios.setTicketsList1(attachedTicketsList1);
            em.persist(usuarios);
            if (fkEmpleado != null) {
                fkEmpleado.getUsuariosList().add(usuarios);
                fkEmpleado = em.merge(fkEmpleado);
            }
            for (Servicios serviciosListServicios : usuarios.getServiciosList()) {
                serviciosListServicios.getUsuariosList().add(usuarios);
                serviciosListServicios = em.merge(serviciosListServicios);
            }
            for (Respuestas respuestasListRespuestas : usuarios.getRespuestasList()) {
                Usuarios oldIdUsuarioOfRespuestasListRespuestas = respuestasListRespuestas.getIdUsuario();
                respuestasListRespuestas.setIdUsuario(usuarios);
                respuestasListRespuestas = em.merge(respuestasListRespuestas);
                if (oldIdUsuarioOfRespuestasListRespuestas != null) {
                    oldIdUsuarioOfRespuestasListRespuestas.getRespuestasList().remove(respuestasListRespuestas);
                    oldIdUsuarioOfRespuestasListRespuestas = em.merge(oldIdUsuarioOfRespuestasListRespuestas);
                }
            }
            for (Tickets ticketsListTickets : usuarios.getTicketsList()) {
                Usuarios oldFkUsuarioEmisorOfTicketsListTickets = ticketsListTickets.getFkUsuarioEmisor();
                ticketsListTickets.setFkUsuarioEmisor(usuarios);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkUsuarioEmisorOfTicketsListTickets != null) {
                    oldFkUsuarioEmisorOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkUsuarioEmisorOfTicketsListTickets = em.merge(oldFkUsuarioEmisorOfTicketsListTickets);
                }
            }
            for (Tickets ticketsList1Tickets : usuarios.getTicketsList1()) {
                Usuarios oldUsuarioReceptorOfTicketsList1Tickets = ticketsList1Tickets.getUsuarioReceptor();
                ticketsList1Tickets.setUsuarioReceptor(usuarios);
                ticketsList1Tickets = em.merge(ticketsList1Tickets);
                if (oldUsuarioReceptorOfTicketsList1Tickets != null) {
                    oldUsuarioReceptorOfTicketsList1Tickets.getTicketsList1().remove(ticketsList1Tickets);
                    oldUsuarioReceptorOfTicketsList1Tickets = em.merge(oldUsuarioReceptorOfTicketsList1Tickets);
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
            List<Servicios> serviciosListOld = persistentUsuarios.getServiciosList();
            List<Servicios> serviciosListNew = usuarios.getServiciosList();
            List<Respuestas> respuestasListOld = persistentUsuarios.getRespuestasList();
            List<Respuestas> respuestasListNew = usuarios.getRespuestasList();
            List<Tickets> ticketsListOld = persistentUsuarios.getTicketsList();
            List<Tickets> ticketsListNew = usuarios.getTicketsList();
            List<Tickets> ticketsList1Old = persistentUsuarios.getTicketsList1();
            List<Tickets> ticketsList1New = usuarios.getTicketsList1();
            List<String> illegalOrphanMessages = null;
            for (Respuestas respuestasListOldRespuestas : respuestasListOld) {
                if (!respuestasListNew.contains(respuestasListOldRespuestas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Respuestas " + respuestasListOldRespuestas + " since its idUsuario field is not nullable.");
                }
            }
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tickets " + ticketsListOldTickets + " since its fkUsuarioEmisor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkEmpleadoNew != null) {
                fkEmpleadoNew = em.getReference(fkEmpleadoNew.getClass(), fkEmpleadoNew.getIdEmpleado());
                usuarios.setFkEmpleado(fkEmpleadoNew);
            }
            List<Servicios> attachedServiciosListNew = new ArrayList<Servicios>();
            for (Servicios serviciosListNewServiciosToAttach : serviciosListNew) {
                serviciosListNewServiciosToAttach = em.getReference(serviciosListNewServiciosToAttach.getClass(), serviciosListNewServiciosToAttach.getIdasuntoS());
                attachedServiciosListNew.add(serviciosListNewServiciosToAttach);
            }
            serviciosListNew = attachedServiciosListNew;
            usuarios.setServiciosList(serviciosListNew);
            List<Respuestas> attachedRespuestasListNew = new ArrayList<Respuestas>();
            for (Respuestas respuestasListNewRespuestasToAttach : respuestasListNew) {
                respuestasListNewRespuestasToAttach = em.getReference(respuestasListNewRespuestasToAttach.getClass(), respuestasListNewRespuestasToAttach.getIdTicket());
                attachedRespuestasListNew.add(respuestasListNewRespuestasToAttach);
            }
            respuestasListNew = attachedRespuestasListNew;
            usuarios.setRespuestasList(respuestasListNew);
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            usuarios.setTicketsList(ticketsListNew);
            List<Tickets> attachedTicketsList1New = new ArrayList<Tickets>();
            for (Tickets ticketsList1NewTicketsToAttach : ticketsList1New) {
                ticketsList1NewTicketsToAttach = em.getReference(ticketsList1NewTicketsToAttach.getClass(), ticketsList1NewTicketsToAttach.getIdTicket());
                attachedTicketsList1New.add(ticketsList1NewTicketsToAttach);
            }
            ticketsList1New = attachedTicketsList1New;
            usuarios.setTicketsList1(ticketsList1New);
            usuarios = em.merge(usuarios);
            if (fkEmpleadoOld != null && !fkEmpleadoOld.equals(fkEmpleadoNew)) {
                fkEmpleadoOld.getUsuariosList().remove(usuarios);
                fkEmpleadoOld = em.merge(fkEmpleadoOld);
            }
            if (fkEmpleadoNew != null && !fkEmpleadoNew.equals(fkEmpleadoOld)) {
                fkEmpleadoNew.getUsuariosList().add(usuarios);
                fkEmpleadoNew = em.merge(fkEmpleadoNew);
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
            for (Respuestas respuestasListNewRespuestas : respuestasListNew) {
                if (!respuestasListOld.contains(respuestasListNewRespuestas)) {
                    Usuarios oldIdUsuarioOfRespuestasListNewRespuestas = respuestasListNewRespuestas.getIdUsuario();
                    respuestasListNewRespuestas.setIdUsuario(usuarios);
                    respuestasListNewRespuestas = em.merge(respuestasListNewRespuestas);
                    if (oldIdUsuarioOfRespuestasListNewRespuestas != null && !oldIdUsuarioOfRespuestasListNewRespuestas.equals(usuarios)) {
                        oldIdUsuarioOfRespuestasListNewRespuestas.getRespuestasList().remove(respuestasListNewRespuestas);
                        oldIdUsuarioOfRespuestasListNewRespuestas = em.merge(oldIdUsuarioOfRespuestasListNewRespuestas);
                    }
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Usuarios oldFkUsuarioEmisorOfTicketsListNewTickets = ticketsListNewTickets.getFkUsuarioEmisor();
                    ticketsListNewTickets.setFkUsuarioEmisor(usuarios);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkUsuarioEmisorOfTicketsListNewTickets != null && !oldFkUsuarioEmisorOfTicketsListNewTickets.equals(usuarios)) {
                        oldFkUsuarioEmisorOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkUsuarioEmisorOfTicketsListNewTickets = em.merge(oldFkUsuarioEmisorOfTicketsListNewTickets);
                    }
                }
            }
            for (Tickets ticketsList1OldTickets : ticketsList1Old) {
                if (!ticketsList1New.contains(ticketsList1OldTickets)) {
                    ticketsList1OldTickets.setUsuarioReceptor(null);
                    ticketsList1OldTickets = em.merge(ticketsList1OldTickets);
                }
            }
            for (Tickets ticketsList1NewTickets : ticketsList1New) {
                if (!ticketsList1Old.contains(ticketsList1NewTickets)) {
                    Usuarios oldUsuarioReceptorOfTicketsList1NewTickets = ticketsList1NewTickets.getUsuarioReceptor();
                    ticketsList1NewTickets.setUsuarioReceptor(usuarios);
                    ticketsList1NewTickets = em.merge(ticketsList1NewTickets);
                    if (oldUsuarioReceptorOfTicketsList1NewTickets != null && !oldUsuarioReceptorOfTicketsList1NewTickets.equals(usuarios)) {
                        oldUsuarioReceptorOfTicketsList1NewTickets.getTicketsList1().remove(ticketsList1NewTickets);
                        oldUsuarioReceptorOfTicketsList1NewTickets = em.merge(oldUsuarioReceptorOfTicketsList1NewTickets);
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
            List<Respuestas> respuestasListOrphanCheck = usuarios.getRespuestasList();
            for (Respuestas respuestasListOrphanCheckRespuestas : respuestasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Respuestas " + respuestasListOrphanCheckRespuestas + " in its respuestasList field has a non-nullable idUsuario field.");
            }
            List<Tickets> ticketsListOrphanCheck = usuarios.getTicketsList();
            for (Tickets ticketsListOrphanCheckTickets : ticketsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Tickets " + ticketsListOrphanCheckTickets + " in its ticketsList field has a non-nullable fkUsuarioEmisor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleados fkEmpleado = usuarios.getFkEmpleado();
            if (fkEmpleado != null) {
                fkEmpleado.getUsuariosList().remove(usuarios);
                fkEmpleado = em.merge(fkEmpleado);
            }
            List<Servicios> serviciosList = usuarios.getServiciosList();
            for (Servicios serviciosListServicios : serviciosList) {
                serviciosListServicios.getUsuariosList().remove(usuarios);
                serviciosListServicios = em.merge(serviciosListServicios);
            }
            List<Tickets> ticketsList1 = usuarios.getTicketsList1();
            for (Tickets ticketsList1Tickets : ticketsList1) {
                ticketsList1Tickets.setUsuarioReceptor(null);
                ticketsList1Tickets = em.merge(ticketsList1Tickets);
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
