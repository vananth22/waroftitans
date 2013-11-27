package org.ananth.waroftitans.twitter;

import java.util.List;


import org.ananth.waroftitans.config.TwitterConfig;
import org.ananth.waroftitans.constants.TweetConstants;

import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterMain {
	
	
	
	
	
	private final static TweetProcessorFunction tweetFunction = new TweetProcessorFunction();
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(Boolean.TRUE).setOAuthConsumerKey(TwitterConfig.TWITTER_OAUTH_CONSUMER_KEY)
										.setOAuthConsumerSecret(TwitterConfig.TWITTER_OAUTH_CONSUMER_SECRET)
										.setOAuthAccessToken(TwitterConfig.TWITTER_ACCESS_TOKEN)
										.setOAuthAccessTokenSecret(TwitterConfig.TWITTER_TOKEN_SECRET);
		
		
		TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
       
        Query rahulQuery = new Query(TweetConstants.RAHUL + " -filter:retweets -filter:links -filter:replies -filter:images");    
        
        Query modiQuery = new Query(TweetConstants.MODI + " -filter:retweets -filter:links -filter:replies -filter:images");   
        
        
        tweetFunction.setTwitter(twitter);
        
        List<org.ananth.waroftitans.analyser.SentimentBean> rahulSentimentList = tweetFunction.apply(rahulQuery);
        
        List<org.ananth.waroftitans.analyser.SentimentBean> modiSentimentList = tweetFunction.apply(modiQuery);
        
        
        System.out.println(rahulSentimentList);
        
        
        System.out.println(modiSentimentList);
        
        
        
       
        

	}
	
	
	
	
	
	

}
