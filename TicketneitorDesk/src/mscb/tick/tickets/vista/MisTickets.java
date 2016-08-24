/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.tickets.vista;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.Tickets;
import mscb.tick.estados.servicios.EstadoServ;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.tickets.servicios.TicketServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class MisTickets extends MenuP {
    Main mainFrame;
    private static MisTickets estePanel;
    private TicketServ serviciosT;
    private List<Tickets> miLista;
    private DefaultTableModel modelo;
    
    /**
     * Creates new form MisTickets
     */
    private MisTickets(Main mainFrame) {
        initComponents();
        if(LoginEJB.usuario.getFkPermiso().getIdPermiso() == 2){
            btn_volver.setText("Cerrar Sesion");
        }
        if(LoginEJB.usuario.getFkPermiso().getIdPermiso() == 1){
            btn_volver.setText("Volver");
        }
        lblNombreUsuario.setText(LoginEJB.usuario.getNombreUsuario());
        miLista = new ArrayList<>();
        modelo = (DefaultTableModel) jt_tickets.getModel();
        this.mainFrame = mainFrame;
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Ticketneitor");
        setSize(800, 600);
        setVisible(true);
        llenarTabla();
    }
    
    public static MisTickets getMisTickets(Main mainFrame){
        if(estePanel == null){
            estePanel = new MisTickets(mainFrame);
        }
        return estePanel;
    }
    
    public void llenarTabla() {
        vaciarTabla(jt_tickets);
        serviciosT = new TicketServ();
        miLista = serviciosT.buscarPorUsuarioAsunto();
        cambiarEstadoDeTickets();
        String v[] = new String[7];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

        for (int i = 0; i < miLista.size(); i++) {
            v[0] = miLista.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(miLista.get(i).getFecha()).toString();
            v[2] = miLista.get(i).getFkAreaEmisor().getNombreArea();
            v[3] = miLista.get(i).getFkUsuarioEmisor().getNombreUsuario();
            /*if(miLista.get(i).getFkAreaSistemas() == null){
                v[4] = "Unknow";
            }else{
                 v[4] = miLista.get(i).getFkAreaSistemas().getNombreArea();
            }*/
            v[4] = miLista.get(i).getFkEstado().getNombre();
            v[5] = miLista.get(i).getAsunto().getPertenece().getNombre() + " - " + miLista.get(i).getAsunto().getNombreasuntoS();
            if(miLista.get(i).getUsuarioReceptor() == null){
                v[6] = "No aun";
            }else{
                v[6] = miLista.get(i).getUsuarioReceptor().getNombreUsuario();
            }
            
            
            modelo.addRow(v);

        }
        revalidate();

    }
    
    private void llenarTablaBuscador(List <Tickets> busca) {
        vaciarTabla(jt_tickets);
        String v[] = new String[7];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);

        for (int i = 0; i < busca.size(); i++) {
            v[0] = busca.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(busca.get(i).getFecha()).toString();
            v[2] = busca.get(i).getFkAreaEmisor().getNombreArea();
            v[3] = busca.get(i).getFkUsuarioEmisor().getNombreUsuario();
            //v[4] = busca.get(i).getFkAreaSistemas().getNombreArea();
            v[4] = miLista.get(i).getFkEstado().getNombre();
            v[5] = busca.get(i).getAsunto().getPertenece().getNombre() + " - " + busca.get(i).getAsunto().getNombreasuntoS();
            if(miLista.get(i).getUsuarioReceptor() == null){
                v[6] = "No aun";
            }else{
                v[6] = miLista.get(i).getUsuarioReceptor().getNombreUsuario();
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
     */
    private void cambiarEstadoDeTickets(){
        EstadoServ esta = new EstadoServ();
        Estados estado = esta.traerEstado(2);
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getFkEstado().getIdEstado() == 1){
                miLista.get(i).setFkEstado(estado);
                serviciosT.modificarTicket(miLista.get(i));
            }
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

        txt_buscar = new javax.swing.JTextField();
        txt_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tickets = new javax.swing.JTable();
        btn_volver = new javax.swing.JToggleButton();
        btn_observacion = new javax.swing.JToggleButton();
        btn_responder = new javax.swing.JToggleButton();
        btn_verResp = new javax.swing.JToggleButton();
        btn_transferir = new javax.swing.JToggleButton();
        lblNombreUsuario = new javax.swing.JLabel();
        btn_enEspera = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mis Tickets", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        txt_buscar.setBackground(new java.awt.Color(0, 102, 204));
        txt_buscar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_buscar.setForeground(new java.awt.Color(255, 255, 255));
        txt_buscar.setText("Usuario, asunto, area...");
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

        txt_id.setBackground(new java.awt.Color(0, 102, 204));
        txt_id.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(255, 255, 255));
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

        jt_tickets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Ticket", "Fecha", "Area Emisor", "Usuario Emisor", "Estado", "Asunto", "Usuario receptor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_tickets);

        btn_volver.setBackground(new java.awt.Color(0, 102, 204));
        btn_volver.setForeground(new java.awt.Color(255, 255, 255));
        btn_volver.setText("volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_observacion.setBackground(new java.awt.Color(0, 102, 204));
        btn_observacion.setForeground(new java.awt.Color(255, 255, 255));
        btn_observacion.setText("ver observacion");
        btn_observacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_observacionActionPerformed(evt);
            }
        });

        btn_responder.setBackground(new java.awt.Color(0, 102, 204));
        btn_responder.setForeground(new java.awt.Color(255, 255, 255));
        btn_responder.setText("responder");
        btn_responder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_responderActionPerformed(evt);
            }
        });

        btn_verResp.setBackground(new java.awt.Color(0, 102, 204));
        btn_verResp.setForeground(new java.awt.Color(255, 255, 255));
        btn_verResp.setText("ver respuestas");
        btn_verResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verRespActionPerformed(evt);
            }
        });

        btn_transferir.setBackground(new java.awt.Color(0, 102, 204));
        btn_transferir.setForeground(new java.awt.Color(255, 255, 255));
        btn_transferir.setText("transferir");
        btn_transferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferirActionPerformed(evt);
            }
        });

        lblNombreUsuario.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Bradley Hand ITC", 3, 24)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_enEspera.setBackground(new java.awt.Color(0, 102, 204));
        btn_enEspera.setForeground(new java.awt.Color(255, 255, 255));
        btn_enEspera.setText("Poner en espera");
        btn_enEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enEsperaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_verResp, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_responder, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_enEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(btn_transferir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                            .addContainerGap()))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_observacion, btn_responder, btn_transferir, btn_verResp, btn_volver});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_responder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_verResp, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_transferir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(74, Short.MAX_VALUE)))
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
            llenarTablaBuscador(serviciosT.buscar(Integer.parseInt(txt_id.getText().trim())));
        }
    }//GEN-LAST:event_txt_idActionPerformed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        if(LoginEJB.usuario.getFkPermiso().getIdPermiso() == 2){
            if(JOptionPane.showConfirmDialog(this,"Seguro desea salir?","Confirmar",JOptionPane.YES_NO_OPTION)==0){
               this.setVisible(false);
                estePanel = null;
                System.gc();
                mainFrame.ventanaLogin(); 
            }
        }
        if(LoginEJB.usuario.getFkPermiso().getIdPermiso() == 1){
            this.setVisible(false);
            mainFrame.menuPrincipal();
            estePanel = null;
            System.gc();
        }
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_observacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_observacionActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.Observaciones(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_observacionActionPerformed

    private void btn_responderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_responderActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.Respuestas(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_responderActionPerformed

    private void btn_verRespActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verRespActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.verRespuestas(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_verRespActionPerformed

    private void btn_transferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transferirActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.transferirTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_transferirActionPerformed

    private void btn_enEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enEsperaActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 4).equals("En espera")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                EstadoServ esta = new EstadoServ();
                Estados estad = esta.traerEstado(3);
                miTicket.setFkEstado(estad);
                serviciosT.modificarTicket(miTicket);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en espera!!");
            }
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_enEsperaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_enEspera;
    private javax.swing.JToggleButton btn_observacion;
    private javax.swing.JToggleButton btn_responder;
    private javax.swing.JToggleButton btn_transferir;
    private javax.swing.JToggleButton btn_verResp;
    private javax.swing.JToggleButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tickets;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
