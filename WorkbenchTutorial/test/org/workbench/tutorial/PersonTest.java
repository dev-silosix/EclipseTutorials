package org.workbench.tutorial;

import org.junit.*;
import org.workbench.tutorial.Person;

import static org.junit.Assert.*;

public class PersonTest {

	@Test
	public void testPerson() {
		Person p = new Person();
		assertEquals("unknown name", p.getName());
		assertEquals(3, p.getMaximumBooks());
	}

	@Test
	public void testSetMaximumBooks() {
		Person p1 = new Person();
		p1.setMaximumBooks(10);
		assertEquals(10, p1.getMaximumBooks());
	}

	@Test
	public void testSetName() {
		Person p1 = new Person();
		p1.setName("Fred Flintstone");
		assertEquals("Fred Flintstone", p1.getName());
	}
	
	@Test
	public void testToString() {
		Person p1 = new Person();
		p1.setName("Fred Flintstone");
		p1.setMaximumBooks(7);
		String testString = "Fred Flintstone (7 books)";
		assertEquals(testString, p1.toString());
	}
	

}
