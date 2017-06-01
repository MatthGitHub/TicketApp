/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.PermisosJpaController;
import mscb.tick.negocio.entidades.Permisos;
import mscb.tick.negocio.entidades.Roles;

/**
 *
 * @author Administrador
 */
public class PermisoServ {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    private PermisosJpaController jpa = new PermisosJpaController(emf);
    private static PermisoServ esto;
    
    private PermisoServ(){
    }
    
    public static PermisoServ getPermisoServ(){
        if(esto == null){
            esto = new PermisoServ();
        }
        return esto;
    }
            
    public List <Permisos> traerTodos(){
        return jpa.findPermisosEntities();
    }
    
    public Permisos traerUno(int ID){
        return jpa.findPermisos(ID);
    }
    
}
