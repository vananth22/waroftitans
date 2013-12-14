/**
 * 
 */
package org.ananth.waroftitans.analyser;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.StringUtils;

/**
 * @author Ananth
 *
 */
public final class EntityTransformFunction implements Function<List<String> , List<String>>{

    
    public List<String> entityTransformCollection = Lists.newLinkedList();
    
    
    public List<String> apply ( final List<String> tweetTextCollectionWithEntity) {
        
        
        final String entityDetectionText =  StringUtils.join(tweetTextCollectionWithEntity);
        final List<List<CoreLabel>> classifyList = EntityClassifier.getInstance().getCRFClassifier().classify(entityDetectionText); 
        
        for (List<CoreLabel> coreLabels : classifyList) {
            for (CoreLabel coreLabel : coreLabels) {
                final String entityStatus = coreLabel.get(CoreAnnotations.AnswerAnnotation.class);
                if("O".equalsIgnoreCase(entityStatus.trim())) {
                    this.entityTransformCollection.add(coreLabel.word());
                } else if("PERSON".equalsIgnoreCase(entityStatus)) {
                    this.entityTransformCollection.add("he");
                } 
                
             
            }
        }        
       
        return this.entityTransformCollection;
    }

}
