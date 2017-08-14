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
@Table(name = "servicios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Servicios.findByIdasuntoS", query = "SELECT s FROM Servicios s WHERE s.idasuntoS = :idasuntoS"),
    @NamedQuery(name = "Servicios.findByNombreasuntoS", query = "SELECT s FROM Servicios s WHERE s.nombreasuntoS = :nombreasuntoS")})
public class Servicios implements Serializable,Comparable<Servicios>{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asuntoS")
    private Integer idasuntoS;
    @Basic(optional = false)
    @Column(name = "nombre_asuntoS")
    private String nombreasuntoS;
    @ManyToMany(mappedBy = "serviciosList")
    private List<Usuarios> usuariosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "servicio")
    private List<Tickets> ticketsList;
    @JoinColumn(name = "pertenece", referencedColumnName = "id_asuntoP")
    @ManyToOne(optional = false)
    private Asuntos pertenece;

    public Servicios() {
    }

    public Servicios(Integer idasuntoS) {
        this.idasuntoS = idasuntoS;
    }

    public Servicios(Integer idasuntoS, String nombreasuntoS) {
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
    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @XmlTransient
    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    public Asuntos getPertenece() {
        return pertenece;
    }

    public void setPertenece(Asuntos pertenece) {
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
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idasuntoS == null && other.idasuntoS != null) || (this.idasuntoS != null && !this.idasuntoS.equals(other.idasuntoS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreasuntoS;
    }
    
    @Override
    public int compareTo(Servicios a) {
        if(nombreasuntoS.compareTo(a.nombreasuntoS) > 0){
            return -1;
        }
        if(nombreasuntoS.compareTo(a.nombreasuntoS) < 0){
            return 1;
        }
        return 0;
    }
}
