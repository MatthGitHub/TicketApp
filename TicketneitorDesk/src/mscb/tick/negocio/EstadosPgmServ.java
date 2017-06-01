/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.EstadosPgmJpaController;
import mscb.tick.negocio.entidades.EstadosPgm;

/**
 *
 * @author Administrador
 */
public class EstadosPgmServ {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    private EstadosPgmJpaController jpa = new EstadosPgmJpaController(emf);
    private static EstadosPgmServ esto;
    
    private EstadosPgmServ(){
        
    }
    
    public static EstadosPgmServ getEstadosPgmServ(){
        if(esto == null){
            esto = new EstadosPgmServ();
        }
        return esto;
    }
    
    public List<EstadosPgm> traerTodos(){
        return jpa.findEstadosPgmEntities();
    }
    
    public EstadosPgm traerUno(int id){
        return jpa.findEstadosPgm(id);
    }
    
    
}
