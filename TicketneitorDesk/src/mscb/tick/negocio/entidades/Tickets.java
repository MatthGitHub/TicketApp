/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.UsuarioServ;

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
    @NamedQuery(name = "Tickets.findByPatrimonio", query = "SELECT t FROM Tickets t WHERE t.patrimonio = :patrimonio"),
    @NamedQuery(name = "Tickets.findByTiempoResolucion", query = "SELECT t FROM Tickets t WHERE t.tiempoResolucion = :tiempoResolucion")})
public class Tickets implements Serializable,Comparable<Tickets> {

    @JoinColumn(name = "fkEdificio", referencedColumnName = "id_edificio")
    @ManyToOne
    private Edificios fkEdificio;

    @Column(name = "nota_entrada")
    private String notaEntrada;
    @Column(name = "nota_salida")
    private String notaSalida;

    @Column(name = "adjunto")
    private String adjunto;

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
    @Column(name = "patrimonio")
    private String patrimonio;
    @Column(name = "tiempoResolucion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tiempoResolucion;
    @JoinColumn(name = "creador", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuarios creador;
    @JoinColumn(name = "servicio", referencedColumnName = "id_asuntoS")
    @ManyToOne(optional = false)
    private Servicios servicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkTicket")
    private List<HistorialTickets> historialTicketsList;

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

    public Usuarios getCreador() {
        return creador;
    }

    public void setCreador(Usuarios creador) {
        this.creador = creador;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    @XmlTransient
    public List<HistorialTickets> getHistorialTicketsList() {
        return historialTicketsList;
    }

    public void setHistorialTicketsList(List<HistorialTickets> historialTicketsList) {
        this.historialTicketsList = historialTicketsList;
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
    
    public Estados getUltimoEstado(){
        HistorialServ his = HistorialServ.getHistorialServ();
        return his.buscarUltimo(this).getFkEstado();
    }
    
    public Usuarios getUltimoUsuario(){
        HistorialServ his = HistorialServ.getHistorialServ();
        return his.buscarUltimo(this).getFkUsuario();
    }
    
    public String getResolucion(){
        HistorialServ his = HistorialServ.getHistorialServ();
        return his.buscarUltimo(this).getResolucion();
    }
    
    @Override
    public String toString() {
        return this.idTicket.toString();
    }

    @Override
    public int compareTo(Tickets t) {
        if(idTicket < t.idTicket){
            return -1;
        }
        if(idTicket > t.idTicket){
            return 1;
        }
        return 0;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }

    public String getNotaEntrada() {
        return notaEntrada;
    }

    public void setNotaEntrada(String notaEntrada) {
        this.notaEntrada = notaEntrada;
    }

    public String getNotaSalida() {
        return notaSalida;
    }

    public void setNotaSalida(String notaSalida) {
        this.notaSalida = notaSalida;
    }

    public Edificios getFkEdificio() {
        return fkEdificio;
    }

    public void setFkEdificio(Edificios fkEdificio) {
        this.fkEdificio = fkEdificio;
    }
    
}
