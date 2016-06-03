package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.AsuntoPrincipal;
import mscb.tick.entidades.Ticket;
import mscb.tick.entidades.Usuario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(AsuntoSecundario.class)
public class AsuntoSecundario_ { 

    public static volatile CollectionAttribute<AsuntoSecundario, Ticket> ticketCollection;
    public static volatile SingularAttribute<AsuntoSecundario, AsuntoPrincipal> pertenece;
    public static volatile SingularAttribute<AsuntoSecundario, Integer> idasuntoS;
    public static volatile SingularAttribute<AsuntoSecundario, String> nombreasuntoS;
    public static volatile CollectionAttribute<AsuntoSecundario, Usuario> usuarioCollection;

}