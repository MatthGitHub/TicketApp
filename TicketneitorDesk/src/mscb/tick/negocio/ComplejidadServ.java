/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import mscb.tick.negocio.controladores.ComplejidadJpaController;
import mscb.tick.negocio.entidades.Complejidad;

/**
 *
 * @author Administrador
 */
public class ComplejidadServ {
    private static ComplejidadServ estaClase;
    private EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
    private ComplejidadJpaController jpa = new ComplejidadJpaController(emf);
    
    
    private ComplejidadServ(){
        
    }
    
    public static ComplejidadServ getComplejidadServ(){
        if(estaClase == null){
            estaClase = new ComplejidadServ();
        }
        return estaClase;
    }
    
    public List<Complejidad> getComplejidades(){
        return jpa.findComplejidadEntities();
    }
    
    public Complejidad getComplejidad(Integer id){
        return jpa.findComplejidad(id);
    }
}
