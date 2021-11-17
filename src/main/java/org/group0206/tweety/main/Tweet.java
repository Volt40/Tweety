package org.group0206.tweety.main;

/**
 * Represents a tweet.
 */
public class Tweet {

    private String message;
    private String pathToMedia;
    private boolean hasMedia;

    /**
     * Makes a tweet with the message.
     * @param message Message to send.
     */
    public Tweet(String message) {
        this(message, null);
        hasMedia = false;
    }

    /**
     * Makes a tweet with the message and the media.
     * @param message Message to send.
     * @param pathToMedia Media to send.
     */
    public Tweet(String message, String pathToMedia) {
        this.message = message;
        this.pathToMedia = pathToMedia;
        hasMedia = true;
    }

    public String getMessage() {
        return message;
    }

    public String getPathToMedia() {
        return pathToMedia;
    }

    public boolean hasMedia() {
        return hasMedia;
    }
}
