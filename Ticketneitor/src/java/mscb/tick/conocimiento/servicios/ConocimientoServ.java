/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.conocimiento.servicios;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.BaseConocimientoJpaController;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.BaseConocimiento;

/**
 *
 * @author Administrador
 */
public class ConocimientoServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    BaseConocimientoJpaController jpa = new BaseConocimientoJpaController(emf);
    
    public List<BaseConocimiento> traerTodos(){
        return jpa.findBaseConocimientoEntities();
    }
    
    public List<BaseConocimiento> traerPorArea(Areas area){
        List<BaseConocimiento> aux = new ArrayList<>();
        List<BaseConocimiento> miLista = jpa.findBaseConocimientoEntities();
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getFkTicket().getFkAreaReceptor().equals(area)){
                aux.add(miLista.get(i));
            }
        }
        return aux;
    }
    
    public boolean nuevaBase(BaseConocimiento nuevo){
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println("Error al cargar nueva base de conocimiento");
            return false;
        }
    }
    
    public BaseConocimiento buscarUno(int id){
        return jpa.findBaseConocimiento(id);
    }
}
