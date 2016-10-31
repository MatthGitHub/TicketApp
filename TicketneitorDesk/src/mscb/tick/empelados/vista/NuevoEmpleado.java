/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.empelados.vista;

import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.areas.servicios.AreaServ;
import mscb.tick.empleados.servicios.EmpleadoServ;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.Empleados;
import mscb.tick.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class NuevoEmpleado extends MenuP {
    private static NuevoEmpleado estePanel;
    Main mainFrame;
    private EmpleadoServ serviciosE;
    private AreaServ serviciosA;
    /**
     * Creates new form NuevoEmpleado
     */
    private NuevoEmpleado(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setVisible(true);
        llenarComboBox();
    }
    
    public static NuevoEmpleado getNuevoEmpelado(Main mainFrame){
        if(estePanel == null){
            estePanel = new NuevoEmpleado(mainFrame);
        }
        return estePanel;
    }
    
    private void llenarComboBox(){
        serviciosA = new AreaServ();
        List<Areas> misA = serviciosA.traerTodas();
        
        for(int i = 0; i < misA.size(); i++){
            cmbx_areas.addItem(misA.get(i));
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

        txt_nombre = new javax.swing.JTextField();
        txt_apellido = new javax.swing.JTextField();
        txt_documento = new javax.swing.JTextField();
        txt_legajo = new javax.swing.JTextField();
        btn_menu = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        cmbx_areas = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo empleado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        txt_nombre.setBackground(new java.awt.Color(204, 204, 204));
        txt_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nombre.setText("Escriba el nombre aqui...");
        txt_nombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nombreMouseClicked(evt);
            }
        });
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        txt_apellido.setBackground(new java.awt.Color(204, 204, 204));
        txt_apellido.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_apellido.setText("Escriba el apellido aqui...");
        txt_apellido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_apellidoMouseClicked(evt);
            }
        });
        txt_apellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidoActionPerformed(evt);
            }
        });

        txt_documento.setBackground(new java.awt.Color(204, 204, 204));
        txt_documento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_documento.setText("Escriba el documento aqui...");
        txt_documento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_documentoMouseClicked(evt);
            }
        });
        txt_documento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_documentoActionPerformed(evt);
            }
        });

        txt_legajo.setBackground(new java.awt.Color(204, 204, 204));
        txt_legajo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_legajo.setText("Escriba el legajo aqui...");
        txt_legajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_legajoMouseClicked(evt);
            }
        });
        txt_legajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_legajoActionPerformed(evt);
            }
        });

        btn_menu.setBackground(new java.awt.Color(153, 153, 153));
        btn_menu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_menu.setText("empelados");
        btn_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_menuActionPerformed(evt);
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

        cmbx_areas.setBackground(new java.awt.Color(204, 204, 204));
        cmbx_areas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_areas.setForeground(new java.awt.Color(255, 255, 255));
        cmbx_areas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eliga un area" }));
        cmbx_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_menu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_guardar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(287, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_nombre)
                    .addComponent(txt_apellido, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txt_documento, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(txt_legajo, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                    .addComponent(cmbx_areas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(265, 265, 265))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(txt_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(txt_documento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(txt_legajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_menu)
                    .addComponent(btn_guardar))
                .addContainerGap(102, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_apellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidoActionPerformed

    private void txt_documentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_documentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_documentoActionPerformed

    private void txt_legajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_legajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_legajoActionPerformed

    private void btn_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_menuActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.empleados();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_menuActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        if((!txt_nombre.getText().isEmpty())&&(!txt_nombre.getText().equals("Escriba el nombre aqui..."))){
            if(((!txt_apellido.getText().isEmpty())&&(!txt_apellido.getText().equals("Escriba el apellido aqui...")))){
                if((!txt_documento.getText().isEmpty())&&(!txt_documento.getText().equals("Escriba el documento aqui..."))){
                    if((!txt_legajo.getText().isEmpty())&&(!txt_legajo.getText().equals("Escriba el legajo aqui..."))){
                        if(cmbx_areas.getSelectedIndex() > 0){
                            Empleados miEmp = new Empleados();
                            serviciosE = new EmpleadoServ();
                            miEmp.setNombre(txt_nombre.getText());
                            miEmp.setApellido(txt_apellido.getText());
                            miEmp.setDocumento(txt_documento.getText());
                            miEmp.setLegajo(Integer.parseInt(txt_legajo.getText()));
                            miEmp.setFkArea((Areas) cmbx_areas.getSelectedItem());
                            if(serviciosE.nuevo(miEmp)){
                                JOptionPane.showMessageDialog(this, "Empleado creado!","Exito!",JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(this, "Error al crear empleado","Error!",JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(this, "Seleccione un area valida","Error!",JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(this, "Ingrese legajo","Error!",JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Ingrese documento","Error!",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Ingrese apellido","Error!",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Ingrese nombre","Error!",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_nombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nombreMouseClicked
        // TODO add your handling code here:
        txt_nombre.setText("");
    }//GEN-LAST:event_txt_nombreMouseClicked

    private void txt_apellidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_apellidoMouseClicked
        // TODO add your handling code here:
        txt_apellido.setText("");
    }//GEN-LAST:event_txt_apellidoMouseClicked

    private void txt_documentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_documentoMouseClicked
        // TODO add your handling code here:
        txt_documento.setText("");
    }//GEN-LAST:event_txt_documentoMouseClicked

    private void txt_legajoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_legajoMouseClicked
        // TODO add your handling code here:
        txt_legajo.setText("");
    }//GEN-LAST:event_txt_legajoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_menu;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JTextField txt_apellido;
    private javax.swing.JTextField txt_documento;
    private javax.swing.JTextField txt_legajo;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
