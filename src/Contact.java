import java.io.Serializable;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Contact implements Serializable{

	private String name;
	private ArrayList<Message> messages;
	
	public Contact(String name) {
		this.name = name;
		messages = new ArrayList<Message>();
	}

	public Contact() {
		messages = new ArrayList<Message>();
	}

	@Override
	public String toString() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	
}
