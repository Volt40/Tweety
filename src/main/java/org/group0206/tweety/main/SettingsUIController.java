package org.group0206.tweety.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;

public class SettingsUIController extends AnchorPane {

    // So this can close itself.
    private Stage thisStage;

    @FXML
    private TextField consumerKey;

    @FXML
    private TextField secretConsumerKey;

    @FXML
    private TextField accessToken;

    @FXML
    private TextField secretAccessToken;

    public SettingsUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("layouts/settings.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(getClass().getClassLoader().getResource("keys.txt").getFile())));
            consumerKey.setText(br.readLine());
            secretConsumerKey.setText(br.readLine());
            accessToken.setText(br.readLine());
            secretAccessToken.setText(br.readLine());
        } catch (IOException e) {
            // Should never happen
        }
    }

    public void setStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    @FXML
    void onDone(ActionEvent event) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("keys.txt"));
            bw.write(consumerKey.getText());
            bw.write(secretConsumerKey.getText());
            bw.write(accessToken.getText());
            bw.write(secretAccessToken.getText());
        } catch (IOException e) {
            // Shouldn't happen.
        }
        thisStage.close();
    }

    @FXML
    void onCancel(ActionEvent event) {
        thisStage.close();
    }

}
