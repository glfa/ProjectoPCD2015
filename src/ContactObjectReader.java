import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ContactObjectReader {

	public static void main(String [] args){
		try{
			FileInputStream fis = new FileInputStream("comtact.ser");
			ObjectInputStream in = new ObjectInputStream(fis); 
			Contact contact = (Contact)in.readObject(); in.close();
		}
		catch(IOException ex){ ex.printStackTrace(); }
		catch(ClassNotFoundException ex){ 
			ex.printStackTrace();
		} 
	}
}
