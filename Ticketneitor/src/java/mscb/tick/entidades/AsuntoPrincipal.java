/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.entidades;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "asunto_principal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsuntoPrincipal.findAll", query = "SELECT a FROM AsuntoPrincipal a"),
    @NamedQuery(name = "AsuntoPrincipal.findByIdasuntoP", query = "SELECT a FROM AsuntoPrincipal a WHERE a.idasuntoP = :idasuntoP"),
    @NamedQuery(name = "AsuntoPrincipal.findByNombre", query = "SELECT a FROM AsuntoPrincipal a WHERE a.nombre = :nombre")})
public class AsuntoPrincipal implements Serializable {
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
    private Collection<AsuntoSecundario> asuntoSecundarioCollection;

    public AsuntoPrincipal() {
    }

    public AsuntoPrincipal(Integer idasuntoP) {
        this.idasuntoP = idasuntoP;
    }

    public AsuntoPrincipal(Integer idasuntoP, String nombre) {
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
    public Collection<AsuntoSecundario> getAsuntoSecundarioCollection() {
        return asuntoSecundarioCollection;
    }

    public void setAsuntoSecundarioCollection(Collection<AsuntoSecundario> asuntoSecundarioCollection) {
        this.asuntoSecundarioCollection = asuntoSecundarioCollection;
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
        if (!(object instanceof AsuntoPrincipal)) {
            return false;
        }
        AsuntoPrincipal other = (AsuntoPrincipal) object;
        if ((this.idasuntoP == null && other.idasuntoP != null) || (this.idasuntoP != null && !this.idasuntoP.equals(other.idasuntoP))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
    
}
