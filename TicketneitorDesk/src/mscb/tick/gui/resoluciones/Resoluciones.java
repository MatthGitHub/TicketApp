/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.resoluciones;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.ResolucioneServ;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class Resoluciones extends MenuP {
    private static Resoluciones estePanel;
    private DefaultTableModel modelo;
    private Main mainFrame;
    /**
     * Creates new form ProyectosResoluciones
     */
    private Resoluciones(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        modelo = (DefaultTableModel) jt_reso.getModel();
        //llenarTabla(ResolucioneServ.getResolucionesServ().getProyectosDeResolucionUltimoMes());
    }
    
    public static Resoluciones getResoluciones(Main mainFrame){
        if(estePanel == null){
            estePanel = new Resoluciones(mainFrame);
        }
        return estePanel;
    }
    
    public void llenarTabla(List<mscb.tick.negocio.entidades.Resoluciones> miLista) {
        vaciarTabla(jt_reso);
        //cambiarEstadoDeTicketsRecibidos(); esta funcion cambia el estado del ticket a recibido porquien abre la app
        Integer cantidad = 0;
        
        String v[] = new String[5];
        DateFormat dateFormatter;
        dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, Locale.UK);
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        
        for (int i = 0; i < miLista.size(); i++) {
            v[0] = miLista.get(i).getResolucionesPK().toString();
            v[1] = dateFormatter.format(miLista.get(i).getFechageneracion()).toString();
            v[2] = miLista.get(i).getIdtiporesolucion().getDescripcion();
            v[3] = miLista.get(i).getAreagenera().getConcepto();
            v[4] = miLista.get(i).getNroproyecto()+"-"+miLista.get(i).getAnoproyecto();
            modelo.addRow(v);
            cantidad ++;
        }
        lblCantidadProyectos.setText("Cantidad de resoluciones: "+cantidad.toString());
        revalidate();

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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jt_reso = new javax.swing.JTable();
        lblCantidadProyectos = new javax.swing.JLabel();
        txt_busca = new javax.swing.JTextField();
        jdp_desde = new org.jdesktop.swingx.JXDatePicker();
        jdp_hasta = new org.jdesktop.swingx.JXDatePicker();
        btn_volver2 = new javax.swing.JButton();
        btn_volver3 = new javax.swing.JButton();
        lblCantidadProyectos1 = new javax.swing.JLabel();
        lblCantidadProyectos2 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resoluciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jt_reso.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Resolucion", "Fecha", "Tipos", "Generado en", "Proyecto"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_reso.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_reso.getTableHeader().setReorderingAllowed(false);
        jt_reso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_resoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_reso);

        lblCantidadProyectos.setBackground(new java.awt.Color(0, 102, 204));
        lblCantidadProyectos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidadProyectos.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadProyectos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txt_busca.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txt_busca.setForeground(new java.awt.Color(0, 108, 118));
        txt_busca.setText("Nº de resolucion...");
        txt_busca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_buscaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_buscaFocusLost(evt);
            }
        });
        txt_busca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscaActionPerformed(evt);
            }
        });

        btn_volver2.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_volver2.setForeground(new java.awt.Color(0, 108, 118));
        btn_volver2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/back-arrow.png"))); // NOI18N
        btn_volver2.setText("Volver");
        btn_volver2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volver2ActionPerformed(evt);
            }
        });

        btn_volver3.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver3.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_volver3.setForeground(new java.awt.Color(0, 108, 118));
        btn_volver3.setText("Buscar");
        btn_volver3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volver3ActionPerformed(evt);
            }
        });

        lblCantidadProyectos1.setBackground(new java.awt.Color(0, 102, 204));
        lblCantidadProyectos1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidadProyectos1.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadProyectos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadProyectos1.setText("Desde");

        lblCantidadProyectos2.setBackground(new java.awt.Color(0, 102, 204));
        lblCantidadProyectos2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidadProyectos2.setForeground(new java.awt.Color(255, 255, 255));
        lblCantidadProyectos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadProyectos2.setText("Hasta");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblCantidadProyectos1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdp_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadProyectos2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jdp_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_volver3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver2)
                        .addGap(109, 109, 109)
                        .addComponent(lblCantidadProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 347, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_busca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jdp_desde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jdp_hasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_volver3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCantidadProyectos1)
                        .addComponent(lblCantidadProyectos2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_volver2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidadProyectos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volver2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volver2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        estePanel = null;
        System.gc();
        mainFrame.menuPrincipal();
    }//GEN-LAST:event_btn_volver2ActionPerformed

    private void btn_volver3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volver3ActionPerformed
        // TODO add your handling code here:
        llenarTabla(ResolucioneServ.getResolucionesServ().getResolucionesEntreFechas(jdp_desde.getDate(), jdp_hasta.getDate()));
    }//GEN-LAST:event_btn_volver3ActionPerformed

    private void txt_buscaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscaFocusGained
        // TODO add your handling code here:
        txt_busca.setText("0000");
        
    }//GEN-LAST:event_txt_buscaFocusGained

    private void txt_buscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscaActionPerformed
        // TODO add your handling code here:
        llenarTabla(ResolucioneServ.getResolucionesServ().getResolucionBuscada(txt_busca.getText()));
    }//GEN-LAST:event_txt_buscaActionPerformed

    private void txt_buscaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_buscaFocusLost
        // TODO add your handling code here:
        txt_busca.setText("Nº de resolucion...");
    }//GEN-LAST:event_txt_buscaFocusLost

    private void jt_resoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_resoMouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == jt_reso){
            if(evt.getClickCount() == 2){
                Tickets miTick = TicketServ.getTicketServ().buscarPorNotaEntrada(modelo.getValueAt(jt_reso.getSelectedRow(), 4).toString());
                if(miTick == null){
                    JOptionPane.showMessageDialog(this, "Este proyecto no posee ticket!");
                }else{
                    mainFrame.Respuestas(miTick);
                }
            }
        }
    }//GEN-LAST:event_jt_resoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_volver2;
    private javax.swing.JButton btn_volver3;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jdp_desde;
    private org.jdesktop.swingx.JXDatePicker jdp_hasta;
    private javax.swing.JTable jt_reso;
    private javax.swing.JLabel lblCantidadProyectos;
    private javax.swing.JLabel lblCantidadProyectos1;
    private javax.swing.JLabel lblCantidadProyectos2;
    private javax.swing.JTextField txt_busca;
    // End of variables declaration//GEN-END:variables
}
