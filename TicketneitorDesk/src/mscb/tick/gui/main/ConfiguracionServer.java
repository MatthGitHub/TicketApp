/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.gui.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import mscb.tick.gui.tickets.ConfigurarTabla;
import mscb.tick.negocio.ConfiguracioneServ;
import mscb.tick.negocio.EntitiesManager;
import mscb.tick.negocio.entidades.Configuracion;

/**
 *
 * @author Administrador
 */
public class ConfiguracionServer {
    private static ConfiguracionServer estaClase;
    private Conf miConfig;
    
    private ConfiguracionServer(){
        
    }
    
    public static ConfiguracionServer getConfiguracionServer(){
        if(estaClase == null){
            estaClase = new ConfiguracionServer();
        }
        return estaClase;
    }
    
     /**
     * Este metodo crea el archivo de configuracion
     * @throws FileNotFoundException
     * @throws IOException 
     */
    private void crearArchivoConfiguracion() throws FileNotFoundException, IOException{
        BufferedWriter bw;
        
        String ruta = new File("conf.srv").getAbsolutePath();
        
        File archivo = new File(ruta);

        List<Configuracion> servers = ConfiguracioneServ.getConfiguracioneServ().getServidores();
        
        Conf[] os = new Conf[servers.size()];
        for(int i = 0; i < servers.size(); i++){
            os[i] = new Conf(servers.get(i).getNombre(),servers.get(i).getDescripcion());
        }
        
        String server = os[JOptionPane.showOptionDialog(null, "Elegir server", "Servers: ", 0, 0, null, os, null)].server;
        
        bw = new BufferedWriter(new FileWriter(archivo));
        bw.write("Server\t"+server+"\n");
        bw.close();
    }
    
    public Conf getConfiguracion() throws IOException{
        String ruta = new File("conf.srv").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        if(archivo.exists()) {
            return traerConfiguracion();
        }else{
            crearArchivoConfiguracion();
            return traerConfiguracion();
        }
    }
    
    
    public Conf crearNuevaConfiguracion() throws IOException{
        String ruta = new File("conf.srv").getAbsolutePath();
        
        File archivo = new File(ruta);
        crearArchivoConfiguracion();
        EntitiesManager.destruirEntityManager();
        return traerConfiguracion();
    }
    
    
    private Conf traerConfiguracion() throws FileNotFoundException, IOException{
        //miConfig = new Conf();
        BufferedWriter bw;
        FileReader fr;
        BufferedReader br;
        
        String ruta = new File("conf.srv").getAbsolutePath();
        
        File archivo = new File(ruta);
        
        br = new BufferedReader(new FileReader(archivo));
        String lineJustFetched = null;
        String[] wordsArray;
        String palabraUno = null;
        String palabraDos = null;
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
                            palabraDos = each;
                            i = 0;
                        }
                    }
                }
            }
            miConfig = new Conf(palabraUno, palabraDos);
        }
        br.close();
        //System.out.println(miConfig);
        return miConfig;
    }
    
    public class Conf {
        private String columna;
        private String server;

        public Conf(String columna, String server){
            this.setColumna(columna);
            this.setServer(server);
        }
        
        public Conf(){
            
        }
        
        @Override
        public String toString(){
            return this.columna+" - "+this.server;
        }

        public String getColumna() {
            return columna;
        }

        public void setColumna(String columna) {
            this.columna = columna;
        }

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }
        
    }
}
