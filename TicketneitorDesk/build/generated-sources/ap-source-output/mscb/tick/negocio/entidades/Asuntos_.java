package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Areas;
import mscb.tick.negocio.entidades.Servicios;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-02-16T10:03:19")
@StaticMetamodel(Asuntos.class)
public class Asuntos_ { 

    public static volatile ListAttribute<Asuntos, Servicios> serviciosList;
    public static volatile SingularAttribute<Asuntos, Areas> fkArea;
    public static volatile SingularAttribute<Asuntos, Integer> idasuntoP;
    public static volatile SingularAttribute<Asuntos, String> nombre;

}