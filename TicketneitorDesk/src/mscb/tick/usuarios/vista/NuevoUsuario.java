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
import mscb.tick.empleados.servicios.EmpleadoServ;
import mscb.tick.entidades.Empleados;
import mscb.tick.entidades.Permisos;
import mscb.tick.entidades.Usuarios;
import mscb.tick.main.Main;
import mscb.tick.permisos.servicios.PermisoServ;
import mscb.tick.usuarios.servicios.UsuarioServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class NuevoUsuario extends MenuP {
    private static NuevoUsuario estePanel;
    NuevoUsuarioF mainFrame;
    Main mainFrameO;
    private EmpleadoServ buscaE;
    private List <Empleados> listaEmp;
    private PermisoServ buscaP;
    private List <Permisos> listaPer;
    private Usuarios nuevoUsu;
    private UsuarioServ guardarU;
    private UsuariosV tabla;
    private DefaultTableModel modelo;
    /**
     * Creates new form NuevoUsuario
     */
    private NuevoUsuario(NuevoUsuarioF mainFrame,Main mainFrameO) {
        initComponents();
        this.mainFrame = mainFrame;
        this.mainFrameO = mainFrameO;
        modelo = (DefaultTableModel) jt_empleados.getModel();
        buscaE = new EmpleadoServ();
        listaEmp = buscaE.traerTodos();
        llenarTabla(listaEmp);
        cargarCmBxPermisos();
        setSize(720, 450);
        setVisible(true);
        txt_nombre.requestFocus();
    }
    
    public static NuevoUsuario getNuevoUsuarioPanel(NuevoUsuarioF mainFrame,Main mainFrameO){
        if(estePanel == null){
            estePanel = new NuevoUsuario(mainFrame,mainFrameO);
        }
        return estePanel;
    }
    
    private void cargarCmBxPermisos(){
        buscaP = new PermisoServ();
        listaPer = new ArrayList<>();
        listaPer = buscaP.traerTodos();
        
        for(int i = 0; i < listaPer.size(); i ++){
            cmbx_permisos.addItem(listaPer.get(i));
        }
    }
    
    private void llenarTabla(List<Empleados> aCargar){
        vaciarTabla(jt_empleados);
        String[] v = new String[3];
        
        for(int i = 0; i < aCargar.size(); i ++){
            v[0] = aCargar.get(i).getApellido();
            v[1] = aCargar.get(i).getNombre();
            v[2] = aCargar.get(i).getIdEmpleado().toString();
            modelo.addRow(v);
        }
    }
    
    private Empleados traerEmpleado(){
        return buscaE.traerEmpleado(Integer.parseInt(modelo.getValueAt(jt_empleados.getSelectedRow(), 2).toString()));
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_clave = new javax.swing.JTextField();
        cmbx_permisos = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_empleados = new javax.swing.JTable();
        txt_buscar = new javax.swing.JTextField();
        btn_volver = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo usuario", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N
        setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Clave:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre de Usuario:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Empleado:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tipo de Usuario:");

        txt_nombre.setBackground(new java.awt.Color(204, 204, 204));
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        txt_clave.setBackground(new java.awt.Color(204, 204, 204));

        cmbx_permisos.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_permisos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_permisos.setForeground(new java.awt.Color(255, 255, 255));

        jt_empleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellido", "Nombre", "IdEmpleado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_empleados);
        if (jt_empleados.getColumnModel().getColumnCount() > 0) {
            jt_empleados.getColumnModel().getColumn(2).setPreferredWidth(25);
        }

        txt_buscar.setBackground(new java.awt.Color(204, 204, 204));
        txt_buscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
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

        btn_guardar.setBackground(new java.awt.Color(153, 153, 153));
        btn_guardar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_guardar.setText("guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
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
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbx_permisos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(262, 262, 262))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txt_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(451, 451, 451)
                                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbx_permisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
        txt_clave.requestFocus();
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
        if(txt_buscar.getText().trim().length() > 0){
            llenarTabla(buscaE.traerEmpleados(txt_buscar.getText()));
        }
    }//GEN-LAST:event_txt_buscarActionPerformed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        //this.setVisible(false);
        mainFrame.setVisible(false);
        estePanel = null;
        mainFrame.dispose();
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        if((txt_nombre.getText().trim().length() > 0)&&(!txt_nombre.getText().trim().contains("0 1 2 3 4 5 6 7 8 9"))){
            if(txt_clave.getText().trim().length() > 0){
                if(JOptionPane.showConfirmDialog(estePanel, "Seguro desea guardar?") == 0){
                    guardarU = new UsuarioServ();
                    nuevoUsu = new Usuarios();
                    nuevoUsu.setActivo(true);
                    nuevoUsu.setContrasenia(txt_clave.getText());
                    nuevoUsu.setNombreUsuario(txt_nombre.getText());
                    nuevoUsu.setFkEmpleado(traerEmpleado());
                    nuevoUsu.setFkPermiso((Permisos) cmbx_permisos.getSelectedItem());
                    if(guardarU.persistirUsuario(nuevoUsu) == 1){
                        JOptionPane.showMessageDialog(estePanel, "Usuario guardado!");
                        tabla = UsuariosV.getUsuarios(mainFrameO);
                        tabla.cargarTablaUsuarios();
                        mainFrame.setVisible(false);
                        this.setVisible(false);
                        estePanel = null;
                        mainFrame = null;
                        System.gc();
                    }else{
                        JOptionPane.showMessageDialog(estePanel, "Error al guardar!","Error!",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(estePanel, "Debe ingresar una clave!", "Error!", JOptionPane.ERROR_MESSAGE);
            }
        }else{
                JOptionPane.showMessageDialog(estePanel, "Debe ingresar un nombre de usuario!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_permisos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_empleados;
    private javax.swing.JTextField txt_buscar;
    private javax.swing.JTextField txt_clave;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
