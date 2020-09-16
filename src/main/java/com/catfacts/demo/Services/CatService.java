package com.catfacts.demo.Services;

import com.catfacts.demo.Model.CatFact;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;



public class CatService {
	
	ArrayList<CatFact> listOfFacts = new ArrayList<CatFact>();

	public CatFact serviceCat() throws IOException {

		//Choose what API to consume
		URL catURL = new URL("https://cat-fact.herokuapp.com/facts/random");
		//Instantiate a Buffered Reader to consume the InputStream from the URL
		BufferedReader inputFromCatURL = new BufferedReader(new InputStreamReader(catURL.openStream()));
		//Map the data from Json to an object
		CatFact data = new Gson().fromJson(inputFromCatURL, CatFact.class);
		//Close the BufferedReader
		inputFromCatURL.close();
	
		return data;
	}
	
	// Returnere en fact.
	@Override
	public String toString() {
		try {
			return serviceCat().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Shit";
	}
	
	// Laver et loop, der tilføjer 10 facts, til en ArrayList, som så bliver returneret.
	public ArrayList<CatFact> listOfTenFacts() throws IOException {
		
		listOfFacts.clear();
		
		for (int i = 0; i < 10; i++) {
			listOfFacts.add(serviceCat());
		}
		
		return listOfFacts;
	}
	
	
	/*
	* 
	* 	Ohhhh.... Lille disclaimer. 
	* 	Jeg har på intet tidspunkt, kunne finde ud af denne opgave. 
	* 	Brugte flere timer på at regne ud hvordan man gjorde.
	* 	Så er ikke helt sikker på hvordan koden fungere, men vil gøre mit bedste at forklarer det.
	* 
	* 	Starter med at lave en ny array, som indenholder 10 facts.
	* 	Kalder så objectet Collections (Ikke helt sikker på hvad det er tbh) methode: sort().
	* 	Den tager så to parameter ind. Min ArrayListe er en af dem. den anden er to objecter fra den liste.
	* 	Den sammneligner så på en eller anden magisk måde de to, og sortere dem.
	* 
	* 
	*  */
	
	
	public String sortedTenFacts() throws IOException {
		
		// Array med 10 facts, som bliver sorteret senere.
		ArrayList<CatFact> toBeSortedFacts = listOfTenFacts();
		
		// Kalder Collections klassen og dens sort() method. Den tager ind to parameter. 
		// Min Arraylist, og en lambda methode, som returner en samlignet udgave af to objecter i min ArrayListe.
		Collections.sort(toBeSortedFacts, (o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()));
		return toBeSortedFacts.toString(); // returnere factsene.
	}
	
	
	public String searchFacts(char c, int n) throws IOException {
		
		// Laver to arrays, en til at holde 10 facts. Den anden til at holde de facts der passer.
		ArrayList<CatFact> searchAbleFactList = listOfTenFacts();
		ArrayList<CatFact> searchedListOfFacts = new ArrayList<CatFact>();
		
		// Looper igennem factsene
		for (CatFact fact : searchAbleFactList) {
			String tempString = fact.getText(); // Jeg følte det var en pænere måde at gøre det på.
			int numberOfChar = 0; // Bruges til at samligne med n.
			
			// Looper igennem teksten for at tjekke om c er i teksten.
			 for (int i = 0; i < tempString.length(); i++) {
			 	if (c == tempString.charAt(i)) {
			 		numberOfChar++; // Tæller hvor mange af c der er i teksten.
				}
			 }
			 
			 // Checker om antallet af c passer på n. Derefter tilføjes facten til den tomme ArrayListe.
			 if (numberOfChar == n) {
				 searchedListOfFacts.add(fact);
			 } 
		}
		
		//Tjekker om søgningen var en succes, elleres retunere en String med en tekst, der siger at det ikke gik op.
		if (searchedListOfFacts.size() > 0) {
			return searchedListOfFacts.toString();
		} else {
			return "There was no fact containing the letter: " + c + " this amount of times " + n + " ";
		}
		
	}
}
