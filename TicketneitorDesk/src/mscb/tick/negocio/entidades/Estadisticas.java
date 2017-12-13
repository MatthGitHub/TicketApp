/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio.entidades;

/**
 *
 * @author Administrador
 */
public class Estadisticas {
    private String nombre;
    private Integer cantidad;

    public Estadisticas(){
        
    }

    public Estadisticas(String nombre, Integer cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the cantidad
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
