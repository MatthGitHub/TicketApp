package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Permisos;
import mscb.tick.negocio.entidades.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-04T14:59:10")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile ListAttribute<Roles, Usuarios> usuariosList;
    public static volatile SingularAttribute<Roles, Integer> idRol;
    public static volatile SingularAttribute<Roles, String> nombreRol;
    public static volatile ListAttribute<Roles, Permisos> permisosList;

}