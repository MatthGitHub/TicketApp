/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.login;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class MenuPrincipal extends MenuP {
    private static MenuPrincipal menuP;
    Main mainFrame;
    
    /**
     * Creates new form MenuPrincipal
     */
       
    private MenuPrincipal(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        //cargarIconos();
        if(LoginEJB.usuario.getNombreUsuario().equals("administrador")){
            lblNombreUsuario.setText("ADMINISTRADOR");
        }else{
            lblNombreUsuario.setText(LoginEJB.usuario.getFkEmpleado().getNombre());
        }
        setVisible(true);
    }
    
    public static MenuPrincipal getMenuPrincipal(Main mainFrame){
        if(menuP == null){
            menuP = new MenuPrincipal(mainFrame);
        }
        return menuP;
    }
    
    public void setLblNombreUsuario(){
        if(LoginEJB.usuario.getNombreUsuario().equals("administrador")){
            lblNombreUsuario.setText("ADMINISTRADOR");
        }else{
            lblNombreUsuario.setText(LoginEJB.usuario.getFkEmpleado().getNombre());
        }
        
    }
    
    public Image getIconImage(String path) {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource(path));


        return retValue;
    }
    
    private void cargarIconos(){
        Image icon = getIconImage("mscb/tick/imagenes/usuarios.png");
        ImageIcon icon2 = new ImageIcon(icon);
        icon = getIconImage("mscb/tick/imagenes/carpeta.png");
        icon2 = new ImageIcon(icon);
    }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        btn_info = new javax.swing.JToggleButton();
        lblNombreUsuario = new javax.swing.JLabel();
        btn_salir = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        btn_cambiarClave = new javax.swing.JButton();
        lblNombreUsuario1 = new javax.swing.JLabel();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Centro de Control de Tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 2, 24), java.awt.Color.white)); // NOI18N
        setAutoscrolls(true);
        setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N

        btn_info.setBackground(new java.awt.Color(204, 204, 204));
        btn_info.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btn_info.setForeground(new java.awt.Color(255, 102, 102));
        btn_info.setText("?");
        btn_info.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_infoActionPerformed(evt);
            }
        });

        lblNombreUsuario.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario.setFont(new java.awt.Font("Tekton Pro", 2, 36)); // NOI18N
        lblNombreUsuario.setForeground(new java.awt.Color(0, 137, 124));
        lblNombreUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btn_salir.setBackground(new java.awt.Color(153, 153, 153));
        btn_salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_salir.setForeground(new java.awt.Color(0, 89, 97));
        btn_salir.setText("SALIR");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });

        btn_logout.setBackground(new java.awt.Color(153, 153, 153));
        btn_logout.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(0, 108, 118));
        btn_logout.setText("Cerrar Sesion");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        btn_cambiarClave.setBackground(new java.awt.Color(153, 153, 153));
        btn_cambiarClave.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cambiarClave.setForeground(new java.awt.Color(0, 108, 118));
        btn_cambiarClave.setText("Cambiar Clave");
        btn_cambiarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cambiarClaveActionPerformed(evt);
            }
        });

        lblNombreUsuario1.setBackground(new java.awt.Color(0, 102, 204));
        lblNombreUsuario1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        lblNombreUsuario1.setForeground(new java.awt.Color(0, 102, 102));
        lblNombreUsuario1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreUsuario1.setText("Bienvenido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btn_cambiarClave)
                .addContainerGap(130, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_info))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_info, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(lblNombreUsuario1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(lblNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cambiarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_infoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_infoActionPerformed
        // TODO add your handling code here:
        mainFrame.ventanaInfo();
    }//GEN-LAST:event_btn_infoActionPerformed

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
            LoginEJB.usuario = null;
            System.gc();
            mainFrame.ventanaLogin();
        }
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void btn_cambiarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cambiarClaveActionPerformed
        // TODO add your handling code here:
        mainFrame.cambiarClave();
    }//GEN-LAST:event_btn_cambiarClaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cambiarClave;
    private javax.swing.JToggleButton btn_info;
    private javax.swing.JButton btn_logout;
    private javax.swing.JButton btn_salir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblNombreUsuario1;
    // End of variables declaration//GEN-END:variables
}
