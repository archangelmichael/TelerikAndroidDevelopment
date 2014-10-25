package task6.MovieRentals;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMovieRentals {

	private static Logger logger = Logger.getLogger("My logger");
	
	public static void main(String[] args) {
		MovieStore store = new MovieStore();
		store.addMovie(new Movie("Dracula Untold", Genre.Antion, 2014));
		store.addMovie(new Movie("Addicted", Genre.Drama, 2014));
		store.addMovie(new Movie("I am Ali", Genre.Historical, 2014));
		store.addMovie(new Movie("Fury", Genre.Drama, 2014));
		store.addMovie(new Movie("Man of steel", Genre.Thriller, 2013));

		Contact gosho = new Contact("gosho", "0888888234");
		store.addContact(gosho);
		store.addContact(gosho);
		
		logger.log(Level.INFO, "All Contacts");
		ArrayList<Contact> allContacts = store.getListOfContacts();
		printList(allContacts);
		
		logger.log(Level.INFO, "All Movies before rental");
		ArrayList<Movie> allMovies = store.getListOfMovies();
		printList(allMovies);
	
		store.rentAMovie("Fury", null);
		store.rentAMovie("Fury", gosho);
	
		logger.log(Level.INFO, "All Movies after rental");
		allMovies = store.getListOfMovies();
		printList(allMovies);
		
		logger.log(Level.INFO, "Overdue Rentals");
		ArrayList<Movie> overdueRentals = store.getOverdueRentals();
		printList(overdueRentals);
		
		logger.log(Level.INFO, "Movie Returned");
		store.returnAMovie("Fury");
	}

	private static <T> void printList(ArrayList<T> collection) {
		if (collection.isEmpty() || collection == null) {
			return;
		}
		
		for (T object : collection) {
			System.out.println(object.toString());
		}
	}
}
