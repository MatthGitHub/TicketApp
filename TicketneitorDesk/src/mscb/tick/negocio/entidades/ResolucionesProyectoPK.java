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
public class ResolucionesProyectoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Nro_proyecto")
    private String nroproyecto;
    @Basic(optional = false)
    @Column(name = "Ano_proyecto")
    private String anoproyecto;

    public ResolucionesProyectoPK() {
    }

    public ResolucionesProyectoPK(String nroproyecto, String anoproyecto) {
        this.nroproyecto = nroproyecto;
        this.anoproyecto = anoproyecto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroproyecto != null ? nroproyecto.hashCode() : 0);
        hash += (anoproyecto != null ? anoproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResolucionesProyectoPK)) {
            return false;
        }
        ResolucionesProyectoPK other = (ResolucionesProyectoPK) object;
        if ((this.nroproyecto == null && other.nroproyecto != null) || (this.nroproyecto != null && !this.nroproyecto.equals(other.nroproyecto))) {
            return false;
        }
        if ((this.anoproyecto == null && other.anoproyecto != null) || (this.anoproyecto != null && !this.anoproyecto.equals(other.anoproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nroproyecto + "-" + this.anoproyecto;
    }
    
}
