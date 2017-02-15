/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.sql.Connection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.negocio.MD5;
import mscb.tick.negocio.UsuarioServ;

/**
 *
 * @author Matth
 */
public class LoginEJB {

    public static Usuarios usuario;
    private UsuarioServ serviciosU;
    private List<Usuarios> miLista;
    public static String mensaje;
    private MD5 md5;

    public LoginEJB() {
        usuario = new Usuarios();
    }

    public boolean login(String nombre, String clave) {
        md5 = new MD5();
        serviciosU = new UsuarioServ();
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
        /*for (int i = 0; i < miLista.size(); i++) {
            if (miLista.get(i).getNombreUsuario().equalsIgnoreCase(nombre)) {
                if (miLista.get(i).getContrasenia().equals(clave)) {
                    if (miLista.get(i).getActivo()) {
                        usuario = miLista.get(i);
                        return true;
                    } else {
                        mensaje = "Usuario Inactivo";
                        break;
                    }
                } else {
                    mensaje = "Clave incorrecta";
                    break;
                }
            } else {
                mensaje = "Usuario inexistente";
                break;
            }
        }*/
    }
    
    
}
