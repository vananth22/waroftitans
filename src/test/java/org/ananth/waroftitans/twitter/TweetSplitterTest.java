/**
 * 
 */
package org.ananth.waroftitans.twitter;


import java.util.Set;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

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

}
