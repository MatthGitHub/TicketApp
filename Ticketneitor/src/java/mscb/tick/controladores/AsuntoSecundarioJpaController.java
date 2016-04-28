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
import mscb.tick.entidades.AsuntoPrincipal;
import mscb.tick.entidades.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.AsuntoSecundario;

/**
 *
 * @author Administrador
 */
public class AsuntoSecundarioJpaController implements Serializable {

    public AsuntoSecundarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsuntoSecundario asuntoSecundario) {
        if (asuntoSecundario.getTicketCollection() == null) {
            asuntoSecundario.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsuntoPrincipal pertenece = asuntoSecundario.getPertenece();
            if (pertenece != null) {
                pertenece = em.getReference(pertenece.getClass(), pertenece.getIdasuntoP());
                asuntoSecundario.setPertenece(pertenece);
            }
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : asuntoSecundario.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            asuntoSecundario.setTicketCollection(attachedTicketCollection);
            em.persist(asuntoSecundario);
            if (pertenece != null) {
                pertenece.getAsuntoSecundarioCollection().add(asuntoSecundario);
                pertenece = em.merge(pertenece);
            }
            for (Ticket ticketCollectionTicket : asuntoSecundario.getTicketCollection()) {
                AsuntoSecundario oldAsuntoOfTicketCollectionTicket = ticketCollectionTicket.getAsunto();
                ticketCollectionTicket.setAsunto(asuntoSecundario);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldAsuntoOfTicketCollectionTicket != null) {
                    oldAsuntoOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldAsuntoOfTicketCollectionTicket = em.merge(oldAsuntoOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsuntoSecundario asuntoSecundario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsuntoSecundario persistentAsuntoSecundario = em.find(AsuntoSecundario.class, asuntoSecundario.getIdasuntoS());
            AsuntoPrincipal perteneceOld = persistentAsuntoSecundario.getPertenece();
            AsuntoPrincipal perteneceNew = asuntoSecundario.getPertenece();
            Collection<Ticket> ticketCollectionOld = persistentAsuntoSecundario.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = asuntoSecundario.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its asunto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (perteneceNew != null) {
                perteneceNew = em.getReference(perteneceNew.getClass(), perteneceNew.getIdasuntoP());
                asuntoSecundario.setPertenece(perteneceNew);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            asuntoSecundario.setTicketCollection(ticketCollectionNew);
            asuntoSecundario = em.merge(asuntoSecundario);
            if (perteneceOld != null && !perteneceOld.equals(perteneceNew)) {
                perteneceOld.getAsuntoSecundarioCollection().remove(asuntoSecundario);
                perteneceOld = em.merge(perteneceOld);
            }
            if (perteneceNew != null && !perteneceNew.equals(perteneceOld)) {
                perteneceNew.getAsuntoSecundarioCollection().add(asuntoSecundario);
                perteneceNew = em.merge(perteneceNew);
            }
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    AsuntoSecundario oldAsuntoOfTicketCollectionNewTicket = ticketCollectionNewTicket.getAsunto();
                    ticketCollectionNewTicket.setAsunto(asuntoSecundario);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldAsuntoOfTicketCollectionNewTicket != null && !oldAsuntoOfTicketCollectionNewTicket.equals(asuntoSecundario)) {
                        oldAsuntoOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldAsuntoOfTicketCollectionNewTicket = em.merge(oldAsuntoOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asuntoSecundario.getIdasuntoS();
                if (findAsuntoSecundario(id) == null) {
                    throw new NonexistentEntityException("The asuntoSecundario with id " + id + " no longer exists.");
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
            AsuntoSecundario asuntoSecundario;
            try {
                asuntoSecundario = em.getReference(AsuntoSecundario.class, id);
                asuntoSecundario.getIdasuntoS();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asuntoSecundario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = asuntoSecundario.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AsuntoSecundario (" + asuntoSecundario + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable asunto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            AsuntoPrincipal pertenece = asuntoSecundario.getPertenece();
            if (pertenece != null) {
                pertenece.getAsuntoSecundarioCollection().remove(asuntoSecundario);
                pertenece = em.merge(pertenece);
            }
            em.remove(asuntoSecundario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsuntoSecundario> findAsuntoSecundarioEntities() {
        return findAsuntoSecundarioEntities(true, -1, -1);
    }

    public List<AsuntoSecundario> findAsuntoSecundarioEntities(int maxResults, int firstResult) {
        return findAsuntoSecundarioEntities(false, maxResults, firstResult);
    }

    private List<AsuntoSecundario> findAsuntoSecundarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsuntoSecundario.class));
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

    public AsuntoSecundario findAsuntoSecundario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsuntoSecundario.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsuntoSecundarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsuntoSecundario> rt = cq.from(AsuntoSecundario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
