/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mscb.tick.util;

import java.sql.Connection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class ConexionSQL {
    
    public Connection getConexion(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TicketneitorPU");
        EntityManager em = emf.createEntityManager();
        java.sql.Connection conn = em.unwrap(java.sql.Connection.class);
        System.out.println(conn);
        return conn;
    }
    
}
