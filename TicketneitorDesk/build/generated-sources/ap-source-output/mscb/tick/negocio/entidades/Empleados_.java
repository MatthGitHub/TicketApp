package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Usuarios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T10:03:19")
@StaticMetamodel(Empleados.class)
public class Empleados_ { 

    public static volatile ListAttribute<Empleados, Usuarios> usuariosList;
    public static volatile SingularAttribute<Empleados, Integer> legajo;
    public static volatile SingularAttribute<Empleados, Areas> fkArea;
    public static volatile SingularAttribute<Empleados, Integer> idEmpleado;
    public static volatile SingularAttribute<Empleados, String> apellido;
    public static volatile SingularAttribute<Empleados, String> documento;
    public static volatile SingularAttribute<Empleados, String> nombre;

}