package org.ananth.waroftitans.analyser;

import java.util.Properties;

import org.ananth.waroftitans.constants.TweetConstants;



import com.google.common.base.Function;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

/**
 * 
 * @author Ananth
 * 
 */

public class SentimentAnalyzerFunction implements
		Function<String, SentimentBean> {

	private final StanfordCoreNLP pipeline;

	public SentimentAnalyzerFunction() {

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		this.pipeline = new StanfordCoreNLP(props);
		;
	}

	@Override
	public SentimentBean apply(String tweeetText) {

		int mainSentiment = 0;
		if (tweeetText != null && tweeetText.length() > 0) {
			int longest = 0;
			Annotation annotation = this.pipeline.process(tweeetText);
			for (CoreMap sentence : annotation
					.get(CoreAnnotations.SentencesAnnotation.class)) {

				Tree tree = sentence
						.get(SentimentCoreAnnotations.AnnotatedTree.class);

				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);

				String partText = sentence.toString();

				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}

			}
		}

		

		if (mainSentiment > 4 || mainSentiment < 0) {
			return null;
		}
		SentimentBean tweetWithSentiment = new SentimentBean(tweeetText,
				sentimentScaleConverter(mainSentiment), mainSentiment);
		return tweetWithSentiment;
	}

	/**
	 * 
	 * @param sentiment
	 * @return
	 */

	private String sentimentScaleConverter(int sentiment) {
		switch (sentiment) {
		case 0:
			return TweetConstants.NEGATIVE;

		case 1:
			return TweetConstants.NEGATIVE;
		case 2:
			return TweetConstants.MODERATE;
		case 3:
			return TweetConstants.POSITIVE;
		case 4:
			return TweetConstants.POSITIVE;
		default:
			return "";
		}
	}

}
