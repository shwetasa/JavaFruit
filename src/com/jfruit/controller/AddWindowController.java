package com.jfruit.controller;

import java.util.List;

import com.jfruit.data.Fruit;
import com.jfruit.data.FruitFile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddWindowController {
	@FXML
	private TextField fruitName;

	@FXML
	private TextField fruitAmt;

	@FXML
	private Text actiontarget;

	public static String fruitFileName;

	@FXML
	protected void hanleAddFruitAction() {
		boolean isAdded = false;
		List<FruitFile> listFruitFile = FruitController.getFruitFileList();
		Fruit fruit = null;
		if (fruitName != null && fruitAmt != null) {
			fruit = new Fruit(fruitName.getText(), fruitAmt.getText());
		}
		for (FruitFile fruitFile : listFruitFile) {
			if (fruitFileName.equalsIgnoreCase(fruitFile.getTitle())) {
				fruitFile.getFruitList().add(fruit);
				isAdded = true;
			}
		}
		if (isAdded) {
			actiontarget.setText("Added New Fruit Successfully");
		}

	}

	@FXML
	protected void hanleGoBackFruitAction(ActionEvent event) {

	}

	public void initFruitFile(String selectedFruitFile) {
		fruitFileName = selectedFruitFile;
	}

}
