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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "IN_ORGANIGRAMA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InOrganigrama.findAll", query = "SELECT i FROM InOrganigrama i"),
    @NamedQuery(name = "InOrganigrama.findByCodigoOrganigrama", query = "SELECT i FROM InOrganigrama i WHERE i.codigoOrganigrama = :codigoOrganigrama"),
    @NamedQuery(name = "InOrganigrama.findByConcepto", query = "SELECT i FROM InOrganigrama i WHERE i.concepto = :concepto"),
    @NamedQuery(name = "InOrganigrama.findByConceptoAbreviado", query = "SELECT i FROM InOrganigrama i WHERE i.conceptoAbreviado = :conceptoAbreviado"),
    @NamedQuery(name = "InOrganigrama.findByCantidad", query = "SELECT i FROM InOrganigrama i WHERE i.cantidad = :cantidad"),
    @NamedQuery(name = "InOrganigrama.findByValor1", query = "SELECT i FROM InOrganigrama i WHERE i.valor1 = :valor1"),
    @NamedQuery(name = "InOrganigrama.findByValor2", query = "SELECT i FROM InOrganigrama i WHERE i.valor2 = :valor2"),
    @NamedQuery(name = "InOrganigrama.findByContador", query = "SELECT i FROM InOrganigrama i WHERE i.contador = :contador"),
    @NamedQuery(name = "InOrganigrama.findByFechaGlobal", query = "SELECT i FROM InOrganigrama i WHERE i.fechaGlobal = :fechaGlobal"),
    @NamedQuery(name = "InOrganigrama.findBySecretaria", query = "SELECT i FROM InOrganigrama i WHERE i.secretaria = :secretaria"),
    @NamedQuery(name = "InOrganigrama.findByDependede", query = "SELECT i FROM InOrganigrama i WHERE i.dependede = :dependede"),
    @NamedQuery(name = "InOrganigrama.findByAprobarEnArea", query = "SELECT i FROM InOrganigrama i WHERE i.aprobarEnArea = :aprobarEnArea"),
    @NamedQuery(name = "InOrganigrama.findByOficinaPersonal", query = "SELECT i FROM InOrganigrama i WHERE i.oficinaPersonal = :oficinaPersonal")})
public class InOrganigrama implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_ORGANIGRAMA")
    private String codigoOrganigrama;
    @Column(name = "CONCEPTO")
    private String concepto;
    @Column(name = "CONCEPTO_ABREVIADO")
    private String conceptoAbreviado;
    @Column(name = "CANTIDAD")
    private String cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_1")
    private Float valor1;
    @Column(name = "VALOR_2")
    private Float valor2;
    @Column(name = "CONTADOR")
    private Integer contador;
    @Column(name = "FECHA_GLOBAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGlobal;
    @Basic(optional = false)
    @Column(name = "Secretaria")
    private boolean secretaria;
    @Column(name = "DEPENDEDE")
    private String dependede;
    @Basic(optional = false)
    @Column(name = "AprobarEnArea")
    private boolean aprobarEnArea;
    @Column(name = "OFICINA_PERSONAL")
    private Integer oficinaPersonal;
    @OneToMany(mappedBy = "areagenera")
    private List<ResolucionesProyecto> resolucionesProyectoList;

    public InOrganigrama() {
    }

    public InOrganigrama(String codigoOrganigrama) {
        this.codigoOrganigrama = codigoOrganigrama;
    }

    public InOrganigrama(String codigoOrganigrama, boolean secretaria, boolean aprobarEnArea) {
        this.codigoOrganigrama = codigoOrganigrama;
        this.secretaria = secretaria;
        this.aprobarEnArea = aprobarEnArea;
    }

    public String getCodigoOrganigrama() {
        return codigoOrganigrama;
    }

    public void setCodigoOrganigrama(String codigoOrganigrama) {
        this.codigoOrganigrama = codigoOrganigrama;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getConceptoAbreviado() {
        return conceptoAbreviado;
    }

    public void setConceptoAbreviado(String conceptoAbreviado) {
        this.conceptoAbreviado = conceptoAbreviado;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Float getValor1() {
        return valor1;
    }

    public void setValor1(Float valor1) {
        this.valor1 = valor1;
    }

    public Float getValor2() {
        return valor2;
    }

    public void setValor2(Float valor2) {
        this.valor2 = valor2;
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Date getFechaGlobal() {
        return fechaGlobal;
    }

    public void setFechaGlobal(Date fechaGlobal) {
        this.fechaGlobal = fechaGlobal;
    }

    public boolean getSecretaria() {
        return secretaria;
    }

    public void setSecretaria(boolean secretaria) {
        this.secretaria = secretaria;
    }

    public String getDependede() {
        return dependede;
    }

    public void setDependede(String dependede) {
        this.dependede = dependede;
    }

    public boolean getAprobarEnArea() {
        return aprobarEnArea;
    }

    public void setAprobarEnArea(boolean aprobarEnArea) {
        this.aprobarEnArea = aprobarEnArea;
    }

    public Integer getOficinaPersonal() {
        return oficinaPersonal;
    }

    public void setOficinaPersonal(Integer oficinaPersonal) {
        this.oficinaPersonal = oficinaPersonal;
    }

    @XmlTransient
    public List<ResolucionesProyecto> getResolucionesProyectoList() {
        return resolucionesProyectoList;
    }

    public void setResolucionesProyectoList(List<ResolucionesProyecto> resolucionesProyectoList) {
        this.resolucionesProyectoList = resolucionesProyectoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoOrganigrama != null ? codigoOrganigrama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InOrganigrama)) {
            return false;
        }
        InOrganigrama other = (InOrganigrama) object;
        if ((this.codigoOrganigrama == null && other.codigoOrganigrama != null) || (this.codigoOrganigrama != null && !this.codigoOrganigrama.equals(other.codigoOrganigrama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.concepto;
    }
    
}
