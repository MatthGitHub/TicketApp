/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.ConfiguracionJpaController;
import mscb.tick.negocio.entidades.Configuracion;

/**
 *
 * @author Administrador
 */
public class ConfiguracioneServ {
    private static ConfiguracioneServ estaClase;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    ConfiguracionJpaController jpa = new ConfiguracionJpaController(emf);
    
    private ConfiguracioneServ(){
        
    }
    
    
    public static ConfiguracioneServ getConfiguracioneServ(){
        if(estaClase == null){
            estaClase = new ConfiguracioneServ();
        }
        return estaClase;
    }
    
    public boolean validarVersion(Configuracion actual){
        if(actual.getDescripcion().equals(jpa.findConfiguracion(1).getDescripcion())){
            return false;
        }else{
            return true;
        }
    }
    
    public Configuracion getUltimaVersion(){
        return jpa.findConfiguracion(1);
    }
    
}
