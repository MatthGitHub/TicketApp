/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "tickets")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tickets.findAll", query = "SELECT t FROM Tickets t"),
    @NamedQuery(name = "Tickets.findByIdTicket", query = "SELECT t FROM Tickets t WHERE t.idTicket = :idTicket"),
    @NamedQuery(name = "Tickets.findByFecha", query = "SELECT t FROM Tickets t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "Tickets.findByHora", query = "SELECT t FROM Tickets t WHERE t.hora = :hora"),
    @NamedQuery(name = "Tickets.findByObservacion", query = "SELECT t FROM Tickets t WHERE t.observacion = :observacion"),
    @NamedQuery(name = "Tickets.findByRespuesta", query = "SELECT t FROM Tickets t WHERE t.respuesta = :respuesta"),
    @NamedQuery(name = "Tickets.findByPatrimonio", query = "SELECT t FROM Tickets t WHERE t.patrimonio = :patrimonio"),
    @NamedQuery(name = "Tickets.findByTiempoResolucion", query = "SELECT t FROM Tickets t WHERE t.tiempoResolucion = :tiempoResolucion")})
public class Tickets implements Serializable {

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
    @Column(name = "respuesta")
    private String respuesta;
    @Column(name = "patrimonio")
    private String patrimonio;
    @Column(name = "tiempoResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoResolucion;
    @JoinColumn(name = "fk_area_emisor", referencedColumnName = "id_area")
    @ManyToOne(optional = false)
    private Areas fkAreaEmisor;
    @JoinColumn(name = "fk_estado", referencedColumnName = "id_estado")
    @ManyToOne
    private Estados fkEstado;
    @JoinColumn(name = "fk_razon", referencedColumnName = "id_razon")
    @ManyToOne
    private RazonesTransferencias fkRazon;
    @JoinColumn(name = "fk_usuario_emisor", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios fkUsuarioEmisor;
    @JoinColumn(name = "usuario_receptor", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuarios usuarioReceptor;
    @JoinColumn(name = "fk_area_receptor", referencedColumnName = "id_area")
    @ManyToOne
    private Areas fkAreaReceptor;
    @JoinColumn(name = "asunto", referencedColumnName = "id_asuntoS")
    @ManyToOne(optional = false)
    private Servicios asunto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTicket")
    private List<BaseConocimiento> baseConocimientoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTicket")
    private List<HistorialTickets> historialTicketsList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tickets")
    private Respuestas respuestas;

    public Tickets() {
    }

    public Tickets(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Tickets(Integer idTicket, Date fecha, Date hora) {
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public Date getTiempoResolucion() {
        return tiempoResolucion;
    }

    public void setTiempoResolucion(Date tiempoResolucion) {
        this.tiempoResolucion = tiempoResolucion;
    }

    public Areas getFkAreaEmisor() {
        return fkAreaEmisor;
    }

    public void setFkAreaEmisor(Areas fkAreaEmisor) {
        this.fkAreaEmisor = fkAreaEmisor;
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

    public Usuarios getFkUsuarioEmisor() {
        return fkUsuarioEmisor;
    }

    public void setFkUsuarioEmisor(Usuarios fkUsuarioEmisor) {
        this.fkUsuarioEmisor = fkUsuarioEmisor;
    }

    public Usuarios getUsuarioReceptor() {
        return usuarioReceptor;
    }

    public void setUsuarioReceptor(Usuarios usuarioReceptor) {
        this.usuarioReceptor = usuarioReceptor;
    }

    public Areas getFkAreaReceptor() {
        return fkAreaReceptor;
    }

    public void setFkAreaReceptor(Areas fkAreaReceptor) {
        this.fkAreaReceptor = fkAreaReceptor;
    }

    public Servicios getAsunto() {
        return asunto;
    }

    public void setAsunto(Servicios asunto) {
        this.asunto = asunto;
    }

    @XmlTransient
    public List<BaseConocimiento> getBaseConocimientoList() {
        return baseConocimientoList;
    }

    public void setBaseConocimientoList(List<BaseConocimiento> baseConocimientoList) {
        this.baseConocimientoList = baseConocimientoList;
    }

    @XmlTransient
    public List<HistorialTickets> getHistorialTicketsList() {
        return historialTicketsList;
    }

    public void setHistorialTicketsList(List<HistorialTickets> historialTicketsList) {
        this.historialTicketsList = historialTicketsList;
    }

    public Respuestas getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Respuestas respuestas) {
        this.respuestas = respuestas;
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
        if (!(object instanceof Tickets)) {
            return false;
        }
        Tickets other = (Tickets) object;
        if ((this.idTicket == null && other.idTicket != null) || (this.idTicket != null && !this.idTicket.equals(other.idTicket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idTicket.toString();
    }
    
}
