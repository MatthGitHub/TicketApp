/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "historial_tickets")
@NamedQueries({
    @NamedQuery(name = "HistorialTickets.findAll", query = "SELECT h FROM HistorialTickets h"),
    @NamedQuery(name = "HistorialTickets.findByIdHistorial", query = "SELECT h FROM HistorialTickets h WHERE h.idHistorial = :idHistorial"),
    @NamedQuery(name = "HistorialTickets.findByFecha", query = "SELECT h FROM HistorialTickets h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HistorialTickets.findByResolucion", query = "SELECT h FROM HistorialTickets h WHERE h.resolucion = :resolucion")})
public class HistorialTickets implements Serializable, Comparable<HistorialTickets>{

    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial")
    private Integer idHistorial;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "resolucion")
    private String resolucion;
    @JoinColumn(name = "fk_ticket", referencedColumnName = "id_ticket")
    @ManyToOne(optional = false)
    private Tickets fkTicket;
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios fkUsuario;
    @JoinColumn(name = "fk_estado", referencedColumnName = "id_estado")
    @ManyToOne
    private Estados fkEstado;
    @JoinColumn(name = "fk_razon", referencedColumnName = "id_razon")
    @ManyToOne
    private RazonesTransferencias fkRazon;

    public HistorialTickets() {
    }

    public HistorialTickets(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public HistorialTickets(Integer idHistorial, Date fecha) {
        this.idHistorial = idHistorial;
        this.fecha = fecha;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public Tickets getFkTicket() {
        return fkTicket;
    }

    public void setFkTicket(Tickets fkTicket) {
        this.fkTicket = fkTicket;
    }

    public Usuarios getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuarios fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Estados getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Estados fkEstado) {
        this.fkEstado = fkEstado;
    }

    public RazonesTransferencias getFkRazon() {
        return fkRazon;
    }

    public void setFkRazon(RazonesTransferencias fkRazon) {
        this.fkRazon = fkRazon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHistorial != null ? idHistorial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HistorialTickets)) {
            return false;
        }
        HistorialTickets other = (HistorialTickets) object;
        if ((this.idHistorial == null && other.idHistorial != null) || (this.idHistorial != null && !this.idHistorial.equals(other.idHistorial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.idHistorial.toString();
    }

    @Override
    public int compareTo(HistorialTickets t) {
        if(fkTicket.getIdTicket() < t.fkTicket.getIdTicket()){
            return -1;
        }
        if(fkTicket.getIdTicket() > t.fkTicket.getIdTicket()){
            return 1;
        }
        return 0;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
}
