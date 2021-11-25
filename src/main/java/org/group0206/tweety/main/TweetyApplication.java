package org.group0206.tweety.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

public class TweetyApplication extends Application {

    public static Stage primaryStage;
    public static TweetyUIController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadKeys();
        TweetyApplication.primaryStage = primaryStage;
        Scene scene = new Scene(controller = new TweetyUIController());
        scene.getStylesheets().add("style.css");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Tweety");
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void loadKeys() throws IOException {
        File f = new File(System.getProperty("user.home") + "/.tweety");
        if (!f.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write("");
            bw.close();
        } else {
            BufferedReader br = new BufferedReader(new FileReader(f));
            Keys.CONSUMER_KEY = br.readLine();
            Keys.SECRET_CONSUMER_KEY = br.readLine();
            Keys.ACCESS_TOKEN = br.readLine();
            Keys.SECRET_ACCESS_TOKEN = br.readLine();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
