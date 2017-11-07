/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "tickets_adjuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TicketsAdjuntos.findAll", query = "SELECT t FROM TicketsAdjuntos t"),
    @NamedQuery(name = "TicketsAdjuntos.findByFkTicket", query = "SELECT t FROM TicketsAdjuntos t WHERE t.ticketsAdjuntosPK.fkTicket = :fkTicket"),
    @NamedQuery(name = "TicketsAdjuntos.findByAdjunto", query = "SELECT t FROM TicketsAdjuntos t WHERE t.ticketsAdjuntosPK.adjunto = :adjunto")})
public class TicketsAdjuntos implements Serializable {

    

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TicketsAdjuntosPK ticketsAdjuntosPK;
    @JoinColumn(name = "fk_ticket", referencedColumnName = "id_ticket", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tickets tickets;
    @Basic(optional = false)
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @Column(name = "mes")
    private int mes;
    
    
    public TicketsAdjuntos() {
    }

    public TicketsAdjuntos(TicketsAdjuntosPK ticketsAdjuntosPK) {
        this.ticketsAdjuntosPK = ticketsAdjuntosPK;
    }

    public TicketsAdjuntos(int fkTicket, String adjunto) {
        this.ticketsAdjuntosPK = new TicketsAdjuntosPK(fkTicket, adjunto);
    }

    public TicketsAdjuntosPK getTicketsAdjuntosPK() {
        return ticketsAdjuntosPK;
    }

    public void setTicketsAdjuntosPK(TicketsAdjuntosPK ticketsAdjuntosPK) {
        this.ticketsAdjuntosPK = ticketsAdjuntosPK;
    }

    public Tickets getTickets() {
        return tickets;
    }

    public void setTickets(Tickets tickets) {
        this.tickets = tickets;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketsAdjuntosPK != null ? ticketsAdjuntosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketsAdjuntos)) {
            return false;
        }
        TicketsAdjuntos other = (TicketsAdjuntos) object;
        if ((this.ticketsAdjuntosPK == null && other.ticketsAdjuntosPK != null) || (this.ticketsAdjuntosPK != null && !this.ticketsAdjuntosPK.equals(other.ticketsAdjuntosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mscb.tick.negocio.entidades.TicketsAdjuntos[ ticketsAdjuntosPK=" + ticketsAdjuntosPK + " ]";
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
}
