/**
 * 
 */
package org.ananth.waroftitans;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.text.StrBuilder;


/**
 * @author Ananth
 *
 */
public class RaviTweet implements Serializable{
    
    private final static String KEY_DELIMITER = "\t";
    private Long      id;
    private Long    userId;
    private String    text;
    private Double    lattitude;
    private Double    longitude;
    private Timestamp createdAt;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Double getLattitude() {
        return lattitude;
    }
    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
   
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        RaviTweet other = (RaviTweet)obj;
        if (id == null) {
            if (other.id != null) return false;
        } else if (!id.equals(other.id)) return false;
        if (userId == null) {
            if (other.userId != null) return false;
        } else if (!userId.equals(other.userId)) return false;
        return true;
    }
    @Override
    public String toString() {
            
        StrBuilder st = new StrBuilder();
        return st.append(id)
          .appendSeparator(KEY_DELIMITER)
          .append(userId)
          .appendSeparator(KEY_DELIMITER)
          .append(lattitude)
          .appendSeparator(KEY_DELIMITER)
          .append(longitude)
          .appendSeparator(KEY_DELIMITER)
          .append(createdAt)
          .appendSeparator(KEY_DELIMITER)
          .append(text)
          .appendSeparator(KEY_DELIMITER)
          .toString();
        
    }

}
