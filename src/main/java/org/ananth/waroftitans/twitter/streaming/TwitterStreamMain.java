package org.ananth.waroftitans.twitter.streaming;

public class TwitterStreamMain {

    /**
     * @param args
     */
    public static void main (
                             String[] args) {
        
        
        TwitterStreamingProcessorFunction twitterStreamingProcessorFunction = new TwitterStreamingProcessorFunction();
        try {
            twitterStreamingProcessorFunction.call();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

}
