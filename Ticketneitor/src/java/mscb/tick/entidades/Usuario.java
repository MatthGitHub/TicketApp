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
import javax.persistence.JoinTable;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuario.findByContrasenia", query = "SELECT u FROM Usuario u WHERE u.contrasenia = :contrasenia"),
    @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo")})
public class Usuario implements Serializable {
    @JoinTable(name = "encargado_asunto", joinColumns = {
        @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "asunto", referencedColumnName = "id_asuntoS")})
    @ManyToMany
    private Collection<AsuntoSecundario> asuntoSecundarioCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Basic(optional = false)
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Basic(optional = false)
    @Column(name = "contrasenia")
    private String contrasenia;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioReceptor")
    private Collection<Ticket> ticketCollectionUsuarioReceptor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioEmisor")
    private Collection<Ticket> ticketCollectionUsuarioEmisor;
    @JoinColumn(name = "fk_permiso", referencedColumnName = "id_permiso")
    @ManyToOne(optional = false)
    private Permisos fkPermiso;
    @JoinColumn(name = "fk_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleado fkEmpleado;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String nombreUsuario, String contrasenia, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.activo = activo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollection() {
        return ticketCollectionUsuarioReceptor;
    }

    public void setTicketCollection(Collection<Ticket> ticketCollection) {
        this.ticketCollectionUsuarioReceptor = ticketCollection;
    }

    @XmlTransient
    public Collection<Ticket> getTicketCollectionUsuarioEmisor() {
        return ticketCollectionUsuarioEmisor;
    }

    public void setTicketCollectionUsuarioEmisor(Collection<Ticket> ticketCollectionUsuarioEmisor) {
        this.ticketCollectionUsuarioEmisor = ticketCollectionUsuarioEmisor;
    }

    public Permisos getFkPermiso() {
        return fkPermiso;
    }

    public void setFkPermiso(Permisos fkPermiso) {
        this.fkPermiso = fkPermiso;
    }

    public Empleado getFkEmpleado() {
        return fkEmpleado;
    }

    public void setFkEmpleado(Empleado fkEmpleado) {
        this.fkEmpleado = fkEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreUsuario;
    }

    @XmlTransient
    public Collection<AsuntoSecundario> getAsuntoSecundarioCollection() {
        return asuntoSecundarioCollection;
    }

    public void setAsuntoSecundarioCollection(Collection<AsuntoSecundario> asuntoSecundarioCollection) {
        this.asuntoSecundarioCollection = asuntoSecundarioCollection;
    }
    
}
