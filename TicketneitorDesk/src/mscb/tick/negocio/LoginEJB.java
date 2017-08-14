/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import mscb.tick.negocio.entidades.Usuarios;

/**
 *
 * @author Matth
 */
public class LoginEJB {

    public static Usuarios usuario;
    private static UsuarioServ serviciosU;
    private List<Usuarios> miLista;
    public static String mensaje;
    private MD5 md5;

    public LoginEJB() {
        usuario = new Usuarios();
    }

    public boolean login(String nombre, String clave){
        md5 = MD5.getMD5();
        serviciosU = UsuarioServ.getUsuarioServ();
        miLista = serviciosU.traerTodos();
        clave = md5.md5(clave);
        
        Usuarios miUsuario = serviciosU.buscarUsuarioPorNombre(nombre);
        if(!miUsuario.getNombreUsuario().equals("No")){
            if(miUsuario.getContrasenia().equals(clave)){
                if(miUsuario.getActivo()){
                    usuario = miUsuario;
                    return true;
                }else{
                    mensaje = "Usuario Inactivo";
                    return false;
                }
            }else{
                 mensaje = "Clave incorrecta";
                 return false;
            }
        }else{
             mensaje = "Usuario inexistente";
             return false;
        }
      
    }
    
    public static void refrescarPermisos(){
        serviciosU = UsuarioServ.getUsuarioServ();
        usuario = serviciosU.buscarUnUsuario(usuario.getIdUsuario());
    }
}
