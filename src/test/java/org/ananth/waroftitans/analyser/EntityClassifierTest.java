/**
 * 
 */
package org.ananth.waroftitans.analyser;

import java.util.List;

import org.junit.Test;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * @author Ananth
 *
 */
public class EntityClassifierTest {
    
    
    @Test
    public void testCommonLetters() {
        final String commonWord = "India is my country";
        this.testClassify(commonWord);
    }
    
    
    @Test
    public void testActors() {
        final String commonWord = "I don't think what people talking about, but Modi making some good progress";
        this.testClassify(commonWord);
    }
    
    
    @Test
    public void testAmbiguity() {
        final String commonWord = "Harry Potter and the Deathly Hallows is my favorite";
        this.testClassify(commonWord);
    }
    
   
    public void testClassify(final String strWord) {
        
        List<List<CoreLabel>> classifyList = EntityClassifier.getInstance().getCRFClassifier().classify(strWord);        
        for (List<CoreLabel> coreLabels : classifyList) {
            for (CoreLabel coreLabel : coreLabels) {
                System.out.println(coreLabel.word());
                System.out.println(coreLabel.get(CoreAnnotations.AnswerAnnotation.class));
            }
        }
        
    }
    

}
