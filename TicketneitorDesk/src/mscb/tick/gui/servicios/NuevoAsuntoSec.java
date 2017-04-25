/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.servicios;

import javax.swing.JOptionPane;
import mscb.tick.negocio.AsuntoPrincipalServ;
import mscb.tick.negocio.AsuntoSecundarioServ;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.gui.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class NuevoAsuntoSec extends MenuP {
    NuevoAsuntoSecD mainFrame;
    Main mainFrameO;
    private static NuevoAsuntoSec nuevoAsunto;
    private AsuntoSecundarioServ serviciosA;
    private Asuntos miAsuntoPP;
    private Servicios miAsunto;
    private AsuntoSec asuntos;
    
    /**
     * Creates new form NuevoAsunto singleton
     */
    private NuevoAsuntoSec(NuevoAsuntoSecD mainFrame,Main mainFrameO,Asuntos miAsuntoPP) {
        initComponents();
        this.mainFrameO = mainFrameO;
        this.mainFrame = mainFrame;
        this.miAsuntoPP = miAsuntoPP;
        setVisible(true);
        lbl_asuntoPP.setText(miAsuntoPP.getNombre());
        txt_nombreAsunto.requestFocus();
    }
    
    public static NuevoAsuntoSec getNuevoAsunto(NuevoAsuntoSecD mainFrame,Main mainFrameO,Asuntos miAsuntoPP){
        if(nuevoAsunto == null){
            nuevoAsunto = new NuevoAsuntoSec(mainFrame,mainFrameO,miAsuntoPP);
        }
        return nuevoAsunto;
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
        txt_nombreAsunto = new javax.swing.JTextField();
        lbl_asuntoPP = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo servicio", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Nombre del asunto:");

        txt_nombreAsunto.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_nombreAsunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreAsuntoActionPerformed(evt);
            }
        });

        lbl_asuntoPP.setFont(new java.awt.Font("Bradley Hand ITC", 1, 18)); // NOI18N
        lbl_asuntoPP.setForeground(new java.awt.Color(255, 255, 255));

        btn_salir.setBackground(new java.awt.Color(153, 153, 153));
        btn_salir.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_salir.setForeground(new java.awt.Color(0, 108, 118));
        btn_salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/close.png"))); // NOI18N
        btn_salir.setText("Cerrar");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_guardar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_nombreAsunto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_asuntoPP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_asuntoPP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(txt_nombreAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreAsuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreAsuntoActionPerformed
        // TODO add your handling code here:
        btn_guardar.requestFocus();
    }//GEN-LAST:event_txt_nombreAsuntoActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.setVisible(false);
        nuevoAsunto = null;
        mainFrame = null;
        System.gc();
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        if(!txt_nombreAsunto.getText().trim().isEmpty()){
            if(JOptionPane.showConfirmDialog(mainFrame, "Guardar?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0){
                serviciosA = new AsuntoSecundarioServ();
                miAsunto = new Servicios();
                miAsunto.setPertenece(miAsuntoPP);
                miAsunto.setNombreasuntoS(txt_nombreAsunto.getText());
                if(serviciosA.nuevoASunto(miAsunto)){
                    miAsuntoPP.getServiciosList().add(miAsunto);
                    asuntos = AsuntoSec.getAsuntoSec(mainFrameO);
                    asuntos.llenarTabla(serviciosA.traerPorAreaPrincipal(miAsuntoPP));
                    JOptionPane.showMessageDialog(mainFrame, "Asunto cargado!");
                }else{
                    JOptionPane.showMessageDialog(mainFrame, "Error al cargar asunto!");
                }
                this.setVisible(false);
                mainFrame.setVisible(false);
                nuevoAsunto = null;
                mainFrame = null;
                System.gc();
            }
        }
    }//GEN-LAST:event_btn_guardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_asuntoPP;
    private javax.swing.JTextField txt_nombreAsunto;
    // End of variables declaration//GEN-END:variables
}