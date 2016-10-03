/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.main;

import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import mscb.tick.encargadoAsuntos.vista.AsuntoSinEncargadosF;
import mscb.tick.entidades.BaseConocimiento;
import mscb.tick.entidades.Usuarios;
import mscb.tick.hitorialTicket.vista.HistorialTicketV;
import mscb.tick.razonesTransf.vista.Razones;
import mscb.tick.tickets.vista.ResolucionF;
import mscb.tick.tickets.vista.ResponderF;
import mscb.tick.tickets.vista.RespuestaF;
import mscb.tick.tickets.vista.TicketsV;
import mscb.tick.tickets.vista.TransferenciaF;
import mscb.tick.usuarios.vista.CambiarClaveFrame;
import mscb.tick.usuarios.vista.CambiarTipoF;
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
    private InfoF inf;
        
    private UsuariosV usu;
    private NuevoUsuarioF formUsuario;
    private CambiarClaveFrame cambiarCl;
    private CambiarTipoF cambiarTipoU;
    
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
    private ResolucionVerF resoVerF;
    
    private AsuntosPrin asuntos;
    private NuevoAsuntoFrame nuevoAsunto;
    
    private AsuntoSec asuntoSec;
    private NuevoAsuntoSecFrame nuevoAsuntoSec;
    public AsuntoSinEncargadosF asuntoSinEnc;
    
    private BaseConocimientoV baseCono;
    private HistorialTicketV hisTick;
    
    private Razones razones;
    
            
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("mscb/tick/imagenes/icono.png"));


        return retValue;
    }
    
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        setDefaultCloseOperation(0);
        setResizable(false);
        setLocationRelativeTo(null);
        jMB_bar.setVisible(false);
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
        if(inf == null){
            inf = new InfoF(this);
        }else{
            inf.setVisible(true);
            inf.panelInfoP();
        }
        revalidate();
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
     * Ventana para modificar el tipo de usuario
     */
    public void cambiarTipoUsuario(Usuarios usu){
        if(cambiarTipoU == null){
            cambiarTipoU = new CambiarTipoF(this,usu);
        }else{
            cambiarTipoU.setVisible(true);
            cambiarTipoU.CambiarTipoM(usu);
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
    public void verResolucionTicket(BaseConocimiento miBase){
        if(resoVerF == null){
            resoVerF = new ResolucionVerF(miBase, this);
        }else{
            resoVerF.setVisible(true);
            resoVerF.ResolucionVerM(miBase);
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
     *Ventana con el historial de ticket seleccionado 
     */
    public void razonesDeTransferencias(){
        razones = Razones.getRazones(this);
        
        if(!razones.isVisible()==false){
            getContentPane().add(razones);
        }else{
            razones.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     * Ventana con asuntos sin encargar a nadie
     * 
     */
    public void asuntosSinEncargar(){
        if(asuntoSinEnc == null){
            asuntoSinEnc = new AsuntoSinEncargadosF(this);
        }else{
            asuntoSinEnc.setVisible(true);
            asuntoSinEnc.asunticosMeth();
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
        jMB_bar = new javax.swing.JMenuBar();
        jM_archivo = new javax.swing.JMenu();
        mi_misTicket = new javax.swing.JMenuItem();
        mi_nuevoTicket = new javax.swing.JMenuItem();
        mi_salir = new javax.swing.JMenuItem();
        jm_administracion = new javax.swing.JMenu();
        mi_administrar = new javax.swing.JMenuItem();
        mi_conocimiento = new javax.swing.JMenuItem();
        mi_estadoPGM = new javax.swing.JMenuItem();
        jM_configuracion = new javax.swing.JMenu();
        mi_areas = new javax.swing.JMenuItem();
        mi_asunutos = new javax.swing.JMenuItem();
        mi_servicios = new javax.swing.JMenuItem();
        mi_razones = new javax.swing.JMenuItem();
        mi_usuarios = new javax.swing.JMenuItem();
        mi_asignar = new javax.swing.JMenuItem();

        chkbx_thread.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jMB_bar.setBackground(new java.awt.Color(153, 153, 153));

        jM_archivo.setText("archivo");

        mi_misTicket.setText("mis tickets");
        mi_misTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_misTicketActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_misTicket);

        mi_nuevoTicket.setText("nuevo ticket");
        mi_nuevoTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoTicketActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_nuevoTicket);

        mi_salir.setText("salir");
        mi_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_salirActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_salir);

        jMB_bar.add(jM_archivo);

        jm_administracion.setText("administracion");

        mi_administrar.setText("administrar tickets");
        mi_administrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_administrarActionPerformed(evt);
            }
        });
        jm_administracion.add(mi_administrar);

        mi_conocimiento.setText("base de conocimiento");
        mi_conocimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_conocimientoActionPerformed(evt);
            }
        });
        jm_administracion.add(mi_conocimiento);

        mi_estadoPGM.setText("estado del pgm");
        mi_estadoPGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_estadoPGMActionPerformed(evt);
            }
        });
        jm_administracion.add(mi_estadoPGM);

        jMB_bar.add(jm_administracion);

        jM_configuracion.setText("configuracion");

        mi_areas.setText("areas");
        mi_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_areasActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_areas);

        mi_asunutos.setText("asuntos");
        mi_asunutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_asunutosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_asunutos);

        mi_servicios.setText("servicios");
        mi_servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_serviciosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_servicios);

        mi_razones.setText("razones de transf");
        mi_razones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_razonesActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_razones);

        mi_usuarios.setText("usuarios");
        mi_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_usuariosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_usuarios);

        mi_asignar.setText("asignar asuntos a usuarios");
        mi_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_asignarActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_asignar);

        jMB_bar.add(jM_configuracion);

        setJMenuBar(jMB_bar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_misTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_misTicketActionPerformed
        // TODO add your handling code here:
        miTickets();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_misTicketActionPerformed

    private void mi_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_areasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mi_areasActionPerformed

    private void mi_administrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_administrarActionPerformed
        // TODO add your handling code here:
        tickets();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_administrarActionPerformed

    private void mi_conocimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_conocimientoActionPerformed
        // TODO add your handling code here:
        mppal.setVisible(false);
        tickets();
    }//GEN-LAST:event_mi_conocimientoActionPerformed

    private void mi_estadoPGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_estadoPGMActionPerformed
        // TODO add your handling code here:
        cambiarEstadoPGM();
    }//GEN-LAST:event_mi_estadoPGMActionPerformed

    private void mi_asunutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_asunutosActionPerformed
        // TODO add your handling code here:
        asuntos();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_asunutosActionPerformed

    private void mi_serviciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_serviciosActionPerformed
        // TODO add your handling code here:
        asuntoSecundarios();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_serviciosActionPerformed

    private void mi_razonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_razonesActionPerformed
        // TODO add your handling code here:
        razonesDeTransferencias();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_razonesActionPerformed

    private void mi_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_usuariosActionPerformed
        // TODO add your handling code here:
        ventanausuarios();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_usuariosActionPerformed

    private void mi_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_asignarActionPerformed
        // TODO add your handling code here:
        asignarAsuntosEncargado();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_asignarActionPerformed

    private void mi_nuevoTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_nuevoTicketActionPerformed
        // TODO add your handling code here:
        nuevoTicket();
        mppal.setVisible(false);
    }//GEN-LAST:event_mi_nuevoTicketActionPerformed

    private void mi_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_salirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "El programa se cerrará. Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_mi_salirActionPerformed

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
    public static javax.swing.JCheckBox chkbx_thread;
    public javax.swing.JMenuBar jMB_bar;
    private javax.swing.JMenu jM_archivo;
    private javax.swing.JMenu jM_configuracion;
    private javax.swing.JMenu jm_administracion;
    private javax.swing.JMenuItem mi_administrar;
    private javax.swing.JMenuItem mi_areas;
    private javax.swing.JMenuItem mi_asignar;
    private javax.swing.JMenuItem mi_asunutos;
    private javax.swing.JMenuItem mi_conocimiento;
    private javax.swing.JMenuItem mi_estadoPGM;
    private javax.swing.JMenuItem mi_misTicket;
    private javax.swing.JMenuItem mi_nuevoTicket;
    private javax.swing.JMenuItem mi_razones;
    private javax.swing.JMenuItem mi_salir;
    private javax.swing.JMenuItem mi_servicios;
    private javax.swing.JMenuItem mi_usuarios;
    // End of variables declaration//GEN-END:variables
}
