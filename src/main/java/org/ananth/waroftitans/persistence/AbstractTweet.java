/**
 * 
 */
package org.ananth.waroftitans.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Ananth
 */

@MappedSuperclass
public abstract class AbstractTweet {

    private Long      id;
    private Long      tweetId;
    private String    text;
    private String    userId;
    private int       score;
    private Double    lattitude;
    private Double    longitude;
    private Timestamp createdAt;
    private String    actor;
    private String    rawText;
    private String url;
    
    
    
    
    
    

    public AbstractTweet() {
        super();
    }

    public AbstractTweet(
                         String actor) {
        super();
        this.actor = actor;
    }

    public AbstractTweet(
                         Long tweetId,
                         String userId) {
        super();
        this.tweetId = tweetId;
        this.userId = userId;
    }

    public AbstractTweet(
                         Long id) {
        super();
        this.id = id;
    }

    public AbstractTweet(
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
        super();
        this.id = id;
        this.tweetId = tweetId;
        this.text = text;
        this.userId = userId;
        this.score = score;
        this.lattitude = lattitude;
        this.longitude = longitude;
        this.createdAt = createdAt;
        this.actor = actor;
        this.rawText = rawText;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(
                    name = "id",
                    unique = true,
                    nullable = false
                    )
                    public Long getId () {
        return id;
    }

    public void setId (
                       Long id) {
        this.id = id;
    }

    @Column(name = "tweet_id", unique = true, nullable = false)
    public Long getTweetId () {
        return tweetId;
    }

    public void setTweetId (
                            Long tweetId) {
        this.tweetId = tweetId;
    }

    @Column(name = "text", nullable = false)
    public String getText () {
        return text;
    }

    public void setText (
                         String text) {
        this.text = text;
    }

    @Column(name = "user_id")
    public String getUserId () {
        return userId;
    }

    public void setUserId (
                           String userId) {
        this.userId = userId;
    }

    @Column(name = "score")
    public int getScore () {
        return score;
    }

    public void setScore (
                          int score) {
        this.score = score;
    }

    @Column(name = "latitude")
    public Double getLattitude () {
        return lattitude;
    }

    public void setLattitude (
                              Double lattitude) {
        this.lattitude = lattitude;
    }

    @Column(name = "longitude")
    public Double getLongitude () {
        return longitude;
    }

    public void setLongitude (
                              Double longitude) {
        this.longitude = longitude;
    }

    @Column(name = "created_at")
    public Timestamp getCreatedAt () {
        return createdAt;
    }

    public void setCreatedAt (
                              Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "actor")
    public String getActor () {
        return actor;
    }

    public void setActor (
                          String actor) {
        this.actor = actor;
    }

    @Column(name = "raw_text")
    public String getRawText () {
        return rawText;
    }

    public void setRawText (
                            String rawText) {
        this.rawText = rawText;
    }
    
    
   
    
    @Column(name = "url")
    public String getUrl () {
        return url;
    }

    public void setUrl (
                        String url) {
        this.url = url;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actor == null) ? 0 : actor.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((lattitude == null) ? 0 : lattitude.hashCode());
        result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
        result = prime * result + ((rawText == null) ? 0 : rawText.hashCode());
        result = prime * result + score;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
        result = prime * result + ((tweetId == null) ? 0 : tweetId.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals (
                           Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractTweet other = (AbstractTweet) obj;
        if (actor == null) {
            if (other.actor != null)
                return false;
        }
        else if (!actor.equals(other.actor))
            return false;
        if (createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!createdAt.equals(other.createdAt))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (lattitude == null) {
            if (other.lattitude != null)
                return false;
        }
        else if (!lattitude.equals(other.lattitude))
            return false;
        if (longitude == null) {
            if (other.longitude != null)
                return false;
        }
        else if (!longitude.equals(other.longitude))
            return false;
        if (rawText == null) {
            if (other.rawText != null)
                return false;
        }
        else if (!rawText.equals(other.rawText))
            return false;
        if (score != other.score)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        }
        else if (!text.equals(other.text))
            return false;
        if (tweetId == null) {
            if (other.tweetId != null)
                return false;
        }
        else if (!tweetId.equals(other.tweetId))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        }
        else if (!userId.equals(other.userId))
            return false;
        return true;
    }

    @Override
    public String toString () {
        return "AbstractTweet [id=" + id + ", tweetId=" + tweetId + ", text=" + text + ", userId=" + userId
                        + ", score=" + score + ", lattitude=" + lattitude + ", longitude=" + longitude + ", createdAt="
                        + createdAt + ", actor=" + actor + "]";
    }

}
