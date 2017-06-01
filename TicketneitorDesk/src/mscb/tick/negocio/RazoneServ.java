/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.RazonesTransferenciasJpaController;
import mscb.tick.negocio.entidades.RazonesTransferencias;

/**
 *
 * @author Administrador
 */
public class RazoneServ {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    private RazonesTransferenciasJpaController jpa = new RazonesTransferenciasJpaController(emf);
    private static RazoneServ esto;
    
    private RazoneServ(){
        
    }
    
    public static RazoneServ getRazoneServ(){
        if(esto == null){
            esto = new RazoneServ();
        }
        return esto;
    }
    
    public List<RazonesTransferencias> traerTodos(){
        return jpa.findRazonesTransferenciasEntities();
    }
    
    public boolean eliminar(int id){
        try {
            jpa.destroy(id);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Eliminar transferencia");
            return false;
        }
    }
    
    public boolean nuevaRazon(RazonesTransferencias nuevo){
        try {
            jpa.create(nuevo);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Error al guardar nueva razon");
            return false;
        }
    }
    
}
