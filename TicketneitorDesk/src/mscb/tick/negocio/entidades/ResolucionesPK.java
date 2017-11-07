/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Administrador
 */
@Embeddable
public class ResolucionesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "NRO_RESOLUCION")
    private String nroResolucion;
    @Basic(optional = false)
    @Column(name = "ORIGEN_RESOLUCION")
    private String origenResolucion;
    @Basic(optional = false)
    @Column(name = "ANO_RESOLUCION")
    private String anoResolucion;

    public ResolucionesPK() {
    }

    public ResolucionesPK(String nroResolucion, String origenResolucion, String anoResolucion) {
        this.nroResolucion = nroResolucion;
        this.origenResolucion = origenResolucion;
        this.anoResolucion = anoResolucion;
    }

    public String getNroResolucion() {
        return nroResolucion;
    }

    public void setNroResolucion(String nroResolucion) {
        this.nroResolucion = nroResolucion;
    }

    public String getOrigenResolucion() {
        return origenResolucion;
    }

    public void setOrigenResolucion(String origenResolucion) {
        this.origenResolucion = origenResolucion;
    }

    public String getAnoResolucion() {
        return anoResolucion;
    }

    public void setAnoResolucion(String anoResolucion) {
        this.anoResolucion = anoResolucion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroResolucion != null ? nroResolucion.hashCode() : 0);
        hash += (origenResolucion != null ? origenResolucion.hashCode() : 0);
        hash += (anoResolucion != null ? anoResolucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResolucionesPK)) {
            return false;
        }
        ResolucionesPK other = (ResolucionesPK) object;
        if ((this.nroResolucion == null && other.nroResolucion != null) || (this.nroResolucion != null && !this.nroResolucion.equals(other.nroResolucion))) {
            return false;
        }
        if ((this.origenResolucion == null && other.origenResolucion != null) || (this.origenResolucion != null && !this.origenResolucion.equals(other.origenResolucion))) {
            return false;
        }
        if ((this.anoResolucion == null && other.anoResolucion != null) || (this.anoResolucion != null && !this.anoResolucion.equals(other.anoResolucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nroResolucion + "- I -" +anoResolucion;
    }
    
}
