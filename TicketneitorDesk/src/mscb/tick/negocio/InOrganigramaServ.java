/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.negocio;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import mscb.tick.negocio.controladores.InOrganigramaJpaController;
import mscb.tick.negocio.entidades.InOrganigrama;

/**
 *
 * @author Administrador
 */
public class InOrganigramaServ {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("10.20.130.6");
    private InOrganigramaJpaController jpa = new InOrganigramaJpaController(emf);
    private static InOrganigramaServ estaClase;
    private static Query q;

    public static InOrganigramaServ getInOrganigramaServ(){
        if(estaClase == null){
            estaClase = new InOrganigramaServ();
        }
        return estaClase;
    }
    
    public List<InOrganigrama> getAreasOrganigrama(){
        return jpa.findInOrganigramaEntities();
    }
    
    public List<InOrganigrama> getAreasOrganigramaInexistentesEnTickets(){
        List<InOrganigrama> aux = jpa.findInOrganigramaEntities();
        List<InOrganigrama> res = new ArrayList<>();
        
        for(int i = 0; i < aux.size(); i++){
            if(AreaServ.getAreaServ().getSolicitantePorCodigo(aux.get(i).getCodigoOrganigrama()) == null){
                res.add(aux.get(i));
            }
        }
        return res;
    }
    
    public InOrganigrama getAreaPorConcepto(String concepto){
        EntityManager em = EntitiesManager.getEmfPgm().createEntityManager();
        InOrganigrama aux = null;
        
        q = em.createNativeQuery("SELECT *"
                                + " FROM IN_ORGANIGRAMA"
                                + " WHERE CONCEPTO = ?1",InOrganigrama.class);
        q.setParameter(1, concepto);
        try {
            aux = (InOrganigrama) q.getSingleResult();
        } catch (Exception e) {
            
        }

        return aux;
    }
    
    
}
