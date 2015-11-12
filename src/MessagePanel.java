


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MessagePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton sendButton;
	JTextField writingTextField;
	JTextArea readingTextArea;
	ClientWindow clientWindow;
	ButtonActionListener listener;

	public MessagePanel(ClientWindow clientWindow) {
		this.clientWindow = clientWindow;
		listener = new ButtonActionListener();

		setLayout(new BorderLayout());
		createWritingPanel();
		createReadingPanel();

	}

	private void createReadingPanel() {
		readingTextArea = new JTextArea();
		readingTextArea.setEditable(false);

		add(readingTextArea, BorderLayout.CENTER);
	}

	private void createWritingPanel() {
		JPanel writingPanel = new JPanel();
		writingPanel.setLayout(new BorderLayout());

		writingTextField = new JTextField();

		sendButton = new JButton("Send");
		sendButton.addActionListener(listener);

		writingTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					writeMessage();
				}
			};
		});

		writingPanel.add(writingTextField, BorderLayout.CENTER);
		writingPanel.add(sendButton, BorderLayout.EAST);

		add(writingPanel, BorderLayout.SOUTH);
	}

	private void writeMessage(){

		String messageSent = "User: " + writingTextField.getText();
		readingTextArea.setText(readingTextArea.getText() + messageSent + "" +  "\n");
		writingTextField.setText("");
		Contact contact = clientWindow.getContactsPanel().getContactsList().getSelectedValue();
		
		Message message = new Message(contact, messageSent);

		contact.getMessages().add(message);

	}


	class ButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			writeMessage();
		}

	}

}
