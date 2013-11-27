/**
 * 
 */
package org.ananth.waroftitans.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Ananth
 *
 */

@Entity
@Table(name = "tweet")
public final class Tweet extends AbstractTweet implements Serializable{

    

    /**
     * 
     */
    private static final long serialVersionUID = -1225453512590751378L;

    public Tweet(
                 Long id,
                 Long tweetId,
                 String text,
                 String userId,
                 int score,
                 Double lattitude,
                 Double longitude,
                 Timestamp createdAt,
                 String actor,
                 String rawText) {
        super(id, tweetId, text, userId, score, lattitude, longitude, createdAt, actor, rawText);
        // TODO Auto-generated constructor stub
    }

    public Tweet(
                 Long tweetId,
                 String userId) {
        super(tweetId, userId);
       
    }

    public Tweet(
                 Long id) {
        super(id);
        
    }

    public Tweet(
                 String actor) {
        super(actor);
        
    }

    public Tweet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString () {
        return super.toString();
    }
    

}
