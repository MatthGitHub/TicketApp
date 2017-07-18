package mscb.tick.util;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mscb.tick.negocio.LoginEJB;
/**
 * @see http://www.jc-mouse.net/
 * @author mouse
 */
public class MySystemTray {
    private static MySystemTray estaClase;
    private JFrame parent;
    private PopupMenu popup = new PopupMenu();
    private final Image image =new ImageIcon(getClass().getResource("/mscb/tick/resources/imagenes/icono.png")).getImage() ;
    private final TrayIcon trayIcon = new TrayIcon(image, "Ticketneitor", popup);    
    private Timer timer;        
    
    /**
     * Constructor de clase
     * @param frame
     */
    private MySystemTray( JFrame frame)
    {
    this.parent = frame;
    //comprueba si SystemTray es soportado en el sistema
    if (SystemTray.isSupported())
    {
      //obtiene instancia SystemTray
      SystemTray systemtray = SystemTray.getSystemTray();
      trayIcon.setImageAutoSize(true);
        
      //acciones del raton sobre el icono en la barra de tareas
        MouseListener mouseListener = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent evt) {            

                //Si se presiona con el boton izquierdo en el icono
                //y la aplicacion esta minimizada se muestra una frase
                if( evt.getButton() == MouseEvent.BUTTON1 && parent.getExtendedState()==JFrame.ICONIFIED ){
                    if(parent.getExtendedState()== JFrame.ICONIFIED){
                        parent.setVisible(true);
                        parent.setExtendedState( JFrame.NORMAL );
                        parent.repaint();                
                        if(timer!=null) timer.cancel();
                    }    
                }            
            }

            @Override
            public void mouseEntered(MouseEvent evt) {/*nada x aqui circulen...*/}

            @Override
            public void mouseExited(MouseEvent evt) {/*nada x aqui circulen...*/}

            @Override
            public void mousePressed(MouseEvent evt) {/*nada x aqui circulen...*/}

            @Override
            public void mouseReleased(MouseEvent evt) {/*nada x aqui circulen...*/}
        };

        /* ----------------- ACCIONES DEL MENU POPUP --------------------- */
        //Salir de aplicacion
        ActionListener exitListener = (ActionEvent e) -> {
            System.exit(0);
        };
        
        //Restaurar aplicacion
        ActionListener restoreListener = (ActionEvent e) -> {
            //si esta minimizado restaura JFrame
            if(parent.getExtendedState()== JFrame.ICONIFIED){
                parent.setVisible(true);
                parent.setExtendedState( JFrame.NORMAL );
                parent.repaint();                
                if(timer!=null) timer.cancel();
            }            
        };
    
        //Se crean los Items del menu PopUp y se añaden
        //MenuItem exitAppItem = new MenuItem("Salir");
        //exitAppItem.addActionListener(exitListener);
        //popup.add(exitAppItem);

        MenuItem restoreAppItem = new MenuItem("Restaurar");
        restoreAppItem.addActionListener(restoreListener);
        popup.add(restoreAppItem);
        
        /* ----------------- ACCIONES DEL MENU POPUP : END ---------------- */
        
        trayIcon.addMouseListener(mouseListener);

        //Añade el TrayIcon al SystemTray
        try {
            systemtray.add(trayIcon);
        } catch (AWTException e) {
            System.err.println( "Error:" + e.getMessage() );
        }
    } else {
        System.err.println( "Error: SystemTray no es soportado" );
        return;
    }

    //Cuando se minimiza JFrame, se oculta para que no aparesca en la barra de tareas
    parent.addWindowListener(new WindowAdapter(){
        @Override
        public void windowIconified(WindowEvent e){
           parent.setVisible(false);//Se oculta JFrame
           //Se inicia una tarea cuando se minimiza           
           if(timer!=null) timer.cancel();
           timer = new Timer();           
           timer.schedule(new MyTimerTask(),2000, 12000 );//Se ejecuta cada 12 segundos
        }
    });

    }

    public static MySystemTray getMySystemTray(JFrame frame){
        if(estaClase == null){
            estaClase = new MySystemTray(frame);
        }
        return estaClase;
    }
    
    //Muestra una burbuja con la accion que se realiza
    public void MensajeTrayIcon(String texto, MessageType tipo)
    {
        trayIcon.displayMessage(LoginEJB.usuario.getNombreUsuario()+": ", texto, tipo);        
    }
    
    /**
     * clase interna que manejara una accion en segundo plano
     */
    class MyTimerTask extends TimerTask {
        
        @Override
        public void run() {            
            actionBackground();
        }

        /**
         * accion a realizar cuando la aplicacion a sido minimizada
         */
        public void actionBackground()
        { 
            
        }

    }
}