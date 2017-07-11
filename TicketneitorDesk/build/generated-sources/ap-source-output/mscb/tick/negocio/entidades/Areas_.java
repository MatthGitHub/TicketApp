package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Asuntos;
import mscb.tick.negocio.entidades.Empleados;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T13:45:20")
@StaticMetamodel(Areas.class)
public class Areas_ { 

    public static volatile SingularAttribute<Areas, String> nombreArea;
    public static volatile ListAttribute<Areas, Empleados> empleadosList;
    public static volatile SingularAttribute<Areas, Integer> idArea;
    public static volatile SingularAttribute<Areas, String> correo;
    public static volatile SingularAttribute<Areas, String> direccion;
    public static volatile ListAttribute<Areas, Asuntos> asuntosList;

}