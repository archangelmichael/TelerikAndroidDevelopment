package com.intro;

public class MessageChange {
	
	public String oldtext;
	public String newtext;
	public String date;

	public MessageChange(String old, String changed, String date) {
		this.oldtext = old;
		this.newtext = changed;
		this.date = date;
	}

	@Override
	public String toString() {
		return "Changed on: \n" + this.date + "; \n From: " + this.oldtext + "; \n To: " + this.newtext;
	}
}