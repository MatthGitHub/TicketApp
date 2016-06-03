package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.Area;
import mscb.tick.entidades.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, Integer> legajo;
    public static volatile SingularAttribute<Empleado, Area> fkArea;
    public static volatile SingularAttribute<Empleado, Integer> idEmpleado;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, String> documento;
    public static volatile CollectionAttribute<Empleado, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Empleado, String> nombre;

}