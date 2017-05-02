package mscb.tick.negocio.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-02T10:41:27")
@StaticMetamodel(Tickets.class)
public class Tickets_ { 

    public static volatile SingularAttribute<Tickets, Integer> idTicket;
    public static volatile SingularAttribute<Tickets, Date> fecha;
    public static volatile SingularAttribute<Tickets, Usuarios> creador;
    public static volatile ListAttribute<Tickets, HistorialTickets> historialTicketsList;
    public static volatile SingularAttribute<Tickets, Servicios> servicio;
    public static volatile SingularAttribute<Tickets, Date> hora;
    public static volatile SingularAttribute<Tickets, String> patrimonio;
    public static volatile SingularAttribute<Tickets, Date> tiempoResolucion;
    public static volatile SingularAttribute<Tickets, String> observacion;

}