package org.persistence.tutorial;

import java.io.Serializable;
import java.util.ArrayList;


public class MyLibrary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293477516382653955L;
	String name;
	ArrayList<Book> books;
	ArrayList<Person> people;

	public MyLibrary(String name) {
		this.name = name;
		books = new ArrayList<Book>();
		people = new ArrayList<Person>();
		
	}

	public String getName() {
		return name;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public ArrayList<Person> getPeople() {
		return people;
	}

	public void addBook(Book b1) {
		this.books.add(b1);
		
	}

	public void removeBook(Book b1) {
		this.books.remove(b1);
		
	}
	
	public void addPerson(Person p1) {
		this.people.add(p1);
	}
	
	public void removePerson(Person p1) {
		this.people.remove(p1);
	}

	public boolean checkOut(Book b1, Person p1) {
		int booksOut = this.getBooksForPerson(p1).size();
		if ((b1.getPerson() == null) &&
				(booksOut < p1.getMaximumBooks()))
		{
			b1.setPerson(p1);
			return true;
		}
		else {
			return false;
		}
	}

	public boolean checkIn(Book b1) {
		if (b1.getPerson() != null) {
			b1.setPerson(null);
			return true;
		}
		else {
			return false;
		}
	}

	public ArrayList<Book> getBooksForPerson(Person p1) {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book aBook : this.getBooks()) {
			if ((aBook.getPerson() != null) &&
					(aBook.getPerson().getName() == p1.getName()))
			{
				result.add(aBook);
			}
//			if (aBook.getPerson().getName() == 
//				p1.getName()) {
//				result.add(aBook);
//			}
			
		}
		return result;
	}

	public ArrayList<Book> getAvailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			if (book.getPerson() == null) {
				result.add(book);
			}
		}
		return result;
	}

	public ArrayList<Book> getUnavailableBooks() {
		ArrayList<Book> result = new ArrayList<Book>();
		for (Book book : this.getBooks()) {
			if (book.getPerson() != null) {
				result.add(book);
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		// create a new mylibrary
		MyLibrary testLibrary = new MyLibrary("Test Drive Library");
		Book b1 = new Book("War and Peace");
		b1.setAuthor("Tolstoy");
		Book b2 = new Book("Great Expectations");
		b2.setAuthor("Charles Dickens");
		
		Person jim = new Person();
		jim.setName("Jim");
		Person sue = new Person();
		sue.setName("Sue");
		
		testLibrary.addBook(b1);
		testLibrary.addBook(b2);
		testLibrary.addPerson(jim);
		testLibrary.addPerson(sue);
		
		System.out.println("Just Created New Library");
		testLibrary.printStatus();
		
		testLibrary.checkOut(b1, sue);
		System.out.println("Checked out War and Peace to Sue");
		testLibrary.printStatus();
		
		testLibrary.checkIn(b1);
		testLibrary.checkOut(b2, jim);
		
		testLibrary.printStatus();
		
		// save TestMyLibrary object to file, then read it back
		//  then printStatus()
		MyUtilities.saveMyLibraryToXMLFile(
				"testmain.xml", testLibrary);
		MyLibrary newMyLibrary = MyUtilities.
				getMyLibraryFromXMLFile("testmain.xml");
		System.out.println("Printing information from saved xml file:\n==================================");
		newMyLibrary.printStatus();
		

	}

	private void printStatus() {
		System.out.println("Status Report of MyLibrary: \n" + 
				this.toString());

		for (Book thisBook : this.getBooks()) {
			System.out.println(thisBook);
		}
		
		for (Person thisPerson : this.getPeople()) {
			int count = this.getBooksForPerson(thisPerson).size();
			System.out.println(thisPerson + " (has " + count + 
					" of my books)");
		}
		
		System.out.println("Books Available: "
				+ this.getAvailableBooks().size());
		System.out.println("--- End of Status Report---\n");
		
		
	}
	
	@Override
	public String toString() {
		return this.getName() + 
		": " + this.getBooks().size() + " books; " 
		+ this.getPeople().size() + " people.";
	}
	
	
}
