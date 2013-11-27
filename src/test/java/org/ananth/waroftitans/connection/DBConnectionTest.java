/**
 * 
 */
package org.ananth.waroftitans.connection;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.ananth.waroftitans.constants.TweetConstants;
import org.ananth.waroftitans.persistence.Tweet;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ananth
 *
 */
public class DBConnectionTest {
    
    private static final String PERSISTENCE_UNIT_NAME = "WAR_OF_TITANS";
    private EntityManager em;
    
    @Before
    public void setup() {
        final EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }
    

    @Test
    public void testConnection () {
        assertTrue(em != null);
    }
    
    @Test
    public void testPersistance() {
        Tweet tweet = new Tweet(TweetConstants.RAHUL);
        tweet.setTweetId(1L);
        tweet.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tweet.setText("I'm doing great");
        tweet.setUserId("Ananth");
        tweet.setScore(2);
        tweet.setLattitude(12.9667);
        tweet.setLongitude(77.5667);
        tweet.setRawText("This is my raw text");
        
        try {
            this.em.getTransaction().begin();
            this.em.persist(tweet);
            this.em.getTransaction().commit();
        } finally {
            this.em.close();
        }
        
        assertTrue(tweet.getId() > 0);
        
        
    }
    
    
    @Test
    public void testSelectResult() {
        Query q = this.em.createQuery("Select t from Tweet t");
        
        List<Tweet> result = q.getResultList();
        
        assertTrue(result != null && result.size() > 0);
        
        
    }
    

}
