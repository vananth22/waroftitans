/**
 * 
 */
package org.ananth.waroftitans;


import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ananth.waroftitans.config.TwitterConfig;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
/**
 * @author Ananth
 *
 */
public class RaviTweetStream implements StatusListener{
    
    private static final String REGEX = "([^ ]*)\t([^ ]*)\t([^ ]*)\t([^ ]*)\t([^\\S]+)\t([^\\S]*)";
    // private static final String REGEX = new String("([^ ]*) ([^ ]*) ([^ ]*) (-|\\[[^\\]]*\\]) \"([^ ]+) ([^ ]+) ([^\"]+)\" (-|[0-9]*) (-|[0-9]*)(?: ([^ \"]*|\"[^\"]*\") ([^ \"]*|\"[^\"]*\"))?");
     static Pattern pattern = null;
  
     public static void main (String args[]) {
         
         RaviTweetStream test = new RaviTweetStream ();
         pattern = Pattern.compile(REGEX);
         test.startStream();
         
     }
     
     public void startStream () {

         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(Boolean.TRUE).setOAuthConsumerKey(TwitterConfig.TWITTER_OAUTH_CONSUMER_KEY)
                                         .setOAuthConsumerSecret(TwitterConfig.TWITTER_OAUTH_CONSUMER_SECRET)
                                         .setOAuthAccessToken(TwitterConfig.TWITTER_ACCESS_TOKEN)
                                         .setOAuthAccessTokenSecret(TwitterConfig.TWITTER_TOKEN_SECRET);
         cb.setJSONStoreEnabled(true);
         
         TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
         twitterStream.addListener(this);
         FilterQuery query = new FilterQuery()
                 .track(new String[] {"hadoop", "big data", "analytics", "bigdata", "cloudera"}); 
          //twitterStream.filter(query);
         twitterStream.sample();
         
     }

     @Override
     public void onException(Exception arg0) {
         // TODO Auto-generated method stub
         
     }

     @Override
     public void onDeletionNotice(StatusDeletionNotice arg0) {
         // TODO Auto-generated method stub
         
     }

     @Override
     public void onScrubGeo(long arg0, long arg1) {
         // TODO Auto-generated method stub
         
     }

     @Override
     public void onStallWarning(StallWarning arg0) {
         // TODO Auto-generated method stub
         
     }

     @Override
     public void onStatus(Status status) {
         RaviTweet tweet = new RaviTweet ();
         tweet.setId(status.getId());
         tweet.setUserId(status.getUser().getId());
         tweet.setLattitude(status.getGeoLocation().getLatitude());
         tweet.setLongitude(status.getGeoLocation().getLongitude());
         tweet.setText(status.getText());
         tweet.setCreatedAt(new Timestamp(status.getCreatedAt().getTime()));
         System.out.println(" the tweet is " + tweet.toString());
         String payload = tweet.toString();
         Matcher m = pattern.matcher(payload);
         
         if (!m.matches()) {
            System.out.println("payload doenst match the regex");
         }
           
         System.out.println(String.format(" first is [%s] ,second is [%s] and fourth is [%s] ",m.group(1),m.group(2),m.group(4)));
     }

     @Override
     public void onTrackLimitationNotice(int arg0) {
         // TODO Auto-generated method stub
         
     }

}
