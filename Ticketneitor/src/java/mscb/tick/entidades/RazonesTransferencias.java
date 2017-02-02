/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "razones_transferencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RazonesTransferencias.findAll", query = "SELECT r FROM RazonesTransferencias r"),
    @NamedQuery(name = "RazonesTransferencias.findByIdRazon", query = "SELECT r FROM RazonesTransferencias r WHERE r.idRazon = :idRazon"),
    @NamedQuery(name = "RazonesTransferencias.findByNombreRazon", query = "SELECT r FROM RazonesTransferencias r WHERE r.nombreRazon = :nombreRazon")})
public class RazonesTransferencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_razon")
    private Integer idRazon;
    @Basic(optional = false)
    @Column(name = "nombre_razon")
    private String nombreRazon;
    @OneToMany(mappedBy = "fkRazon")
    private List<Tickets> ticketsList;

    public RazonesTransferencias() {
    }

    public RazonesTransferencias(Integer idRazon) {
        this.idRazon = idRazon;
    }

    public RazonesTransferencias(Integer idRazon, String nombreRazon) {
        this.idRazon = idRazon;
        this.nombreRazon = nombreRazon;
    }

    public Integer getIdRazon() {
        return idRazon;
    }

    public void setIdRazon(Integer idRazon) {
        this.idRazon = idRazon;
    }

    public String getNombreRazon() {
        return nombreRazon;
    }

    public void setNombreRazon(String nombreRazon) {
        this.nombreRazon = nombreRazon;
    }

    @XmlTransient
    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRazon != null ? idRazon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RazonesTransferencias)) {
            return false;
        }
        RazonesTransferencias other = (RazonesTransferencias) object;
        if ((this.idRazon == null && other.idRazon != null) || (this.idRazon != null && !this.idRazon.equals(other.idRazon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreRazon;
    }
    
}
