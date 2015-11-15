import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ContactObjectWriter{


	private String filePath;
	private ArrayList<Contact> contactsList;

	/**
	 * @param filePath
	 * @param contactsList
	 */
	public ContactObjectWriter(String filePath, ArrayList<Contact> contactsList) {
		super();
		this.filePath = filePath;
		this.contactsList = contactsList;
	}

	public void saveContactsToFile(){

		try {
			FileOutputStream fos = new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fos); 
			for (Contact contact : contactsList) {
				out.writeObject(contact);
				System.out.println("gravei o: " + contact);
			}
			out.close();
		}
		catch(IOException ex)     {
			ex.printStackTrace();
		}
	}



	public static void main(String[] args) {




		ArrayList<Contact> contactsList = new ArrayList<Contact>();
		contactsList.add(new Contact("Mario"));
		contactsList.add(new Contact("Rui"));

		ContactObjectWriter contactObjectWriter = new ContactObjectWriter("/Users/GLFA/Documents/ISCTE/Workspace/ProjectoPCD2015/src/contact.ser", 
				contactsList);

		contactObjectWriter.saveContactsToFile();
		
		ContactObjectReader contactObjectReader = new ContactObjectReader("/Users/GLFA/Documents/ISCTE/Workspace/ProjectoPCD2015/src/contact.ser", 
				contactsList);
		
		contactObjectReader.loadContacts();
	}
}
