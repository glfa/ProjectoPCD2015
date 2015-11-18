
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ContactsPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel contactsLabel;
	private JList<Contact> contactsList;
	private JButton addButton, removeButton;
	private DefaultListModel<Contact> contactsListModel;
	private ClientWindow clientWindow;
	private ButtonListener buttonListener;
	private Contact selectedContact;

	public ContactsPanel(ClientWindow clientWindow) {
		this.clientWindow = clientWindow;
		buttonListener = new ButtonListener();
		setLayout(new BorderLayout());

		setContactsLabel();
		setContactsList();
		setButtons();
	}

	private void setButtons() {
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout());

		addButton = new JButton("Add");
		removeButton = new JButton("Remove");

		addButton.addActionListener(buttonListener);
		buttonsPanel.add(addButton);

		removeButton.addActionListener(buttonListener);
		buttonsPanel.add(removeButton);

		add(buttonsPanel, BorderLayout.SOUTH);
	}

	private void insertContact(String contactName) {

		contactsListModel.addElement(new Contact(contactName));

	}

	private void setContactsList() {

		contactsListModel = new DefaultListModel<Contact>();

		contactsList = new JList<Contact>(contactsListModel);
		contactsList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		contactsList.setLayoutOrientation(JList.VERTICAL);
		contactsList.setVisibleRowCount(-1);
		contactsList.setBackground(Color.LIGHT_GRAY);
		contactsList.addListSelectionListener(buttonListener);

		loadContacts();


		add(contactsList, BorderLayout.CENTER);	

	}

	/**
	 * Implementa a 
	 */
	private void setContactsLabel() {
		contactsLabel = new JLabel("Contacts:");
		contactsLabel.setOpaque(true);
		contactsLabel.setBackground(Color.WHITE);
		add(contactsLabel, BorderLayout.NORTH);
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}

	public void loadContacts(){
		ContactObjectReader.loadContacts("/Users/GLFA/Documents/ISCTE/Workspace/ProjectoPCD2015/src/contact.ser",
				contactsListModel);
	}

	public void saveContacts(){
		ContactObjectWriter.saveContactsToFile("/Users/GLFA/Documents/ISCTE/Workspace/ProjectoPCD2015/src/contact.ser",
				contactsListModel);
	}

	public JList<Contact> getContactsList() {
		return contactsList;
	}

	public DefaultListModel<Contact> getContactsListModel() {
		return contactsListModel;
	}

	private void showMessages(Contact selectedContact) {
		
		for (Message message : selectedContact.getMessages()) {
			clientWindow.getMessagePanel().readingTextArea.setText(
					clientWindow.getMessagePanel().readingTextArea.getText()
					+ message.getMessageText() + "\n");
		}
		
	}

	class ButtonListener implements ActionListener, ListSelectionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == addButton){
				String contactName = JOptionPane.showInputDialog(null, "Contact Name:");
				insertContact(contactName);
			}
			if(e.getSource() == removeButton){
				contactsListModel.remove(contactsList.getSelectedIndex());
			}

		}

		@Override
		public void valueChanged(ListSelectionEvent e) {	

			clientWindow.getMessagePanel().readingTextArea.setText("");

			selectedContact = contactsListModel.get(contactsList.getSelectedIndex());
			showMessages(selectedContact);
		}

	}


}