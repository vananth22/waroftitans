package org.ananth.waroftitans.twitter;

import java.util.ArrayList;
import java.util.List;

import org.ananth.waroftitans.analyser.SentimentAnalyzerFunction;
import org.ananth.waroftitans.analyser.SentimentBean;
import org.ananth.waroftitans.exception.SentimentDataFlowException;



import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.google.common.base.Function;

/**
 * 
 * @author Ananth
 * 
 */

public class TweetProcessorFunction implements
		Function<Query, List<SentimentBean>> {

	private final static SentimentAnalyzerFunction sentimentFunction = new SentimentAnalyzerFunction();

	private Twitter twitter;

	@Override
	public List<SentimentBean> apply(final Query query) {

		if (query == null) {
			throw new SentimentDataFlowException("Query Object can't be null here");
		}

		query.setCount(20);
		query.setLocale("en");
		query.setLang("en");

		final List<SentimentBean> sentimentList = new ArrayList<>();

		try {

			if (this.twitter == null) {
				throw new SentimentDataFlowException(
						"Twitter Object can't be empty here");
			}

			final QueryResult queryResult = this.twitter.search(query);

			final List<Status> statusList = queryResult.getTweets();
			for (Status status : statusList) {
				SentimentBean bean = sentimentFunction.apply(status.getText());
				sentimentList.add(bean);
			}

		} catch (TwitterException e) {
			final String message = String.format(
					"Exception caught in sentiment processing for [%s]",
					query.getQuery());
			throw new SentimentDataFlowException(message, e);
		}
		return sentimentList;
	}

	public Twitter getTwitter() {
		return twitter;
	}

	public void setTwitter(Twitter twitter) {
		this.twitter = twitter;
	}

}
