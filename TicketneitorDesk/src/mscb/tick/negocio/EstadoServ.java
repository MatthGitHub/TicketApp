/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.EstadosJpaController;
import mscb.tick.negocio.entidades.Estados;

/**
 *
 * @author Administrador
 */
public class EstadoServ {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    private EstadosJpaController jpa = new EstadosJpaController(emf);
    private static EstadoServ esto;
    
    private EstadoServ(){
        
    }
    
    public static EstadoServ getEstadoServ(){
        if(esto == null){
            esto = new EstadoServ();
        }
        return esto;
    }
    
    public Estados traerEstado(int id){
        return jpa.findEstados(id);
    }
    
    public List<Estados> traerTodos(){
        return jpa.findEstadosEntities();
    }
}
