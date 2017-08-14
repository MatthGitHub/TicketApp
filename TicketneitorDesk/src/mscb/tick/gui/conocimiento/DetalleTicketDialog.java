/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.conocimiento;

import java.text.DateFormat;
import java.util.Locale;
import javax.swing.JFrame;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class DetalleTicketDialog extends javax.swing.JDialog {

    /**
     * Creates new form DetalleTicketDialog
     */
    public DetalleTicketDialog(java.awt.Frame parent, boolean modal,HistorialTickets miBase) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(parent);
        setTitle("Detalle Ticket");
        setResizable(false);
        setDefaultCloseOperation(1);
        setearValoresTicket(miBase);
        txtAObs.setLineWrap(true);
        txtAReso.setLineWrap(true);
        pack();
        setVisible(true);
        
    }

    private DetalleTicketDialog(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * 
     */
    public void setearValoresTicket(HistorialTickets miBase){
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        txtNumTicket.setText(miBase.getFkTicket().getIdTicket().toString());
        txtFecha.setText(dateFormatter.format(miBase.getFkTicket().getFecha()));
        txtUsuarioE.setText(miBase.getFkTicket().getUltimoUsuario().getFkEmpleado().getNombre()+" "+miBase.getFkTicket().getUltimoUsuario().getFkEmpleado().getApellido());
        txtUsuarioR.setText(miBase.getFkUsuario().getFkEmpleado().getNombre()+" "+miBase.getFkUsuario().getFkEmpleado().getApellido());
        txtAObs.setText(miBase.getFkTicket().getObservacion());
        txtAReso.setText(miBase.getResolucion());
        txtAreaE.setText(miBase.getFkTicket().getCreador().getFkEmpleado().getFkArea().getNombreArea());
        txtAsunto.setText(miBase.getFkTicket().getServicio().getPertenece().getNombre());
        txtServicio.setText(miBase.getFkTicket().getServicio().getNombreasuntoS());
        txtPatrimonio.setText(miBase.getFkTicket().getPatrimonio());
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new MenuP();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAObs = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAReso = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNumTicket = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUsuarioE = new javax.swing.JLabel();
        txtUsuarioR = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtAreaE = new javax.swing.JLabel();
        txtAsunto = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JLabel();
        txtPatrimonio = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detalle Ticket", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Observacion:");

        txtAObs.setEditable(false);
        txtAObs.setColumns(20);
        txtAObs.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtAObs.setForeground(new java.awt.Color(0, 108, 118));
        txtAObs.setRows(5);
        jScrollPane1.setViewportView(txtAObs);

        txtAReso.setEditable(false);
        txtAReso.setColumns(20);
        txtAReso.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtAReso.setForeground(new java.awt.Color(0, 108, 118));
        txtAReso.setRows(5);
        jScrollPane3.setViewportView(txtAReso);

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 108, 118));
        jLabel3.setText("Resolucion:");

        jLabel4.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 108, 118));
        jLabel4.setText("Nº Ticket:");

        txtNumTicket.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtNumTicket.setForeground(new java.awt.Color(0, 108, 118));

        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 108, 118));
        jLabel6.setText("Fecha:");

        txtFecha.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(0, 108, 118));

        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 108, 118));
        jLabel8.setText("Emisor:");

        txtUsuarioE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtUsuarioE.setForeground(new java.awt.Color(0, 108, 118));

        txtUsuarioR.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtUsuarioR.setForeground(new java.awt.Color(0, 108, 118));

        jLabel11.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 108, 118));
        jLabel11.setText("Receptor:");

        jButton1.setBackground(new java.awt.Color(153, 153, 153));
        jButton1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 108, 118));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/close.png"))); // NOI18N
        jButton1.setText("Cerrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 108, 118));
        jLabel9.setText("Area Emisor:");

        txtAreaE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtAreaE.setForeground(new java.awt.Color(0, 108, 118));

        txtAsunto.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtAsunto.setForeground(new java.awt.Color(0, 108, 118));

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 108, 118));
        jLabel10.setText("Asunto:");

        jLabel12.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 108, 118));
        jLabel12.setText("Servicio:");

        txtServicio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtServicio.setForeground(new java.awt.Color(0, 108, 118));

        txtPatrimonio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtPatrimonio.setForeground(new java.awt.Color(0, 108, 118));

        jLabel13.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 108, 118));
        jLabel13.setText("Patrimonio:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsuarioR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(135, 135, 135)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPatrimonio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAreaE, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtNumTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUsuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11)
                        .addComponent(txtUsuarioR, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAreaE, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addComponent(jLabel13)
                    .addComponent(txtPatrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
        System.gc();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(DetalleTicketDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleTicketDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleTicketDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleTicketDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetalleTicketDialog dialog = new DetalleTicketDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea txtAObs;
    private javax.swing.JTextArea txtAReso;
    private javax.swing.JLabel txtAreaE;
    private javax.swing.JLabel txtAsunto;
    private javax.swing.JLabel txtFecha;
    private javax.swing.JLabel txtNumTicket;
    private javax.swing.JLabel txtPatrimonio;
    private javax.swing.JLabel txtServicio;
    private javax.swing.JLabel txtUsuarioE;
    private javax.swing.JLabel txtUsuarioR;
    // End of variables declaration//GEN-END:variables
}
