/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.usuarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.negocio.UsuarioServ;
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
        this.setVisible(true);
        validarPermisos();
        cargarTablaUsuarios();
    }
    
    public static UsuariosV getUsuarios(Main mainFrame){
        if(usuV == null){
            usuV = new UsuariosV(mainFrame);
        }
        return usuV;
    }
    private void validarPermisos(){
        //boton eliminar
        if(!mainFrame.validarPermisos(28)){
            btn_eliminar.setEnabled(false);
            btn_eliminar.setVisible(false);
        }else{
            btn_eliminar.setEnabled(true);
            btn_eliminar.setVisible(true); 
        }
        //boton deshabilitar
        if(!mainFrame.validarPermisos(29)){
            btn_desactivar.setEnabled(false);
            btn_desactivar.setVisible(false);
        }else{
            btn_desactivar.setEnabled(true);
            btn_desactivar.setVisible(true); 
        }
        //boton habilitar
        if(!mainFrame.validarPermisos(30)){
            btn_activar.setEnabled(false);
            btn_activar.setVisible(false);
        }else{
            btn_activar.setEnabled(true);
            btn_activar.setVisible(true); 
        }
        //boton habilitar
        if(!mainFrame.validarPermisos(31)){
            btn_resetClave.setEnabled(false);
            btn_resetClave.setVisible(false);
        }else{
            btn_resetClave.setEnabled(true);
            btn_resetClave.setVisible(true); 
        }
        //boton nuevo
        if(!mainFrame.validarPermisos(32)){
            btn_nuevo.setEnabled(false);
            btn_nuevo.setVisible(false);
        }else{
            btn_nuevo.setEnabled(true);
            btn_nuevo.setVisible(true); 
        }
        //boton cambiar tipo
        if(!mainFrame.validarPermisos(33)){
            btn_cambiarTipo.setEnabled(false);
            btn_cambiarTipo.setVisible(false);
        }else{
            btn_cambiarTipo.setEnabled(true);
            btn_cambiarTipo.setVisible(true); 
        }
    }
    private List <Usuarios> traerUsuarios(){
        servicios = UsuarioServ.getUsuarioServ();
        if(mainFrame.validarPermisos(59)){
            listaUsuarios = servicios.traerTodos();
        }else{
            listaUsuarios = servicios.traerPorArea(LoginEJB.usuario.getFkEmpleado().getFkArea());
        }
        
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
            vector[7] = aCargar.get(i).getFkRol().getNombreRol();
            modelo.addRow(vector);
            
            if(aCargar.get(i).getActivo() == true ){
                modelo.setValueAt(true, i, 6);
            }else{
                modelo.setValueAt(false, i, 6);
            }
        }
        revalidate();
    }
    
    public void cargarTablaUsuariosBuscados(List<Usuarios> aCargar){
        modelo = (DefaultTableModel) jt_usuarios.getModel();
        String vector[] = new String[8];
        vaciarTabla(jt_usuarios);
        
        for(int i = 0 ; i < aCargar.size(); i ++){
            vector[0] = aCargar.get(i).getIdUsuario().toString();
            vector[1] = aCargar.get(i).getNombreUsuario();
            vector[2] = "*****";
            vector[3] = aCargar.get(i).getFkEmpleado().getNombre();
            vector[4] = aCargar.get(i).getFkEmpleado().getApellido();
            vector[5] = aCargar.get(i).getFkEmpleado().getFkArea().getNombreArea();
            vector[7] = aCargar.get(i).getFkRol().getNombreRol();
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
            modelo = (DefaultTableModel) tabla.getModel();
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
        btn_cambiarTipo = new javax.swing.JButton();
        txt_id = new javax.swing.JTextField();

        chkbx_activo.setText("jCheckBox1");

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

        jt_usuarios.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
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
        jt_usuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
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
        btn_volver.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_volver.setForeground(new java.awt.Color(0, 108, 118));
        btn_volver.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/back-arrow.png"))); // NOI18N
        btn_volver.setText("Volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(153, 153, 153));
        btn_eliminar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(0, 108, 118));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/delete.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_desactivar.setBackground(new java.awt.Color(153, 153, 153));
        btn_desactivar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_desactivar.setForeground(new java.awt.Color(0, 108, 118));
        btn_desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/disable_user.png"))); // NOI18N
        btn_desactivar.setText("Deshabilitar");
        btn_desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_desactivarActionPerformed(evt);
            }
        });

        btn_activar.setBackground(new java.awt.Color(153, 153, 153));
        btn_activar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_activar.setForeground(new java.awt.Color(0, 108, 118));
        btn_activar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/enable_user.png"))); // NOI18N
        btn_activar.setText("Habilitar");
        btn_activar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_activarActionPerformed(evt);
            }
        });

        btn_resetClave.setBackground(new java.awt.Color(153, 153, 153));
        btn_resetClave.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_resetClave.setForeground(new java.awt.Color(0, 108, 118));
        btn_resetClave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/reset_pass.png"))); // NOI18N
        btn_resetClave.setText("resetear clave");
        btn_resetClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetClaveActionPerformed(evt);
            }
        });

        btn_nuevo.setBackground(new java.awt.Color(153, 153, 153));
        btn_nuevo.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_nuevo.setForeground(new java.awt.Color(0, 108, 118));
        btn_nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/add.png"))); // NOI18N
        btn_nuevo.setText("Nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_cambiarTipo.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarTipo.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_cambiarTipo.setForeground(new java.awt.Color(0, 108, 118));
        btn_cambiarTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/user_type.png"))); // NOI18N
        btn_cambiarTipo.setText("Cambiar tipo");
        btn_cambiarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarTipoActionPerformed(evt);
            }
        });

        txt_id.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(0, 108, 118));
        txt_id.setText("Usuario, nombre, apellido, legajo...");
        txt_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_idFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_idFocusLost(evt);
            }
        });
        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cambiarTipo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_resetClave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_activar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_desactivar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_nuevo))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cambiarTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_resetClave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_activar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_desactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar))
                .addGap(48, 48, 48))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_desactivar, btn_eliminar});

    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
        usuV = null;
        System.gc();
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
            if(JOptionPane.showConfirmDialog(mainFrame,"La nueva clava sera el legajo del usuario", "Seguro desea resetear la clave?",JOptionPane.YES_OPTION) == 0){
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
        mainFrame.nuevoUsuario();
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

    private void btn_cambiarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarTipoActionPerformed
        // TODO add your handling code here:
         if((jt_usuarios.getSelectedRow() != -1)&&(jt_usuarios.getSelectedRowCount()== 1)){
             mainFrame.cambiarTipoUsuario(servicios.buscarUnUsuario(Integer.parseInt(modelo.getValueAt(jt_usuarios.getSelectedRow(), 0).toString())));
         }else{
            JOptionPane.showMessageDialog(mainFrame,"Seleccionar una y solo una fila!", "Error", JOptionPane.ERROR_MESSAGE);
              }
    }//GEN-LAST:event_btn_cambiarTipoActionPerformed

    private void btn_desactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_desactivarActionPerformed
        // TODO add your handling code here:
        if((jt_usuarios.getSelectedRow() != -1)&&(jt_usuarios.getSelectedRowCount()== 1)){
            if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea inhabilitar?") == 0){
                if(Boolean.valueOf(modelo.getValueAt(jt_usuarios.getSelectedRow(), 6).toString())== true){
                    if(servicios.deshabilitarUsuario(Integer.parseInt(modelo.getValueAt(jt_usuarios.getSelectedRow(), 0).toString()))== 0){
                        JOptionPane.showMessageDialog(mainFrame, "Usuario inhabilitado!");
                        cargarTablaUsuarios();
                    }else{
                        JOptionPane.showMessageDialog(mainFrame, "Error al inhabilitar usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(mainFrame,"Este usuario ya se encuentra inhabilitado!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }else{
           JOptionPane.showMessageDialog(mainFrame,"Seleccionar una y solo una fila!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_desactivarActionPerformed

    private void txt_idFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusGained
        // TODO add your handling code here:
        txt_id.setText("");
    }//GEN-LAST:event_txt_idFocusGained

    private void txt_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_idFocusLost
        // TODO add your handling code here:
        txt_id.setText("Usuario, Servicio, Patrimonio, Lugar de trabajo...");
    }//GEN-LAST:event_txt_idFocusLost

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
        if(txt_id.getText().trim().length() >0){
            if(mainFrame.validarPermisos(59)){
                cargarTablaUsuariosBuscados(servicios.buscarUsuarios(txt_id.getText().trim()));
            }else{
                cargarTablaUsuariosBuscados(servicios.buscarUsuariosPorArea(txt_id.getText().trim()));
            }

        }
    }//GEN-LAST:event_txt_idActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_activar;
    private javax.swing.JButton btn_cambiarTipo;
    private javax.swing.JButton btn_desactivar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_resetClave;
    private javax.swing.JButton btn_volver;
    private javax.swing.JCheckBox chkbx_activo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_usuarios;
    private javax.swing.JTextField txt_id;
    // End of variables declaration//GEN-END:variables
}
