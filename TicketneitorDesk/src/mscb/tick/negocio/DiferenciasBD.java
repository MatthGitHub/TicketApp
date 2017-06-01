/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.List;
import mscb.tick.negocio.entidades.Tickets;

/**
 *
 * @author Administrador
 */
public class DiferenciasBD {

    private List<Tickets> miLista;
    public static List<Tickets> cacheDatos;
    private static DiferenciasBD single;
    private TicketServ serviciosT;
    public static List<Tickets> nuevosTick;

    private DiferenciasBD() {
        serviciosT = TicketServ.getTicketServ();
        cacheDatos = new ArrayList<>();
        nuevosTick = new ArrayList<>();
        cargarCache();
    }

    public static DiferenciasBD getDiferenciasBD() {
        if (single == null) {
            single = new DiferenciasBD();
        }
        return single;
    }

    public int buscarDiferencias() {
        int i = 0;
        miLista = serviciosT.traerTodos();
        if(miLista.size() != cacheDatos.size()) {
            for ( i = 0; i < miLista.size(); i++) {
                if (!cacheDatos.contains(miLista.get(i))) {
                    nuevosTick.add(miLista.get(i));
                    cacheDatos.add(miLista.get(i));
                }
            }
            return nuevosTick.size();
        }
        return 0;

        /**
         * metodo viejo if(cacheDatos.containsAll(miLista)){ return 0; }else{
         * cargarCache(); return 1; }
        *
         */
    }

    private void cargarCache() {
        cacheDatos = serviciosT.traerTodos();
    }

}
