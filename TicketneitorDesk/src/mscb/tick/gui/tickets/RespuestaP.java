/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.gui.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class RespuestaP extends MenuP {
    private static RespuestaP estePanel;
    private Tickets miTick;
    private Main mainFrameO;
    private RespuestaD mainFrame;
    /**
     * Creates new form RespuestaP
     */
    private RespuestaP(Tickets miTick, RespuestaD mainFrame) {
        initComponents();
        setSize(520, 300);
        setVisible(true);
        this.miTick = miTick;
        this.mainFrame = mainFrame;
        lbl_numero.setText(miTick.getIdTicket().toString());
        if(miTick.getUltimoUsuario() == null){
             lbl_usuarioE.setText("No tiene aun");
             lbl_areaE.setText("--------------------");
        }else{
            lbl_usuarioE.setText(miTick.getUltimoUsuario().getNombreUsuario());
            lbl_areaE.setText(miTick.getUltimoUsuario().getFkEmpleado().getFkArea().getNombreArea());
        }
        
        
        txtA_obser.setText(miTick.getResolucion());
        txtA_obser.setEditable(false);
    }
    
    public static RespuestaP getRespuestaP(Tickets miTick,RespuestaD mainFrame){
        if(estePanel == null){
            estePanel = new RespuestaP(miTick,mainFrame);
        }
        return estePanel;
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
        txtA_obser = new javax.swing.JTextArea();
        lbl_areaE = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_numero = new javax.swing.JLabel();
        lbl_usuarioE = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Respuesta", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

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

        txtA_obser.setColumns(20);
        txtA_obser.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtA_obser.setRows(5);
        jScrollPane1.setViewportView(txtA_obser);

        lbl_areaE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_areaE.setForeground(new java.awt.Color(0, 108, 118));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 108, 118));
        jLabel3.setText("de:");

        lbl_numero.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_numero.setForeground(new java.awt.Color(0, 108, 118));

        lbl_usuarioE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_usuarioE.setForeground(new java.awt.Color(0, 108, 118));

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("de:");

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 108, 118));
        jLabel2.setText("Ticket Nº:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_usuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 137, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_cerrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(lbl_usuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(lbl_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_cerrar)
                .addContainerGap(38, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        mainFrame.dispose();
        this.setVisible(false);
        mainFrame.setVisible(false);
        estePanel = null;
        mainFrame = null;
        System.gc();
        //mainFrame.dispose();
    }//GEN-LAST:event_btn_cerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_areaE;
    private javax.swing.JLabel lbl_numero;
    private javax.swing.JLabel lbl_usuarioE;
    private javax.swing.JTextArea txtA_obser;
    // End of variables declaration//GEN-END:variables
}
