/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.login;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.usuarios.servicios.UsuarioServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class Login extends MenuP {

    Main mainFrame;
    private static Login ingreso;
    private int nombre, clave;
    private UsuarioServ buscador;
    private LoginEJB loginejb;

    /**
     * Creates new form Login
     */
    //new ColorUIResource(0,102,204)

    private Login(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        lbl_mensaje.setVisible(false);
        setSize(320, 220);
        setVisible(true);
        mainFrame.setSize(320, 220);
        mainFrame.setTitle("Login");
    }

    public static Login getLogin(Main mainFrame) {
        if (ingreso == null) {
            ingreso = new Login(mainFrame);
        }
        return ingreso;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_nombre = new javax.swing.JTextField();
        btn_salir = new javax.swing.JButton();
        pswr_clave = new javax.swing.JPasswordField();
        btn_entrar = new javax.swing.JButton();
        lbl_mensaje = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N
        setForeground(new java.awt.Color(255, 0, 0));

        txt_nombre.setBackground(new java.awt.Color(204, 204, 204));
        txt_nombre.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });

        btn_salir.setBackground(new java.awt.Color(153, 153, 153));
        btn_salir.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_salir.setText("salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        pswr_clave.setBackground(new java.awt.Color(204, 204, 204));
        pswr_clave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pswr_clave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pswr_claveActionPerformed(evt);
            }
        });

        btn_entrar.setBackground(new java.awt.Color(153, 153, 153));
        btn_entrar.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btn_entrar.setText("entrar");
        btn_entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_entrarActionPerformed(evt);
            }
        });
        btn_entrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_entrarKeyPressed(evt);
            }
        });

        lbl_mensaje.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lbl_mensaje.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_entrar)))
                .addContainerGap(58, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pswr_clave, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(pswr_clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(lbl_mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_entrar)
                    .addComponent(btn_salir)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
        if (txt_nombre.getText().trim().length() > 0) {
            pswr_clave.requestFocus();
            pswr_clave.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Seguro desea salir?", "Confirmar", JOptionPane.YES_NO_OPTION) == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_entrarActionPerformed
        // TODO add your handling code here:
        if ((txt_nombre.getText().trim().length() > 0) && (pswr_clave.getText().trim().length() > 0)) {
            loginejb = new LoginEJB();
            if (loginejb.login(txt_nombre.getText(), pswr_clave.getText())) {
                    mainFrame.setSize(800, 600);
                    mainFrame.setResizable(true);
                    mainFrame.validarPermisos(LoginEJB.usuario);
                    mainFrame.setLocationRelativeTo(null);
                    mainFrame.setTitle("Ticketneitor");
                    mainFrame.menuPrincipal();
                    ingreso.setVisible(false);
                    ingreso = null;
                    System.gc();
            }else{
                lbl_mensaje.setText(LoginEJB.mensaje);
                lbl_mensaje.setVisible(true);
            }

        } else {
            JOptionPane.showMessageDialog(mainFrame, "Completar nombre y clave!");
        }

    }//GEN-LAST:event_btn_entrarActionPerformed

    private void pswr_claveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswr_claveActionPerformed
        // TODO add your handling code here:
        if (pswr_clave.getText().trim().length() > 0) {
            btn_entrar.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese una clave!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_pswr_claveActionPerformed

    private void btn_entrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_entrarKeyPressed
        // TODO add your handling code here
    }//GEN-LAST:event_btn_entrarKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_entrar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel lbl_mensaje;
    private javax.swing.JPasswordField pswr_clave;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
