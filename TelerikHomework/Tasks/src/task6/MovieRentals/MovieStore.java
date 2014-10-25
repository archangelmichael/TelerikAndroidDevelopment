package task6.MovieRentals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MovieStore {
	// Fields
	private final int RENT_PERIOD = 2;
	private HashMap<String, Movie> movies;
	private HashMap<String, Contact> contacts;
	
	// Constructors
	public MovieStore(HashMap<String, Movie> films, HashMap<String, Contact> contacts) {
		this.setMovies(films);
		this.setContacts(contacts);
	}
	
	public MovieStore() {
		this.setMovies(new HashMap<String, Movie>());
		this.setContacts(new HashMap<String, Contact>());
	}
	
	
	// Properties
	public HashMap<String, Contact> getContacts() {
		return contacts;
	}

	public void setContacts(HashMap<String, Contact> contacts) {
		this.contacts = contacts;
	}
	
	public HashMap<String, Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(HashMap<String, Movie> films) {
		this.movies = films;
	}
	
	
	// Methods
	public void addMovie (Movie movie) {
		String movieTitle = movie.getTitle();
		Movie exsistingMovie = getMovieByTitle(movieTitle);
		if (exsistingMovie == null) {
			this.getMovies().put(movieTitle, movie);
			System.out.println(String.format("Movie: \' %s \' added successfully", movieTitle));
			return;
		}
		
		System.out.println(String.format("Movie: \' %s \' addready in store", movieTitle));
		return;
	}
	
	public void addContact (Contact contact) {
		String contactName = contact.getName();
		
		Contact exsistingContact = getContactByName(contactName);
		if (exsistingContact == null) {
			this.getContacts().put(contactName, contact);
			System.out.println(String.format("Contact: \' %s \' added successfully", contactName));
			return;
		}
		
		System.out.println(String.format("Contact: \' %s \' addready in store", contactName));
		return;
	}
	
	public Movie getMovieByTitle(String title) {
		Movie film = null;
		if (!this.getMovies().containsKey(title)) {
			System.out.println(String.format("Store dont have the movie: \' %s \' ", title));
			return film;
		}
		
		film = this.getMovies().get(title);
		return film;
	}
	
	public Contact getContactByName(String name) {
		Contact customer = null;
		if (!this.getContacts().containsKey(name)) {
			System.out.println(String.format("Store dont have customer named: \' %s \' ", name));
			return customer;
		}
		
		customer = this.getContacts().get(name);
		return customer;
	}
	
	public ArrayList<Movie> getListOfMovies() {
		ArrayList<Movie> list = new ArrayList<Movie>();
		for (Movie value : this.getMovies().values()) {
		    list.add(value);
		}
		
		return list;
	}
	
	public ArrayList<Contact> getListOfContacts() {
		ArrayList<Contact> list = new ArrayList<Contact>();
		for (Contact value : this.getContacts().values()) {
		    list.add(value);
		}
		
		return list;
	}
	
	public void rentAMovie (String title, Contact contact) {
		if (contact == null || title == null) {
			// Throw exception
			System.out.println(String.format("Cant rent a movie anonymously", title));
			return;
		}
		
		Movie movieToRent = this.getMovieByTitle(title);
		if (movieToRent.getRented()) {
			System.out.println(String.format("Movie: \' %s \' already rented", title));
			return;
		}
		
		if (this.getContactByName(contact.getName()) == null) {
			System.out.println(String.format("Customer : \' %s \' is not registered", contact.getName()));
			return;
		}
		
		Calendar date = Calendar.getInstance();
		movieToRent.setRenter(contact);
		movieToRent.setRented(true);
		movieToRent.setRentDate(date);
		date.roll(Calendar.MONTH, RENT_PERIOD);
		movieToRent.setDueDate(date);
		this.getMovies().put(title, movieToRent);
		System.out.println(String.format("Movie: \'%s\' rented by %s", title, contact.getName()));
		return;
	}
	
	public void returnAMovie (String title) {
		if (title == null) {
			// Throw exception
			System.out.println(String.format("No movie with that name", title));
			return;
		}
		
		Movie movieToReturn = this.getMovieByTitle(title);
		if (movieToReturn == null) {
			System.out.println(String.format("No movie with that name", title));
			return;
		}
		
		if (!movieToReturn.getRented()) {
			System.out.println("Cant return not rented movie");
			return;
		}
		
		Contact renter = movieToReturn.getRenter();
		movieToReturn.setDueDate(null);
		movieToReturn.setRentDate(null);
		movieToReturn.setRented(false);
		movieToReturn.setRenter(null);
		System.out.println(String.format("Movie: \'%s\' has been returned by %s", title, renter.getName()));
		return;
	}
	
	public ArrayList<Movie> getOverdueRentals() {
		ArrayList<Movie> overdue = new ArrayList<Movie>();
		Calendar date = Calendar.getInstance();
		ArrayList<Movie> allMovies = getListOfMovies();
		
		for (Movie movie: allMovies) {
			if (movie.getRented()) {
				if (!movie.getDueDate().before(date)) {
					overdue.add(movie);
				}
			}
		}
		
		return overdue;
	}
}
