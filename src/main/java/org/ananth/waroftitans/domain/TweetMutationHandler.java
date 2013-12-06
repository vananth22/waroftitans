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

    private final EntityManager em;
    private static final String PERSISTENCE_UNIT_NAME = "WAR_OF_TITANS";

    public TweetMutationHandler() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        this.em = factory.createEntityManager();
    }

    public Tweet saveTweet (
                            final Tweet tweet) {

        try {
            this.em.getTransaction().begin();
            this.em.persist(tweet);
            this.em.getTransaction().commit();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return tweet;
    }

}
