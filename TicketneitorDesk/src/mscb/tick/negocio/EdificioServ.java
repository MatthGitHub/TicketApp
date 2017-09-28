/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.EdificiosJpaController;
import mscb.tick.negocio.entidades.Edificios;

/**
 *
 * @author Administrador
 */
public class EdificioServ {
    private static EdificioServ estaClase;
    private String PU = Main.getServer();
    private EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private EdificiosJpaController jpa = new EdificiosJpaController(emf);

    
    private EdificioServ(){
        
    }
    
    public static EdificioServ getEdificioServ(){
        if(estaClase == null){
            estaClase = new EdificioServ();
        }
        return estaClase;
    }
    
    
    public List<Edificios> traerTodos(){
        return jpa.findEdificiosEntities();
    }
    
    public Edificios buscarUno(String nombre){
        List<Edificios> miLista = jpa.findEdificiosEntities();
        
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getNombre().equals(nombre)){
                return miLista.get(i);
            }
        }
        return null;
    }
    
    public void nuevo(Edificios nuevo) throws Exception{
        jpa.create(nuevo);
    }
    
}
