/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import mscb.tick.util.reportes.EjecutarReporte;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class BandejaEnviados extends MenuP {
    Main mainFrame;
    private static BandejaEnviados estePanel;
    private TicketServ serviciosT;
    private List<Tickets> miLista;
    private DefaultTableModel modelo;
    private HistorialServ servH;
    private EstadoServ esta;
    private UsuarioServ serviciosU;
    private EjecutarReporte report;
    private Date fecha;
    
    /**
     * Creates new form MisTickets
     */
    private BandejaEnviados(Main mainFrame) {
        initComponents();
        jt_tickets.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.mainFrame = mainFrame;
        servH = new HistorialServ();
        lblNombreUsuario.setText(LoginEJB.usuario.getNombreUsuario());
        miLista = new ArrayList<>();
        modelo = (DefaultTableModel) jt_tickets.getModel();
        //mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Ticketneitor");
        //setSize(mainFrame.getSize());
        setVisible(true);
        llenarTabla();
        report = EjecutarReporte.getEjecutarReporte();
    }
    
    public static BandejaEnviados getBandejaEnviados(Main mainFrame){
        if(estePanel == null){
            estePanel = new BandejaEnviados(mainFrame);
        }
        return estePanel;
    }
    
    public void llenarTabla() {
        vaciarTabla(jt_tickets);
        serviciosT = new TicketServ();
        miLista = serviciosT.buscarPorUsuarioEmisor();
        //cambiarEstadoDeTicketsRecibidos(); esta funcion cambia el estado del ticket a recibido porquien abre la app
        
        String v[] = new String[8];
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
            modelo.addRow(v);
        }
        revalidate();

    }
    
    private void llenarTablaBuscador(List <Tickets> busca) {
        vaciarTabla(jt_tickets);
        String v[] = new String[8];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

        for (int i = 0; i < busca.size(); i++) {
            v[0] = busca.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(busca.get(i).getFecha()).toString();
            v[2] = busca.get(i).getCreador().getFkEmpleado().getFkArea().getNombreArea();
            v[3] = busca.get(i).getCreador().getNombreUsuario();
            //v[4] = busca.get(i).getFkAreaSistemas().getNombreArea();
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
            modelo.addRow(v);

        }
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

        txt_buscar = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tickets = new javax.swing.JTable();
        lblNombreUsuario = new javax.swing.JLabel();
        btn_observacion = new javax.swing.JButton();
        btn_verResp = new javax.swing.JButton();
        btn_refrescar = new javax.swing.JButton();
        btn_volver = new javax.swing.JButton();
        btn_responder1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mis Tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N
        setMinimumSize(new java.awt.Dimension(827, 569));

        txt_buscar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_buscar.setForeground(new java.awt.Color(0, 108, 118));
        txt_buscar.setText("Usuario, asunto, area, patrimonio...");
        txt_buscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_buscarMouseClicked(evt);
            }
        });
        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });

        txt_id.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 108, 118));
        txt_id.setText("Nº de ticket....");
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

        jt_tickets.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jt_tickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Ticket", "Fecha", "Area Emisor", "Usuario Emisor", "Estado", "Asunto", "Usuario receptor", "Patrimonio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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

        btn_observacion.setBackground(new java.awt.Color(153, 153, 153));
        btn_observacion.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_observacion.setForeground(new java.awt.Color(0, 108, 118));
        btn_observacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/observation.png"))); // NOI18N
        btn_observacion.setText("Ver observacion");
        btn_observacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_observacionActionPerformed(evt);
            }
        });

        btn_verResp.setBackground(new java.awt.Color(153, 153, 153));
        btn_verResp.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_verResp.setForeground(new java.awt.Color(0, 108, 118));
        btn_verResp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/email.png"))); // NOI18N
        btn_verResp.setText("Ver respuestas");
        btn_verResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verRespActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_responder1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_observacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_verResp)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_refrescar))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                        .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_refrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_verResp, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_responder1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_buscarMouseClicked
        // TODO add your handling code here:
        txt_buscar.setText("");
    }//GEN-LAST:event_txt_buscarMouseClicked

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
        if(txt_buscar.getText().trim().length() >0){
            llenarTablaBuscador(serviciosT.buscar(txt_buscar.getText().trim()));
        }

    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseClicked
        // TODO add your handling code here:
        txt_id.setText("");
    }//GEN-LAST:event_txt_idMouseClicked

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        if(txt_id.getText().trim().length() >0){
            llenarTablaBuscador(serviciosT.buscarNoResueltos(Integer.parseInt(txt_id.getText().trim())));
        }
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_observacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_observacionActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.Observaciones(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_observacionActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_observacion;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JButton btn_responder1;
    private javax.swing.JButton btn_verResp;
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tickets;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
