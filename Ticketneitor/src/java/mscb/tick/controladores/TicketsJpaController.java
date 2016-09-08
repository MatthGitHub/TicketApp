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
import mscb.tick.entidades.RazonesTransferencias;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.Usuarios;
import mscb.tick.entidades.AreaSistemas;
import mscb.tick.entidades.Servicios;
import mscb.tick.entidades.Respuestas;
import mscb.tick.entidades.BaseConocimiento;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Tickets;

/**
 *
 * @author Administrador
 */
public class TicketsJpaController implements Serializable {

    public TicketsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tickets tickets) {
        if (tickets.getBaseConocimientoList() == null) {
            tickets.setBaseConocimientoList(new ArrayList<BaseConocimiento>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RazonesTransferencias fkRazon = tickets.getFkRazon();
            if (fkRazon != null) {
                fkRazon = em.getReference(fkRazon.getClass(), fkRazon.getIdRazon());
                tickets.setFkRazon(fkRazon);
            }
            Areas fkAreaEmisor = tickets.getFkAreaEmisor();
            if (fkAreaEmisor != null) {
                fkAreaEmisor = em.getReference(fkAreaEmisor.getClass(), fkAreaEmisor.getIdArea());
                tickets.setFkAreaEmisor(fkAreaEmisor);
            }
            Estados fkEstado = tickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado = em.getReference(fkEstado.getClass(), fkEstado.getIdEstado());
                tickets.setFkEstado(fkEstado);
            }
            Usuarios fkUsuarioEmisor = tickets.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor = em.getReference(fkUsuarioEmisor.getClass(), fkUsuarioEmisor.getIdUsuario());
                tickets.setFkUsuarioEmisor(fkUsuarioEmisor);
            }
            Usuarios usuarioReceptor = tickets.getUsuarioReceptor();
            if (usuarioReceptor != null) {
                usuarioReceptor = em.getReference(usuarioReceptor.getClass(), usuarioReceptor.getIdUsuario());
                tickets.setUsuarioReceptor(usuarioReceptor);
            }
            AreaSistemas fkAreaSistemas = tickets.getFkAreaSistemas();
            if (fkAreaSistemas != null) {
                fkAreaSistemas = em.getReference(fkAreaSistemas.getClass(), fkAreaSistemas.getIdAreaSistemas());
                tickets.setFkAreaSistemas(fkAreaSistemas);
            }
            Servicios asunto = tickets.getAsunto();
            if (asunto != null) {
                asunto = em.getReference(asunto.getClass(), asunto.getIdasuntoS());
                tickets.setAsunto(asunto);
            }
            Respuestas respuestas = tickets.getRespuestas();
            if (respuestas != null) {
                respuestas = em.getReference(respuestas.getClass(), respuestas.getIdTicket());
                tickets.setRespuestas(respuestas);
            }
            List<BaseConocimiento> attachedBaseConocimientoList = new ArrayList<BaseConocimiento>();
            for (BaseConocimiento baseConocimientoListBaseConocimientoToAttach : tickets.getBaseConocimientoList()) {
                baseConocimientoListBaseConocimientoToAttach = em.getReference(baseConocimientoListBaseConocimientoToAttach.getClass(), baseConocimientoListBaseConocimientoToAttach.getIdResolucion());
                attachedBaseConocimientoList.add(baseConocimientoListBaseConocimientoToAttach);
            }
            tickets.setBaseConocimientoList(attachedBaseConocimientoList);
            em.persist(tickets);
            if (fkRazon != null) {
                fkRazon.getTicketsList().add(tickets);
                fkRazon = em.merge(fkRazon);
            }
            if (fkAreaEmisor != null) {
                fkAreaEmisor.getTicketsList().add(tickets);
                fkAreaEmisor = em.merge(fkAreaEmisor);
            }
            if (fkEstado != null) {
                fkEstado.getTicketsList().add(tickets);
                fkEstado = em.merge(fkEstado);
            }
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getTicketsList().add(tickets);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            if (usuarioReceptor != null) {
                usuarioReceptor.getTicketsList().add(tickets);
                usuarioReceptor = em.merge(usuarioReceptor);
            }
            if (fkAreaSistemas != null) {
                fkAreaSistemas.getTicketsList().add(tickets);
                fkAreaSistemas = em.merge(fkAreaSistemas);
            }
            if (asunto != null) {
                asunto.getTicketsList().add(tickets);
                asunto = em.merge(asunto);
            }
            if (respuestas != null) {
                Tickets oldTicketsOfRespuestas = respuestas.getTickets();
                if (oldTicketsOfRespuestas != null) {
                    oldTicketsOfRespuestas.setRespuestas(null);
                    oldTicketsOfRespuestas = em.merge(oldTicketsOfRespuestas);
                }
                respuestas.setTickets(tickets);
                respuestas = em.merge(respuestas);
            }
            for (BaseConocimiento baseConocimientoListBaseConocimiento : tickets.getBaseConocimientoList()) {
                Tickets oldFkTicketOfBaseConocimientoListBaseConocimiento = baseConocimientoListBaseConocimiento.getFkTicket();
                baseConocimientoListBaseConocimiento.setFkTicket(tickets);
                baseConocimientoListBaseConocimiento = em.merge(baseConocimientoListBaseConocimiento);
                if (oldFkTicketOfBaseConocimientoListBaseConocimiento != null) {
                    oldFkTicketOfBaseConocimientoListBaseConocimiento.getBaseConocimientoList().remove(baseConocimientoListBaseConocimiento);
                    oldFkTicketOfBaseConocimientoListBaseConocimiento = em.merge(oldFkTicketOfBaseConocimientoListBaseConocimiento);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tickets tickets) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets persistentTickets = em.find(Tickets.class, tickets.getIdTicket());
            RazonesTransferencias fkRazonOld = persistentTickets.getFkRazon();
            RazonesTransferencias fkRazonNew = tickets.getFkRazon();
            Areas fkAreaEmisorOld = persistentTickets.getFkAreaEmisor();
            Areas fkAreaEmisorNew = tickets.getFkAreaEmisor();
            Estados fkEstadoOld = persistentTickets.getFkEstado();
            Estados fkEstadoNew = tickets.getFkEstado();
            Usuarios fkUsuarioEmisorOld = persistentTickets.getFkUsuarioEmisor();
            Usuarios fkUsuarioEmisorNew = tickets.getFkUsuarioEmisor();
            Usuarios usuarioReceptorOld = persistentTickets.getUsuarioReceptor();
            Usuarios usuarioReceptorNew = tickets.getUsuarioReceptor();
            AreaSistemas fkAreaSistemasOld = persistentTickets.getFkAreaSistemas();
            AreaSistemas fkAreaSistemasNew = tickets.getFkAreaSistemas();
            Servicios asuntoOld = persistentTickets.getAsunto();
            Servicios asuntoNew = tickets.getAsunto();
            Respuestas respuestasOld = persistentTickets.getRespuestas();
            Respuestas respuestasNew = tickets.getRespuestas();
            List<BaseConocimiento> baseConocimientoListOld = persistentTickets.getBaseConocimientoList();
            List<BaseConocimiento> baseConocimientoListNew = tickets.getBaseConocimientoList();
            List<String> illegalOrphanMessages = null;
            if (respuestasOld != null && !respuestasOld.equals(respuestasNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Respuestas " + respuestasOld + " since its tickets field is not nullable.");
            }
            for (BaseConocimiento baseConocimientoListOldBaseConocimiento : baseConocimientoListOld) {
                if (!baseConocimientoListNew.contains(baseConocimientoListOldBaseConocimiento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BaseConocimiento " + baseConocimientoListOldBaseConocimiento + " since its fkTicket field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (fkRazonNew != null) {
                fkRazonNew = em.getReference(fkRazonNew.getClass(), fkRazonNew.getIdRazon());
                tickets.setFkRazon(fkRazonNew);
            }
            if (fkAreaEmisorNew != null) {
                fkAreaEmisorNew = em.getReference(fkAreaEmisorNew.getClass(), fkAreaEmisorNew.getIdArea());
                tickets.setFkAreaEmisor(fkAreaEmisorNew);
            }
            if (fkEstadoNew != null) {
                fkEstadoNew = em.getReference(fkEstadoNew.getClass(), fkEstadoNew.getIdEstado());
                tickets.setFkEstado(fkEstadoNew);
            }
            if (fkUsuarioEmisorNew != null) {
                fkUsuarioEmisorNew = em.getReference(fkUsuarioEmisorNew.getClass(), fkUsuarioEmisorNew.getIdUsuario());
                tickets.setFkUsuarioEmisor(fkUsuarioEmisorNew);
            }
            if (usuarioReceptorNew != null) {
                usuarioReceptorNew = em.getReference(usuarioReceptorNew.getClass(), usuarioReceptorNew.getIdUsuario());
                tickets.setUsuarioReceptor(usuarioReceptorNew);
            }
            if (fkAreaSistemasNew != null) {
                fkAreaSistemasNew = em.getReference(fkAreaSistemasNew.getClass(), fkAreaSistemasNew.getIdAreaSistemas());
                tickets.setFkAreaSistemas(fkAreaSistemasNew);
            }
            if (asuntoNew != null) {
                asuntoNew = em.getReference(asuntoNew.getClass(), asuntoNew.getIdasuntoS());
                tickets.setAsunto(asuntoNew);
            }
            if (respuestasNew != null) {
                respuestasNew = em.getReference(respuestasNew.getClass(), respuestasNew.getIdTicket());
                tickets.setRespuestas(respuestasNew);
            }
            List<BaseConocimiento> attachedBaseConocimientoListNew = new ArrayList<BaseConocimiento>();
            for (BaseConocimiento baseConocimientoListNewBaseConocimientoToAttach : baseConocimientoListNew) {
                baseConocimientoListNewBaseConocimientoToAttach = em.getReference(baseConocimientoListNewBaseConocimientoToAttach.getClass(), baseConocimientoListNewBaseConocimientoToAttach.getIdResolucion());
                attachedBaseConocimientoListNew.add(baseConocimientoListNewBaseConocimientoToAttach);
            }
            baseConocimientoListNew = attachedBaseConocimientoListNew;
            tickets.setBaseConocimientoList(baseConocimientoListNew);
            tickets = em.merge(tickets);
            if (fkRazonOld != null && !fkRazonOld.equals(fkRazonNew)) {
                fkRazonOld.getTicketsList().remove(tickets);
                fkRazonOld = em.merge(fkRazonOld);
            }
            if (fkRazonNew != null && !fkRazonNew.equals(fkRazonOld)) {
                fkRazonNew.getTicketsList().add(tickets);
                fkRazonNew = em.merge(fkRazonNew);
            }
            if (fkAreaEmisorOld != null && !fkAreaEmisorOld.equals(fkAreaEmisorNew)) {
                fkAreaEmisorOld.getTicketsList().remove(tickets);
                fkAreaEmisorOld = em.merge(fkAreaEmisorOld);
            }
            if (fkAreaEmisorNew != null && !fkAreaEmisorNew.equals(fkAreaEmisorOld)) {
                fkAreaEmisorNew.getTicketsList().add(tickets);
                fkAreaEmisorNew = em.merge(fkAreaEmisorNew);
            }
            if (fkEstadoOld != null && !fkEstadoOld.equals(fkEstadoNew)) {
                fkEstadoOld.getTicketsList().remove(tickets);
                fkEstadoOld = em.merge(fkEstadoOld);
            }
            if (fkEstadoNew != null && !fkEstadoNew.equals(fkEstadoOld)) {
                fkEstadoNew.getTicketsList().add(tickets);
                fkEstadoNew = em.merge(fkEstadoNew);
            }
            if (fkUsuarioEmisorOld != null && !fkUsuarioEmisorOld.equals(fkUsuarioEmisorNew)) {
                fkUsuarioEmisorOld.getTicketsList().remove(tickets);
                fkUsuarioEmisorOld = em.merge(fkUsuarioEmisorOld);
            }
            if (fkUsuarioEmisorNew != null && !fkUsuarioEmisorNew.equals(fkUsuarioEmisorOld)) {
                fkUsuarioEmisorNew.getTicketsList().add(tickets);
                fkUsuarioEmisorNew = em.merge(fkUsuarioEmisorNew);
            }
            if (usuarioReceptorOld != null && !usuarioReceptorOld.equals(usuarioReceptorNew)) {
                usuarioReceptorOld.getTicketsList().remove(tickets);
                usuarioReceptorOld = em.merge(usuarioReceptorOld);
            }
            if (usuarioReceptorNew != null && !usuarioReceptorNew.equals(usuarioReceptorOld)) {
                usuarioReceptorNew.getTicketsList().add(tickets);
                usuarioReceptorNew = em.merge(usuarioReceptorNew);
            }
            if (fkAreaSistemasOld != null && !fkAreaSistemasOld.equals(fkAreaSistemasNew)) {
                fkAreaSistemasOld.getTicketsList().remove(tickets);
                fkAreaSistemasOld = em.merge(fkAreaSistemasOld);
            }
            if (fkAreaSistemasNew != null && !fkAreaSistemasNew.equals(fkAreaSistemasOld)) {
                fkAreaSistemasNew.getTicketsList().add(tickets);
                fkAreaSistemasNew = em.merge(fkAreaSistemasNew);
            }
            if (asuntoOld != null && !asuntoOld.equals(asuntoNew)) {
                asuntoOld.getTicketsList().remove(tickets);
                asuntoOld = em.merge(asuntoOld);
            }
            if (asuntoNew != null && !asuntoNew.equals(asuntoOld)) {
                asuntoNew.getTicketsList().add(tickets);
                asuntoNew = em.merge(asuntoNew);
            }
            if (respuestasNew != null && !respuestasNew.equals(respuestasOld)) {
                Tickets oldTicketsOfRespuestas = respuestasNew.getTickets();
                if (oldTicketsOfRespuestas != null) {
                    oldTicketsOfRespuestas.setRespuestas(null);
                    oldTicketsOfRespuestas = em.merge(oldTicketsOfRespuestas);
                }
                respuestasNew.setTickets(tickets);
                respuestasNew = em.merge(respuestasNew);
            }
            for (BaseConocimiento baseConocimientoListNewBaseConocimiento : baseConocimientoListNew) {
                if (!baseConocimientoListOld.contains(baseConocimientoListNewBaseConocimiento)) {
                    Tickets oldFkTicketOfBaseConocimientoListNewBaseConocimiento = baseConocimientoListNewBaseConocimiento.getFkTicket();
                    baseConocimientoListNewBaseConocimiento.setFkTicket(tickets);
                    baseConocimientoListNewBaseConocimiento = em.merge(baseConocimientoListNewBaseConocimiento);
                    if (oldFkTicketOfBaseConocimientoListNewBaseConocimiento != null && !oldFkTicketOfBaseConocimientoListNewBaseConocimiento.equals(tickets)) {
                        oldFkTicketOfBaseConocimientoListNewBaseConocimiento.getBaseConocimientoList().remove(baseConocimientoListNewBaseConocimiento);
                        oldFkTicketOfBaseConocimientoListNewBaseConocimiento = em.merge(oldFkTicketOfBaseConocimientoListNewBaseConocimiento);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tickets.getIdTicket();
                if (findTickets(id) == null) {
                    throw new NonexistentEntityException("The tickets with id " + id + " no longer exists.");
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
            Tickets tickets;
            try {
                tickets = em.getReference(Tickets.class, id);
                tickets.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tickets with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Respuestas respuestasOrphanCheck = tickets.getRespuestas();
            if (respuestasOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tickets (" + tickets + ") cannot be destroyed since the Respuestas " + respuestasOrphanCheck + " in its respuestas field has a non-nullable tickets field.");
            }
            List<BaseConocimiento> baseConocimientoListOrphanCheck = tickets.getBaseConocimientoList();
            for (BaseConocimiento baseConocimientoListOrphanCheckBaseConocimiento : baseConocimientoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tickets (" + tickets + ") cannot be destroyed since the BaseConocimiento " + baseConocimientoListOrphanCheckBaseConocimiento + " in its baseConocimientoList field has a non-nullable fkTicket field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            RazonesTransferencias fkRazon = tickets.getFkRazon();
            if (fkRazon != null) {
                fkRazon.getTicketsList().remove(tickets);
                fkRazon = em.merge(fkRazon);
            }
            Areas fkAreaEmisor = tickets.getFkAreaEmisor();
            if (fkAreaEmisor != null) {
                fkAreaEmisor.getTicketsList().remove(tickets);
                fkAreaEmisor = em.merge(fkAreaEmisor);
            }
            Estados fkEstado = tickets.getFkEstado();
            if (fkEstado != null) {
                fkEstado.getTicketsList().remove(tickets);
                fkEstado = em.merge(fkEstado);
            }
            Usuarios fkUsuarioEmisor = tickets.getFkUsuarioEmisor();
            if (fkUsuarioEmisor != null) {
                fkUsuarioEmisor.getTicketsList().remove(tickets);
                fkUsuarioEmisor = em.merge(fkUsuarioEmisor);
            }
            Usuarios usuarioReceptor = tickets.getUsuarioReceptor();
            if (usuarioReceptor != null) {
                usuarioReceptor.getTicketsList().remove(tickets);
                usuarioReceptor = em.merge(usuarioReceptor);
            }
            AreaSistemas fkAreaSistemas = tickets.getFkAreaSistemas();
            if (fkAreaSistemas != null) {
                fkAreaSistemas.getTicketsList().remove(tickets);
                fkAreaSistemas = em.merge(fkAreaSistemas);
            }
            Servicios asunto = tickets.getAsunto();
            if (asunto != null) {
                asunto.getTicketsList().remove(tickets);
                asunto = em.merge(asunto);
            }
            em.remove(tickets);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tickets> findTicketsEntities() {
        return findTicketsEntities(true, -1, -1);
    }

    public List<Tickets> findTicketsEntities(int maxResults, int firstResult) {
        return findTicketsEntities(false, maxResults, firstResult);
    }

    private List<Tickets> findTicketsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tickets.class));
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

    public Tickets findTickets(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tickets.class, id);
        } finally {
            em.close();
        }
    }

    public int getTicketsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tickets> rt = cq.from(Tickets.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
