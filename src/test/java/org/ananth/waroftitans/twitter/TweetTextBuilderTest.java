/**
 * 
 */
package org.ananth.waroftitans.twitter;

import java.io.IOException;
import java.util.Properties;

import org.ananth.waroftitans.analyser.SentimentAnalyzerFunction;
import org.ananth.waroftitans.analyser.SentimentBean;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.json.DataObjectFactory;

/**
 * @author Ananth
 *
 */
public class TweetTextBuilderTest {
    
    
    
    private static Properties property = null;
    private static final String PROPERTIES_FILE = "/json.properties";
    private  static SentimentAnalyzerFunction function = null;
    private String sentimentText = null;
    
    @BeforeClass
    public static void setup() throws IOException {     
   
        property = new Properties();
        property.load(TweetTextBuilderTest.class.getResourceAsStream(PROPERTIES_FILE));
        function = new SentimentAnalyzerFunction();
        
    }
    
    @Before
    public void testWithOutURL() throws TwitterException {
        final Status status = DataObjectFactory.createStatus(property.getProperty("json.without.url"));
        
       sentimentText = TweetTextBuilder.on(status.getText(), status.getURLEntities())
                                                    .removeUsersWithTweet(status.getUserMentionEntities())
                                                    .removeHashWords(status.getHashtagEntities())
                                                    .removeURL(status.getURLEntities())
                                                    .removeEntity()
                                                    .build();
        
        System.out.println(sentimentText);
    }
    
    @Test
    public void testSentiment() {
        SentimentBean bean = function.apply(sentimentText);
        
        System.out.println(bean);
        
    }
    
    
    
    

}
