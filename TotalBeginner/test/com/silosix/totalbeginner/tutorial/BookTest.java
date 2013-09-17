package com.silosix.totalbeginner.tutorial;

import org.junit.Test;

import junit.framework.TestCase;

public class BookTest extends TestCase {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	public void testBook() {
		Book b1 = new Book("Great Expectations");
		assertEquals("Great Expectations", b1.title);
		assertEquals("unknown author", b1.author);
	}
	
	public void testGetPerson() {
		Book b2 = new Book("War and Peace");
		Person p2 = new Person();
		p2.setName("Elvis");
		
		// method to say person who loaned the book
		b2.setPerson(p2);
		
//		Person testPerson = b2.getPerson();
//		String testName = testPerson.getName();
		String testName = b2.getPerson().getName();
		assertEquals("Elvis", testName);
	}
	
	public void testToString() {
		Book b2 = new Book("War And Peace");
		Person p2 = new Person();
		p2.setName("Elvis");
		
		assertEquals("War And Peace by unknown author; Available",
				 b2.toString());
		 
		b2.setPerson(p2);
		assertEquals("War And Peace by unknown author; Checked out to Elvis",
				 b2.toString());
	}

}
