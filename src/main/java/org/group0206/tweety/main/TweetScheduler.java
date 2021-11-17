package org.group0206.tweety.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TweetScheduler {

    private final Tweet tweet;
    private final int minutes;

    private ScheduledExecutorService scheduler;

    public TweetScheduler(Tweet tweet, int minutes) {
        this.tweet = tweet;
        this.minutes = minutes;
    }

    public void schedule() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> ready(), minutes, TimeUnit.MINUTES);
    }

    private void ready() {
        TweetyApplication.controller.pushTweet(tweet);
        scheduler.shutdown();
    }

}
