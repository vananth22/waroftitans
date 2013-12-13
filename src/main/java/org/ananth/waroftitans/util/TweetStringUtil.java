/**
 * 
 */
package org.ananth.waroftitans.util;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * String utility method for Tweet Processing
 * 
 * @author Ananth
 */
public final class TweetStringUtil {

    
    
    /**
     * General text cleanup
     * @param text
     * @return
     */
    public static String textCleanUp (
                                      final String text) {
        if (Strings.isNullOrEmpty(text)) {
            return text;
        }
        final String cleanText = text.replaceAll("\\n", "")
                        .replaceAll("\\r", "")
                        .replaceAll("||", "");
        return cleanText;

    }
    
    
    /**
     * Utility method to remove the duplicate entry caused by Entity Detection
     * @param tweetTextBag
     * @return
     */
    public static List<String> cleanupDuplicateElements (
                                                         final List<String> tweetTextBag) {

        final List<String> cleanupTweetBag = Lists.newLinkedList();

        String lastElement = null;
        for (String text : tweetTextBag) {
            if (!text.equalsIgnoreCase(lastElement)) {
                cleanupTweetBag.add(text);
            }

            lastElement = text;

        }

        return cleanupTweetBag;

    }

}
