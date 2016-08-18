/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.asuntoSecundarios.vista;

import mscb.tick.asuntos.vista.*;
import java.awt.MouseInfo;
import java.awt.Point;
import mscb.tick.entidades.Asuntos;
import mscb.tick.main.Main;

/**
 *
 * @author Administrador
 */
public class NuevoAsuntoSecFrame extends javax.swing.JFrame {
    Main mainFrame;
    private NuevoAsuntoSec nuevoAsunto;
    private int x;
    private int y;
    /**
     * Creates new form NuevoAsuntoFrame
     */
    public NuevoAsuntoSecFrame(Main mainFrame,Asuntos miAsuntoPP) {
        initComponents();
        this.mainFrame = mainFrame;
        setSize(260, 320);
        setTitle("Nuevo asunto");
        setLocationRelativeTo(null);
        setVisible(true);
        nuevoAsunto(miAsuntoPP);
    }

    private NuevoAsuntoSecFrame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void nuevoAsunto(Asuntos miAsuntoPP){
        nuevoAsunto = NuevoAsuntoSec.getNuevoAsunto(this,mainFrame,miAsuntoPP);
        
        if(!nuevoAsunto.isVisible() == false){
            getContentPane().add(nuevoAsunto);
        }else{
            nuevoAsunto.setVisible(true);
        }
        revalidate();
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_mover = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        btn_mover.setBackground(new java.awt.Color(0, 102, 204));
        btn_mover.setText("+");
        btn_mover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_moverMousePressed(evt);
            }
        });
        btn_mover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                btn_moverMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                btn_moverMouseMoved(evt);
            }
        });
        btn_mover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_moverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_mover)
                .addGap(0, 219, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_mover)
                .addGap(0, 297, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_moverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_moverMousePressed
        // TODO add your handling code here:
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_btn_moverMousePressed

    private void btn_moverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_moverMouseDragged
        // TODO add your handling code here:
        Point point = MouseInfo.getPointerInfo().getLocation();
        setLocation(point.x - x, point.y - y);
    }//GEN-LAST:event_btn_moverMouseDragged

    private void btn_moverMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_moverMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_moverMouseMoved

    private void btn_moverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moverActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_moverActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NuevoAsuntoSecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoAsuntoSecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoAsuntoSecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoAsuntoSecFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NuevoAsuntoSecFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_mover;
    // End of variables declaration//GEN-END:variables
}
