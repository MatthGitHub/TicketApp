/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrador
 */
public class Listener extends Thread {
    private DiferenciasBD dif;
    private static int ran;
    private static Listener listener;
    public boolean diferencias;
    //private TicketsV panelAdmTi = TicketsV.getTickets(mainFrame);
    //private MisTickets panelMisti = MisTickets.getMisTickets(mainFrame);
    
    private Listener(int ran){
        this.ran = ran;
        diferencias = false;
    }
    
    public static Listener getListener(int ran){
        if(listener == null){
            listener = new Listener(ran);
        }
        return listener;
    }
    
    
    @Override
    public void run(){
        //int ran=1;
        while(ran == 1){
            int f = 0;
            dif = DiferenciasBD.getDiferenciasBD();
            f = dif.buscarDiferencias();
            //System.err.println("Buscando diferencias - Aun no se conecta el usuario"+e);
            
            
            if(f != 0){
                System.out.println("Hay nuevos tickets: "+LoginEJB.usuario.getNombreUsuario());
                diferencias = true;
            }
        esperar();
        }
    }
            
    private void esperar(){
        try {
            Thread.sleep(30000);
            System.out.println("Listener dumiendo!");
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Error en el metodo de espera del Thread Listener");
        }
    }
}
