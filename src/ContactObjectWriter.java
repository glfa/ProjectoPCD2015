import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

public class ContactObjectWriter{


	private String filePath;
	private DefaultListModel<Contact> contactsList;

	/**
	 * @param filePath
	 * @param contactsListModel
	 */
	public ContactObjectWriter(String filePath, DefaultListModel<Contact> contactsListModel) {
		super();
		this.filePath = filePath;
		this.contactsList = contactsListModel;
	}

	public void saveContactsToFile(){
		
		ArrayList<Contact> contactsArrayList = DLMtoArrayList(contactsList);
		
		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fos); 
		
			for (Contact contact : contactsArrayList) {
				out.writeObject(contact);
			}
			out.close();
		}
		catch(IOException ex)     {
			ex.printStackTrace();
		}
	}

	/**
	 * Converts a DefaultListModel list into a ArrayList.
	 * @param contactDLM
	 * @return ArrayList 
	 */
	private ArrayList<Contact> DLMtoArrayList(DefaultListModel<Contact> contactDLM){
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		for (int i = 0; i < contactDLM.getSize(); i++) {
			contacts.add(contactDLM.getElementAt(i));
		}
		
		
		return contacts;
	}
}

