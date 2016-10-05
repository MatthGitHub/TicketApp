/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.areas.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.AreasJpaController;
import mscb.tick.entidades.Areas;

/**
 *
 * @author Administrador
 */
public class AreaServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    AreasJpaController jpa = new AreasJpaController(emf);
    List<Areas> miLista;
    
    public List<Areas> traerTodasconAsuntos(){
         miLista = jpa.findAreasEntities();
         List<Areas> aux = new ArrayList<>();
         
         for(int i = 0; i < miLista.size(); i++){
             if(!miLista.get(i).getAsuntosList().isEmpty()){
                 aux.add(miLista.get(i));
             }
         }
         return aux;
    }
    
    public List<Areas> traerTodas(){
        return jpa.findAreasEntities();
    }
}
