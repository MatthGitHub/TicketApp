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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "RESOLUCIONES_TIPOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResolucionesTipos.findAll", query = "SELECT r FROM ResolucionesTipos r"),
    @NamedQuery(name = "ResolucionesTipos.findByIdtiporesolucion", query = "SELECT r FROM ResolucionesTipos r WHERE r.idtiporesolucion = :idtiporesolucion"),
    @NamedQuery(name = "ResolucionesTipos.findByDescripcion", query = "SELECT r FROM ResolucionesTipos r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "ResolucionesTipos.findByActivo", query = "SELECT r FROM ResolucionesTipos r WHERE r.activo = :activo")})
public class ResolucionesTipos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id_tipo_resolucion")
    private Integer idtiporesolucion;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @Lob
    @Column(name = "Plantilla")
    private String plantilla;
    @Basic(optional = false)
    @Column(name = "Activo")
    private boolean activo;
    @OneToMany(mappedBy = "idtiporesolucion")
    private List<Resoluciones> resolucionesList;
    @OneToMany(mappedBy = "idtiporesolucion")
    private List<ResolucionesProyecto> resolucionesProyectoList;

    public ResolucionesTipos() {
    }

    public ResolucionesTipos(Integer idtiporesolucion) {
        this.idtiporesolucion = idtiporesolucion;
    }

    public ResolucionesTipos(Integer idtiporesolucion, String descripcion, boolean activo) {
        this.idtiporesolucion = idtiporesolucion;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public Integer getIdtiporesolucion() {
        return idtiporesolucion;
    }

    public void setIdtiporesolucion(Integer idtiporesolucion) {
        this.idtiporesolucion = idtiporesolucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Resoluciones> getResolucionesList() {
        return resolucionesList;
    }

    public void setResolucionesList(List<Resoluciones> resolucionesList) {
        this.resolucionesList = resolucionesList;
    }

    @XmlTransient
    public List<ResolucionesProyecto> getResolucionesProyectoList() {
        return resolucionesProyectoList;
    }

    public void setResolucionesProyectoList(List<ResolucionesProyecto> resolucionesProyectoList) {
        this.resolucionesProyectoList = resolucionesProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtiporesolucion != null ? idtiporesolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResolucionesTipos)) {
            return false;
        }
        ResolucionesTipos other = (ResolucionesTipos) object;
        if ((this.idtiporesolucion == null && other.idtiporesolucion != null) || (this.idtiporesolucion != null && !this.idtiporesolucion.equals(other.idtiporesolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mscb.tick.negocio.ResolucionesTipos[ idtiporesolucion=" + idtiporesolucion + " ]";
    }
    
}
