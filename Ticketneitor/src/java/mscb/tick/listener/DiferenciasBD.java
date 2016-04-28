/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.listener;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import mscb.tick.controladores.TicketJpaController;
import mscb.tick.entidades.Ticket;
import mscb.tick.tickets.servicios.TicketServ;

/**
 *
 * @author Administrador
 */
public class DiferenciasBD {

    private List<Ticket> miLista;
    public static List<Ticket> cacheDatos;
    private static DiferenciasBD single;
    private TicketServ serviciosT;
    public static List<Ticket> nuevosTick;

    private DiferenciasBD() {
        serviciosT = new TicketServ();
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
