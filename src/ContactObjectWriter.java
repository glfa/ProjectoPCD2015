import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class ContactObjectWriter{

	public static void main(String [] args){
	     Contact contact = new Contact();
	     try {
	       FileOutputStream fos = new
	         FileOutputStream("contact.ser");
	ObjectOutputStream out = new ObjectOutputStream(fos); out.writeObject(contact);
	            out.close();
	      }
	      catch(IOException ex)     {
	        ex.printStackTrace();
	      }
	}
}
