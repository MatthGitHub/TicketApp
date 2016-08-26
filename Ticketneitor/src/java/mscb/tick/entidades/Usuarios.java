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
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByIdUsuario", query = "SELECT u FROM Usuarios u WHERE u.idUsuario = :idUsuario"),
    @NamedQuery(name = "Usuarios.findByNombreUsuario", query = "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "Usuarios.findByContrasenia", query = "SELECT u FROM Usuarios u WHERE u.contrasenia = :contrasenia"),
    @NamedQuery(name = "Usuarios.findByActivo", query = "SELECT u FROM Usuarios u WHERE u.activo = :activo"),
    @NamedQuery(name = "Usuarios.findByIdExtreme", query = "SELECT u FROM Usuarios u WHERE u.idExtreme = :idExtreme")})
public class Usuarios implements Serializable {

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
    @Column(name = "id_extreme")
    private String idExtreme;
    @JoinTable(name = "encargado_servicios", joinColumns = {
        @JoinColumn(name = "usuario", referencedColumnName = "id_usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "asunto", referencedColumnName = "id_asuntoS")})
    @ManyToMany
    private List<Servicios> serviciosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioEmisor")
    private List<Tickets> ticketsList;
    @OneToMany(mappedBy = "usuarioReceptor")
    private List<Tickets> ticketsList1;
    @JoinColumn(name = "fk_empleado", referencedColumnName = "id_empleado")
    @ManyToOne(optional = false)
    private Empleados fkEmpleado;
    @JoinColumn(name = "fk_permiso", referencedColumnName = "id_permiso")
    @ManyToOne(optional = false)
    private Permisos fkPermiso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioReceptor")
    private List<HistorialTickets> historialTicketsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsuarioEmisor")
    private List<HistorialTickets> historialTicketsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario")
    private List<Respuestas> respuestasList;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String nombreUsuario, String contrasenia, boolean activo) {
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

    public String getIdExtreme() {
        return idExtreme;
    }

    public void setIdExtreme(String idExtreme) {
        this.idExtreme = idExtreme;
    }

    @XmlTransient
    public List<Servicios> getServiciosList() {
        return serviciosList;
    }

    public void setServiciosList(List<Servicios> serviciosList) {
        this.serviciosList = serviciosList;
    }

    @XmlTransient
    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }

    @XmlTransient
    public List<Tickets> getTicketsList1() {
        return ticketsList1;
    }

    public void setTicketsList1(List<Tickets> ticketsList1) {
        this.ticketsList1 = ticketsList1;
    }

    public Empleados getFkEmpleado() {
        return fkEmpleado;
    }

    public void setFkEmpleado(Empleados fkEmpleado) {
        this.fkEmpleado = fkEmpleado;
    }

    public Permisos getFkPermiso() {
        return fkPermiso;
    }

    public void setFkPermiso(Permisos fkPermiso) {
        this.fkPermiso = fkPermiso;
    }

    @XmlTransient
    public List<HistorialTickets> getHistorialTicketsList() {
        return historialTicketsList;
    }

    public void setHistorialTicketsList(List<HistorialTickets> historialTicketsList) {
        this.historialTicketsList = historialTicketsList;
    }

    @XmlTransient
    public List<HistorialTickets> getHistorialTicketsList1() {
        return historialTicketsList1;
    }

    public void setHistorialTicketsList1(List<HistorialTickets> historialTicketsList1) {
        this.historialTicketsList1 = historialTicketsList1;
    }

    @XmlTransient
    public List<Respuestas> getRespuestasList() {
        return respuestasList;
    }

    public void setRespuestasList(List<Respuestas> respuestasList) {
        this.respuestasList = respuestasList;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreUsuario;
    }
    
}
