package com.jfruit.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/fruit_page.fxml"));
			// FXMLLoader loader = new
			// FXMLLoader(getClass().getResource("/fxml/fruit_page.fxml"));
			// Parent root = loader.load();
			// FruitController fruitController = loader.getController();
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("J-Fruit Project");
			stage.setScene(scene);
			stage.show();
			// fruitController.setFruitFileComboBox();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
