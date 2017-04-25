/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.usuarios;

import mscb.tick.gui.main.Main;
import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.negocio.entidades.Permisos;
import mscb.tick.negocio.entidades.Roles;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.negocio.PermisoServ;
import mscb.tick.negocio.RoleServ;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class CambiarTipoP extends MenuP {
    private static CambiarTipoP estePanel;
    private CambiarTipoD mainFrame;
    private Main mainFrameO;
    private Usuarios usu;
    private RoleServ serviciosP;
    private UsuarioServ serviciosU;
    private UsuariosV tabla;
    
    /**
     * 
     * @param mainFrame
     * @param usu 
     */
    public CambiarTipoP(CambiarTipoD mainFrame, Usuarios usu,Main mainFrameO) {
        initComponents();
        serviciosP = new RoleServ();
        serviciosU = new UsuarioServ();
        this.mainFrame = mainFrame;
        this.mainFrameO = mainFrameO;
        setSize(260, 360);
        setVisible(true);
        this.usu = usu;
        lbl_nombre.setText(usu.getNombreUsuario());
        lbl_tipoU.setText(usu.getFkRol().getNombreRol());
        cargarComboBox();
    }
    
    public static CambiarTipoP getPanelPgm(CambiarTipoD mainFrame, Usuarios usu,Main mainFrameO){
        if(estePanel == null ){
            estePanel = new CambiarTipoP(mainFrame,usu,mainFrameO);
        }
        return estePanel;
    }
    
    
    /**
     * 
     */
    private void cargarComboBox(){
        List<Roles> miLista = serviciosP.traerTodos();
        for(int i = 0; i < miLista.size(); i++){
            cmbx_permisos.addItem(miLista.get(i));
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

        btn_volver = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        lbl_tipoU = new javax.swing.JLabel();
        lbl_nombre = new javax.swing.JLabel();
        lbl_tipoU1 = new javax.swing.JLabel();
        cmbx_permisos = new javax.swing.JComboBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cambiar tipo usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        setForeground(new java.awt.Color(255, 255, 255));

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

        btn_guardar.setBackground(new java.awt.Color(153, 153, 153));
        btn_guardar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(0, 108, 118));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        lbl_tipoU.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lbl_tipoU.setForeground(new java.awt.Color(0, 108, 118));

        lbl_nombre.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lbl_nombre.setForeground(new java.awt.Color(0, 108, 118));

        lbl_tipoU1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lbl_tipoU1.setForeground(new java.awt.Color(0, 108, 118));
        lbl_tipoU1.setText("Nuevo tipo:");

        cmbx_permisos.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_permisos.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        cmbx_permisos.setForeground(new java.awt.Color(0, 108, 118));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbx_permisos, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tipoU, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_tipoU1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_volver)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_guardar))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbl_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_tipoU, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_tipoU1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbx_permisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver)
                    .addComponent(btn_guardar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        mainFrame.setVisible(false);
        estePanel = null;
        mainFrame.dispose();
        mainFrame = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        usu.setFkRol((Roles) cmbx_permisos.getSelectedItem());
        if(serviciosU.modificarUsuario(usu)){
            tabla = UsuariosV.getUsuarios(mainFrameO);
            tabla.cargarTablaUsuarios();
            setVisible(false);
            mainFrame.setVisible(false);
            estePanel = null;
            mainFrame.dispose();
            mainFrame = null;
            System.gc();
        }else{
            JOptionPane.showMessageDialog(this, "Ocurrio un error al modificar", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_permisos;
    private javax.swing.JLabel lbl_nombre;
    private javax.swing.JLabel lbl_tipoU;
    private javax.swing.JLabel lbl_tipoU1;
    // End of variables declaration//GEN-END:variables
}
