/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "estado_actual_pgm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoActualPgm.findAll", query = "SELECT e FROM EstadoActualPgm e"),
    @NamedQuery(name = "EstadoActualPgm.findById", query = "SELECT e FROM EstadoActualPgm e WHERE e.id = :id"),
    @NamedQuery(name = "EstadoActualPgm.findByFecha", query = "SELECT e FROM EstadoActualPgm e WHERE e.fecha = :fecha")})
public class EstadoActualPgm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "fk_estado_pgm", referencedColumnName = "id_estado")
    @ManyToOne(optional = false)
    private EstadosPgm fkEstadoPgm;

    public EstadoActualPgm() {
    }

    public EstadoActualPgm(Integer id) {
        this.id = id;
    }

    public EstadoActualPgm(Integer id, Date fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadosPgm getFkEstadoPgm() {
        return fkEstadoPgm;
    }

    public void setFkEstadoPgm(EstadosPgm fkEstadoPgm) {
        this.fkEstadoPgm = fkEstadoPgm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoActualPgm)) {
            return false;
        }
        EstadoActualPgm other = (EstadoActualPgm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getFkEstadoPgm().getEstado();
    }
    
}
