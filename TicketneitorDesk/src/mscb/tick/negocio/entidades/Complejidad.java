/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

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
@Table(name = "complejidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Complejidad.findAll", query = "SELECT c FROM Complejidad c"),
    @NamedQuery(name = "Complejidad.findByIdComplejidad", query = "SELECT c FROM Complejidad c WHERE c.idComplejidad = :idComplejidad"),
    @NamedQuery(name = "Complejidad.findByDescripcion", query = "SELECT c FROM Complejidad c WHERE c.descripcion = :descripcion")})
public class Complejidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_complejidad")
    private Integer idComplejidad;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "fkComplejidad")
    private List<Tickets> ticketsList;

    public Complejidad() {
    }

    public Complejidad(Integer idComplejidad) {
        this.idComplejidad = idComplejidad;
    }

    public Complejidad(Integer idComplejidad, String descripcion) {
        this.idComplejidad = idComplejidad;
        this.descripcion = descripcion;
    }

    public Integer getIdComplejidad() {
        return idComplejidad;
    }

    public void setIdComplejidad(Integer idComplejidad) {
        this.idComplejidad = idComplejidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idComplejidad != null ? idComplejidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complejidad)) {
            return false;
        }
        Complejidad other = (Complejidad) object;
        if ((this.idComplejidad == null && other.idComplejidad != null) || (this.idComplejidad != null && !this.idComplejidad.equals(other.idComplejidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
