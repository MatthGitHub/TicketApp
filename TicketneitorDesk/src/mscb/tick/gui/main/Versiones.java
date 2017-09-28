/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.main;

import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class Versiones extends MenuP {
    Main mainFrameO;
    InfoD mainFrame;
    private static Versiones estePanel;
    
    /**
     * Creates new form InfoP
     */
    private Versiones(InfoD mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        setSize(485, 320);
        setVisible(true);
    }
    
    public static Versiones getVersiones(InfoD mainFrame){
        if(estePanel == null){
            estePanel = new Versiones(mainFrame);
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

        btn_gud = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)), "Acerca de ...", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 2, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        btn_gud.setBackground(new java.awt.Color(153, 153, 153));
        btn_gud.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btn_gud.setForeground(new java.awt.Color(0, 108, 118));
        btn_gud.setText("Cerrar");
        btn_gud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gudActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Versiones...");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("A partir de version 5...\n**Version 5.1.0:  ************************************\n- Se actualiza la lista de asuntos agregados sin\ntener que cerrar la aplicacion.\n- Orden descendente de Administracion de Tickets y\nTickets resueltos.\n- Single selection de las filas en las tablas.\n- Cambio de nombre de Mis Tickets a BandejaEntrada.\n- Se agrega ventana para BandejaSalida para tickets\nenviados.\n- Se quita la auto recepcion de tickets cuando inicia\nla aplicacion.\n- Orden filtro por area de usuario los tickets \nresuletos.\n**Version 5.3.0:   ***********************************\n- Se cambia la vista del Login y se le agregan iconos.\n- Se agregan iconos al Menu Principal.\n- Se agrega boton de ayuda en Menu Principal.\n- Se agrega tab con ayudas segun tipo.\n- Se agregan manuales de ayuda en PDF.\n- Se agrega Ayuda a la barra de menu y acceso rapido con F11.\n**Version 5.3.1:   ***********************************\n- Se agrega ventana Mis servicios para consulta de los servicios del \n  usuario logueado.\n- Se auto selecciona el area del usuario en Nuevo ticket.\n- Se arregla continuidad de resolucion luego de cambiar el estado del\n  ticket desde administrador de deuda.\n- Si se carga una resolucion por transferencia, el ticket no se marca\n  como resuelto.\n- Se arregla continuidad de resolucion luego de enviar respuesta.\n**Version 6.0.0:   *******************************************************\n- Se agrega la funcion para agregar un archivo adjunto al ticket.\n\tEste se copia de la maquina local al server.\n- Se agrega el campo \"adjunto\" en la tabla tickets de la base de datos.\n- Se agrega funcion para ver el archivo adjunto dentro de BandejaEntrada.\n**Version 6.3.2:   *******************************************************\n-Se agrega archivo de bloqueo para no abrir mas de una instancia del programa.\n-Se agrega archivo de configuracion de tabla de bandeja de entrada.\n-Ordenamiento de por default de la tabal bandeja de entradas es por fecha de moficiacion.\n-Se agrega funcion para poder ordenar por columna clickeandola.");
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_gud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_gud)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_gudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gudActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        mainFrame.setSize(230, 290);
        mainFrame.setVisible(false);
        estePanel = null;
        mainFrame.dispose();
        mainFrame = null;
        System.gc();
    }//GEN-LAST:event_btn_gudActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_gud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
