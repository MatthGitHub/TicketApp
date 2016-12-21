package mscb.tick.util.reportes;


/**
 *
 * @author Javier Dom{inguez Geniz
 * http://ajdgeniz.wordpress.com
 */
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;
import mscb.tick.entidades.Tickets;
import mscb.tick.login.servicios.LoginEJB;
import mscb.tick.tickets.servicios.TicketServ;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.save.JRPdfSaveContributor.*;
import net.sf.jasperreports.view.JRViewer.*;
import net.sf.jasperreports.view.save.JRMultipleSheetsXlsSaveContributor.*;

public class EjecutarReporte {
    
    private static EjecutarReporte estaClase;
    public static final String DRIVER="com.mysql.jdbc.Driver";
    public static final String RUTA="jdbc:mysql://10.20.130.242/ticket";
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
            javax.swing.JOptionPane.showMessageDialog(null,"Conexion establecida");
            
            String template="Reporte.jasper";
            JasperReport reporte=null;
            reporte=(JasperReport) JRLoader.loadObject(template);

            Map param=new HashMap();
            param.put("id", id);
            
            JasperPrint jasperprint= JasperFillManager.fillReport(reporte,param,CONEXION);
            JasperViewer visor=new JasperViewer(jasperprint,false);
            visor.setTitle("Mi ticket");
            visor.setVisible(true);

        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null, e);

        }
    }


}
