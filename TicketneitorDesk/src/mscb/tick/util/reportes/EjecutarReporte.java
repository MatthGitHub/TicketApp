package mscb.tick.util.reportes;


/**
 *
 * @author Javier Dom{inguez Geniz
 * http://ajdgeniz.wordpress.com
 */
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import javax.swing.JOptionPane;
import mscb.tick.gui.main.Main;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;

public class EjecutarReporte {
    
    private static EjecutarReporte estaClase;
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String RUTA="jdbc:mysql://"+Main.getServer()+"/ticket";
    public static final String USER="administrador";
    public static final String PASSWORD="cavaliere";
    public static Connection CONEXION;
    
    private EjecutarReporte(){
        //log = new ConexionSQL();
        //CONEXION = log.getConexion();
        //serviciosT = new TicketServ();
        
    }
    
    public static EjecutarReporte getEjecutarReporte(){
        if(estaClase == null){
            estaClase = new EjecutarReporte();
        }
        return estaClase;
    }
    
    public void reporteMiTicket(int id){

        try{
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA,USER,PASSWORD);
            System.out.println("Conexion establecida");
            
            String template="//10.20.130.242/TicketneitorDeskUpdt/reportes/MiTicket.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);
            System.out.println("Template: "+template);
            System.out.println("Base de datos: "+RUTA);
            Map param=new HashMap();
            param.put("id", id);
            
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,CONEXION);
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Mi ticket Nº: "+id);
            visor.setVisible(true);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);

        }
    }
    
    
    public void reporteTicketPorUsuario(int userId){
        
        /*try{
            Class.forName(DRIVER);
            CONEXION = DriverManager.getConnection(RUTA,USER,PASSWORD);
            javax.swing.JOptionPane.showMessageDialog(null,"Conexion establecida");
            
            String template="Reporte.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);

            Map param=new HashMap();
            param.put("id", userId);
            
            
            
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,CONEXION);
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Tickets de: "+userId);
            visor.setVisible(true);

        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }*/
    }
    

}
