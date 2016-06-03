package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.Ticket;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(AreaSistemas.class)
public class AreaSistemas_ { 

    public static volatile SingularAttribute<AreaSistemas, String> nombreArea;
    public static volatile CollectionAttribute<AreaSistemas, Ticket> ticketCollection;
    public static volatile SingularAttribute<AreaSistemas, Integer> idAreaSistemas;

}