package mscb.tick.negocio.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Estados;
import mscb.tick.negocio.entidades.RazonesTransferencias;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T10:03:19")
@StaticMetamodel(HistorialTickets.class)
public class HistorialTickets_ { 

    public static volatile SingularAttribute<HistorialTickets, Estados> fkEstado;
    public static volatile SingularAttribute<HistorialTickets, Date> fecha;
    public static volatile SingularAttribute<HistorialTickets, String> resolucion;
    public static volatile SingularAttribute<HistorialTickets, Usuarios> fkUsuario;
    public static volatile SingularAttribute<HistorialTickets, RazonesTransferencias> fkRazon;
    public static volatile SingularAttribute<HistorialTickets, Integer> idHistorial;
    public static volatile SingularAttribute<HistorialTickets, Tickets> fkTicket;

}