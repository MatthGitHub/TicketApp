/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.util.Date;
import javax.swing.JOptionPane;
import mscb.tick.negocio.EmpleadoServ;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.gui.login.Login;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class ResponderP extends MenuP {
    private ResponderD mainFrame;
    Main mainFrameO;
    private static ResponderP estePanel;
    private Tickets miTick;
    private TicketServ serviciosT;
    private BandejaTickets panelMisti;
    private EmpleadoServ serviciosE;
    /**
     * Creates new form ResponderP
     */
    private ResponderP(Tickets miTick, ResponderD mainFrame) {
        initComponents();
        panelMisti = BandejaTickets.getBandejaTickets(mainFrameO);
        this.mainFrame = mainFrame;
        this.miTick = miTick;
        serviciosE = new EmpleadoServ();
        lbl_ticket.setText(miTick.getIdTicket().toString());
        lbl_usuarioE.setText(miTick.getCreador().getFkEmpleado().getNombre()+" "+miTick.getCreador().getFkEmpleado().getApellido());
        lbl_areaE.setText(miTick.getCreador().getFkEmpleado().getFkArea().getNombreArea());
        setSize(520, 380);
        setVisible(true);
    }
    
    public static ResponderP getResponder(Tickets miTick, ResponderD mainFrame){
        if(estePanel == null ){
            estePanel = new ResponderP(miTick, mainFrame);
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
        btn_enviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_ticket = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_usuarioE = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_areaE = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_respuesta = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responder", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

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

        btn_enviar.setBackground(new java.awt.Color(153, 153, 153));
        btn_enviar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_enviar.setForeground(new java.awt.Color(0, 108, 118));
        btn_enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/send-button.png"))); // NOI18N
        btn_enviar.setText("Enviar");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Ticket Nº:");

        lbl_ticket.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_ticket.setForeground(new java.awt.Color(0, 108, 118));

        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 108, 118));
        jLabel3.setText("de:");

        lbl_usuarioE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_usuarioE.setForeground(new java.awt.Color(0, 108, 118));

        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 108, 118));
        jLabel5.setText("de:");

        lbl_areaE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_areaE.setForeground(new java.awt.Color(0, 108, 118));

        txtA_respuesta.setColumns(20);
        txtA_respuesta.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtA_respuesta.setForeground(new java.awt.Color(0, 108, 118));
        txtA_respuesta.setRows(5);
        jScrollPane1.setViewportView(txtA_respuesta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_usuarioE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_enviar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_enviar)
                    .addComponent(btn_cerrar))
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

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(mainFrame,"Seguro desea enviar?","Confirmar", JOptionPane.YES_NO_OPTION) == 0){
            Estados estado = new Estados();
            serviciosT = new TicketServ();
            EstadoServ serviciosE = new EstadoServ();
            HistorialServ servH = new HistorialServ();
            Date fecha = new Date();
            estado = serviciosE.traerEstado(4);
            HistorialTickets his = new HistorialTickets();
            String obs = miTick.getObservacion();
                       
            if((obs == null)||(obs.isEmpty())){
                miTick.setObservacion(" Mensaje de "+LoginEJB.usuario.getNombreUsuario()+": "+txtA_respuesta.getText());
            }else{
                miTick.setObservacion(obs+"\n Mensaje de "+LoginEJB.usuario.getNombreUsuario()+": "+txtA_respuesta.getText());
            }
            his.setFkEstado(estado);
            his.setFkUsuario(LoginEJB.usuario);
            his.setFkTicket(miTick);
            his.setFecha(fecha);
            serviciosT.modificarTicket(miTick);
            servH.nuevo(his);
            panelMisti.llenarTabla();
            setVisible(false);
            mainFrame.setVisible(false);
            estePanel = null;
            mainFrame.dispose();
            mainFrame = null;
            System.gc();
            
        }
    }//GEN-LAST:event_btn_enviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_areaE;
    private javax.swing.JLabel lbl_ticket;
    private javax.swing.JLabel lbl_usuarioE;
    private javax.swing.JTextArea txtA_respuesta;
    // End of variables declaration//GEN-END:variables
}