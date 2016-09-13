/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.conocimiento.vista;

import javax.swing.JOptionPane;
import mscb.tick.conocimiento.servicios.ConocimientoServ;
import mscb.tick.entidades.BaseConocimiento;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.Tickets;
import mscb.tick.estados.servicios.EstadoServ;
import mscb.tick.login.Login;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.tickets.servicios.TicketServ;
import mscb.tick.tickets.vista.MisTickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class ResolucionVerP extends MenuP {
    private ResolucionVerF mainFrame;
    Main mainFrameO;
    private static ResolucionVerP estePanel;
    private Tickets miTick;
    private TicketServ serviciosT;
    private ConocimientoServ serviciosC;
    /**
     * Creates new form ResponderP
     */
    private ResolucionVerP(BaseConocimiento miBase, ResolucionVerF mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        this.miTick = miBase.getFkTicket();
        lbl_ticket.setText(miTick.getIdTicket().toString());
        lbl_usuarioE.setText(miTick.getFkUsuarioEmisor().getNombreUsuario());
        lbl_areaE.setText(miTick.getFkAreaEmisor().getNombreArea());
        txtA_resolucion.setText(miBase.getResolucion());
        setSize(520, 380);
        setVisible(true);
    }
    
    public static ResolucionVerP getVerResolucion(BaseConocimiento miBase, ResolucionVerF mainFrame){
        if(estePanel == null ){
            estePanel = new ResolucionVerP(miBase, mainFrame);
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
        jLabel1 = new javax.swing.JLabel();
        lbl_ticket = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_usuarioE = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_areaE = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_resolucion = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resolucion", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 36), java.awt.Color.white)); // NOI18N

        btn_cerrar.setBackground(new java.awt.Color(153, 153, 153));
        btn_cerrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cerrar.setText("cerrar");
        btn_cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ticket Nº:");

        lbl_ticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_ticket.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("de:");

        lbl_usuarioE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_usuarioE.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("de:");

        lbl_areaE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl_areaE.setForeground(new java.awt.Color(255, 255, 255));

        txtA_resolucion.setEditable(false);
        txtA_resolucion.setBackground(new java.awt.Color(204, 204, 204));
        txtA_resolucion.setColumns(20);
        txtA_resolucion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtA_resolucion.setRows(5);
        jScrollPane1.setViewportView(txtA_resolucion);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btn_cerrar)
                .addGap(31, 393, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_usuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 169, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lbl_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbl_usuarioE, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_cerrar)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        mainFrame.setVisible(false);
        estePanel = null;
        mainFrame.dispose();
        mainFrame = null;
        System.gc();
    }//GEN-LAST:event_btn_cerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_areaE;
    private javax.swing.JLabel lbl_ticket;
    private javax.swing.JLabel lbl_usuarioE;
    private javax.swing.JTextArea txtA_resolucion;
    // End of variables declaration//GEN-END:variables
}
