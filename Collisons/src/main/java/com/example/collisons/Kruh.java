package com.example.collisons;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Kruh {
    private double x, y;
    public static double radius;
    private static Random random = new Random();
    private double dx, dy;  // Velocity components
    private Circle circle;

    public Kruh(double x, double y, double radius, Color color) {
        this.x = x;
        this.y = y;
        Kruh.radius = radius;
        this.circle = new Circle(x, y, radius, color);
        this.dx = Math.random() * 2 - 1;  // Random initial velocity (-1 to 1)
        this.dy = Math.random() * 2 - 1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public Circle getCircle() {
        return circle;
    }

    public void move() {
        x += dx;
        y += dy;
        circle.setCenterX(x);
        circle.setCenterY(y);

        // Reflect when hitting the borders
        if (x <= radius || x >= CollisionMain.WIDTH - radius) {
            dx = -dx;
        }
        if (y <= radius || y >= CollisionMain.HEIGHT - radius) {
            dy = -dy;
        }
    }

    public boolean collidesWith(Kruh otherCircle) {
        double dx = otherCircle.x - x;
        double dy = otherCircle.y - y;
        double distance = Math.sqrt(dx * dx + dy * dy);
        return distance <= radius + otherCircle.radius;
    }

    public boolean canSplit() {
        return radius > CollisionMain.MIN_RADIUS * 2;
    }
    public boolean canMerge(Kruh otherCircle) {
        return Math.abs(radius - otherCircle.radius) < CollisionMain.MIN_RADIUS;
    }

    public static Kruh mergeWith(Kruh circle1, Kruh circle2) {
        //double mergedRadius = Math.sqrt(circle1.radius * circle1.radius + circle2.radius * circle2.radius);
        double mergedRadius = Math.PI*Math.pow((circle1.getRadius()+circle2.getRadius()),2);
        double mergedX = (circle1.x + circle2.x) / 2;
        double mergedY = (circle1.y + circle2.y) / 2;

        return new Kruh(mergedX, mergedY, mergedRadius, Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
    }


    public List<Kruh> split(List<Kruh> circles) {
        List<Kruh> newCircles = new ArrayList<>();
        double newRadius = radius / 2;
        //zmena na eandom farbu
        Kruh circle1 = new Kruh(x - newRadius, y, newRadius, Color.RED);
        Kruh circle2 = new Kruh(x + newRadius, y, newRadius, Color.RED);
        newCircles.add(circle1);
        newCircles.add(circle2);
        return newCircles;
    }

    public void setVelocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
