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
import mscb.tick.entidades.AsuntoSecundario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.AsuntoPrincipal;

/**
 *
 * @author Administrador
 */
public class AsuntoPrincipalJpaController implements Serializable {

    public AsuntoPrincipalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AsuntoPrincipal asuntoPrincipal) {
        if (asuntoPrincipal.getAsuntoSecundarioCollection() == null) {
            asuntoPrincipal.setAsuntoSecundarioCollection(new ArrayList<AsuntoSecundario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<AsuntoSecundario> attachedAsuntoSecundarioCollection = new ArrayList<AsuntoSecundario>();
            for (AsuntoSecundario asuntoSecundarioCollectionAsuntoSecundarioToAttach : asuntoPrincipal.getAsuntoSecundarioCollection()) {
                asuntoSecundarioCollectionAsuntoSecundarioToAttach = em.getReference(asuntoSecundarioCollectionAsuntoSecundarioToAttach.getClass(), asuntoSecundarioCollectionAsuntoSecundarioToAttach.getIdasuntoS());
                attachedAsuntoSecundarioCollection.add(asuntoSecundarioCollectionAsuntoSecundarioToAttach);
            }
            asuntoPrincipal.setAsuntoSecundarioCollection(attachedAsuntoSecundarioCollection);
            em.persist(asuntoPrincipal);
            for (AsuntoSecundario asuntoSecundarioCollectionAsuntoSecundario : asuntoPrincipal.getAsuntoSecundarioCollection()) {
                AsuntoPrincipal oldPerteneceOfAsuntoSecundarioCollectionAsuntoSecundario = asuntoSecundarioCollectionAsuntoSecundario.getPertenece();
                asuntoSecundarioCollectionAsuntoSecundario.setPertenece(asuntoPrincipal);
                asuntoSecundarioCollectionAsuntoSecundario = em.merge(asuntoSecundarioCollectionAsuntoSecundario);
                if (oldPerteneceOfAsuntoSecundarioCollectionAsuntoSecundario != null) {
                    oldPerteneceOfAsuntoSecundarioCollectionAsuntoSecundario.getAsuntoSecundarioCollection().remove(asuntoSecundarioCollectionAsuntoSecundario);
                    oldPerteneceOfAsuntoSecundarioCollectionAsuntoSecundario = em.merge(oldPerteneceOfAsuntoSecundarioCollectionAsuntoSecundario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AsuntoPrincipal asuntoPrincipal) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AsuntoPrincipal persistentAsuntoPrincipal = em.find(AsuntoPrincipal.class, asuntoPrincipal.getIdasuntoP());
            Collection<AsuntoSecundario> asuntoSecundarioCollectionOld = persistentAsuntoPrincipal.getAsuntoSecundarioCollection();
            Collection<AsuntoSecundario> asuntoSecundarioCollectionNew = asuntoPrincipal.getAsuntoSecundarioCollection();
            List<String> illegalOrphanMessages = null;
            for (AsuntoSecundario asuntoSecundarioCollectionOldAsuntoSecundario : asuntoSecundarioCollectionOld) {
                if (!asuntoSecundarioCollectionNew.contains(asuntoSecundarioCollectionOldAsuntoSecundario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AsuntoSecundario " + asuntoSecundarioCollectionOldAsuntoSecundario + " since its pertenece field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<AsuntoSecundario> attachedAsuntoSecundarioCollectionNew = new ArrayList<AsuntoSecundario>();
            for (AsuntoSecundario asuntoSecundarioCollectionNewAsuntoSecundarioToAttach : asuntoSecundarioCollectionNew) {
                asuntoSecundarioCollectionNewAsuntoSecundarioToAttach = em.getReference(asuntoSecundarioCollectionNewAsuntoSecundarioToAttach.getClass(), asuntoSecundarioCollectionNewAsuntoSecundarioToAttach.getIdasuntoS());
                attachedAsuntoSecundarioCollectionNew.add(asuntoSecundarioCollectionNewAsuntoSecundarioToAttach);
            }
            asuntoSecundarioCollectionNew = attachedAsuntoSecundarioCollectionNew;
            asuntoPrincipal.setAsuntoSecundarioCollection(asuntoSecundarioCollectionNew);
            asuntoPrincipal = em.merge(asuntoPrincipal);
            for (AsuntoSecundario asuntoSecundarioCollectionNewAsuntoSecundario : asuntoSecundarioCollectionNew) {
                if (!asuntoSecundarioCollectionOld.contains(asuntoSecundarioCollectionNewAsuntoSecundario)) {
                    AsuntoPrincipal oldPerteneceOfAsuntoSecundarioCollectionNewAsuntoSecundario = asuntoSecundarioCollectionNewAsuntoSecundario.getPertenece();
                    asuntoSecundarioCollectionNewAsuntoSecundario.setPertenece(asuntoPrincipal);
                    asuntoSecundarioCollectionNewAsuntoSecundario = em.merge(asuntoSecundarioCollectionNewAsuntoSecundario);
                    if (oldPerteneceOfAsuntoSecundarioCollectionNewAsuntoSecundario != null && !oldPerteneceOfAsuntoSecundarioCollectionNewAsuntoSecundario.equals(asuntoPrincipal)) {
                        oldPerteneceOfAsuntoSecundarioCollectionNewAsuntoSecundario.getAsuntoSecundarioCollection().remove(asuntoSecundarioCollectionNewAsuntoSecundario);
                        oldPerteneceOfAsuntoSecundarioCollectionNewAsuntoSecundario = em.merge(oldPerteneceOfAsuntoSecundarioCollectionNewAsuntoSecundario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asuntoPrincipal.getIdasuntoP();
                if (findAsuntoPrincipal(id) == null) {
                    throw new NonexistentEntityException("The asuntoPrincipal with id " + id + " no longer exists.");
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
            AsuntoPrincipal asuntoPrincipal;
            try {
                asuntoPrincipal = em.getReference(AsuntoPrincipal.class, id);
                asuntoPrincipal.getIdasuntoP();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asuntoPrincipal with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<AsuntoSecundario> asuntoSecundarioCollectionOrphanCheck = asuntoPrincipal.getAsuntoSecundarioCollection();
            for (AsuntoSecundario asuntoSecundarioCollectionOrphanCheckAsuntoSecundario : asuntoSecundarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AsuntoPrincipal (" + asuntoPrincipal + ") cannot be destroyed since the AsuntoSecundario " + asuntoSecundarioCollectionOrphanCheckAsuntoSecundario + " in its asuntoSecundarioCollection field has a non-nullable pertenece field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(asuntoPrincipal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AsuntoPrincipal> findAsuntoPrincipalEntities() {
        return findAsuntoPrincipalEntities(true, -1, -1);
    }

    public List<AsuntoPrincipal> findAsuntoPrincipalEntities(int maxResults, int firstResult) {
        return findAsuntoPrincipalEntities(false, maxResults, firstResult);
    }

    private List<AsuntoPrincipal> findAsuntoPrincipalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AsuntoPrincipal.class));
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

    public AsuntoPrincipal findAsuntoPrincipal(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AsuntoPrincipal.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsuntoPrincipalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AsuntoPrincipal> rt = cq.from(AsuntoPrincipal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
