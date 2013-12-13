/**
 * 
 */
package org.ananth.waroftitans.twitter;


import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import edu.stanford.nlp.util.StringUtils;

/**
 * @author Ananth
 *
 */
public class TweetSplitterTest {
    
    
    @Test  
    public void testSplit() {
        String text = "RT @: # RT @: Sonia Gandhi isn't just Rahul's mother,but our mother too.She's entire nation's mother";
        Iterable<String> textIterator = Splitter.on(" ").trimResults().split(text);
        
        for(String s : textIterator) {
            System.out.println(s);
        }
        
        
        Set<String> removeWords = Sets.newHashSet();
        removeWords.add("RT");
        
        Set<String> finalResult = Sets.difference(Sets.newLinkedHashSet(textIterator), removeWords);  
      
        
        System.out.println(finalResult);
        
        
        
    }
    
    @Test
    public void testStringUtilJoin() {
        
        final List<String> sampleSet = Lists.newLinkedList();
        sampleSet.add("he");
        sampleSet.add("he");
        sampleSet.add("doing");
        sampleSet.add("it");
        sampleSet.add("good");
        sampleSet.add("it");
        sampleSet.add("it");
        String lastElement = null;
        for(String text : sampleSet) {            
            if(!text.equalsIgnoreCase(lastElement)) {
                System.out.println(text);
            }
            
            lastElement = text;
            
            
           
        }
        
        
        
    }

}
