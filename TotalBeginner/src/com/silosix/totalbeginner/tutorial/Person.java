/**
 * COPYRIGHT SILOSIX 2013 All rights reserved.
 */
package com.silosix.totalbeginner.tutorial;

/**
 * @author Setup
 *
 */
public class Person {
	// fields
	private String name; //name of the person
	private int maximumBooks; // most books the person can check out at a time
	
	// constructor
	public Person() {
		name = "unknown name";
		maximumBooks = 3;
	}
	public String getName() {
		return name;
	}

	public void setName(String anyName) {
		name = anyName;

	}
	public int getMaximumBooks() {
		return maximumBooks;
	}
	public void setMaximumBooks(int maximumBooks) {
		this.maximumBooks = maximumBooks;
	}
	public String toString() {
		return this.getName() + " (" + this.getMaximumBooks() + " books)";
		
	}
}
