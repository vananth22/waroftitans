/**
 * 
 */
package org.ananth.waroftitans.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ananth.waroftitans.persistence.Tweet;

/**
 * @author Ananth
 */
public class TweetMutationHandler {

    private final EntityManagerFactory factory;
    private static final String PERSISTENCE_UNIT_NAME = "WAR_OF_TITANS";

    public TweetMutationHandler() {
        this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
    }

    public Tweet saveTweet (
                            final Tweet tweet) {
        
        final EntityManager em = factory.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(tweet);
            em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        
        return tweet;
    }

}
