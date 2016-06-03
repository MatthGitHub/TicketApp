package mscb.tick.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.Area;
import mscb.tick.entidades.AreaSistemas;
import mscb.tick.entidades.AsuntoSecundario;
import mscb.tick.entidades.Estados;
import mscb.tick.entidades.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(Ticket.class)
public class Ticket_ { 

    public static volatile SingularAttribute<Ticket, Estados> fkEstado;
    public static volatile SingularAttribute<Ticket, Integer> idTicket;
    public static volatile SingularAttribute<Ticket, Date> fecha;
    public static volatile SingularAttribute<Ticket, Date> hora;
    public static volatile SingularAttribute<Ticket, Area> fkAreaEmisor;
    public static volatile SingularAttribute<Ticket, AsuntoSecundario> asunto;
    public static volatile SingularAttribute<Ticket, AreaSistemas> fkAreaSistemas;
    public static volatile SingularAttribute<Ticket, String> respuesta;
    public static volatile SingularAttribute<Ticket, Usuario> usuarioReceptor;
    public static volatile SingularAttribute<Ticket, String> observacion;
    public static volatile SingularAttribute<Ticket, Usuario> fkUsuarioEmisor;

}