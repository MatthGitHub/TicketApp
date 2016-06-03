package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.Empleado;
import mscb.tick.entidades.Ticket;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(Area.class)
public class Area_ { 

    public static volatile SingularAttribute<Area, String> nombreArea;
    public static volatile CollectionAttribute<Area, Ticket> ticketCollection;
    public static volatile SingularAttribute<Area, Integer> idArea;
    public static volatile CollectionAttribute<Area, Empleado> empleadoCollection;
    public static volatile SingularAttribute<Area, String> correo;
    public static volatile SingularAttribute<Area, String> direccion;

}