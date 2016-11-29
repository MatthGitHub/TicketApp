/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.tickets.vista;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.areaSistemas.servicios.AreaSistemaServ;
import mscb.tick.areas.servicios.AreaServ;
import mscb.tick.asuntoPrincipal.servicios.AsuntoPrincipalServ;
import mscb.tick.asuntoSecundario.servicios.AsuntoSecundarioServ;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.Asuntos;
import mscb.tick.entidades.HistorialTickets;
import mscb.tick.entidades.Servicios;
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
public class NuevoTicket extends MenuP {
    Main mainFrame;
    private static NuevoTicket form;
    private AsuntoPrincipalServ serviciosPrincipal;
    private List <Asuntos> miListaAP;
    private AsuntoSecundarioServ serviciosSecundario;
    private List <Servicios> miListaAS;
    int b;
    private Tickets miTick;
    private Date fecha;
    private AreaSistemaServ sis;
    private TicketServ serviciosT;
    private Time hora;
    private EstadoServ serviciosEst;
    private AreaServ serviciosA;
    
    /**
     * Creates new form NuevoTicket
     */
    private NuevoTicket(Main mainFrame) {
        initComponents();
        //B es una bandera para que el combobox de asuntos no se cargue cuando entre, sino que cuando selecciones
        b = 0;
        this.mainFrame = mainFrame;
        setSize(800, 600);
        setVisible(true);
        lblAsunto.setVisible(false);
        lblServicio.setVisible(false);
        cmbx_asuntoPrincipal.setVisible(false);
        cmbx_asuntoSecundario.setVisible(false);
        llenarAreas();
    }
    
    public static NuevoTicket getNuevoTicket(Main mainFrame){
        if(form == null){
            form = new NuevoTicket(mainFrame);
        }
        return form;
    }
    
    private void llenarAreas(){
        serviciosA = new AreaServ();
        List<Areas> misAreas = serviciosA.traerTodasconAsuntos();
        for(int i = 0; i < misAreas.size(); i++){
            cmbx_areas.addItem(misAreas.get(i));
        }
        
        List<Areas> misAreas2 = serviciosA.traerTodas();
        for(int i = 0; i < misAreas2.size(); i++){
            cmbx_areas1.addItem(misAreas2.get(i));
        }
    }
    
    private void llenarAsuntoPrincipal(Areas area){
        serviciosPrincipal = new AsuntoPrincipalServ();
        miListaAP = serviciosPrincipal.asuntosPorArea(area);
        
        for(int i = 0 ; i < miListaAP.size(); i++){
            cmbx_asuntoPrincipal.addItem(miListaAP.get(i));
        }
    }
    /**
     * Funcion para pedir que escriban los requisitos del pedido en el campo de observaciones
     * en base a 2 switch, uno para el cmbx principal y otro para el cmbx secundario.
     */
    public void label(){
        //Switch para ver el asunto prçincipal
        switch(cmbx_asuntoPrincipal.getSelectedItem().toString()){
            case "PGM":
                //Switch para ver los asuntos secundarios de PGM
                switch(cmbx_asuntoSecundario.getSelectedItem().toString()){
                    case "Usuarios (Alta - Baja - Permisos)":
                        txtA_obs.setText("En caso de alta escriba el nombre y apellido de la persona, y dni.\n "
                                + "En caso de permisos poner un usuario de referencia que ya posea los permisos.");
                        break;
                        
                    case "Quitar disposicion":
                        txtA_obs.setText("Escribir aqui numero de cuenta y numero de disposicion");
                        break;
                        
                    case "Anular cedulon":
                        txtA_obs.setText("Escribir aqui numero de cedulon y monto.");
                        break;
                }
            //Switch para ver los asuntos secundarios de WebDoc
            case "WebDoc":
                switch(cmbx_asuntoSecundario.getSelectedItem().toString()){
                    case "Usuarios (Alta - Baja - Permisos)":
                        txtA_obs.setText("Escribir nombre, apellido y un usuario de referencia \n"
                                + "con los permisos solicitados");
                        break;
                   
                }
            //Switch para ver los asuntos secundarios de Correo
            case "Correo":
                switch(cmbx_asuntoSecundario.getSelectedItem().toString()){
                    case "Alta nueva casilla de correo":
                        txtA_obs.setText("Sugerir el correo a dar de alta");
                        break;
                   
                    case "Baja de casilla de correo":
                        txtA_obs.setText("Escribir el correo a dar de baja");
                        break;
                }
            //Switch para ver los asuntos secundarios de Pagina de medicina
            case "Pagina de medicina":
                switch(cmbx_asuntoSecundario.getSelectedItem().toString()){
                    case "Usuarios (Alta - Baja - Permisos)":
                        txtA_obs.setText("Escribir nombre, apellido y un usuario de referencia \n"
                                + "con los permisos solicitados");
                        break;
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

        jLabel1 = new javax.swing.JLabel();
        cmbx_asuntoPrincipal = new javax.swing.JComboBox();
        cmbx_asuntoSecundario = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_obs = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_volver = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        cmbx_areas = new javax.swing.JComboBox();
        lblAsunto = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        cmbx_areas1 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo pedido a sistemas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Area Receptor:");

        cmbx_asuntoPrincipal.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_asuntoPrincipal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_asuntoPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoPrincipalActionPerformed(evt);
            }
        });

