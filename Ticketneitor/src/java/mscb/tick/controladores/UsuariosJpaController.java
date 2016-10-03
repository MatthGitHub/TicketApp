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
import mscb.tick.entidades.Permisos;
import mscb.tick.entidades.Servicios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Tickets;
import mscb.tick.entidades.HistorialTickets;
import mscb.tick.entidades.Respuestas;
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
        if (usuarios.getTicketsList() == null) {
            usuarios.setTicketsList(new ArrayList<Tickets>());
        }
        if (usuarios.getTicketsList1() == null) {
            usuarios.setTicketsList1(new ArrayList<Tickets>());
        }
        if (usuarios.getHistorialTicketsList() == null) {
            usuarios.setHistorialTicketsList(new ArrayList<HistorialTickets>());
        }
        if (usuarios.getHistorialTicketsList1() == null) {
            usuarios.setHistorialTicketsList1(new ArrayList<HistorialTickets>());
        }
        if (usuarios.getRespuestasList() == null) {
            usuarios.setRespuestasList(new ArrayList<Respuestas>());
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
            Permisos fkPermiso = usuarios.getFkPermiso();
            if (fkPermiso != null) {
                fkPermiso = em.getReference(fkPermiso.getClass(), fkPermiso.getIdPermiso());
                usuarios.setFkPermiso(fkPermiso);
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
            List<Tickets> attachedTicketsList1 = new ArrayList<Tickets>();
            for (Tickets ticketsList1TicketsToAttach : usuarios.getTicketsList1()) {
                ticketsList1TicketsToAttach = em.getReference(ticketsList1TicketsToAttach.getClass(), ticketsList1TicketsToAttach.getIdTicket());
                attachedTicketsList1.add(ticketsList1TicketsToAttach);
            }
            usuarios.setTicketsList1(attachedTicketsList1);
            List<HistorialTickets> attachedHistorialTicketsList = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListHistorialTicketsToAttach : usuarios.getHistorialTicketsList()) {
                historialTicketsListHistorialTicketsToAttach = em.getReference(historialTicketsListHistorialTicketsToAttach.getClass(), historialTicketsListHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList.add(historialTicketsListHistorialTicketsToAttach);
            }
            usuarios.setHistorialTicketsList(attachedHistorialTicketsList);
            List<HistorialTickets> attachedHistorialTicketsList1 = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsList1HistorialTicketsToAttach : usuarios.getHistorialTicketsList1()) {
                historialTicketsList1HistorialTicketsToAttach = em.getReference(historialTicketsList1HistorialTicketsToAttach.getClass(), historialTicketsList1HistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList1.add(historialTicketsList1HistorialTicketsToAttach);
            }
            usuarios.setHistorialTicketsList1(attachedHistorialTicketsList1);
            List<Respuestas> attachedRespuestasList = new ArrayList<Respuestas>();
            for (Respuestas respuestasListRespuestasToAttach : usuarios.getRespuestasList()) {
                respuestasListRespuestasToAttach = em.getReference(respuestasListRespuestasToAttach.getClass(), respuestasListRespuestasToAttach.getIdTicket());
                attachedRespuestasList.add(respuestasListRespuestasToAttach);
            }
            usuarios.setRespuestasList(attachedRespuestasList);
            em.persist(usuarios);
            if (fkEmpleado != null) {
                fkEmpleado.getUsuariosList().add(usuarios);
                fkEmpleado = em.merge(fkEmpleado);
            }
            if (fkPermiso != null) {
                fkPermiso.getUsuariosList().add(usuarios);
                fkPermiso = em.merge(fkPermiso);
            }
            for (Servicios serviciosListServicios : usuarios.getServiciosList()) {
                serviciosListServicios.getUsuariosList().add(usuarios);
                serviciosListServicios = em.merge(serviciosListServicios);
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
            for (HistorialTickets historialTicketsListHistorialTickets : usuarios.getHistorialTicketsList()) {
                Usuarios oldFkUsuarioEmisorOfHistorialTicketsListHistorialTickets = historialTicketsListHistorialTickets.getFkUsuarioEmisor();
                historialTicketsListHistorialTickets.setFkUsuarioEmisor(usuarios);
                historialTicketsListHistorialTickets = em.merge(historialTicketsListHistorialTickets);
                if (oldFkUsuarioEmisorOfHistorialTicketsListHistorialTickets != null) {
                    oldFkUsuarioEmisorOfHistorialTicketsListHistorialTickets.getHistorialTicketsList().remove(historialTicketsListHistorialTickets);
                    oldFkUsuarioEmisorOfHistorialTicketsListHistorialTickets = em.merge(oldFkUsuarioEmisorOfHistorialTicketsListHistorialTickets);
                }
            }
            for (HistorialTickets historialTicketsList1HistorialTickets : usuarios.getHistorialTicketsList1()) {
                Usuarios oldFkUsuarioReceptorOfHistorialTicketsList1HistorialTickets = historialTicketsList1HistorialTickets.getFkUsuarioReceptor();
                historialTicketsList1HistorialTickets.setFkUsuarioReceptor(usuarios);
                historialTicketsList1HistorialTickets = em.merge(historialTicketsList1HistorialTickets);
                if (oldFkUsuarioReceptorOfHistorialTicketsList1HistorialTickets != null) {
                    oldFkUsuarioReceptorOfHistorialTicketsList1HistorialTickets.getHistorialTicketsList1().remove(historialTicketsList1HistorialTickets);
                    oldFkUsuarioReceptorOfHistorialTicketsList1HistorialTickets = em.merge(oldFkUsuarioReceptorOfHistorialTicketsList1HistorialTickets);
                }
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
            Permisos fkPermisoOld = persistentUsuarios.getFkPermiso();
            Permisos fkPermisoNew = usuarios.getFkPermiso();
            List<Servicios> serviciosListOld = persistentUsuarios.getServiciosList();
            List<Servicios> serviciosListNew = usuarios.getServiciosList();
            List<Tickets> ticketsListOld = persistentUsuarios.getTicketsList();
            List<Tickets> ticketsListNew = usuarios.getTicketsList();
            List<Tickets> ticketsList1Old = persistentUsuarios.getTicketsList1();
            List<Tickets> ticketsList1New = usuarios.getTicketsList1();
            List<HistorialTickets> historialTicketsListOld = persistentUsuarios.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsListNew = usuarios.getHistorialTicketsList();
            List<HistorialTickets> historialTicketsList1Old = persistentUsuarios.getHistorialTicketsList1();
            List<HistorialTickets> historialTicketsList1New = usuarios.getHistorialTicketsList1();
            List<Respuestas> respuestasListOld = persistentUsuarios.getRespuestasList();
            List<Respuestas> respuestasListNew = usuarios.getRespuestasList();
            List<String> illegalOrphanMessages = null;
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tickets " + ticketsListOldTickets + " since its fkUsuarioEmisor field is not nullable.");
                }
            }
            for (HistorialTickets historialTicketsListOldHistorialTickets : historialTicketsListOld) {
                if (!historialTicketsListNew.contains(historialTicketsListOldHistorialTickets)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain HistorialTickets " + historialTicketsListOldHistorialTickets + " since its fkUsuarioEmisor field is not nullable.");
                }
            }
            for (Respuestas respuestasListOldRespuestas : respuestasListOld) {
                if (!respuestasListNew.contains(respuestasListOldRespuestas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Respuestas " + respuestasListOldRespuestas + " since its idUsuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkEmpleadoNew != null) {
                fkEmpleadoNew = em.getReference(fkEmpleadoNew.getClass(), fkEmpleadoNew.getIdEmpleado());
                usuarios.setFkEmpleado(fkEmpleadoNew);
            }
            if (fkPermisoNew != null) {
                fkPermisoNew = em.getReference(fkPermisoNew.getClass(), fkPermisoNew.getIdPermiso());
                usuarios.setFkPermiso(fkPermisoNew);
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
            List<Tickets> attachedTicketsList1New = new ArrayList<Tickets>();
            for (Tickets ticketsList1NewTicketsToAttach : ticketsList1New) {
                ticketsList1NewTicketsToAttach = em.getReference(ticketsList1NewTicketsToAttach.getClass(), ticketsList1NewTicketsToAttach.getIdTicket());
                attachedTicketsList1New.add(ticketsList1NewTicketsToAttach);
            }
            ticketsList1New = attachedTicketsList1New;
            usuarios.setTicketsList1(ticketsList1New);
            List<HistorialTickets> attachedHistorialTicketsListNew = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsListNewHistorialTicketsToAttach : historialTicketsListNew) {
                historialTicketsListNewHistorialTicketsToAttach = em.getReference(historialTicketsListNewHistorialTicketsToAttach.getClass(), historialTicketsListNewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsListNew.add(historialTicketsListNewHistorialTicketsToAttach);
            }
            historialTicketsListNew = attachedHistorialTicketsListNew;
            usuarios.setHistorialTicketsList(historialTicketsListNew);
            List<HistorialTickets> attachedHistorialTicketsList1New = new ArrayList<HistorialTickets>();
            for (HistorialTickets historialTicketsList1NewHistorialTicketsToAttach : historialTicketsList1New) {
                historialTicketsList1NewHistorialTicketsToAttach = em.getReference(historialTicketsList1NewHistorialTicketsToAttach.getClass(), historialTicketsList1NewHistorialTicketsToAttach.getIdHistorial());
                attachedHistorialTicketsList1New.add(historialTicketsList1NewHistorialTicketsToAttach);
            }
            historialTicketsList1New = attachedHistorialTicketsList1New;
            usuarios.setHistorialTicketsList1(historialTicketsList1New);
            List<Respuestas> attachedRespuestasListNew = new ArrayList<Respuestas>();
            for (Respuestas respuestasListNewRespuestasToAttach : respuestasListNew) {
                respuestasListNewRespuestasToAttach = em.getReference(respuestasListNewRespuestasToAttach.getClass(), respuestasListNewRespuestasToAttach.getIdTicket());
                attachedRespuestasListNew.add(respuestasListNewRespuestasToAttach);
            }
            respuestasListNew = attachedRespuestasListNew;
            usuarios.setRespuestasList(respuestasListNew);
            usuarios = em.merge(usuarios);
            if (fkEmpleadoOld != null && !fkEmpleadoOld.equals(fkEmpleadoNew)) {
                fkEmpleadoOld.getUsuariosList().remove(usuarios);
                fkEmpleadoOld = em.merge(fkEmpleadoOld);
            }
            if (fkEmpleadoNew != null && !fkEmpleadoNew.equals(fkEmpleadoOld)) {
                fkEmpleadoNew.getUsuariosList().add(usuarios);
                fkEmpleadoNew = em.merge(fkEmpleadoNew);
            }
            if (fkPermisoOld != null && !fkPermisoOld.equals(fkPermisoNew)) {
                fkPermisoOld.getUsuariosList().remove(usuarios);
                fkPermisoOld = em.merge(fkPermisoOld);
            }
            if (fkPermisoNew != null && !fkPermisoNew.equals(fkPermisoOld)) {
                fkPermisoNew.getUsuariosList().add(usuarios);
                fkPermisoNew = em.merge(fkPermisoNew);
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
            for (HistorialTickets historialTicketsListNewHistorialTickets : historialTicketsListNew) {
                if (!historialTicketsListOld.contains(historialTicketsListNewHistorialTickets)) {
                    Usuarios oldFkUsuarioEmisorOfHistorialTicketsListNewHistorialTickets = historialTicketsListNewHistorialTickets.getFkUsuarioEmisor();
                    historialTicketsListNewHistorialTickets.setFkUsuarioEmisor(usuarios);
                    historialTicketsListNewHistorialTickets = em.merge(historialTicketsListNewHistorialTickets);
                    if (oldFkUsuarioEmisorOfHistorialTicketsListNewHistorialTickets != null && !oldFkUsuarioEmisorOfHistorialTicketsListNewHistorialTickets.equals(usuarios)) {
                        oldFkUsuarioEmisorOfHistorialTicketsListNewHistorialTickets.getHistorialTicketsList().remove(historialTicketsListNewHistorialTickets);
                        oldFkUsuarioEmisorOfHistorialTicketsListNewHistorialTickets = em.merge(oldFkUsuarioEmisorOfHistorialTicketsListNewHistorialTickets);
                    }
                }
            }
            for (HistorialTickets historialTicketsList1OldHistorialTickets : historialTicketsList1Old) {
                if (!historialTicketsList1New.contains(historialTicketsList1OldHistorialTickets)) {
                    historialTicketsList1OldHistorialTickets.setFkUsuarioReceptor(null);
                    historialTicketsList1OldHistorialTickets = em.merge(historialTicketsList1OldHistorialTickets);
                }
            }
            for (HistorialTickets historialTicketsList1NewHistorialTickets : historialTicketsList1New) {
                if (!historialTicketsList1Old.contains(historialTicketsList1NewHistorialTickets)) {
                    Usuarios oldFkUsuarioReceptorOfHistorialTicketsList1NewHistorialTickets = historialTicketsList1NewHistorialTickets.getFkUsuarioReceptor();
                    historialTicketsList1NewHistorialTickets.setFkUsuarioReceptor(usuarios);
                    historialTicketsList1NewHistorialTickets = em.merge(historialTicketsList1NewHistorialTickets);
                    if (oldFkUsuarioReceptorOfHistorialTicketsList1NewHistorialTickets != null && !oldFkUsuarioReceptorOfHistorialTicketsList1NewHistorialTickets.equals(usuarios)) {
                        oldFkUsuarioReceptorOfHistorialTicketsList1NewHistorialTickets.getHistorialTicketsList1().remove(historialTicketsList1NewHistorialTickets);
                        oldFkUsuarioReceptorOfHistorialTicketsList1NewHistorialTickets = em.merge(oldFkUsuarioReceptorOfHistorialTicketsList1NewHistorialTickets);
                    }
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
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Tickets " + ticketsListOrphanCheckTickets + " in its ticketsList field has a non-nullable fkUsuarioEmisor field.");
            }
            List<HistorialTickets> historialTicketsListOrphanCheck = usuarios.getHistorialTicketsList();
            for (HistorialTickets historialTicketsListOrphanCheckHistorialTickets : historialTicketsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the HistorialTickets " + historialTicketsListOrphanCheckHistorialTickets + " in its historialTicketsList field has a non-nullable fkUsuarioEmisor field.");
            }
            List<Respuestas> respuestasListOrphanCheck = usuarios.getRespuestasList();
            for (Respuestas respuestasListOrphanCheckRespuestas : respuestasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Respuestas " + respuestasListOrphanCheckRespuestas + " in its respuestasList field has a non-nullable idUsuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleados fkEmpleado = usuarios.getFkEmpleado();
            if (fkEmpleado != null) {
                fkEmpleado.getUsuariosList().remove(usuarios);
                fkEmpleado = em.merge(fkEmpleado);
            }
            Permisos fkPermiso = usuarios.getFkPermiso();
            if (fkPermiso != null) {
                fkPermiso.getUsuariosList().remove(usuarios);
                fkPermiso = em.merge(fkPermiso);
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
            List<HistorialTickets> historialTicketsList1 = usuarios.getHistorialTicketsList1();
            for (HistorialTickets historialTicketsList1HistorialTickets : historialTicketsList1) {
                historialTicketsList1HistorialTickets.setFkUsuarioReceptor(null);
                historialTicketsList1HistorialTickets = em.merge(historialTicketsList1HistorialTickets);
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
