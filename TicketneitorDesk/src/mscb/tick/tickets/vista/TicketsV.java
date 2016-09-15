/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.tickets.vista;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.HistorialTickets;
import mscb.tick.entidades.Tickets;
import mscb.tick.estados.servicios.EstadoServ;
import mscb.tick.historial.servicios.HistorialServ;
import mscb.tick.main.Main;
import mscb.tick.tickets.servicios.TicketServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class TicketsV extends MenuP {

    Main mainFrame;
    private static TicketsV estePanel;
    private TicketServ serviciosT;
    private EstadoServ serviciosE;
    private HistorialServ serviciosH;
    private List<Tickets> miLista;
    private DefaultTableModel modelo;
    private HistorialTickets hst;
    private Date fecha;
    /**
     * Creates new form Tickets
     */
    private TicketsV(Main mainFrame) {
        initComponents();
        modelo = (DefaultTableModel) jt_tickets.getModel();
        miLista = new ArrayList<>();
        serviciosT = new TicketServ();
        serviciosE = new EstadoServ();
        serviciosH = new HistorialServ();
        this.mainFrame = mainFrame;
        setSize(800, 600);
        setVisible(true);
        llenarTabla();
    }

    public static TicketsV getTickets(Main mainFrame) {
        if (estePanel == null) {
            estePanel = new TicketsV(mainFrame);
        }
        return estePanel;
    }
    
    
    
    public void llenarTabla() {
        vaciarTabla(jt_tickets);
        miLista = serviciosT.traerTodos();
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

        btn_obs = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_tickets = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_buscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        btn_volver = new javax.swing.JButton();
        ver_historial = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cambiarEstado = new javax.swing.JButton();
        btn_observacion = new javax.swing.JButton();

        btn_obs.setText("jButton1");
        btn_obs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_obsMouseClicked(evt);
            }
        });

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

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
        if (jt_tickets.getColumnModel().getColumnCount() > 0) {
            jt_tickets.getColumnModel().getColumn(0).setMinWidth(15);
            jt_tickets.getColumnModel().getColumn(0).setPreferredWidth(15);
            jt_tickets.getColumnModel().getColumn(1).setPreferredWidth(25);
            jt_tickets.getColumnModel().getColumn(3).setPreferredWidth(40);
            jt_tickets.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Bradley Hand ITC", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Buscar:");

        txt_buscar.setBackground(new java.awt.Color(204, 204, 204));
        txt_buscar.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
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

        jLabel2.setFont(new java.awt.Font("Bradley Hand ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar:");

        txt_id.setBackground(new java.awt.Color(204, 204, 204));
        txt_id.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
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

        btn_volver.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_volver.setText("volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        ver_historial.setBackground(new java.awt.Color(153, 153, 153));
        ver_historial.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ver_historial.setText("ver historial");
        ver_historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_historialActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_eliminar.setText("eliminar registro");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_cambiarEstado.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarEstado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cambiarEstado.setText("cambiar estado");
        btn_cambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarEstadoActionPerformed(evt);
            }
        });

        btn_observacion.setBackground(new java.awt.Color(153, 153, 153));
        btn_observacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_observacion.setText("ver observacion");
        btn_observacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_observacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ver_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_eliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ver_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_obsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_obsMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(mainFrame, "funciona");
    }//GEN-LAST:event_btn_obsMouseClicked

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
        if(txt_buscar.getText().trim().length() >0){
            llenarTablaBuscador(serviciosT.buscar(txt_buscar.getText().trim()));
        }
        
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        if(txt_id.getText().trim().length() >0){
            llenarTablaBuscador(serviciosT.buscar(Integer.parseInt(txt_id.getText().trim())));
        }
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_buscarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_buscarMouseClicked
        // TODO add your handling code here:
        txt_buscar.setText("");
    }//GEN-LAST:event_txt_buscarMouseClicked

    private void txt_idMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_idMouseClicked
        // TODO add your handling code here:
        txt_id.setText("");
    }//GEN-LAST:event_txt_idMouseClicked

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.menuPrincipal();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void ver_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_historialActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRowCount() != -1)&&(jt_tickets.getSelectedRowCount() < 2)){
            mainFrame.historialDeTickets(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
            this.setVisible(false);
            estePanel = null;
            System.gc();
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fla!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ver_historialActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRowCount() != -1)&&(jt_tickets.getSelectedRowCount() < 2)){
            Tickets miTick = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString()));
            miTick.setFkEstado(serviciosE.traerEstado(7));
             if(serviciosT.modificarTicket(miTick) == 0){
                JOptionPane.showMessageDialog(this, "Ticket eliminado");
                hst = new HistorialTickets();
                fecha = new Date();
                hst.setFecha(fecha);
                hst.setFkTicket(miTick);
                hst.setFkUsuarioEmisor(miTick.getFkUsuarioEmisor());
                hst.setFkUsuarioReceptor(miTick.getUsuarioReceptor());
                serviciosH.nuevo(hst);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(this, "Error al eliminar ticket");
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fla!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_cambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarEstadoActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_cambiarEstadoActionPerformed

    private void btn_observacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_observacionActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.Observaciones(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_observacionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiarEstado;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_obs;
    private javax.swing.JButton btn_observacion;
    private javax.swing.JButton btn_volver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tickets;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_id;
    private javax.swing.JButton ver_historial;
    // End of variables declaration//GEN-END:variables
}
