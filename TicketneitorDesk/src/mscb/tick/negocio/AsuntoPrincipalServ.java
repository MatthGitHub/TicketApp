/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.AsuntosJpaController;
import mscb.tick.negocio.controladores.ServiciosJpaController;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Asuntos;

/**
 *
 * @author Administrador
 */
public class AsuntoPrincipalServ {
    private String PU = Main.getServer();
    private EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private AsuntosJpaController jpa = new AsuntosJpaController(emf);
    private static AsuntoPrincipalServ esto;
    
    private AsuntoPrincipalServ(){
        
    }
    
    public static AsuntoPrincipalServ getAsuntoPrincipalServ(){
        if(esto == null){
            esto = new AsuntoPrincipalServ();
        }
        return esto;
    }
    
    public List <Asuntos> traerTodos(){
        return jpa.findAsuntosEntities();
    }
    
    public boolean nuevoAsunto(Asuntos nuevo){
        try{
            jpa.create(nuevo);
            return true;
        }catch(Exception e){
            System.out.println(e+"- Nuevo AsuntoPrincipal");
            return false;
        }
    }
    
    public boolean eliminarAsunto(int id){
        try{
            jpa.destroy(id);
            return true;
        }catch(Exception e){
            System.out.println(e + "- Eliminar AsuntoPrincipal");
            return false;
        }
    }
    
    public List<Asuntos> asuntosPorArea(Areas area){
        List<Asuntos> miLista = jpa.findAsuntosEntities();
        List<Asuntos> aux = new ArrayList<>();
        
        for(int i = 0 ; i < miLista.size(); i ++){
            if(miLista.get(i).getFkArea().equals(area)){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public Asuntos getAsunto(int id){
        return jpa.findAsuntos(id);
    }
    
    public void setVisible(Asuntos asuntico) throws NonexistentEntityException, Exception{
        
        jpa.edit(asuntico);
    }
    
    
}
