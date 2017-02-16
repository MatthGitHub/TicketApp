package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Tickets;
import mscb.tick.negocio.entidades.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T10:03:19")
@StaticMetamodel(Servicios.class)
public class Servicios_ { 

    public static volatile ListAttribute<Servicios, Tickets> ticketsList;
    public static volatile ListAttribute<Servicios, Usuarios> usuariosList;
    public static volatile SingularAttribute<Servicios, Asuntos> pertenece;
    public static volatile SingularAttribute<Servicios, Integer> idasuntoS;
    public static volatile SingularAttribute<Servicios, String> nombreasuntoS;

}