/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.ConfiguracionJpaController;
import mscb.tick.negocio.entidades.Configuracion;

/**
 *
 * @author Administrador
 */
public class ConfiguracioneServ {
    private static ConfiguracioneServ estaClase;
    private String PU = Main.getServer();
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("10.20.130.242");
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
    
    public List<Configuracion> getServidores(){
        List<Configuracion> miLista = jpa.findConfiguracionEntities();
        List<Configuracion> aux = new ArrayList<>();
        
        for(int i = 0; i< miLista.size(); i++){
            if(miLista.get(i).getIdConfiguracion() > 1){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
}
