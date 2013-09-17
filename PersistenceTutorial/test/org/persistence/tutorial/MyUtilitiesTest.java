package org.persistence.tutorial;

//imports shorten statements
import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;


public class MyUtilitiesTest {

	
	// using @Test allows any name to be used for the method ( instead of test*... )
	@Test 
	public void saveStringToFile() {
		String saveString = "this is test line 1\n" + 
				"this is test line two\n";

		// reading from testfile, strips \n newline
		// when writing to file, need to add back in
		File testFile = new File("testsavetostring.txt");
		
		// delete previously existing files
		testFile.delete();  // Eclipse automatically adds 'java.io.File' package
		assertFalse("File should not exist",
				testFile.exists());
		
		assertTrue("File should have been saved", 
				MyUtilities.saveStringToFile("testsavestring.txt",
						saveString));
		
		String newString = MyUtilities.getStringFromFile(
				"testsavestring.txt");
		assertTrue("Save and get strings should be equal", 
				saveString.equals(newString));

		// save string to file, then eval the method result for 'true'/successful
//		assertFalse("File should not be saved", MyUtilities.saveStringToFile(
//				"Non-existent directory/thisshouldfail", 
//				saveString));
		
		// create empty string, retrieve file contents and ensure still empty
//		String emptyString = MyUtilities.getStringFromFile(
//				"badfilename.txt	");
//		assertTrue("String should be empty", 
//				emptyString.length() == 0 );
	}
	public MyLibrary createMyLibrary() {
		// declarations
		Book b1;
		Book b2;
		Person p1;
		Person p2;
		MyLibrary ml;
		
		// instantiate objects
		b1 = new Book("Book1");
		b2 = new Book("Book2");
		p1 = new Person();
		p1.setName("Fred");
		p2 = new Person();
		p2.setName("Sue");
		ml = new MyLibrary("Test");
		
		// add books
		ml.addBook(b1);
		ml.addBook(b2);
		ml.addPerson(p1);
		ml.addPerson(p2);
		ml.checkOut(b1, p1);
		return ml;
	}
	
	@Test public void convertToXML() {
		// Instantiate MyLibrary
		MyLibrary startMyLibrary = createMyLibrary();
		// Convert to XML
		String testXMLOut = MyUtilities.convertToXML(startMyLibrary);
		// Instantiate another MyLibrary
		MyLibrary endMyLibrary = MyUtilities.convertFromXML(testXMLOut);
		
		assertEquals("Test", endMyLibrary.getName());
		assertEquals(2, endMyLibrary.getBooks().size());
		assertEquals(2, endMyLibrary.getPeople().size());
		assertEquals("Fred", endMyLibrary.getBooks().get(0).getPerson().getName());
		
		
		
	}

	@Test public void saveToXMLFile() {
		MyLibrary startMyLibrary = createMyLibrary();
		String fileName = "testmylibrary.xml";
		File testFile = new File(fileName);
		// delete previously existing files
		testFile.delete();
		assertFalse("File should not exist",
				testFile.exists());
		assertTrue("File Should have been saved", 
				MyUtilities.saveMyLibraryToXMLFile(
						fileName, startMyLibrary));
		MyLibrary endMyLibrary = 
				MyUtilities.getMyLibraryFromXMLFile(fileName);

		assertEquals("Test", endMyLibrary.getName());
		assertEquals(2, endMyLibrary.getBooks().size());
		assertEquals(2, endMyLibrary.getPeople().size());
		assertEquals("Fred", endMyLibrary.getBooks().
				get(0).getPerson().getName());
	}
	
	@Test public void saveToSerialFile() {
		MyLibrary startMyLibrary = createMyLibrary();
		String fileName = "testmylibrary.ser";
		File testFile = new File(fileName);
		// delete previously existing files
		testFile.delete();
		assertFalse("File should not exist",
				testFile.exists());
		assertTrue("File Should have been saved", 
				MyUtilities.saveMyLibraryToSerialFile(
						fileName, startMyLibrary));
		MyLibrary endMyLibrary = 
				MyUtilities.getMyLibraryFromSerialFile(fileName);

		assertEquals("Test", endMyLibrary.getName());
		assertEquals(2, endMyLibrary.getBooks().size());
		assertEquals(2, endMyLibrary.getPeople().size());
		assertEquals("Fred", endMyLibrary.getBooks().
				get(0).getPerson().getName());
	}
	
}
