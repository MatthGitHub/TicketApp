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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "area_sistemas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaSistemas.findAll", query = "SELECT a FROM AreaSistemas a"),
    @NamedQuery(name = "AreaSistemas.findByIdAreaSistemas", query = "SELECT a FROM AreaSistemas a WHERE a.idAreaSistemas = :idAreaSistemas"),
    @NamedQuery(name = "AreaSistemas.findByNombreArea", query = "SELECT a FROM AreaSistemas a WHERE a.nombreArea = :nombreArea")})
public class AreaSistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area_sistemas")
    private Integer idAreaSistemas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_area")
    private String nombreArea;
    @OneToMany(mappedBy = "fkAreaSistemas")
    private List<Tickets> ticketsList;

    public AreaSistemas() {
    }

    public AreaSistemas(Integer idAreaSistemas) {
        this.idAreaSistemas = idAreaSistemas;
    }

    public AreaSistemas(Integer idAreaSistemas, String nombreArea) {
        this.idAreaSistemas = idAreaSistemas;
        this.nombreArea = nombreArea;
    }

    public Integer getIdAreaSistemas() {
        return idAreaSistemas;
    }

    public void setIdAreaSistemas(Integer idAreaSistemas) {
        this.idAreaSistemas = idAreaSistemas;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
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
        hash += (idAreaSistemas != null ? idAreaSistemas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AreaSistemas)) {
            return false;
        }
        AreaSistemas other = (AreaSistemas) object;
        if ((this.idAreaSistemas == null && other.idAreaSistemas != null) || (this.idAreaSistemas != null && !this.idAreaSistemas.equals(other.idAreaSistemas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mscb.tick.entidades.AreaSistemas[ idAreaSistemas=" + idAreaSistemas + " ]";
    }
    
}
