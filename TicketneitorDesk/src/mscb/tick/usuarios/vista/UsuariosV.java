/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.usuarios.vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.entidades.Usuarios;
import mscb.tick.main.Main;
import mscb.tick.usuarios.servicios.UsuarioServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class UsuariosV extends MenuP {
    Main mainFrame;
    private static UsuariosV usuV;
    private List <Usuarios> listaUsuarios;
    private static List <Usuarios> cacheUsuarios;
    private UsuarioServ servicios;
    private DefaultTableModel modelo;
    
    /**
     * Creates new form Usuarios
     */    
    private UsuariosV(Main mainFrame) {
        initComponents();
        listaUsuarios = new ArrayList<>();
        cacheUsuarios = new ArrayList<>();
        this.mainFrame = mainFrame;
        this.setSize(800, 600);
        this.setVisible(true);
        cargarTablaUsuarios();
    }
    
    public static UsuariosV getUsuarios(Main mainFrame){
        if(usuV == null){
            usuV = new UsuariosV(mainFrame);
        }
        return usuV;
    }

    private List <Usuarios> traerUsuarios(){
        servicios = new UsuarioServ();
        listaUsuarios = servicios.traerTodos();
        cargarCache(listaUsuarios);
        
        return listaUsuarios;
    }
    
    private void cargarCache(List <Usuarios> aCargar){
        for(int i = 0; i < aCargar.size(); i++){
            if(!cacheUsuarios.contains(aCargar.get(i))){
                cacheUsuarios.add(aCargar.get(i));
            }
        }
        for(int i = 0; i < cacheUsuarios.size(); i++){
            if(!aCargar.contains(cacheUsuarios.get(i))){
                cacheUsuarios.remove(i);
            }
        }
        
    }
    
    public void cargarTablaUsuarios(){
        modelo = (DefaultTableModel) jt_usuarios.getModel();
        String vector[] = new String[8];
        vaciarTabla(jt_usuarios);
        List<Usuarios> aCargar;
        aCargar = traerUsuarios();
        
        for(int i = 0 ; i < aCargar.size(); i ++){
            vector[0] = aCargar.get(i).getIdUsuario().toString();
            vector[1] = aCargar.get(i).getNombreUsuario();
            vector[2] = "*****";
            vector[3] = aCargar.get(i).getFkEmpleado().getNombre();
            vector[4] = aCargar.get(i).getFkEmpleado().getApellido();
            vector[5] = aCargar.get(i).getFkEmpleado().getFkArea().getNombreArea();
            vector[7] = aCargar.get(i).getFkPermiso().getNombrePermiso();
            modelo.addRow(vector);
            
            if(aCargar.get(i).getActivo() == true ){
                modelo.setValueAt(true, i, 6);
            }else{
                modelo.setValueAt(false, i, 6);
            }
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

        chkbx_activo = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_usuarios = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_desactivar = new javax.swing.JButton();
        btn_activar = new javax.swing.JButton();
        btn_resetClave = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_desactivar1 = new javax.swing.JButton();

        chkbx_activo.setText("jCheckBox1");

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        jt_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Clave", "Nombre", "Apellido", "Area", "Activo", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class
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
        jt_usuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_usuarios);
        if (jt_usuarios.getColumnModel().getColumnCount() > 0) {
            jt_usuarios.getColumnModel().getColumn(0).setPreferredWidth(20);
            jt_usuarios.getColumnModel().getColumn(1).setPreferredWidth(35);
            jt_usuarios.getColumnModel().getColumn(2).setPreferredWidth(35);
            jt_usuarios.getColumnModel().getColumn(3).setPreferredWidth(90);
            jt_usuarios.getColumnModel().getColumn(4).setPreferredWidth(70);
            jt_usuarios.getColumnModel().getColumn(5).setPreferredWidth(150);
            jt_usuarios.getColumnModel().getColumn(6).setPreferredWidth(15);
        }

        btn_volver.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_volver.setText("volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        btn_eliminar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_eliminar.setText("eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_desactivar.setBackground(new java.awt.Color(153, 153, 153));
        btn_desactivar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_desactivar.setText("deshabilitar");

        btn_activar.setBackground(new java.awt.Color(153, 153, 153));
        btn_activar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_activar.setText("habilitar");
        btn_activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_activarActionPerformed(evt);
            }
        });

        btn_resetClave.setBackground(new java.awt.Color(153, 153, 153));
        btn_resetClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_resetClave.setText("resetear clave");
        btn_resetClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetClaveActionPerformed(evt);
            }
        });

        btn_nuevo.setBackground(new java.awt.Color(153, 153, 153));
        btn_nuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_nuevo.setText("nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_desactivar1.setBackground(new java.awt.Color(153, 153, 153));
        btn_desactivar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_desactivar1.setText("cambiar tipo");
        btn_desactivar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_desactivar1ActionPerformed(evt);
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
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133)
                        .addComponent(btn_desactivar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(276, 276, 276)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_desactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_activar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_resetClave, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_desactivar, btn_eliminar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_desactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_activar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resetClave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_desactivar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_desactivar, btn_eliminar});

    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_activarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_activarActionPerformed
        // TODO add your handling code here:
         if((jt_usuarios.getSelectedRow() != -1)&&(jt_usuarios.getSelectedRowCount()== 1)){
            if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea habilitar?") == 0){
                if(Boolean.valueOf(modelo.getValueAt(jt_usuarios.getSelectedRow(), 6).toString())== false){
                    if(servicios.habilitarUsuario(Integer.parseInt(modelo.getValueAt(jt_usuarios.getSelectedRow(), 0).toString()))== 0){
                        JOptionPane.showMessageDialog(mainFrame, "Usuario habilitado!");
                        cargarTablaUsuarios();
                    }else{
                        JOptionPane.showMessageDialog(mainFrame, "Error al habilitar usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(mainFrame,"Este usuario ya se encuentra habilitado!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
           JOptionPane.showMessageDialog(mainFrame,"Seleccionar una y solo una fila!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_activarActionPerformed

    private void btn_resetClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetClaveActionPerformed
        // TODO add your handling code here:
        if((jt_usuarios.getSelectedRow() != -1)&&(jt_usuarios.getSelectedRowCount()== 1)){
            if(JOptionPane.showConfirmDialog(mainFrame,"La nueva clava sera el nombre completo del dueño del usuario", "Seguro desea resetear la clave?",JOptionPane.YES_OPTION) == 0){
                    if(servicios.resetClave(Integer.parseInt(modelo.getValueAt(jt_usuarios.getSelectedRow(), 0).toString()))== 0){
                        JOptionPane.showMessageDialog(mainFrame, "Clave reseteada!");
                        //cargarTablaUsuarios(); --> Es innecesario ya que la clave no se ve.
                    }else{
                        JOptionPane.showMessageDialog(mainFrame, "Error al habilitar usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    }
            }
        }else{
           JOptionPane.showMessageDialog(mainFrame,"Seleccionar una y solo una fila!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_resetClaveActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        mainFrame.nuevoUsuario(mainFrame);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if((jt_usuarios.getSelectedRow() != -1)&&(jt_usuarios.getSelectedRowCount()== 1)){
            if(JOptionPane.showConfirmDialog(this, "Seguro desea eliminar?")==0){
                if(servicios.eliminarUsuario(Integer.parseInt(modelo.getValueAt(jt_usuarios.getSelectedRow(), 0).toString()))){
                    JOptionPane.showMessageDialog(this, "Usuario eliminado");
                    cargarTablaUsuarios();
                }else{
                    JOptionPane.showMessageDialog(this, "Error al eliminar usuario");
                }
            }
        }else{
           JOptionPane.showMessageDialog(mainFrame,"Seleccionar una y solo una fila!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_desactivar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_desactivar1ActionPerformed
        // TODO add your handling code here:
         if((jt_usuarios.getSelectedRow() != -1)&&(jt_usuarios.getSelectedRowCount()== 1)){
             mainFrame.cambiarTipoUsuario(servicios.buscarUnUsuario(Integer.parseInt(modelo.getValueAt(jt_usuarios.getSelectedRow(), 0).toString())));
         }else{
            JOptionPane.showMessageDialog(mainFrame,"Seleccionar una y solo una fila!", "Error", JOptionPane.ERROR_MESSAGE);
              }
    }//GEN-LAST:event_btn_desactivar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_activar;
    private javax.swing.JButton btn_desactivar;
    private javax.swing.JButton btn_desactivar1;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_resetClave;
    private javax.swing.JButton btn_volver;
    private javax.swing.JCheckBox chkbx_activo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_usuarios;
    // End of variables declaration//GEN-END:variables
}
