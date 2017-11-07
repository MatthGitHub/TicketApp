/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.TicketsAdjuntosServ;
import mscb.tick.negocio.controladores.exceptions.NonexistentEntityException;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.TicketsAdjuntos;
import mscb.tick.negocio.entidades.TicketsAdjuntosPK;
import mscb.tick.util.MenuP;
import org.jfree.chart.axis.Tick;

/**
 *
 * @author Administrador
 */
public class AdjuntosD extends javax.swing.JDialog {
    private Tickets miTick;
    private String anio;
    private String mes;
    private JFileChooser fc;
    private File archivoElegido;
    
    
    /**
     * Creates new form AdjuntosD
     */
    public AdjuntosD(java.awt.Frame parent, boolean modal,Tickets miTick) {
        super(parent, modal);
        initComponents();
        Calendar c = Calendar.getInstance();
        anio = Integer.toString(c.get(c.YEAR));
        mes = Integer.toString(c.get(c.MONTH)+1);
        fc = new JFileChooser();
        //Creamos el filtro
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Textos", "txt","pdf","doc","docx","odt","ods","xls");
        //Le indicamos el filtro
        fc.setFileFilter(filtro);
        setearValores(miTick);
        setVisible(true);
    }

    private AdjuntosD(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public void setearValores(Tickets miTick){
        this.miTick = miTick;
        llenarTabla(miTick);
        lbl_ticket.setText(miTick.getIdTicket().toString());
    }
    
    private void llenarTabla(Tickets miTick){
        vaciarTabla(jt_adjuntos);
        String[] v = new String[1];
        DefaultTableModel modelo = (DefaultTableModel) jt_adjuntos.getModel();
        
        
        List<TicketsAdjuntos> miLista = miTick.getTicketsAdjuntosList();
        
        for(int i = 0; i < miLista.size(); i++){
            v[0] = miLista.get(i).getTicketsAdjuntosPK().getAdjunto();
            modelo.addRow(v);
        }
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
    
    public void abrirArchivo(String archivo){
        if (Desktop.isDesktopSupported()) {
           try {
               Desktop dk = Desktop.getDesktop();
               dk.browse(new URI(archivo));
           } catch (IOException | URISyntaxException e1) {
                try {
                    Desktop dk = Desktop.getDesktop();
                    dk.browse(new URI(archivo));
                } catch (IOException | URISyntaxException e2) {
                    JOptionPane.showMessageDialog(null, "ERROR: " + e2.getMessage());
                }
            }
        }
    }
    
    public void copyFile(File source,String nombre,String carpeta){

        Path sourceP = Paths.get(source.getAbsolutePath());
        SmbFile newFile = null;
        SmbFile newCarpetaTicket = null;
        SmbFile newCarpetaAnio = null;
        SmbFile newCarpetaMes = null;
        nombre = "smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio+"/"+mes+"/"+carpeta+"/"+nombre;
        
        //System.out.println(nombre);

        try {
            newCarpetaTicket = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio+"/"+mes+"/"+carpeta, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            newCarpetaAnio = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            newCarpetaMes = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio+"/"+mes, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            
            if(newCarpetaAnio.exists()){
                if(newCarpetaMes.exists()){
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }
                }else{
                    newCarpetaMes.mkdirs();
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }
                }
            }else{
                newCarpetaAnio.mkdirs();
                if(newCarpetaMes.exists()){
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }
                }else{
                    newCarpetaMes.mkdirs();
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                    }
                }
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(NuevoTicket.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("new SMBFile: "+ex);
        } catch (SmbException ex) {
            Logger.getLogger(NuevoTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (OutputStream out = newFile.getOutputStream())
        {
            Files.copy(sourceP, out);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Acceso denegado: "+e);
            System.out.println(e);
        }
       
    }
    
    public void borrarArchivo(TicketsAdjuntos ta){
        SmbFile newFile = null;
        SmbFile newCarpeta = null;
        
        String adjunto = "smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+ta.getAnio()+"/"+ta.getMes()+"/"+ta.getTickets().getIdTicket().toString()+"/"+ta.getTicketsAdjuntosPK().getAdjunto();
        String carpeta = "smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+ta.getAnio()+"/"+ta.getMes()+"/"+ta.getTickets().getIdTicket().toString()+"/";
        
        try { 
            newFile = new SmbFile(adjunto, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            newCarpeta =  new SmbFile(carpeta, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            String[] array= newCarpeta.list();
            
            if(array.length < 2){
                newFile.delete();
                newCarpeta.delete();
            }else{
                newFile.delete();
            }
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(AdjuntosD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SmbException ex) {
            Logger.getLogger(AdjuntosD.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new MenuP();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_adjuntos = new org.jdesktop.swingx.JXTable();
        jLabel1 = new javax.swing.JLabel();
        lbl_ticket = new javax.swing.JLabel();
        btn_cerrar = new javax.swing.JButton();
        btn_cerrar1 = new javax.swing.JButton();
        btn_cerrar2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Adjuntos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jt_adjuntos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Adjuntos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jt_adjuntos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jt_adjuntosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jt_adjuntos);
        if (jt_adjuntos.getColumnModel().getColumnCount() > 0) {
            jt_adjuntos.getColumnModel().getColumn(0).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Ticket Nº:");

        lbl_ticket.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_ticket.setForeground(new java.awt.Color(0, 108, 118));

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

        btn_cerrar1.setBackground(new java.awt.Color(153, 153, 153));
        btn_cerrar1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_cerrar1.setForeground(new java.awt.Color(0, 108, 118));
        btn_cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/delete.png"))); // NOI18N
        btn_cerrar1.setText("Eliminar adjunto");
        btn_cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar1ActionPerformed(evt);
            }
        });

        btn_cerrar2.setBackground(new java.awt.Color(153, 153, 153));
        btn_cerrar2.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_cerrar2.setForeground(new java.awt.Color(0, 108, 118));
        btn_cerrar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/save.png"))); // NOI18N
        btn_cerrar2.setText("Agregar adjunto");
        btn_cerrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cerrar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn_cerrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_cerrar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cerrar1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_ticket, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_cerrar)
                    .addComponent(btn_cerrar1)
                    .addComponent(btn_cerrar2)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrarActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        dispose();
        System.gc();
    }//GEN-LAST:event_btn_cerrarActionPerformed

    private void btn_cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar1ActionPerformed
        // TODO add your handling code here:
        if((jt_adjuntos.getSelectedRow() != -1)&&(jt_adjuntos.getSelectedRowCount() == 1)){
            TicketsAdjuntos ta = new TicketsAdjuntos();
            TicketsAdjuntosPK tapk = new TicketsAdjuntosPK(miTick.getIdTicket(), (String) jt_adjuntos.getValueAt(jt_adjuntos.getSelectedRow(), 0));
            ta.setTicketsAdjuntosPK(tapk);
            ta.setTickets(miTick);
            ta.setAnio(Integer.parseInt(anio));
            ta.setMes(Integer.parseInt(mes));
            miTick.getTicketsAdjuntosList().remove(ta);
            try {
                TicketsAdjuntosServ.getTicketsAdjuntosServ().removeAdjuntoTicket(ta);
                TicketServ.getTicketServ().modificarTicket(miTick);
                borrarArchivo(ta/*ta.getTicketsAdjuntosPK().getAdjunto()*/);
                setearValores(miTick);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(AdjuntosD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_cerrar1ActionPerformed

    private void jt_adjuntosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jt_adjuntosMouseClicked
        // TODO add your handling code here:
        if(evt.getSource() == jt_adjuntos){
            if(evt.getClickCount() == 2){
                if((jt_adjuntos.getSelectedRow() != -1)&&(jt_adjuntos.getSelectedRowCount() == 1)){
                    String adj = jt_adjuntos.getValueAt(jt_adjuntos.getSelectedRow(), 0).toString();
                    TicketsAdjuntos ta = TicketsAdjuntosServ.getTicketsAdjuntosServ().getAdjuntoTicket(new TicketsAdjuntosPK(miTick.getIdTicket(),adj ));
                    String arch = "file://"+Main.getServer()+"/www/TicketWeb/archivos/"+ta.getAnio()+"/"+ta.getMes()+"/"+ta.getTickets().getIdTicket().toString()+"/"+jt_adjuntos.getValueAt(jt_adjuntos.getSelectedRow(), 0);
                    //System.out.println(arch);
                    abrirArchivo(arch);
                }else{
                    
                }
            }
        }
    }//GEN-LAST:event_jt_adjuntosMouseClicked

    private void btn_cerrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cerrar2ActionPerformed
      
        int respuesta = fc.showSaveDialog(this);
        
        //Comprobar si se ha pulsado Aceptar
        if (respuesta == JFileChooser.APPROVE_OPTION){
            //Crear un objeto File con el archivo elegido
            archivoElegido = fc.getSelectedFile();
            TicketsAdjuntosPK tapk = new TicketsAdjuntosPK(miTick.getIdTicket(), archivoElegido.getName().trim());
            TicketsAdjuntos ta = new TicketsAdjuntos();
            ta.setTicketsAdjuntosPK(tapk);
            ta.setTickets(miTick);
            miTick.getTicketsAdjuntosList().add(ta);
            try {
                TicketsAdjuntosServ.getTicketsAdjuntosServ().nuevoAdjuntoTicket(ta);
                TicketServ.getTicketServ().modificarTicket(miTick);
                copyFile(archivoElegido, archivoElegido.getName().trim(), miTick.getIdTicket().toString());
                setearValores(miTick);
            } catch (Exception ex) {
                Logger.getLogger(AdjuntosD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_cerrar2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdjuntosD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdjuntosD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdjuntosD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdjuntosD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdjuntosD dialog = new AdjuntosD(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JButton btn_cerrar1;
    private javax.swing.JButton btn_cerrar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable jt_adjuntos;
    private javax.swing.JLabel lbl_ticket;
    // End of variables declaration//GEN-END:variables
}
