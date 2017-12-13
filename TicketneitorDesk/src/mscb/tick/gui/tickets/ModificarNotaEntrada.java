/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class ModificarNotaEntrada extends javax.swing.JDialog {
    Main mainFrame;
    private Tickets miTick;
    private TicketServ serviciosT;
    /**
     * Creates new form ModificarPatrimonio
     */
    public ModificarNotaEntrada(java.awt.Frame parent, boolean modal,Tickets miTick) {
        super(parent, modal);
        initComponents();
        mainFrame = (Main) parent;
        setTitle("Cambiar numero nota entrada ticket");
        setLocationRelativeTo(mainFrame);
        setSize(230, 300);
        //setResizable(false);
        setDefaultCloseOperation(0);
        setearValores(miTick);
        pack();
        setVisible(true);
    }

    private ModificarNotaEntrada(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setearValores(Tickets miTick){
        this.miTick = miTick;
        lbl_ticket.setText(miTick.getIdTicket().toString());
        lbl_patrimonio.setText(miTick.getNotaEntrada());
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
        lbl_ticket = new javax.swing.JLabel();
        lbl_patrimonio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_guardar = new javax.swing.JButton();
        btn_cerrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txt_n_nota = new javax.swing.JTextField();
        txt_area_nota = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nota Entrada", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(0, 108, 118))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Nº ticket:");

        lbl_ticket.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_ticket.setForeground(new java.awt.Color(0, 108, 118));
        lbl_ticket.setText("######");

        lbl_patrimonio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_patrimonio.setForeground(new java.awt.Color(0, 108, 118));
        lbl_patrimonio.setText("######");

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 108, 118));
        jLabel3.setText("Nota salida");

        btn_guardar.setBackground(new java.awt.Color(153, 153, 153));
        btn_guardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(0, 108, 118));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/save.png"))); // NOI18N
        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        btn_cerrar.setBackground(new java.awt.Color(153, 153, 153));
        btn_cerrar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_cerrar.setForeground(new java.awt.Color(0, 108, 118));
        btn_cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/close.png"))); // NOI18N
        btn_cerrar.setText("Cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(0, 102, 204));
        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 108, 118));
        jLabel8.setText("Nº de nota entrada:");

        txt_n_nota.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_n_nota.setForeground(new java.awt.Color(0, 108, 118));
        txt_n_nota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_n_notaActionPerformed(evt);
            }
        });
        txt_n_nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_n_notaKeyTyped(evt);
            }
        });

        txt_area_nota.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_area_nota.setForeground(new java.awt.Color(0, 108, 118));
        txt_area_nota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_area_notaKeyTyped(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(0, 102, 204));
        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 108, 118));
        jLabel10.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ticket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_patrimonio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_cerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_guardar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(109, 109, 109))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_patrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
       //Completo numero de nota para que cumpla con el estandar
            /*if(txt_n_nota.getText().trim().length() < 4){
                for(int i = 0; i < 4-txt_n_nota.getText().trim().length();i++){
                    txt_n_nota.setText("0"+txt_n_nota.getText().trim());
                }
            }*/
            /*if(txt_area_nota.getText().trim().isEmpty()){
                txt_area_nota.setText("--------");
            }*/
            if((txt_n_nota.getText().trim().isEmpty())&&(txt_n_nota.getText().trim().isEmpty())){
               JOptionPane.showMessageDialog(this, "Debe escribir un numero de nota!");
            }else{ 
                miTick.setNotaEntrada(txt_n_nota.getText()+"-"+txt_area_nota.getText().trim());
                TicketServ.getTicketServ().modificarTicket(miTick);
                BandejaTickets tickete = BandejaTickets.getBandejaTickets(mainFrame);
                tickete.llenarTabla();
                setVisible(false);
                dispose();
                System.gc();
            }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
        System.gc();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void txt_n_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_n_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_n_notaActionPerformed

    private void txt_n_notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_n_notaKeyTyped
        // TODO add your handling code here:
        /*if(txt_n_nota.getText().length()== 4){
            evt.consume();
        }*/
    }//GEN-LAST:event_txt_n_notaKeyTyped

    private void txt_area_notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_area_notaKeyTyped
        // TODO add your handling code here:
        /*if (txt_area_nota.getText().length()== 8){
            txt_area_nota.setText(txt_area_nota.getText().toUpperCase());
            evt.consume();
        }else{
            txt_area_nota.setText(txt_area_nota.getText().toUpperCase());
        }*/
    }//GEN-LAST:event_txt_area_notaKeyTyped

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
            java.util.logging.Logger.getLogger(ModificarNotaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModificarNotaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModificarNotaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModificarNotaEntrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ModificarNotaEntrada dialog = new ModificarNotaEntrada(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_patrimonio;
    private javax.swing.JLabel lbl_ticket;
    private javax.swing.JTextField txt_area_nota;
    private javax.swing.JTextField txt_n_nota;
    // End of variables declaration//GEN-END:variables
}
