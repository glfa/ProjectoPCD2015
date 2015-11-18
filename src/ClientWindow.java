import java.awt.BorderLayout;

import javax.swing.JFrame;

public class ClientWindow {

	private ContactsPanel contactsPanel;
	private MessagePanel messagePanel;
	private JFrame frame;
	private Client client;

	public ClientWindow(Client client) {
		super();
		this.client = client;
		contactsPanel = new ContactsPanel(this);
		messagePanel = new MessagePanel(this);
		createWindow();
		frame.validate();
		frame.setVisible(true);
		
	}

	public ContactsPanel getContactsPanel() {
		return contactsPanel;
	}
	
	public MessagePanel getMessagePanel() {
		return messagePanel;
	}

	private void createWindow() {
		frame = new JFrame("QuequeApp | " + client.getName() + " | Status: ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 500);
		frame.setLocation(400, 0);
		
		frame.add(contactsPanel, BorderLayout.WEST);
		frame.add(messagePanel, BorderLayout.CENTER);
	}

}
