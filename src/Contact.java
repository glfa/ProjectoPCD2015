import java.util.ArrayList;


public class Contact{

	private String name;
	private ArrayList<Message> messages;
	
	public Contact(String name) {
		this.name = name;
		messages = new ArrayList<Message>();
	}

	@Override
	public String toString() {
		return name;
	}
	
	public ArrayList<Message> getMessages() {
		return messages;
	}
	
	
}
