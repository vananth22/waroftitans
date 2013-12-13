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

}
