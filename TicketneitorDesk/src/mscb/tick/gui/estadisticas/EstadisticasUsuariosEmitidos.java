/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.estadisticas;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.AreaServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.Estadisticas;
import mscb.tick.util.MenuP;

/**
 *Estadistica de usuarios que enviaron los tickets de EstadisticasAreasRecibidos
 * @author Administrador
 */
public class EstadisticasUsuariosEmitidos extends MenuP {
    private static EstadisticasUsuariosEmitidos estePanel;
    private static Integer idArea;
    /**
     * Creates new form EstadisticasAreas
     */
    private EstadisticasUsuariosEmitidos(Integer idArea) {
        initComponents();
        this.idArea = idArea;
        llenarTabla(idArea);
        setVisible(true);
    }
    
    public static EstadisticasUsuariosEmitidos getEstadisticasUsuarios(Integer idArea){
        if(estePanel == null){
            estePanel = new EstadisticasUsuariosEmitidos(idArea);
        }
        EstadisticasUsuariosEmitidos.idArea = idArea;
        return estePanel;
    }
    
    
    public void llenarTabla(Integer idArea){
        lblArea.setText(AreaServ.getAreaServ().getArea(idArea).getNombreArea());
        vaciarTabla(jt_estadisticas);
        List<Estadisticas> miLista = TicketServ.getTicketServ().getEstadisticasPorUsuarios(idArea);
        DefaultTableModel modelo = (DefaultTableModel) jt_estadisticas.getModel();
        String[] v = new String[2];

        for(int i = 0; i < miLista.size(); i++){
            v[0] = miLista.get(i).getNombre();
            v[1] = miLista.get(i).getCantidad().toString();
            modelo.addRow(v);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_estadisticas = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        lblArea = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tickets por usuarios", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N

        jt_estadisticas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuarios", "Cantidad de tickets"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jt_estadisticas);

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

        lblArea.setBackground(new java.awt.Color(0, 102, 204));
        lblArea.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        lblArea.setForeground(new java.awt.Color(0, 108, 118));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addComponent(lblArea, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblArea, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        estePanel = null;
        System.gc();
        Main.getMainFrame().estadisticasPorArea();
    }//GEN-LAST:event_btn_volverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_estadisticas;
    private javax.swing.JLabel lblArea;
    // End of variables declaration//GEN-END:variables
}