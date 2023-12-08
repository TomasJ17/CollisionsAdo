package com.example.collisons;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class CollisionMain extends Application {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int MAX_RADIUS = 50;
    public static final int MIN_RADIUS = 10;
    static final double INITIAL_SPEED = 1.0;

    private List<Kruh> circles = new ArrayList<>();
    private Random random = new Random();
    public static Pane root = new Pane();
    public AnchorPane pojebMa = new AnchorPane();
    private final Scene scene = new Scene(root, WIDTH, HEIGHT);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Circle Collision Animation!");
        primaryStage.setScene(scene);
        primaryStage.show();

        pojebMa.setPrefWidth(WIDTH-100);
        pojebMa.setPrefHeight(HEIGHT);
        root.getChildren().add(pojebMa);

        // Animation loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Kruh circle : circles) {
                    circle.move();
                }
                checkCollisions();
            }
        };
        timer.start();

        // Generate initial circles without collisions
        for (int i = 0; i < 10; i++) {
            double radius = random.nextInt(MAX_RADIUS - MIN_RADIUS + 1) + MIN_RADIUS;
            double x, y;
            boolean collision;
            do {
                collision = false;
                x = random.nextDouble() * (WIDTH - 2 * radius) + radius;
                y = random.nextDouble() * (HEIGHT - 2 * radius) + radius;

                // Check for collisions with existing circles
                for (Kruh existingCircle : circles) {
                    double distance = Math.sqrt(Math.pow(x - existingCircle.getX(), 2) + Math.pow(y - existingCircle.getY(), 2));
                    if (distance < radius + existingCircle.getRadius()) {
                        collision = true;
                        break;
                    }
                }
            } while (collision);

            Color color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            Kruh circle = new Kruh(x, y, radius, color);
            circles.add(circle);
            pojebMa.getChildren().add(circle.getCircle());
        }
    }

    private void checkCollisions() {
        for (int i = 0; i < circles.size(); i++) {
            Kruh circle1 = circles.get(i);
            for (int j = i ; j < circles.size(); j++) {
                Kruh circle2 = circles.get(j);
                if (circle1!=circle2){
                    if (circle1.collidesWith(circle2)) {
                        resolveCollision(circle1, circle2);}
                }
            }
        }
    }

    private void resolveCollision(Kruh circle1, Kruh circle2) {
        double dx = circle2.getX() - circle1.getX();
        double dy = circle2.getY() - circle1.getY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < circle1.getRadius() + circle2.getRadius()) {
            if (circle1.getRadius() == circle2.getRadius()) {
                // If two circles of the same size collide, merge them into one larger circle
                Kruh mergedCircle = null;
                mergedCircle = Kruh.mergeWith(circle1,circle2);
                circles.remove(circle1);
                circles.remove(circle2);
                pojebMa.getChildren().removeAll(circle1.getCircle(), circle2.getCircle());
                circles.add(mergedCircle);
                pojebMa.getChildren().add(mergedCircle.getCircle());
            } else if (circle1.canSplit() && circle2.canSplit()) {
                // If two circles of different sizes collide and both can split, split them

                //dokoncit zistit vacsi Kruh ten splitnut a mensi kruh zvacsit

               // if (){}
                List<Kruh> newCircles1 = circle1.split(circles);
                List<Kruh> newCircles2 = circle2.split(circles);

                circles.addAll(newCircles1);
                circles.addAll(newCircles2);
                pojebMa.getChildren().addAll(newCircles1.get(0).getCircle(), newCircles1.get(1).getCircle(),
                        newCircles2.get(0).getCircle(), newCircles2.get(1).getCircle());

                circles.remove(circle1);
                circles.remove(circle2);
                pojebMa.getChildren().removeAll(circle1.getCircle(), circle2.getCircle());
            }
        }
    }

    protected void onKruhButtonClick() {
        Random random = new Random();
        double x, y;
        boolean collision;
        do {
            collision = false;
            x = random.nextDouble() * (WIDTH - 2 * Kruh.radius) + Kruh.radius;
            y = random.nextDouble() * (HEIGHT - 2 * Kruh.radius) + Kruh.radius;

            // Check for collisions with existing circles
            for (Kruh existingCircle : circles) {
                double distance = Math.sqrt(Math.pow(x - existingCircle.getX(), 2) + Math.pow(y - existingCircle.getY(), 2));
                if (distance < Kruh.radius + existingCircle.getRadius()) {
                    collision = true;
                    break;
                }
            }
        } while (collision);

        Kruh a = new Kruh(random.nextInt(550), random.nextInt(400), random.nextInt((30/5-(5+4)/5+1)*5+(5+4)/5*5), Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        root.getChildren().add(a.getCircle());
        circles.add(a);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
