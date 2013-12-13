/**
 * 
 */
package org.ananth.waroftitans.analyser;

import org.ananth.waroftitans.analyser.SentimentAnalyzerFunction;
import org.ananth.waroftitans.analyser.SentimentBean;
import org.junit.Test;

/**
 * Test cases for Sentiment Analyser Function
 * @author Ananth
 *
 */
public class SentimentAnalyzerFunctionTest {
    
    
    private final static String withEntity = "Narandera Modi you are great sir";
    private final static String withOutEntity = "you are great sir";
    
    private final static String WITHCAPS = "He HAS PROVED HIMSELF OVER AND AGAIN LOOK AT WE WANT THE SAME DEVELOPMENT FOR INDIA";
    private final static String WITHLOWER = "He has proved himself over and again look at we want the same development for india";
    
    private final static String MULTIPLE_ENTITY = "He is a joker with dirty mouth: he ||he appointed him to the for his literary";
    
    
    
    private final SentimentAnalyzerFunction function = new SentimentAnalyzerFunction();
    
    
    @Test
    public void testSentimentScoreWithEntity() {
        SentimentBean bean = function.apply(withEntity);
        System.out.println(bean);
        
    }
    
    
    @Test
    public void testSentimentScore() {
        SentimentBean bean = function.apply(withOutEntity);
        System.out.println(bean);
    }
    
    
    @Test
    public void testSentimentWithCaps() {
        SentimentBean bean = function.apply(WITHCAPS);
        System.out.println(bean);
    }
    
    
    @Test
    public void testSentimentWithLower() {
        SentimentBean bean = function.apply(WITHLOWER);
        System.out.println(bean);
    }
    
    
    @Test
    public void testSentimentWithMultipleEntity() {
        SentimentBean bean = function.apply(MULTIPLE_ENTITY);
        System.out.println(bean);
    }
    
    

}
