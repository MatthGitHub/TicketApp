/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.asuntos.vista;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.areas.servicios.AreaServ;
import mscb.tick.asuntoPrincipal.servicios.AsuntoPrincipalServ;
import mscb.tick.entidades.Areas;
import mscb.tick.entidades.Asuntos;
import mscb.tick.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class AsuntosPrin extends MenuP {
    Main mainFrame;
    private static AsuntosPrin asuntos;
    private AsuntoPrincipalServ serviciosA;
    private static List<Asuntos> misAsuntos;
    private DefaultTableModel modelo;
    private AreaServ serviciosAr;
    
    /**
     * Creates new form Asuntos
     */
    
    private AsuntosPrin(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setSize(800, 600);
        setVisible(true);
        serviciosA = new AsuntoPrincipalServ();
        serviciosAr = new AreaServ();
        validarPermisos();
        llenarComboAreas();
    }
    
    public static AsuntosPrin getAsuntos(Main mainFrame){
        if(asuntos == null){
            asuntos = new AsuntosPrin(mainFrame);
        }
        return asuntos;
    }
    
    private void validarPermisos(){
        //boton eliminar asunto
        if(mainFrame.validarPermisos(19)){
            btn_eliminar.setEnabled(true);
            btn_eliminar.setVisible(true);
        }else{
            btn_eliminar.setEnabled(false);
            btn_eliminar.setVisible(false); 
        }
        //boton nuevo
        if(mainFrame.validarPermisos(20)){
            btn_nuevo.setEnabled(true);
            btn_nuevo.setVisible(true);
        }else{
            btn_nuevo.setEnabled(false);
            btn_nuevo.setVisible(false); 
        }
    }
    
    private void eliminarAsunto(int id){
        if(serviciosA.eliminarAsunto(id) == true){
            JOptionPane.showMessageDialog(mainFrame, "Registro eliminado");
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Error al eliminar registro. Recuerde eliminar los asuntos secundarios de este asunto");
        }
    }
    
    
    public void llenarTabla(){
        vaciarTabla(jt_asuntos);
        modelo = (DefaultTableModel) jt_asuntos.getModel();
        String v[] = new String[2];
        misAsuntos =  serviciosA.asuntosPorArea((Areas) cmbx_areas.getSelectedItem());
        
        for(int i = 0; i < misAsuntos.size(); i++){
            v[0] = misAsuntos.get(i).getIdasuntoP().toString();
            v[1] = misAsuntos.get(i).getNombre();
            modelo.addRow(v);
        }
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

    private void llenarComboAreas(){
        List<Areas> misAreas = serviciosAr.traerTodasconAsuntos();
        for(int i = 0; i < misAreas.size(); i++){
            cmbx_areas.addItem(misAreas.get(i));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_asuntos = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        cmbx_areas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asuntos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        jt_asuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        jScrollPane1.setViewportView(jt_asuntos);

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

        btn_nuevo.setBackground(new java.awt.Color(153, 153, 153));
        btn_nuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_nuevo.setText("nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        cmbx_areas.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_areas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cmbx_areas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbx_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areasActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("CG Times", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Eliga un area:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.menuPrincipal();
        asuntos = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if((jt_asuntos.getSelectedRow() != -1)&&(jt_asuntos.getSelectedRowCount() == 1)){
            if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0){
                eliminarAsunto(Integer.parseInt(jt_asuntos.getValueAt(jt_asuntos.getSelectedRow(), 0).toString()));
                llenarTabla();
            }
            
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        if((!cmbx_areas.getSelectedItem().equals("-"))&&(cmbx_areas.getSelectedIndex() != -1)){
            mainFrame.nuevoAsunto((Areas) cmbx_areas.getSelectedItem());
        }else{
            Areas area = new Areas();
            area.setNombreArea("nuevo");
            mainFrame.nuevoAsunto(area);
        }
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
        if((cmbx_areas.getSelectedIndex() != -1)&&(!cmbx_areas.getSelectedItem().equals("-"))){
            llenarTabla();
        }
    }//GEN-LAST:event_cmbx_areasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_asuntos;
    // End of variables declaration//GEN-END:variables
}
