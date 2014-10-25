package task6.MovieRentals;

import java.util.Calendar;
import java.time.Year;

public class Movie {
	private String title;
	private Genre genre;
	private int year;
	private boolean isRented;
	private Contact renter;
	private Calendar rentDate;
	private Calendar dueDate;
	
	public Movie(String title, Genre genre, int year) {
		this.setTitle(title);
		this.setGenre(genre);
		this.setYear(year);
		this.setRentDate(null);
		this.setDueDate(null);
		this.setRented(false);
		this.setRenter(null);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
	
	public boolean getRented() {
		return this.isRented;
	}

	public Calendar getRentDate() {
		return rentDate;
	}

	public void setRentDate(Calendar rentDate) {
		this.rentDate = rentDate;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public Contact getRenter() {
		return renter;
	}

	public void setRenter(Contact renter) {
		this.renter = renter;
	}

	@Override
	public String toString() {
		if (isRented) {
			return "Movie: " + title + ", genre: " + genre + ", year: " + year
					+ ", isRented: " + isRented + ", renter: " + renter
					+ ", rentDate: " + rentDate.getTime() + ", dueDate: " + dueDate.getTime() + "]";
		}
		
		return "Movie: " + title + ", genre: " + genre + ", year: " + year
				+ ", isRented: NO]";
	}
}
