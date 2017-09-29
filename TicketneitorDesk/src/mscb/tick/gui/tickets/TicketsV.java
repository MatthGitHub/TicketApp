/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.negocio.TicketServ;
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
    private Date fecha;
    /**
     * Creates new form Tickets
     */
    private TicketsV(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        modelo = (DefaultTableModel) jt_tickets.getModel();
        miLista = new ArrayList<>();
        serviciosT = TicketServ.getTicketServ();
        serviciosH = HistorialServ.getHistorialServ();
        validarPermisos();
        configurarTabla(jt_tickets);
        setVisible(true);
        llenarTabla();
    }

    public static TicketsV getTickets(Main mainFrame) {
        if (estePanel == null) {
            estePanel = new TicketsV(mainFrame);
        }
        return estePanel;
    }
    
    private void validarPermisos(){
        //boton ver historial
        if(mainFrame.validarPermisos(9)){
            ver_historial.setEnabled(true);
            ver_historial.setVisible(true);
        }else{
            ver_historial.setEnabled(false);
            ver_historial.setVisible(false); 
        }
        //boton eliminar ticket
        if(mainFrame.validarPermisos(10)){
            btn_eliminar.setEnabled(true);
            btn_eliminar.setVisible(true);
        }else{
            btn_eliminar.setEnabled(false);
            btn_eliminar.setVisible(false); 
        }
        //boton cambiar estado
        if(mainFrame.validarPermisos(11)){
            btn_cambiarEstado.setEnabled(true);
            btn_cambiarEstado.setVisible(true);
        }else{
            btn_cambiarEstado.setEnabled(false);
            btn_cambiarEstado.setVisible(false); 
        }
    }
    
    public void configurarTabla(JTable MiTabla){
        //MiTabla.setIntercellSpacing(Dimension newSpacing);
        MiTabla.setRowHeight(20);
        //MiTabla.setRowHeight(row, rowHeight);
    }
    
    public void llenarTabla() {
        vaciarTabla(jt_tickets);
        if(LoginEJB.usuario.getFkRol().getIdRol() == 1){
            miLista = serviciosT.traerTodos();
        }else{
            miLista = serviciosT.traerTodosPorArea();
        }
        
        Comparator<Tickets> compara = Collections.reverseOrder();
        Collections.sort(miLista,compara);
        
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
            if(miLista.get(i).getUltimoUsuario()== null){
                v[6] = "";
            }else{
                v[6] = miLista.get(i).getUltimoUsuario().getNombreUsuario();
            }
            if((miLista.get(i).getPatrimonio() == null)||(miLista.get(i).getPatrimonio().isEmpty())){
                v[7] = "";
            }else{
                v[7] = miLista.get(i).getPatrimonio();
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
        
        Comparator<Tickets> compara = Collections.reverseOrder();
        Collections.sort(miLista,compara);
        
        for (int i = 0; i < busca.size(); i++) {
            v[0] = busca.get(i).getIdTicket().toString();
            v[1] = dateFormatter.format(busca.get(i).getFecha()).toString();
            v[2] = busca.get(i).getCreador().getFkEmpleado().getFkArea().getNombreArea();
            v[3] = busca.get(i).getCreador().getNombreUsuario();
            //v[4] = busca.get(i).getFkAreaSistemas().getNombreArea();
            v[4] = busca.get(i).getUltimoEstado().getNombre();
            v[5] = busca.get(i).getServicio().getPertenece().getNombre() + " - " + busca.get(i).getServicio().getNombreasuntoS();
            if(busca.get(i).getUltimoUsuario()== null){
                v[6] = "";
            }else{
                v[6] = busca.get(i).getUltimoUsuario().getNombreUsuario();
            }
            
            modelo.addRow(v);

        }
        revalidate();

    }
   /**
     *Cambia el estado de los tickets enviados a recibidos 
     *//*
    private void cambiarEstadoDeTicketsRecibidos(){
        EstadoServ esta = new EstadoServ();
        Estados estado = esta.traerEstado(2);
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getUltimoEstado().getIdEstado() == 1){
                HistorialTickets his = new HistorialTickets();
                fecha = new Date();
                his.setFecha(fecha);
                his.setFkEstado(estado);
                his.setFkTicket(miLista.get(i));
                his.setFkUsuario(LoginEJB.usuario);
                serviciosH.nuevo(his);
            }
        }
    }
    */

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
        jt_tickets.setToolTipText("");
        jt_tickets.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_tickets.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_tickets);
        if (jt_tickets.getColumnModel().getColumnCount() > 0) {
            jt_tickets.getColumnModel().getColumn(0).setMinWidth(15);
            jt_tickets.getColumnModel().getColumn(0).setPreferredWidth(15);
            jt_tickets.getColumnModel().getColumn(1).setPreferredWidth(25);
            jt_tickets.getColumnModel().getColumn(3).setPreferredWidth(40);
            jt_tickets.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Buscar:");

        txt_buscar.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_buscar.setForeground(new java.awt.Color(0, 108, 118));
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

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 108, 118));
        jLabel2.setText("Buscar:");

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

        ver_historial.setBackground(new java.awt.Color(153, 153, 153));
        ver_historial.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        ver_historial.setForeground(new java.awt.Color(0, 108, 118));
        ver_historial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/folder.png"))); // NOI18N
        ver_historial.setText("Ver historial");
        ver_historial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_historialActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        btn_eliminar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(0, 108, 118));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar registro");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_cambiarEstado.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarEstado.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_cambiarEstado.setForeground(new java.awt.Color(0, 108, 118));
        btn_cambiarEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/edit.png"))); // NOI18N
        btn_cambiarEstado.setText("Cambiar estado");
        btn_cambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarEstadoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                        .addComponent(btn_cambiarEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ver_historial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_observacion)))
                .addContainerGap())
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ver_historial, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cambiarEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
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
            llenarTablaBuscador(serviciosT.buscarTodos(txt_id.getText().trim()));
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
        estePanel = null;
        System.gc();
        mainFrame.menuPrincipal();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRowCount() != -1)&&(jt_tickets.getSelectedRowCount() < 2)){
            Tickets miTick = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString()));
            fecha = new Date();
            HistorialTickets his = new HistorialTickets();
            his.setFkEstado(serviciosE.getEstadoServ().traerEstado(7));
            his.setFecha(fecha);
            his.setFkTicket(miTick);
            his.setFkUsuario(LoginEJB.usuario);
            serviciosH.nuevo(his);
            llenarTabla();
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fla!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_cambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarEstadoActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRowCount() != -1)&&(jt_tickets.getSelectedRowCount() < 2)){
            mainFrame.cambiarEstadoTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fla!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_cambiarEstadoActionPerformed

    private void btn_observacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_observacionActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            mainFrame.detalleTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())).getUltimoHistorial());
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_observacionActionPerformed

    private void ver_historialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_historialActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRowCount() != 0)&&(jt_tickets.getSelectedRowCount() < 2)){
            mainFrame.historialDeTickets(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
            this.setVisible(false);
            estePanel = null;
            System.gc();
        }else{
            JOptionPane.showMessageDialog(this, "Debe seleccionar una y solo una fla!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ver_historialActionPerformed

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
