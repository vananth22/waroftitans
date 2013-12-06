/**
 * 
 */
package org.ananth.waroftitans.connection;

import org.ananth.waroftitans.parser.HTMLParserFunction;
import org.junit.Test;

/**
 * @author Ananth
 *
 */
public class HTMLParserFunctionTest {
    
    @Test
    public void testArticle() {
        HTMLParserFunction parserFunction = new HTMLParserFunction();
        String text = parserFunction.apply("https://www.openshift.com/blogs/day-18-boilerpipe-article-extraction-for-java-developers");
        System.out.println(text);
        
        
    }   
    

}
