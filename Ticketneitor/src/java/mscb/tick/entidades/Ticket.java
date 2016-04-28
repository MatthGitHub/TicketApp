/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.entidades;

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
@Table(name = "ticket")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ticket.findAll", query = "SELECT t FROM Ticket t"),
    @NamedQuery(name = "Ticket.findByIdTicket", query = "SELECT t FROM Ticket t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Ticket.findByFecha", query = "SELECT t FROM Ticket t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Ticket.findByHora", query = "SELECT t FROM Ticket t WHERE t.hora = :hora"),
    @NamedQuery(name = "Ticket.findByObservacion", query = "SELECT t FROM Ticket t WHERE t.observacion = :observacion")})
public class Ticket implements Serializable {
    @Column(name = "respuesta")
    private String respuesta;
    @JoinColumn(name = "fk_estado", referencedColumnName = "id_estado")
    @ManyToOne
    private Estados fkEstado;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ticket")
    private Integer idTicket;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "fk_area_sistemas", referencedColumnName = "id_area_sistemas")
    @ManyToOne(optional = false)
    private AreaSistemas fkAreaSistemas;
    @JoinColumn(name = "usuario_receptor", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario usuarioReceptor;
    @JoinColumn(name = "fk_usuario_emisor", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario fkUsuarioEmisor;
    @JoinColumn(name = "fk_area_emisor", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Area fkAreaEmisor;
    @JoinColumn(name = "asunto", referencedColumnName = "id_asuntoS")
    @ManyToOne(optional = false)
    private AsuntoSecundario asunto;

    public Ticket() {
    }

    public Ticket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Ticket(Integer idTicket, Date fecha, Date hora) {
        this.idTicket = idTicket;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public AreaSistemas getFkAreaSistemas() {
        return fkAreaSistemas;
    }

    public void setFkAreaSistemas(AreaSistemas fkAreaSistemas) {
        this.fkAreaSistemas = fkAreaSistemas;
    }

    public Usuario getUsuarioReceptor() {
        return usuarioReceptor;
    }

    public void setUsuarioReceptor(Usuario usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

    public Usuario getFkUsuarioEmisor() {
        return fkUsuarioEmisor;
    }

    public void setFkUsuarioEmisor(Usuario fkUsuarioEmisor) {
        this.fkUsuarioEmisor = fkUsuarioEmisor;
    }

    public Area getFkAreaEmisor() {
        return fkAreaEmisor;
    }

    public void setFkAreaEmisor(Area fkAreaEmisor) {
        this.fkAreaEmisor = fkAreaEmisor;
    }

    public AsuntoSecundario getAsunto() {
        return asunto;
    }

    public void setAsunto(AsuntoSecundario asunto) {
        this.asunto = asunto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTicket != null ? idTicket.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.idTicket.toString() +" "+ this.fkUsuarioEmisor.getNombreUsuario() +" "+ this.fkAreaEmisor.getNombreArea();
    }

    public Estados getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Estados fkEstado) {
        this.fkEstado = fkEstado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
}
