package com.example.collisons;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
public class main extends Application {

        @Override/*www. j ava 2 s  .c o m*/
        public void start(Stage primaryStage) {
            BallPane ballPane = new BallPane();

            HBox buttonPane = new HBox(5);
            Button btLeft = new Button("Left");
            Button btRight = new Button("Right");
            Button btUp = new Button("Up");
            Button btDown = new Button("Down");
            buttonPane.getChildren().addAll(btLeft, btRight, btUp, btDown);

            btLeft.setOnAction(e -> ballPane.moveLeft());
            btRight.setOnAction(e -> ballPane.moveRight());
            btUp.setOnAction(e -> ballPane.moveUp());
            btDown.setOnAction(e -> ballPane.moveDown());

            BorderPane pane = new BorderPane();
            pane.setCenter(ballPane);
            pane.setBottom(buttonPane);
            pane.setPadding(new Insets(0, 20, 0, 20));
            BorderPane.setAlignment(buttonPane, Pos.CENTER);

            Scene scene = new Scene(pane, 250, 250);
            primaryStage.setTitle("E15_03");
            primaryStage.setScene(scene);
            primaryStage.show();
        }



        public static void main(String[] args) {
            launch(args);
        }
    }