package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Empleados;
import mscb.tick.negocio.entidades.HistorialTickets;
import mscb.tick.negocio.entidades.Roles;
import mscb.tick.negocio.entidades.Servicios;
import mscb.tick.negocio.entidades.Tickets;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-08-14T14:24:05")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile ListAttribute<Usuarios, Tickets> ticketsList;
    public static volatile ListAttribute<Usuarios, Servicios> serviciosList;
    public static volatile ListAttribute<Usuarios, HistorialTickets> historialTicketsList;
    public static volatile SingularAttribute<Usuarios, String> idExtreme;
    public static volatile SingularAttribute<Usuarios, Empleados> fkEmpleado;
    public static volatile SingularAttribute<Usuarios, Roles> fkRol;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuario;
    public static volatile SingularAttribute<Usuarios, String> contrasenia;
    public static volatile SingularAttribute<Usuarios, String> nombreUsuario;
    public static volatile SingularAttribute<Usuarios, Boolean> activo;

}