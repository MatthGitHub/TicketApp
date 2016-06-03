/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import mscb.tick.entidades.Usuario;
import mscb.tick.usuarios.servicios.UsuarioServ;

/**
 *
 * @author Matth
 */
@ManagedBean
@RequestScoped
public class LoginMB {

    /**
     * Creates a new instance of LoginMB
     */
    
    public List <Usuario> usuarios;

    
    public LoginMB() {
       inicializar();
    }
    
    public void inicializar(){
        service = new UsuarioServ();
        usuarios = service.traerTodos();
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public UsuarioServ getService() {
        return service;
    }

    public void setService(UsuarioServ service) {
        this.service = service;
    }
    private UsuarioServ service;
    
    
    
}
