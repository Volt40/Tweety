package org.group0206.tweety.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TweetyUIController extends AnchorPane {

    private boolean postMedia;
    private boolean schedule;

    private String pathToMedia;

    private double[] offset;

    // Used for sending tweets later.
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @FXML
    private TextArea tweetQueue;

    @FXML
    private Button browseButton;

    @FXML
    private TextArea tweetTextArea;

    @FXML
    private TextField hoursPrompt;

    @FXML
    private TextField minsPrompt;

    @FXML
    private CheckBox mediaCheckBox;

    @FXML
    private CheckBox scheduleCheckBox;

    public TweetyUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layouts/tweety.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        postMedia = false;
        schedule = false;

        offset = new double[] {0, 0};
    }

    private void addToQueue(String str) {
        tweetQueue.setText(tweetQueue.getText() + str + "\n");
    }

    @FXML
    void onSettings(ActionEvent event) {
        // TODO
    }

    @FXML
    void onSubmit(ActionEvent event) {
        String tweetMessage = tweetTextArea.getText();
        Tweet tweet = null;
        if (postMedia)
            tweet = new Tweet(tweetMessage, pathToMedia);
        else
            tweet = new Tweet(tweetMessage);
        if (schedule) {
            if (hoursPrompt.getText() == "")
                hoursPrompt.setText("0");
            if (minsPrompt.getText() == "")
                minsPrompt.setText("0");
            try {
                int timeInMinutes = (Integer.parseInt(hoursPrompt.getText()) * 60) + Integer.parseInt(minsPrompt.getText());
                new TweetScheduler(tweet, timeInMinutes).schedule();
                addToQueue("Tweet scheduled to be sent in " + timeInMinutes + " minute(s): \"" + tweet.getMessage() + "\"");
            } catch (Exception e) {
                addToQueue("Something went wrong.");
            }
        } else
            pushTweet(tweet);
        reset();
    }

    /**
     * Sends the tweet to twitter w/ the python api.
     * @param tweet Tweet to be sent.
     */
    public void pushTweet(Tweet tweet) {
        String pathToScript = TweetyUIController.class.getClassLoader().getResource("scripts/send_tweet.py").getPath();
        if (tweet.hasMedia()) {
            // TODO: run python scrypt to post media.
            try {
                Runtime.getRuntime().exec("python " + pathToScript + " " + tweet.getPathToMedia() + " " + tweet.getMessage());
            } catch (IOException e) {
                addToQueue("Something went wrong.");
            }
            addToQueue("Tweet sent (with media): \"" + tweet.getMessage() + "\"");
        } else {
            // TODO: run python scrypt to send tweet.
            try {
                Runtime.getRuntime().exec("python " + pathToScript + " \"" + tweet.getMessage() + "\"");
                System.out.println("python " + pathToScript + " \"" + tweet.getMessage() + "\"");
            } catch (IOException e) {
                addToQueue("Something went wrong.");
            }
            addToQueue("Tweet sent: \"" + tweet.getMessage() + "\"");
        }
    }

    @FXML
    void onBrowse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse Media");
        File f = fileChooser.showOpenDialog(TweetyApplication.primaryStage);
        pathToMedia = f.getPath();
        browseButton.setText(pathToMedia);
    }

    @FXML
    void onPostMediaCheck(ActionEvent event) {
        postMedia = !postMedia;
        if (postMedia)
            browseButton.setDisable(false);
        else {
            browseButton.setDisable(true);
            browseButton.setText("Browse");
        }
    }

    @FXML
    void onScheduleCheck(ActionEvent event) {
        schedule = !schedule;
        if (schedule) {
            hoursPrompt.setDisable(false);
            minsPrompt.setDisable(false);
        } else {
            hoursPrompt.clear();
            minsPrompt.clear();
            hoursPrompt.setDisable(true);
            minsPrompt.setDisable(true);
        }
    }

    @FXML
    void onDragBarPressed(MouseEvent event) {
        offset[0] = event.getSceneX();
        offset[1] = event.getSceneY();
    }

    @FXML
    void OnDragBarDragged(MouseEvent event) {
        TweetyApplication.primaryStage.setX(event.getScreenX() - offset[0]);
        TweetyApplication.primaryStage.setY(event.getScreenY() - offset[1]);
    }

    @FXML
    void onCloseDragged(MouseEvent event) {
        event.consume();
    }

    @FXML
    void onClosePressed(MouseEvent event) {
        event.consume();
    }

    @FXML
    void onSettings(MouseEvent event) {
        System.out.println("bruh");
        Stage settingsStage = new Stage();
        SettingsUIController settings = new SettingsUIController();
        settingsStage.setScene(new Scene(settings));
        settings.setStage(settingsStage);
        settingsStage.show();
    }

    @FXML
    void onClose(MouseEvent event) {
        Platform.exit();
    }

    private void reset() {
        tweetTextArea.clear();
        mediaCheckBox.selectedProperty().set(false);
        scheduleCheckBox.selectedProperty().set(false);
        hoursPrompt.clear();
        hoursPrompt.setDisable(true);
        minsPrompt.clear();
        minsPrompt.setDisable(true);
        browseButton.setText("Browse");
        browseButton.setDisable(true);
        postMedia = false;
        schedule = false;
    }

}
