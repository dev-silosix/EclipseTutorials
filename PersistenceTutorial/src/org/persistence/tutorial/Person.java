package org.persistence.tutorial;

import java.io.Serializable;

/**
 * @author Mark Dexter
 * @version 1.0
 * The Person class represents people who
 * check out books from a MyLibrary.
 *
 */
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4926353133378926761L;
	// fields
	private String name; // person's name
	private int maximumBooks; // # books can check out
	
	// constructors
	public Person() {
		name = "unknown name";
		maximumBooks = 3;
	}
	
	public Person(String name, int maximumBooks) {
		super();
		this.name = name;
		this.maximumBooks = maximumBooks;
	}
	
	public int getMaximumBooks() {
		return maximumBooks;
	}

	// methods
	public String getName() {
		return name;
	}

	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks;
	}

	public void setName(String newName) {
		this.name = newName;
	}
	
	public String toString() {
		return this.getName() + " (" + this.getMaximumBooks()
		+ " books)";
	}
	
	
	

}
