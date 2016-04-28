/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.permisos.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.PermisosJpaController;
import mscb.tick.entidades.Permisos;

/**
 *
 * @author Administrador
 */
public class PermisoServ {
    
    public List <Permisos> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        PermisosJpaController jpa = new PermisosJpaController(emf);
        
        return jpa.findPermisosEntities();
    }
}
