/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.UserGroups;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Diese Klasse Stellt eine Schnittstelle zwischen UserGroupsController und Datenbank dar, ein Model
 * @author Philipp Adler
 * @version 2015-06-11
 */
@Stateless
public class UserGroupsFacade extends AbstractFacade<UserGroups> {
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
    public UserGroupsFacade() {
        super(UserGroups.class);
    }
    
    /**
     * Diese Methode sucht in der Datenbank nach den uebergebenen Usernamen
     * @param username der Username nach dem gesucht wird
     * @return Liste von UserGroups die gefunden wurden
     */
    public List<UserGroups> findByUsername(String username){
        return em.createNamedQuery("UserGroups.findByUsername").setParameter("username", username).getResultList();
    }
}
