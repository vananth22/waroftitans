/**
 * 
 */
package org.ananth.waroftitans.analyser;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * @author Ananth
 */
public class EntityClassifier {

    /**
     * Make it Singleton
     */

    private EntityClassifier() {
    };

    private static class SingletonHelper {
        private static final EntityClassifier   entity     = new EntityClassifier();
        private static CRFClassifier<CoreLabel> classifier = null;

        static {
            if (classifier == null) {
                final String serializedClassifier = "edu/stanford/nlp/models/ner/english.all.3class.distsim.crf.ser.gz";
                classifier = CRFClassifier.getClassifierNoExceptions(serializedClassifier);
            }
        }
    }

    public static EntityClassifier getInstance () {
        return SingletonHelper.entity;
    }

    public CRFClassifier<CoreLabel> getCRFClassifier () {
        return SingletonHelper.classifier;

    }

}
