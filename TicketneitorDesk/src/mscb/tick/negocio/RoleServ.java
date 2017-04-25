/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.controladores.RolesJpaController;
import mscb.tick.negocio.entidades.Roles;

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
    
    public Roles traerUno(int id){
        return jpa.findRoles(id);
    }
    
    public boolean modificar(Roles modi){
        try {
            jpa.edit(modi);
            return true;
        } catch (Exception e) {
            System.out.println(e+" - Error en modificar Rol");
            return false;
        }
    }
}