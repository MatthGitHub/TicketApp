package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.Ticket;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(Estados.class)
public class Estados_ { 

    public static volatile SingularAttribute<Estados, Integer> idEstado;
    public static volatile CollectionAttribute<Estados, Ticket> ticketCollection;
    public static volatile SingularAttribute<Estados, String> nombre;

}