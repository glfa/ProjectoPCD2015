
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Esta classe implmenta o painel de contactos da janela do cliente.
 * Aqui é mostrada uma lista de contactos.
 * Neste painel é possivel adicionar/remover contactos.
 * É neste painel que se vai selecionar os contactos cujas mensagens devem ser apresentadas
 * no noutro painel ('MessagePanel').
 * 
 * 
 * @author GLFA
 *
 */
public class ContactsPanel extends JPanel{

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

	/**
	 * Implementa os botões para adicionar e remover contacto.
	 */
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

	/**
	 * Insere novo contacto na lista 
	 * @param contactName Nome do novo contacto.
	 */
	private void insertContact(String contactName) {
		contactsListModel.addElement(new Contact(contactName));
	}

	/**
	 * Implementa a lista de contactos.
	 * Carrega-os a partir do ficheiro.
	 */
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
	 * Implementa a label onde estão posicionados os contactos.
	 */
	private void setContactsLabel() {
		contactsLabel = new JLabel("Contacts:");
		contactsLabel.setOpaque(true);
		contactsLabel.setBackground(Color.WHITE);
		add(contactsLabel, BorderLayout.NORTH);
	}

	/**
	 * 
	 * @return devolve o contacto selecionado da lista de contactos.
	 */
	public Contact getSelectedContact() {
		return selectedContact;
	}

	/**
	 * Carrega os contactos para a lista.\n
	 * Usa a classe 'ContactObjectReader'.
	 */
	public void loadContacts(){
		ContactObjectReader.loadContacts("/Users/GLFA/Documents/ISCTE/Workspace/ProjectoPCD2015/src/contact.ser",
				contactsListModel);
	}
	
	/**
	 * Grava os contactos da lista.\n
	 * Usa a classe 'ContactObjectWriter'.
	 */
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

	/**
	 * Mostra as mensagens dum certo contacto. Apresenta-as na zona 'readingTextArea'. \n
	 * @param selectedContact	Contacto cujas messagens serão apresentadas.
	 */
	private void showMessages(Contact selectedContact) {	
		
		for (Message message : selectedContact.getMessages()) {
			clientWindow.getMessagePanel().getReadingTextArea().setText(
					clientWindow.getMessagePanel().getReadingTextArea().getText()
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

			clientWindow.getMessagePanel().getReadingTextArea().setText("");

			selectedContact = contactsListModel.get(contactsList.getSelectedIndex());
			showMessages(selectedContact);
		}

	}


}