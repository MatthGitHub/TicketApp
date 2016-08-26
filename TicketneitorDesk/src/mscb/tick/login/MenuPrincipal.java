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
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class MenuPrincipal extends MenuP {
    private static String fondo;
    private Image background;
    private static MenuPrincipal menuP;
    Main mainFrame;
    /**
     * Creates new form MenuPrincipal
     */
       
    private MenuPrincipal(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        lblNombreUsuario.setText(LoginEJB.usuario.getNombreUsuario());
        mainFrame.btn_mini.setVisible(true);
        setSize(800, 600);
        setVisible(true);
        mainFrame.setSize(800, 600);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Ticketneitor");
    }
    
    public static MenuPrincipal getMenuPrincipal(Main mainFrame){
        if(menuP == null){
            menuP = new MenuPrincipal(mainFrame);
        }
        return menuP;
    }
    
    
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_info = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_misTickets = new javax.swing.JButton();
        btn_nuevo = new javax.swing.JButton();
        btn_admusu = new javax.swing.JButton();
        btn_asignar = new javax.swing.JButton();
        btn_admTick = new javax.swing.JButton();
        btn_conocimiento = new javax.swing.JButton();
        btn_admAsun = new javax.swing.JButton();
        btn_admAsunSec = new javax.swing.JButton();
        btn_admRazones = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_cambiarClave = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Centro de Control de Tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), java.awt.Color.white)); // NOI18N

        btn_info.setBackground(new java.awt.Color(204, 204, 204));
        btn_info.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_info.setText("?");
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 102, 204));
        jLabel2.setFont(new java.awt.Font("CG Times", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Personal");

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("CG Times", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Configuracion");

        lblNombreUsuario.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Bradley Hand ITC", 3, 24)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("CG Times", 3, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Administracion");

        btn_misTickets.setBackground(new java.awt.Color(153, 153, 153));
        btn_misTickets.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_misTickets.setText("misTickets");
        btn_misTickets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_misTicketsActionPerformed(evt);
            }
        });

        btn_nuevo.setBackground(new java.awt.Color(153, 153, 153));
        btn_nuevo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_nuevo.setText("nuevo pedido a sistemas");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_admusu.setBackground(new java.awt.Color(153, 153, 153));
        btn_admusu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_admusu.setText("administrar usuarios");
        btn_admusu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admusuActionPerformed(evt);
            }
        });

        btn_asignar.setBackground(new java.awt.Color(153, 153, 153));
        btn_asignar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_asignar.setText("asignar servicios a usuario");
        btn_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_asignarActionPerformed(evt);
            }
        });

        btn_admTick.setBackground(new java.awt.Color(153, 153, 153));
        btn_admTick.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_admTick.setText("administrar tickets");
        btn_admTick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admTickActionPerformed(evt);
            }
        });

        btn_conocimiento.setBackground(new java.awt.Color(153, 153, 153));
        btn_conocimiento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_conocimiento.setText("base de conocimiento");
        btn_conocimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_conocimientoActionPerformed(evt);
            }
        });

        btn_admAsun.setBackground(new java.awt.Color(153, 153, 153));
        btn_admAsun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_admAsun.setText("administrar asuntos");
        btn_admAsun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admAsunActionPerformed(evt);
            }
        });

        btn_admAsunSec.setBackground(new java.awt.Color(153, 153, 153));
        btn_admAsunSec.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_admAsunSec.setText("administrar servicios");
        btn_admAsunSec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admAsunSecActionPerformed(evt);
            }
        });

        btn_admRazones.setBackground(new java.awt.Color(153, 153, 153));
        btn_admRazones.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_admRazones.setText("administrar razones de transf");
        btn_admRazones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_admRazonesActionPerformed(evt);
            }
        });

        btn_salir.setBackground(new java.awt.Color(153, 153, 153));
        btn_salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_salir.setText("salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_logout.setBackground(new java.awt.Color(153, 153, 153));
        btn_logout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_logout.setText("cerrar sesion");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        btn_cambiarClave.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cambiarClave.setText("cambiar mi clave");
        btn_cambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarClaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(142, 142, 142)
                                .addComponent(btn_cambiarClave))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(387, 387, 387)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_misTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(65, 65, 65)
                                        .addComponent(btn_admusu, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(55, 55, 55))))
                            .addComponent(btn_admTick, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_asignar)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(138, 138, 138)
                                .addComponent(btn_conocimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn_admAsun, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_admAsunSec, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_admRazones, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_info, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(28, 28, 28))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btn_admAsun, btn_admAsunSec, btn_admRazones, btn_admTick, btn_admusu, btn_asignar, btn_conocimiento, btn_misTickets, btn_nuevo});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_info, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_misTickets, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_admusu, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_admAsun, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_admAsunSec, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_asignar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_admTick, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_admRazones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_conocimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cambiarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_admAsun, btn_admAsunSec, btn_admRazones, btn_admTick, btn_admusu, btn_asignar, btn_conocimiento, btn_misTickets, btn_nuevo});

    }// </editor-fold>//GEN-END:initComponents

    private void btn_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_infoActionPerformed
        // TODO add your handling code here:
        mainFrame.ventanaInfo();
    }//GEN-LAST:event_btn_infoActionPerformed

    private void btn_misTicketsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_misTicketsActionPerformed
        // TODO add your handling code here:
        mainFrame.miTickets();
        this.setVisible(false);
    }//GEN-LAST:event_btn_misTicketsActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        mainFrame.nuevoTicket();
        this.setVisible(false);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void btn_admusuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admusuActionPerformed
        // TODO add your handling code here:
        mainFrame.ventanausuarios();
        this.setVisible(false);
    }//GEN-LAST:event_btn_admusuActionPerformed

    private void btn_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_asignarActionPerformed
        // TODO add your handling code here:
        mainFrame.asignarAsuntosEncargado();
        this.setVisible(false);
    }//GEN-LAST:event_btn_asignarActionPerformed

    private void btn_admTickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admTickActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.tickets();
    }//GEN-LAST:event_btn_admTickActionPerformed

    private void btn_conocimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_conocimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_conocimientoActionPerformed

    private void btn_admAsunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admAsunActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.asuntos();
    }//GEN-LAST:event_btn_admAsunActionPerformed

    private void btn_admAsunSecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admAsunSecActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.asuntoSecundarios();
    }//GEN-LAST:event_btn_admAsunSecActionPerformed

    private void btn_admRazonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_admRazonesActionPerformed
        // TODO add your handling code here:
        mainFrame.miTickets();
        this.setVisible(false);
    }//GEN-LAST:event_btn_admRazonesActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this,"El programa se cerrará. Continuar?","Confirmar",JOptionPane.YES_NO_OPTION)==0){
            System.exit(0);
        }
    }//GEN-LAST:event_btn_salirActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this,"Seguro desea cerrar sesion?","Confirmar",JOptionPane.YES_NO_OPTION)==0){
            this.setVisible(false);
            menuP = null;
            System.gc();
            mainFrame.ventanaLogin();
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_cambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarClaveActionPerformed
        // TODO add your handling code here:
        mainFrame.cambiarClave();
    }//GEN-LAST:event_btn_cambiarClaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_admAsun;
    private javax.swing.JButton btn_admAsunSec;
    private javax.swing.JButton btn_admRazones;
    private javax.swing.JButton btn_admTick;
    private javax.swing.JButton btn_admusu;
    private javax.swing.JButton btn_asignar;
    private javax.swing.JButton btn_cambiarClave;
    private javax.swing.JButton btn_conocimiento;
    private javax.swing.JToggleButton btn_info;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_misTickets;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_salir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
