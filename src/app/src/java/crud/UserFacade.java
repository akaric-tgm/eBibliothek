/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Diese Klasse Stellt eine Schnittstelle zwischen Controller und Datenbank dar, ein Model
 * @author Philipp Adler
 * @version 2015-06-11
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    /**
     * Dies Getter-Methode gibt den EntityManager zurueck
     * @return den EM
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Default Konstruktor
     */
    public UserFacade() {
        super(User.class);
    }
    
    /**
     * Diese Methode sucht in der Datenbank nach den uebergebenen Usernamen
     * @param username der Username nach dem gesucht wird
     * @return Liste von Usern die gefunden wurden
     */
    public List<User> findByUsername(String username){
        return em.createNamedQuery("User.findByUsername").setParameter("username", username).getResultList();
    }
    
    /**
     * Diese Methode sucht in der Datenbank nach der uebergebenen Email-Adresse
     * @param email die Email-Adresse nach dem gesucht wird
     * @return Liste von Usern die gefunden wurden
     */
    public List<User> findByEmail(String email){
        return em.createNamedQuery("User.findByEmail").setParameter("email", email).getResultList();
    }
    
}
