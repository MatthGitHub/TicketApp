package mscb.tick.negocio.entidades;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.EstadosPgm;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-18T12:29:00")
@StaticMetamodel(EstadoActualPgm.class)
public class EstadoActualPgm_ { 

    public static volatile SingularAttribute<EstadoActualPgm, Date> fecha;
    public static volatile SingularAttribute<EstadoActualPgm, EstadosPgm> fkEstadoPgm;
    public static volatile SingularAttribute<EstadoActualPgm, Integer> id;

}