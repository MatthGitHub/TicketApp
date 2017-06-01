/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "asuntos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asuntos.findAll", query = "SELECT a FROM Asuntos a"),
    @NamedQuery(name = "Asuntos.findByIdasuntoP", query = "SELECT a FROM Asuntos a WHERE a.idasuntoP = :idasuntoP"),
    @NamedQuery(name = "Asuntos.findByNombre", query = "SELECT a FROM Asuntos a WHERE a.nombre = :nombre")})
public class Asuntos implements Serializable {

    @Basic(optional = false)
    @Column(name = "visible")
    private boolean visible;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asuntoP")
    private Integer idasuntoP;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pertenece")
    private List<Servicios> serviciosList;
    @JoinColumn(name = "fk_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Areas fkArea;

    public Asuntos() {
    }

    public Asuntos(Integer idasuntoP) {
        this.idasuntoP = idasuntoP;
    }

    public Asuntos(Integer idasuntoP, String nombre) {
        this.idasuntoP = idasuntoP;
        this.nombre = nombre;
    }

    public Integer getIdasuntoP() {
        return idasuntoP;
    }

    public void setIdasuntoP(Integer idasuntoP) {
        this.idasuntoP = idasuntoP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    public Areas getFkArea() {
        return fkArea;
    }

    public void setFkArea(Areas fkArea) {
        this.fkArea = fkArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasuntoP != null ? idasuntoP.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asuntos)) {
            return false;
        }
        Asuntos other = (Asuntos) object;
        if ((this.idasuntoP == null && other.idasuntoP != null) || (this.idasuntoP != null && !this.idasuntoP.equals(other.idasuntoP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    
}
