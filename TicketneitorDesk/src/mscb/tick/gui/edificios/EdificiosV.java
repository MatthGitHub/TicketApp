/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.edificios;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.EdificioServ;
import mscb.tick.negocio.entidades.Edificios;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class EdificiosV extends MenuP {
    Main mainFrame;
    private static EdificiosV estePanel;
    private DefaultTableModel modelo;
    
    /**
     * Creates new form Usuarios
     */    
    private EdificiosV(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        this.setVisible(true);
        cargarTablaUsuarios();
        validarPermisos();
    }
    
    public static EdificiosV getEdificiosV(Main mainFrame){
        if(estePanel == null){
            estePanel = new EdificiosV(mainFrame);
        }
        return estePanel;
    }
   
    private void validarPermisos(){
        //boton modificar
        if(!mainFrame.validarPermisos(48)){
            btn_nuevo.setEnabled(false);
            btn_nuevo.setVisible(false);
        }else{
            btn_nuevo.setEnabled(true);
            btn_nuevo.setVisible(true); 
        }
        //boton eliminar
        if(!mainFrame.validarPermisos(50)){
            btn_eliminar.setEnabled(false);
            btn_eliminar.setVisible(false);
        }else{
            btn_eliminar.setEnabled(true);
            btn_eliminar.setVisible(true); 
        }
    }
    
    public void cargarTablaUsuarios(){
        modelo = (DefaultTableModel) jt_edificios.getModel();
        String vector[] = new String[3];
        vaciarTabla(jt_edificios);
        List<Edificios> aCargar;
        aCargar = EdificioServ.getEdificioServ().traerTodos();
        
        for(int i = 0 ; i < aCargar.size(); i ++){
            vector[0] = aCargar.get(i).getIdEdificio().toString();
            vector[1] = aCargar.get(i).getNombre();
            vector[2] = aCargar.get(i).getDireccion();
            modelo.addRow(vector);
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
        jt_edificios = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();

        chkbx_activo.setText("jCheckBox1");

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Edificios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N

        jt_edificios.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jt_edificios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Direccion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jt_edificios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_edificios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jt_edificios);
        if (jt_edificios.getColumnModel().getColumnCount() > 0) {
            jt_edificios.getColumnModel().getColumn(0).setPreferredWidth(20);
            jt_edificios.getColumnModel().getColumn(1).setPreferredWidth(35);
            jt_edificios.getColumnModel().getColumn(2).setPreferredWidth(35);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 817, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_eliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_nuevo))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar))
                .addGap(48, 48, 48))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.nuevoEdificio();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_volver;
    private javax.swing.JCheckBox chkbx_activo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_edificios;
    // End of variables declaration//GEN-END:variables
}
