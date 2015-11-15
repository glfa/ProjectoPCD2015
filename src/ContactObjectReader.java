import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class ContactObjectReader {

	private String filePath;
	private ArrayList<Contact> contactsList;

	/**
	 * @param filePath
	 * @param contactsList
	 */
	public ContactObjectReader(String filePath, ArrayList<Contact> contactsList) {
		super();
		this.filePath = filePath;
		this.contactsList = contactsList;
	}

	public void loadContacts(){
		try{
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fis);
			Contact contact;
			
			System.out.println(in.available());
			
			while(in.available() == 0){
				contact = (Contact)in.readObject();
				System.out.println(contact);
			}			
			in.close();
		}
		catch(IOException ex){ ex.printStackTrace(); }
		catch(ClassNotFoundException ex){ 
			ex.printStackTrace();
		} 
	}
}
