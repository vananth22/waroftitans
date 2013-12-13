/**
 * 
 */
package org.ananth.waroftitans.twitter;


import java.util.List;
import java.util.Set;

import org.ananth.waroftitans.analyser.EntityClassifier;
import org.ananth.waroftitans.exception.SentimentDataFlowException;
import org.ananth.waroftitans.parser.HTMLParserFunction;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import com.google.common.collect.Sets;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

import twitter4j.HashtagEntity;

import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;

/**
 * 
 * This class will clean the Raw Twitter Text, by finding URL, entity detection etc
 * 
 * @author Ananth
 */
public final class TweetTextBuilder {    
    
    
    private Set<String> tweetTextBag = null;
    private Set<String> stopWordBag = null;
        
    
    private TweetTextBuilder(final Set<String> tweetTextList) {
        this.tweetTextBag = tweetTextList;
        this.stopWordBag = Sets.newHashSet();
    }
    
    
    public static TweetTextBuilder on(final String text, final URLEntity[] entities) {
        
        if(Strings.isNullOrEmpty(text)) {
            throw new SentimentDataFlowException("Tweet Text can't be empty or null in the tweet text builder");
        }
        
        
        if(entities == null || entities.length == 0) {   
        
        
            return new TweetTextBuilder( Sets.newLinkedHashSet( 
                                                            Splitter.on(" ")
                                                            .trimResults()
                                                            .split(text)));
        }
        
        
        final String urlToParse = entities[0].getExpandedURL();
        final HTMLParserFunction               htmlParserFunction   = new HTMLParserFunction();
        final String title = htmlParserFunction.apply(urlToParse);
        
        
        if(Strings.isNullOrEmpty(title)) {
            
            return new TweetTextBuilder( Sets.newLinkedHashSet( 
                                                            Splitter.on(" ")
                                                            .trimResults()
                                                            .split(text)));
            
            
            
        }
        
        
        return new TweetTextBuilder( Sets.newLinkedHashSet( 
                                                        Splitter.on(" ")
                                                        .trimResults()
                                                        .split(title)));
                        
                       
    }
    
    
    
    public TweetTextBuilder removeURL(final URLEntity[] entities) {
        
        if(entities == null || entities.length == 0) {
            return this;
        }
        
        for(final URLEntity entity : entities) {
            this.stopWordBag.add(entity.getURL());
        }    
        
        return this;
    }
    
    
    public TweetTextBuilder removeHashWords(HashtagEntity[] entities) {
        if(entities == null || entities.length == 0) {
            return this;
        }
        
        for(HashtagEntity entity : entities) {
            this.stopWordBag.add("#" + entity.getText());
        }        
        
        return this;
    }
    
    
    public TweetTextBuilder removeUsersWithTweet(UserMentionEntity[] entities) {
        
        if(entities == null || entities.length == 0) {
            return this;
        }
        
        for(UserMentionEntity entity : entities) {
            this.stopWordBag.add("@" + entity.getScreenName());
        }
        
        return this;
        
    }
    
    public TweetTextBuilder removeEntity() {
        
        final StringBuilder builder = new StringBuilder(); 
        for(final String text : this.tweetTextBag) {
            builder.append(text);
            builder.append(" ");
        }
        
        final String entityDetectionText =  builder.toString().trim();
        final List<List<CoreLabel>> classifyList = EntityClassifier.getInstance().getCRFClassifier().classify(entityDetectionText); 
        
        for (List<CoreLabel> coreLabels : classifyList) {
            for (CoreLabel coreLabel : coreLabels) {
                final String entityStatus = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
                if(!"O".equalsIgnoreCase(entityStatus.trim())) {
                    this.stopWordBag.add(coreLabel.word());
                }          
             
            }
        }        
        
        return this;
    }
    
    
    public String build() {
        final StringBuilder builder = new StringBuilder();        
        final Set<String> stopWordsIntersection = Sets.difference(this.tweetTextBag, this.stopWordBag);
        
        for(final String text : stopWordsIntersection) {
            builder.append(text);
            builder.append(" ");
        }
        
        return builder.toString().trim();

    }


    public Set<String> getTweetTextList () {
        return tweetTextBag;
    }


    public void setTweetTextList (
                                  Set<String> tweetTextList) {
        this.tweetTextBag = tweetTextList;
    }


    public Set<String> getStopWordBagList () {
        return stopWordBag;
    }


    public void setStopWordBagList (
                                    Set<String> stopWordBagList) {
        this.stopWordBag = stopWordBagList;
    }
    
   
    
    
     


    

   

}
