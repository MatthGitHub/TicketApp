package mscb.tick.util;

import java.awt.event.ActionEvent;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mscb.tick.gui.main.Main;
import mscb.tick.gui.tickets.BandejaTickets;
import mscb.tick.negocio.EstadoServ;
import mscb.tick.negocio.HistorialServ;
import mscb.tick.negocio.LoginEJB;
import mscb.tick.negocio.TicketServ;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.util.reportes.EjecutarReporte;

/**
 * Accion generica para los items del menu.
 * 
 * @author Chuidiang
 * 
 */
public class AccionMenu extends AbstractAction {
	private String textoOpcion;
        private JPopupMenu main;
	/**
	 * Se le pasa el nombre que se quiere que se muestre
	 * 
	 * @param textoOpcion
	 */
	public AccionMenu(String textoOpcion,JPopupMenu main) {
            this.textoOpcion = textoOpcion;
            this.putValue(Action.NAME, textoOpcion);
            this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
            System.out.println("Pulsado " + textoOpcion);
            switch(textoOpcion){
                /* El usuario logueado acepta el ticket*/
                case "Aceptar":
                    aceptar();
                    break;
                case "Transferir":
                    transferir();
                    break;
                case "Recibido":
                    cambiarEstado("Recibido");
                    break;
                case "En espera":
                    cambiarEstado("En espera");
                    break;
                case "En trabajo":
                    cambiarEstado("Trabajando");
                    break;
                case "En control":
                    cambiarEstado("Control");
                    break;
                case "Modificar patrimonio":
                    modificarPatrimonio();
                    break;
                case "Modificar nota de salida":
                    modificarNotaSalida();
                    break;
                case "Responder":
                    responder();
                    break;
                case "Ver adjunto":
                    verAdjunto();
                    break;
                case "Ver resolucion":
                    verResolucion();
                    break;
                case "Imprimir":
                    imprimir();
                    break;
                case "Marcar resuelto":
                    marcarResuelto();
                    break;
            }
        main.setVisible(false);
	}
        
        private void aceptar(){
            EstadoServ esta;
            Date fecha;
            Tickets miTick = TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString()));
            String usuarioAct;
            if(miTick.getUltimoUsuario() == null){
            usuarioAct = "No aun";
            }else{
                usuarioAct = miTick.getUltimoUsuario().getNombreUsuario();
            }
            System.out.println(usuarioAct);

            //Verifica si el ticket le pertenece a alguien
            if(usuarioAct.equalsIgnoreCase("No aun")){
                HistorialTickets his = new HistorialTickets();
                esta = EstadoServ.getEstadoServ();
                fecha = new Date();
                his.setFecha(fecha);
                his.setHora(fecha);
                his.setFkEstado(esta.traerEstado(2));
                his.setFkTicket(miTick);
                his.setFkUsuario(LoginEJB.usuario);
                HistorialServ.getHistorialServ().nuevo(his);
                BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
            }else{
                //Verifica si el ticket ya le pertenece al que quiere tomarlo
                if(usuarioAct.equalsIgnoreCase(LoginEJB.usuario.getNombreUsuario())){
                    JOptionPane.showMessageDialog(Main.getMainFrame(), "Este ticket ya le pertence!", "Error", JOptionPane.ERROR_MESSAGE);
                }else{// Si no cambia el ticket de propietario
                    HistorialTickets his = new HistorialTickets();
                    esta = EstadoServ.getEstadoServ();
                    fecha = new Date();
                    his.setFecha(fecha);
                    his.setHora(fecha);
                    his.setFkEstado(esta.traerEstado(2));
                    his.setFkTicket(miTick);
                    his.setFkUsuario(LoginEJB.usuario);
                    HistorialServ.getHistorialServ().nuevo(his);
                    BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
                }

            }
        }
        
        private void transferir(){
            if(JOptionPane.showConfirmDialog(Main.getMainFrame(), "Desea cargar una resolucion para el ticket?", "Base de conocimiento", JOptionPane.YES_NO_OPTION) == 0){
                int ide = Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(), 0).toString());
                Main.getMainFrame().transferirTicket(TicketServ.getTicketServ().buscarUno(ide));
                Main.getMainFrame().resolucionTicketSinMarcar(TicketServ.getTicketServ().buscarUno(ide));
                BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
            }else{
                Main.getMainFrame().transferirTicket(TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(), 0).toString())));
                BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
            }
        }
        
        private void cambiarEstado(String nombreEstado){
            Estados estado = EstadoServ.getEstadoServ().traerEstado(nombreEstado);
            Date fecha;
            Tickets miTicket = TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString()));
            if(!miTicket.getUltimoEstado().equals(estado)){
                HistorialTickets his = new HistorialTickets();
                fecha = new Date();
                his.setHora(fecha);
                his.setFecha(fecha);
                his.setFkEstado(estado);
                his.setFkTicket(miTicket);
                his.setFkUsuario(LoginEJB.usuario);
                his.setResolucion(miTicket.getResolucion());
                HistorialServ.getHistorialServ().nuevo(his);
                BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
            }else{
                JOptionPane.showMessageDialog(Main.getMainFrame(), "El ticket ya se encuentra "+nombreEstado+"!!");
            }
        }
        
        private void modificarPatrimonio(){
            Main.getMainFrame().modificarPatrimonio(TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString())));
        }
        
        private void modificarNotaSalida(){
            Main.getMainFrame().modificarNotaSalida(TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString())));
        }
        
        private void responder(){
            Main.getMainFrame().Respuestas(TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString())));
        }
        
        private void verAdjunto(){
            if(TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(), 0).toString())).getAdjunto() != null){
            //String arch = "\\\\10.20.130.242\\www\\TicketWeb\\archivos\\"+serviciosT.buscarUno(Integer.parseInt(modelo.getValueAt(jt_tickets.getSelectedRow(), 0).toString())).getAdjunto();
            String arch = "file://10.20.130.242/www/TicketWeb/archivos/"+TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(), 0).toString())).getAdjunto();
            BandejaTickets.getBandejaTickets(Main.getMainFrame()).abrirArchivo(arch);
        }else{
            JOptionPane.showMessageDialog(Main.getMainFrame(), "Este ticket no posse adjunto");
        }
        }
        
        private void verResolucion(){
            Main.getMainFrame().verRespuestas(TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString())));
        }
        
        private void imprimir(){
            EjecutarReporte report = EjecutarReporte.getEjecutarReporte();
            report.reporteMiTicket(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(), 0).toString()));
        }
        
        private void marcarResuelto(){
            if(!BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(), 4).equals("Resuelto")){
                Tickets miTicket = TicketServ.getTicketServ().buscarUno(Integer.parseInt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).modelo.getValueAt(BandejaTickets.getBandejaTickets(Main.getMainFrame()).jt_tickets.getSelectedRow(),0).toString()));
                Main.getMainFrame().resolucionTicket(miTicket);
                BandejaTickets.getBandejaTickets(Main.getMainFrame()).llenarTabla();
            }else{
                JOptionPane.showMessageDialog(Main.getMainFrame(), "El ticket ya se encuentra en resuelto!!");
            }
        }
}