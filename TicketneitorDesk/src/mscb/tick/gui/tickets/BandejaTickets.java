/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.gui.tickets.ConfigurarTabla.ListaConf;
import mscb.tick.negocio.AreaServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.util.reportes.EjecutarReporte;
import mscb.tick.util.MenuP;
import mscb.tick.util.AccionMenu;

/**
 *
 * @author Administrador
 */
public class BandejaTickets extends MenuP {
    Main mainFrame;
    private static BandejaTickets estePanel;
    private TicketServ serviciosT;
    private List<Tickets> miLista;
    public DefaultTableModel modelo;
    private HistorialServ servH;
    private EstadoServ esta;
    private UsuarioServ serviciosU;
    private EjecutarReporte report;
    private Date fecha;
    private boolean mostrarEspera;
    private static JPopupMenu popup;
    private ConfigurarTabla configuracion;
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
        if(mainFrame.validarPermisos(58)){
            cmbx_areas.setVisible(true);
        }else{
            cmbx_areas.setVisible(false);
        }
        validarPermisos();
        cargarComboAreas();
        try {
            configurarTabla(jt_tickets);
        } catch (IOException ex) {
            Logger.getLogger(BandejaTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarEspera = false;
        setVisible(true);
        llenarTabla();
        initPopup();
        report = EjecutarReporte.getEjecutarReporte();
    }
    
    public static BandejaTickets getBandejaTickets(Main mainFrame){
        if(estePanel == null){
            estePanel = new BandejaTickets(mainFrame);
        }
        return estePanel;
    }
    
    //Construccion del JPopupMenu
    private void initPopup(){
        popup = new JPopupMenu();
        JMenu menuEstados = new JMenu("Estados");
        menuEstados.add(new AccionMenu("En espera",popup));
        menuEstados.add(new AccionMenu("En trabajo",popup));
        menuEstados.add(new AccionMenu("En control",popup));
        
        popup.add(new AccionMenu("Aceptar",popup));
        popup.add(menuEstados);
        popup.add(new AccionMenu("Transferir",popup));
        if(mainFrame.validarPermisos(45)){
            popup.add(new AccionMenu("Modificar patrimonio",popup));
        }
        if(mainFrame.validarPermisos(54)){
            popup.add(new AccionMenu("Modificar nota de entrada",popup));
        }
        if(mainFrame.validarPermisos(46)){
            popup.add(new AccionMenu("Modificar nota de salida",popup));
        }
        popup.add(new AccionMenu("Detalle",popup));
        popup.add(new AccionMenu("Ver adjuntos",popup));
        popup.add(new AccionMenu("Imprimir",popup));
        popup.add(new AccionMenu("Resoluciones",popup));
        
        
        jt_tickets.addMouseListener(new MouseListener() {

                @Override
                public void mouseReleased(MouseEvent e) {
                        muestraMenu(e);
                }

                /**
                 * Método que muestra el menú.
                 * 
                 * @param e
                 */
                private void muestraMenu(MouseEvent e) {
                        // isPopupTrigger() indica si es el evento de raton
                        // por defecto en el sistema operativo para mostrar
                        // el menu.
                        if (e.isPopupTrigger()) {
                             int r = jt_tickets.rowAtPoint(e.getPoint());
                                if (r >= 0 && r < jt_tickets.getRowCount()){
                                   jt_tickets.setRowSelectionInterval(r, r); 
                                }
                        popup.show(e.getComponent(), e.getX(), e.getY());
                        
                            //popup.setLocation(e.getLocationOnScreen());
                            //popup.setVisible(true);
                        }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                        muestraMenu(e);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                        muestraMenu(e);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                        muestraMenu(e);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                        muestraMenu(e);
                }


        });
    }
    
    private void cargarComboAreas(){
        List<Areas> misAreas = AreaServ.getAreaServ().traerTodasconAsuntos();
        for(int i = 0 ; i < misAreas.size(); i++){
            cmbx_areas.addItem(misAreas.get(i));
        }
    }
    
    public void configurarTabla(JTable MiTabla) throws IOException{
        
        configuracion = ConfigurarTabla.getConfigurarTabla(Main.getMainFrame());
        List<ListaConf> miConfig = configuracion.getListaDeConfiguracionTabla();
        //Configura tamaño de las filas
        if(configuracion.getSiMuestra("RowSize", miConfig)){
            MiTabla.setRowHeight(20);
        }else{
            MiTabla.setRowHeight(40);
        }
        
        
        if(!configuracion.getSiMuestra("NumTicket", miConfig)){
            MiTabla.getColumnModel().getColumn(0).setResizable(false);
            MiTabla.getColumnModel().getColumn(0).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(0).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(0).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Fecha", miConfig)){
            MiTabla.getColumnModel().getColumn(1).setResizable(false);
            MiTabla.getColumnModel().getColumn(1).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(1).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("LugarTrabajo", miConfig)){
            MiTabla.getColumnModel().getColumn(2).setResizable(false);
            MiTabla.getColumnModel().getColumn(2).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(2).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(2).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Estado", miConfig)){
            MiTabla.getColumnModel().getColumn(5).setResizable(false);
            MiTabla.getColumnModel().getColumn(5).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(5).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(5).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Hora", miConfig)){
            MiTabla.getColumnModel().getColumn(6).setResizable(false);
            MiTabla.getColumnModel().getColumn(6).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(6).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(6).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Asunto", miConfig)){
            MiTabla.getColumnModel().getColumn(8).setResizable(false);
            MiTabla.getColumnModel().getColumn(8).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(8).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Receptor", miConfig)){
            MiTabla.getColumnModel().getColumn(9).setResizable(false);
            MiTabla.getColumnModel().getColumn(9).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(9).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Patrimonio", miConfig)){
            MiTabla.getColumnModel().getColumn(10).setResizable(false);
            MiTabla.getColumnModel().getColumn(10).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(10).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(10).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Adjunto", miConfig)){
            MiTabla.getColumnModel().getColumn(11).setResizable(false);
            MiTabla.getColumnModel().getColumn(11).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(11).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(11).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("NotaEntrada", miConfig)){
            MiTabla.getColumnModel().getColumn(12).setResizable(false);
            MiTabla.getColumnModel().getColumn(12).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(12).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(12).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("NotaSalida", miConfig)){
            MiTabla.getColumnModel().getColumn(13).setResizable(false);
            MiTabla.getColumnModel().getColumn(13).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(13).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(13).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Observacion", miConfig)){
            MiTabla.getColumnModel().getColumn(7).setResizable(false);
            MiTabla.getColumnModel().getColumn(7).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(7).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        }/*else{
            MiTabla.getColumnModel().getColumn(7).setMaxWidth(400);
            MiTabla.getColumnModel().getColumn(7).setMinWidth(400);
            MiTabla.getColumnModel().getColumn(7).setPreferredWidth(400);
        }*/
        if(!configuracion.getSiMuestra("AreaEmisora", miConfig)){
            MiTabla.getColumnModel().getColumn(3).setResizable(false);
            MiTabla.getColumnModel().getColumn(3).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(3).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(3).setPreferredWidth(0);
        }
        if(!configuracion.getSiMuestra("Creador", miConfig)){
            MiTabla.getColumnModel().getColumn(4).setResizable(false);
            MiTabla.getColumnModel().getColumn(4).setMaxWidth(0);
            MiTabla.getColumnModel().getColumn(4).setMinWidth(0);
            MiTabla.getColumnModel().getColumn(4).setPreferredWidth(0);
        }
    }
    
    private void validarPermisos(){
        if(mainFrame.validarPermisos(54)){
            
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
        
        String v[] = new String[14];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        
        for (int i = 0; i < miLista.size(); i++) {
            v[0] = miLista.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(miLista.get(i).getFecha()).toString();
            if(miLista.get(i).getFkEdificio() == null){
                v[2] = "";
            }else{
                v[2] = miLista.get(i).getFkEdificio().getNombre();
            }
            v[3] = miLista.get(i).getFkareaSolicitante().getNombreArea();
            v[4] = miLista.get(i).getCreador().getNombreUsuario();
            v[5] = miLista.get(i).getUltimoEstado().getNombre();
            if(miLista.get(i).getUltimoHistorial().getHora() != null){
                v[6] = hourFormat.format(miLista.get(i).getUltimoHistorial().getHora()).toString();
            }else{
                v[6] = ""
;            }
            v[7] = miLista.get(i).getObservacion();
            v[8] = miLista.get(i).getServicio().getPertenece().getNombre() + " - " + miLista.get(i).getServicio().getNombreasuntoS();
            if(miLista.get(i).getUltimoUsuario() == null){
                v[9] = "";
            }else{
                v[9] = miLista.get(i).getUltimoUsuario().getNombreUsuario();
            }
            if((miLista.get(i).getPatrimonio() == null)||(miLista.get(i).getPatrimonio().isEmpty())){
                v[10] = "";
            }else{
                v[10] = miLista.get(i).getPatrimonio();
            }
            if(miLista.get(i).getFkComplejidad() == null){
                v[11] = "";
            }else{
                v[11] = miLista.get(i).getFkComplejidad().getDescripcion();
            }
            if((miLista.get(i).getNotaEntrada() == null)||(miLista.get(i).getNotaEntrada().equals("0000---------"))){
                v[12] = "";
            }else{
                v[12] = miLista.get(i).getNotaEntrada();
            }
            if((miLista.get(i).getNotaSalida()== null)||(miLista.get(i).getNotaSalida().equals("00---------"))){
                v[13] = "";
            }else{
                v[13] = miLista.get(i).getNotaSalida();
            }
            modelo.addRow(v);
            cantidad ++;
        }
        lblCantidadTickets.setText("Cantidad de tickets: "+cantidad.toString());
        revalidate();

    }
    
    /**
     * Llena la tabla de la bandeja de entrada con la lista quie recibe
     * @param busca 
     */
    private void llenarTablaBuscador(List <Tickets> busca) {
        vaciarTabla(jt_tickets);
        String v[] = new String[14];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        Integer cantidad = 0;
        
        Comparator<Tickets> compara = Collections.reverseOrder();
        Collections.sort(busca,compara);
        
        for (int i = 0; i < busca.size(); i++) {
            v[0] = busca.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(busca.get(i).getFecha()).toString();
            if(busca.get(i).getFkEdificio() == null){
                v[2] = "";
            }else{
                v[2] = busca.get(i).getFkEdificio().getNombre();
            }
            v[3] = busca.get(i).getCreador().getFkEmpleado().getFkArea().getNombreArea();
            v[4] = busca.get(i).getCreador().getNombreUsuario();
            v[5] = busca.get(i).getUltimoEstado().getNombre();
            if(busca.get(i).getUltimoHistorial().getHora() != null){
                v[6] = hourFormat.format(busca.get(i).getUltimoHistorial().getHora()).toString();
            }else{
                v[6] = ""
;            }
            v[7] = busca.get(i).getObservacion();
            v[8] = busca.get(i).getServicio().getPertenece().getNombre() + " - " + busca.get(i).getServicio().getNombreasuntoS();
            if(busca.get(i).getUltimoUsuario() == null){
                v[9] = "";
            }else{
                v[9] = busca.get(i).getUltimoUsuario().getNombreUsuario();
            }
            if((busca.get(i).getPatrimonio() == null)||(busca.get(i).getPatrimonio().isEmpty())){
                v[10] = "";
            }else{
                v[10] = busca.get(i).getPatrimonio();
            }
            if(miLista.get(i).getFkComplejidad() == null){
                v[11] = "";
            }else{
                v[11] = miLista.get(i).getFkComplejidad().getDescripcion();
            }
            if((busca.get(i).getNotaEntrada() == null)||(busca.get(i).getNotaEntrada().equals("0000---------"))){
                v[12] = "";
            }else{
                v[12] = busca.get(i).getNotaEntrada();
            }
            if((busca.get(i).getNotaSalida()== null)||(busca.get(i).getNotaSalida().equals("00---------"))){
                v[13] = "";
            }else{
                v[13] = busca.get(i).getNotaSalida();
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
    
    private void modificarAtributosTicket(String atributo, Tickets miTick){
        switch(atributo){
            case "Patrimonio":
                if(serviciosT.getTicketServ().modificarTicket(miTick) == 0){
                    JOptionPane.showMessageDialog(this, "Patrimonio modificado");
                }
                break;
                
            case "NotaEntrada":
                if(serviciosT.getTicketServ().modificarTicket(miTick) == 0){
                    JOptionPane.showMessageDialog(this, "Nota de entrada modificada");
                }
                break;
                
            case "NotaSalida":
                if(serviciosT.getTicketServ().modificarTicket(miTick) == 0){
                    JOptionPane.showMessageDialog(this, "Nota de salida modificada");
                }
                break;
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

        txt_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tickets = new javax.swing.JTable();
        lblNombreUsuario = new javax.swing.JLabel();
        btn_refrescar = new javax.swing.JButton();
        lblCantidadTickets = new javax.swing.JLabel();
        cmbx_areas = new javax.swing.JComboBox();
        btn_patrimonio1 = new javax.swing.JButton();
        btn_patrimonio2 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mis Tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N
        setMinimumSize(new java.awt.Dimension(827, 569));

        txt_id.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 108, 118));
        txt_id.setText("Usuario, Servicio, Patrimonio, Lugar de trabajo...");
        txt_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_idFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_idFocusLost(evt);
            }
        });
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        jt_tickets.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jt_tickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Ticket", "Fecha", "Lugar de trabajo", "Area solicitante", "Creador", "Estado", "Hora", "Observacion", "Asunto", "Receptor", "Patrimonio", "Complejidad", "Nota entrada/Proyecto", "Nota salida/Resolucion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_tickets.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tickets.getTableHeader().setReorderingAllowed(false);
        jt_tickets.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_ticketsFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_ticketsFocusLost(evt);
            }
        });
        jt_tickets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_ticketsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_tickets);
        if (jt_tickets.getColumnModel().getColumnCount() > 0) {
            jt_tickets.getColumnModel().getColumn(0).setMinWidth(40);
            jt_tickets.getColumnModel().getColumn(0).setPreferredWidth(40);
            jt_tickets.getColumnModel().getColumn(0).setMaxWidth(60);
            jt_tickets.getColumnModel().getColumn(1).setMinWidth(75);
            jt_tickets.getColumnModel().getColumn(1).setPreferredWidth(75);
            jt_tickets.getColumnModel().getColumn(1).setMaxWidth(75);
            jt_tickets.getColumnModel().getColumn(2).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(2).setPreferredWidth(500);
            jt_tickets.getColumnModel().getColumn(2).setMaxWidth(600);
            jt_tickets.getColumnModel().getColumn(3).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(3).setPreferredWidth(150);
            jt_tickets.getColumnModel().getColumn(3).setMaxWidth(400);
            jt_tickets.getColumnModel().getColumn(4).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(4).setPreferredWidth(75);
            jt_tickets.getColumnModel().getColumn(4).setMaxWidth(100);
            jt_tickets.getColumnModel().getColumn(5).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(5).setPreferredWidth(100);
            jt_tickets.getColumnModel().getColumn(5).setMaxWidth(150);
            jt_tickets.getColumnModel().getColumn(6).setMinWidth(75);
            jt_tickets.getColumnModel().getColumn(6).setPreferredWidth(75);
            jt_tickets.getColumnModel().getColumn(6).setMaxWidth(75);
            jt_tickets.getColumnModel().getColumn(8).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(8).setPreferredWidth(50);
            jt_tickets.getColumnModel().getColumn(9).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(9).setPreferredWidth(100);
            jt_tickets.getColumnModel().getColumn(9).setMaxWidth(100);
            jt_tickets.getColumnModel().getColumn(10).setMinWidth(50);
            jt_tickets.getColumnModel().getColumn(10).setPreferredWidth(100);
            jt_tickets.getColumnModel().getColumn(10).setMaxWidth(100);
            jt_tickets.getColumnModel().getColumn(11).setMinWidth(40);
            jt_tickets.getColumnModel().getColumn(11).setPreferredWidth(75);
            jt_tickets.getColumnModel().getColumn(11).setMaxWidth(150);
            jt_tickets.getColumnModel().getColumn(12).setMinWidth(100);
            jt_tickets.getColumnModel().getColumn(12).setPreferredWidth(250);
            jt_tickets.getColumnModel().getColumn(12).setMaxWidth(250);
            jt_tickets.getColumnModel().getColumn(13).setMinWidth(100);
            jt_tickets.getColumnModel().getColumn(13).setPreferredWidth(250);
            jt_tickets.getColumnModel().getColumn(13).setMaxWidth(250);
        }

        lblNombreUsuario.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Tekton Pro", 3, 24)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(0, 108, 118));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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

        lblCantidadTickets.setBackground(new java.awt.Color(0, 102, 204));
        lblCantidadTickets.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidadTickets.setForeground(new java.awt.Color(0, 108, 118));
        lblCantidadTickets.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cmbx_areas.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_areas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_areas.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_areas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todas" }));
        cmbx_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areasActionPerformed(evt);
            }
        });

        btn_patrimonio1.setBackground(new java.awt.Color(153, 153, 153));
        btn_patrimonio1.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_patrimonio1.setForeground(new java.awt.Color(0, 108, 118));
        btn_patrimonio1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/loading.png"))); // NOI18N
        btn_patrimonio1.setText("Ocultar en espera");
        btn_patrimonio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_patrimonio1ActionPerformed(evt);
            }
        });

        btn_patrimonio2.setBackground(new java.awt.Color(153, 153, 153));
        btn_patrimonio2.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_patrimonio2.setForeground(new java.awt.Color(0, 108, 118));
        btn_patrimonio2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/work.png"))); // NOI18N
        btn_patrimonio2.setText("Configurar tabla");
        btn_patrimonio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_patrimonio2ActionPerformed(evt);
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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_patrimonio1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_refrescar)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbx_areas, 0, 47, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblCantidadTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_patrimonio2)))
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_patrimonio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_refrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btn_patrimonio2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(lblCantidadTickets, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        if(txt_id.getText().trim().length() >0){
            if(UsuarioServ.getUsuarioServ().traerPorArea(LoginEJB.usuario.getFkEmpleado().getFkArea()).contains(UsuarioServ.getUsuarioServ().buscarUsuarioPorNombre(txt_id.getText()))){
                llenarTablaBuscador(serviciosT.buscarPorUsuario(txt_id.getText().trim()));
            }else{
                llenarTablaBuscador(serviciosT.buscar(txt_id.getText().trim()));
            }
            
        }
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_btn_refrescarActionPerformed

    private void jt_ticketsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_ticketsMouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == jt_tickets){
            if(evt.getClickCount() == 2){
                if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
                    mainFrame.Respuestas(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
                }else{
                    JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
                }
            }
        }
    }//GEN-LAST:event_jt_ticketsMouseClicked

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
        if(cmbx_areas.getSelectedItem().equals("Todas")){
            llenarTabla();
        }else{
            llenarTablaBuscador(serviciosT.traerTodosPorArea((Areas) cmbx_areas.getSelectedItem()));
        }
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void btn_patrimonio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_patrimonio1ActionPerformed
        // TODO add your handling code here:
        llenarTablaBuscador(serviciosT.buscarPorUsuarioAsuntoSinEnEspera());
    }//GEN-LAST:event_btn_patrimonio1ActionPerformed

    private void jt_ticketsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_ticketsFocusLost
        // TODO add your handling code here:
        /*if(jt_tickets.isEditing()){
            //System.out.println("Focus lost");
        }*/
    }//GEN-LAST:event_jt_ticketsFocusLost

    private void jt_ticketsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_ticketsFocusGained
        // TODO add your handling code here:
        /*if(jt_tickets.isCellSelected(jt_tickets.getSelectedRow(), 10)){
            //System.out.println("Focus gained");
            Tickets miTick = serviciosT.getTicketServ().buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString()));
            
            //Verifico si se modifca patrimonio
            if(miTick.getPatrimonio() != null){
                if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 10).toString().equals("")&&(!modelo.getValueAt(jt_tickets.getSelectedRow(), 10).toString().equals(miTick.getPatrimonio()))){
                    miTick.setPatrimonio(modelo.getValueAt(jt_tickets.getSelectedRow(), 10).toString());
                    modificarAtributosTicket("Patrimonio",miTick);
                }
            }else{
                if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 10).toString().equals("")){
                    miTick.setPatrimonio(modelo.getValueAt(jt_tickets.getSelectedRow(), 10).toString());
                    modificarAtributosTicket("Patrimonio",miTick);
                }
            }
            
            //Verifico si se modifca nota de entrada
            if(miTick.getNotaEntrada()!= null){
                if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 12).toString().equals("")&&(!modelo.getValueAt(jt_tickets.getSelectedRow(), 12).toString().equals(miTick.getNotaEntrada()))){
                    miTick.setNotaEntrada(modelo.getValueAt(jt_tickets.getSelectedRow(), 12).toString());
                    modificarAtributosTicket("NotaEntrada",miTick);
                }
            }else{
                if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 12).toString().equals("")){
                    miTick.setNotaEntrada(modelo.getValueAt(jt_tickets.getSelectedRow(), 12).toString());
                    modificarAtributosTicket("NotaEntrada",miTick);
                }
            }
            
            //Verifico si se modifca nota de entrada
            if(miTick.getNotaSalida()!= null){
                if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 13).toString().equals("")&&(!modelo.getValueAt(jt_tickets.getSelectedRow(), 13).toString().equals(miTick.getNotaSalida()))){
                    miTick.setNotaSalida(modelo.getValueAt(jt_tickets.getSelectedRow(), 13).toString());
                    modificarAtributosTicket("NotaSalida",miTick);
                }
            }else{
                if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 13).toString().equals("")){
                    miTick.setNotaSalida(modelo.getValueAt(jt_tickets.getSelectedRow(), 13).toString());
                    modificarAtributosTicket("NotaSalida",miTick);
                }
            }
        }*/
    }//GEN-LAST:event_jt_ticketsFocusGained

    private void btn_patrimonio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_patrimonio2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.configurarTabla();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_patrimonio2ActionPerformed

    private void txt_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusLost
        // TODO add your handling code here:
        txt_id.setText("Usuario, Servicio, Patrimonio, Lugar de trabajo...");
    }//GEN-LAST:event_txt_idFocusLost

    private void txt_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusGained
        // TODO add your handling code here:
        txt_id.setText("");
    }//GEN-LAST:event_txt_idFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_patrimonio1;
    private javax.swing.JButton btn_patrimonio2;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jt_tickets;
    private javax.swing.JLabel lblCantidadTickets;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
