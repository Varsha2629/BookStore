package com.fdmgroup.bookstore.model;

import java.util.Objects;

public class AudioBook extends Book {
	
	
	public AudioBook(int itemld, double price, String title, String author, BookGenre bookGenre) {
		super(itemld, price, title, author, bookGenre);
		// TODO Auto-generated constructor stub
	}
	private int timeLengthSeconds;
	
	public int getTimeLengthSeconds() {
		return timeLengthSeconds;
	}

	public void setTimeLengthSeconds(int timeLengthSeconds) {
		this.timeLengthSeconds = timeLengthSeconds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(timeLengthSeconds);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AudioBook other = (AudioBook) obj;
		return timeLengthSeconds == other.timeLengthSeconds;
	}

	@Override
	public String toString() {
		return "AudioBook [timeLengthSeconds=" + timeLengthSeconds + ", getTimeLengthSeconds()="
				+ getTimeLengthSeconds() + ", hashCode()=" + hashCode() + ", getItemld()=" + getItemld()
				+ ", getPrice()=" + getPrice() + ", getTitle()=" + getTitle() + ", getAuthor()=" + getAuthor()
				+ ", getBookGenre()=" + getBookGenre() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}
	
	

	
	
		
	

}
