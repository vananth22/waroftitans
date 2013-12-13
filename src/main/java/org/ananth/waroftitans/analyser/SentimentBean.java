/**
 * 
 */
package org.ananth.waroftitans.analyser;

import java.io.Serializable;

/**
 * @author Ananth
 */
public class SentimentBean implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8288661305765638559L;

    private final String      text;
    private final String      sentiment;
    private final int         sentimentScore;
    private String            sentimentText;

    public SentimentBean(
                         final String text,
                         final String sentiment,
                         final int sentimentScore) {
        super();
        this.text = text;
        this.sentiment = sentiment;
        this.sentimentScore = sentimentScore;
    }

    public String getText () {
        return text;
    }

    public String getSentiment () {
        return sentiment;
    }

    public int getSentimentScore () {
        return sentimentScore;
    }
    
    
    

    public String getSentimentText () {
        return sentimentText;
    }

    public void setSentimentText (
                                  String sentimentText) {
        this.sentimentText = sentimentText;
    }

    @Override
    public String toString () {

        return this.sentimentText + "=" + this.sentimentScore;
    }

    @Override
    public int hashCode () {
        final int prime = 31;
        int result = 1;
        result = prime * result
                        + ((sentiment == null) ? 0 : sentiment.hashCode());
        result = prime * result + sentimentScore;
        result = prime * result + ((text == null) ? 0 : text.hashCode());
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
        SentimentBean other = (SentimentBean) obj;
        if (sentiment == null) {
            if (other.sentiment != null)
                return false;
        }
        else if (!sentiment.equals(other.sentiment))
            return false;
        if (sentimentScore != other.sentimentScore)
            return false;
        if (text == null) {
            if (other.text != null)
                return false;
        }
        else if (!text.equals(other.text))
            return false;
        return true;
    }

}
