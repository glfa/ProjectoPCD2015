import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

/**
 * Esta classe implementa a gravacao de objectos do tipo 'Contacto' para um dado ficheiro.
 * @author GLFA
 *
 */
public class ContactObjectWriter{

	/**
	 * Grava uma lista do tipo 'DefaultListModel' para um dado ficheiro, 
	 * implementando a serializacao de objectos.
	 * 
	 * @param filePath	Destino do ficheiro onde os ficheiros vao ser gravados.
	 * @param contactsList	Lista de ficheiros que ir‡ ser gravada.
	 */
	public static void saveContactsToFile(String filePath, DefaultListModel<Contact> contactsList){
		
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
	 * Converte a DefaultListModel num ArrayList.
	 * @param contactDLM
	 * @return ArrayList 
	 */
	private static ArrayList<Contact> DLMtoArrayList(DefaultListModel<Contact> contactDLM){
		
		ArrayList<Contact> contacts = new ArrayList<Contact>();
		
		for (int i = 0; i < contactDLM.getSize(); i++) {
			contacts.add(contactDLM.getElementAt(i));
		}
		
		
		return contacts;
	}
}

