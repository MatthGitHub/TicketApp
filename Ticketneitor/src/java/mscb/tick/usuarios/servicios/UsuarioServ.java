/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.usuarios.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.UsuarioJpaController;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Usuario;

/**
 *
 * @author Administrador
 */
public class UsuarioServ {
    private List<Usuario> miLista;
    private Usuario miUsuario;
    private Date miFecha;
    
    public Usuario buscarUsuario(String nombre, String clave){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        miLista = new ArrayList<>();
        miUsuario = new Usuario();
        
        miLista = jpa.findUsuarioEntities();
        for(int i = 0; i < miLista.size();i++){
            if(miLista.get(i).getNombreUsuario().equals(nombre)){
                miUsuario.setNombreUsuario("si");
                if(miLista.get(i).getContrasenia().equals(clave)){
                    if(miLista.get(i).getActivo() == true){
                        return miLista.get(i);
                    }else{
                        System.out.println("Usuario no activo");
                        miUsuario.setActivo(false);
                    }
                }else{
                    System.out.println("Contraseña incorrecta");
                    miUsuario.setContrasenia("no");
                }
            }else{
                System.out.println("Nombre incorrecto");
                miUsuario.setNombreUsuario("no");
            }
        }
        return miUsuario;
    }
    
    public List <Usuario> traerTodos(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);

        return jpa.findUsuarioEntities();
    }
    
    public int persistirUsuario(Usuario aGuardar){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        
        try {
            jpa.create(aGuardar);
            return 1;
        } catch (Exception e) {
            System.out.println("Error en la carga");
            return 0;
        }
            
         }
    
    public int eliminarUsuario(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        
        try {
            jpa.destroy(id);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
    }
    
    public int deshabilitarUsuario(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        Usuario usu;
        usu = jpa.findUsuario(id);
        usu.setActivo(false);
        
        try {
            jpa.edit(usu);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
        
    }
    
    public int habilitarUsuario(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        Usuario usu;
        usu = jpa.findUsuario(id);
        usu.setActivo(true);
        
        try {
            jpa.edit(usu);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
        
    }
    
    public int resetClave(int id){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        Usuario usu;
        usu = jpa.findUsuario(id);
        usu.setContrasenia(usu.getFkEmpleado().getNombre());
        
        try {
            jpa.edit(usu);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        return 0;
    }
    
    public int cambiarClave(Usuario usu ,String clave){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        
        usu.setContrasenia(clave);
        
        try {
            jpa.edit(usu);
            return 0;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
    }
    
    
    public List<Usuario> traerSistemas(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        miLista = new ArrayList<>();
        List<Usuario> aux;
        aux = new ArrayList<>();
        
        miLista = jpa.findUsuarioEntities();
        for(int i = 0; i < miLista.size();i++){
            if(miLista.get(i).getFkEmpleado().getFkArea().getIdArea().equals(38)){
                aux.add(miLista.get(i));
            }
            
        }
        return aux;
    }
    
    
    public int modificarUsuario(Usuario aModif){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        UsuarioJpaController jpa = new UsuarioJpaController(emf);
        
        try {
            jpa.edit(aModif);
            return 0;
        } catch (NonexistentEntityException ex) {
            System.out.println("Error al modificar usuario. No existe");
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        } catch (Exception ex) {
            System.out.println("Error al modificar usuario");
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return 1;
        }
        
    }
    
}