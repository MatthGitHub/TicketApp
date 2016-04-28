/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.login.servicios;

import java.util.List;
import mscb.tick.entidades.Usuario;
import mscb.tick.usuarios.servicios.UsuarioServ;

/**
 *
 * @author Matth
 */
public class LoginEJB {

    public static Usuario usuario;
    private UsuarioServ serviciosU;
    private List<Usuario> miLista;
    public static String mensaje;

    public LoginEJB() {
        usuario = new Usuario();
    }

    public boolean login(String nombre, String clave) {
        serviciosU = new UsuarioServ();
        miLista = serviciosU.traerTodos();
        for (int i = 0; i < miLista.size(); i++) {
            if (miLista.get(i).getNombreUsuario().equalsIgnoreCase(nombre)) {
                if (miLista.get(i).getContrasenia().equals(clave)) {
                    if (miLista.get(i).getActivo() == true) {
                        usuario = miLista.get(i);
                        return true;
                    } else {
                        mensaje = "Usuario Inactivo";
                    }
                } else {
                    mensaje = "Clave incorrecta";
                }
            }else{
                mensaje = "Usuario inexistente";
            }

            }
        return false;

        }
    }
