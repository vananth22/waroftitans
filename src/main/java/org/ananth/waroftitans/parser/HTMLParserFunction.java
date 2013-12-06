/**
 * 
 */
package org.ananth.waroftitans.parser;

import java.io.IOException;

import java.net.URL;

import org.ananth.waroftitans.exception.SentimentDataFlowException;
import org.xml.sax.SAXException;

import com.google.common.base.Function;

import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.document.TextDocument;
import de.l3s.boilerpipe.sax.BoilerpipeSAXInput;
import de.l3s.boilerpipe.sax.HTMLDocument;
import de.l3s.boilerpipe.sax.HTMLFetcher;

/**
 * @author Ananth
 */
public final class HTMLParserFunction implements Function<String, String> {

    @Override
    public String apply (
                         String urlInput) {

        HTMLDocument htmlDoc;
        String articleTitle = null;
        try {
            htmlDoc = HTMLFetcher.fetch(new URL(urlInput));
            final TextDocument doc = new BoilerpipeSAXInput(htmlDoc.toInputSource()).getTextDocument();
            articleTitle = doc.getTitle();

        }
        catch (BoilerpipeProcessingException | SAXException | IOException e) {
            throw new SentimentDataFlowException("Error in HTML Parsing", e);
           
        }

        return articleTitle;
    }

}
