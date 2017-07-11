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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.UsuarioServ;
import static mscb.tick.util.Funciones.connectToServer;
import static mscb.tick.util.Funciones.dissconectFromServer;
import mscb.tick.util.reportes.EjecutarReporte;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class BandejaTickets extends MenuP {
    Main mainFrame;
    private static BandejaTickets estePanel;
    private TicketServ serviciosT;
    private List<Tickets> miLista;
    private DefaultTableModel modelo;
    private HistorialServ servH;
    private EstadoServ esta;
    private UsuarioServ serviciosU;
    private EjecutarReporte report;
    private Date fecha;
    private boolean mostrarEspera;
    
    /**
     * Creates new form MisTickets
     */
    private BandejaTickets(Main mainFrame) {
        initComponents();
        jt_tickets.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.mainFrame = mainFrame;
        servH = HistorialServ.getHistorialServ();
        lblNombreUsuario.setText(LoginEJB.usuario.getNombreUsuario());
        miLista = new ArrayList<>();
        modelo = (DefaultTableModel) jt_tickets.getModel();
        //mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Ticketneitor");
        //setSize(mainFrame.getSize());
        validarPermisos();
        configurarTabla(jt_tickets);
        mostrarEspera = false;
        setVisible(true);
        llenarTabla();
        report = EjecutarReporte.getEjecutarReporte();
    }
    
    public static BandejaTickets getBandejaTickets(Main mainFrame){
        if(estePanel == null){
            estePanel = new BandejaTickets(mainFrame);
        }
        return estePanel;
    }
    
    public void configurarTabla(JTable MiTabla){
        //MiTabla.setIntercellSpacing(Dimension newSpacing);
        MiTabla.setRowHeight(20);
        //MiTabla.setRowHeight(row, rowHeight);
    }
    
    private void validarPermisos(){
        if(mainFrame.validarPermisos(45)){
            btn_patrimonio.setVisible(true);
        }else{
            btn_patrimonio.setVisible(false);
        }
        if(mainFrame.validarPermisos(46)){
            btn_nota_salida.setVisible(true);
        }else{
            btn_nota_salida.setVisible(false);
        }
        
    }
    
    public void llenarTabla() {
        vaciarTabla(jt_tickets);
        serviciosT = TicketServ.getTicketServ();
        miLista = serviciosT.buscarPorUsuarioAsunto();
        //cambiarEstadoDeTicketsRecibidos(); esta funcion cambia el estado del ticket a recibido porquien abre la app
        Integer cantidad = 0;
        
        Comparator<Tickets> compara = Collections.reverseOrder();
        Collections.sort(miLista,compara);
        
        String v[] = new String[11];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        
        for (int i = 0; i < miLista.size(); i++) {
            v[0] = miLista.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(miLista.get(i).getFecha()).toString();
            v[2] = miLista.get(i).getCreador().getFkEmpleado().getFkArea().getNombreArea();
            v[3] = miLista.get(i).getCreador().getNombreUsuario();
            v[4] = miLista.get(i).getUltimoEstado().getNombre();
            v[5] = miLista.get(i).getServicio().getPertenece().getNombre() + " - " + miLista.get(i).getServicio().getNombreasuntoS();
            if(miLista.get(i).getUltimoUsuario() == null){
                v[6] = "No aun";
            }else{
                v[6] = miLista.get(i).getUltimoUsuario().getNombreUsuario();
            }
            if((miLista.get(i).getPatrimonio() == null)||(miLista.get(i).getPatrimonio().isEmpty())){
                v[7] = "Sin";
            }else{
                v[7] = miLista.get(i).getPatrimonio();
            }
            if((miLista.get(i).getAdjunto() == null)||(miLista.get(i).getAdjunto().isEmpty())){
                v[8] = "Sin";
            }else{
                v[8] = miLista.get(i).getAdjunto();
            }
            if((miLista.get(i).getNotaEntrada()== null)||(miLista.get(i).getNotaEntrada().isEmpty())){
                v[9] = "Sin";
            }else{
                v[9] = miLista.get(i).getNotaEntrada();
            }
            if((miLista.get(i).getNotaSalida()== null)||(miLista.get(i).getNotaSalida().isEmpty())){
                v[10] = "Sin";
            }else{
                v[10] = miLista.get(i).getNotaSalida();
            }
            modelo.addRow(v);
            cantidad ++;
        }
        lblCantidadTickets.setText("Cantidad de tickets: "+cantidad.toString());
        revalidate();

    }
    
    private void llenarTablaBuscador(List <Tickets> busca) {
        vaciarTabla(jt_tickets);
        String v[] = new String[11];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        Integer cantidad = 0;
        
        Comparator<Tickets> compara = Collections.reverseOrder();
        Collections.sort(miLista,compara);
        
        for (int i = 0; i < busca.size(); i++) {
            v[0] = busca.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(busca.get(i).getFecha()).toString();
            v[2] = busca.get(i).getCreador().getFkEmpleado().getFkArea().getNombreArea();
            v[3] = busca.get(i).getCreador().getNombreUsuario();
            v[4] = miLista.get(i).getUltimoEstado().getNombre();
            v[5] = busca.get(i).getServicio().getPertenece().getNombre() + " - " + busca.get(i).getServicio().getNombreasuntoS();
            if(miLista.get(i).getUltimoUsuario() == null){
                v[6] = "No aun";
            }else{
                v[6] = miLista.get(i).getUltimoUsuario().getNombreUsuario();
            }
            if((miLista.get(i).getPatrimonio() == null)||(miLista.get(i).getPatrimonio().isEmpty())){
                v[7] = "Sin";
            }else{
                v[7] = miLista.get(i).getPatrimonio();
            }
            if((miLista.get(i).getAdjunto() == null)||(miLista.get(i).getAdjunto().isEmpty())){
                v[8] = "Sin";
            }else{
                v[8] = miLista.get(i).getAdjunto();
            }
            if((miLista.get(i).getNotaEntrada()== null)||(miLista.get(i).getNotaEntrada().isEmpty())){
                v[9] = "Sin";
            }else{
                v[9] = miLista.get(i).getNotaEntrada();
            }
            if((miLista.get(i).getNotaSalida()== null)||(miLista.get(i).getNotaSalida().isEmpty())){
                v[10] = "Sin";
            }else{
                v[10] = miLista.get(i).getNotaSalida();
            }
            modelo.addRow(v);
            cantidad ++;
        }
        lblCantidadTickets.setText("Cantidad de tickets: "+cantidad.toString());
        revalidate();

    }
    
    

    private void vaciarTabla(JTable tabla) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
            int filas = tabla.getRowCount();
            for (int i = 0; filas > i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    
    /**
     * Abrir archivo externo
     * @param archivo 
     */
    public void abrirArchivo(String archivo){
        
        try {
            connectToServer();
        } catch (IOException ex) {
            Logger.getLogger(BandejaTickets.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al conectar con el servidor: "+ex);
        }
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
            dissconectFromServer();
            
    }
    
    
    
    /**
     *Cambia el estado de los tickets enviados a recibidos 
     *//*
    private void cambiarEstadoDeTicketsRecibidos(){
        esta = new EstadoServ();
        Estados estado = esta.traerEstado(2);
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getUltimoEstado().getIdEstado() == 1){
                HistorialTickets his = new HistorialTickets();
                fecha = new Date();
                his.setFecha(fecha);
                his.setFkEstado(estado);
                his.setFkTicket(miLista.get(i));
                his.setFkUsuario(LoginEJB.usuario);
                servH.nuevo(his);
            }
        }
    }*/
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tickets = new javax.swing.JTable();
        lblNombreUsuario = new javax.swing.JLabel();
        btn_verResp = new javax.swing.JButton();
        btn_responder = new javax.swing.JButton();
        btn_enEspera = new javax.swing.JButton();
        btn_tomar = new javax.swing.JButton();
        btn_transferir = new javax.swing.JButton();
        btn_resuelto = new javax.swing.JButton();
        btn_refrescar = new javax.swing.JButton();
        btn_volver = new javax.swing.JButton();
        btn_responder1 = new javax.swing.JButton();
        btn_trabajando = new javax.swing.JButton();
        btn_ver_adjunto = new javax.swing.JButton();
        lblCantidadTickets = new javax.swing.JLabel();
        btn_control = new javax.swing.JButton();
        btn_patrimonio = new javax.swing.JButton();
        btn_nota_salida = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mis Tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N
        setMinimumSize(new java.awt.Dimension(827, 569));

        txt_id.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 108, 118));
        txt_id.setText("Usuario, Servicio, Patrimonio...");
        txt_id.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_idMouseClicked(evt);
            }
        });
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        jt_tickets.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        jt_tickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Ticket", "Fecha", "Area creador", "Creador", "Estado", "Asunto", "Receptor", "Patrimonio", "Adjunto", "Nota entrada", "Nota salida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tickets.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tickets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_ticketsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_tickets);
        if (jt_tickets.getColumnModel().getColumnCount() > 0) {
            jt_tickets.getColumnModel().getColumn(0).setMinWidth(15);
            jt_tickets.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        lblNombreUsuario.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Tekton Pro", 3, 24)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_verResp.setBackground(new java.awt.Color(153, 153, 153));
        btn_verResp.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_verResp.setForeground(new java.awt.Color(0, 108, 118));
        btn_verResp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/email.png"))); // NOI18N
        btn_verResp.setText("Ver resolucion");
        btn_verResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verRespActionPerformed(evt);
            }
        });

        btn_responder.setBackground(new java.awt.Color(153, 153, 153));
        btn_responder.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_responder.setForeground(new java.awt.Color(0, 108, 118));
        btn_responder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/reply-email_small.png"))); // NOI18N
        btn_responder.setText("Responder");
        btn_responder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_responderActionPerformed(evt);
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

        btn_resuelto.setBackground(new java.awt.Color(153, 153, 153));
        btn_resuelto.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_resuelto.setForeground(new java.awt.Color(0, 108, 118));
        btn_resuelto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/check.png"))); // NOI18N
        btn_resuelto.setText("Resuelto");
        btn_resuelto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resueltoActionPerformed(evt);
            }
        });

        btn_refrescar.setBackground(new java.awt.Color(153, 153, 153));
        btn_refrescar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_refrescar.setForeground(new java.awt.Color(0, 108, 118));
        btn_refrescar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/refresh.png"))); // NOI18N
        btn_refrescar.setText("Refrescar");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });

        btn_volver.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_volver.setForeground(new java.awt.Color(0, 108, 118));
        btn_volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/back-arrow.png"))); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_responder1.setBackground(new java.awt.Color(153, 153, 153));
        btn_responder1.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_responder1.setForeground(new java.awt.Color(0, 108, 118));
        btn_responder1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/print.png"))); // NOI18N
        btn_responder1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_responder1ActionPerformed(evt);
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

        lblCantidadTickets.setBackground(new java.awt.Color(0, 102, 204));
        lblCantidadTickets.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidadTickets.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadTickets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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

        btn_patrimonio.setBackground(new java.awt.Color(153, 153, 153));
        btn_patrimonio.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_patrimonio.setForeground(new java.awt.Color(0, 108, 118));
        btn_patrimonio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/work.png"))); // NOI18N
        btn_patrimonio.setText("Patrimonio");
        btn_patrimonio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_patrimonioActionPerformed(evt);
            }
        });

        btn_nota_salida.setBackground(new java.awt.Color(153, 153, 153));
        btn_nota_salida.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_nota_salida.setForeground(new java.awt.Color(0, 108, 118));
        btn_nota_salida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/work.png"))); // NOI18N
        btn_nota_salida.setText("Nota salida");
        btn_nota_salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nota_salidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_tomar, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 66, Short.MAX_VALUE)))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_transferir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_responder)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_enEspera)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_resuelto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_trabajando)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                        .addComponent(btn_control))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_ver_adjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_verResp, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_patrimonio)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_nota_salida)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_responder1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_refrescar))))
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblCantidadTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCantidadTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_tomar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_enEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_transferir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_resuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_trabajando, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_responder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_control, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_responder1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_ver_adjunto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_verResp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_patrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_nota_salida, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btn_refrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseClicked
        // TODO add your handling code here:
        txt_id.setText("");
    }//GEN-LAST:event_txt_idMouseClicked

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        if(txt_id.getText().trim().length() >0){
            llenarTablaBuscador(serviciosT.buscar(txt_id.getText().trim()));
        }
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_verRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verRespActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.verRespuestas(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_verRespActionPerformed

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_btn_refrescarActionPerformed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
       this.setVisible(false);
       mainFrame.menuPrincipal();
       estePanel = null;
       System.gc();
        
    }//GEN-LAST:event_btn_volverActionPerformed

    private void jt_ticketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ticketsMouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == jt_tickets){
            if(evt.getClickCount() == 2){
                if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
                    mainFrame.Observaciones(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
                }else{
                    JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
                }
            }
        }
    }//GEN-LAST:event_jt_ticketsMouseClicked

    private void btn_responder1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_responder1ActionPerformed
        // TODO add your handling code here:
         if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            report.reporteMiTicket(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString()));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_responder1ActionPerformed

    private void btn_trabajandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_trabajandoActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 4).equals("Trabajando")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                EstadoServ esta = EstadoServ.getEstadoServ();
                Estados estado = esta.traerEstado(6);
                HistorialTickets his = new HistorialTickets();
                fecha = new Date();
                his.setFecha(fecha);
                his.setFkEstado(estado);
                his.setFkTicket(miTicket);
                his.setFkUsuario(LoginEJB.usuario);
                servH.nuevo(his);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra trabajando!!");
            }
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_trabajandoActionPerformed

    private void btn_resueltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resueltoActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 4).equals("Resuelto")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                mainFrame.resolucionTicket(miTicket);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en resuelto!!");
            }
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_resueltoActionPerformed

    private void btn_tomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tomarActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            String usuarioAct = modelo.getValueAt(jt_tickets.getSelectedRow(), 6).toString();
            System.out.println(usuarioAct);

            //Verifica si el ticket le pertenece a alguien
            if(usuarioAct.equalsIgnoreCase("No aun")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                HistorialTickets his = new HistorialTickets();
                esta = EstadoServ.getEstadoServ();
                fecha = new Date();
                his.setFecha(fecha);
                his.setFkEstado(esta.traerEstado(2));
                his.setFkTicket(miTicket);
                his.setFkUsuario(LoginEJB.usuario);
                servH.nuevo(his);
                llenarTabla();
            }else{
                //Verifica si el ticket ya le pertenece al que quiere tomarlo
                if(usuarioAct.equalsIgnoreCase(LoginEJB.usuario.getNombreUsuario())){
                    JOptionPane.showMessageDialog(this, "Este ticket ya le pertence!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{// Si no cambia el ticket de propietario
                    Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                    HistorialTickets his = new HistorialTickets();
                    esta = EstadoServ.getEstadoServ();
                    fecha = new Date();
                    his.setFecha(fecha);
                    his.setFkEstado(esta.traerEstado(2));
                    his.setFkTicket(miTicket);
                    his.setFkUsuario(LoginEJB.usuario);
                    servH.nuevo(his);
                    llenarTabla();
                }

            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_tomarActionPerformed

    private void btn_transferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferirActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(JOptionPane.showConfirmDialog(this, "Desea cargar una resolucion para el ticket?", "Base de conocimiento", JOptionPane.YES_NO_OPTION) == 0){
                int ide = Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString());
                mainFrame.transferirTicket(serviciosT.buscarUno(ide));
                mainFrame.resolucionTicketSinMarcar(serviciosT.buscarUno(ide));
                llenarTabla();
            }else{
                mainFrame.transferirTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
                llenarTabla();
            }

        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_transferirActionPerformed

    private void btn_responderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_responderActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.Respuestas(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_responderActionPerformed

    private void btn_enEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enEsperaActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 4).equals("En espera")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                EstadoServ esta = EstadoServ.getEstadoServ();
                Estados estado = esta.traerEstado(3);
                HistorialTickets his = new HistorialTickets();
                fecha = new Date();
                his.setFecha(fecha);
                his.setFkEstado(estado);
                his.setFkTicket(miTicket);
                his.setFkUsuario(LoginEJB.usuario);
                servH.nuevo(his);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en espera!!");
            }
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_enEsperaActionPerformed

    private void btn_ver_adjuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_adjuntoActionPerformed
        // TODO add your handling code here:
        if(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())).getAdjunto() != null){
            //String arch = "\\\\10.20.130.242\\www\\TicketWeb\\archivos\\"+serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())).getAdjunto();
            String arch = "file://10.20.130.242/www/TicketWeb/archivos/"+serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())).getAdjunto();
            abrirArchivo(arch);
        }else{
            JOptionPane.showMessageDialog(this, "Este ticket no posse adjunto");
        }
        
    }//GEN-LAST:event_btn_ver_adjuntoActionPerformed

    private void btn_controlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_controlActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 4).equals("Control")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                EstadoServ esta = EstadoServ.getEstadoServ();
                Estados estado = esta.traerEstado(8);
                HistorialTickets his = new HistorialTickets();
                fecha = new Date();
                his.setFecha(fecha);
                his.setFkEstado(estado);
                his.setFkTicket(miTicket);
                his.setFkUsuario(LoginEJB.usuario);
                servH.nuevo(his);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en control!!");
            }
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_controlActionPerformed

    private void btn_patrimonioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_patrimonioActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.modificarPatrimonio(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_patrimonioActionPerformed

    private void btn_nota_salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nota_salidaActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.modificarNotaSalida(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_nota_salidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_control;
    private javax.swing.JButton btn_enEspera;
    private javax.swing.JButton btn_nota_salida;
    private javax.swing.JButton btn_patrimonio;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JButton btn_responder;
    private javax.swing.JButton btn_responder1;
    private javax.swing.JButton btn_resuelto;
    private javax.swing.JButton btn_tomar;
    private javax.swing.JButton btn_trabajando;
    private javax.swing.JButton btn_transferir;
    private javax.swing.JButton btn_verResp;
    private javax.swing.JButton btn_ver_adjunto;
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tickets;
    private javax.swing.JLabel lblCantidadTickets;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
