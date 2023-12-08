package com.example.collisons;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class BallPane extends Pane {
    private final double radius = 15;
    private final double width = 200;
    private final double height = 200;
    Circle ball = new Circle(width / 2, height / 2, radius);

    BallPane() {
        ball.setFill(Color.WHITE);
        ball.setStroke(Color.BLACK);
        getChildren().add(ball);
    }

    public void moveLeft() {
        if (ball.getRadius() < ball.getCenterX()) {
            ball.setCenterX(ball.getCenterX() - 10);
        }
    }

    public void moveRight() {
        if (ball.getCenterX() < width - ball.getRadius()) {
            ball.setCenterX(ball.getCenterX() + 10);
        }
    }

    public void moveUp() {
        if (ball.getRadius() < ball.getCenterY()) {
            ball.setCenterY(ball.getCenterY() - 10);
        }
    }

    public void moveDown() {
        if (ball.getCenterY() < height - ball.getRadius()) {
            ball.setCenterY(ball.getCenterY() + 10);
        }
    }}