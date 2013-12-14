/**
 * 
 */
package org.ananth.waroftitans.twitter;


import java.util.List;
import java.util.Set;

import org.ananth.waroftitans.analyser.EntityClassifier;
import org.ananth.waroftitans.analyser.EntityTransformFunction;
import org.ananth.waroftitans.exception.SentimentDataFlowException;
import org.ananth.waroftitans.parser.HTMLParserFunction;
import org.ananth.waroftitans.util.PairObject;
import org.ananth.waroftitans.util.TweetStringUtil;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CleanXmlAnnotator;
import edu.stanford.nlp.util.StringUtils;

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
    
    
    private List<String> tweetTextBag = null;
    private List<String> stopWordBag = null;
        
    
    private TweetTextBuilder(final List<String> tweetTextList) {
        this.tweetTextBag = tweetTextList;
        this.stopWordBag = Lists.newLinkedList();
    }
    
    
    public static TweetTextBuilder on(final String text, final URLEntity[] entities) {
        
        if(Strings.isNullOrEmpty(text)) {
            throw new SentimentDataFlowException("Tweet Text can't be empty or null in the tweet text builder");
        }
        
        
        
        if(entities == null || entities.length == 0) {   
        
        
            return new TweetTextBuilder( Lists.newLinkedList( 
                                                            Splitter.on(" ")
                                                            .trimResults()
                                                            .omitEmptyStrings()
                                                            .split(TweetStringUtil.textCleanUp(text))));
        }
        
        
        final String urlToParse = entities[0].getExpandedURL();
        final HTMLParserFunction               htmlParserFunction   = new HTMLParserFunction();
        final String title = htmlParserFunction.apply(urlToParse);
        
        
        if(Strings.isNullOrEmpty(title)) {
            
            return new TweetTextBuilder( Lists.newLinkedList( 
                                                            Splitter.on(" ")
                                                            .trimResults()
                                                            .omitEmptyStrings()
                                                            .split(TweetStringUtil.textCleanUp(text))));
            
            
            
        }
        
        
        return new TweetTextBuilder( Lists.newLinkedList( 
                                                        Splitter.on(" ")
                                                        .trimResults()
                                                        .omitEmptyStrings()
                                                        .split(TweetStringUtil.textCleanUp(title))));
                        
                       
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
    
    
    public TweetTextBuilder removeHashWords(final HashtagEntity[] entities) {
        if(entities == null || entities.length == 0) {
            return this;
        }
        
        for(HashtagEntity entity : entities) {
            this.stopWordBag.add("#" + entity.getText());
        }        
        
        return this;
    }
    
    
    public TweetTextBuilder removeUsersWithTweet(final UserMentionEntity[] entities) {
        
        if(entities == null || entities.length == 0) {
            return this;
        }
        
        for(UserMentionEntity entity : entities) {
            this.stopWordBag.add("@" + entity.getScreenName());
        }
        
        return this;
        
    }
    
    private List<String> removeEntity(final List<String> transformList) {
        final EntityTransformFunction function = new EntityTransformFunction();
        final List<String> entityTransformList = function.apply(transformList);
        return entityTransformList;
    }
    
    
    public String build() {
        
        final TweetStopWordTransformerFunction function = new TweetStopWordTransformerFunction();
        final List<String> transformList = function.apply(PairObject.of(this.tweetTextBag, this.stopWordBag));        
        return StringUtils.join(TweetStringUtil.cleanupDuplicateElements(this.removeEntity(transformList))).toLowerCase();

    }


    public List<String> getTweetTextList () {
        return tweetTextBag;
    }


    public void setTweetTextList (final List<String> tweetTextList) {
        this.tweetTextBag = tweetTextList;
    }


    public List<String> getStopWordBagList () {
        return stopWordBag;
    }


    public void setStopWordBagList (final List<String> stopWordBagList) {
        this.stopWordBag = stopWordBagList;
    }
 

}
