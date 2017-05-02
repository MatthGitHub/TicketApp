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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.filechooser.FileNameExtensionFilter;
import mscb.tick.negocio.UsuarioServ;
import mscb.tick.negocio.entidades.Usuarios;

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
    private AreaServ serviciosA;
    private TextAutoCompleter textAutoAcompleter;
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
        txtArchivo.setVisible(false);
        lblAsunto.setVisible(false);
        lblServicio.setVisible(false);
        asteriscoAsunto.setVisible(false);
        asteriscoServicio.setVisible(false);
        cmbx_asuntoPrincipal.setVisible(false);
        cmbx_asuntoSecundario.setVisible(false);
        cmbx_usuarios.setVisible(false);
        textAutoAcompleter = new TextAutoCompleter(txt_areaE);
        textAutoAcompleter.setMode(0);
        textAutoAcompleter.setCaseSensitive(false);
        llenarAreas();
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
    
    private void llenarAreas(){
        serviciosA = new AreaServ();
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
        serviciosPrincipal = new AsuntoPrincipalServ();
        miListaAP = serviciosPrincipal.asuntosPorArea(area);
        
        for(int i = 0 ; i < miListaAP.size(); i++){
            cmbx_asuntoPrincipal.addItem(miListaAP.get(i));
        }
    }
    
    private void llenarUsuariosPorServicio(Servicios servicio){
        serviciosU = new UsuarioServ();
        miListaU = serviciosU.traerPorServicios(servicio);
        
        cmbx_usuarios.addItem("Sin");
        for(int i = 0 ; i < miListaU.size() ; i ++){
            cmbx_usuarios.addItem(miListaU.get(i));
        }
    }
    
    
    public static boolean copyFile(File source, File destination){
		
		try{
		// Declares new buffer to read line by line
		BufferedReader bufferR = new BufferedReader(
				new FileReader(source));
		// Declares new fileWriter to write the content of the
		// file
		FileWriter writer = new FileWriter(destination
				.getAbsoluteFile());
		// Uses bufferedWriter to allow string buffer and copy
		BufferedWriter bufferW = new BufferedWriter(writer);
		
		String linea;
			// While there are lines in the source file, it writes
			// them to the destination file
		while ((linea = bufferR.readLine()) != null) {
			bufferW.write(linea);
			bufferW.write(System.getProperty("line.separator"));
			// adds line separator
		}

		// Close buffers to liberate memory
		bufferR.close();
		bufferW.close();

		return true;
		
		}
		catch(Exception e){
			return false;
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
        txt_areaE = new javax.swing.JTextField();
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
        btn_volver1 = new javax.swing.JButton();
        txtArchivo = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nuevo pedido a sistemas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), java.awt.Color.white)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 102, 204));
        jLabel1.setFont(new java.awt.Font("SansSerif", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 108, 118));
        jLabel1.setText("Area Receptor:");

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
        jLabel5.setText("Area emisora:");

        txt_areaE.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        txt_areaE.setForeground(new java.awt.Color(0, 108, 118));
        txt_areaE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_areaEActionPerformed(evt);
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

        asteriscoAsunto.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        asteriscoAsunto.setForeground(new java.awt.Color(255, 255, 255));
        asteriscoAsunto.setText("*");

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("* (Campos obligatorios)");

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

        btn_volver1.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_volver1.setForeground(new java.awt.Color(0, 108, 118));
        btn_volver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/back-arrow.png"))); // NOI18N
        btn_volver1.setText("Adjuntar");
        btn_volver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volver1ActionPerformed(evt);
            }
        });

        txtArchivo.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        txtArchivo.setForeground(new java.awt.Color(255, 255, 255));
        txtArchivo.setText("Adjunto: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_areaE)
                            .addComponent(txt_solicita)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_patrimonio)
                                .addGap(271, 271, 271)))
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_volver1))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(btn_volver)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
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
                                        .addComponent(cmbx_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addContainerGap(21, Short.MAX_VALUE))))
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
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_solicita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_areaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_patrimonio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_volver1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
            txtA_obs.setText("");
            serviciosSecundario = new AsuntoSecundarioServ();
            miListaAS = serviciosSecundario.traerPorAreaPrincipal((Asuntos) cmbx_asuntoPrincipal.getSelectedItem());
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
        
            if(JOptionPane.showConfirmDialog(mainFrame, "Seguro desea enviar?","Confirmar",JOptionPane.YES_NO_OPTION) == 0){
                serviciosEst = new EstadoServ();
                serviciosT = new TicketServ();
                miTick = new Tickets();
                fecha = new Date();
                miTick.setServicio((Servicios) cmbx_asuntoSecundario.getSelectedItem());
                miTick.setFecha(fecha);
                miTick.setCreador(LoginEJB.usuario);
                miTick.setHora(fecha);
                if(!txt_solicita.getText().isEmpty()){
                    if(!txt_areaE.getText().isEmpty()){
                        if(!txtA_obs.getText().isEmpty()){
                            miTick.setObservacion("Solicita: "+txt_solicita.getText()+"\n Area Emisora: "+txt_areaE.getText()+"\n Observacion: "+txtA_obs.getText());
                        }else{
                             miTick.setObservacion("Solicita: "+txt_solicita.getText()+"\n Area Emisora: "+txt_areaE.getText());
                        }
                    }else{
                        if(!txtA_obs.getText().isEmpty()){
                            miTick.setObservacion("Solicita: "+txt_solicita.getText()+"\n Observacion: "+txtA_obs.getText());
                        }else{
                            miTick.setObservacion("Solicita: "+txt_solicita.getText());
                        }
                    }
                }else{
                    if(!txt_areaE.getText().isEmpty()){
                        if(textAutoAcompleter.itemExists(textAutoAcompleter.getItemSelected())){
                            if(!txtA_obs.getText().isEmpty()){
                                miTick.setObservacion("Area Emisora: "+txt_areaE.getText()+"\n Observacion: "+txtA_obs.getText());
                            }else{
                                 miTick.setObservacion("Area Emisora: "+txt_areaE.getText());
                            }
                        }else{
                            JOptionPane.showMessageDialog(mainFrame, "Error", "Error al seleccionar area emisora", JOptionPane.ERROR_MESSAGE);
                        }   
                    }else{
                        if(!txtA_obs.getText().isEmpty()){
                            miTick.setObservacion("Observacion: "+txtA_obs.getText());
                        }else{
                            miTick.setObservacion("");
                        }
                    }
                }
                miTick.setPatrimonio(txt_patrimonio.getText());
                if(serviciosT.nuevoTicket(miTick) == 0){
                    JOptionPane.showMessageDialog(mainFrame, "Ticket enviado...");
                    HistorialServ serviciosH = new HistorialServ();
                    HistorialTickets miHis = new HistorialTickets();
                    Date fecha = new Date();
                    miHis.setFecha(fecha);
                    miHis.setFkTicket(miTick);
                    //miHis.setFkUsuario(LoginEJB.usuario);
                    miHis.setFkEstado(serviciosEst.traerEstado(1));
                    if(!cmbx_usuarios.getSelectedItem().equals("Sin")){
                        miHis.setFkUsuario((Usuarios) cmbx_usuarios.getSelectedItem());
                    }
                    serviciosH.nuevo(miHis);
                    mainFrame.menuPrincipal();
                    this.setVisible(false);
                    b=0;
                    form = null;
                    System.gc();
                }else{
                    JOptionPane.showMessageDialog(mainFrame,"No se envio ticket","Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        
    }//GEN-LAST:event_btn_guardarActionPerformed

    private void cmbx_areasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_areasActionPerformed
        // TODO add your handling code here:
        b++;
        if(b != 1){
            b =0;
            cmbx_asuntoPrincipal.removeAllItems();
            llenarAsuntoPrincipal((Areas) cmbx_areas.getSelectedItem());
            cmbx_asuntoPrincipal.setSelectedIndex(0);
            cmbx_asuntoPrincipal.setVisible(true);
            lblAsunto.setVisible(true);
            asteriscoAsunto.setVisible(true);
        }
        
    }//GEN-LAST:event_cmbx_areasActionPerformed

    private void txt_areaEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_areaEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_areaEActionPerformed

    private void cmbx_usuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbx_usuariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbx_usuariosActionPerformed

    private void btn_volver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volver1ActionPerformed
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
        }
    }//GEN-LAST:event_btn_volver1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asteriscoArea;
    private javax.swing.JLabel asteriscoAsunto;
    private javax.swing.JLabel asteriscoServicio;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_volver;
    private javax.swing.JButton btn_volver1;
    private javax.swing.JComboBox cmbx_areas;
    private javax.swing.JComboBox cmbx_asuntoPrincipal;
    private javax.swing.JComboBox cmbx_asuntoSecundario;
    private javax.swing.JComboBox cmbx_usuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAsunto;
    private javax.swing.JLabel lblServicio;
    private javax.swing.JLabel lblusuario;
    private javax.swing.JTextArea txtA_obs;
    private javax.swing.JLabel txtArchivo;
    private javax.swing.JTextField txt_areaE;
    private javax.swing.JTextField txt_patrimonio;
    private javax.swing.JTextField txt_solicita;
    // End of variables declaration//GEN-END:variables
}
