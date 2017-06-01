/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.encargadoAsuntos;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.negocio.AsuntoPrincipalServ;
import mscb.tick.negocio.AsuntoSecundarioServ;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Usuarios;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class EncargadoAsuntos extends MenuP {

    Main mainFrame;
    private static EncargadoAsuntos estePanel;
    private List<Usuarios> miListaU;
    private Usuarios user;
    private UsuarioServ serviciosU;
    private List<Servicios> miListaA;
    private AsuntoSecundarioServ serviciosA;
    private List<Asuntos> miListaP;
    private AsuntoPrincipalServ serviciosP;
    private DefaultTableModel modelo;

    /**
     * Creates new form EncargadoAsuntos
     */
    private EncargadoAsuntos(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        modelo = (DefaultTableModel) jt_asuntos.getModel();
        cmbx_asuntosP.setVisible(false);
        cmbx_asuntosS.setVisible(false);
        lbl_asuntos.setVisible(false);
        lbl_servicios.setVisible(false);
        btn_agregar.setVisible(false);
        miListaA = new ArrayList<>();
        this.setVisible(true);
        cargarComboBoxUsuarios();
        cargarComboBoxAsuntoP();

    }

    public static EncargadoAsuntos getEncargadoAsuntos(Main mainFrame) {
        if (estePanel == null) {
            estePanel = new EncargadoAsuntos(mainFrame);
        }
        return estePanel;
    }

    private void cargarComboBoxUsuarios() {
        serviciosU = UsuarioServ.getUsuarioServ();
        miListaU = new ArrayList<>();
        miListaU = serviciosU.traerSistemas();

        for (int i = 0; i < miListaU.size(); i++) {
            cmbx_usuarios.addItem(miListaU.get(i));
        }
    }

    private void cargarComboBoxAsuntoS() {
        serviciosA = AsuntoSecundarioServ.getAsuntoPrincipalServ();
        if(!cmbx_asuntosP.getSelectedItem().equals(" ")){
            miListaA = serviciosA.traerPorAreaPrincipalyUsuario((Asuntos) cmbx_asuntosP.getSelectedItem(), (Usuarios) cmbx_usuarios.getSelectedItem());
            cmbx_asuntosS.removeAllItems();
            for (int i = 0; i < miListaA.size(); i++) {
            cmbx_asuntosS.addItem(miListaA.get(i));
            }
        }
        
    }

    private void cargarComboBoxAsuntoP() {
        serviciosP = AsuntoPrincipalServ.getAsuntoPrincipalServ();
        miListaP = new ArrayList<>();
        miListaP = serviciosP.traerTodos();
        for (int i = 0; i < miListaP.size(); i++) {
            cmbx_asuntosP.addItem(miListaP.get(i));
        }
    }

    private void cargarTabla() {
        vaciarTabla(jt_asuntos);
        
        String v[] = new String[2];
        List<Servicios> aux = new ArrayList<>();
        
        int a = miListaU.get(miListaU.indexOf(cmbx_usuarios.getSelectedItem())).getServiciosList().size();
        //List<Servicios> miLi = (List<Servicios>) miListaU.get(miListaU.indexOf(cmbx_usuarios.getSelectedItem())).getServiciosList();
        List<Servicios> miLi = user.getServiciosList();
        
        for (int i = 0; i < a; i++) {
            v[0] = miLi.get(i).getPertenece().getNombre();
            v[1] = miLi.get(i).getNombreasuntoS();
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
    
    private void quitarAsunto(){
        if((jt_asuntos.getSelectedRow() != -1)&&(jt_asuntos.getSelectedRowCount() == 1)){
            if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea quitar este asunto?") == 0){
                serviciosU = UsuarioServ.getUsuarioServ();
                serviciosA = AsuntoSecundarioServ.getAsuntoPrincipalServ();
                serviciosP = AsuntoPrincipalServ.getAsuntoPrincipalServ();
                
                Usuarios miUsuario = (Usuarios) cmbx_usuarios.getSelectedItem();
                
                List <Servicios> asuntos = serviciosA.traerTodos();
                List <Asuntos> asuntoP = serviciosP.traerTodos();
               Servicios miAsu = new Servicios();
                Asuntos miAsup = new Asuntos();
                
                for(int i = 0; i < asuntoP.size(); i ++ ){
                    if(asuntoP.get(i).getNombre().equals(modelo.getValueAt(jt_asuntos.getSelectedRow(), 0))){
                        miAsup = asuntoP.get(i);
                    }
                }
                
                for(int i = 0; i < asuntos.size(); i++){
                    if((asuntos.get(i).getNombreasuntoS().equals(modelo.getValueAt(jt_asuntos.getSelectedRow(), 1).toString()))
                        &&(asuntos.get(i).getPertenece().equals(miAsup))){
                        miAsu = asuntos.get(i);
                    }
                }
                miUsuario.getServiciosList().remove(miAsu);
                serviciosU = UsuarioServ.getUsuarioServ();
                if (serviciosU.modificarUsuario(miUsuario)) {
                    JOptionPane.showMessageDialog(mainFrame, "Asunto quitado!");
                    cargarComboBoxAsuntoS();
                    cargarTabla();
                    if(mainFrame.asuntoSinEnc != null){
                        AsuntoSinEncargadosP tablEnc = AsuntoSinEncargadosP.getAsuntoSinEncargadosP(mainFrame.asuntoSinEnc);
                        tablEnc.llenarTabla();
                    }
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Error al quitar asunto!");
                }
            }
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

        cmbx_usuarios = new javax.swing.JComboBox();
        cmbx_asuntosP = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_asuntos = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        btn_quitar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_asuntos = new javax.swing.JLabel();
        cmbx_asuntosS = new javax.swing.JComboBox();
        btn_agregar = new javax.swing.JButton();
        lbl_servicios = new javax.swing.JLabel();
        btn_quitar1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Asignar asuntos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

        cmbx_usuarios.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        cmbx_usuarios.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_usuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbx_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_usuariosActionPerformed(evt);
            }
        });

        cmbx_asuntosP.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        cmbx_asuntosP.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_asuntosP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbx_asuntosP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntosPActionPerformed(evt);
            }
        });

        jt_asuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Asuntos", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_asuntos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jt_asuntosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jt_asuntos);

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

        btn_quitar.setBackground(new java.awt.Color(153, 153, 153));
        btn_quitar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_quitar.setForeground(new java.awt.Color(0, 108, 118));
        btn_quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/delete.png"))); // NOI18N
        btn_quitar.setText("Eliminar");
        btn_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Elegir un usuario:");

        lbl_asuntos.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lbl_asuntos.setForeground(new java.awt.Color(0, 108, 118));
        lbl_asuntos.setText("Asuntos:");

        cmbx_asuntosS.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        cmbx_asuntosS.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_asuntosS.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        cmbx_asuntosS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntosSActionPerformed(evt);
            }
        });

        btn_agregar.setBackground(new java.awt.Color(153, 153, 153));
        btn_agregar.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_agregar.setForeground(new java.awt.Color(0, 108, 118));
        btn_agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/add.png"))); // NOI18N
        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_servicios.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lbl_servicios.setForeground(new java.awt.Color(0, 108, 118));
        lbl_servicios.setText("Servicios:");

        btn_quitar1.setBackground(new java.awt.Color(153, 153, 153));
        btn_quitar1.setFont(new java.awt.Font("SansSerif", 1, 10)); // NOI18N
        btn_quitar1.setForeground(new java.awt.Color(0, 108, 118));
        btn_quitar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/ver.png"))); // NOI18N
        btn_quitar1.setText("Asuntos sin encargados");
        btn_quitar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 819, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                            .addComponent(cmbx_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_asuntos, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125)
                                .addComponent(lbl_servicios, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbx_asuntosP, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbx_asuntosS, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_quitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_quitar1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl_asuntos)
                    .addComponent(lbl_servicios))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbx_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbx_asuntosP, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbx_asuntosS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_quitar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cmbx_asuntosP, cmbx_asuntosS, cmbx_usuarios});

    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void cmbx_asuntosPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntosPActionPerformed
        // TODO add your handling code here:
        cmbx_asuntosS.setVisible(true);
        lbl_servicios.setVisible(true);
        cargarComboBoxAsuntoS();
    }//GEN-LAST:event_cmbx_asuntosPActionPerformed

    private void cmbx_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_usuariosActionPerformed
        // TODO add your handling code here:
        cmbx_asuntosP.setVisible(true);
        lbl_asuntos.setVisible(true);
        user = (Usuarios) cmbx_usuarios.getSelectedItem();
        cargarTabla();
        if(cmbx_asuntosS.isVisible()){
            cargarComboBoxAsuntoS();
        }
    }//GEN-LAST:event_cmbx_usuariosActionPerformed

    private void cmbx_asuntosSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntosSActionPerformed
        // TODO add your handling code here:
        btn_agregar.setVisible(true);
    }//GEN-LAST:event_cmbx_asuntosSActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:
        if(cmbx_asuntosS.getSelectedItem() == null){
            JOptionPane.showMessageDialog(this, "No quedan servicios", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            if (JOptionPane.showConfirmDialog(mainFrame, "Seguro desea agregar este asunto?", "Seguro", JOptionPane.YES_NO_OPTION) == 0) {
            //Usuarios miUsuario = (Usuarios) cmbx_usuarios.getSelectedItem();
            user.getServiciosList().add((Servicios) cmbx_asuntosS.getSelectedItem());
            serviciosU = UsuarioServ.getUsuarioServ();
            if (serviciosU.modificarUsuario(user)) {
                JOptionPane.showMessageDialog(mainFrame, "Asunto agregado!");
                cargarTabla();
                LoginEJB.refrescarPermisos();
                if(mainFrame.asuntoSinEnc != null){
                    AsuntoSinEncargadosP tablEnc = AsuntoSinEncargadosP.getAsuntoSinEncargadosP(mainFrame.asuntoSinEnc);
                    tablEnc.llenarTabla();
                }
                cargarComboBoxAsuntoS();
                if(user.equals(LoginEJB.usuario)){
                    LoginEJB.usuario.getServiciosList().add((Servicios) cmbx_asuntosS.getSelectedItem());
                }
            } else {
                JOptionPane.showMessageDialog(mainFrame, "Error al agregar asunto!");
            }
        }
        }
        
    }//GEN-LAST:event_btn_agregarActionPerformed

    private void btn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarActionPerformed
        // TODO add your handling code here:
        quitarAsunto();
    }//GEN-LAST:event_btn_quitarActionPerformed

    private void btn_quitar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitar1ActionPerformed
        // TODO add your handling code here:
        mainFrame.asuntosSinEncargar();
    }//GEN-LAST:event_btn_quitar1ActionPerformed

    private void jt_asuntosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_asuntosKeyPressed
        // TODO add your handling code here:
        if(evt.getSource() == jt_asuntos){
            if(evt.getKeyCode() == KeyEvent.VK_DELETE){
                quitarAsunto();
            }
        }
    }//GEN-LAST:event_jt_asuntosKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JButton btn_quitar1;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_asuntosP;
    private javax.swing.JComboBox cmbx_asuntosS;
    private javax.swing.JComboBox cmbx_usuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_asuntos;
    private javax.swing.JLabel lbl_asuntos;
    private javax.swing.JLabel lbl_servicios;
    // End of variables declaration//GEN-END:variables
}
