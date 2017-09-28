/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mscb.tick.gui.main.Main;

/**
 *
 * @author Administrador
 */
public class Funciones {
    
    public static void connectToServer() throws IOException{
        String commandConnect = "cmd /c net use \\\\"+Main.getServer()+"\\ipc$ /user:administrador cavaliere";
        Runtime.getRuntime().exec(commandConnect);
        String commandConnect2 = "cmd /c net use \\\\10.20.130.242\\ipc$ /user:administrador cavaliere";
        Runtime.getRuntime().exec(commandConnect2);
        //System.out.println(commandConnect+" Conectado");
    }
 
    public static void dissconectFromServer(){
        String commandDisconnect = "cmd /c net use \\\\"+Main.getServer()+"\\ipc$ /d";
        try {
            Runtime.getRuntime().exec(commandDisconnect);
            //System.out.println(commandDisconnect+" Desconectado");
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String commandDisconnect2 = "cmd /c net use \\\\10.20.130.242\\ipc$ /d";
        try {
            Runtime.getRuntime().exec(commandDisconnect2);
            //System.out.println(commandDisconnect2+" Desconectado");
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

