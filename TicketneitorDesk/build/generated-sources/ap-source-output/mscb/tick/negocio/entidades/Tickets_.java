package mscb.tick.negocio.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Edificios;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-18T12:29:00")
@StaticMetamodel(Tickets.class)
public class Tickets_ { 

    public static volatile SingularAttribute<Tickets, Servicios> servicio;
    public static volatile SingularAttribute<Tickets, String> notaEntrada;
    public static volatile SingularAttribute<Tickets, Date> hora;
    public static volatile SingularAttribute<Tickets, String> patrimonio;
    public static volatile SingularAttribute<Tickets, Integer> idTicket;
    public static volatile SingularAttribute<Tickets, Date> fecha;
    public static volatile SingularAttribute<Tickets, Usuarios> creador;
    public static volatile ListAttribute<Tickets, HistorialTickets> historialTicketsList;
    public static volatile SingularAttribute<Tickets, String> adjunto;
    public static volatile SingularAttribute<Tickets, Date> tiempoResolucion;
    public static volatile SingularAttribute<Tickets, Edificios> fkEdificio;
    public static volatile SingularAttribute<Tickets, String> notaSalida;
    public static volatile SingularAttribute<Tickets, String> observacion;

}