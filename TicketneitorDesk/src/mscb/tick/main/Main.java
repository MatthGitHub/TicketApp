/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.main;

import java.awt.MouseInfo;
import java.awt.Point;
import mscb.tick.asuntoSecundarios.vista.AsuntoSec;
import mscb.tick.asuntoSecundarios.vista.NuevoAsuntoSecFrame;
import mscb.tick.asuntos.vista.AsuntosPrin;
import mscb.tick.asuntos.vista.NuevoAsuntoFrame;
import mscb.tick.conocimiento.vista.BaseConocimientoV;
import mscb.tick.encargadoAsuntos.vista.EncargadoAsuntos;
import mscb.tick.entidades.Asuntos;
import mscb.tick.entidades.Tickets;
import mscb.tick.listener.Listener;
import mscb.tick.login.Login;
import mscb.tick.login.MenuPrincipal;
import mscb.tick.tickets.vista.Actualizador;
import mscb.tick.tickets.vista.MisTickets;
import mscb.tick.tickets.vista.NuevoTicket;
import mscb.tick.tickets.vista.ObservacionF;
import mscb.tick.conocimiento.vista.ResolucionVerF;
import mscb.tick.conocimiento.vista.ResolucionVerP;
import mscb.tick.historial.servicios.HistorialServ;
import mscb.tick.hitorialTicket.vista.HistorialTicketV;
import mscb.tick.tickets.vista.ResolucionF;
import mscb.tick.tickets.vista.ResolucionP;
import mscb.tick.tickets.vista.ResponderF;
import mscb.tick.tickets.vista.RespuestaF;
import mscb.tick.tickets.vista.TicketsV;
import mscb.tick.tickets.vista.TransferenciaF;
import mscb.tick.usuarios.vista.CambiarClaveFrame;
import mscb.tick.usuarios.vista.NuevoUsuarioF;
import mscb.tick.usuarios.vista.UsuariosV;

/**
 *
 * @author Administrador
 */
public class Main extends javax.swing.JFrame {
    
    private int x;
    private int y;

    //Objetos de los paneles.
    private Listener escuchador;
    private Actualizador actualizador;
    
    private Login ingreso;
    private MenuPrincipal mppal;
    private Info inf;
        
    private UsuariosV usu;
    private NuevoUsuarioF formUsuario;
    private CambiarClaveFrame cambiarCl;
    
    private NuevoTicket formTicket;
    private TicketsV tabTick;
    
    
    
    private EncargadoAsuntos asignarEncargado;
        
    private MisTickets misTickets;
    
    private ObservacionF observacion;
    private Tickets miTick;
    
    private ResponderF responder;
    private RespuestaF respuesta;
    private TransferenciaF transferir;
    private EstadoPGMF cambiarPgm;
    private ResolucionF resoF;
    private ResolucionP resoP;
    private ResolucionVerF resoVerF;
    private ResolucionVerP resoVerP;
    
    private AsuntosPrin asuntos;
    private NuevoAsuntoFrame nuevoAsunto;
    
