package org.example.zdarzenia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Zdarzenia extends Application {

    private static final double MOVE_STEP = 10;
    private static final double RADIUS_STEP = 5;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 400, 400);

        Circle circle = new Circle(200, 200, 50);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(1);

        root.getChildren().add(circle);

        scene.setOnKeyPressed((KeyEvent event) -> {
            KeyCode code = event.getCode();
            switch (code) {
                case UP -> circle.setCenterY(circle.getCenterY() - MOVE_STEP);
                case DOWN -> circle.setCenterY(circle.getCenterY() + MOVE_STEP);
                case LEFT -> circle.setCenterX(circle.getCenterX() - MOVE_STEP);
                case RIGHT -> circle.setCenterX(circle.getCenterX() + MOVE_STEP);
                case ENTER, PLUS -> circle.setRadius(circle.getRadius() + RADIUS_STEP);
                case MINUS, BACK_SPACE -> {
                    double newRadius = circle.getRadius() - RADIUS_STEP;
                    if (newRadius > 0) {
                        circle.setRadius(newRadius);
                    }
                }
            }
        });

        circle.setOnMouseMoved((MouseEvent e) -> {
            circle.setStroke(Color.BLUE);
            circle.setStrokeWidth(3);
        });

        circle.setOnMouseExited((MouseEvent e) -> {
            circle.setStroke(Color.BLACK);
            circle.setStrokeWidth(1);
        });

        primaryStage.setTitle("Prosty Sterownik Kształtu");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
