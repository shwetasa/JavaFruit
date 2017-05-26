package com.jfruit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jfruit.data.Fruit;
import com.jfruit.data.FruitFile;
import com.jfruit.utilities.Constants;
import com.sun.prism.impl.Disposer.Record;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class EditWindowController {

	@FXML
	private TableView<Fruit> tableView;

	@FXML
	private TextField title;

	@FXML
	private HBox titleHBox;

	@FXML
	private ComboBox<String> saveORLoad;

	@FXML
	private TableColumn<Fruit, String> fruitNameColumn;

	@FXML
	private TableColumn<Fruit, String> fruitAmountColumn;

	@FXML
	private PieChart piechartFXid;

	@FXML
	private ComboBox<String> saveORLoadComboBox;

	@FXML
	private TableColumn fxRemoveButtonTableColumn;

	private String selectedFruitFileName;

	private String oldTitleStr;

	private ObservableList<Fruit> fruitData;

	public String getOldTitleStr() {
		return oldTitleStr;
	}

	public void setOldTitleStr(String oldTitleStr) {
		this.oldTitleStr = oldTitleStr;
	}

	public String getSelectedFruitFileName() {
		return selectedFruitFileName;
	}

	public void setSelectedFruitFileName(String selectedFruitFileName) {
		this.selectedFruitFileName = selectedFruitFileName;
	}

	@FXML
	protected void hanleGoAction() {
		String loadOrSave = null;
		String titleStr = null;
		if (saveORLoadComboBox.getSelectionModel() != null
				&& saveORLoadComboBox.getSelectionModel().getSelectedItem() != null) {
			loadOrSave = saveORLoadComboBox.getSelectionModel().getSelectedItem().toString();
		}
		if (loadOrSave.equalsIgnoreCase(Constants.LOAD)) {
			initFruitTableView(selectedFruitFileName);
		} else if (loadOrSave.equalsIgnoreCase(Constants.SAVE)) {
			if (title != null) {
				List<Fruit> updatedfruitList = tableView.getItems();
				List<FruitFile> existingFruitList = FruitController.getFruitFileList();
				int i = 0;
				titleStr = title.getText();
				for (FruitFile fruitFile : existingFruitList) {
					if (fruitFile.getTitle().equalsIgnoreCase(getOldTitleStr())) {
						i++;
					}
				}
				FruitFile newFruitFile = new FruitFile();
				newFruitFile.setTitle(titleStr);
				newFruitFile.setFruitList(updatedfruitList);
				if (existingFruitList != null && existingFruitList.size() > 0 && i > 0) {
					existingFruitList.remove(i - 1);

				} else {
					existingFruitList = new ArrayList<FruitFile>();
				}
				FruitController.getFruitFileList().add(newFruitFile);
			}
		}
	}

	@FXML
	protected void handleNewFruitButtonAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.ADD_FXML_PATH));
			Parent addPageroot = loader.load();
			AddWindowController addWindowController = loader.getController();
			addWindowController.initFruitFile(getSelectedFruitFileName());
			Stage stage = new Stage();
			stage.setScene(new Scene(addPageroot, 300, 300));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void handleRemoveLoadButtonAction(ActionEvent event) {
	}

	@FXML
	protected void removefruit(ActionEvent event) {

	}

	@FXML
	protected void addFruit(ActionEvent event) {

	}

	public void initFruitTableView(String selectedFruitFile) {
		setSelectedFruitFileName(selectedFruitFile);
		buttonInTableView();
		List<Fruit> editableFruitList = null;
		List<FruitFile> listFruitFile = FruitController.getFruitFileList();
		for (FruitFile fruitFile : listFruitFile) {
			if (selectedFruitFile.equalsIgnoreCase(fruitFile.getTitle())) {
				editableFruitList = fruitFile.getFruitList();
			}
		}
		TableColumn<Fruit, Integer> amountCol = new TableColumn<>(Constants.AMOUNT);
		amountCol.setCellValueFactory(new PropertyValueFactory<Fruit, Integer>(Constants.AMOUNT));
		setAmountOnEdit();
		tableView.setEditable(true);
		title.setText(selectedFruitFile);
		setOldTitleStr(selectedFruitFile);
		if (editableFruitList != null) {
			fruitData = FXCollections.observableArrayList(editableFruitList);
			tableView.setItems(fruitData);
			loadPieChart(editableFruitList);
		}

	}

	private void setAmountOnEdit() {
		fruitAmountColumn.setOnEditCommit(new EventHandler<CellEditEvent<Fruit, String>>() {
			public void handle(CellEditEvent<Fruit, String> t) {
				((Fruit) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAmount(t.getNewValue());
			}
		});
	}

	private void loadPieChart(List<Fruit> editableFruitList) {
		List<PieChart.Data> pieChart = new ArrayList<PieChart.Data>();
		if (editableFruitList != null && editableFruitList.size() > 0) {
			for (Fruit fruit : editableFruitList)
				pieChart.add(new PieChart.Data(fruit.getName(), Integer.parseInt(fruit.getAmount())));
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(pieChart);
		piechartFXid.setTitle("Fruits Data View");
		piechartFXid.setData(pieChartData);
	}

	private void buttonInTableView() {
		// Insert Button

		fxRemoveButtonTableColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Record, Boolean>, ObservableValue<Boolean>>() {

					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});

		// Adding the Button to the cell
		fxRemoveButtonTableColumn
				.setCellFactory(new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

					@Override
					public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
						return new ButtonCell();
					}

				});

	}

	// Define the button cell
	private class ButtonCell extends TableCell<Record, Boolean> {
		final Button cellButton = new Button(Constants.REMOVE);

		ButtonCell() {
			// Action when the button is pressed
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					// get Selected Item
					Fruit currentFruit = (Fruit) ButtonCell.this.getTableView().getItems()
							.get(ButtonCell.this.getIndex());
					// remove selected item from the table list
					removeSelectedItemFromList(currentFruit);
					fruitData.remove(currentFruit);
				}
			});
		}

		// Display button if the row is not empty
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			} else {
				setGraphic(null);
			}
		}
	}

	private void removeSelectedItemFromList(Fruit currentFruit) {
		LinkedList<FruitFile> existingFruitList = FruitController.getFruitFileList();
		int i = 0;
		for (FruitFile fruitFile : existingFruitList) {
			if (fruitFile.getTitle().equalsIgnoreCase(getOldTitleStr())) {
				i++;
			}
			if (existingFruitList != null && existingFruitList.size() > 0 && i > 0) {
				boolean isDeletedCurrentElement = existingFruitList.get(i - 1).getFruitList().remove(currentFruit);
				if (isDeletedCurrentElement) {
					// set message saying done it
				}
				FruitController.setFruitFileList(existingFruitList);

			}

		}
	}
}
