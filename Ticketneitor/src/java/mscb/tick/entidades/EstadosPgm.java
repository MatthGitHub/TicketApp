/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "estados_pgm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosPgm.findAll", query = "SELECT e FROM EstadosPgm e"),
    @NamedQuery(name = "EstadosPgm.findByIdEstado", query = "SELECT e FROM EstadosPgm e WHERE e.idEstado = :idEstado"),
    @NamedQuery(name = "EstadosPgm.findByEstado", query = "SELECT e FROM EstadosPgm e WHERE e.estado = :estado")})
public class EstadosPgm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado")
    private Integer idEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "estado")
    private String estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkEstadoPgm")
    private List<EstadoActualPgm> estadoActualPgmList;

    public EstadosPgm() {
    }

    public EstadosPgm(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public EstadosPgm(Integer idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<EstadoActualPgm> getEstadoActualPgmList() {
        return estadoActualPgmList;
    }

    public void setEstadoActualPgmList(List<EstadoActualPgm> estadoActualPgmList) {
        this.estadoActualPgmList = estadoActualPgmList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadosPgm)) {
            return false;
        }
        EstadosPgm other = (EstadosPgm) object;
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mscb.tick.entidades.EstadosPgm[ idEstado=" + idEstado + " ]";
    }
    
}
