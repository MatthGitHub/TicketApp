package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.AsuntoSecundario;
import mscb.tick.entidades.Empleado;
import mscb.tick.entidades.Permisos;
import mscb.tick.entidades.Ticket;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile CollectionAttribute<Usuario, Ticket> ticketCollectionUsuarioReceptor;
    public static volatile SingularAttribute<Usuario, Empleado> fkEmpleado;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, Permisos> fkPermiso;
    public static volatile CollectionAttribute<Usuario, AsuntoSecundario> asuntoSecundarioCollection;
    public static volatile SingularAttribute<Usuario, String> contrasenia;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile SingularAttribute<Usuario, Boolean> activo;
    public static volatile CollectionAttribute<Usuario, Ticket> ticketCollectionUsuarioEmisor;

}