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
import mscb.tick.entidades.Ticket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.AreaSistemas;

/**
 *
 * @author Administrador
 */
public class AreaSistemasJpaController implements Serializable {

    public AreaSistemasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AreaSistemas areaSistemas) {
        if (areaSistemas.getTicketCollection() == null) {
            areaSistemas.setTicketCollection(new ArrayList<Ticket>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ticket> attachedTicketCollection = new ArrayList<Ticket>();
            for (Ticket ticketCollectionTicketToAttach : areaSistemas.getTicketCollection()) {
                ticketCollectionTicketToAttach = em.getReference(ticketCollectionTicketToAttach.getClass(), ticketCollectionTicketToAttach.getIdTicket());
                attachedTicketCollection.add(ticketCollectionTicketToAttach);
            }
            areaSistemas.setTicketCollection(attachedTicketCollection);
            em.persist(areaSistemas);
            for (Ticket ticketCollectionTicket : areaSistemas.getTicketCollection()) {
                AreaSistemas oldFkAreaSistemasOfTicketCollectionTicket = ticketCollectionTicket.getFkAreaSistemas();
                ticketCollectionTicket.setFkAreaSistemas(areaSistemas);
                ticketCollectionTicket = em.merge(ticketCollectionTicket);
                if (oldFkAreaSistemasOfTicketCollectionTicket != null) {
                    oldFkAreaSistemasOfTicketCollectionTicket.getTicketCollection().remove(ticketCollectionTicket);
                    oldFkAreaSistemasOfTicketCollectionTicket = em.merge(oldFkAreaSistemasOfTicketCollectionTicket);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AreaSistemas areaSistemas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AreaSistemas persistentAreaSistemas = em.find(AreaSistemas.class, areaSistemas.getIdAreaSistemas());
            Collection<Ticket> ticketCollectionOld = persistentAreaSistemas.getTicketCollection();
            Collection<Ticket> ticketCollectionNew = areaSistemas.getTicketCollection();
            List<String> illegalOrphanMessages = null;
            for (Ticket ticketCollectionOldTicket : ticketCollectionOld) {
                if (!ticketCollectionNew.contains(ticketCollectionOldTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ticket " + ticketCollectionOldTicket + " since its fkAreaSistemas field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ticket> attachedTicketCollectionNew = new ArrayList<Ticket>();
            for (Ticket ticketCollectionNewTicketToAttach : ticketCollectionNew) {
                ticketCollectionNewTicketToAttach = em.getReference(ticketCollectionNewTicketToAttach.getClass(), ticketCollectionNewTicketToAttach.getIdTicket());
                attachedTicketCollectionNew.add(ticketCollectionNewTicketToAttach);
            }
            ticketCollectionNew = attachedTicketCollectionNew;
            areaSistemas.setTicketCollection(ticketCollectionNew);
            areaSistemas = em.merge(areaSistemas);
            for (Ticket ticketCollectionNewTicket : ticketCollectionNew) {
                if (!ticketCollectionOld.contains(ticketCollectionNewTicket)) {
                    AreaSistemas oldFkAreaSistemasOfTicketCollectionNewTicket = ticketCollectionNewTicket.getFkAreaSistemas();
                    ticketCollectionNewTicket.setFkAreaSistemas(areaSistemas);
                    ticketCollectionNewTicket = em.merge(ticketCollectionNewTicket);
                    if (oldFkAreaSistemasOfTicketCollectionNewTicket != null && !oldFkAreaSistemasOfTicketCollectionNewTicket.equals(areaSistemas)) {
                        oldFkAreaSistemasOfTicketCollectionNewTicket.getTicketCollection().remove(ticketCollectionNewTicket);
                        oldFkAreaSistemasOfTicketCollectionNewTicket = em.merge(oldFkAreaSistemasOfTicketCollectionNewTicket);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areaSistemas.getIdAreaSistemas();
                if (findAreaSistemas(id) == null) {
                    throw new NonexistentEntityException("The areaSistemas with id " + id + " no longer exists.");
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
            AreaSistemas areaSistemas;
            try {
                areaSistemas = em.getReference(AreaSistemas.class, id);
                areaSistemas.getIdAreaSistemas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaSistemas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ticket> ticketCollectionOrphanCheck = areaSistemas.getTicketCollection();
            for (Ticket ticketCollectionOrphanCheckTicket : ticketCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AreaSistemas (" + areaSistemas + ") cannot be destroyed since the Ticket " + ticketCollectionOrphanCheckTicket + " in its ticketCollection field has a non-nullable fkAreaSistemas field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(areaSistemas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AreaSistemas> findAreaSistemasEntities() {
        return findAreaSistemasEntities(true, -1, -1);
    }

    public List<AreaSistemas> findAreaSistemasEntities(int maxResults, int firstResult) {
        return findAreaSistemasEntities(false, maxResults, firstResult);
    }

    private List<AreaSistemas> findAreaSistemasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AreaSistemas.class));
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

    public AreaSistemas findAreaSistemas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AreaSistemas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaSistemasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AreaSistemas> rt = cq.from(AreaSistemas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
