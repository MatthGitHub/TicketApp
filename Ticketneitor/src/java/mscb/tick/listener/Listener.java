/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.listener;

import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mscb.tick.entidades.Tickets;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.tickets.vista.MisTickets;
import mscb.tick.tickets.vista.TicketsV;

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
            int f;
            dif = DiferenciasBD.getDiferenciasBD();
            System.out.println("Corriendo listener - "+System.currentTimeMillis()/100000);
            f = dif.buscarDiferencias();
            
            if(f != 0){
                for(int i = 0; i < f; i++){
                    if(LoginEJB.usuario.getServiciosList().contains(DiferenciasBD.nuevosTick.get(i).getAsunto())){
                    /*JOptionPane.showMessageDialog(null, "Tiene un nuevo pedido de "
                            +DiferenciasBD.nuevosTick.get(i).getFkUsuarioEmisor()
                            +" - "+DiferenciasBD.nuevosTick.get(i).getFkAreaEmisor());
                    */
                    System.out.println("Hay nuevos tickets: "+LoginEJB.usuario.getNombreUsuario());
                    diferencias = true;
                   // panelAdmTi = TicketsV.getTickets(mainFrame);
                    //panelMisti = MisTickets.getMisTickets(mainFrame);
                   // panelAdmTi.llenarTabla();
                   // panelMisti.llenarTabla();
                    }
                }
                
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
