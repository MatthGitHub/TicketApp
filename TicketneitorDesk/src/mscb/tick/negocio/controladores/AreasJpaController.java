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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.exceptions.IllegalOrphanException;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Tickets;

/**
 *
 * @author Administrador
 */
public class AreasJpaController implements Serializable {

    public AreasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Areas areas) {
        if (areas.getEmpleadosList() == null) {
            areas.setEmpleadosList(new ArrayList<Empleados>());
        }
        if (areas.getAsuntosList() == null) {
            areas.setAsuntosList(new ArrayList<Asuntos>());
        }
        if (areas.getTicketsList() == null) {
            areas.setTicketsList(new ArrayList<Tickets>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleados> attachedEmpleadosList = new ArrayList<Empleados>();
            for (Empleados empleadosListEmpleadosToAttach : areas.getEmpleadosList()) {
                empleadosListEmpleadosToAttach = em.getReference(empleadosListEmpleadosToAttach.getClass(), empleadosListEmpleadosToAttach.getIdEmpleado());
                attachedEmpleadosList.add(empleadosListEmpleadosToAttach);
            }
            areas.setEmpleadosList(attachedEmpleadosList);
            List<Asuntos> attachedAsuntosList = new ArrayList<Asuntos>();
            for (Asuntos asuntosListAsuntosToAttach : areas.getAsuntosList()) {
                asuntosListAsuntosToAttach = em.getReference(asuntosListAsuntosToAttach.getClass(), asuntosListAsuntosToAttach.getIdasuntoP());
                attachedAsuntosList.add(asuntosListAsuntosToAttach);
            }
            areas.setAsuntosList(attachedAsuntosList);
            List<Tickets> attachedTicketsList = new ArrayList<Tickets>();
            for (Tickets ticketsListTicketsToAttach : areas.getTicketsList()) {
                ticketsListTicketsToAttach = em.getReference(ticketsListTicketsToAttach.getClass(), ticketsListTicketsToAttach.getIdTicket());
                attachedTicketsList.add(ticketsListTicketsToAttach);
            }
            areas.setTicketsList(attachedTicketsList);
            em.persist(areas);
            for (Empleados empleadosListEmpleados : areas.getEmpleadosList()) {
                Areas oldFkAreaOfEmpleadosListEmpleados = empleadosListEmpleados.getFkArea();
                empleadosListEmpleados.setFkArea(areas);
                empleadosListEmpleados = em.merge(empleadosListEmpleados);
                if (oldFkAreaOfEmpleadosListEmpleados != null) {
                    oldFkAreaOfEmpleadosListEmpleados.getEmpleadosList().remove(empleadosListEmpleados);
                    oldFkAreaOfEmpleadosListEmpleados = em.merge(oldFkAreaOfEmpleadosListEmpleados);
                }
            }
            for (Asuntos asuntosListAsuntos : areas.getAsuntosList()) {
                Areas oldFkAreaOfAsuntosListAsuntos = asuntosListAsuntos.getFkArea();
                asuntosListAsuntos.setFkArea(areas);
                asuntosListAsuntos = em.merge(asuntosListAsuntos);
                if (oldFkAreaOfAsuntosListAsuntos != null) {
                    oldFkAreaOfAsuntosListAsuntos.getAsuntosList().remove(asuntosListAsuntos);
                    oldFkAreaOfAsuntosListAsuntos = em.merge(oldFkAreaOfAsuntosListAsuntos);
                }
            }
            for (Tickets ticketsListTickets : areas.getTicketsList()) {
                Areas oldFkareaSolicitanteOfTicketsListTickets = ticketsListTickets.getFkareaSolicitante();
                ticketsListTickets.setFkareaSolicitante(areas);
                ticketsListTickets = em.merge(ticketsListTickets);
                if (oldFkareaSolicitanteOfTicketsListTickets != null) {
                    oldFkareaSolicitanteOfTicketsListTickets.getTicketsList().remove(ticketsListTickets);
                    oldFkareaSolicitanteOfTicketsListTickets = em.merge(oldFkareaSolicitanteOfTicketsListTickets);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Areas areas) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areas persistentAreas = em.find(Areas.class, areas.getIdArea());
            List<Empleados> empleadosListOld = persistentAreas.getEmpleadosList();
            List<Empleados> empleadosListNew = areas.getEmpleadosList();
            List<Asuntos> asuntosListOld = persistentAreas.getAsuntosList();
            List<Asuntos> asuntosListNew = areas.getAsuntosList();
            List<Tickets> ticketsListOld = persistentAreas.getTicketsList();
            List<Tickets> ticketsListNew = areas.getTicketsList();
            List<String> illegalOrphanMessages = null;
            for (Empleados empleadosListOldEmpleados : empleadosListOld) {
                if (!empleadosListNew.contains(empleadosListOldEmpleados)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleados " + empleadosListOldEmpleados + " since its fkArea field is not nullable.");
                }
            }
            for (Asuntos asuntosListOldAsuntos : asuntosListOld) {
                if (!asuntosListNew.contains(asuntosListOldAsuntos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asuntos " + asuntosListOldAsuntos + " since its fkArea field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleados> attachedEmpleadosListNew = new ArrayList<Empleados>();
            for (Empleados empleadosListNewEmpleadosToAttach : empleadosListNew) {
                empleadosListNewEmpleadosToAttach = em.getReference(empleadosListNewEmpleadosToAttach.getClass(), empleadosListNewEmpleadosToAttach.getIdEmpleado());
                attachedEmpleadosListNew.add(empleadosListNewEmpleadosToAttach);
            }
            empleadosListNew = attachedEmpleadosListNew;
            areas.setEmpleadosList(empleadosListNew);
            List<Asuntos> attachedAsuntosListNew = new ArrayList<Asuntos>();
            for (Asuntos asuntosListNewAsuntosToAttach : asuntosListNew) {
                asuntosListNewAsuntosToAttach = em.getReference(asuntosListNewAsuntosToAttach.getClass(), asuntosListNewAsuntosToAttach.getIdasuntoP());
                attachedAsuntosListNew.add(asuntosListNewAsuntosToAttach);
            }
            asuntosListNew = attachedAsuntosListNew;
            areas.setAsuntosList(asuntosListNew);
            List<Tickets> attachedTicketsListNew = new ArrayList<Tickets>();
            for (Tickets ticketsListNewTicketsToAttach : ticketsListNew) {
                ticketsListNewTicketsToAttach = em.getReference(ticketsListNewTicketsToAttach.getClass(), ticketsListNewTicketsToAttach.getIdTicket());
                attachedTicketsListNew.add(ticketsListNewTicketsToAttach);
            }
            ticketsListNew = attachedTicketsListNew;
            areas.setTicketsList(ticketsListNew);
            areas = em.merge(areas);
            for (Empleados empleadosListNewEmpleados : empleadosListNew) {
                if (!empleadosListOld.contains(empleadosListNewEmpleados)) {
                    Areas oldFkAreaOfEmpleadosListNewEmpleados = empleadosListNewEmpleados.getFkArea();
                    empleadosListNewEmpleados.setFkArea(areas);
                    empleadosListNewEmpleados = em.merge(empleadosListNewEmpleados);
                    if (oldFkAreaOfEmpleadosListNewEmpleados != null && !oldFkAreaOfEmpleadosListNewEmpleados.equals(areas)) {
                        oldFkAreaOfEmpleadosListNewEmpleados.getEmpleadosList().remove(empleadosListNewEmpleados);
                        oldFkAreaOfEmpleadosListNewEmpleados = em.merge(oldFkAreaOfEmpleadosListNewEmpleados);
                    }
                }
            }
            for (Asuntos asuntosListNewAsuntos : asuntosListNew) {
                if (!asuntosListOld.contains(asuntosListNewAsuntos)) {
                    Areas oldFkAreaOfAsuntosListNewAsuntos = asuntosListNewAsuntos.getFkArea();
                    asuntosListNewAsuntos.setFkArea(areas);
                    asuntosListNewAsuntos = em.merge(asuntosListNewAsuntos);
                    if (oldFkAreaOfAsuntosListNewAsuntos != null && !oldFkAreaOfAsuntosListNewAsuntos.equals(areas)) {
                        oldFkAreaOfAsuntosListNewAsuntos.getAsuntosList().remove(asuntosListNewAsuntos);
                        oldFkAreaOfAsuntosListNewAsuntos = em.merge(oldFkAreaOfAsuntosListNewAsuntos);
                    }
                }
            }
            for (Tickets ticketsListOldTickets : ticketsListOld) {
                if (!ticketsListNew.contains(ticketsListOldTickets)) {
                    ticketsListOldTickets.setFkareaSolicitante(null);
                    ticketsListOldTickets = em.merge(ticketsListOldTickets);
                }
            }
            for (Tickets ticketsListNewTickets : ticketsListNew) {
                if (!ticketsListOld.contains(ticketsListNewTickets)) {
                    Areas oldFkareaSolicitanteOfTicketsListNewTickets = ticketsListNewTickets.getFkareaSolicitante();
                    ticketsListNewTickets.setFkareaSolicitante(areas);
                    ticketsListNewTickets = em.merge(ticketsListNewTickets);
                    if (oldFkareaSolicitanteOfTicketsListNewTickets != null && !oldFkareaSolicitanteOfTicketsListNewTickets.equals(areas)) {
                        oldFkareaSolicitanteOfTicketsListNewTickets.getTicketsList().remove(ticketsListNewTickets);
                        oldFkareaSolicitanteOfTicketsListNewTickets = em.merge(oldFkareaSolicitanteOfTicketsListNewTickets);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areas.getIdArea();
                if (findAreas(id) == null) {
                    throw new NonexistentEntityException("The areas with id " + id + " no longer exists.");
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
            Areas areas;
            try {
                areas = em.getReference(Areas.class, id);
                areas.getIdArea();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Empleados> empleadosListOrphanCheck = areas.getEmpleadosList();
            for (Empleados empleadosListOrphanCheckEmpleados : empleadosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Areas (" + areas + ") cannot be destroyed since the Empleados " + empleadosListOrphanCheckEmpleados + " in its empleadosList field has a non-nullable fkArea field.");
            }
            List<Asuntos> asuntosListOrphanCheck = areas.getAsuntosList();
            for (Asuntos asuntosListOrphanCheckAsuntos : asuntosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Areas (" + areas + ") cannot be destroyed since the Asuntos " + asuntosListOrphanCheckAsuntos + " in its asuntosList field has a non-nullable fkArea field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Tickets> ticketsList = areas.getTicketsList();
            for (Tickets ticketsListTickets : ticketsList) {
                ticketsListTickets.setFkareaSolicitante(null);
                ticketsListTickets = em.merge(ticketsListTickets);
            }
            em.remove(areas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Areas> findAreasEntities() {
        return findAreasEntities(true, -1, -1);
    }

    public List<Areas> findAreasEntities(int maxResults, int firstResult) {
        return findAreasEntities(false, maxResults, firstResult);
    }

    private List<Areas> findAreasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areas.class));
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

    public Areas findAreas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Areas.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Areas> rt = cq.from(Areas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
