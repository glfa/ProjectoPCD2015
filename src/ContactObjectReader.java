import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.DefaultListModel;

/**
 * Esta classe implementa a busca de de objectos do tipo 'Contacto' a um dado ficheiro.
 * @author GLFA
 *
 */
public class ContactObjectReader {

	/**
	 * Carrega os contactos para uma lista do tipo 'DefaultListModel' a partir dum ficheiro dado como argumento.
	 * @param filePath Path do ficheiro que contém os objectos 'Contacto'.
	 * @param contactsList	Destino dos contactos carregados do ficheiro.
	 */
	public static void loadContacts(String filePath, DefaultListModel<Contact> contactsList){
		
		try{
			FileInputStream fis = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fis);

			while(in.available() != 0){
				System.out.println("aqui");
				Contact	contact = (Contact)in.readObject();
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
