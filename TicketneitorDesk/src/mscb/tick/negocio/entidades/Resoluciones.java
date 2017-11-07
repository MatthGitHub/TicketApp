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
@Table(name = "RESOLUCIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Resoluciones.findAll", query = "SELECT r FROM Resoluciones r"),
    @NamedQuery(name = "Resoluciones.findByNroResolucion", query = "SELECT r FROM Resoluciones r WHERE r.resolucionesPK.nroResolucion = :nroResolucion"),
    @NamedQuery(name = "Resoluciones.findByOrigenResolucion", query = "SELECT r FROM Resoluciones r WHERE r.resolucionesPK.origenResolucion = :origenResolucion"),
    @NamedQuery(name = "Resoluciones.findByAnoResolucion", query = "SELECT r FROM Resoluciones r WHERE r.resolucionesPK.anoResolucion = :anoResolucion"),
    @NamedQuery(name = "Resoluciones.findByFechageneracion", query = "SELECT r FROM Resoluciones r WHERE r.fechageneracion = :fechageneracion"),
    @NamedQuery(name = "Resoluciones.findByFechavigencia", query = "SELECT r FROM Resoluciones r WHERE r.fechavigencia = :fechavigencia"),
    @NamedQuery(name = "Resoluciones.findByNroproyecto", query = "SELECT r FROM Resoluciones r WHERE r.nroproyecto = :nroproyecto"),
    @NamedQuery(name = "Resoluciones.findByAnoproyecto", query = "SELECT r FROM Resoluciones r WHERE r.anoproyecto = :anoproyecto"),
    @NamedQuery(name = "Resoluciones.findByAreagenera", query = "SELECT r FROM Resoluciones r WHERE r.areagenera = :areagenera"),
    @NamedQuery(name = "Resoluciones.findByPublica", query = "SELECT r FROM Resoluciones r WHERE r.publica = :publica")})
public class Resoluciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ResolucionesPK resolucionesPK;
    @Lob
    @Column(name = "Texto")
    private String texto;
    @Column(name = "Fecha_generacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechageneracion;
    @Column(name = "Fecha_vigencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavigencia;
    @Column(name = "Nro_proyecto")
    private String nroproyecto;
    @Column(name = "Ano_proyecto")
    private String anoproyecto;
    @JoinColumn(name = "Area_genera", referencedColumnName = "CODIGO_ORGANIGRAMA")
    @ManyToOne
    private InOrganigrama areagenera;
    @Basic(optional = false)
    @Column(name = "Publica")
    private boolean publica;
    @JoinColumn(name = "Id_tipo_resolucion", referencedColumnName = "Id_tipo_resolucion")
    @ManyToOne
    private ResolucionesTipos idtiporesolucion;

    public Resoluciones() {
    }

    public Resoluciones(ResolucionesPK resolucionesPK) {
        this.resolucionesPK = resolucionesPK;
    }

    public Resoluciones(ResolucionesPK resolucionesPK, boolean publica) {
        this.resolucionesPK = resolucionesPK;
        this.publica = publica;
    }

    public Resoluciones(String nroResolucion, String origenResolucion, String anoResolucion) {
        this.resolucionesPK = new ResolucionesPK(nroResolucion, origenResolucion, anoResolucion);
    }

    public ResolucionesPK getResolucionesPK() {
        return resolucionesPK;
    }

    public void setResolucionesPK(ResolucionesPK resolucionesPK) {
        this.resolucionesPK = resolucionesPK;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFechageneracion() {
        return fechageneracion;
    }

    public void setFechageneracion(Date fechageneracion) {
        this.fechageneracion = fechageneracion;
    }

    public Date getFechavigencia() {
        return fechavigencia;
    }

    public void setFechavigencia(Date fechavigencia) {
        this.fechavigencia = fechavigencia;
    }

    public String getNroproyecto() {
        return nroproyecto;
    }

    public void setNroproyecto(String nroproyecto) {
        this.nroproyecto = nroproyecto;
    }

    public String getAnoproyecto() {
        return anoproyecto;
    }

    public void setAnoproyecto(String anoproyecto) {
        this.anoproyecto = anoproyecto;
    }

    public InOrganigrama getAreagenera() {
        return areagenera;
    }

    public void setAreagenera(InOrganigrama areagenera) {
        this.areagenera = areagenera;
    }

    public boolean getPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
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
        hash += (resolucionesPK != null ? resolucionesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Resoluciones)) {
            return false;
        }
        Resoluciones other = (Resoluciones) object;
        if ((this.resolucionesPK == null && other.resolucionesPK != null) || (this.resolucionesPK != null && !this.resolucionesPK.equals(other.resolucionesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mscb.tick.negocio.Resoluciones[ resolucionesPK=" + resolucionesPK + " ]";
    }
    
}
