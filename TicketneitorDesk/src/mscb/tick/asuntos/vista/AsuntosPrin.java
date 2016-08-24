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
import mscb.tick.asuntoPrincipal.servicios.AsuntoPrincipalServ;
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
    
    /**
     * Creates new form Asuntos
     */
    
    private AsuntosPrin(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setSize(800, 600);
        setVisible(true);
        serviciosA = new AsuntoPrincipalServ();
        llenarTablar();
    }
    
    public static AsuntosPrin getAsuntos(Main mainFrame){
        if(asuntos == null){
            asuntos = new AsuntosPrin(mainFrame);
        }
        return asuntos;
    }
    
    private List<Asuntos> traerAsuntos(){
        return serviciosA.traerTodos();
    }
    
    private void eliminarAsunto(int id){
        if(serviciosA.eliminarAsunto(id) == true){
            JOptionPane.showMessageDialog(mainFrame, "Registro eliminado");
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Error al eliminar registro. Recuerde eliminar los asuntos secundarios de este asunto");
        }
    }
    
    
    public void llenarTablar(){
        vaciarTabla(jt_asuntos);
        modelo = (DefaultTableModel) jt_asuntos.getModel();
        String v[] = new String[2];
        misAsuntos = traerAsuntos();
        
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
        btn_voler = new javax.swing.JToggleButton();
        btn_eliminar = new javax.swing.JToggleButton();
        btn_nuevo = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asuntos", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 36), java.awt.Color.white)); // NOI18N

        jt_asuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        jScrollPane1.setViewportView(jt_asuntos);

        btn_voler.setBackground(new java.awt.Color(0, 102, 204));
        btn_voler.setForeground(new java.awt.Color(255, 255, 255));
        btn_voler.setText("volver");
        btn_voler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volerActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(0, 102, 204));
        btn_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setText("eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_nuevo.setBackground(new java.awt.Color(0, 102, 204));
        btn_nuevo.setForeground(new java.awt.Color(255, 255, 255));
        btn_nuevo.setText("nuevo");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_voler, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_eliminar, btn_voler});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_voler))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_eliminar, btn_voler});

    }// </editor-fold>//GEN-END:initComponents

    private void btn_volerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volerActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.menuPrincipal();
    }//GEN-LAST:event_btn_volerActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if((jt_asuntos.getSelectedRow() != -1)&&(jt_asuntos.getSelectedRowCount() == 1)){
            if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea eliminar?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0){
                eliminarAsunto(Integer.parseInt(jt_asuntos.getValueAt(jt_asuntos.getSelectedRow(), 0).toString()));
                llenarTablar();
            }
            
        }else{
            JOptionPane.showMessageDialog(mainFrame, "Debe seleccionar una y solo una fila!");
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        mainFrame.nuevoAsunto();
    }//GEN-LAST:event_btn_nuevoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btn_eliminar;
    private javax.swing.JToggleButton btn_nuevo;
    private javax.swing.JToggleButton btn_voler;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_asuntos;
    // End of variables declaration//GEN-END:variables
}