package org.group0206.tweety.main;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class TweetyUIController extends AnchorPane {

    private boolean postMedia;
    private boolean schedule;

    private String pathToMedia;

    private double[] offset;

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

    public TweetyUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Tweety.fxml"));
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
    void onSubmit(ActionEvent event) {
        String tweet = tweetTextArea.getText();
        if (schedule) {
            // TODO: figure this out
        } else
            pushTweet(tweet);
    }

    /**
     * Sends the tweet to twitter w/ the python api.
     * @param tweet Tweet to be sent.
     */
    private void pushTweet(String tweet) {
        if (postMedia) {
            // TODO: run python scrypt to post media.
            addToQueue("Tweet sent (with media): \"" + tweet + "\"");
        } else {
            // TODO: run python scrypt to send tweet.
            addToQueue("Tweet sent: \"" + tweet + "\"");
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
    void onClose(MouseEvent event) {
        Platform.exit();
    }

}
