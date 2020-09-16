package com.catfacts.demo.Controllers;

import com.catfacts.demo.Services.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class CatFactsController {

	private CatService catFact = new CatService();
	
	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "<div>" +
				"<h1>Hello! and welcome to Cat Facts!</h1>" +
				"<p>Here you can read about cool cat facts! </p>" +
				"</div>";
	}
	
	@GetMapping("/getSingle")
	@ResponseBody
	public String getSingle() {
		return catFact.toString();
	}
	
	@GetMapping("/getTen")
	@ResponseBody
	public String getTen() {
		try {
			return catFact.listOfTenFacts().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error";
	}
	
	
	@GetMapping("/getTenSorted")
	@ResponseBody
	public String sortedTen() {
		try {
			return catFact.sortedTenFacts().toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error: sorted";
	}
	
	@GetMapping("/getSearchedFacts")
	@ResponseBody
	public String searchedFacts(@RequestParam char c, @RequestParam int n) {
		try {
			return catFact.searchFacts(c, n).toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "Error: Something went wrong.";
	}
}
