/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.conocimiento;

import java.text.DateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class BaseConocimientoV extends MenuP {
    Main mainFrame;
    private static BaseConocimientoV estePanel;
    private DefaultTableModel modelo;
    private TicketServ serviciosT;
    private List<HistorialTickets> miLista;
    private HistorialServ servH;
    /**
     * Creates new form BaseConocimiento
     */
    private BaseConocimientoV(Main mainFrame) {
        initComponents();
        modelo = (DefaultTableModel) jt_conocimiento.getModel();
        serviciosT = TicketServ.getTicketServ();
        servH = HistorialServ.getHistorialServ();
        this.mainFrame = mainFrame;
        setSize(800, 600);
        setVisible(true);
        llenarTabla();
    }
    
    public static BaseConocimientoV getBaseConocimiento(Main mainFrame){
        if(estePanel == null){
            estePanel = new BaseConocimientoV(mainFrame);
        }
        return estePanel;
    }
    
    private void llenarTabla(){
        vaciarTabla(jt_conocimiento);
        if(mainFrame.validarPermisos(58)){
            miLista = servH.traerTodosResueltos();
        }else{
             miLista = servH.traerTodosResueltosPorServicios();
        }
       
        String v[] = new String[10];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        
        Comparator<HistorialTickets> compara = Collections.reverseOrder();
        Collections.sort(miLista,compara);
        Integer cant =0;
        
        for(int i = 0; i < miLista.size();i++){
            v[0] = miLista.get(i).getFkTicket().toString();
            v[1] = miLista.get(i).getFkTicket().getCreador().getNombreUsuario();
            v[2] = dateFormatter.format(miLista.get(i).getFkTicket().getFecha()).toString();
            v[3] = miLista.get(i).getFkTicket().getUltimoUsuario().getNombreUsuario();
            v[4] = dateFormatter.format(miLista.get(i).getFecha()).toString();
            v[5] = miLista.get(i).getFkTicket().getServicio().getPertenece().getNombre();
            v[6] = miLista.get(i).getFkTicket().getServicio().getNombreasuntoS();
            if((miLista.get(i).getFkTicket().getPatrimonio() == null)||(miLista.get(i).getFkTicket().getPatrimonio().isEmpty())){
                v[7] = "";
            }else{
                v[7] = miLista.get(i).getFkTicket().getPatrimonio();
            }
            if((miLista.get(i).getFkTicket().getNotaSalida()== null)||(miLista.get(i).getFkTicket().getNotaSalida().isEmpty())||(miLista.get(i).getFkTicket().getNotaSalida().equals("00---------"))){
                v[8] = "";
            }else{
                v[8] = miLista.get(i).getFkTicket().getNotaSalida();
            }
            if((miLista.get(i).getFkTicket().getFkareaSolicitante()== null)){
                v[9] = "";
            }else{
                v[9] = miLista.get(i).getFkTicket().getFkareaSolicitante().getNombreArea();
            }
            modelo.addRow(v);
            cant++;
        }
        if(mainFrame.validarPermisos(58)){
            lblCant.setText(Integer.toString(cant)+" Tickets resueltos de: "+serviciosT.traerTodos().size());
        }else{
             lblCant.setText(Integer.toString(cant)+" Tickets resueltos de: "+serviciosT.traerTodosPorArea().size());
        }
        
        revalidate();
    }
    
    private void llenarTablaBuscador(List<HistorialTickets> miLista){
        vaciarTabla(jt_conocimiento);
        String v[] = new String[10];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        
        Comparator<HistorialTickets> compara = Collections.reverseOrder();
        Collections.sort(miLista,compara);
        Integer cant =0;
        
        for(int i = 0; i < miLista.size();i++){
            v[0] = miLista.get(i).getFkTicket().toString();
            v[1] = miLista.get(i).getFkTicket().getCreador().getNombreUsuario();
            v[2] = dateFormatter.format(miLista.get(i).getFkTicket().getFecha()).toString();
            v[3] = miLista.get(i).getFkTicket().getUltimoUsuario().getNombreUsuario();
            v[4] = dateFormatter.format(miLista.get(i).getFecha()).toString();
            v[5] = miLista.get(i).getFkTicket().getServicio().getPertenece().getNombre();
            v[6] = miLista.get(i).getFkTicket().getServicio().getNombreasuntoS();
            if((miLista.get(i).getFkTicket().getPatrimonio() == null)||(miLista.get(i).getFkTicket().getPatrimonio().isEmpty())){
                v[7] = "";
            }else{
                v[7] = miLista.get(i).getFkTicket().getPatrimonio();
            }
            if((miLista.get(i).getFkTicket().getNotaSalida()== null)||(miLista.get(i).getFkTicket().getNotaSalida().isEmpty())||(miLista.get(i).getFkTicket().getNotaSalida().equals("00---------"))){
                v[8] = "";
            }else{
                v[8] = miLista.get(i).getFkTicket().getNotaSalida();
            }
            if((miLista.get(i).getFkTicket().getFkareaSolicitante()== null)){
                v[9] = "";
            }else{
                v[9] = miLista.get(i).getFkTicket().getFkareaSolicitante().getNombreArea();
            }
            modelo.addRow(v);
            cant++;
        }
        if(mainFrame.validarPermisos(58)){
            lblCant.setText(Integer.toString(cant)+" Tickets resueltos de: "+serviciosT.traerTodos().size());
        }else{
             lblCant.setText(Integer.toString(cant)+" Tickets resueltos de: "+serviciosT.traerTodosPorArea().size());
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_conocimiento = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        lblCant = new javax.swing.JLabel();
        txt_id2 = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Base de conocimiento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N

        jt_conocimiento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jt_conocimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº ticket", "Emisor", "Fecha entrada", "Receptor", "Fecha resuelto", "Asunto", "Servicio", "Patrimonio", "Nota salida", "Area solicitante"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_conocimiento.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_conocimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_conocimientoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_conocimiento);
        if (jt_conocimiento.getColumnModel().getColumnCount() > 0) {
            jt_conocimiento.getColumnModel().getColumn(0).setMinWidth(50);
            jt_conocimiento.getColumnModel().getColumn(5).setPreferredWidth(100);
            jt_conocimiento.getColumnModel().getColumn(6).setMinWidth(200);
            jt_conocimiento.getColumnModel().getColumn(6).setPreferredWidth(200);
        }

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

        lblCant.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCant.setForeground(new java.awt.Color(255, 255, 255));

        txt_id2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_id2.setForeground(new java.awt.Color(0, 108, 118));
        txt_id2.setText("Nº de Ticket, Patrimonio, Observacion, usuario....");
        txt_id2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_id2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_id2FocusLost(evt);
            }
        });
        txt_id2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addGap(188, 188, 188)
                        .addComponent(lblCant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_id2, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_id2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCant, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.menuPrincipal();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void jt_conocimientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_conocimientoMouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == jt_conocimiento){
            if(evt.getClickCount() == 2){
                if(jt_conocimiento.getSelectedRow()!= -1){
                    HistorialTickets miBase = servH.buscarUltimo(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_conocimiento.getSelectedRow(), 0).toString())));
                    mainFrame.detalleTicket(miBase);
                }
            }
        }
    }//GEN-LAST:event_jt_conocimientoMouseClicked

    private void txt_id2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id2ActionPerformed
        // TODO add your handling code here:
        if(txt_id2.getText().trim().length() >0){
            llenarTablaBuscador(servH.buscarTodosResueltosPorServicios(txt_id2.getText().trim()));
        }
    }//GEN-LAST:event_txt_id2ActionPerformed

    private void txt_id2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_id2FocusGained
        // TODO add your handling code here:
        txt_id2.setText("");
    }//GEN-LAST:event_txt_id2FocusGained

    private void txt_id2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_id2FocusLost
        // TODO add your handling code here:
        txt_id2.setText("Nº de Ticket");
    }//GEN-LAST:event_txt_id2FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_conocimiento;
    private javax.swing.JLabel lblCant;
    private javax.swing.JTextField txt_id2;
    // End of variables declaration//GEN-END:variables
}
