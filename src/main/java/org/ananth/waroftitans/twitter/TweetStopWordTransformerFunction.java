/**
 * 
 */
package org.ananth.waroftitans.twitter;

import java.util.List;

import org.ananth.waroftitans.util.PairObject;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

/**
 * @author Ananth
 *
 */
public final class TweetStopWordTransformerFunction implements Function<PairObject<List<String>, List<String>>, List<String>>{

    @Override
    public List<String> apply (
                        PairObject<List<String>, List<String>> pairList) {
        
       final List<String> stopWordsTransformerList = Lists.newLinkedList();
       
       for(String text : pairList.getLeft()) {
           
           if(!pairList.getRight().contains(text)) {
               stopWordsTransformerList.add(text);
           }
           
       }
       
        
       return stopWordsTransformerList;
    }

}
