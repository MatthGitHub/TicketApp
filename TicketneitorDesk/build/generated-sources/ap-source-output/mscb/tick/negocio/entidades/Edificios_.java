package mscb.tick.negocio.entidades;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import mscb.tick.negocio.entidades.Tickets;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-18T12:29:00")
@StaticMetamodel(Edificios.class)
public class Edificios_ { 

    public static volatile ListAttribute<Edificios, Tickets> ticketsList;
    public static volatile SingularAttribute<Edificios, Integer> idEdificio;
    public static volatile SingularAttribute<Edificios, String> direccion;
    public static volatile SingularAttribute<Edificios, String> nombre;

}