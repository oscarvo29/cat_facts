package com.catfacts.demo.Model;

import java.util.Date;

public class CatFact {
	private String text;
	private Date createdAt;
	private Date updatedAt;

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}



	@Override
	public String toString() {
		
		// Formatere en fact til html og giver det lidt css for visual pleasing.
		return "<div style=\"margin: 15px auto; border: 3px solid black; width: 50%; text-align:center\">" +
					"<h1>Cat fact:</h1>" +
					"<p>Fact: " + text + "</p>" +
					"<p>Created at: " + createdAt + "</p>" +
					"<p>Last update: " + updatedAt + "</p>" +
				"</div>";
	}

	
	
	
}
