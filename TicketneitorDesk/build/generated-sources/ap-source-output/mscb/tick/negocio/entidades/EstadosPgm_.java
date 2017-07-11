package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.EstadoActualPgm;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T13:45:20")
@StaticMetamodel(EstadosPgm.class)
public class EstadosPgm_ { 

    public static volatile SingularAttribute<EstadosPgm, Integer> idEstado;
    public static volatile SingularAttribute<EstadosPgm, String> estado;
    public static volatile ListAttribute<EstadosPgm, EstadoActualPgm> estadoActualPgmList;

}