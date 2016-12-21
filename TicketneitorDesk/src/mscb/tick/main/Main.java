/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.main;

import java.awt.Dialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.areas.vista.AreasV;
import mscb.tick.areas.vista.NuevaAreaD;
import mscb.tick.asuntoSecundarios.vista.AsuntoSec;
import mscb.tick.asuntoSecundarios.vista.NuevoAsuntoSecD;
import mscb.tick.asuntos.vista.AsuntosPrin;
import mscb.tick.asuntos.vista.NuevoAsuntoD;
import mscb.tick.conocimiento.vista.BaseConocimientoV;
import mscb.tick.conocimiento.vista.DetalleTicketDialog;
import mscb.tick.conocimiento.vista.ResolucionVerD;
import mscb.tick.empelados.vista.EmpleadosV;
import mscb.tick.empelados.vista.NuevoEmpleado;
import mscb.tick.encargadoAsuntos.vista.EncargadoAsuntos;
import mscb.tick.entidades.Asuntos;
import mscb.tick.entidades.Tickets;
import mscb.tick.listener.Listener;
import mscb.tick.login.Login;
import mscb.tick.login.MenuPrincipal;
import mscb.tick.tickets.vista.Actualizador;
import mscb.tick.tickets.vista.MisTickets;
import mscb.tick.tickets.vista.NuevoTicket;
import mscb.tick.encargadoAsuntos.vista.AsuntoSinEncargadoD;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.BaseConocimiento;
import mscb.tick.entidades.Permisos;
import mscb.tick.entidades.Usuarios;
import mscb.tick.hitorialTicket.vista.HistorialTicketV;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.permisos.servicios.PermisoServ;
import mscb.tick.razonesTransf.vista.Razones;
import mscb.tick.roles.vista.RolesV;
import mscb.tick.tickets.vista.CambiarEstadoTicketD;
import mscb.tick.tickets.vista.ObservacionD;
import mscb.tick.tickets.vista.ResolucionD;
import mscb.tick.tickets.vista.ResponderD;
import mscb.tick.tickets.vista.RespuestaD;
import mscb.tick.tickets.vista.TicketsV;
import mscb.tick.tickets.vista.TransferenciaD;
import mscb.tick.usuarios.vista.CambiarClaveDialog;
import mscb.tick.usuarios.vista.CambiarTipoD;
import mscb.tick.usuarios.vista.NuevoUsuarioD;
import mscb.tick.usuarios.vista.UsuariosV;

/**
 *
 * @author Administrador
 */


public class Main extends javax.swing.JFrame {
    private static Main esteMain;
    
    //Objetos de los paneles.
    private Listener escuchador;
    private Actualizador actualizador;
    
    private Login ingreso;
    private MenuPrincipal mppal;
    private InfoD inf;
    private PermisoServ serviciosP;
    
    private UsuariosV usu;
    private NuevoUsuarioD formUsuario;
    private CambiarClaveDialog cambiarCl;
    private CambiarTipoD cambiarTipoU;
    
    private RolesV roles;
    
    private NuevoTicket formTicket;
    private TicketsV tabTick;
    
    private EmpleadosV empleadosV;
    private NuevoEmpleado nuevoEmpleado;
    
    private EncargadoAsuntos asignarEncargado;
        
    private MisTickets misTickets;
    
    private ObservacionD observacion;
    private Tickets miTick;
    
    private ResponderD responder;
    private RespuestaD respuesta;
    private TransferenciaD transferir;
    private EstadoPGMD cambiarPgm;
    private ResolucionD resoF;
    private ResolucionVerD resoVerF;
    private CambiarEstadoTicketD cambiaEstadoTicket;
    
    private AsuntosPrin asuntos;
    private NuevoAsuntoD nuevoAsunto;
    
    private AsuntoSec asuntoSec;
    private NuevoAsuntoSecD nuevoAsuntoSec;
    public AsuntoSinEncargadoD asuntoSinEnc;
    
    private BaseConocimientoV baseCono;
    private DetalleTicketDialog detalleTicket;
    private HistorialTicketV hisTick;
    
