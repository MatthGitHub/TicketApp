/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrador
 */
@Embeddable
public class TicketsAdjuntosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "fk_ticket")
    private int fkTicket;
    @Basic(optional = false)
    @Column(name = "adjunto")
    private String adjunto;

    public TicketsAdjuntosPK() {
    }

    public TicketsAdjuntosPK(Integer fkTicket, String adjunto) {
        this.fkTicket = fkTicket;
        this.adjunto = adjunto;
    }

    public int getFkTicket() {
        return fkTicket;
    }

    public void setFkTicket(int fkTicket) {
        this.fkTicket = fkTicket;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) fkTicket;
        hash += (adjunto != null ? adjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketsAdjuntosPK)) {
            return false;
        }
        TicketsAdjuntosPK other = (TicketsAdjuntosPK) object;
        if (this.fkTicket != other.fkTicket) {
            return false;
        }
        if ((this.adjunto == null && other.adjunto != null) || (this.adjunto != null && !this.adjunto.equals(other.adjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mscb.tick.negocio.entidades.TicketsAdjuntosPK[ fkTicket=" + fkTicket + ", adjunto=" + adjunto + " ]";
    }
    
}
