/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.tickets;

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
import mscb.tick.negocio.EdificioServ;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.negocio.entidades.Edificios;
import mscb.tick.negocio.entidades.Usuarios;
import org.springframework.util.StringUtils;

/**
 *
 * @author Administrador
 */
public class NuevoTicket extends MenuP {
    Main mainFrame;
    private static NuevoTicket form;
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
    private NuevoTicket(Main mainFrame) {
        initComponents();
        //B es una bandera para que el combobox de asuntos no se cargue cuando entre, sino que cuando selecciones
        b = 0;
        this.mainFrame = mainFrame;
        setVisible(true);
        lblDefecto.setText(LoginEJB.usuario.getFkEmpleado().getFkArea().getNombreArea());
        txt_areaSolicitante.setText(LoginEJB.usuario.getFkEmpleado().getFkArea().getNombreArea());
        txtA_obs.setLineWrap(true);
        txtArchivo.setVisible(false);
        lblAsunto.setVisible(false);
        lblServicio.setVisible(false);
        asteriscoAsunto.setVisible(false);
        asteriscoServicio.setVisible(false);
        asteriscoArea.setVisible(false);
        jLabel1.setVisible(false);
        cmbx_areas.setVisible(false);
        cmbx_asuntoPrincipal.setVisible(false);
        cmbx_asuntoSecundario.setVisible(false);
        cmbx_usuarios.setVisible(false);
        textAutoAcompleter = new TextAutoCompleter(txt_areaSolicitante);
        textAutoAcompleter.setMode(0);
        textAutoAcompleter.setCaseSensitive(false);
        textAutoAcompleter2 = new TextAutoCompleter(txt_edificio);
        textAutoAcompleter2.setMode(0);
        textAutoAcompleter2.setCaseSensitive(false);
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
    
    public static NuevoTicket getNuevoTicket(Main mainFrame){
        if(form == null){
            form = new NuevoTicket(mainFrame);
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
        
        List<Areas> misAreas2 = serviciosA.traerTodas();
        for(int i = 0; i < misAreas2.size(); i++){
            textAutoAcompleter.addItem(misAreas2.get(i));
        }
        
    }
    
    private void llenarAsuntoPrincipal(Areas area){
        serviciosPrincipal = AsuntoPrincipalServ.getAsuntoPrincipalServ();
        miListaAP = serviciosPrincipal.asuntosPorArea(area);
        
        Comparator<Asuntos> compara = Collections.reverseOrder();
        Collections.sort(miListaAP,compara);
        
        for(int i = 0 ; i < miListaAP.size(); i++){
            cmbx_asuntoPrincipal.addItem(miListaAP.get(i));
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
            Logger.getLogger(NuevoTicket.class.getName()).log(Level.SEVERE, null, ex);
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
        cmbx_asuntoPrincipal = new javax.swing.JComboBox();
        cmbx_asuntoSecundario = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtA_obs = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_volver = new javax.swing.JButton();
        btn_guardar = new javax.swing.JButton();
        cmbx_areas = new javax.swing.JComboBox();
        lblAsunto = new javax.swing.JLabel();
        lblServicio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_areaSolicitante = new javax.swing.JTextField();
        txt_patrimonio = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_solicita = new javax.swing.JTextField();
        asteriscoServicio = new javax.swing.JLabel();
        asteriscoArea = new javax.swing.JLabel();
        asteriscoAsunto = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblusuario = new javax.swing.JLabel();
        cmbx_usuarios = new javax.swing.JComboBox();
        btn_adjuntar = new javax.swing.JButton();
        txtArchivo = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_area_nota = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_n_nota = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_edificio = new javax.swing.JTextField();
        lblDefecto = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo pedido a sistemas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Area Receptor:");
        jLabel1.setEnabled(false);

        cmbx_asuntoPrincipal.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_asuntoPrincipal.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_asuntoPrincipal.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_asuntoPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoPrincipalActionPerformed(evt);
            }
        });

        cmbx_asuntoSecundario.setBackground(new java.awt.Color(153, 153, 153));
        cmbx_asuntoSecundario.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cmbx_asuntoSecundario.setForeground(new java.awt.Color(0, 108, 118));
        cmbx_asuntoSecundario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_asuntoSecundarioActionPerformed(evt);
            }
        });

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

        jLabel3.setBackground(new java.awt.Color(0, 102, 204));
        jLabel3.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 108, 118));
        jLabel3.setText("Escriba una observacion siempre que sea posible...");

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
        cmbx_areas.setEnabled(false);
        cmbx_areas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbx_areasActionPerformed(evt);
            }
        });

        lblAsunto.setBackground(new java.awt.Color(0, 102, 204));
        lblAsunto.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblAsunto.setForeground(new java.awt.Color(0, 108, 118));
        lblAsunto.setText("Asunto:");

        lblServicio.setBackground(new java.awt.Color(0, 102, 204));
        lblServicio.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        lblServicio.setForeground(new java.awt.Color(0, 108, 118));
        lblServicio.setText("Servicio:");

        jLabel5.setBackground(new java.awt.Color(0, 102, 204));
        jLabel5.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 108, 118));
        jLabel5.setText("Area solicitante:");

        txt_areaSolicitante.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_areaSolicitante.setForeground(new java.awt.Color(0, 108, 118));
        txt_areaSolicitante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_areaSolicitanteActionPerformed(evt);
            }
        });

        txt_patrimonio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_patrimonio.setForeground(new java.awt.Color(0, 108, 118));

        jLabel6.setBackground(new java.awt.Color(0, 102, 204));
        jLabel6.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 108, 118));
        jLabel6.setText("Patrimonio:");

        jLabel7.setBackground(new java.awt.Color(0, 102, 204));
        jLabel7.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 108, 118));
        jLabel7.setText("Solicita: ");

        txt_solicita.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_solicita.setForeground(new java.awt.Color(0, 108, 118));

        asteriscoServicio.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        asteriscoServicio.setForeground(new java.awt.Color(255, 255, 255));
        asteriscoServicio.setText("*");

        asteriscoArea.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        asteriscoArea.setForeground(new java.awt.Color(255, 255, 255));
        asteriscoArea.setText("*");
        asteriscoArea.setEnabled(false);

        asteriscoAsunto.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        asteriscoAsunto.setForeground(new java.awt.Color(255, 255, 255));
        asteriscoAsunto.setText("*");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" (Campos obligatorios)");

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

        jLabel9.setBackground(new java.awt.Color(0, 102, 204));
        jLabel9.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 108, 118));
        jLabel9.setText("Lugar de trabajo:");

        txt_edificio.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_edificio.setForeground(new java.awt.Color(0, 108, 118));
        txt_edificio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_edificioActionPerformed(evt);
            }
        });

        lblDefecto.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDefecto.setForeground(new java.awt.Color(204, 0, 0));

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Si area solicitante queda en blanco por defecto será:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_adjuntar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblDefecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_areaSolicitante, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addComponent(txt_patrimonio, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_solicita))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_edificio, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(asteriscoAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblAsunto))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(asteriscoArea, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(asteriscoServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblServicio))
                                    .addComponent(lblusuario))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbx_asuntoPrincipal, 0, 590, Short.MAX_VALUE)
                                    .addComponent(cmbx_areas, 0, 590, Short.MAX_VALUE)
                                    .addComponent(cmbx_asuntoSecundario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbx_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAsunto)
                            .addComponent(asteriscoAsunto)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbx_asuntoPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbx_asuntoSecundario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblServicio)
                        .addComponent(asteriscoServicio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblusuario)
                    .addComponent(cmbx_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblDefecto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_solicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9)
                                .addComponent(txt_edificio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txt_areaSolicitante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_patrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txt_area_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txt_n_nota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)))
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_adjuntar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbx_asuntoPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntoPrincipalActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            b =0;
            //txtA_obs.setText("");
            serviciosSecundario = AsuntoSecundarioServ.getAsuntoPrincipalServ();
            miListaAS = serviciosSecundario.traerPorAreaPrincipal((Asuntos) cmbx_asuntoPrincipal.getSelectedItem());
            
            Comparator<Servicios> comparador = Collections.reverseOrder();
            Collections.sort(miListaAS,comparador);
            
            cmbx_asuntoSecundario.removeAllItems();
            for(int i = 0; i < miListaAS.size();i++){
                cmbx_asuntoSecundario.addItem(miListaAS.get(i));
            }
            cmbx_asuntoSecundario.setSelectedIndex(0);
            cmbx_asuntoSecundario.setVisible(true);
            lblServicio.setVisible(true);
            asteriscoServicio.setVisible(true);
        }
       
    }//GEN-LAST:event_cmbx_asuntoPrincipalActionPerformed

    private void cmbx_asuntoSecundarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_asuntoSecundarioActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            lblusuario.setVisible(true);
            cmbx_usuarios.removeAllItems();
            cmbx_usuarios.setVisible(true);
            llenarUsuariosPorServicio((Servicios) cmbx_asuntoSecundario.getSelectedItem());
        }
        
    }//GEN-LAST:event_cmbx_asuntoSecundarioActionPerformed

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
        miTick.setServicio((Servicios) cmbx_asuntoSecundario.getSelectedItem());
        miTick.setFecha(fecha);
        miTick.setCreador(LoginEJB.usuario);
        if(!txt_areaSolicitante.getText().trim().isEmpty()){
            Areas solicitante = AreaServ.getAreaServ().getSolicitante(txt_areaSolicitante.getText());
            if(solicitante != null){
                miTick.setFkareaSolicitante(solicitante);
            }else{
                JOptionPane.showMessageDialog(this, "El area no existe en el sistema.");
                error = 1;
            }
        }else{
                miTick.setFkareaSolicitante(LoginEJB.usuario.getFkEmpleado().getFkArea());
            }
        String observacion = "";
        if(!txtA_obs.getText().trim().isEmpty()){
            observacion = observacion + txtA_obs.getText()+"\n";
        }
        if(!txt_solicita.getText().trim().isEmpty()){
            observacion = observacion + "Solicita: "+txt_solicita.getText()+"\n";
        }
        if(!observacion.trim().isEmpty()){
            miTick.setObservacion(observacion);
        }else{
            miTick.setObservacion(null);
        }

        if(txt_edificio.getText().trim().isEmpty()){

        }else{
            miTick.setFkEdificio(EdificioServ.getEdificioServ().buscarUno(txt_edificio.getText()));
        }

        miTick.setPatrimonio(txt_patrimonio.getText());
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
        b++;
        if(b != 1){
            b =0;
            cmbx_asuntoPrincipal.removeAllItems();
            llenarAsuntoPrincipal(LoginEJB.usuario.getFkEmpleado().getFkArea());
            //llenarAsuntoPrincipal((Areas) cmbx_areas.getSelectedItem());
            cmbx_asuntoPrincipal.setSelectedIndex(0);
            cmbx_asuntoPrincipal.setVisible(true);
            lblAsunto.setVisible(true);
            asteriscoAsunto.setVisible(true);
        }
        
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void txt_areaSolicitanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_areaSolicitanteActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_areaSolicitanteActionPerformed

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

    private void txt_edificioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_edificioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_edificioActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asteriscoArea;
    private javax.swing.JLabel asteriscoAsunto;
    private javax.swing.JLabel asteriscoServicio;
    private javax.swing.JButton btn_adjuntar;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JComboBox cmbx_asuntoPrincipal;
    private javax.swing.JComboBox cmbx_asuntoSecundario;
    private javax.swing.JComboBox cmbx_usuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblDefecto;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextArea txtA_obs;
    private javax.swing.JLabel txtArchivo;
    private javax.swing.JTextField txt_areaSolicitante;
    private javax.swing.JTextField txt_area_nota;
    private javax.swing.JTextField txt_edificio;
    private javax.swing.JTextField txt_n_nota;
    private javax.swing.JTextField txt_patrimonio;
    private javax.swing.JTextField txt_solicita;
    // End of variables declaration//GEN-END:variables
}
