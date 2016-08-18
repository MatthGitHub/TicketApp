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
import mscb.tick.entidades.Tickets;
import mscb.tick.entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.controladores.exceptions.PreexistingEntityException;
import mscb.tick.entidades.Respuestas;

/**
 *
 * @author Administrador
 */
public class RespuestasJpaController implements Serializable {

    public RespuestasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Respuestas respuestas) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Tickets ticketsOrphanCheck = respuestas.getTickets();
        if (ticketsOrphanCheck != null) {
            Respuestas oldRespuestasOfTickets = ticketsOrphanCheck.getRespuestas();
            if (oldRespuestasOfTickets != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Tickets " + ticketsOrphanCheck + " already has an item of type Respuestas whose tickets column cannot be null. Please make another selection for the tickets field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tickets tickets = respuestas.getTickets();
            if (tickets != null) {
                tickets = em.getReference(tickets.getClass(), tickets.getIdTicket());
                respuestas.setTickets(tickets);
            }
            Usuarios idUsuario = respuestas.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                respuestas.setIdUsuario(idUsuario);
            }
            em.persist(respuestas);
            if (tickets != null) {
                tickets.setRespuestas(respuestas);
                tickets = em.merge(tickets);
            }
            if (idUsuario != null) {
                idUsuario.getRespuestasList().add(respuestas);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRespuestas(respuestas.getIdTicket()) != null) {
                throw new PreexistingEntityException("Respuestas " + respuestas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Respuestas respuestas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Respuestas persistentRespuestas = em.find(Respuestas.class, respuestas.getIdTicket());
            Tickets ticketsOld = persistentRespuestas.getTickets();
            Tickets ticketsNew = respuestas.getTickets();
            Usuarios idUsuarioOld = persistentRespuestas.getIdUsuario();
            Usuarios idUsuarioNew = respuestas.getIdUsuario();
            List<String> illegalOrphanMessages = null;
            if (ticketsNew != null && !ticketsNew.equals(ticketsOld)) {
                Respuestas oldRespuestasOfTickets = ticketsNew.getRespuestas();
                if (oldRespuestasOfTickets != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Tickets " + ticketsNew + " already has an item of type Respuestas whose tickets column cannot be null. Please make another selection for the tickets field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ticketsNew != null) {
                ticketsNew = em.getReference(ticketsNew.getClass(), ticketsNew.getIdTicket());
                respuestas.setTickets(ticketsNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                respuestas.setIdUsuario(idUsuarioNew);
            }
            respuestas = em.merge(respuestas);
            if (ticketsOld != null && !ticketsOld.equals(ticketsNew)) {
                ticketsOld.setRespuestas(null);
                ticketsOld = em.merge(ticketsOld);
            }
            if (ticketsNew != null && !ticketsNew.equals(ticketsOld)) {
                ticketsNew.setRespuestas(respuestas);
                ticketsNew = em.merge(ticketsNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getRespuestasList().remove(respuestas);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getRespuestasList().add(respuestas);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = respuestas.getIdTicket();
                if (findRespuestas(id) == null) {
                    throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.");
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
            Respuestas respuestas;
            try {
                respuestas = em.getReference(Respuestas.class, id);
                respuestas.getIdTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The respuestas with id " + id + " no longer exists.", enfe);
            }
            Tickets tickets = respuestas.getTickets();
            if (tickets != null) {
                tickets.setRespuestas(null);
                tickets = em.merge(tickets);
            }
            Usuarios idUsuario = respuestas.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getRespuestasList().remove(respuestas);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(respuestas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Respuestas> findRespuestasEntities() {
        return findRespuestasEntities(true, -1, -1);
    }

    public List<Respuestas> findRespuestasEntities(int maxResults, int firstResult) {
        return findRespuestasEntities(false, maxResults, firstResult);
    }

    private List<Respuestas> findRespuestasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Respuestas.class));
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

    public Respuestas findRespuestas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Respuestas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRespuestasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Respuestas> rt = cq.from(Respuestas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
