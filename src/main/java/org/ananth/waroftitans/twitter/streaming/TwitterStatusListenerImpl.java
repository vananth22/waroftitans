package org.ananth.waroftitans.twitter.streaming;

import java.sql.Timestamp;

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
    private final TweetTextBuilder                 tweetTextBuilder     = new TweetTextBuilder();
    

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
        
        
        if (!status.getText().contains(TweetConstants.RAHUL) && !status.getText().contains(TweetConstants.MODI)) {
            // No competitors in the tweet. status = 0
            tweet.setStatus(TweetConstants.NO_COMPETITORS_IN_TWEET);
            tweet.setActor(null);
        }

        if (status.getText().contains(TweetConstants.RAHUL) && status.getText().contains(TweetConstants.MODI)) {
            // Both competitors in the tweet. status = 1
            tweet.setStatus(TweetConstants.BOTH_COMPETITORS_IN_TWEET);
            tweet.setActor(null);
        }
        else {
            // all fine. only one competitor in the tweet
            tweet.setStatus(TweetConstants.ONLY_ONE_COMPETITORS_IN_TWEET);
            if (status.getText().contains(TweetConstants.RAHUL)) {
                tweet.setActor(TweetConstants.RAHUL);
            }
            else {
                tweet.setActor(TweetConstants.MODI);
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
        SentimentBean tweetWithSentiment = sentimentFunction.apply(this.tweetTextBuilder
                                                                        .setTweetStatus(status)
                                                                        .setURLToParse(tweet.getUrl())
                                                                        .build());
        tweet.setScore(tweetWithSentiment.getSentimentScore());

        

        tweetMutationHandler.saveTweet(tweet);

        System.out.println("Added in to log::" + tweet.toString());

    }

    @Override
    public void onTrackLimitationNotice (
                                         int arg0) {
        // TODO Auto-generated method stub

    }

}
