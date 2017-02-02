/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "base_conocimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BaseConocimiento.findAll", query = "SELECT b FROM BaseConocimiento b"),
    @NamedQuery(name = "BaseConocimiento.findByIdResolucion", query = "SELECT b FROM BaseConocimiento b WHERE b.idResolucion = :idResolucion"),
    @NamedQuery(name = "BaseConocimiento.findByResolucion", query = "SELECT b FROM BaseConocimiento b WHERE b.resolucion = :resolucion"),
    @NamedQuery(name = "BaseConocimiento.findByFecha", query = "SELECT b FROM BaseConocimiento b WHERE b.fecha = :fecha")})
public class BaseConocimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_resolucion")
    private Integer idResolucion;
    @Basic(optional = false)
    @Column(name = "resolucion")
    private String resolucion;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "fk_ticket", referencedColumnName = "id_ticket")
    @ManyToOne(optional = false)
    private Tickets fkTicket;

    public BaseConocimiento() {
    }

    public BaseConocimiento(Integer idResolucion) {
        this.idResolucion = idResolucion;
    }

    public BaseConocimiento(Integer idResolucion, String resolucion, Date fecha) {
        this.idResolucion = idResolucion;
        this.resolucion = resolucion;
        this.fecha = fecha;
    }

    public Integer getIdResolucion() {
        return idResolucion;
    }

    public void setIdResolucion(Integer idResolucion) {
        this.idResolucion = idResolucion;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Tickets getFkTicket() {
        return fkTicket;
    }

    public void setFkTicket(Tickets fkTicket) {
        this.fkTicket = fkTicket;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResolucion != null ? idResolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BaseConocimiento)) {
            return false;
        }
        BaseConocimiento other = (BaseConocimiento) object;
        if ((this.idResolucion == null && other.idResolucion != null) || (this.idResolucion != null && !this.idResolucion.equals(other.idResolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.idResolucion.toString();
    }
    
}
