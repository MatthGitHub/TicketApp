package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.HistorialTickets;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-05-04T14:49:38")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Integer> idEstado;
    public static volatile ListAttribute<Estados, HistorialTickets> historialTicketsList;
    public static volatile SingularAttribute<Estados, String> nombre;

}