/**
 * 
 */
package org.ananth.waroftitans.twitter;

import org.ananth.waroftitans.parser.HTMLParserFunction;

import com.google.common.base.Strings;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;

/**
 * @author Ananth
 */
public class TweetTextBuilder {

    private Status  status;
    private boolean removeURL      = true;
    private boolean removeHashTags = true;
    private String  urlToParse = null;
    private final HTMLParserFunction               htmlParserFunction   = new HTMLParserFunction();

    public TweetTextBuilder setRemoveURL (
                                          boolean removeURL) {
        this.removeURL = removeURL;
        return this;
    }

    public TweetTextBuilder setRemoveHashTag (
                                              boolean removeHashTag) {
        this.removeHashTags = removeHashTag;
        return this;
    }

    public TweetTextBuilder setTweetStatus (
                                            Status status) {
        this.status = status;
        return this;
    }
    
    public TweetTextBuilder setURLToParse(String urlToParse) {
        this.urlToParse = urlToParse;
        return this;
    }
    

    public String build () {

        String text = "";
        
        
        if(Strings.isNullOrEmpty(this.urlToParse)) {
            return this.htmlParserFunction.apply(this.urlToParse);
        }
        

        if (Strings.isNullOrEmpty(this.status.getText())) {
            return text;
        }

        if (this.removeURL) {
            for (URLEntity entity : this.status.getURLEntities()) {
                text = text.replaceAll(entity.getDisplayURL(), "");
            }
        }

        if (this.removeHashTags) {
            for (HashtagEntity entity : this.status.getHashtagEntities()) {
                text = text.replaceAll(entity.getText(), "");
            }
        }

        return text;
    }

}
