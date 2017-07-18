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
import mscb.tick.util.MySystemTray;

/**
 *
 * @author Administrador
 */
public class Actualizador extends Thread {

    private Listener diferencia;
    private BandejaTickets misTi;
    private TicketsV tickets;
    private Main mainFrame;
    private int ran = 1;

    public Actualizador(Main mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void run() {
        while (ran == 1) {
            diferencia = Listener.getListener(1);
            System.out.println("Corriendo Actualizador - " + System.currentTimeMillis() / 100000);
            if (diferencia.diferencias == true) {
                System.out.println("Hay diferencias en BD");
                MySystemTray.getMySystemTray(mainFrame).MensajeTrayIcon("Tiene nuevos tickets", TrayIcon.MessageType.INFO);
                misTi = BandejaTickets.getBandejaTickets(mainFrame);
                tickets = TicketsV.getTickets(mainFrame);
                misTi.llenarTabla();
                tickets.llenarTabla();
                diferencia.diferencias = false;
            }
            esperar();
        }
    }

    private void esperar() {
        try {
            Thread.sleep(30000);
            System.out.println("Actualizador dumiendo!");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en el metodo de espera del Thread Listener");
        }
    }
    
}
