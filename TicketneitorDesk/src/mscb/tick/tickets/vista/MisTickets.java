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
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.tickets.servicios.TicketServ;
import mscb.tick.usuarios.servicios.UsuarioServ;
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
    private HistorialServ serviciosH;
    private EstadoServ esta;
    private UsuarioServ serviciosU;
    
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
            btn_cambiarClave.setVisible(false);
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
        esta = new EstadoServ();
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
        lblNombreUsuario = new javax.swing.JLabel();
        btn_observacion = new javax.swing.JButton();
        btn_verResp = new javax.swing.JButton();
        btn_responder = new javax.swing.JButton();
        btn_enEspera = new javax.swing.JButton();
        btn_tomar = new javax.swing.JButton();
        btn_transferir = new javax.swing.JButton();
        btn_resuelto = new javax.swing.JButton();
        btn_refrescar = new javax.swing.JButton();
        btn_cambiarClave = new javax.swing.JButton();
        btn_volver = new javax.swing.JButton();

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
        if (jt_tickets.getColumnModel().getColumnCount() > 0) {
            jt_tickets.getColumnModel().getColumn(0).setMinWidth(15);
            jt_tickets.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        lblNombreUsuario.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Bradley Hand ITC", 3, 24)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_observacion.setBackground(new java.awt.Color(153, 153, 153));
        btn_observacion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_observacion.setText("ver observacion");
        btn_observacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_observacionActionPerformed(evt);
            }
        });

        btn_verResp.setBackground(new java.awt.Color(153, 153, 153));
        btn_verResp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_verResp.setText("ver respuestas");
        btn_verResp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verRespActionPerformed(evt);
            }
        });

        btn_responder.setBackground(new java.awt.Color(153, 153, 153));
        btn_responder.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_responder.setText("responder");
        btn_responder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_responderActionPerformed(evt);
            }
        });

        btn_enEspera.setBackground(new java.awt.Color(153, 153, 153));
        btn_enEspera.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_enEspera.setText("poner en espera");
        btn_enEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enEsperaActionPerformed(evt);
            }
        });

        btn_tomar.setBackground(new java.awt.Color(153, 153, 153));
        btn_tomar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_tomar.setText("tomar ticket");
        btn_tomar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tomarActionPerformed(evt);
            }
        });

        btn_transferir.setBackground(new java.awt.Color(153, 153, 153));
        btn_transferir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_transferir.setText("transferir");
        btn_transferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transferirActionPerformed(evt);
            }
        });

        btn_resuelto.setBackground(new java.awt.Color(153, 153, 153));
        btn_resuelto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_resuelto.setText("marcar resuelto");
        btn_resuelto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resueltoActionPerformed(evt);
            }
        });

        btn_refrescar.setBackground(new java.awt.Color(153, 153, 153));
        btn_refrescar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_refrescar.setText("refrescar tabla");
        btn_refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refrescarActionPerformed(evt);
            }
        });

        btn_cambiarClave.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cambiarClave.setText("cambiar mi clave");
        btn_cambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarClaveActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_cambiarClave)
                        .addGap(539, 539, 539))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_transferir)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addComponent(btn_verResp)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(btn_resuelto))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_responder)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_enEspera)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_tomar, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_refrescar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(177, 177, 177)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(33, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_observacion, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_verResp, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(btn_responder, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_enEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tomar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_transferir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resuelto, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_refrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cambiarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(194, Short.MAX_VALUE)))
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

    private void btn_tomarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tomarActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            String usuarioAct = modelo.getValueAt(jt_tickets.getSelectedRow(), 6).toString();
            System.out.println(usuarioAct);
            
            if(usuarioAct.equalsIgnoreCase("No aun")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                miTicket.setUsuarioReceptor(LoginEJB.usuario);
                serviciosT.modificarTicket(miTicket);
                serviciosH = new HistorialServ();
                serviciosU = new UsuarioServ();
                HistorialTickets miHis = serviciosH.buscarUno(miTicket.getFkUsuarioEmisor(),miTicket);
                miHis.setFkUsuarioReceptor(LoginEJB.usuario);
                serviciosH.modificar(miHis);
                llenarTabla();
            }else{
                if(usuarioAct.equalsIgnoreCase(LoginEJB.usuario.getNombreUsuario())){
                    JOptionPane.showMessageDialog(this, "Este ticket ya le pertence!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                    serviciosH = new HistorialServ();
                    serviciosU = new UsuarioServ();
                    HistorialTickets miHis = new HistorialTickets();
                    Date fecha = new Date();
                    miHis.setFecha(fecha);
                    miHis.setFkTicket(miTicket);
                    miHis.setFkUsuarioEmisor(miTicket.getUsuarioReceptor());
                    miTicket.setUsuarioReceptor(LoginEJB.usuario);
                    serviciosT.modificarTicket(miTicket);
                    serviciosH.nuevo(miHis);
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
                mainFrame.transferirTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
                mainFrame.resolucionTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
                llenarTabla();
            }else{
                mainFrame.transferirTicket(serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())));
                llenarTabla();
            }
            
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_transferirActionPerformed

    private void btn_resueltoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resueltoActionPerformed
        // TODO add your handling code here:
        if((jt_tickets.getSelectedRow() != -1)&&(jt_tickets.getSelectedRowCount() == 1)){
            if(!modelo.getValueAt(jt_tickets.getSelectedRow(), 4).equals("Resuelto")){
                Tickets miTicket = serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(),0).toString()));
                EstadoServ esta = new EstadoServ();
                Estados estad = esta.traerEstado(5);
                miTicket.setUsuarioReceptor(LoginEJB.usuario);
                miTicket.setFkEstado(estad);
                serviciosT.modificarTicket(miTicket);
                mainFrame.resolucionTicket(miTicket);
                llenarTabla();
            }else{
                JOptionPane.showMessageDialog(mainFrame, "El ticket ya se encuentra en resuelto!!");
            }
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_resueltoActionPerformed

    private void btn_refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refrescarActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_btn_refrescarActionPerformed

    private void btn_cambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarClaveActionPerformed
        // TODO add your handling code here:
        mainFrame.cambiarClave();
    }//GEN-LAST:event_btn_cambiarClaveActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiarClave;
    private javax.swing.JButton btn_enEspera;
    private javax.swing.JButton btn_observacion;
    private javax.swing.JButton btn_refrescar;
    private javax.swing.JButton btn_responder;
    private javax.swing.JButton btn_resuelto;
    private javax.swing.JButton btn_tomar;
    private javax.swing.JButton btn_transferir;
    private javax.swing.JButton btn_verResp;
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_tickets;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
