package com.jfruit.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jfruit.data.Fruit;
import com.jfruit.data.FruitFile;

public class JacksonService {
	public static void main(String[] args) {
		JacksonService obj = new JacksonService();
		 obj.run();
		//obj.jsonToJavaObject();
	}

	private void run() {
		ObjectMapper mapper = new ObjectMapper();

		FruitFile fruitFile = createDummyObject();

		try {
			// Convert object to JSON string and save into a file directly
			mapper.writeValue(new File("F:\\FruitFile.json"), fruitFile);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(fruitFile);
			System.out.println(jsonInString);
			System.out.println("------------------------------------------------------");
			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(fruitFile);
			System.out.println(jsonInString);

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void jsonToJavaObject() {
		ObjectMapper mapper = new ObjectMapper();
		// JSON from file to Object
		try {
			FruitFile obj = mapper.readValue(new File("F:\\FruitFile.json"), FruitFile.class);
			// JSON from String to Object
			// FruitFile obj1 = mapper.readValue(jsonInString, FruitFile.class);
			System.out.println(obj);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static FruitFile jsonToJavaObject(File file) {
		ObjectMapper mapper = null;
		FruitFile fruitFile = null;
		// JSON from file to Object
		try {
			if (file != null) {
				mapper = new ObjectMapper();
				fruitFile = mapper.readValue(file, FruitFile.class);
				// JSON from String to Object
				// FruitFile obj1 = mapper.readValue(jsonInString,
				// FruitFile.class);
				System.out.println(fruitFile);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fruitFile;
	}

	private FruitFile createDummyObject() {
		List<Fruit> fruitList = new ArrayList<>();
		Fruit fruit = new Fruit();
		fruit.setName("Orange");
		fruit.setAmount("2");

		Fruit fruit1 = new Fruit();
		fruit1.setName("Pear");
		fruit1.setAmount("2");
		Fruit fruit2 = new Fruit();
		fruit2.setName("Watermelon");
		fruit2.setAmount("20");
		Fruit fruit3 = new Fruit();
		fruit3.setName("Apple");
		fruit3.setAmount("60");

		Fruit fruit4 = new Fruit();
		fruit4.setName("Kiwi");
		fruit4.setAmount("80");
		Fruit fruit5 = new Fruit();
		fruit5.setName("Banana");
		fruit5.setAmount("10");

		fruitList.add(fruit);
		fruitList.add(fruit1);
		fruitList.add(fruit2);
		fruitList.add(fruit3);
		fruitList.add(fruit4);
		fruitList.add(fruit5);
		FruitFile fruitFile = new FruitFile();
		fruitFile.setTitle("Costco-Number of Fruits");
		fruitFile.setFruitList(fruitList);
		return fruitFile;

	}
}
