/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import mscb.tick.negocio.EmpleadoServ;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.ComplejidadServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.Complejidad;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.util.MenuP;
import mscb.tick.util.reportes.EjecutarReporte;

/**
 *
 * @author Administrador
 */
public class ResponderP extends MenuP {
    private ResponderD mainFrame;
    private static ResponderP estePanel;
    private Tickets miTick;
    private BandejaTickets panelMisti;
    private EmpleadoServ serviciosE;
    private EjecutarReporte report;
    /**
     * Creates new form ResponderP
     */
    private ResponderP(Tickets miTick, ResponderD mainFrame) {
        initComponents();
        panelMisti = BandejaTickets.getBandejaTickets(Main.getMainFrame());
        this.mainFrame = mainFrame;
        this.miTick = miTick;
        serviciosE = EmpleadoServ.getEmpleadoServ();
        txtA_respuesta.setLineWrap(true);
        lbl_ticket.setText(miTick.getIdTicket().toString());
        lbl_usuarioE.setText(miTick.getCreador().getFkEmpleado().getNombre()+" "+miTick.getCreador().getFkEmpleado().getApellido());
        lbl_areaE.setText(miTick.getCreador().getFkEmpleado().getFkArea().getNombreArea());
        if(miTick.getFkEdificio() != null){
            lbl_edificio.setText(miTick.getFkEdificio().getNombre());
        }
        lbl_estado.setText(miTick.getUltimoEstado().getNombre());
        lbl_servicio.setText(miTick.getServicio().getPertenece().getNombre()+" - "+miTick.getServicio().getNombreasuntoS());
        txtA_obser.setText(miTick.getObservacion());
        txtA_obser.setEditable(false);
        txtA_obser.setLineWrap(true);
        txtA_obser.setWrapStyleWord(true);
        txtA_reso.setLineWrap(true);
        txtA_Resos.setLineWrap(true);
        txtA_Resos.setWrapStyleWord(true);
        txtA_Resos.setText(miTick.getResolucion());
        txtA_Resos.setEditable(false);
        lbl_dias.setText(dias(miTick.getFecha()).toString());
        lbl_dias1.setText(dias(miTick.getUltimoHistorial().getFecha()).toString());
        if(miTick.getNotaEntrada() != null){
            lbl_notaEntrada.setText(miTick.getNotaEntrada());
        }
        txt_n_nota.setText("0000");
        txt_area_nota.setText("2017");
        if(Main.getMainFrame().validarPermisos(57)){
            cmbx_complejidad.setVisible(true);
            lblComplejidad.setVisible(true);
        }else{
            cmbx_complejidad.setVisible(false);
            lblComplejidad.setVisible(false);
        }
        report = EjecutarReporte.getEjecutarReporte();
        cargarEstados();
        cargarComplejidad();
        setVisible(true);
    }
    
    public static ResponderP getResponder(Tickets miTick, ResponderD mainFrame){
        if(estePanel == null ){
            estePanel = new ResponderP(miTick, mainFrame);
        }
        return estePanel;
    }
    
    public static ResponderP getResponder(){
        if(estePanel == null ){
            JOptionPane.showMessageDialog(estePanel, "Aun no se abrio mensajes");
        }
        return estePanel;
    }
    
    public Integer dias(Date miFe){
        Date hoy = new Date();
        long diferenciaEn_ms = hoy.getTime() - miFe.getTime();
        long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
        return (int) dias;
    }
    
    private void actualizazrTicket(){
        miTick = TicketServ.getTicketServ().buscarUno(miTick.getIdTicket());
    }
    private void actualizarLabelEstado(){
        actualizazrTicket();
        lbl_estado.setText(miTick.getUltimoEstado().getNombre());
        txtA_Resos.setText(miTick.getResolucion());
        txtA_obser.setText(miTick.getObservacion());
    }
    
