package com.example.javafxproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Piechart extends Application {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/products";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    public void start(Stage primaryStage) {
        List<Product> products = retrieveProductsFromDatabase();

        // Filter products with quantity > 0 using Java Stream API
        List<Product> filteredProducts = products.stream()
                .filter(p -> p.getQuantity() > 0)
                .collect(Collectors.toList());

        PieChart pieChart = createPieChart(filteredProducts);

        Scene scene = new Scene(pieChart, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Food Pie Chart Example");
        primaryStage.show();
    }

    private List<Product> retrieveProductsFromDatabase() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String selectQuery = "SELECT name, quantity FROM produqtebi";

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(selectQuery)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    int quantity = resultSet.getInt("quantity");
                    products.add(new Product(name, quantity));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }

        return products;
    }

    private PieChart createPieChart(List<Product> products) {
        PieChart pieChart = new PieChart();

        for (Product product : products) {
            PieChart.Data data = new PieChart.Data(product.getName(), product.getQuantity());
            pieChart.getData().add(data);
        }

        return pieChart;
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Product {
        private String name;
        private int quantity;

        public Product(String name, int quantity) {
            this.name = name;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
