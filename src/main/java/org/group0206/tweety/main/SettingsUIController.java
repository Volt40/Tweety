package org.group0206.tweety.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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

    private double[] offset;

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
            BufferedReader br = new BufferedReader(new FileReader(getClass().getClassLoader().getResource("keys.txt").getFile()));
            consumerKey.setText(br.readLine());
            secretConsumerKey.setText(br.readLine());
            accessToken.setText(br.readLine());
            secretAccessToken.setText(br.readLine());
        } catch (IOException e) {
            // Should never happen
        }
        offset = new double[] {0, 0};
    }

    public void setStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    @FXML
    void onDone(ActionEvent event) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/keys.txt"));
            bw.write(consumerKey.getText() + "\n");
            bw.write(secretConsumerKey.getText() + "\n");
            bw.write(accessToken.getText() + "\n");
            bw.write(secretAccessToken.getText());
            bw.close();
        } catch (IOException e) {
            // Shouldn't happen.
        }
        thisStage.close();
    }

    @FXML
    void onCancel(ActionEvent event) {
        thisStage.close();
    }

    @FXML
    void onDragBarPressed(MouseEvent event) {
        offset[0] = event.getSceneX();
        offset[1] = event.getSceneY();
    }

    @FXML
    void onDragBarDragged(MouseEvent event) {
        thisStage.setX(event.getScreenX() - offset[0]);
        thisStage.setY(event.getScreenY() - offset[1]);
    }

}
