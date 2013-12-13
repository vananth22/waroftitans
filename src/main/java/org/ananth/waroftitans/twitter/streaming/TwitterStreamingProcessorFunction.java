package org.ananth.waroftitans.twitter.streaming;

import java.util.concurrent.Callable;

import org.ananth.waroftitans.config.TwitterConfig;
import org.ananth.waroftitans.constants.TweetConstants;

import twitter4j.FilterQuery;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Call the Twitter Status Listener and keep on dump the twitter stream in to the database
 * @author Ananth
 *
 */



public class TwitterStreamingProcessorFunction implements Callable<String>{
    
    
    
    private final TwitterStatusListenerImpl twitterStatusListenerImpl = new TwitterStatusListenerImpl();

    @Override
    public String call ()
                         throws Exception {
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(Boolean.TRUE).setOAuthConsumerKey(TwitterConfig.TWITTER_OAUTH_CONSUMER_KEY)
                                        .setOAuthConsumerSecret(TwitterConfig.TWITTER_OAUTH_CONSUMER_SECRET)
                                        .setOAuthAccessToken(TwitterConfig.TWITTER_ACCESS_TOKEN)
                                        .setOAuthAccessTokenSecret(TwitterConfig.TWITTER_TOKEN_SECRET);
        cb.setJSONStoreEnabled(true);
        
        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        twitterStream.addListener(twitterStatusListenerImpl);
        
        twitterStream.filter(this.getFilterQueryBuilder());
        return "success";
    }
    
    
    
    private FilterQuery getFilterQueryBuilder() {
        String track[] =  new String[1];
        track[0] =   TweetConstants.RAHUL + "," + TweetConstants.MODI;        
        FilterQuery query = new FilterQuery(0,null,track);
        return query;
        
        
    }

}
