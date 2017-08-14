package mscb.tick.gui.tickets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import mscb.tick.gui.main.Main;
import mscb.tick.util.MenuP;

/**
 *
 * @author Administrador
 */
public class ConfigurarTabla extends MenuP {
    private static ConfigurarTabla estePanel;
    private List<ListaConf> miConfig;
    Main mainFrame;
    
    /**
     * Creates new form ConfgurarTabla
     */
    private ConfigurarTabla(Main mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        btGrpTamanio.add(jrPeque);
        btGrpTamanio.add(jrGrande);
        setVisible(true);
        try {
            cargarView();
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarTabla.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static ConfigurarTabla getConfigurarTabla(Main mainFrame){
        if(estePanel == null){
            estePanel = new ConfigurarTabla(mainFrame);
        }
        return estePanel;
    }
    
    /**
     * Este metodo devuelve una ArrayList con las configuraciones del archivo de configuracion
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private List<ListaConf> getListaConf() throws FileNotFoundException, IOException{
        miConfig = new ArrayList<>();
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        
        String ruta = new File("conf.tbl").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        br = new BufferedReader(new FileReader(archivo));
        String lineJustFetched = null;
        String[] wordsArray;
        String palabraUno = null;
        Boolean palabraDos = false;
        int i = 0;

        while(true){
            lineJustFetched = br.readLine();
            if(lineJustFetched == null){  
                break; 
            }else{
                wordsArray = lineJustFetched.split("\t");
                for(String each : wordsArray){
                    if(!"".equals(each)){
                        i++;
                        if(i == 1){
                            //System.out.println("If1: "+each);
                            palabraUno = each;
                        }
                        if(i == 2){
                            //System.out.println("If2: "+each);
                            palabraDos = Boolean.parseBoolean(each);
                            i = 0;
                        }
                    }
                }
            }
            miConfig.add(new ListaConf(palabraUno, palabraDos));
        }
        br.close();
        System.out.println(miConfig);
        return miConfig;
    }
    
    /**
     * Este metodo crea el archivo de configuracion
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void crearArchivoConfiguracion() throws FileNotFoundException, IOException{
        BufferedWriter bw;
        
        String ruta = new File("conf.tbl").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("RowSize\ttrue\n");
        bw.write("NumTicket\ttrue\n");
        bw.write("Fecha\ttrue\n");
        bw.write("LugarTrabajo\ttrue\n");
        bw.write("Estado\ttrue\n");
        bw.write("Hora\ttrue\n");
        bw.write("Asunto\ttrue\n");
        bw.write("Receptor\ttrue\n");
        bw.write("Patrimonio\ttrue\n");
        bw.write("Adjunto\ttrue\n");
        bw.write("NotaEntrada\ttrue\n");
        bw.write("NotaSalida\ttrue\n");
        bw.write("Observacion\ttrue\n");
        bw.write("AreaEmisora\ttrue\n");
        bw.write("Creador\ttrue");
        bw.close();
    }
    
    /**
     * Este metodo se fija si el archivo de configuracion existe, si existe devuelve los valores, si no
     * existe lo crea y devuelve los valores.
     * @return
     * @throws IOException 
     */
    public List<ListaConf> getListaDeConfiguracionTabla() throws IOException{
        
        String ruta = new File("conf.tbl").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        if(archivo.exists()) {
            return getListaConf();
        }else{
            crearArchivoConfiguracion();
            return getListaConf();
        }
    }
    
    /**
     * Carga la vista para configuracion de las columnas que muestra la bandeja de tickets
     * @throws IOException 
     */
    private void cargarView() throws IOException{
        miConfig = getListaDeConfiguracionTabla();
        cbxTicket.setSelected(getSiMuestra("NumTicket", miConfig));
        cbxFecha.setSelected(getSiMuestra("Fecha", miConfig));
        cbxLugar.setSelected(getSiMuestra("LugarTrabajo", miConfig));
        cbxEstado.setSelected(getSiMuestra("Estado", miConfig));
        cbxHora.setSelected(getSiMuestra("Hora", miConfig));
        cbxAsunto.setSelected(getSiMuestra("Asunto", miConfig));
        cbxReceptor.setSelected(getSiMuestra("Receptor", miConfig));
        cbxPatrimonio.setSelected(getSiMuestra("Patrimonio", miConfig));
        cbxAdjunto.setSelected(getSiMuestra("Adjunto", miConfig));
        cbxNotaE.setSelected(getSiMuestra("NotaEntrada", miConfig));
        cbxNotaS.setSelected(getSiMuestra("NotaSalida", miConfig));
        cbxObservacion.setSelected(getSiMuestra("Observacion", miConfig));
        cbxAreaEmisora.setSelected(getSiMuestra("AreaEmisora", miConfig));
        cbxCreador.setSelected(getSiMuestra("Creador", miConfig));
        if(getSiMuestra("RowSize", miConfig)){
            jrPeque.setSelected(true);
        }else{
            jrGrande.setSelected(true);
        }
    }
    
    /**
     * Este metodo devuelve el valor de Muestra de una columna
     * @param columna
     * @param miLista
     * @return 
     */
    public boolean getSiMuestra(String columna, List<ListaConf> miLista){
        for(int i = 0; i < miLista.size(); i++){
            if(miLista.get(i).getColumna().equals(columna)){
                return miLista.get(i).getMuestra();
            }
        }
        return false;
    }
    
    private void guardarNuevaListaConfiguracion(List<ListaConf> miLista) throws IOException{
        BufferedWriter bw;
        
        String ruta = new File("conf.tbl").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        bw = new BufferedWriter(new FileWriter(archivo));
        
        for(int i = 0; i < miLista.size(); i++){
            bw.write(miLista.get(i).getColumna()+"\t"+miLista.get(i).getMuestra().toString()+"\n");
        }
        bw.close();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGrpTamanio = new javax.swing.ButtonGroup();
        btn_volver = new javax.swing.JButton();
        btn_volver1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cbxTicket = new javax.swing.JCheckBox();
        cbxFecha = new javax.swing.JCheckBox();
        cbxLugar = new javax.swing.JCheckBox();
        cbxEstado = new javax.swing.JCheckBox();
        cbxHora = new javax.swing.JCheckBox();
        cbxAsunto = new javax.swing.JCheckBox();
        cbxReceptor = new javax.swing.JCheckBox();
        cbxPatrimonio = new javax.swing.JCheckBox();
        cbxNotaE = new javax.swing.JCheckBox();
        cbxObservacion = new javax.swing.JCheckBox();
        cbxAdjunto = new javax.swing.JCheckBox();
        cbxNotaS = new javax.swing.JCheckBox();
        cbxAreaEmisora = new javax.swing.JCheckBox();
        cbxCreador = new javax.swing.JCheckBox();
        jPanel1 = new MenuP();
        jrPeque = new javax.swing.JRadioButton();
        jrGrande = new javax.swing.JRadioButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracion de tabla de tickets", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

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

        btn_volver1.setBackground(new java.awt.Color(153, 153, 153));
        btn_volver1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btn_volver1.setForeground(new java.awt.Color(0, 108, 118));
        btn_volver1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icons/check.png"))); // NOI18N
        btn_volver1.setText("Guardar");
        btn_volver1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_volver1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("SansSerif", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 108, 118));
        jLabel2.setText("Seleccione que columnas desea visualizar en su tabla:");

        cbxTicket.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxTicket.setForeground(new java.awt.Color(0, 102, 102));
        cbxTicket.setText("Nº ticket");

        cbxFecha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxFecha.setForeground(new java.awt.Color(0, 102, 102));
        cbxFecha.setText("Fecha");

        cbxLugar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxLugar.setForeground(new java.awt.Color(0, 102, 102));
        cbxLugar.setText("Lugar de trabajo");

        cbxEstado.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxEstado.setForeground(new java.awt.Color(0, 102, 102));
        cbxEstado.setText("Estado");

        cbxHora.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxHora.setForeground(new java.awt.Color(0, 102, 102));
        cbxHora.setText("Hora");

        cbxAsunto.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxAsunto.setForeground(new java.awt.Color(0, 102, 102));
        cbxAsunto.setText("Asunto");

        cbxReceptor.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxReceptor.setForeground(new java.awt.Color(0, 102, 102));
        cbxReceptor.setText("Receptor");

        cbxPatrimonio.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxPatrimonio.setForeground(new java.awt.Color(0, 102, 102));
        cbxPatrimonio.setText("Patrimonio");

        cbxNotaE.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxNotaE.setForeground(new java.awt.Color(0, 102, 102));
        cbxNotaE.setText("Nota entrada");

        cbxObservacion.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxObservacion.setForeground(new java.awt.Color(0, 102, 102));
        cbxObservacion.setText("Observacion");

        cbxAdjunto.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxAdjunto.setForeground(new java.awt.Color(0, 102, 102));
        cbxAdjunto.setText("Adjunto");

        cbxNotaS.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxNotaS.setForeground(new java.awt.Color(0, 102, 102));
        cbxNotaS.setText("Nota salida");

        cbxAreaEmisora.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxAreaEmisora.setForeground(new java.awt.Color(0, 102, 102));
        cbxAreaEmisora.setText("Area emisora");

        cbxCreador.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        cbxCreador.setForeground(new java.awt.Color(0, 102, 102));
        cbxCreador.setText("Creador");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tamaño de las filas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 18), new java.awt.Color(255, 255, 255))); // NOI18N

        jrPeque.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jrPeque.setForeground(new java.awt.Color(0, 102, 102));
        jrPeque.setText("Pequeño");

        jrGrande.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jrGrande.setForeground(new java.awt.Color(0, 102, 102));
        jrGrande.setText("Grande");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(327, 327, 327)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jrPeque, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                    .addComponent(jrGrande, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jrPeque)
                .addGap(27, 27, 27)
                .addComponent(jrGrande)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btn_volver)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_volver1)
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxHora)
                    .addComponent(cbxTicket)
                    .addComponent(cbxAdjunto))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxFecha)
                    .addComponent(cbxAsunto)
                    .addComponent(cbxNotaE)
                    .addComponent(cbxAreaEmisora))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxLugar)
                    .addComponent(cbxReceptor)
                    .addComponent(cbxNotaS)
                    .addComponent(cbxCreador))
                .addGap(81, 81, 81)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxEstado)
                    .addComponent(cbxPatrimonio)
                    .addComponent(cbxObservacion))
                .addGap(16, 16, 16))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTicket)
                    .addComponent(cbxFecha)
                    .addComponent(cbxLugar)
                    .addComponent(cbxEstado))
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxHora)
                    .addComponent(cbxAsunto)
                    .addComponent(cbxReceptor)
                    .addComponent(cbxPatrimonio))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxAdjunto)
                    .addComponent(cbxNotaE)
                    .addComponent(cbxNotaS)
                    .addComponent(cbxObservacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxCreador)
                    .addComponent(cbxAreaEmisora))
                .addGap(23, 23, 23)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_volver, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_volver1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volverActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        mainFrame.bandejaEntrada();
        estePanel = null;
        System.gc();
    }//GEN-LAST:event_btn_volverActionPerformed

    private void btn_volver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_volver1ActionPerformed
        // TODO add your handling code here:
        List<ListaConf> miLista = new ArrayList<>();
        if(jrPeque.isSelected()){
            miLista.add(new ListaConf("RowSize",true));
        }else{
            miLista.add(new ListaConf("RowSize",false));
        }
        
        miLista.add(new ListaConf("NumTicket", cbxTicket.isSelected()));
        miLista.add(new ListaConf("Fecha", cbxFecha.isSelected()));
        miLista.add(new ListaConf("LugarTrabajo", cbxLugar.isSelected()));
        miLista.add(new ListaConf("Estado", cbxEstado.isSelected()));
        miLista.add(new ListaConf("Hora", cbxHora.isSelected()));
        miLista.add(new ListaConf("Asunto", cbxAsunto.isSelected()));
        miLista.add(new ListaConf("Receptor", cbxReceptor.isSelected()));
        miLista.add(new ListaConf("Patrimonio", cbxPatrimonio.isSelected()));
        miLista.add(new ListaConf("Adjunto", cbxAdjunto.isSelected()));
        miLista.add(new ListaConf("NotaEntrada", cbxNotaE.isSelected()));
        miLista.add(new ListaConf("NotaSalida", cbxNotaS.isSelected()));
        miLista.add(new ListaConf("Observacion", cbxObservacion.isSelected()));
        miLista.add(new ListaConf("AreaEmisora", cbxAreaEmisora.isSelected()));
        miLista.add(new ListaConf("Creador", cbxCreador.isSelected()));
        try {
            guardarNuevaListaConfiguracion(miLista);
            BandejaEnviados.getBandejaEnviados(mainFrame).llenarTabla();
            btn_volverActionPerformed(evt);
        } catch (IOException ex) {
            Logger.getLogger(ConfigurarTabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_volver1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btGrpTamanio;
    private javax.swing.JButton btn_volver;
    private javax.swing.JButton btn_volver1;
    private javax.swing.JCheckBox cbxAdjunto;
    private javax.swing.JCheckBox cbxAreaEmisora;
    private javax.swing.JCheckBox cbxAsunto;
    private javax.swing.JCheckBox cbxCreador;
    private javax.swing.JCheckBox cbxEstado;
    private javax.swing.JCheckBox cbxFecha;
    private javax.swing.JCheckBox cbxHora;
    private javax.swing.JCheckBox cbxLugar;
    private javax.swing.JCheckBox cbxNotaE;
    private javax.swing.JCheckBox cbxNotaS;
    private javax.swing.JCheckBox cbxObservacion;
    private javax.swing.JCheckBox cbxPatrimonio;
    private javax.swing.JCheckBox cbxReceptor;
    private javax.swing.JCheckBox cbxTicket;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jrGrande;
    private javax.swing.JRadioButton jrPeque;
    // End of variables declaration//GEN-END:variables


    public class ListaConf {
        private String columna;
        private Boolean muestra;

        public ListaConf(String columna, Boolean muestra){
            this.setColumna(columna);
            this.setMuestra(muestra);
        }
        
        @Override
        public String toString(){
            return this.columna+" - "+this.muestra;
        }

        public String getColumna() {
            return columna;
        }

        public void setColumna(String columna) {
            this.columna = columna;
        }

        public Boolean getMuestra() {
            return muestra;
        }

        public void setMuestra(Boolean muestra) {
            this.muestra = muestra;
        }
        
    }

}