/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.asuntoSecundario.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.AsuntoSecundarioJpaController;
import mscb.tick.entidades.AsuntoPrincipal;
import mscb.tick.entidades.AsuntoSecundario;

/**
 *
 * @author Administrador
 */
public class AsuntoSecundarioServ {
    private List <AsuntoSecundario> miLista;
    private List <AsuntoSecundario> aux;
    
    public List <AsuntoSecundario> traerPorAreaPrincipal(AsuntoPrincipal asunto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoSecundarioJpaController jpa = new AsuntoSecundarioJpaController(emf);
        miLista = new ArrayList<>();
        miLista = jpa.findAsuntoSecundarioEntities();
        aux = new ArrayList<>();
        for(int i = 0; i < miLista.size(); i++){
            if(asunto.getAsuntoSecundarioCollection().contains(miLista.get(i))){
                aux.add(miLista.get(i));
            }
        }
        return aux;
        
    }
    public List <AsuntoSecundario> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoSecundarioJpaController jpa = new AsuntoSecundarioJpaController(emf);
        
        return jpa.findAsuntoSecundarioEntities();
    }
   
    public boolean eliminar(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoSecundarioJpaController jpa = new AsuntoSecundarioJpaController(emf);
        
        try{
            jpa.destroy(id);
            return true;
        }catch(Exception e){
            System.out.println(e +" - Eliminar asunto secundario");
            return false;
        }
        
    }
    
    public AsuntoSecundario traerUno(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoSecundarioJpaController jpa = new AsuntoSecundarioJpaController(emf);
        
        return jpa.findAsuntoSecundario(id);
    }
    
    public boolean nuevoASunto (AsuntoSecundario nuevo){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        AsuntoSecundarioJpaController jpa = new AsuntoSecundarioJpaController(emf);
        
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Carga nuevo asunto secundario");
            return false;
        }
    }
    
}
