/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.notas;

import javax.swing.JFileChooser;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.negocio.AreaServ;
import mscb.tick.negocio.AsuntoPrincipalServ;
import mscb.tick.negocio.AsuntoSecundarioServ;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.gui.main.Main;
import mscb.tick.negocio.TicketServ;
import mscb.tick.util.MenuP;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.io.File;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.filechooser.FileNameExtensionFilter;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import mscb.tick.gui.tickets.BandejaTickets;
import mscb.tick.negocio.EdificioServ;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.negocio.entidades.Edificios;
import mscb.tick.negocio.entidades.Usuarios;
import org.springframework.util.StringUtils;

/**
 *
 * @author Administrador
 */
public class NuevaNota extends MenuP {
    Main mainFrame;
    private static NuevaNota form;
    private AsuntoPrincipalServ serviciosPrincipal;
    private List <Asuntos> miListaAP;
    private AsuntoSecundarioServ serviciosSecundario;
    private List <Servicios> miListaAS;
    private UsuarioServ serviciosU;
    private List<Usuarios> miListaU;
    int b;
    private Tickets miTick;
    private Date fecha;
    private TicketServ serviciosT;
    private Time hora;
    private EstadoServ serviciosEst;
    private EdificioServ serviciosE;
    private AreaServ serviciosA;
    private TextAutoCompleter textAutoAcompleter;
    private TextAutoCompleter textAutoAcompleter2;
    //Crear un objeto FileChooser
    private JFileChooser fc;
    
    private File archivoElegido;
            
    /**
     * Creates new form NuevoTicket
     */
    private NuevaNota(Main mainFrame) {
        initComponents();
        //B es una bandera para que el combobox de asuntos no se cargue cuando entre, sino que cuando selecciones
        b = 0;
        this.mainFrame = mainFrame;
        setVisible(true);
        txtA_obs.setLineWrap(true);
        txtArchivo.setVisible(false);
        asteriscoArea.setVisible(false);
        jLabel1.setVisible(false);
        cmbx_areas.setVisible(false);
        cmbx_usuarios.setVisible(false);
        txt_n_nota.setText("0000");
        llenarAreas();
        llenarEdificios();
        cmbx_areas.setSelectedItem(LoginEJB.usuario.getFkEmpleado().getFkArea());
        //Instanciamos
        fc = new JFileChooser();
        //Creamos el filtro
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Textos", "txt","pdf","doc","docx");
        //Le indicamos el filtro
        fc.setFileFilter(filtro);
    }
    
    public static NuevaNota getNuevoTicket(Main mainFrame){
        if(form == null){
            form = new NuevaNota(mainFrame);
        }
        return form;
    }
    
    private void llenarEdificios(){
        serviciosE = EdificioServ.getEdificioServ();
        List<Edificios> misEdificios = serviciosE.traerTodos();
        for(int i = 0; i < misEdificios.size(); i++){
            textAutoAcompleter2.addItem(misEdificios.get(i));
        }
    }
    
    private void llenarAreas(){
        serviciosA = AreaServ.getAreaServ();
        List<Areas> misAreas = serviciosA.traerTodasconAsuntos();
        for(int i = 0; i < misAreas.size(); i++){
            cmbx_areas.addItem(misAreas.get(i));
        }
    }
    
    private void llenarUsuariosPorServicio(Servicios servicio){
        serviciosU = UsuarioServ.getUsuarioServ();
        miListaU = serviciosU.traerPorServicios(servicio);
        
        cmbx_usuarios.addItem("Sin");
        for(int i = 0 ; i < miListaU.size() ; i ++){
            cmbx_usuarios.addItem(miListaU.get(i));
        }
    }
    
    
    public static void copyFile(File source,String nombre){

        Path sourceP = Paths.get(source.getAbsolutePath());
        SmbFile newFile = null;
        try {
            newFile = new SmbFile("smb://"+Main.getServer()+"/www/TicketWeb/archivos/"+nombre, new NtlmPasswordAuthentication("", "administrador", "cavaliere"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(NuevaNota.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("new SMBFile: "+ex);
        }
        try (OutputStream out = newFile.getOutputStream())
        {
            Files.copy(sourceP, out);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Acceso denegado: "+e);
            System.out.println(e);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_obs = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btn_volver = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        cmbx_areas = new javax.swing.JComboBox();
        asteriscoArea = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        cmbx_usuarios = new javax.swing.JComboBox();
        btn_adjuntar = new javax.swing.JButton();
        txtArchivo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_area_nota = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_n_nota = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nueva nota", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Area Receptor:");

        txtA_obs.setBackground(new java.awt.Color(239, 239, 239));
        txtA_obs.setColumns(20);
        txtA_obs.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        txtA_obs.setForeground(new java.awt.Color(0, 60, 66));
        txtA_obs.setRows(5);
        txtA_obs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtA_obsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(txtA_obs);

        jLabel2.setBackground(new java.awt.Color(0, 102, 204));
        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 108, 118));
        jLabel2.setText("Observacion:");

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

        btn_guardar.setBackground(new java.awt.Color(153, 153, 153));
        btn_guardar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_guardar.setForeground(new java.awt.Color(0, 108, 118));
        btn_guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/send-button.png"))); // NOI18N
        btn_guardar.setText("Enviar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        cmbx_areas.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_areas.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_areas.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areasActionPerformed(evt);
            }
        });

        asteriscoArea.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        asteriscoArea.setForeground(new java.awt.Color(255, 255, 255));
        asteriscoArea.setText("*");

