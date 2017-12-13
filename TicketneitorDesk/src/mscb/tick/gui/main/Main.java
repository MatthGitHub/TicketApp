/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.main;


import java.awt.Dialog;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import mscb.tick.gui.areas.AreasV;
import mscb.tick.gui.areas.NuevaAreaD;
import mscb.tick.gui.servicios.AsuntoSec;
import mscb.tick.gui.servicios.NuevoAsuntoSecD;
import mscb.tick.gui.asuntos.AsuntosPrin;
import mscb.tick.gui.asuntos.NuevoAsuntoD;
import mscb.tick.gui.conocimiento.BaseConocimientoV;
import mscb.tick.gui.conocimiento.DetalleTicketDialog;
import mscb.tick.gui.edificios.EdificiosV;
import mscb.tick.gui.edificios.NuevoEdificio;
import mscb.tick.gui.empleados.EmpleadosV;
import mscb.tick.gui.empleados.NuevoEmpleado;
import mscb.tick.gui.encargadoAsuntos.EncargadoAsuntos;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.Listener;
import mscb.tick.gui.login.Login;
import mscb.tick.gui.login.MenuPrincipal;
import mscb.tick.gui.tickets.Actualizador;
import mscb.tick.gui.tickets.BandejaTickets;
import mscb.tick.gui.tickets.NuevoTicket;
import mscb.tick.gui.encargadoAsuntos.AsuntoSinEncargadoD;
import mscb.tick.gui.encargadoAsuntos.MiServicios;
import mscb.tick.gui.estadisticas.EstadisticasAreasRecibidos;
import mscb.tick.gui.estadisticas.EstadisticasUsuariosEmitidos;
import mscb.tick.gui.estados.EstadosAsuntos;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Permisos;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.gui.historialTicket.HistorialTicketV;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.negocio.PermisoServ;
import mscb.tick.gui.razonesTransf.Razones;
import mscb.tick.gui.resoluciones.DetalleProyecto;
import mscb.tick.gui.resoluciones.ProyectosResoluciones;
import mscb.tick.gui.resoluciones.Resoluciones;
import mscb.tick.gui.roles.RolesV;
import mscb.tick.gui.tickets.AdjuntosD;
import mscb.tick.gui.tickets.BandejaEnviados;
import mscb.tick.gui.tickets.CambiarEstadoTicketD;
import mscb.tick.gui.tickets.ConfigurarTabla;
import mscb.tick.gui.tickets.ModificarNotaEntrada;
import mscb.tick.gui.tickets.ModificarNotaSalida;
import mscb.tick.gui.tickets.ModificarPatrimonio;
import mscb.tick.gui.tickets.ResolucionD;
import mscb.tick.gui.tickets.ResponderD;
import mscb.tick.gui.tickets.RespuestaD;
import mscb.tick.gui.tickets.TicketsV;
import mscb.tick.gui.tickets.TransferenciaD;
import mscb.tick.gui.usuarios.CambiarClaveDialog;
import mscb.tick.gui.usuarios.CambiarTipoD;
import mscb.tick.gui.usuarios.NuevoUsuarioD;
import mscb.tick.gui.usuarios.UsuariosV;
import mscb.tick.negocio.ConfiguracioneServ;
import mscb.tick.negocio.entidades.Configuracion;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.ResolucionesProyecto;
import mscb.tick.negocio.entidades.TicketsAdjuntos;
import mscb.tick.util.Funciones;
import mscb.tick.util.MySystemTray;

/**
 *
 * @author Administrador
 */


public class Main extends javax.swing.JFrame {
    private static Main esteMain;
    public static Configuracion version;
    public static String server;
    //Objetos de los paneles.
    private Listener escuchador;
    private Actualizador actualizador;
    
    private Login ingreso;
    private MenuPrincipal mppal;
    private InfoD inf;
    private AyudaD aiuda;
    private MiServicios miser;
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
    private EstadosAsuntos estadosAsuntos;
    
    private BandejaTickets bandejaEntrada;
    private BandejaEnviados bandejaSalida;
    private ConfigurarTabla configTabla;
    
