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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
@Table(name = "asunto_secundario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsuntoSecundario.findAll", query = "SELECT a FROM AsuntoSecundario a"),
    @NamedQuery(name = "AsuntoSecundario.findByIdasuntoS", query = "SELECT a FROM AsuntoSecundario a WHERE a.idasuntoS = :idasuntoS"),
    @NamedQuery(name = "AsuntoSecundario.findByNombreasuntoS", query = "SELECT a FROM AsuntoSecundario a WHERE a.nombreasuntoS = :nombreasuntoS")})
public class AsuntoSecundario implements Serializable {
    @ManyToMany(mappedBy = "asuntoSecundarioCollection")
    private Collection<Usuario> usuarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asuntoS")
    private Integer idasuntoS;
    @Basic(optional = false)
    @Column(name = "nombre_asuntoS")
    private String nombreasuntoS;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asunto")
    private Collection<Ticket> ticketCollection;
    @JoinColumn(name = "pertenece", referencedColumnName = "id_asuntoP")
    @ManyToOne(optional = false)
    private AsuntoPrincipal pertenece;

    public AsuntoSecundario() {
    }

    public AsuntoSecundario(Integer idasuntoS) {
        this.idasuntoS = idasuntoS;
    }

    public AsuntoSecundario(Integer idasuntoS, String nombreasuntoS) {
        this.idasuntoS = idasuntoS;
        this.nombreasuntoS = nombreasuntoS;
    }

    public Integer getIdasuntoS() {
        return idasuntoS;
    }

    public void setIdasuntoS(Integer idasuntoS) {
        this.idasuntoS = idasuntoS;
    }

    public String getNombreasuntoS() {
        return nombreasuntoS;
    }

    public void setNombreasuntoS(String nombreasuntoS) {
        this.nombreasuntoS = nombreasuntoS;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollection;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollection = ticketCollection;
    }

    public AsuntoPrincipal getPertenece() {
        return pertenece;
    }

    public void setPertenece(AsuntoPrincipal pertenece) {
        this.pertenece = pertenece;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idasuntoS != null ? idasuntoS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsuntoSecundario)) {
            return false;
        }
        AsuntoSecundario other = (AsuntoSecundario) object;
        if ((this.idasuntoS == null && other.idasuntoS != null) || (this.idasuntoS != null && !this.idasuntoS.equals(other.idasuntoS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreasuntoS;
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }
    
}
