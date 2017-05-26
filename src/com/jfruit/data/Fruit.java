package com.jfruit.data;

import javafx.beans.property.SimpleStringProperty;

public class Fruit {
	private final SimpleStringProperty name = new SimpleStringProperty("");;
	private final SimpleStringProperty amount = new SimpleStringProperty();
	public Fruit() {
        this("", "");
    }
	
	public Fruit(String name, String amount) {
       setName(name);
       setAmount(amount);
    }

	public String getName() {
		return name.get();
	}

	public void setName(String fname) {
		name.set(fname);
	}

	public String getAmount() {
		return amount.get();
	}

	public void setAmount(String famount) {
		amount.set(famount);
	}

	

	

}
