package org.ananth.waroftitans.twitter.streaming;

import java.sql.Timestamp;
import java.util.Locale;

import org.ananth.waroftitans.analyser.SentimentAnalyzerFunction;
import org.ananth.waroftitans.analyser.SentimentBean;
import org.ananth.waroftitans.constants.TweetConstants;
import org.ananth.waroftitans.domain.TweetMutationHandler;
import org.ananth.waroftitans.parser.HTMLParserFunction;
import org.ananth.waroftitans.persistence.Tweet;
import org.ananth.waroftitans.twitter.TweetTextBuilder;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.json.DataObjectFactory;

/**
 * @author Ananth
 */

public class TwitterStatusListenerImpl implements StatusListener {

    private final static SentimentAnalyzerFunction sentimentFunction    = new SentimentAnalyzerFunction();
    private final TweetMutationHandler             tweetMutationHandler = new TweetMutationHandler();
    

    @Override
    public void onException (
                             Exception arg0) {

    }

    @Override
    public void onDeletionNotice (
                                  StatusDeletionNotice arg0) {

    }

    @Override
    public void onScrubGeo (
                            long arg0,
                            long arg1) {

    }

    @Override
    public void onStallWarning (
                                StallWarning arg0) {

    }

    @Override
    public void onStatus (
                          Status status) {

        Tweet tweet = new Tweet();

        if (!status.getText().toUpperCase().contains(TweetConstants.RAHUL.toUpperCase()) && !status.getText().toUpperCase().contains(TweetConstants.MODI.toUpperCase())) {
            // No competitors in the tweet. status = 0
            tweet.setStatus(TweetConstants.NO_COMPETITORS_IN_TWEET);
            tweet.setActor(null);
        }

        if (status.getText().toUpperCase().contains(TweetConstants.RAHUL.toUpperCase()) && status.getText().toUpperCase().contains(TweetConstants.MODI.toUpperCase())) {
            // Both competitors in the tweet. status = 1
            tweet.setStatus(TweetConstants.BOTH_COMPETITORS_IN_TWEET);
            tweet.setActor(null);
        }
        else {
            // all fine. only one competitor in the tweet

            if (status.getText().toUpperCase().contains(TweetConstants.RAHUL.toUpperCase())) {
                tweet.setStatus(TweetConstants.ONLY_ONE_COMPETITORS_IN_TWEET);
                tweet.setActor(TweetConstants.RAHUL);
            }
            else if (status.getText().toUpperCase().contains(TweetConstants.MODI.toUpperCase())) {
                tweet.setStatus(TweetConstants.ONLY_ONE_COMPETITORS_IN_TWEET);
                tweet.setActor(TweetConstants.MODI);
            }
            else {
                tweet.setStatus(TweetConstants.NO_COMPETITORS_IN_TWEET);
                tweet.setActor(null);
            }

        }

        tweet.setTweetId(status.getId());
        tweet.setCreatedAt(new Timestamp(status.getCreatedAt().getTime()));
        tweet.setText(status.getText());
        tweet.setUserId(status.getUser().getName());

        if (status.getGeoLocation() != null) {

            tweet.setLattitude(status.getGeoLocation().getLatitude());
            tweet.setLongitude(status.getGeoLocation().getLongitude());
        }

        if (status.getURLEntities() != null && status.getURLEntities().length > 0) {
            tweet.setUrl(status.getURLEntities()[0].getExpandedURL());
        }

        tweet.setRawText(DataObjectFactory.getRawJSON(status));
        SentimentBean tweetWithSentiment = sentimentFunction.apply(TweetTextBuilder.on(status.getText(), status.getURLEntities())
                                                                                    .removeUsersWithTweet(status.getUserMentionEntities())
                                                                                    .removeHashWords(status.getHashtagEntities())
                                                                                    .removeURL(status.getURLEntities())                                                                                   
                                                                                    .build());
        tweet.setScore(tweetWithSentiment.getSentimentScore());
        tweet.setSentimentText(tweetWithSentiment.getText());

        tweetMutationHandler.saveTweet(tweet);

        System.out.println("Added in to log::" + tweet.toString());

    }

    @Override
    public void onTrackLimitationNotice (
                                         int arg0) {
        // TODO Auto-generated method stub

    }

}