    private Tickets miTick;
    
    private ModificarPatrimonio patri;
    private ModificarNotaSalida salia;
    private ModificarNotaEntrada entraa;
    
    private ResponderD responder;
    private RespuestaD respuesta;
    private TransferenciaD transferir;
    private EstadoPGMD cambiarPgm;
    private ResolucionD resoF;
    private CambiarEstadoTicketD cambiaEstadoTicket;
    private AdjuntosD adjuntos;
    
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
    
    private EdificiosV edificiosV;
    private NuevoEdificio nuevoEdificio;
    
    private ProyectosResoluciones resproyect;
    private DetalleProyecto detalleProy;
    
    private Resoluciones reso;
    
    private EstadisticasAreasRecibidos estadAreas;
    private EstadisticasUsuariosEmitidos estadUsuarios;    
    
    private MySystemTray mySystemTray;
    private static BufferedWriter bw = null;
            
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("mscb/tick/resources/imagenes/icono.png"));


        return retValue;
    }
    
    /**
     * Creates new form Main
     */
    private Main() {
        initComponents();
        version = new Configuracion();
        try {
            server = ConfiguracionServer.getConfiguracionServer().getConfiguracion().getServer();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Server: "+server);
        version.setIdConfiguracion(1);
        version.setNombre("version");
        version.setDescripcion("6.7.0");
        mySystemTray = MySystemTray.getMySystemTray(this);
        
        setDefaultCloseOperation(0);
        setResizable(false);
        setLocationRelativeTo(null);
        
        actualizador = new Actualizador(this);
        ventanaLogin();
        actualizador.start();
        crearArchivoBlockeo();
        conectarAlServidor();
        try{
            verificarVersion();
            verificarLibreria();
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e+" - No hay conexion al servidor","Error", JOptionPane.ERROR_MESSAGE);
            salirPrograma();
        }
        
        
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
    
    public static String getServer(){
        return Main.server;
    }
    /**
     * Verifica la version actual de la aplicacion contra la base de datos y si son diferentes avisa que hay
     * una nueva version o ejecuta la actualizacion si es una actualizacion obligatoria
     */    
    public void verificarVersion(){
        if(ConfiguracioneServ.getConfiguracioneServ().validarVersion(version)){
            if(ConfiguracioneServ.getConfiguracioneServ().getUltimaVersion().getObligatorio()){
                JOptionPane.showMessageDialog(this, "Hay una nueva actualizacion obligatoria!");
                actualizarPrograma();
            }else{
                JOptionPane.showMessageDialog(this, "Hay una nueva actualizacion!");
            }
            
        }
        
    }
    
    /**
     * Genera un archivo que si ya existe no deja crear otra instancia de la aplicacion
     */
    public void crearArchivoBlockeo(){
        File aBorrar = new File("Block.info").getAbsoluteFile();
        if(aBorrar.delete()){
             //System.out.println("Se borro archivo");
        }else{
            //System.out.println("No se borro archivo");
        }
        
        String ruta = new File("Block.info").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        System.out.println(ruta);
        
        if(archivo.exists()) {
            JOptionPane.showMessageDialog(this, "Ya hay una instancia del programa abierta");
            System.exit(0);
        } else {
            try {
                bw = new BufferedWriter(new FileWriter(archivo));
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                bw.write("Desarrollado por MB.\n No tocar este archivo.");
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Valida los permisos de la aplicacion
     */
    public void validarPermisos(){
        serviciosP = PermisoServ.getPermisoServ();
        List<Permisos> permisosU = LoginEJB.usuario.getFkRol().getPermisosList();
        jMB_bar.setVisible(true);
        
        //miTickets
        if(permisosU.contains(serviciosP.traerUno(4))){
            mi_bandejaEntrada.setVisible(true);
        }else{
            mi_bandejaEntrada.setVisible(false);
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
            mi_asignar.setVisible(true);
        }else{
            mi_asignar.setVisible(false);
        }
        //roles
        if(permisosU.contains(serviciosP.traerUno(35))){
            mi_roles.setVisible(true);
        }else{
            mi_roles.setVisible(false);
        }
        //empleados
        if(permisosU.contains(serviciosP.traerUno(40))){
            mi_empleados.setVisible(true);
        }else{
            mi_empleados.setVisible(false);
        }
        //Edificios
        if(permisosU.contains(serviciosP.traerUno(47))){
            mi_edificios.setVisible(true);
        }else{
            mi_edificios.setVisible(false);
        }
        
        //ConfigurarSistema
        if(permisosU.contains(serviciosP.traerUno(51))){
            mi_sistema.setVisible(true);
        }else{
            mi_sistema.setVisible(false);
        }
        
        //Proyectos de Resolucion
        if(permisosU.contains(serviciosP.traerUno(52))){
            mi_resproyect.setVisible(true);
        }else{
            mi_resproyect.setVisible(false);
        }
        
        //Resoluciones
        if(permisosU.contains(serviciosP.traerUno(53))){
            mi_resos.setVisible(true);
        }else{
            mi_resos.setVisible(false);
        }
        
        //Resoluciones
        if(permisosU.contains(serviciosP.traerUno(55))){
            mi_estados.setVisible(true);
        }else{
            mi_estados.setVisible(false);
        }
        
        //Menu estadisticas
        if(permisosU.contains(serviciosP.traerUno(56))){
            jm_estadisticas.setVisible(true);
        }else{
            jm_estadisticas.setVisible(false);
        }
    }
    
    /**
     * Este metodo se conecta al servidor donde estan los archivos adjuntos guardados
     */
    public void conectarAlServidor(){
        try {
            Funciones.connectToServer();
        } catch (IOException ex) {
            Logger.getLogger(BandejaTickets.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: "+ex);
        }
    }
    
    /**
     * Se desconecta del servidor
     */
    public void desconectarDelServidor(){
        Funciones.dissconectFromServer();
    }
    
    public boolean validarPermisos(int idPer){
        serviciosP = PermisoServ.getPermisoServ();
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
     * Ventana de ayuda de la app. Es un Frame
     */
    public void ventanaAyuda(){
        if(aiuda == null){
            aiuda = new AyudaD(this,true);
        }else{
            //aiuda.panelInfoP();
            aiuda.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ventana de ayuda de la app. Es un Frame
     */
    public void misServicios(){
        if(miser == null){
            miser = new MiServicios(this,true);
        }else{
            //miser.panelInfoP();
            miser.setVisible(true);
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
     * Ventana para modificar patrimonio de un ticket. Es un Frame
     */
    public void modificarPatrimonio(Tickets elTi){
        if(patri == null){
            patri = new ModificarPatrimonio(this, false, elTi);
        }else{
            patri.setearValores(elTi);
            patri.setVisible(true);
        }
        revalidate();
    }
    
     /**
     * Ventana para modificar nota entrada de un ticket. Es un Frame
     */
    public void modificarNotaEntrada(Tickets elTi){
        if(entraa == null){
            entraa = new ModificarNotaEntrada(this, false, elTi);
        }else{
            entraa.setearValores(elTi);
            entraa.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ventana para modificar nota salida de un ticket. Es un Frame
     */
    public void modificarNotaSalida(Tickets elTi){
        if(salia == null){
            salia = new ModificarNotaSalida(this, false, elTi);
        }else{
            salia.setearValores(elTi);
            salia.setVisible(true);
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
    public void bandejaEntrada(){
        bandejaEntrada = BandejaTickets.getBandejaTickets(this);
        
        if(!bandejaEntrada.isVisible() == false){
            getContentPane().add(bandejaEntrada);
        }else{
            bandejaEntrada.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Formulario para usuarios
     */
    public void bandejaSalida(){
        bandejaSalida = BandejaEnviados.getBandejaEnviados(this);
        
        if(!bandejaSalida.isVisible() == false){
            getContentPane().add(bandejaSalida);
        }else{
            bandejaSalida.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Vista de confifuracion de la tabla de bandeja de entrada
     */
    public void configurarTabla(){
        configTabla = ConfigurarTabla.getConfigurarTabla(this);
        
        if(!configTabla.isVisible() == false){
            getContentPane().add(configTabla);
        }else{
            configTabla.setVisible(true);
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
            responder = new ResponderD(this,false,miTick);
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
            resoF = new ResolucionD(this,true,miTick,"si");
        }else{
            resoF.ResolucionM(miTick,"si");
            resoF.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ventana para dar de alta nueva area
     */
    public void adjuntosTickets(Tickets miTick){
        if(adjuntos == null){
            adjuntos = new AdjuntosD(this, true, miTick);
        }else{
            adjuntos.setearValores(miTick);
            adjuntos.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     * Abre ventana para escribir la resolucion del ticket sin marcarlo resuelto
     * @param miTick 
     */
    public void resolucionTicketSinMarcar(Tickets miTick){
        if(resoF == null){
            resoF = new ResolucionD(this,true,miTick,"no");
        }else{
            resoF.ResolucionM(miTick,"no");
            resoF.setVisible(true);
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
     * @param miArea
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
     * @param miTick
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
    public void detalleTicket(HistorialTickets miBase){
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
            nuevaArea.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Llamada a la ventana de edificios
     */
    public void edificios(){
        edificiosV = edificiosV.getEdificiosV(this);
        
        if(!edificiosV.isVisible() == false){
            getContentPane().add(edificiosV);
        }else{
            edificiosV.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Llamada a la ventana para generar un nuevo edificio
     */
    public void nuevoEdificio(){
        nuevoEdificio = NuevoEdificio.getNuevoEdificio(this);
        
        if(!nuevoEdificio.isVisible() == false){
            getContentPane().add(nuevoEdificio);
        }else{
            nuevoEdificio.setVisible(true);
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
     * Nueva nota
     */
    public void nuevaNota(){
        
    }
    
    /**
     * Proyecto de resolucion
     */
    public void proyectosResolucion(){
        resproyect = ProyectosResoluciones.getProyectosResoluciones(this);

        if(!resproyect.isVisible()==false){
            getContentPane().add(resproyect);
        }else{
            resproyect.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Ventana para dar de alta nueva area
     */
    public void detalleProyectoResolucion(ResolucionesProyecto miProy){
        if(detalleProy == null){
            detalleProy = new DetalleProyecto(this, true, miProy);
        }else{
            detalleProy.setearValores(miProy);
            detalleProy.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Resoluciones
     */
    public void resoluciones(){
        reso = Resoluciones.getResoluciones(this);

        if(!reso.isVisible()==false){
            getContentPane().add(reso);
        }else{
            reso.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     * Resoluciones
     */
    public void estadosPorAsunto(){
        estadosAsuntos = EstadosAsuntos.getEstadosAsuntos();

        if(!estadosAsuntos.isVisible()==false){
            getContentPane().add(estadosAsuntos);
        }else{
            estadosAsuntos.setVisible(true);
        }
        revalidate();
    }
    
    
    /**
     * Estadisticas areas
     */
    public void estadisticasPorArea(){
        estadAreas = EstadisticasAreasRecibidos.getEstadisticasAreasRecibidos();

        if(!estadAreas.isVisible() == false){
            getContentPane().add(estadAreas);
        }else{
            estadAreas.setVisible(true);
        }
        revalidate();
    }
    
    /**
     * Estadisticas areas
     */
    public void estadisticasPorUsuario(Integer idArea){
        estadUsuarios = EstadisticasUsuariosEmitidos.getEstadisticasUsuarios(idArea);

        if(!estadUsuarios.isVisible()==false){
            getContentPane().add(estadUsuarios);
        }else{
            estadUsuarios.llenarTabla(idArea);
            estadUsuarios.setVisible(true);
        }
        revalidate();
    }
    
    
    
    
    
    
    
    
    
    
    
    /**
     * Metodo para actualizar el programa
     */
    private void actualizarPrograma(){
        try {
            Runtime.getRuntime().exec("java -jar TicketneitorUpdater.jar");
            salirPrograma();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    /**
     * Metodo que cierra el programa
     */
    public void salirPrograma(){
        File aBorrar = new File("Block.info").getAbsoluteFile();
        
        try {
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(aBorrar.delete()){
             //System.out.println("Se borro archivo");
        }else{
            //System.out.println("No se borro archivo");
        }
        desconectarDelServidor();
        System.exit(0);
    }
    
    public boolean existeArchivo(File lib){
         if(lib.exists()){
            return true;
        }else{
            return false;
        }
    }
    
    public void verificarLibreria(){
        
        List<String> agregarLibrerias = new ArrayList<>();
        List<String> borrarLibrerias = new ArrayList<>();
        
        /**
         * Librerias a agregar
         */
        agregarLibrerias.add("lib/jtds-1.3.1.jar");
        agregarLibrerias.add("lib/iText-5.0.5.jar");
        /**
         * Librferias a borrar
         */
        borrarLibrerias.add("lib/iText-2.1.7.js2.jar");
        
        /**
         * Agrego las librerias nuevas
         */
        
        for(int i = 0; i < agregarLibrerias.size(); i++){
            File lib = new File(agregarLibrerias.get(i)).getAbsoluteFile();
            if(lib.exists()){
            
            }else{
                try {
                    copiarLibreria(agregarLibrerias.get(i));
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        for(int i = 0; i < borrarLibrerias.size(); i++){
            File lib = new File(borrarLibrerias.get(i)).getAbsoluteFile();
            if(lib.exists()){
                lib.delete();
            }else{
                System.out.println("No existe la libreria: "+borrarLibrerias.get(i));
            }
        }
    }
    
    public void copiarLibreria(String lib) throws MalformedURLException{
        String fuente = "smb://10.20.130.242/TicketneitorDeskUpdt/"+lib; 
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", "administrador", "cavaliere");
        
        SmbFile dir = new SmbFile(fuente, auth);
        
        System.out.println("Copiando libreria: "+lib);
        
        
        InputStream in;
        try {
            in = dir.getInputStream();
            Files.copy(in,new File(lib).toPath());
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(this, "Error al copiar archivo: "+ex);
        }
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
        mi_bandejaEntrada = new javax.swing.JMenuItem();
        mi_bandejaSalida = new javax.swing.JMenuItem();
        mi_nuevoTicket = new javax.swing.JMenuItem();
        mi_conocimiento = new javax.swing.JMenuItem();
        mi_resproyect = new javax.swing.JMenuItem();
        mi_resos = new javax.swing.JMenuItem();
        mi_conocimiento1 = new javax.swing.JMenuItem();
        mi_salir = new javax.swing.JMenuItem();
        mi_aactualizar = new javax.swing.JMenuItem();
        jm_administracion = new javax.swing.JMenu();
        mi_administrar = new javax.swing.JMenuItem();
        mi_estadoPGM = new javax.swing.JMenuItem();
        jM_configuracion = new javax.swing.JMenu();
        mi_edificios = new javax.swing.JMenuItem();
        mi_areas = new javax.swing.JMenuItem();
        mi_asunutos = new javax.swing.JMenuItem();
        mi_servicios = new javax.swing.JMenuItem();
        mi_estados = new javax.swing.JMenuItem();
        mi_razones = new javax.swing.JMenuItem();
        mi_usuarios = new javax.swing.JMenuItem();
        mi_asignar = new javax.swing.JMenuItem();
        mi_roles = new javax.swing.JMenuItem();
        mi_empleados = new javax.swing.JMenuItem();
        mi_sistema = new javax.swing.JMenuItem();
        mi_empleados1 = new javax.swing.JMenuItem();
        jm_estadisticas = new javax.swing.JMenu();
        mi_ticketsArea = new javax.swing.JMenuItem();

        chkbx_thread.setText("jCheckBox1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());

        jMB_bar.setBackground(new java.awt.Color(153, 153, 153));

        jM_archivo.setText("Archivo");
        jM_archivo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        mi_inicio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_inicio.setText("Inicio");
        mi_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_inicioActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_inicio);

        mi_bandejaEntrada.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mi_bandejaEntrada.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_bandejaEntrada.setText("Bandeja entrada");
        mi_bandejaEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_bandejaEntradaActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_bandejaEntrada);

        mi_bandejaSalida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mi_bandejaSalida.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_bandejaSalida.setText("Bandeja enviados");
        mi_bandejaSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_bandejaSalidaActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_bandejaSalida);

        mi_nuevoTicket.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mi_nuevoTicket.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_nuevoTicket.setText("Nuevo ticket");
        mi_nuevoTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_nuevoTicketActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_nuevoTicket);

        mi_conocimiento.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mi_conocimiento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_conocimiento.setText("Tickets resueltos");
        mi_conocimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_conocimientoActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_conocimiento);

        mi_resproyect.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_resproyect.setText("Proyectos de Resolucion");
        mi_resproyect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_resproyectActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_resproyect);

        mi_resos.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_resos.setText("Resoluciones");
        mi_resos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_resosActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_resos);

        mi_conocimiento1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        mi_conocimiento1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_conocimiento1.setText("Mis servicios");
        mi_conocimiento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_conocimiento1ActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_conocimiento1);

        mi_salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mi_salir.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_salir.setText("Salir");
        mi_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_salirActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_salir);

        mi_aactualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        mi_aactualizar.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_aactualizar.setText("Actualizar");
        mi_aactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_aactualizarActionPerformed(evt);
            }
        });
        jM_archivo.add(mi_aactualizar);

        jMB_bar.add(jM_archivo);

        jm_administracion.setText("Administracion");
        jm_administracion.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        mi_administrar.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_administrar.setText("Administrar tickets");
        mi_administrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_administrarActionPerformed(evt);
            }
        });
        jm_administracion.add(mi_administrar);

        mi_estadoPGM.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_estadoPGM.setText("Estado del PGM");
        mi_estadoPGM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_estadoPGMActionPerformed(evt);
            }
        });
        jm_administracion.add(mi_estadoPGM);

        jMB_bar.add(jm_administracion);

        jM_configuracion.setText("Configuracion");
        jM_configuracion.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        mi_edificios.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_edificios.setText("Edificios");
        mi_edificios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_edificiosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_edificios);

        mi_areas.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_areas.setText("Areas");
        mi_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_areasActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_areas);

        mi_asunutos.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_asunutos.setText("Asuntos");
        mi_asunutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_asunutosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_asunutos);

        mi_servicios.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_servicios.setText("Servicios");
        mi_servicios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_serviciosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_servicios);

        mi_estados.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_estados.setText("Estados de asuntos");
        mi_estados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_estadosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_estados);

        mi_razones.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_razones.setText("Razones de transferencia");
        mi_razones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_razonesActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_razones);

        mi_usuarios.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_usuarios.setText("Usuarios");
        mi_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_usuariosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_usuarios);

        mi_asignar.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_asignar.setText("Asignar servicios a usuario");
        mi_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_asignarActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_asignar);

        mi_roles.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_roles.setText("Roles");
        mi_roles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_rolesActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_roles);

        mi_empleados.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_empleados.setText("Empleados");
        mi_empleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_empleadosActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_empleados);

        mi_sistema.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_sistema.setText("Sistema");
        mi_sistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_sistemaActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_sistema);

        mi_empleados1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        mi_empleados1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_empleados1.setText("Ayuda");
        mi_empleados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_empleados1ActionPerformed(evt);
            }
        });
        jM_configuracion.add(mi_empleados1);

        jMB_bar.add(jM_configuracion);

        jm_estadisticas.setText("Estadisticas");
        jm_estadisticas.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        mi_ticketsArea.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        mi_ticketsArea.setText("Tickets por area");
        mi_ticketsArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_ticketsAreaActionPerformed(evt);
            }
        });
        jm_estadisticas.add(mi_ticketsArea);

        jMB_bar.add(jm_estadisticas);

        setJMenuBar(jMB_bar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mi_bandejaEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_bandejaEntradaActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        bandejaEntrada();
        this.repaint();
    }//GEN-LAST:event_mi_bandejaEntradaActionPerformed

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
            salirPrograma();
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

    private void mi_bandejaSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_bandejaSalidaActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        bandejaSalida();
        this.repaint();
    }//GEN-LAST:event_mi_bandejaSalidaActionPerformed

    private void mi_empleados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_empleados1ActionPerformed
        // TODO add your handling code here:
        //this.getContentPane().removeAll();
        ventanaAyuda();
        //this.repaint();
    }//GEN-LAST:event_mi_empleados1ActionPerformed

    private void mi_conocimiento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_conocimiento1ActionPerformed
        // TODO add your handling code here:
        misServicios();
    }//GEN-LAST:event_mi_conocimiento1ActionPerformed

    private void mi_aactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_aactualizarActionPerformed
        // TODO add your handling code here:
            if(ConfiguracioneServ.getConfiguracioneServ().validarVersion(version)){
                if(JOptionPane.showConfirmDialog(null,"Seguro desea actualizar", "Confirmar", JOptionPane.YES_NO_OPTION)==0){
                    actualizarPrograma();
                }
            }else{
                JOptionPane.showMessageDialog(this, "No hay actualizaciones nuevas");
            }
    }//GEN-LAST:event_mi_aactualizarActionPerformed

    private void mi_edificiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_edificiosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        edificios();
        this.repaint();
    }//GEN-LAST:event_mi_edificiosActionPerformed

    private void mi_sistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_sistemaActionPerformed
        // TODO add your handling code here:
        try {
            server = ConfiguracionServer.getConfiguracionServer().crearNuevaConfiguracion().getServer();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mi_sistemaActionPerformed

    private void mi_resproyectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_resproyectActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        proyectosResolucion();
        this.repaint();
    }//GEN-LAST:event_mi_resproyectActionPerformed

    private void mi_resosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_resosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        resoluciones();
        this.repaint();
    }//GEN-LAST:event_mi_resosActionPerformed

    private void mi_estadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_estadosActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        estadosPorAsunto();
        this.repaint();
    }//GEN-LAST:event_mi_estadosActionPerformed

    private void mi_ticketsAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_ticketsAreaActionPerformed
        // TODO add your handling code here:
        this.getContentPane().removeAll();
        estadisticasPorArea();
        this.repaint();
    }//GEN-LAST:event_mi_ticketsAreaActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
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
    private javax.swing.JMenu jm_estadisticas;
    private javax.swing.JMenuItem mi_aactualizar;
    private javax.swing.JMenuItem mi_administrar;
    private javax.swing.JMenuItem mi_areas;
    private javax.swing.JMenuItem mi_asignar;
    private javax.swing.JMenuItem mi_asunutos;
    private javax.swing.JMenuItem mi_bandejaEntrada;
    private javax.swing.JMenuItem mi_bandejaSalida;
    private javax.swing.JMenuItem mi_conocimiento;
    private javax.swing.JMenuItem mi_conocimiento1;
    private javax.swing.JMenuItem mi_edificios;
    private javax.swing.JMenuItem mi_empleados;
    private javax.swing.JMenuItem mi_empleados1;
    private javax.swing.JMenuItem mi_estadoPGM;
    private javax.swing.JMenuItem mi_estados;
    private javax.swing.JMenuItem mi_inicio;
    private javax.swing.JMenuItem mi_nuevoTicket;
    private javax.swing.JMenuItem mi_razones;
    private javax.swing.JMenuItem mi_resos;
    private javax.swing.JMenuItem mi_resproyect;
    private javax.swing.JMenuItem mi_roles;
    private javax.swing.JMenuItem mi_salir;
    private javax.swing.JMenuItem mi_servicios;
    private javax.swing.JMenuItem mi_sistema;
    private javax.swing.JMenuItem mi_ticketsArea;
    private javax.swing.JMenuItem mi_usuarios;
    // End of variables declaration//GEN-END:variables

}
