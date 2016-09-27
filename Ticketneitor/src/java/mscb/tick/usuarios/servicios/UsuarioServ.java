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
import mscb.tick.controladores.UsuariosJpaController;
import mscb.tick.controladores.exceptions.IllegalOrphanException;
import mscb.tick.controladores.exceptions.NonexistentEntityException;
import mscb.tick.entidades.Usuarios;
import mscb.tick.seguridad.MD5;

/**
 *
 * @author Administrador
 */
public class UsuarioServ {
    private List<Usuarios> miLista;
    private Usuarios miUsuario;
    private Date miFecha;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
    UsuariosJpaController jpa = new UsuariosJpaController(emf);
    private MD5 md5;
    
    public Usuarios buscarUsuario(String nombre, String clave){
        
        miLista = new ArrayList<>();
        miUsuario = new Usuarios();
        
        miLista = jpa.findUsuariosEntities();
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
    
    public List <Usuarios> traerTodos(){
        return jpa.findUsuariosEntities();
    }
    
    public int persistirUsuario(Usuarios aGuardar){
        md5 = new MD5();
        try {
            aGuardar.setContrasenia(md5.md5(aGuardar.getContrasenia()));
            System.out.println(aGuardar.getContrasenia());
            jpa.create(aGuardar);
            System.out.println("Usuario cargado exitosamente");
            return 1;
        } catch (Exception e) {
            System.out.println(aGuardar.getContrasenia());
            System.out.println("Error en la carga de usuario - "+e);
            return 0;
        }
            
         }
    
    public boolean eliminarUsuario(int id){
        try {
            jpa.destroy(id);
            return true;
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public int deshabilitarUsuario(int id){
        Usuarios usu;
        usu = jpa.findUsuarios(id);
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
        Usuarios usu;
        usu = jpa.findUsuarios(id);
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
        Usuarios usu;
        md5 = new MD5();
        usu = jpa.findUsuarios(id);
        usu.setContrasenia(md5.md5(usu.getFkEmpleado().getNombre()));
        
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
    
    public int cambiarClave(Usuarios usu ,String clave){
        md5 = new MD5();
        clave = md5.md5(clave);
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
    
    
    public List<Usuarios> traerSistemas(){
        miLista = new ArrayList<>();
        List<Usuarios> aux;
        aux = new ArrayList<>();
        
        miLista = jpa.findUsuariosEntities();
        for(int i = 0; i < miLista.size();i++){
            if(miLista.get(i).getFkEmpleado().getFkArea().getIdArea().equals(38)){
                aux.add(miLista.get(i));
            }
            
        }
        return aux;
    }
    
    
    public boolean modificarUsuario(Usuarios aModif){
        try {
            jpa.edit(aModif);
            return true;
        } catch (NonexistentEntityException ex) {
            System.out.println("Error al modificar usuario. No existe");
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            System.out.println("Error al modificar usuario");
            Logger.getLogger(UsuarioServ.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public Usuarios buscarUsuarioPorNombre(String nombre){
        List<Usuarios> miListaU = jpa.findUsuariosEntities();
        for(int i = 0; i < miListaU.size(); i++){
            if(miListaU.get(i).getNombreUsuario().equalsIgnoreCase(nombre)){
                return miListaU.get(i);
            }
        }
        Usuarios miUsu = new Usuarios();
        miUsu.setNombreUsuario("No se encontro");
        return miUsu;
    }
    
    public Usuarios buscarUnUsuario(int id){
        return jpa.findUsuarios(id);
    }
}