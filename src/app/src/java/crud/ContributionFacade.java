/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import entity.Contribution;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author user
 */
@Stateless
public class ContributionFacade extends AbstractFacade<Contribution> {
    @PersistenceContext(unitName = "appPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContributionFacade() {
        super(Contribution.class);
    }
    /**
     * Gibt eine Liste mit Bewertungen (+Kommentar) zurueck die fuer das Buch verfuegbar sind.
     * @param bookId
     * @return Liste der Bewertungen
     */
    public List<Contribution> getContributionByBookId(int bookId){
       List<Contribution> liste =  this.getEntityManager().createNamedQuery("Contribution.findByBookid").setParameter("bookid", bookId).getResultList();
       return liste;
    }
    
}
