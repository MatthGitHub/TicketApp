/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.negocio.controladores.ResolucionesProyectoJpaController;
import mscb.tick.negocio.entidades.Resoluciones;
import mscb.tick.negocio.entidades.ResolucionesProyecto;
import mscb.tick.negocio.entidades.ResolucionesProyectoPK;

/**
 *
 * @author Administrador
 */
public class ResolucioneServ {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("10.20.130.6");
    private ResolucionesProyectoJpaController jpa = new ResolucionesProyectoJpaController(emf);
    private static Query q;
    private static ResolucioneServ estaClase;
    
    private ResolucioneServ(){
        
    }
    
    public static ResolucioneServ getResolucionesServ(){
        if(estaClase == null){
            estaClase = new ResolucioneServ();
        }
        return estaClase;
    }
    
    
    /*NO USAR*/
    public List<ResolucionesProyecto> getProyectosDeResolucionUltimoMes(){
        EntityManager em = emf.createEntityManager();
        List<ResolucionesProyecto> aux = new ArrayList<>();
        //em.getTransaction().begin();
        q = em.createNativeQuery("select Nro_proyecto,ano_proyecto, id_tipo_resolucion,texto,fecha_inicio,Area_genera,Area_destino,Observacion\n" +
                                "from resoluciones_proyecto\n" +
                                "WHERE fecha_inicio > '2017-01-01' AND anulado = 0",ResolucionesProyecto.class);
        aux = q.getResultList();
        return aux;
    }
    
    public List<ResolucionesProyecto> getProyectosDeResolucionEntreFechas(Date desde, Date hasta){
        EntityManager em = emf.createEntityManager();
        List<ResolucionesProyecto> aux = new ArrayList<>();
        //em.getTransaction().begin();
        q = em.createNativeQuery("select Nro_proyecto,ano_proyecto, id_tipo_resolucion,texto,fecha_inicio,Area_genera,Area_destino,Observacion\n" +
                                "from resoluciones_proyecto\n" +
                                "WHERE fecha_inicio > ?1 AND fecha_inicio < ?2 AND anulado = 0",ResolucionesProyecto.class);
        q.setParameter(1, desde);
        q.setParameter(2, hasta);
        aux = q.getResultList();
        return aux;
    }
    
    public List<ResolucionesProyecto> getProyectoBuscada(String id){
        EntityManager em = emf.createEntityManager();
        List<ResolucionesProyecto> aux = new ArrayList<>();
        //em.getTransaction().begin();
        q = em.createNativeQuery("select Nro_proyecto,ano_proyecto, id_tipo_resolucion,texto,fecha_inicio,Area_genera,Area_destino,Observacion\n" +
                                "from resoluciones_proyecto\n" +
                                "WHERE nro_proyecto = ?1 AND anulado = 0",ResolucionesProyecto.class);
        q.setParameter(1, id);
        aux = q.getResultList();
        return aux;
    }
    
    public ResolucionesProyecto getProyecto(String nro, String ano){
        ResolucionesProyectoPK id = new ResolucionesProyectoPK(nro, ano);
        return jpa.findResolucionesProyecto(id);
    }
    
    public List<Resoluciones> getResolucionesEntreFechas(Date desde, Date hasta){
        EntityManager em = emf.createEntityManager();
        List<Resoluciones> aux = new ArrayList<>();
        //em.getTransaction().begin();
        q = em.createNativeQuery("select * \n"+
                                "from resoluciones, resoluciones_tipos, in_organigrama\n" +
                                "where resoluciones.id_tipo_resolucion=resoluciones_tipos.id_tipo_resolucion\n" +
                                "and resoluciones.Area_genera=in_organigrama.codigo_organigrama\n" +
                                "and resoluciones.Fecha_generacion >= ?1 \n" +
                                "and resoluciones.Fecha_generacion <=?2\n" +
                                "order by Fecha_generacion, nro_resolucion+'- I -'+ANO_RESOLUCION",Resoluciones.class);
        q.setParameter(1, desde);
        q.setParameter(2, hasta);
        aux = q.getResultList();
        return aux;
    }
    
    
    public List<Resoluciones> getResolucionBuscada(String id){
        EntityManager em = emf.createEntityManager();
        List<Resoluciones> aux = new ArrayList<>();
        //em.getTransaction().begin();
        q = em.createNativeQuery("select * \n" +
                                "from resoluciones\n" +
                                "WHERE NRO_RESOLUCION = ?1",Resoluciones.class);
        q.setParameter(1, id);
        aux = q.getResultList();
        return aux;
    }
    
}
