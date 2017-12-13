/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.resoluciones;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import mscb.tick.gui.main.Main;
import mscb.tick.gui.tickets.BandejaTickets;
import mscb.tick.gui.tickets.NuevoTicket;
import mscb.tick.negocio.AreaServ;
import mscb.tick.negocio.AsuntoPrincipalServ;
import mscb.tick.negocio.AsuntoSecundarioServ;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.TicketsAdjuntosServ;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.ResolucionesProyecto;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.TicketsAdjuntos;
import mscb.tick.negocio.entidades.TicketsAdjuntosPK;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class DetalleProyecto extends javax.swing.JDialog {
    private ResolucionesProyecto miProy;
    private String texto;
    /**
     * Creates new form DetalleTicket
     */
    public DetalleProyecto(java.awt.Frame parent, boolean modal,ResolucionesProyecto miProy) {
        super(parent, modal);
        initComponents();
        //txtA_obser.setLineWrap(true);
        //txtA_obser.setWrapStyleWord(true);
        setearValores(miProy);
        llenarComboServicios();
        setVisible(true);
    }

    private DetalleProyecto(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void setearValores(ResolucionesProyecto miProy){
        setTitle("Detalle proyecto nº: "+miProy.getResolucionesProyectoPK().getNroproyecto());
        this.miProy = miProy;
        lbl_proyecto.setText(miProy.getResolucionesProyectoPK().getNroproyecto());
        //txtA_obser.setEditable(false);
        //txtA_obser.setContentType("text/html");
        txtA_obser.setWrapStyleWord(true);
        txtA_obser.setLineWrap(true);
        //la siguiente linea elimina todos los labes y lo que haya entre medio de ellos.
        texto = miProy.getTexto().replaceAll("<LABEL.*?>", "");
        texto = texto.replaceAll("</LABEL>", "");
        txtA_obser.setText("Proveedor: \n"+"Tipo proyecto: "+miProy.getIdtiporesolucion().getDescripcion()+"\n"+"Observacion: "+miProy.getObservacion());
    }
    
    private void llenarComboServicios(){
        List<Servicios> miLista = AsuntoSecundarioServ.getAsuntoPrincipalServ().traerPorAreaPrincipal(AsuntoPrincipalServ.getAsuntoPrincipalServ().getAsunto(35));
        for(int i = 0; i < miLista.size(); i++){
            cmbx_asuntoSecundario.addItem(miLista.get(i));
        }
    }
    
    
    private void escribirArchivo(SmbFileOutputStream archivo,String texto) throws DocumentException, MalformedURLException, IOException{
        //byte[] array = texto.getBytes();
        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        HTMLWorker htmlWorker = new HTMLWorker(documento); 
        htmlWorker.parse(new StringReader(texto)); 
//documento.add(new Paragraph(texto));
        documento.close();
    }
    
    public void crearArchivoReso(String texto,String ticket,String nombre){
        SmbFile newFile = null;
        
        Calendar c = Calendar.getInstance();
        String anio = Integer.toString(c.get(c.YEAR));
        String mes = Integer.toString(c.get(c.MONTH)+1);
        nombre = "smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio+"/"+mes+"/"+ticket+"/"+nombre;
        SmbFile newCarpetaTicket = null;
        SmbFile newCarpetaAnio = null;
        SmbFile newCarpetaMes = null;
        
        
        try {
            newCarpetaTicket = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio+"/"+mes+"/"+ticket, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            newCarpetaAnio = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            newCarpetaMes = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+anio+"/"+mes, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
            
            
            
            if(newCarpetaAnio.exists()){
                if(newCarpetaMes.exists()){
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }
                }else{
                    newCarpetaMes.mkdirs();
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }
                }
            }else{
                newCarpetaAnio.mkdirs();
                if(newCarpetaMes.exists()){
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }
                }else{
                    newCarpetaMes.mkdirs();
                    if(newCarpetaTicket.exists()){
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }else{
                        newCarpetaTicket.mkdirs();
                        newFile = new SmbFile(nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere")); 
                        SmbFileOutputStream sfos = new SmbFileOutputStream(newFile);
                        escribirArchivo(sfos, texto);
                    }
                }
                
            }
            
        } catch (MalformedURLException ex) {
            System.out.println("new SMBFile: "+ex);
        } catch (SmbException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel1 = new javax.swing.JLabel();
        lbl_proyecto = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_cerrar = new javax.swing.JButton();
        btn_generar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cmbx_asuntoSecundario = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtA_obser = new org.jdesktop.swingx.JXTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Proyecto Nº:");

        lbl_proyecto.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        lbl_proyecto.setForeground(new java.awt.Color(0, 108, 118));

        jLabel10.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 108, 118));
        jLabel10.setText("Mensajes:");

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

        btn_generar.setBackground(new java.awt.Color(153, 153, 153));
        btn_generar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_generar.setForeground(new java.awt.Color(0, 108, 118));
        btn_generar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/tickets(1).png"))); // NOI18N
        btn_generar.setText("Generar ticket");
        btn_generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 108, 118));
        jLabel6.setText("Servicio:");

        cmbx_asuntoSecundario.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_asuntoSecundario.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_asuntoSecundario.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_asuntoSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoSecundarioActionPerformed(evt);
            }
        });

        txtA_obser.setColumns(20);
        txtA_obser.setRows(5);
        jScrollPane2.setViewportView(txtA_obser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                    .addComponent(btn_cerrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_generar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_proyecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(227, 227, 227))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbx_asuntoSecundario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_proyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_generar)
                .addGap(4, 4, 4)
                .addComponent(btn_cerrar)
                .addContainerGap())
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

    private void btn_generarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarActionPerformed
        // TODO add your handling code here:
        Tickets miTick = TicketServ.getTicketServ().buscarPorNotaEntrada(miProy.getResolucionesProyectoPK().toString());
        int error = 0;
        if(miTick == null){
            miTick = new Tickets();
            miTick.setCreador(LoginEJB.usuario);
            miTick.setFecha(new Date());
            Areas miArea = AreaServ.getAreaServ().getSolicitantePorCodigo(miProy.getAreagenera().getCodigoOrganigrama());
            if(miArea == null){
                JOptionPane.showMessageDialog(this, "El area no esta cargada");
                error = error +1;
            }else{
                miTick.setFkareaSolicitante(miArea);
            }
            miTick.setNotaEntrada(miProy.getResolucionesProyectoPK().toString());
            miTick.setObservacion(txtA_obser.getText());
            miTick.setServicio((Servicios) cmbx_asuntoSecundario.getSelectedItem());
           // miTick.set
            if((error == 0)&&(TicketServ.getTicketServ().nuevoTicket(miTick) == 0)){
                Calendar c = Calendar.getInstance();
                
                HistorialTickets miHis = new HistorialTickets();
                Date fecha = new Date();
                TicketsAdjuntos ta;
                miHis.setFecha(fecha);
                miHis.setHora(fecha);
                miHis.setFkTicket(miTick);
                //miHis.setFkUsuario(LoginEJB.usuario);
                miHis.setFkEstado(EstadoServ.getEstadoServ().traerEstado(1));
                
                BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
                Main.getMainFrame().bandejaEntrada();
                crearArchivoReso(texto, miTick.getIdTicket().toString(), miProy.getResolucionesProyectoPK().toString()+".pdf");
                ta = new TicketsAdjuntos();
                ta.setTickets(miTick);
                ta.setAnio(c.get(c.YEAR));
                ta.setMes(c.get(c.MONTH)+1);
                ta.setTicketsAdjuntosPK(new TicketsAdjuntosPK(miTick.getIdTicket(), miProy.getResolucionesProyectoPK().toString()+".pdf"));
                 try {
                     TicketsAdjuntosServ.getTicketsAdjuntosServ().nuevoAdjuntoTicket(ta);
                 } catch (Exception ex) {
                     Logger.getLogger(NuevoTicket.class.getName()).log(Level.SEVERE, null, ex);
                 }
                miTick.getTicketsAdjuntosList().add(ta);
                TicketServ.getTicketServ().modificarTicket(miTick);
                HistorialServ.getHistorialServ().nuevo(miHis);
                btn_cerrarActionPerformed(evt);
            }
        }else{
            JOptionPane.showMessageDialog(this, "El proyecto ya tiene posee el ticket: "+miTick.getIdTicket().toString());
        }
        
        
        
    }//GEN-LAST:event_btn_generarActionPerformed

    private void cmbx_asuntoSecundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntoSecundarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_asuntoSecundarioActionPerformed

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
            java.util.logging.Logger.getLogger(DetalleProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DetalleProyecto dialog = new DetalleProyecto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_generar;
    private javax.swing.JComboBox cmbx_asuntoSecundario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_proyecto;
    private org.jdesktop.swingx.JXTextArea txtA_obser;
    // End of variables declaration//GEN-END:variables
}
