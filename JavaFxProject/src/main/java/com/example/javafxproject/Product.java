package com.example.javafxproject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Product extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/products";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        Label fishLabel = new Label("Fish");
        Label beefLabel = new Label("Beef");
        Label porkLabel = new Label("Pork");

        gridPane.add(fishLabel, 0, 0);
        gridPane.add(beefLabel, 1, 0);
        gridPane.add(porkLabel, 0, 1);

        Button sendButton = new Button("Send to Database");
        sendButton.setOnAction(event -> saveToDatabase());

        gridPane.add(sendButton, 1, 2);

        Scene scene = new Scene(gridPane, 200, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Food GridPane Example");
        primaryStage.show();
    }

    private void saveToDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String insertQuery = "INSERT INTO produqtebi (name, quantity) VALUES (?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, "Fish");
                statement.setInt(2,10);
                statement.executeUpdate();
            }


            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, "Beef");
                statement.setInt(2,20);
                statement.executeUpdate();
            }


            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, "Pork");
                statement.setInt(2,30);
                statement.executeUpdate();
            }

            System.out.println("Products saved to the database.");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