    private Razones razones;
    
    private AreasV areasV;
    private NuevaAreaD nuevaArea;
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("mscb/tick/imagenes/icono.png"));


        return retValue;
    }
    
    /**
     * Creates new form Main
     */
    private Main() {
        initComponents();
        setDefaultCloseOperation(0);
        setResizable(false);
        setLocationRelativeTo(null);
        escuchador =  Listener.getListener(1);
        actualizador = new Actualizador(this);
        ventanaLogin();
        escuchador.start();
        actualizador.start();
    }
    /**
     * 
     * @return 
     */
    public static Main getMainFrame(){
        if(esteMain == null){
            esteMain = new Main();
        }
        return esteMain;
    }
    
    public void validarPermisos(){
        serviciosP = new PermisoServ();
        List<Permisos> permisosU = LoginEJB.usuario.getFkRol().getPermisosList();
        jMB_bar.setVisible(true);
        
        //miTickets
        if(permisosU.contains(serviciosP.traerUno(4))){
            mi_misTicket.setVisible(true);
        }else{
            mi_misTicket.setVisible(false);
        }
        //nuevo Ticket
        if(permisosU.contains(serviciosP.traerUno(5))){
            mi_nuevoTicket.setVisible(true);
        }else{
            mi_nuevoTicket.setVisible(false);
        }
        //base conocimiento
        if(permisosU.contains(serviciosP.traerUno(6))){
            mi_conocimiento.setVisible(true);
        }else{
            mi_conocimiento.setVisible(false);
        }
        //************** Menu Administracion ********************************
        if(permisosU.contains(serviciosP.traerUno(7))){
            jm_administracion.setVisible(true);
        }else{
            jm_administracion.setVisible(false);
        }
        //administrar tickets
        if(permisosU.contains(serviciosP.traerUno(8))){
            mi_administrar.setVisible(true);
        }else{
            mi_administrar.setVisible(false);
        }
        //ver estado del pgm
        if(permisosU.contains(serviciosP.traerUno(12))){
            mi_estadoPGM.setVisible(true);
        }else{
            mi_estadoPGM.setVisible(false);
        }
        //**************** Menu Configuracion *******************************
        if(permisosU.contains(serviciosP.traerUno(13))){
            jM_configuracion.setVisible(true);
        }else{
            jM_configuracion.setVisible(false);
        }
        //areas
        if(permisosU.contains(serviciosP.traerUno(14))){
            mi_areas.setVisible(true);
        }else{
            mi_areas.setVisible(false);
        }
        //asuntos
        if(permisosU.contains(serviciosP.traerUno(18))){
            mi_asunutos.setVisible(true);
        }else{
            mi_asunutos.setVisible(false);
        }
        //servicios
        if(permisosU.contains(serviciosP.traerUno(21))){
            mi_servicios.setVisible(true);
        }else{
            mi_servicios.setVisible(false);
        }
        //Razones de transferencias
        if(permisosU.contains(serviciosP.traerUno(24))){
            mi_razones.setVisible(true);
        }else{
            mi_razones.setVisible(false);
        }
        //usuarios
        if(permisosU.contains(serviciosP.traerUno(27))){
            mi_usuarios.setVisible(true);
        }else{
            mi_usuarios.setVisible(false);
        }
        //asignar asuntos
        if(permisosU.contains(serviciosP.traerUno(34))){
            mi_servicios.setVisible(true);
        }else{
            mi_servicios.setVisible(false);
        }
        //roles
        if(permisosU.contains(serviciosP.traerUno(35))){
            mi_roles.setVisible(true);
        }else{
            mi_roles.setVisible(false);
        }
        //roles
        if(permisosU.contains(serviciosP.traerUno(40))){
            mi_empleados.setVisible(true);
        }else{
            mi_empleados.setVisible(false);
        }
        
    }
    
    public boolean validarPermisos(int idPer){
        serviciosP = new PermisoServ();
         List<Permisos> permisosU = LoginEJB.usuario.getFkRol().getPermisosList();
         if(permisosU.contains(serviciosP.traerUno(idPer))){
             return true;
         }else{
             return false;
         }
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
            validarPermisos();
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
            inf = new InfoD(this,true);
        }else{
            inf.panelInfoP();
            inf.setVisible(true);
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
    public void nuevoUsuario(){
        if(formUsuario == null){
            formUsuario = new NuevoUsuarioD(this,true);
        }else{
            formUsuario.cargarFormulario(this);
            formUsuario.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana para modificar el tipo de usuario
     * @param usu
     */
    public void cambiarTipoUsuario(Usuarios usu){
        if(cambiarTipoU == null){
            cambiarTipoU = new CambiarTipoD(this,true,usu);
        }else{
            cambiarTipoU.CambiarTipoM(usu);
            cambiarTipoU.setVisible(true);
        }
        revalidate();
    }
    /**
     * Llama al frame con el fomrulario para cambiar la clave del usuario 
     */
    public void cambiarClave(){
        if(cambiarCl == null){
            cambiarCl = new CambiarClaveDialog(this,Dialog.ModalityType.APPLICATION_MODAL);
        }else{
            cambiarCl.cargarPanel(cambiarCl);
            cambiarCl.setVisible(true);
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
     * @param miTick
     */
    public void Observaciones(Tickets miTick){
        if(observacion == null){
            observacion = new ObservacionD(this,true,miTick);
        }else{
            observacion.Observacion(miTick);
            observacion.setVisible(true);
        }
        revalidate();
    }
    /**
     * Formulario de respuesta para tickets
     * 
     * @param miTick
     */
    public void Respuestas(Tickets miTick){
        if(responder == null){
            responder = new ResponderD(this,true,miTick);
        }else{
            responder.ResponderM(miTick);
            responder.setVisible(true);
        }
        revalidate();
    }
    /**
     * Muestra ventana con respuestas
     * @param miTick
     */
    public void verRespuestas(Tickets miTick){
        if(respuesta == null){
            respuesta = new RespuestaD(this,true,miTick);
        }else{
            respuesta.RespuestaMeth(miTick);
            respuesta.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana para transferir ticket de asunto
     * @param miTick 
     */
    public void transferirTicket(Tickets miTick){
        if(transferir == null){
            transferir = new TransferenciaD(this,true,miTick);
        }else{
            transferir.TransPanel(miTick);
            transferir.setVisible(true);
        }
        revalidate();
    }
    /**
     * Abre ventana para escribir la resolucion del ticket
     * @param miTick 
     */
    public void resolucionTicket(Tickets miTick){
        if(resoF == null){
            resoF = new ResolucionD(this,true,miTick);
        }else{
            resoF.ResolucionM(miTick);
            resoF.setVisible(true);
        }
        revalidate();
    }
    /**
     * Abre ventana para ver la resolucion del ticket 
     * @param miBase
     */
    public void verResolucionTicket(BaseConocimiento miBase){
        if(resoVerF == null){
            resoVerF = new ResolucionVerD(this,true,miBase);
        }else{
            resoVerF.ResolucionVerM(miBase);
            resoVerF.setVisible(true);
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
    public void nuevoAsunto(Areas miArea){
        if(nuevoAsunto == null){
            nuevoAsunto = new NuevoAsuntoD(this,true,miArea);
        }else{
            nuevoAsunto.nuevoAsunto(miArea);
            nuevoAsunto.setVisible(true);
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
     * @param miAsuntoPP
     */
    public void nuevoAsuntoSecundario(Asuntos miAsuntoPP){
        if(nuevoAsuntoSec == null){
            nuevoAsuntoSec = new NuevoAsuntoSecD(this,true, miAsuntoPP);
        }else{
            nuevoAsuntoSec.nuevoAsunto(miAsuntoPP);
            nuevoAsuntoSec.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana para modificar el estado del pgm para reflejarlo en la web
     */
    public void cambiarEstadoPGM(){
        if(cambiarPgm == null){
            cambiarPgm = new EstadoPGMD(this,false);
        }else{
            cambiarPgm.PgmPanel();
            cambiarPgm.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ventana con asuntos sin encargar a nadie
     * 
     */
    public void cambiarEstadoTicket(Tickets miTick){
        if(cambiaEstadoTicket == null){
            cambiaEstadoTicket = new CambiarEstadoTicketD(this,true,miTick);
        }else{
            cambiaEstadoTicket.getCambiarEstadoTicketPanel(miTick);
            cambiaEstadoTicket.setVisible(true);
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
     * Ventanaq con detalle de ticket
     */
    public void detalleTicket(BaseConocimiento miBase){
        if(detalleTicket == null){
            detalleTicket = new DetalleTicketDialog(this,true,miBase);
        }else{
            detalleTicket.setearValoresTicket(miBase);
            detalleTicket.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     *Ventana con el historial de ticket seleccionado 
     * @param miTick
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
            asuntoSinEnc = new AsuntoSinEncargadoD(this,false);
        }else{
            asuntoSinEnc.asunticosMeth();
            asuntoSinEnc.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     * Llamada a la ventana de areas
     */
    public void areas(){
        areasV = AreasV.getAreasV(this);
        
        if(!areasV.isVisible() == false){
            getContentPane().add(areasV);
        }else{
            areasV.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana para dar de alta nueva area
     */
    public void nuevaArea(){
        if(nuevaArea == null){
            nuevaArea = new NuevaAreaD(this, true);
        }else{
            nuevaArea.nuevaAreaM();
            nuevaArea.setVisible(true);
        }
        revalidate();
    } 
    /**
     * Ventana de roles
     */
    public void roles(){
        roles = RolesV.getRolesV(this);
        
        if(!roles.isVisible()==false){
            getContentPane().add(roles);
        }else{
            roles.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana de empleados
     */
    public void empleados(){
        empleadosV = EmpleadosV.getEmpeladosV(this);
        
        if(!empleadosV.isVisible()==false){
            getContentPane().add(empleadosV);
        }else{
            empleadosV.setVisible(true);
        }
        revalidate();
    }
    /**
     * Ventana de empleados
     */
    public void nuevoEmpleado(){
        nuevoEmpleado = NuevoEmpleado.getNuevoEmpelado(this);

        if(!nuevoEmpleado.isVisible()==false){
            getContentPane().add(nuevoEmpleado);
        }else{
            nuevoEmpleado.setVisible(true);
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
        mi_inicio = new javax.swing.JMenuItem();
        mi_misTicket = new javax.swing.JMenuItem();
        mi_nuevoTicket = new javax.swing.JMenuItem();
        mi_conocimiento = new javax.swing.JMenuItem();
        mi_salir = new javax.swing.JMenuItem();
        jm_administracion = new javax.swing.JMenu();
        mi_administrar = new javax.swing.JMenuItem();
        mi_estadoPGM = new javax.swing.JMenuItem();
        jM_configuracion = new javax.swing.JMenu();
        mi_areas = new javax.swing.JMenuItem();
        mi_asunutos = new javax.swing.JMenuItem();
        mi_servicios = new javax.swing.JMenuItem();
        mi_razones = new javax.swing.JMenuItem();
        mi_usuarios = new javax.swing.JMenuItem();
        mi_asignar = new javax.swing.JMenuItem();
        mi_roles = new javax.swing.JMenuItem();
        mi_empleados = new javax.swing.JMenuItem();

        chkbx_thread.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jMB_bar.setBackground(new java.awt.Color(153, 153, 153));

        jM_archivo.setText("archivo");

        mi_inicio.setText("inicio");
        mi_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_inicioActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_inicio);

        mi_misTicket.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_WINDOWS, 0));
        mi_misTicket.setText("mis tickets");
        mi_misTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_misTicketActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_misTicket);

        mi_nuevoTicket.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mi_nuevoTicket.setText("nuevo ticket");
        mi_nuevoTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoTicketActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_nuevoTicket);

        mi_conocimiento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mi_conocimiento.setText("base de conocimiento");
        mi_conocimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_conocimientoActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_conocimiento);

        mi_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
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

        mi_estadoPGM.setText("estado del pgm");
        mi_estadoPGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_estadoPGMActionPerformed(evt);
            }
        });
        jm_administracion.add(mi_estadoPGM);

        jMB_bar.add(jm_administracion);

        jM_configuracion.setText("configuracion");

        mi_areas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mi_areas.setText("areas");
        mi_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_areasActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_areas);

        mi_asunutos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        mi_asunutos.setText("asuntos");
        mi_asunutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_asunutosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_asunutos);

        mi_servicios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mi_servicios.setText("servicios");
        mi_servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_serviciosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_servicios);

        mi_razones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mi_razones.setText("razones de transf");
        mi_razones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_razonesActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_razones);

        mi_usuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        mi_usuarios.setText("usuarios");
        mi_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_usuariosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_usuarios);

        mi_asignar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        mi_asignar.setText("asignar asuntos a usuarios");
        mi_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_asignarActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_asignar);

        mi_roles.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        mi_roles.setText("roles");
        mi_roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_rolesActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_roles);

        mi_empleados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mi_empleados.setText("empleados");
        mi_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_empleadosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_empleados);

        jMB_bar.add(jM_configuracion);

        setJMenuBar(jMB_bar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_misTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_misTicketActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        miTickets();
        this.repaint();
    }//GEN-LAST:event_mi_misTicketActionPerformed

    private void mi_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_areasActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        areas();
        this.repaint();
    }//GEN-LAST:event_mi_areasActionPerformed

    private void mi_administrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_administrarActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        tickets();
        this.repaint();
    }//GEN-LAST:event_mi_administrarActionPerformed

    private void mi_conocimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_conocimientoActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        baseDeConocimiento();
        this.repaint();
    }//GEN-LAST:event_mi_conocimientoActionPerformed

    private void mi_estadoPGMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_estadoPGMActionPerformed
        // TODO add your handling code here:
        cambiarEstadoPGM();
    }//GEN-LAST:event_mi_estadoPGMActionPerformed

    private void mi_asunutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_asunutosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        asuntos();
        this.repaint();
    }//GEN-LAST:event_mi_asunutosActionPerformed

    private void mi_serviciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_serviciosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        asuntoSecundarios();
        this.repaint();
    }//GEN-LAST:event_mi_serviciosActionPerformed

    private void mi_razonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_razonesActionPerformed
        // TODO add your handling code here:
       this.getContentPane().removeAll();
        razonesDeTransferencias();
        this.repaint();
    }//GEN-LAST:event_mi_razonesActionPerformed

    private void mi_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_usuariosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        ventanausuarios();
        this.repaint();
    }//GEN-LAST:event_mi_usuariosActionPerformed

    private void mi_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_asignarActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        asignarAsuntosEncargado();
        this.repaint();
    }//GEN-LAST:event_mi_asignarActionPerformed

    private void mi_nuevoTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_nuevoTicketActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        nuevoTicket();
        this.repaint();
    }//GEN-LAST:event_mi_nuevoTicketActionPerformed

    private void mi_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_salirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "El programa se cerrará. Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_mi_salirActionPerformed

    private void mi_inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_inicioActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        menuPrincipal();
        this.repaint();
    }//GEN-LAST:event_mi_inicioActionPerformed

    private void mi_rolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_rolesActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        roles();
        this.repaint();
    }//GEN-LAST:event_mi_rolesActionPerformed

    private void mi_empleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_empleadosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        empleados();
        this.repaint();
    }//GEN-LAST:event_mi_empleadosActionPerformed

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
                getMainFrame().setVisible(true);
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
    private javax.swing.JMenuItem mi_empleados;
    private javax.swing.JMenuItem mi_estadoPGM;
    private javax.swing.JMenuItem mi_inicio;
    private javax.swing.JMenuItem mi_misTicket;
    private javax.swing.JMenuItem mi_nuevoTicket;
    private javax.swing.JMenuItem mi_razones;
    private javax.swing.JMenuItem mi_roles;
    private javax.swing.JMenuItem mi_salir;
    private javax.swing.JMenuItem mi_servicios;
    private javax.swing.JMenuItem mi_usuarios;
    // End of variables declaration//GEN-END:variables
}
