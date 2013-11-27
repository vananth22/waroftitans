package org.ananth.waroftitans.twitter.streaming;

import java.sql.Timestamp;

import org.ananth.waroftitans.analyser.SentimentAnalyzerFunction;
import org.ananth.waroftitans.analyser.SentimentBean;
import org.ananth.waroftitans.constants.TweetConstants;
import org.ananth.waroftitans.domain.TweetMutationHandler;
import org.ananth.waroftitans.persistence.Tweet;

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

        if (!status.getText().contains(TweetConstants.RAHUL) && !status.getText().contains(TweetConstants.MODI)) {
            return;
        }

        if (status.getText().contains(TweetConstants.RAHUL) && status.getText().contains(TweetConstants.MODI)) {
            return;
        }

        Tweet tweet = new Tweet();
        tweet.setTweetId(status.getId());
        tweet.setCreatedAt(new Timestamp(status.getCreatedAt().getTime()));
        tweet.setText(status.getText());
        tweet.setUserId(status.getUser().getName());

        if (status.getGeoLocation() != null) {

            tweet.setLattitude(status.getGeoLocation().getLatitude());
            tweet.setLongitude(status.getGeoLocation().getLongitude());
        }
        tweet.setRawText(DataObjectFactory.getRawJSON(status));
        SentimentBean tweetWithSentiment = sentimentFunction.apply(status.getText());
        tweet.setScore(tweetWithSentiment.getSentimentScore());

        if (status.getURLEntities() != null && status.getURLEntities().length > 0) {
            tweet.setUrl(status.getURLEntities()[0].getExpandedURL());
        }

        if (status.getText().contains(TweetConstants.RAHUL)) {
            tweet.setActor(TweetConstants.RAHUL);
        }
        else {
            tweet.setActor(TweetConstants.MODI);
        }
        
        
        
        tweetMutationHandler.saveTweet(tweet);
        
        System.out.println("Added in to log::" + tweet.toString());

    }

    @Override
    public void onTrackLimitationNotice (
                                         int arg0) {
        // TODO Auto-generated method stub

    }

}