    private void tomarTicket(){
        HistorialTickets his = new HistorialTickets();
        his.setFecha(new Date());
        his.setHora(new Date());
        his.setFkEstado(EstadoServ.getEstadoServ().traerEstado(2));
        his.setFkTicket(miTick);
        his.setResolucion(miTick.getResolucion());
        his.setFkUsuario(LoginEJB.usuario);
        HistorialServ.getHistorialServ().nuevo(his);
        actualizarLabelEstado();
        panelMisti.llenarTabla();
    }
    
    /**
     * Abrir archivo externo
     * @param archivo 
     */
    public void abrirArchivo(String archivo){
        if (Desktop.isDesktopSupported()) {
           try {
               Desktop dk = Desktop.getDesktop();
               dk.browse(new URI(archivo));
           } catch (IOException | URISyntaxException e1) {
                try {
                    Desktop dk = Desktop.getDesktop();
                    dk.browse(new URI(archivo));
                } catch (IOException | URISyntaxException e2) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e2.getMessage());
                }
            }
        }
    }
    
    public void cargarEstados(){
        if(traerEstados()> 0){
            cmbx_estados.setVisible(true);
            lblEstado.setVisible(true);
            btn_cambiarEstados.setVisible(true);
        }else{
            cmbx_estados.setVisible(false);
            lblEstado.setVisible(false);
            btn_cambiarEstados.setVisible(false);
        }
    }
    
    public void cargarComplejidad(){
        List<Complejidad> miLista = ComplejidadServ.getComplejidadServ().getComplejidades();
        
        for(int i = 0; i < miLista.size(); i++){
            cmbx_complejidad.addItem(miLista.get(i));
        }
    }
    
    public Integer traerEstados(){
        List<Estados> miLista = EstadoServ.getEstadoServ().getEstadosPorAsunto(miTick.getServicio().getPertenece().getIdasuntoP());
        for(int i = 0; i < miLista.size(); i++){
            cmbx_estados.addItem(miLista.get(i));
        }
        return miLista.size();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cerrar = new javax.swing.JButton();
        btn_enviarMensaje = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_ticket = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_usuarioE = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_areaE = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_respuesta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_obser = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        lbl_edificio = new javax.swing.JLabel();
        lbl_servicio = new javax.swing.JLabel();
        lbl_dias = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_dias1 = new javax.swing.JLabel();
        btn_tomar = new javax.swing.JButton();
        btn_transferir = new javax.swing.JButton();
        btn_enEspera = new javax.swing.JButton();
        btn_trabajando = new javax.swing.JButton();
        btn_control = new javax.swing.JButton();
        btn_ver_adjunto = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lbl_estado = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtA_Resos = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtA_reso = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_n_nota = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txt_area_nota = new javax.swing.JTextField();
        btn_guardarReso = new javax.swing.JButton();
        cbxResuelto = new javax.swing.JCheckBox();
        btn_responder1 = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        cmbx_estados = new javax.swing.JComboBox();
        btn_cambiarEstados = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        lbl_notaEntrada = new javax.swing.JLabel();
        cmbx_complejidad = new javax.swing.JComboBox();
        lblComplejidad = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responder", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N

        btn_cerrar.setBackground(new java.awt.Color(153, 153, 153));
        btn_cerrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_cerrar.setForeground(new java.awt.Color(0, 108, 118));
        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/close.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        btn_enviarMensaje.setBackground(new java.awt.Color(153, 153, 153));
        btn_enviarMensaje.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_enviarMensaje.setForeground(new java.awt.Color(0, 108, 118));
        btn_enviarMensaje.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/send-button.png"))); // NOI18N
        btn_enviarMensaje.setText("Enviar mensaje");
        btn_enviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarMensajeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Ticket Nº:");

        lbl_ticket.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_ticket.setForeground(new java.awt.Color(0, 108, 118));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 108, 118));
        jLabel3.setText("de:");

        lbl_usuarioE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_usuarioE.setForeground(new java.awt.Color(0, 108, 118));

        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 108, 118));
        jLabel5.setText("de:");

        lbl_areaE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_areaE.setForeground(new java.awt.Color(0, 108, 118));

        txtA_respuesta.setColumns(20);
        txtA_respuesta.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtA_respuesta.setForeground(new java.awt.Color(0, 108, 118));
        txtA_respuesta.setRows(5);
        jScrollPane1.setViewportView(txtA_respuesta);

        txtA_obser.setColumns(20);
        txtA_obser.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtA_obser.setForeground(new java.awt.Color(0, 108, 118));
        txtA_obser.setRows(5);
        jScrollPane2.setViewportView(txtA_obser);

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 108, 118));
        jLabel4.setText("Lugar de trabajo:");

        lbl_edificio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_edificio.setForeground(new java.awt.Color(0, 108, 118));

        lbl_servicio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_servicio.setForeground(new java.awt.Color(0, 108, 118));

        lbl_dias.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_dias.setForeground(new java.awt.Color(0, 108, 118));

        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 108, 118));
        jLabel6.setText("Dias desde que se creó:");

        jLabel7.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 108, 118));
        jLabel7.setText("Dias sin movimientos:");

        lbl_dias1.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_dias1.setForeground(new java.awt.Color(0, 108, 118));

        btn_tomar.setBackground(new java.awt.Color(153, 153, 153));
        btn_tomar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_tomar.setForeground(new java.awt.Color(0, 108, 118));
        btn_tomar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/ok.png"))); // NOI18N
        btn_tomar.setText("Aceptar");
        btn_tomar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tomarActionPerformed(evt);
            }
        });

        btn_transferir.setBackground(new java.awt.Color(153, 153, 153));
        btn_transferir.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_transferir.setForeground(new java.awt.Color(0, 108, 118));
        btn_transferir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/transfer.png"))); // NOI18N
        btn_transferir.setText("Transferir");
        btn_transferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferirActionPerformed(evt);
            }
        });

        btn_enEspera.setBackground(new java.awt.Color(153, 153, 153));
        btn_enEspera.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_enEspera.setForeground(new java.awt.Color(0, 108, 118));
        btn_enEspera.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/loading.png"))); // NOI18N
        btn_enEspera.setText("En espera");
        btn_enEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enEsperaActionPerformed(evt);
            }
        });

        btn_trabajando.setBackground(new java.awt.Color(153, 153, 153));
        btn_trabajando.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_trabajando.setForeground(new java.awt.Color(0, 108, 118));
        btn_trabajando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/work.png"))); // NOI18N
        btn_trabajando.setText("En trabajo");
        btn_trabajando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_trabajandoActionPerformed(evt);
            }
        });

        btn_control.setBackground(new java.awt.Color(153, 153, 153));
        btn_control.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_control.setForeground(new java.awt.Color(0, 108, 118));
        btn_control.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/work.png"))); // NOI18N
        btn_control.setText("Control");
        btn_control.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_controlActionPerformed(evt);
            }
        });

        btn_ver_adjunto.setBackground(new java.awt.Color(153, 153, 153));
        btn_ver_adjunto.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_ver_adjunto.setForeground(new java.awt.Color(0, 108, 118));
        btn_ver_adjunto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/attachment.png"))); // NOI18N
        btn_ver_adjunto.setText("Ver adjunto");
        btn_ver_adjunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_adjuntoActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 108, 118));
        jLabel8.setText("Estado:");

        lbl_estado.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_estado.setForeground(new java.awt.Color(0, 108, 118));

        txtA_Resos.setColumns(20);
        txtA_Resos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtA_Resos.setForeground(new java.awt.Color(0, 108, 118));
        txtA_Resos.setRows(5);
        jScrollPane3.setViewportView(txtA_Resos);

        txtA_reso.setColumns(20);
        txtA_reso.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtA_reso.setForeground(new java.awt.Color(0, 108, 118));
        txtA_reso.setRows(5);
        jScrollPane4.setViewportView(txtA_reso);

        jLabel9.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 108, 118));
        jLabel9.setText("Servicio:");

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 108, 118));
        jLabel10.setText("Mensajes:");

        jLabel11.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 108, 118));
        jLabel11.setText("Soluciones:");

        jLabel12.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 108, 118));
        jLabel12.setText("Nuevo mensaje:");

        jLabel13.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 108, 118));
        jLabel13.setText("Nueva solucion:");

        jLabel14.setBackground(new java.awt.Color(0, 102, 204));
        jLabel14.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 108, 118));
        jLabel14.setText("Nota salida / Resolucion:");

        txt_n_nota.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_n_nota.setForeground(new java.awt.Color(0, 108, 118));
        txt_n_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_n_notaActionPerformed(evt);
            }
        });
        txt_n_nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_n_notaKeyTyped(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(0, 102, 204));
        jLabel15.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 108, 118));
        jLabel15.setText("-");

        txt_area_nota.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_area_nota.setForeground(new java.awt.Color(0, 108, 118));
        txt_area_nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_area_notaKeyTyped(evt);
            }
        });

        btn_guardarReso.setBackground(new java.awt.Color(153, 153, 153));
        btn_guardarReso.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_guardarReso.setForeground(new java.awt.Color(0, 108, 118));
        btn_guardarReso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/save.png"))); // NOI18N
        btn_guardarReso.setText("Guardar resolucion");
        btn_guardarReso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarResoActionPerformed(evt);
            }
        });

        cbxResuelto.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxResuelto.setForeground(new java.awt.Color(0, 108, 118));
        cbxResuelto.setText("Marcar como resuelto");

        btn_responder1.setBackground(new java.awt.Color(153, 153, 153));
        btn_responder1.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_responder1.setForeground(new java.awt.Color(0, 108, 118));
        btn_responder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/print.png"))); // NOI18N
        btn_responder1.setText("Imprimir");
        btn_responder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_responder1ActionPerformed(evt);
            }
        });

        lblEstado.setBackground(new java.awt.Color(0, 102, 204));
        lblEstado.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblEstado.setForeground(new java.awt.Color(0, 108, 118));
        lblEstado.setText("Estados:");

        cmbx_estados.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_estados.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_estados.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_estados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_estadosActionPerformed(evt);
            }
        });

        btn_cambiarEstados.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarEstados.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_cambiarEstados.setForeground(new java.awt.Color(0, 108, 118));
        btn_cambiarEstados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/ok.png"))); // NOI18N
        btn_cambiarEstados.setText("Cambiar");
        btn_cambiarEstados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarEstadosActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 108, 118));
        jLabel16.setText("Nota entrada / Proyecto:");

        lbl_notaEntrada.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_notaEntrada.setForeground(new java.awt.Color(0, 108, 118));

        cmbx_complejidad.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_complejidad.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_complejidad.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_complejidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_complejidadActionPerformed(evt);
            }
        });

        lblComplejidad.setBackground(new java.awt.Color(0, 102, 204));
        lblComplejidad.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblComplejidad.setForeground(new java.awt.Color(0, 108, 118));
        lblComplejidad.setText("Complejidad:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_edificio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_dias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(93, 93, 93)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_dias1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(77, 77, 77))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_estado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(19, 19, 19))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(399, 399, 399)
                                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(222, 222, 222))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_servicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_notaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_ticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(57, 57, 57)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_usuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_tomar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_enEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbx_estados, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_transferir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(1, 1, 1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_cambiarEstados)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_areaE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_ver_adjunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_responder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_trabajando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addGap(283, 283, 283))
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_enviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(241, 241, 241)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(106, 106, 106))
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblComplejidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbx_complejidad, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxResuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btn_guardarReso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_usuarioE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_ticket, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(lbl_areaE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(lbl_edificio, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(lbl_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(lbl_dias1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_dias, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_servicio, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(lbl_notaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_enviarMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbx_complejidad, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblComplejidad)
                            .addComponent(cbxResuelto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_guardarReso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_transferir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_ver_adjunto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_trabajando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_enEspera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_control, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_tomar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblEstado)
                                    .addComponent(cmbx_estados, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_responder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_cambiarEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(1, 1, 1))
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        mainFrame.setVisible(false);
        estePanel = null;
        mainFrame.dispose();
        mainFrame = null;
        System.gc();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_enviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarMensajeActionPerformed
        // TODO add your handling code here:
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        Date fecha = new Date();
        HistorialTickets his = new HistorialTickets();
        miTick = TicketServ.getTicketServ().buscarUno(miTick.getIdTicket());
        System.out.println("Obs: "+miTick.getObservacion());
        String obs = miTick.getObservacion();

        if((obs == null)||(obs.isEmpty())){
            miTick.setObservacion(dateFormatter.format(fecha)+" Mensaje de "+LoginEJB.usuario.getNombreUsuario()+": "+txtA_respuesta.getText());
        }else{
            miTick.setObservacion(obs+"\n"+dateFormatter.format(fecha)+" Mensaje de "+LoginEJB.usuario.getNombreUsuario()+": "+txtA_respuesta.getText());
        }
        his.setFkEstado(EstadoServ.getEstadoServ().traerEstado(4));
        his.setFkUsuario(LoginEJB.usuario);
        his.setFkTicket(miTick);
        his.setFecha(fecha);
        his.setHora(fecha);
        his.setResolucion(miTick.getResolucion());
        TicketServ.getTicketServ().modificarTicket(miTick);
        HistorialServ.getHistorialServ().nuevo(his);
        actualizarLabelEstado();
        txtA_respuesta.setText("");
        panelMisti.llenarTabla();
    }//GEN-LAST:event_btn_enviarMensajeActionPerformed

    private void btn_tomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tomarActionPerformed
        // TODO add your handling code here:
            Usuarios usuarioAct = miTick.getUltimoUsuario();

            //Verifica si el ticket le pertenece a alguien
            if((usuarioAct == null)||(!usuarioAct.equals(LoginEJB.usuario))){
                tomarTicket();
            }else{
                JOptionPane.showMessageDialog(this, "Este ticket ya le pertence!", "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btn_tomarActionPerformed

    private void btn_transferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferirActionPerformed
        // TODO add your handling code here:
        Main.getMainFrame().transferirTicket(miTick);
        actualizazrTicket();
        actualizarLabelEstado();
    }//GEN-LAST:event_btn_transferirActionPerformed

    private void btn_enEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enEsperaActionPerformed
        // TODO add your handling code here:
            if(!miTick.getUltimoEstado().getNombre().equals("En espera")){
                HistorialTickets his = new HistorialTickets();
                his.setFecha(new Date());
                his.setHora(new Date());
                his.setFkEstado(EstadoServ.getEstadoServ().traerEstado(3));
                his.setFkTicket(miTick);
                his.setFkUsuario(LoginEJB.usuario);
                his.setResolucion(miTick.getResolucion());
                HistorialServ.getHistorialServ().nuevo(his);
                actualizarLabelEstado();
                panelMisti.llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en espera!!");
            }
    }//GEN-LAST:event_btn_enEsperaActionPerformed

    private void btn_trabajandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trabajandoActionPerformed
        // TODO add your handling code here:
            if(!miTick.getUltimoEstado().getNombre().equals("Trabajando")){
                HistorialTickets his = new HistorialTickets();
                his.setFecha(new Date());
                his.setHora(new Date());
                his.setFkEstado(EstadoServ.getEstadoServ().traerEstado(6));
                his.setFkTicket(miTick);
                his.setFkUsuario(LoginEJB.usuario);
                his.setResolucion(miTick.getResolucion());
                HistorialServ.getHistorialServ().nuevo(his);
                actualizarLabelEstado();
                panelMisti.llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra trabajando!!");
            }
    }//GEN-LAST:event_btn_trabajandoActionPerformed

    private void btn_controlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_controlActionPerformed
        // TODO add your handling code here:
            if(!miTick.getUltimoEstado().getNombre().equals("Control")){
                HistorialTickets his = new HistorialTickets();
                his.setFecha(new Date());
                his.setHora(new Date());
                his.setFkEstado(EstadoServ.getEstadoServ().traerEstado(8));
                his.setFkTicket(miTick);
                his.setFkUsuario(LoginEJB.usuario);
                his.setResolucion(miTick.getResolucion());
                HistorialServ.getHistorialServ().nuevo(his);
                actualizarLabelEstado();
                panelMisti.llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en control!!");
            }
    }//GEN-LAST:event_btn_controlActionPerformed

    private void btn_ver_adjuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_adjuntoActionPerformed
        // TODO add your handling code here:
        if(miTick.getTicketsAdjuntosList() != null){
            //String arch = "\\\\10.20.130.242\\www\\TicketWeb\\archivos\\"+serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())).getAdjunto();
            //String arch = "file://"+Main.getServer()+"/www/TicketWeb/archivos/"+miTick.getAdjunto();
            //abrirArchivo(arch);
            Main.getMainFrame().adjuntosTickets(miTick);
        }else{
            JOptionPane.showMessageDialog(this, "Este ticket no posse adjunto");
        }
    }//GEN-LAST:event_btn_ver_adjuntoActionPerformed

    private void txt_n_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_n_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_n_notaActionPerformed

    private void txt_n_notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_n_notaKeyTyped
        // TODO add your handling code here:
        /*if(txt_n_nota.getText().length()== 4){
            evt.consume();
        }*/
    }//GEN-LAST:event_txt_n_notaKeyTyped

    private void txt_area_notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_area_notaKeyTyped
        // TODO add your handling code here:
        /*if (txt_area_nota.getText().length()== 8){
            txt_area_nota.setText(txt_area_nota.getText().toUpperCase());
            evt.consume();
        }else{
            txt_area_nota.setText(txt_area_nota.getText().toUpperCase());
        }*/
    }//GEN-LAST:event_txt_area_notaKeyTyped

    private void btn_guardarResoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarResoActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(mainFrame,"Seguro desea enviar?","Confirmar",JOptionPane.YES_NO_OPTION) == 0){
            //Completo numero de nota para que cumpla con el estandar
            if(txt_n_nota.getText().trim().length() < 4){
                for(int i = 0; i < 4-txt_n_nota.getText().trim().length();i++){
                    txt_n_nota.setText("0"+txt_n_nota.getText().trim());
                }
            }
            if(txt_area_nota.getText().trim().isEmpty()){
                txt_area_nota.setText("--------");
            }
            miTick.setNotaSalida(txt_n_nota.getText()+"-"+txt_area_nota.getText().trim());
            
            long milisegundos_dia = 24 * 60 * 60 * 1000;
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            
            cal1.setTime(miTick.getFecha());
            cal2.setTime(Calendar.getInstance().getTime());
            
            int horas = (int)((cal2.getTimeInMillis()-cal1.getTimeInMillis())/milisegundos_dia) *24;
            
            miTick.setTiempoResolucion(horas);
            miTick.setFkComplejidad((Complejidad) cmbx_complejidad.getSelectedItem());
            
            TicketServ.getTicketServ().modificarTicket(miTick);

            Estados estado;

            if(cbxResuelto.isSelected()){
                estado = EstadoServ.getEstadoServ().traerEstado(5);
            }else{
                estado = miTick.getUltimoEstado();
            }

            HistorialTickets his = new HistorialTickets();
            Date fecha = new Date();
            his.setFkTicket(miTick);
            his.setFkEstado(estado);
            String reso = miTick.getResolucion();
            if((reso == null)||(reso.isEmpty())){
                if(txtA_reso.getText().trim().isEmpty()){
                    his.setResolucion(null);
                }else{
                    his.setResolucion("Resolucion por "+LoginEJB.usuario+" : "+txtA_reso.getText());
                }

            }else{
                his.setResolucion(reso+"\nResolucion por "+LoginEJB.usuario+" : "+txtA_reso.getText());
            }
            his.setFecha(fecha);
            his.setHora(fecha);
            his.setFkUsuario(LoginEJB.usuario);
            HistorialServ.getHistorialServ().nuevo(his);
            /*if(marcar.equals("si")){
                servH.nuevo(his);
            }else{
                his.setIdHistorial(servH.buscarUltimo(miTick).getIdHistorial());
                servH.modificar(his);
            }*/
            actualizarLabelEstado();
            txtA_reso.setText("");
            panelMisti.llenarTabla();
            if(cbxResuelto.isSelected()){
                btn_cerrarActionPerformed(evt);
            }
        }
    }//GEN-LAST:event_btn_guardarResoActionPerformed

    private void btn_responder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_responder1ActionPerformed
        // TODO add your handling code here:
        report.reporteMiTicket(miTick.getIdTicket());
    }//GEN-LAST:event_btn_responder1ActionPerformed

    private void cmbx_estadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_estadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_estadosActionPerformed

    private void btn_cambiarEstadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarEstadosActionPerformed
        // TODO add your handling code here:
        Estados estado = (Estados) cmbx_estados.getSelectedItem();
        if(!miTick.getUltimoEstado().equals(estado)){
                HistorialTickets his = new HistorialTickets();
                his.setFecha(new Date());
                his.setHora(new Date());
                his.setFkEstado(estado);
                his.setFkTicket(miTick);
                his.setFkUsuario(LoginEJB.usuario);
                his.setResolucion(miTick.getResolucion());
                HistorialServ.getHistorialServ().nuevo(his);
                actualizarLabelEstado();
                panelMisti.llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en "+estado.getNombre()+" !!");
            }
    }//GEN-LAST:event_btn_cambiarEstadosActionPerformed

    private void cmbx_complejidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_complejidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_complejidadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiarEstados;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_control;
    private javax.swing.JButton btn_enEspera;
    private javax.swing.JButton btn_enviarMensaje;
    private javax.swing.JButton btn_guardarReso;
    private javax.swing.JButton btn_responder1;
    private javax.swing.JButton btn_tomar;
    private javax.swing.JButton btn_trabajando;
    private javax.swing.JButton btn_transferir;
    private javax.swing.JButton btn_ver_adjunto;
    private javax.swing.JCheckBox cbxResuelto;
    private javax.swing.JComboBox cmbx_complejidad;
    private javax.swing.JComboBox cmbx_estados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblComplejidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lbl_areaE;
    private javax.swing.JLabel lbl_dias;
    private javax.swing.JLabel lbl_dias1;
    private javax.swing.JLabel lbl_edificio;
    private javax.swing.JLabel lbl_estado;
    private javax.swing.JLabel lbl_notaEntrada;
    private javax.swing.JLabel lbl_servicio;
    private javax.swing.JLabel lbl_ticket;
    private javax.swing.JLabel lbl_usuarioE;
    private javax.swing.JTextArea txtA_Resos;
    private javax.swing.JTextArea txtA_obser;
    private javax.swing.JTextArea txtA_reso;
    private javax.swing.JTextArea txtA_respuesta;
    private javax.swing.JTextField txt_area_nota;
    private javax.swing.JTextField txt_n_nota;
    // End of variables declaration//GEN-END:variables
}
