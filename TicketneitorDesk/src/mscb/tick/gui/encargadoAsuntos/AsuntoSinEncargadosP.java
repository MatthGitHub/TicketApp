/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.encargadoAsuntos;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.AsuntoSecundarioServ;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.gui.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class AsuntoSinEncargadosP extends MenuP {
    AsuntoSinEncargadoD mainFrame;
    private Main mainFrameO;
    private static AsuntoSinEncargadosP estePanel;
    private DefaultTableModel modelo;
    private AsuntoSecundarioServ servicioA;
    private List<Servicios> miLista;
    /**
     * Creates new form AsuntoSinEncargados
     */
    private AsuntoSinEncargadosP(AsuntoSinEncargadoD mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setSize(360, 320);
        setVisible(true);
        modelo = (DefaultTableModel) jt_asuntos.getModel();
        servicioA = AsuntoSecundarioServ.getAsuntoPrincipalServ();
        llenarTabla();
    }
    
    public static AsuntoSinEncargadosP getAsuntoSinEncargadosP(AsuntoSinEncargadoD mainFrame){
        if(estePanel == null){
            estePanel = new AsuntoSinEncargadosP(mainFrame);
        }
        return estePanel;
    }
    
    public void llenarTabla(){
        vaciarTabla(jt_asuntos);
        miLista = servicioA.traerAsuntoSinEncargado();
        String v[] = new String[1];
        
        for(int i = 0; i < miLista.size(); i++){
            v[0] = miLista.get(i).getPertenece().getNombre()+" - "+miLista.get(i).getNombreasuntoS();
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

        btn_cerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_asuntos = new javax.swing.JTable();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asuntos sin encargados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N

        btn_cerrar.setBackground(new java.awt.Color(153, 153, 153));
        btn_cerrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_cerrar.setForeground(new java.awt.Color(0, 108, 118));
        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/back-arrow.png"))); // NOI18N
        btn_cerrar.setText("Volver");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        jt_asuntos.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        jt_asuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Asunto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_asuntos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jt_asuntos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cerrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        mainFrame.setVisible(false);
        mainFrame.dispose();
        mainFrame = null;
        this.setVisible(false);
        estePanel = null;
        System.gc();
        //mainFrame.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_asuntos;
    // End of variables declaration//GEN-END:variables
}
