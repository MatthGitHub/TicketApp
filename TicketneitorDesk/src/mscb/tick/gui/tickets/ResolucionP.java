/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.util.Date;
import javax.swing.JOptionPane;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class ResolucionP extends MenuP {
    private ResolucionD mainFrame;
    Main mainFrameO;
    private static ResolucionP estePanel;
    private Tickets miTick;
    private TicketServ serviciosT;
    private BandejaTickets panelMisti;
    private HistorialServ servH;
    private String marcar;
    /**
     * Creates new form ResponderP
     */
    private ResolucionP(Tickets miTick, ResolucionD mainFrame,String marcar) {
        initComponents();
        panelMisti = BandejaTickets.getBandejaTickets(mainFrameO);
        this.mainFrame = mainFrame;
        this.miTick = miTick;
        this.marcar = marcar;
        jLabel6.setVisible(false);
        txtA_respuesta.setLineWrap(true);
        lbl_ticket.setText(miTick.getIdTicket().toString());
        lbl_usuarioE.setText(miTick.getCreador().getNombreUsuario());
        lbl_areaE.setText(miTick.getCreador().getFkEmpleado().getFkArea().getNombreArea());
        setSize(520, 380);
        setVisible(true);
        if(!marcar.equals("si")){
            cbxResuelto.setVisible(false);
        }
    }
    
    public static ResolucionP getResolucion(Tickets miTick, ResolucionD mainFrame,String marcar){
        if(estePanel == null ){
            estePanel = new ResolucionP(miTick, mainFrame,marcar);
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
        jLabel6 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_n_nota = new javax.swing.JTextField();
        txt_area_nota = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cbxResuelto = new javax.swing.JCheckBox();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resolucion", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

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
        btn_enviar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/save.png"))); // NOI18N
        btn_enviar.setText("Guardar");
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

        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 108, 118));
        jLabel6.setText("Tiempo de resolucion:");

        txtTiempo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txtTiempo.setForeground(new java.awt.Color(0, 108, 118));

        jLabel7.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 108, 118));
        jLabel7.setText("Resolucion:");

        jLabel8.setBackground(new java.awt.Color(0, 102, 204));
        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 108, 118));
        jLabel8.setText("Nº de nota salida:");

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

        cbxResuelto.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxResuelto.setForeground(new java.awt.Color(255, 255, 255));
        cbxResuelto.setText("Marcar como resuelto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
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
                                        .addComponent(lbl_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 82, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_cerrar)
                                .addGap(18, 18, 18)
                                .addComponent(btn_enviar)
                                .addGap(18, 18, 18)
                                .addComponent(cbxResuelto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_enviar)
                    .addComponent(btn_cerrar)
                    .addComponent(cbxResuelto))
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
        if(JOptionPane.showConfirmDialog(mainFrame,"Seguro desea enviar?","Confirmar",JOptionPane.YES_NO_OPTION) == 0){
            servH = HistorialServ.getHistorialServ();
            serviciosT = TicketServ.getTicketServ();
            
            //Completo numero de nota para que cumpla con el estandar
            if(txt_n_nota.getText().trim().length() < 4){
                for(int i = 0; i < 4-txt_n_nota.getText().trim().length();i++){
                    txt_n_nota.setText("0"+txt_n_nota.getText().trim());
                }
            }
            if(txt_area_nota.getText().trim().isEmpty()){
                txt_area_nota.setText("--------");
            }
            miTick.setNotaSalida(txt_n_nota.getText()+"-"+txt_area_nota.getText().trim());
            serviciosT.modificarTicket(miTick);
            
            EstadoServ esta = EstadoServ.getEstadoServ();
            Estados estado;
            
            if(marcar.equals("si")){
                if(cbxResuelto.isSelected()){
                    estado = esta.traerEstado(5);
                }else{
                    estado = miTick.getUltimoEstado();
                }
            }else{
                estado = esta.traerEstado(9);
            }
            
            HistorialTickets his = new HistorialTickets();
            Date fecha = new Date();
            his.setFkTicket(miTick);
            his.setFkEstado(estado);
            String reso = miTick.getResolucion();
            if((reso == null)||(reso.isEmpty())){
                if(txtA_respuesta.getText().trim().isEmpty()){
                    his.setResolucion(null);
                }else{
                    his.setResolucion("Resolucion por "+LoginEJB.usuario+" : "+txtA_respuesta.getText());
                }
                
            }else{
                his.setResolucion(reso+"\nResolucion por "+LoginEJB.usuario+" : "+txtA_respuesta.getText());
            }
            his.setFecha(fecha);
            his.setHora(fecha);
            his.setFkUsuario(LoginEJB.usuario);
            if(marcar.equals("si")){
               servH.nuevo(his);
            }else{
                his.setIdHistorial(servH.buscarUltimo(miTick).getIdHistorial());
                servH.modificar(his);
            }
            setVisible(false);
            mainFrame.setVisible(false);
            estePanel = null;
            mainFrame.dispose();
            mainFrame = null;
            System.gc();
        }
    }//GEN-LAST:event_btn_enviarActionPerformed

    private void txt_n_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_n_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_n_notaActionPerformed

    private void txt_n_notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_n_notaKeyTyped
        // TODO add your handling code here:
        if(txt_n_nota.getText().length()== 4){
            evt.consume();
        }
    }//GEN-LAST:event_txt_n_notaKeyTyped

    private void txt_area_notaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_area_notaKeyTyped
        // TODO add your handling code here:
        if (txt_area_nota.getText().length()== 8){
            txt_area_nota.setText(txt_area_nota.getText().toUpperCase());
            evt.consume();
        }else{
            txt_area_nota.setText(txt_area_nota.getText().toUpperCase());
        }
    }//GEN-LAST:event_txt_area_notaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JCheckBox cbxResuelto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_areaE;
    private javax.swing.JLabel lbl_ticket;
    private javax.swing.JLabel lbl_usuarioE;
    private javax.swing.JTextArea txtA_respuesta;
    private javax.swing.JLabel txtTiempo;
    private javax.swing.JTextField txt_area_nota;
    private javax.swing.JTextField txt_n_nota;
    // End of variables declaration//GEN-END:variables
}
