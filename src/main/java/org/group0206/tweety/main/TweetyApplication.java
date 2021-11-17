package org.group0206.tweety.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TweetyApplication extends Application {

    public static Stage primaryStage;
    public static TweetyUIController controller;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TweetyApplication.primaryStage = primaryStage;
        Scene scene = new Scene(controller = new TweetyUIController());
        scene.getStylesheets().add("style.css");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Tweety");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
