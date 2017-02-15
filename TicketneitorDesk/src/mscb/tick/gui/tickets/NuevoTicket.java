/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.negocio.AreaServ;
import mscb.tick.negocio.AsuntoPrincipalServ;
import mscb.tick.negocio.AsuntoSecundarioServ;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.util.MenuP;
import com.mxrck.autocompleter.TextAutoCompleter;

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
    private TicketServ serviciosT;
    private Time hora;
    private EstadoServ serviciosEst;
    private AreaServ serviciosA;
    private TextAutoCompleter textAutoAcompleter;
    
    /**
     * Creates new form NuevoTicket
     */
    private NuevoTicket(Main mainFrame) {
        initComponents();
        //B es una bandera para que el combobox de asuntos no se cargue cuando entre, sino que cuando selecciones
        b = 0;
        this.mainFrame = mainFrame;
        setVisible(true);
        lblAsunto.setVisible(false);
        lblServicio.setVisible(false);
        asteriscoAsunto.setVisible(false);
        asteriscoServicio.setVisible(false);
        cmbx_asuntoPrincipal.setVisible(false);
        cmbx_asuntoSecundario.setVisible(false);
        textAutoAcompleter = new TextAutoCompleter(txt_areaE);
        textAutoAcompleter.setMode(0);
        textAutoAcompleter.setCaseSensitive(false);
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
            textAutoAcompleter.addItem(misAreas2.get(i));
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
        btn_volver = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        cmbx_areas = new javax.swing.JComboBox();
        lblAsunto = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_areaE = new javax.swing.JTextField();
        txt_patrimonio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_solicita = new javax.swing.JTextField();
        asteriscoServicio = new javax.swing.JLabel();
        asteriscoArea = new javax.swing.JLabel();
        asteriscoAsunto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

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
        jLabel3.setText("Escriba una observacion siempre que sea posible...");

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

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Area emisora:");

        txt_areaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_areaEActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Patrimonio:");

        jLabel7.setBackground(new java.awt.Color(0, 102, 204));
        jLabel7.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Solicita: ");

        asteriscoServicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        asteriscoServicio.setForeground(new java.awt.Color(255, 0, 0));
        asteriscoServicio.setText("*");

        asteriscoArea.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        asteriscoArea.setForeground(new java.awt.Color(255, 0, 0));
        asteriscoArea.setText("*");

        asteriscoAsunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        asteriscoAsunto.setForeground(new java.awt.Color(255, 0, 0));
        asteriscoAsunto.setText("*");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("* (Campos obligatorios)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_areaE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_solicita, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_patrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(275, 275, 275))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(asteriscoAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblAsunto))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(asteriscoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblServicio))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(asteriscoArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbx_asuntoPrincipal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(asteriscoArea))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbx_asuntoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAsunto)
                    .addComponent(asteriscoAsunto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblServicio)
                    .addComponent(asteriscoServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_solicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_patrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            asteriscoServicio.setVisible(true);
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
                miTick = new Tickets();
                fecha = new Date();
                miTick.setServicio((Servicios) cmbx_asuntoSecundario.getSelectedItem());
                miTick.setFecha(fecha);
                miTick.setCreador(LoginEJB.usuario);
                miTick.setHora(fecha);
                if(!txt_solicita.getText().isEmpty()){
                    if(!txt_areaE.getText().isEmpty()){
                        if(textAutoAcompleter.itemExists(textAutoAcompleter.getItemSelected())){
                            if(!txtA_obs.getText().isEmpty()){
                                miTick.setObservacion("Solicita: "+txt_solicita.getText()+"\n Area Emisora: "+txt_areaE.getText()+"\n Observacion: "+txtA_obs.getText());
                            }else{
                                 miTick.setObservacion("Solicita: "+txt_solicita.getText()+"\n Area Emisora: "+txt_areaE.getText());
                            }
                        }else{
                            JOptionPane.showMessageDialog(mainFrame, "Error", "Error al seleccionar area emisora", JOptionPane.ERROR_MESSAGE);
                        }   
                    }else{
                        if(!txtA_obs.getText().isEmpty()){
                            miTick.setObservacion("Solicita: "+txt_solicita.getText()+"\n Observacion: "+txtA_obs.getText());
                        }else{
                            miTick.setObservacion("Solicita: "+txt_solicita.getText());
                        }
                    }
                }else{
                    if(!txt_areaE.getText().isEmpty()){
                        if(textAutoAcompleter.itemExists(textAutoAcompleter.getItemSelected())){
                            if(!txtA_obs.getText().isEmpty()){
                                miTick.setObservacion("Area Emisora: "+txt_areaE.getText()+"\n Observacion: "+txtA_obs.getText());
                            }else{
                                 miTick.setObservacion("Area Emisora: "+txt_areaE.getText());
                            }
                        }else{
                            JOptionPane.showMessageDialog(mainFrame, "Error", "Error al seleccionar area emisora", JOptionPane.ERROR_MESSAGE);
                        }   
                    }else{
                        if(!txtA_obs.getText().isEmpty()){
                            miTick.setObservacion("Observacion: "+txtA_obs.getText());
                        }else{
                            miTick.setObservacion("");
                        }
                    }
                }
                miTick.setPatrimonio(txt_patrimonio.getText());
                if(serviciosT.nuevoTicket(miTick) == 0){
                    JOptionPane.showMessageDialog(mainFrame, "Ticket enviado...");
                    HistorialServ serviciosH = new HistorialServ();
                    HistorialTickets miHis = new HistorialTickets();
                    Date fecha = new Date();
                    miHis.setFecha(fecha);
                    miHis.setFkTicket(miTick);
                    miHis.setFkUsuario(LoginEJB.usuario);
                    miHis.setFkEstado(serviciosEst.traerEstado(1));
                    serviciosH.nuevo(miHis);
                    mainFrame.menuPrincipal();
                    this.setVisible(false);
                    b=0;
                    form = null;
                    System.gc();
                }else{
                    JOptionPane.showMessageDialog(mainFrame,"No se envio ticket","Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            b =0;
            llenarAsuntoPrincipal((Areas) cmbx_areas.getSelectedItem());
            cmbx_asuntoPrincipal.setVisible(true);
            lblAsunto.setVisible(true);
            asteriscoAsunto.setVisible(true);
        }
        
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void txt_areaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_areaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_areaEActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asteriscoArea;
    private javax.swing.JLabel asteriscoAsunto;
    private javax.swing.JLabel asteriscoServicio;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JComboBox cmbx_asuntoPrincipal;
    private javax.swing.JComboBox cmbx_asuntoSecundario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JTextArea txtA_obs;
    private javax.swing.JTextField txt_areaE;
    private javax.swing.JTextField txt_patrimonio;
    private javax.swing.JTextField txt_solicita;
    // End of variables declaration//GEN-END:variables
}
