/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.roles.servicios;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.RolesJpaController;
import mscb.tick.entidades.Roles;

/**
 *
 * @author Administrador
 */
public class RoleServ {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    RolesJpaController jpa = new RolesJpaController(emf);
    
    public List<Roles> traerTodos(){
        return jpa.findRolesEntities();
    }
}