        lblusuario.setBackground(new java.awt.Color(0, 102, 204));
        lblusuario.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblusuario.setForeground(new java.awt.Color(0, 108, 118));
        lblusuario.setText("Usuario receptor:");

        cmbx_usuarios.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_usuarios.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_usuarios.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_usuarios.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sin" }));
        cmbx_usuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_usuariosActionPerformed(evt);
            }
        });

        btn_adjuntar.setBackground(new java.awt.Color(153, 153, 153));
        btn_adjuntar.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_adjuntar.setForeground(new java.awt.Color(0, 108, 118));
        btn_adjuntar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/attachment.png"))); // NOI18N
        btn_adjuntar.setText("Adjuntar");
        btn_adjuntar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adjuntarActionPerformed(evt);
            }
        });

        txtArchivo.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        txtArchivo.setForeground(new java.awt.Color(255, 255, 255));
        txtArchivo.setText("Adjunto: ");

        jLabel8.setBackground(new java.awt.Color(0, 102, 204));
        jLabel8.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 108, 118));
        jLabel8.setText("Nº de nota:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblusuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbx_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(asteriscoArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_adjuntar))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(asteriscoArea)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(cmbx_areas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblusuario)
                    .addComponent(cmbx_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_adjuntar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtA_obsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtA_obsMousePressed
        // TODO add your handling code here:
        //txtA_obs.setText("");
    }//GEN-LAST:event_txtA_obsMousePressed

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        mainFrame.menuPrincipal();
        this.setVisible(false);
        b=0;
        form = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:
        int error = 0;
        serviciosEst = EstadoServ.getEstadoServ();
        serviciosT = TicketServ.getTicketServ();
        miTick = new Tickets();
        fecha = new Date();
        miTick.setFecha(fecha);
        miTick.setCreador(LoginEJB.usuario);
        String observacion = "";
        if(!txtA_obs.getText().trim().isEmpty()){
            observacion = observacion + txtA_obs.getText()+"\n";
        }
        if(!observacion.trim().isEmpty()){
            miTick.setObservacion(observacion);
        }else{
            miTick.setObservacion(null);
        }
        //Completo numero de nota para que cumpla con el estandar
        if(txt_n_nota.getText().trim().length() < 4){
            for(int i = 0; i < 4-txt_n_nota.getText().trim().length();i++){
                txt_n_nota.setText("0"+txt_n_nota.getText().trim());
            }
        }
        if(txt_area_nota.getText().trim().isEmpty()){
            txt_area_nota.setText("--------");
        }
        miTick.setNotaEntrada(txt_n_nota.getText()+"-"+txt_area_nota.getText().trim());
        //Creo el historial de enviado
        
        if((error == 0)&&(serviciosT.nuevoTicket(miTick) == 0)){
            //JOptionPane.showMessageDialog(mainFrame, "Ticket enviado...");
            HistorialServ serviciosH = HistorialServ.getHistorialServ();
            HistorialTickets miHis = new HistorialTickets();
            Date fecha = new Date();
            miHis.setFecha(fecha);
            miHis.setHora(fecha);
            miHis.setFkTicket(miTick);
            //miHis.setFkUsuario(LoginEJB.usuario);
            miHis.setFkEstado(serviciosEst.traerEstado(1));
            if(!cmbx_usuarios.getSelectedItem().equals("Sin")){
                miHis.setFkUsuario((Usuarios) cmbx_usuarios.getSelectedItem());
            }
            //Edito el ticket y le agrego el adjunto si es que hay
            if(archivoElegido != null){
            miTick.setAdjunto(miTick.getIdTicket()+"."+StringUtils.getFilenameExtension(archivoElegido.getPath()).toLowerCase());
                if(serviciosT.modificarTicket(miTick) == 0){
                    copyFile(archivoElegido,miTick.getIdTicket()+"."+StringUtils.getFilenameExtension(archivoElegido.getPath()).toLowerCase());
                }
            }
            serviciosH.nuevo(miHis);
            BandejaTickets.getBandejaTickets(mainFrame).llenarTabla();
            mainFrame.bandejaEntrada();
            this.setVisible(false);
            b=0;
            form = null;
            System.gc();
        }else{
            JOptionPane.showMessageDialog(mainFrame,"No se envio ticket","Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void cmbx_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_usuariosActionPerformed

    private void btn_adjuntarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adjuntarActionPerformed
        // TODO add your handling code here:
        txtArchivo.setVisible(true);
        //Mostrar la ventana para abrir archivo y recoger la respuesta
        //En el parámetro del showOpenDialog se indica la ventana
        //  al que estará asociado. Con el valor this se asocia a la
        //  ventana que la abre.
        int respuesta = fc.showSaveDialog(this);
        //Comprobar si se ha pulsado Aceptar
        if (respuesta == JFileChooser.APPROVE_OPTION)
        {
            //Crear un objeto File con el archivo elegido
            archivoElegido = fc.getSelectedFile();
            //Mostrar el nombre del archivo en un campo de texto
            txtArchivo.setText(txtArchivo.getText()+archivoElegido.getName());
            //Llamo al metodo que copia el archivo al servidor
        }
    }//GEN-LAST:event_btn_adjuntarActionPerformed

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

    private void txt_n_notaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_n_notaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_n_notaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asteriscoArea;
    private javax.swing.JButton btn_adjuntar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JComboBox cmbx_usuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextArea txtA_obs;
    private javax.swing.JLabel txtArchivo;
    private javax.swing.JTextField txt_area_nota;
    private javax.swing.JTextField txt_n_nota;
    // End of variables declaration//GEN-END:variables
}
