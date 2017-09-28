/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.awt.TrayIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import mscb.tick.negocio.Listener;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.TicketServ;
import mscb.tick.util.MySystemTray;

/**
 *
 * @author Administrador
 */
public class Actualizador extends Thread {

    //private Listener diferencia;
    private BandejaTickets misTi;
    private TicketsV tickets;
    private Main mainFrame;
    private int j;
    private int i;
    //private DiferenciasBD dif;
    //public boolean diferencias;
    public static Integer cacheDatos;
    
    
    public Actualizador(Main mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        j = 0;
        while(true){    
            //System.out.println("Corriendo Actualizador - " + System.currentTimeMillis());
            //System.out.println("J: "+j);
            j++;
            if(cacheDatos == null){
                cargarCache();
            }
            
            i = 0;
            
            try{
               i = buscarDiferencias();
            }catch(Exception e){
                //esperar();
            }
            
            if (i != 0) {
                //System.out.println("Hay diferencias en BD");
                MySystemTray.getMySystemTray(mainFrame).MensajeTrayIcon("Tiene "+i+" nuevos tickets", TrayIcon.MessageType.INFO);
                BandejaTickets.getBandejaTickets(mainFrame).llenarTabla();
                TicketsV.getTickets(mainFrame).llenarTabla();
            }
            esperar();
        }
    }

    private void esperar() {
        try {
            Thread.sleep(15000);
            //System.out.println("Actualizador dumiendo!");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Error en el metodo de espera del Thread Listener");
        }
    }
    
    public int buscarDiferencias() {
        if(TicketServ.getTicketServ().buscarPorUsuarioAsunto().size() > cacheDatos){
                int dif = cacheDatos;
                cacheDatos = TicketServ.getTicketServ().buscarPorUsuarioAsunto().size();
                return TicketServ.getTicketServ().buscarPorUsuarioAsunto().size() - dif;
            }
        return 0;
    }
    
    private void cargarCache() {
        try{
            cacheDatos = TicketServ.getTicketServ().buscarPorUsuarioAsunto().size();
        }catch(Exception e){
            //System.err.println("Carga catché - Aun no se conecto el usuario"+e);
        }
        
    }
    
}
