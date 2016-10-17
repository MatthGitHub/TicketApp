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
@Table(name = "areas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Areas.findAll", query = "SELECT a FROM Areas a"),
    @NamedQuery(name = "Areas.findByIdArea", query = "SELECT a FROM Areas a WHERE a.idArea = :idArea"),
    @NamedQuery(name = "Areas.findByNombreArea", query = "SELECT a FROM Areas a WHERE a.nombreArea = :nombreArea"),
    @NamedQuery(name = "Areas.findByDireccion", query = "SELECT a FROM Areas a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Areas.findByCorreo", query = "SELECT a FROM Areas a WHERE a.correo = :correo")})
public class Areas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_area")
    private Integer idArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_area")
    private String nombreArea;
    @Size(max = 30)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 45)
    @Column(name = "correo")
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArea")
    private List<Asuntos> asuntosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAreaEmisor")
    private List<Tickets> ticketsList;
    @OneToMany(mappedBy = "fkAreaReceptor")
    private List<Tickets> ticketsList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkArea")
    private List<Empleados> empleadosList;

    public Areas() {
    }

    public Areas(Integer idArea) {
        this.idArea = idArea;
    }

    public Areas(Integer idArea, String nombreArea) {
        this.idArea = idArea;
        this.nombreArea = nombreArea;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @XmlTransient
    public List<Asuntos> getAsuntosList() {
        return asuntosList;
    }

    public void setAsuntosList(List<Asuntos> asuntosList) {
        this.asuntosList = asuntosList;
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

    @XmlTransient
    public List<Empleados> getEmpleadosList() {
        return empleadosList;
    }

    public void setEmpleadosList(List<Empleados> empleadosList) {
        this.empleadosList = empleadosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArea != null ? idArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Areas)) {
            return false;
        }
        Areas other = (Areas) object;
        if ((this.idArea == null && other.idArea != null) || (this.idArea != null && !this.idArea.equals(other.idArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNombreArea();
    }
    
}
