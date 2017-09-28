/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.List;
import mscb.tick.negocio.entidades.Tickets;

/**
 *
 * @author Administrador
 */
public class DiferenciasBD {

    private List<Tickets> miLista;
    public static Integer cacheDatos;
    private static DiferenciasBD single;

    private DiferenciasBD() {
        cargarCache();
    }

    public static DiferenciasBD getDiferenciasBD() {
        if (single == null) {
            single = new DiferenciasBD();
        }
        return single;
    }

    public int buscarDiferencias() {
        miLista = TicketServ.getTicketServ().buscarPorUsuarioAsunto();
            if(miLista.size() > cacheDatos){
                cacheDatos = miLista.size();
                return cacheDatos;
            }
        
        return 0;

        /**
         * metodo viejo if(cacheDatos.containsAll(miLista)){ return 0; }else{
         * cargarCache(); return 1; }
        *
         */
    }

    private void cargarCache() {
        try{
            cacheDatos = TicketServ.getTicketServ().buscarPorUsuarioAsunto().size();
        }catch(Exception e){
            System.err.println("Carga catché - Aun no se conecto el usuario"+e);
        }
        
    }

}
