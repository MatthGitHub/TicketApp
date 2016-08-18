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
import mscb.tick.controladores.ServiciosJpaController;
import mscb.tick.entidades.Asuntos;
import mscb.tick.entidades.Servicios;

/**
 *
 * @author Administrador
 */
public class AsuntoSecundarioServ {
    private List <Servicios> miLista;
    private List <Servicios> aux;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    ServiciosJpaController jpa = new ServiciosJpaController(emf);
    
    public List <Servicios> traerPorAreaPrincipal(Asuntos asunto){
        
        miLista = new ArrayList<>();
        miLista = jpa.findServiciosEntities();
        aux = new ArrayList<>();
        for(int i = 0; i < miLista.size(); i++){
            if(asunto.getServiciosList().contains(miLista.get(i))){
                aux.add(miLista.get(i));
            }
        }
        return aux;
        
    }
    public List <Servicios> traerTodos(){
        return jpa.findServiciosEntities();
    }
   
    public boolean eliminar(int id){
        try{
            jpa.destroy(id);
            return true;
        }catch(Exception e){
            System.out.println(e +" - Eliminar asunto secundario");
            return false;
        }
        
    }
    
    public Servicios traerUno(int id){
        return jpa.findServicios(id);
    }
    
    public boolean nuevoASunto (Servicios nuevo){
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Carga nuevo asunto secundario");
            return false;
        }
    }
    
}
