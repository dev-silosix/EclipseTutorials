package org.persistence.tutorial;

import java.io.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class MyUtilities {

	public static boolean saveStringToFile(String fileName, 
			String saveString) {
		boolean saved = false;
		BufferedWriter bw = null;
		try{
//			Since we never use the FileWriter directly, can just nest
//			FileWriter fw = new FileWriter(fileName);
//			bw = new BufferedWriter(fw);
			
			// create BufferedWriter that contains new FileWriter(filename)
			bw = new BufferedWriter(
					new FileWriter(fileName));
			
			try{
				bw.write(saveString);
				saved = true;
			}
			// guaranteed to run even if error encountered on write
			finally {
				bw.close();
			}
			// no catch needed on inner since the outer catch will catch anything
			// from this inner try/finally block
		}
		catch ( IOException ex) {
			ex.printStackTrace();
		}
		return saved;
		
	}
	
	public static String getStringFromFile(String fileName) {
		BufferedReader br = null;
		// StringBuilder optimized for adding strings together
		// much faster for large iterations
		StringBuilder sb = new StringBuilder();
		
		try {
			br = new BufferedReader(
					new FileReader(fileName));
			try {
				String s;
				// readline() reads one line at a time ( until \n )
				//  doesn't include the linefeed chars
				while ((s = br.readLine()) != null){
					// add linefeed back since stripped by readline
					sb.append(s);
					sb.append("\n");
				} 
			}
			// always runs so good place to close file
			finally {
				br.close();
			}
			
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		// StringBuilder, not a String so convert to one
		return sb.toString();
	}

	public static String convertToXML(MyLibrary ml) {
		// create XStream object to get at the methods
		XStream xstream = new XStream(new DomDriver());
		// tell xstream to add IDs for object references WITH optional aliases or 'reference paths'
		xstream.setMode(XStream.ID_REFERENCES);
		
		// adding alias commands
		xstream.alias("person", Person.class);
		xstream.alias("book", Book.class);
		xstream.alias("mylibrary", MyLibrary.class);
	
		// convert MyLibrary to string
		return xstream.toXML(ml);
	}

	public static MyLibrary convertFromXML(String XMLString) {
		MyLibrary ml = null;
		XStream xstream = new XStream(new DomDriver());
		// tell xstream to add IDs for object references WITH optional aliases or 'reference paths'
		xstream.setMode(XStream.ID_REFERENCES);
		
		// adding alias commands
		xstream.alias("person", Person.class);
		xstream.alias("book", Book.class);
		xstream.alias("mylibrary", MyLibrary.class);
		
		// xstream doesn't know what XMLString is, so use obj
		Object obj = xstream.fromXML(XMLString);
		// Check if the returned object is a 'MyLibrary' object
		if ( obj instanceof MyLibrary) {
			// case ml as MyLibrary from obj
			ml = (MyLibrary) obj;
		}
		return ml;
	}
	
	/*
	 * Converts a MyLibrary object to XML and saves to fileName
	 */
	public static boolean saveMyLibraryToXMLFile(String fileName,
			MyLibrary ml) {
		return saveStringToFile(fileName, convertToXML(ml));
	}

	/*
	 * Gets a MyLibary XML object from file, then converts
	 *  to a MyLibrary object
	 */
	public static MyLibrary getMyLibraryFromXMLFile(String fileName) {
		return convertFromXML(getStringFromFile(fileName));
	}

	public static boolean saveMyLibraryToSerialFile(String fileName,
			MyLibrary ml) {
		boolean saved = false;
		try {
			// writes byte data
			// Buffers output to improve performance
			// embed oos to bos

			//FileOutputStream fos = new FileOutputStream(fileName);
			//BufferedOutputStream bos = new BufferedOutputStream(fos);
			//ObjectOutputStream oos = new ObjectOutputStream(bos);

			ObjectOutputStream oos = new ObjectOutputStream(
					new BufferedOutputStream(
							new FileOutputStream(fileName)));

			// inner try
			try {
				// does the serialization
				// this will fail until the Serializable Interface is added to all classes
				oos.writeObject(ml);
				saved = true;
			} 
			finally {
				oos.close();
			}
			
		}
		// catch all exceptions as too many possibilities
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return saved;
	}

	public static MyLibrary getMyLibraryFromSerialFile(String fileName) {
		MyLibrary ml = null;
		try {
			ObjectInputStream ois = 
					new ObjectInputStream(
							new BufferedInputStream(
									new FileInputStream(fileName)));
			try {
				Object obj = ois.readObject();
				if ( obj instanceof MyLibrary) {
					ml = (MyLibrary) obj;
				}
			}
			finally {
				ois.close();
			}
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return ml;
	}

	
	
	
	

}
