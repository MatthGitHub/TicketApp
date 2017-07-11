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
@Table(name = "edificios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Edificios.findAll", query = "SELECT e FROM Edificios e"),
    @NamedQuery(name = "Edificios.findByIdEdificio", query = "SELECT e FROM Edificios e WHERE e.idEdificio = :idEdificio"),
    @NamedQuery(name = "Edificios.findByNombre", query = "SELECT e FROM Edificios e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Edificios.findByDireccion", query = "SELECT e FROM Edificios e WHERE e.direccion = :direccion")})
public class Edificios implements Serializable {

    @OneToMany(mappedBy = "fkEdificio")
    private List<Tickets> ticketsList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_edificio")
    private Integer idEdificio;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;

    public Edificios() {
    }

    public Edificios(Integer idEdificio) {
        this.idEdificio = idEdificio;
    }

    public Edificios(Integer idEdificio, String nombre, String direccion) {
        this.idEdificio = idEdificio;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Integer getIdEdificio() {
        return idEdificio;
    }

    public void setIdEdificio(Integer idEdificio) {
        this.idEdificio = idEdificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEdificio != null ? idEdificio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Edificios)) {
            return false;
        }
        Edificios other = (Edificios) object;
        if ((this.idEdificio == null && other.idEdificio != null) || (this.idEdificio != null && !this.idEdificio.equals(other.idEdificio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

    @XmlTransient
    public List<Tickets> getTicketsList() {
        return ticketsList;
    }

    public void setTicketsList(List<Tickets> ticketsList) {
        this.ticketsList = ticketsList;
    }
    
}
