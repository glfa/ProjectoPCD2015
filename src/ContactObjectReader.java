import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;


public class ContactObjectReader {

	private String filePath;
	private DefaultListModel<Contact> contactsList;

	/**
	 * @param filePath
	 * @param contactsList2
	 */
	public ContactObjectReader(String filePath, DefaultListModel<Contact> contactsList2) {
		super();
		this.filePath = filePath;
		this.contactsList = contactsList2;
	}

	public void loadContacts(){
		try{
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fis);
			System.out.println(in);

			Contact contact;



			while(in.available() != 0){
				contact = (Contact)in.readObject();
				contactsList.addElement(contact);
			}			
			in.close();
		}
		catch(IOException ex){ ex.printStackTrace(); }
		catch(ClassNotFoundException ex){ 
			ex.printStackTrace();
		}
	}
}
