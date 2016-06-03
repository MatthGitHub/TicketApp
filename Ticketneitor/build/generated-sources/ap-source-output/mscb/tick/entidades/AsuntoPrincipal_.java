package mscb.tick.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.entidades.AsuntoSecundario;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-06-02T22:34:05")
@StaticMetamodel(AsuntoPrincipal.class)
public class AsuntoPrincipal_ { 

    public static volatile CollectionAttribute<AsuntoPrincipal, AsuntoSecundario> asuntoSecundarioCollection;
    public static volatile SingularAttribute<AsuntoPrincipal, Integer> idasuntoP;
    public static volatile SingularAttribute<AsuntoPrincipal, String> nombre;

}