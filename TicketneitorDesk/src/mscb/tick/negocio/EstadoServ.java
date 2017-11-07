/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.controladores.EstadosJpaController;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Estados;

/**
 *
 * @author Administrador
 */
public class EstadoServ {
    private String PU = Main.getServer();
    private EntityManagerFactory emf = EntitiesManager.getEntityManagerFactory();
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
    
    public Estados traerEstado(String nombre){
        List<Estados> miLista = jpa.findEstadosEntities();
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getNombre().equals(nombre)){
                return miLista.get(i);
            }
        }
        return null;
    }
    
    public List<Estados> traerTodos(){
        List<Estados> aux = jpa.findEstadosEntities();
        List<Estados> aux2 = new ArrayList<>();
        for(int i = 0; i < aux.size(); i++){
            if(aux.get(i).getGeneral()){
                aux2.add(aux.get(i));
            }
        }
        return aux2;
    }
    
    public List<Estados> getEstadosPorAsunto(int idAsunto){
        return AsuntoPrincipalServ.getAsuntoPrincipalServ().getAsunto(idAsunto).getEstadosList();
    }
    
    public boolean nuevoEstadoPorArea(Estados nuevo, Asuntos asunto){
        try {
            jpa.create(nuevo);
                List<Estados> misEstados = EstadoServ.getEstadoServ().getEstadosPorAsunto(asunto.getIdasuntoP());
                misEstados.add(nuevo);
                asunto.setEstadosList(misEstados);
                try{
                    AsuntoPrincipalServ.getAsuntoPrincipalServ().modificarAsunto(asunto);
                    return true;
                }catch(Exception e){
                    System.err.println("Nuevo estado por area: "+e);
                    return false;
                }
        } catch (Exception e) {
            System.err.println("Nuevo estado por area: "+e);
            return false;
        }
            
    }
    
    
}
