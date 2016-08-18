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
import mscb.tick.asuntoPrincipal.servicios.AsuntoPrincipalServ;
import mscb.tick.asuntoSecundario.servicios.AsuntoSecundarioServ;
import mscb.tick.entidades.AreaSistemas;
import mscb.tick.entidades.Asuntos;
import mscb.tick.entidades.Servicios;
import mscb.tick.entidades.Tickets;
import mscb.tick.estados.servicios.EstadoServ;
import mscb.tick.login.Login;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.tickets.servicios.TicketServ;
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
        cmbx_asuntoSecundario.setVisible(false);
        llenarAsuntoPrincipal();
    }
    
    public static NuevoTicket getNuevoTicket(Main mainFrame){
        if(form == null){
            form = new NuevoTicket(mainFrame);
        }
        return form;
    }
    
    private void llenarAsuntoPrincipal(){
        serviciosPrincipal = new AsuntoPrincipalServ();
        miListaAP = serviciosPrincipal.traerTodos();
        
        for(int i = 0 ; i < miListaAP.size(); i++){
            cmbx_asuntoPrincipal.addItem(miListaAP.get(i));
        }
    }
    /**
     * Funcion para pedir que escriban los requisitos del pedido en el campo de observaciones
     * en base a 2 switch, uno para el cmbx principal y otro para el cmbx secundario.
     */
    public void label(){
        //Swit para ver el asunto prçincipal
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

        btn_volver1 = new javax.swing.JToggleButton();
        btn_guardar = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        cmbx_asuntoPrincipal = new javax.swing.JComboBox();
        cmbx_asuntoSecundario = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_obs = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo pedido a sistemas", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        btn_volver1.setBackground(new java.awt.Color(0, 102, 204));
        btn_volver1.setForeground(new java.awt.Color(255, 255, 255));
        btn_volver1.setText("Volver");
        btn_volver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volver1ActionPerformed(evt);
            }
        });

        btn_guardar.setBackground(new java.awt.Color(0, 102, 204));
        btn_guardar.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Eliga un asunto:");

        cmbx_asuntoPrincipal.setBackground(new java.awt.Color(0, 102, 204));
        cmbx_asuntoPrincipal.setForeground(new java.awt.Color(255, 255, 255));
        cmbx_asuntoPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoPrincipalActionPerformed(evt);
            }
        });

        cmbx_asuntoSecundario.setBackground(new java.awt.Color(0, 102, 204));
        cmbx_asuntoSecundario.setForeground(new java.awt.Color(255, 255, 255));
        cmbx_asuntoSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoSecundarioActionPerformed(evt);
            }
        });

        txtA_obs.setBackground(new java.awt.Color(0, 102, 204));
        txtA_obs.setColumns(20);
        txtA_obs.setForeground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_volver1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbx_asuntoSecundario, 0, 388, Short.MAX_VALUE)
                                    .addComponent(cmbx_asuntoPrincipal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 299, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbx_asuntoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 133, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_volver1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volver1ActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
        b=0;
        form = null;
        System.gc();
    }//GEN-LAST:event_btn_volver1ActionPerformed

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
            miTick.setFkAreaEmisor(LoginEJB.usuario.getFkEmpleado().getFkArea());
            miTick.setFkUsuarioEmisor(LoginEJB.usuario);
            miTick.setHora(fecha);
            miTick.setObservacion(txtA_obs.getText());
            switch (cmbx_asuntoPrincipal.getSelectedItem().toString()){
                case "PGM":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                
                case "WebDoc":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                
                case "Bariloche.gov.ar":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                
                case "Comunicar":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                
                case "Cajero":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                    
                case "Sueldos":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                    
                case "Pagina de Medicina":
                    miTick.setFkAreaSistemas(sis.traerUno(1));
                    break;
                    
                case "Correo":
                    miTick.setFkAreaSistemas(sis.traerUno(2));
                    break;
                    
                case "Tecnico":
                    miTick.setFkAreaSistemas(sis.traerUno(2));
                    break;
                    
                case "Problemas de comunicacion":
                    miTick.setFkAreaSistemas(sis.traerUno(2));
                    break;
            }
           miTick.setFkEstado(serviciosEst.traerEstado(1));
           miTick.setUsuarioReceptor(null);
           miTick.setRespuesta(null);
           if(serviciosT.nuevoTicket(miTick) == 0){
               JOptionPane.showMessageDialog(mainFrame, "Ticket enviado...");
           }else{
               JOptionPane.showMessageDialog(mainFrame, "Error", "No se envio ticket", JOptionPane.ERROR_MESSAGE);
           }
        mainFrame.menuPrincipal();
        this.setVisible(false);
        b=0;
        form = null;
        System.gc();
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_guardar;
    private javax.swing.JToggleButton btn_volver1;
    private javax.swing.JComboBox cmbx_asuntoPrincipal;
    private javax.swing.JComboBox cmbx_asuntoSecundario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtA_obs;
    // End of variables declaration//GEN-END:variables
}
