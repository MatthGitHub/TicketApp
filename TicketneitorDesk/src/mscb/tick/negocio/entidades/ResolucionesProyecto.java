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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "RESOLUCIONES_PROYECTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResolucionesProyecto.findAll", query = "SELECT r FROM ResolucionesProyecto r"),
    @NamedQuery(name = "ResolucionesProyecto.findByNroproyecto", query = "SELECT r FROM ResolucionesProyecto r WHERE r.resolucionesProyectoPK.nroproyecto = :nroproyecto"),
    @NamedQuery(name = "ResolucionesProyecto.findByAnoproyecto", query = "SELECT r FROM ResolucionesProyecto r WHERE r.resolucionesProyectoPK.anoproyecto = :anoproyecto"),
    @NamedQuery(name = "ResolucionesProyecto.findByFechainicio", query = "SELECT r FROM ResolucionesProyecto r WHERE r.fechainicio = :fechainicio"),
    @NamedQuery(name = "ResolucionesProyecto.findByFechamodificacion", query = "SELECT r FROM ResolucionesProyecto r WHERE r.fechamodificacion = :fechamodificacion"),
    @NamedQuery(name = "ResolucionesProyecto.findByFechavigencia", query = "SELECT r FROM ResolucionesProyecto r WHERE r.fechavigencia = :fechavigencia"),
    @NamedQuery(name = "ResolucionesProyecto.findByAreadestino", query = "SELECT r FROM ResolucionesProyecto r WHERE r.areadestino = :areadestino"),
    @NamedQuery(name = "ResolucionesProyecto.findByAnulado", query = "SELECT r FROM ResolucionesProyecto r WHERE r.anulado = :anulado"),
    @NamedQuery(name = "ResolucionesProyecto.findByUsuariomodifico", query = "SELECT r FROM ResolucionesProyecto r WHERE r.usuariomodifico = :usuariomodifico"),
    @NamedQuery(name = "ResolucionesProyecto.findByIdentificadormodifico", query = "SELECT r FROM ResolucionesProyecto r WHERE r.identificadormodifico = :identificadormodifico"),
    @NamedQuery(name = "ResolucionesProyecto.findByOrden", query = "SELECT r FROM ResolucionesProyecto r WHERE r.orden = :orden"),
    @NamedQuery(name = "ResolucionesProyecto.findByAfirmar", query = "SELECT r FROM ResolucionesProyecto r WHERE r.afirmar = :afirmar")})
public class ResolucionesProyecto implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResolucionesProyectoPK resolucionesProyectoPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "Texto")
    private String texto;
    @Basic(optional = false)
    @Column(name = "Fecha_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicio;
    @Column(name = "Fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechamodificacion;
    @Column(name = "Fecha_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigencia;
    @JoinColumn(name = "Area_destino", referencedColumnName = "CODIGO_ORGANIGRAMA")
    @ManyToOne
    private InOrganigrama areadestino;
    @Lob
    @Column(name = "Observacion")
    private String observacion;
    @Basic(optional = false)
    @Column(name = "Anulado")
    private boolean anulado;
    @Column(name = "Usuario_modifico")
    private String usuariomodifico;
    @Column(name = "Identificador_modifico")
    private String identificadormodifico;
    @Column(name = "Orden")
    private Integer orden;
    @Basic(optional = false)
    @Column(name = "A_firmar")
    private boolean afirmar;
    @JoinColumn(name = "Area_genera", referencedColumnName = "CODIGO_ORGANIGRAMA")
    @ManyToOne
    private InOrganigrama areagenera;
    @JoinColumn(name = "Id_tipo_resolucion", referencedColumnName = "Id_tipo_resolucion")
    @ManyToOne
    private ResolucionesTipos idtiporesolucion;

    public ResolucionesProyecto() {
    }

    public ResolucionesProyecto(ResolucionesProyectoPK resolucionesProyectoPK) {
        this.resolucionesProyectoPK = resolucionesProyectoPK;
    }

    public ResolucionesProyecto(ResolucionesProyectoPK resolucionesProyectoPK, String texto, Date fechainicio, boolean anulado, boolean afirmar) {
        this.resolucionesProyectoPK = resolucionesProyectoPK;
        this.texto = texto;
        this.fechainicio = fechainicio;
        this.anulado = anulado;
        this.afirmar = afirmar;
    }

    public ResolucionesProyecto(String nroproyecto, String anoproyecto) {
        this.resolucionesProyectoPK = new ResolucionesProyectoPK(nroproyecto, anoproyecto);
    }

    public ResolucionesProyectoPK getResolucionesProyectoPK() {
        return resolucionesProyectoPK;
    }

    public void setResolucionesProyectoPK(ResolucionesProyectoPK resolucionesProyectoPK) {
        this.resolucionesProyectoPK = resolucionesProyectoPK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public Date getFechavigencia() {
        return fechavigencia;
    }

    public void setFechavigencia(Date fechavigencia) {
        this.fechavigencia = fechavigencia;
    }

    public InOrganigrama getAreadestino() {
        return areadestino;
    }

    public void setAreadestino(InOrganigrama areadestino) {
        this.areadestino = areadestino;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }

    public String getUsuariomodifico() {
        return usuariomodifico;
    }

    public void setUsuariomodifico(String usuariomodifico) {
        this.usuariomodifico = usuariomodifico;
    }

    public String getIdentificadormodifico() {
        return identificadormodifico;
    }

    public void setIdentificadormodifico(String identificadormodifico) {
        this.identificadormodifico = identificadormodifico;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public boolean getAfirmar() {
        return afirmar;
    }

    public void setAfirmar(boolean afirmar) {
        this.afirmar = afirmar;
    }

    public InOrganigrama getAreagenera() {
        return areagenera;
    }

    public void setAreagenera(InOrganigrama areagenera) {
        this.areagenera = areagenera;
    }

    public ResolucionesTipos getIdtiporesolucion() {
        return idtiporesolucion;
    }

    public void setIdtiporesolucion(ResolucionesTipos idtiporesolucion) {
        this.idtiporesolucion = idtiporesolucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (resolucionesProyectoPK != null ? resolucionesProyectoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResolucionesProyecto)) {
            return false;
        }
        ResolucionesProyecto other = (ResolucionesProyecto) object;
        if ((this.resolucionesProyectoPK == null && other.resolucionesProyectoPK != null) || (this.resolucionesProyectoPK != null && !this.resolucionesProyectoPK.equals(other.resolucionesProyectoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.resolucionesProyectoPK.toString();
    }
    
}