    private AsuntoSec asuntoSec;
    private NuevoAsuntoSecFrame nuevoAsuntoSec;
    private BaseConocimientoV baseCono;
    private HistorialTicketV hisTick;
    
            
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setLocationRelativeTo(null);
        escuchador =  Listener.getListener(1);
        actualizador = new Actualizador(this);
        ventanaLogin();
        escuchador.start();
        actualizador.start();
    }
    
    public Main getMainFrame(){
        return this;
    }
    /**
     * Llamada a la ventana del Login
     */
    public void ventanaLogin(){
        ingreso = Login.getLogin(this);
        
        if(!ingreso.isVisible() == false){
           getContentPane().add(ingreso);
        }else{
            ingreso.setVisible(true);
        }
        revalidate();
    }
    /**
     * Llamada a la ventana del menu principal
     */
    public void menuPrincipal(){
        mppal = MenuPrincipal.getMenuPrincipal(this);
        
        if(!mppal.isVisible() == false){
            getContentPane().add(mppal);
        }else{
            mppal.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana de informacion de la app. Es un Frame
     */
    public void ventanaInfo(){
        inf = new Info();
    }
    /**
     * Llama a la ventana con la tabla de usuarios y sus menus.
     */
    public void ventanausuarios(){
        usu = UsuariosV.getUsuarios(this);
        
        if(!usu.isVisible() == false){
            getContentPane().add(usu);
        }else{
            usu.setVisible(true);
        }
        revalidate();
    }
    /**
     * Llama al frame con el fomrulario para un nuevo usuario
     */
    public void nuevoUsuario(Main mainFrame){
        if(formUsuario == null){
            formUsuario = new NuevoUsuarioF(mainFrame);
        }else{
            formUsuario.setVisible(true);
            formUsuario.cargarFormulario(mainFrame);
        }
        revalidate();
    }
    
    /**
     * Llama al frame con el fomrulario para cambiar la clave del usuario 
     */
    public void cambiarClave(){
        if(cambiarCl == null){
            cambiarCl = new CambiarClaveFrame(this);
        }else{
            cambiarCl.setVisible(true);
            cambiarCl.cargarPanel(cambiarCl);
        }
        revalidate();
    }
    /**
     * Formulario para crear nuevo ticket
     */
    public void nuevoTicket(){
        formTicket = NuevoTicket.getNuevoTicket(this);
        
        if(!formTicket.isVisible() == false){
            getContentPane().add(formTicket);
        }else{
            formTicket.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana con tabla de tickets
     */
    public void tickets(){
        tabTick = TicketsV.getTickets(this);
        
        if(!tabTick.isVisible()==false){
            getContentPane().add(tabTick);
        }else{
            tabTick.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Formulario para asignar asuntos a los usuarios de sistemas
     */
    public void asignarAsuntosEncargado(){
        asignarEncargado = EncargadoAsuntos.getEncargadoAsuntos(this);
        
        if(!asignarEncargado.isVisible()==false){
            getContentPane().add(asignarEncargado);
        }else{
            asignarEncargado.setVisible(true);
        }
        revalidate();
    }
    /**
     * Formulario para usuarios
     */
    public void miTickets(){
        misTickets = MisTickets.getMisTickets(this);
        
        if(!misTickets.isVisible() == false){
            getContentPane().add(misTickets);
        }else{
            misTickets.setVisible(true);
        }
        revalidate();
    }
    /**
     * Muestra ventana con observacion
     */
    public void Observaciones(Tickets miTick){
        if(observacion == null){
            observacion = new ObservacionF(miTick, this);
        }else{
            observacion.setVisible(true);
            observacion.Observacion(miTick);
        }
        revalidate();
    }
    /**
     * Formulario de respuesta para tickets
     */
    public void Respuestas(Tickets miTick){
        if(responder == null){
            responder = new ResponderF(miTick, this);
        }else{
            responder.setVisible(true);
            responder.ResponderM(miTick);
        }
        revalidate();
    }
    /**
     * Muestra ventana con respuestas
     */
    public void verRespuestas(Tickets miTick){
        if(respuesta == null){
            respuesta = new RespuestaF(miTick, this);
        }else{
            respuesta.setVisible(true);
            respuesta.RespuestaMeth(miTick);
        }
        revalidate();
    }
    /**
     * Ventana para transferir ticket de asunto
     * @param miTick 
     */
    public void transferirTicket(Tickets miTick){
        if(transferir == null){
            transferir = new TransferenciaF(miTick, this);
        }else{
            transferir.setVisible(true);
            transferir.TransPanel(miTick);
        }
        revalidate();
    }
    /**
     * Abre ventana para escribir la resolucion del ticket
     * @param miTick 
     */
    public void resolucionTicket(Tickets miTick){
        if(resoF == null){
            resoF = new ResolucionF(miTick, this);
        }else{
            resoF.setVisible(true);
            resoF.ResolucionM(miTick);
        }
        revalidate();
    }
    /**
     * Abre ventana para ver la resolucion del ticket
     * @param miTick 
     */
    public void verResolucionTicket(Tickets miTick){
        if(resoVerF == null){
            resoVerF = new ResolucionVerF(miTick, this);
        }else{
            resoVerF.setVisible(true);
            resoVerF.ResolucionVerM(miTick);
        }
        revalidate();
    }
    
    /**
     * Muestra ventana con asuntos
     */
    public void asuntos(){
        asuntos = AsuntosPrin.getAsuntos(this);
        
        if(!asuntos.isVisible() == false){
            getContentPane().add(asuntos);
        }else{
            asuntos.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Muestra ventana nuevo asunto principal
     */
    public void nuevoAsunto(){
        if(nuevoAsunto == null){
            nuevoAsunto = new NuevoAsuntoFrame(this);
        }else{
            nuevoAsunto.setVisible(true);
            nuevoAsunto.nuevoAsunto();
        }
        revalidate();
    }
    
    /**
     * Panel de asuntos secundarios
     */
    public void asuntoSecundarios(){
        asuntoSec = AsuntoSec.getAsuntoSec(this);
        
        if(!asuntoSec.isVisible() == false){
            getContentPane().add(asuntoSec);
        }else{
            asuntoSec.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Muestra ventana nuevo asunto secundario
     */
    public void nuevoAsuntoSecundario(Asuntos miAsuntoPP){
        if(nuevoAsuntoSec == null){
            nuevoAsuntoSec = new NuevoAsuntoSecFrame(this, miAsuntoPP);
        }else{
            nuevoAsuntoSec.setVisible(true);
            nuevoAsuntoSec.nuevoAsunto(miAsuntoPP);
        }
        revalidate();
    }
    /**
     * Ventana para modificar el estado del pgm para reflejarlo en la web
     */
    public void cambiarEstadoPGM(){
        if(cambiarPgm == null){
            cambiarPgm = new EstadoPGMF(this);
        }else{
            cambiarPgm.setVisible(true);
            cambiarPgm.PgmPanel();
        }
        revalidate();
    }
    
    /**
     * Ventana de base de conocimiento
     */
    public void baseDeConocimiento(){
        baseCono = BaseConocimientoV.getBaseConocimiento(this);
        
        if(!baseCono.isVisible()==false){
            getContentPane().add(baseCono);
        }else{
            baseCono.setVisible(true);
        }
        revalidate();
    }
    
    /**
     *Ventana con el historial de ticket seleccionado 
     */
    public void historialDeTickets(Tickets miTick){
        hisTick = HistorialTicketV.getHistorialTicketV(this,miTick);
        
        if(!hisTick.isVisible()==false){
            getContentPane().add(hisTick);
        }else{
            hisTick.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chkbx_thread = new javax.swing.JCheckBox();
        btn_mini = new javax.swing.JButton();
        btn_mover = new javax.swing.JButton();

        chkbx_thread.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        btn_mini.setBackground(new java.awt.Color(153, 153, 0));
        btn_mini.setText("-");
        btn_mini.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_miniActionPerformed(evt);
            }
        });

        btn_mover.setBackground(new java.awt.Color(0, 153, 153));
        btn_mover.setText("+");
        btn_mover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btn_moverMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_moverMouseMoved(evt);
            }
        });
        btn_mover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_moverMousePressed(evt);
            }
        });
        btn_mover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_moverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_mini)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_mover)
                .addGap(0, 484, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_mini, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mover, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 291, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_miniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_miniActionPerformed
        // TODO add your handling code here:
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btn_miniActionPerformed

    private void btn_moverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_moverMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_btn_moverMousePressed

    private void btn_moverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_moverMouseDragged
        // TODO add your handling code here:
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_btn_moverMouseDragged

    private void btn_moverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_moverMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_moverMouseMoved

    private void btn_moverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_moverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_mini;
    private javax.swing.JButton btn_mover;
    public static javax.swing.JCheckBox chkbx_thread;
    // End of variables declaration//GEN-END:variables
}