        cmbx_asuntoSecundario.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_asuntoSecundario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_asuntoSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoSecundarioActionPerformed(evt);
            }
        });

        txtA_obs.setBackground(new java.awt.Color(204, 204, 204));
        txtA_obs.setColumns(20);
        txtA_obs.setRows(5);
        txtA_obs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtA_obsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtA_obs);

        jLabel2.setBackground(new java.awt.Color(0, 102, 204));
        jLabel2.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Observacion:");

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Escriba una observacion");

        jLabel4.setBackground(new java.awt.Color(0, 102, 204));
        jLabel4.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("siempre que sea posible...");

        btn_volver.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_volver.setText("volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_guardar.setBackground(new java.awt.Color(153, 153, 153));
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar.setText("guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        cmbx_areas.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_areas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areasActionPerformed(evt);
            }
        });

        lblAsunto.setBackground(new java.awt.Color(0, 102, 204));
        lblAsunto.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        lblAsunto.setForeground(new java.awt.Color(255, 255, 255));
        lblAsunto.setText("Asunto:");

        lblServicio.setBackground(new java.awt.Color(0, 102, 204));
        lblServicio.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        lblServicio.setForeground(new java.awt.Color(255, 255, 255));
        lblServicio.setText("Servicio:");

        cmbx_areas1.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_areas1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_areas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areas1ActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Area emisora:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblAsunto)
                    .addComponent(jLabel1)
                    .addComponent(lblServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.Alignment.LEADING, 0, 271, Short.MAX_VALUE)
                    .addComponent(cmbx_asuntoPrincipal, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbx_areas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbx_areas1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbx_areas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbx_asuntoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAsunto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblServicio))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(121, 121, 121))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbx_asuntoPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntoPrincipalActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            b =0;
            txtA_obs.setText("");
            serviciosSecundario = new AsuntoSecundarioServ();
            miListaAS = serviciosSecundario.traerPorAreaPrincipal((Asuntos) cmbx_asuntoPrincipal.getSelectedItem());
            cmbx_asuntoSecundario.removeAllItems();
            for(int i = 0; i < miListaAS.size();i++){
                cmbx_asuntoSecundario.addItem(miListaAS.get(i));
            }
            cmbx_asuntoSecundario.setVisible(true);
            lblServicio.setVisible(true);
        }
       
    }//GEN-LAST:event_cmbx_asuntoPrincipalActionPerformed

    private void cmbx_asuntoSecundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntoSecundarioActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            label();
        }
        
    }//GEN-LAST:event_cmbx_asuntoSecundarioActionPerformed

    private void txtA_obsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtA_obsMousePressed
        // TODO add your handling code here:
        txtA_obs.setText("");
    }//GEN-LAST:event_txtA_obsMousePressed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
        b=0;
        form = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea enviar?","Confirmar",JOptionPane.YES_NO_OPTION) == 0){
            serviciosEst = new EstadoServ();
            serviciosT = new TicketServ();
            sis = new AreaSistemaServ();
            miTick = new Tickets();
            fecha = new Date();
            miTick.setAsunto((Servicios) cmbx_asuntoSecundario.getSelectedItem());
            miTick.setFecha(fecha);
            miTick.setFkAreaEmisor((Areas) cmbx_areas1.getSelectedItem());
            miTick.setFkUsuarioEmisor(LoginEJB.usuario);
            miTick.setHora(fecha);
            miTick.setObservacion(txtA_obs.getText());
            miTick.setFkEstado(serviciosEst.traerEstado(1));
            miTick.setUsuarioReceptor(null);
            miTick.setFkAreaReceptor((Areas) cmbx_areas.getSelectedItem());
            miTick.setRespuesta(null);
            if(serviciosT.nuevoTicket(miTick) == 0){
                JOptionPane.showMessageDialog(mainFrame, "Ticket enviado...");
            }else{
                JOptionPane.showMessageDialog(mainFrame, "Error", "No se envio ticket", JOptionPane.ERROR_MESSAGE);
            }
            HistorialServ serviciosH = new HistorialServ();
            HistorialTickets miHis = new HistorialTickets();
            Date fecha = new Date();
            miHis.setFecha(fecha);
            miHis.setFkTicket(miTick);
            miHis.setFkUsuarioEmisor(LoginEJB.usuario);
            miHis.setFkEstado(miTick.getFkEstado());
            serviciosH.nuevo(miHis);
            mainFrame.menuPrincipal();
            this.setVisible(false);
            b=0;
            form = null;
            System.gc();
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            b =0;
            llenarAsuntoPrincipal((Areas) cmbx_areas.getSelectedItem());
            cmbx_asuntoPrincipal.setVisible(true);
        }
        
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void cmbx_areas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_areas1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JComboBox cmbx_areas1;
    private javax.swing.JComboBox cmbx_asuntoPrincipal;
    private javax.swing.JComboBox cmbx_asuntoSecundario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JTextArea txtA_obs;
    // End of variables declaration//GEN-END:variables
}
