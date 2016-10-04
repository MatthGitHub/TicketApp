/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.conocimiento.vista;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mscb.tick.conocimiento.servicios.ConocimientoServ;
import mscb.tick.entidades.BaseConocimiento;
import mscb.tick.entidades.Tickets;
import mscb.tick.login.Login;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.main.Main;
import mscb.tick.tickets.servicios.TicketServ;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class BaseConocimientoV extends MenuP {
    Main mainFrame;
    private static BaseConocimientoV estePanel;
    private ConocimientoServ serviciosC;
    private List<BaseConocimiento> miLista;
    private DefaultTableModel modelo;
    private TicketServ serviciosT;
    
    /**
     * Creates new form BaseConocimiento
     */
    private BaseConocimientoV(Main mainFrame) {
        initComponents();
        serviciosC = new ConocimientoServ();
        modelo = (DefaultTableModel) jt_conocimiento.getModel();
        this.mainFrame = mainFrame;
        setSize(800, 600);
        setVisible(true);
        llenarTabla();
    }
    
    public static BaseConocimientoV getBaseConocimiento(Main mainFrame){
        if(estePanel == null){
            estePanel = new BaseConocimientoV(mainFrame);
        }
        return estePanel;
    }
    
    private void llenarTabla(){
        miLista = serviciosC.traerTodos();
        String v[] = new String[4];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        
        for(int i = 0; i < miLista.size();i++){
            v[0] = miLista.get(i).getFkTicket().toString();
            v[1] = miLista.get(i).getIdResolucion().toString();
            v[2] = dateFormatter.format(miLista.get(i).getFkTicket().getFecha()).toString();
            v[3] = dateFormatter.format(miLista.get(i).getFecha()).toString();
            modelo.addRow(v);
        }
        revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_conocimiento = new javax.swing.JTable();
        btn_volver = new javax.swing.JButton();
        btn_verResolucion = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Base de conocimiento", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bradley Hand ITC", 0, 24), new java.awt.Color(255, 255, 255))); // NOI18N

        jt_conocimiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº ticket", "Nº resolucion", "Fecha entrada", "Fecha resuelto"
            }
        ));
        jScrollPane1.setViewportView(jt_conocimiento);

        btn_volver.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_volver.setText("volver");
        btn_volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volverActionPerformed(evt);
            }
        });

        btn_verResolucion.setBackground(new java.awt.Color(153, 153, 153));
        btn_verResolucion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_verResolucion.setText("ver resolucion");
        btn_verResolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verResolucionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_verResolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_volver, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btn_verResolucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        if(LoginEJB.usuario.getFkPermiso().getIdPermiso() == 1){
            mainFrame.menuPrincipal();
        }else{
            mainFrame.miTickets();
        }
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_verResolucionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verResolucionActionPerformed
        // TODO add your handling code here:
        if(jt_conocimiento.getSelectedRow() != -1){
            serviciosT = new TicketServ();
            BaseConocimiento miBase = serviciosC.buscarUno(Integer.parseInt(modelo.getValueAt(jt_conocimiento.getSelectedRow(), 1).toString()));
            mainFrame.verResolucionTicket(miBase);
        }else{
            JOptionPane.showMessageDialog(this, "Error","Debe seleccionar una y solo una fila", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btn_verResolucionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_verResolucion;
    private javax.swing.JButton btn_volver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jt_conocimiento;
    // End of variables declaration//GEN-END:variables
}
