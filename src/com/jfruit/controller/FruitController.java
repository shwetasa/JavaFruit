package com.jfruit.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jfruit.data.Fruit;
import com.jfruit.data.FruitFile;
import com.jfruit.service.JacksonService;
import com.jfruit.utilities.Constants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FruitController {
	@FXML
	private Text actiontarget;
	@FXML
	private VBox vbox;
	@FXML
	private ToggleGroup myToggleGroup;
	@FXML
	private ComboBox<String> fruitCombo;
	@FXML
	private Label fruitSelectorLabel;
	@FXML
	private ImageView orangeImage;
	@FXML
	private ImageView pearImage;
	@FXML
	private ImageView appleImage;
	@FXML
	private Label selectedFruit;
	@FXML
	private HBox editorWindowButton;
	@FXML
	private HBox handleAddLoadButton;
	private static LinkedList<FruitFile> fruitFileList = new LinkedList<FruitFile>();
	// private static List<FruitFile> fruitFileList = new
	// ArrayList<FruitFile>();

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		actiontarget.setText("Sign in button pressed");
	}

	public static LinkedList<FruitFile> getFruitFileList() {
		return fruitFileList;
	}

	public static void setFruitFileList(LinkedList<FruitFile> fruitFileList) {
		FruitController.fruitFileList = fruitFileList;
	}


	@FXML
	protected void handleAddLoadButtonAction(ActionEvent event) {
		String radioText = null;
		RadioButton chk = (RadioButton) myToggleGroup.getSelectedToggle();
		if (chk != null) {
			radioText = chk.getText();
		}
		if (radioText != null && chk.getText().trim().equalsIgnoreCase(Constants.ADD_FRUIT_FILE)) {
			fruitCombo.setVisible(false);
			editorWindowButton.setVisible(false);
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(Constants.EXTENSION_FILTER,
					Constants.JSON_EXTENSION);
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(new Stage());
			if (file != null && file.isFile()) {
				addNewFile(file);

			}
		} else if (radioText != null && chk.getText().trim().equalsIgnoreCase(Constants.LOAD_FRUIT_FILE)) {
			actiontarget.setVisible(false);
			fruitCombo.setVisible(true);
			setFruitFileComboBox();
			editorWindowButton.setVisible(true);
		}
	}

	@FXML
	protected void handleEditorWindowAction(ActionEvent event) {
		String selectedFruitFile = null;
		String selectedFruitName = null;
		System.out.println(selectedFruitName);
		try {
			if (fruitCombo.getSelectionModel() != null && fruitCombo.getSelectionModel().getSelectedItem() != null) {
				selectedFruitFile = fruitCombo.getSelectionModel().getSelectedItem().toString();
			}
			// FXMLLoader fxmlLoader = new
			// FXMLLoader(getClass().getResource("/fxml/edit_page.fxml"));
			// Parent editPageroot = fxmlLoader.load();
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.EDIT_FXML_PATH));
			Parent editPageroot = loader.load();
			EditWindowController editWindowController = loader.getController();
			if (selectedFruitFile != null) {
				editWindowController.initFruitTableView(selectedFruitFile);
			}
			Stage stage = new Stage();
			stage.setScene(new Scene(editPageroot));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addNewFile(final File file) {
		FruitFile fruitFile = JacksonService.jsonToJavaObject(file);
		if (fruitFile != null) {
			fruitFileList.add(fruitFile);
			actiontarget.setText("Added Fruit File");
		}
	}

	@FXML
	protected void addLoadButtonAction(ActionEvent event) {
		System.out.println("Do something");
	}

	public void setFruitFileComboBox() {
		fruitCombo.getItems().clear();
		LinkedList<FruitFile> fruitFileList = getFruitFileList();
		if (fruitFileList != null && fruitFileList.size() > 0) {
			for (FruitFile fruitfile : fruitFileList) {
				fruitCombo.getItems().add(fruitfile.getTitle());
			}
		}
	}

}
