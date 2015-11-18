import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Esta classe implmenta o painel onde as mensagens s‹o escritas e apresentadas.
 * @author GLFA
 *
 */
public class MessagePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JButton sendButton;
	private JTextField writingTextField;
	private JTextArea readingTextArea;
	private ClientWindow clientWindow;
	private ButtonActionListener listener;

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

		writingTextField.addKeyListener(listener);

		writingPanel.add(writingTextField, BorderLayout.CENTER);
		writingPanel.add(sendButton, BorderLayout.EAST);

		add(writingPanel, BorderLayout.SOUTH);
	}

	/**
	 * 
	 */
	private void writeMessage(){

		String messageSent = "User: " + writingTextField.getText();
		readingTextArea.setText(readingTextArea.getText() + messageSent + "" +  "\n");
		writingTextField.setText("");
		Contact contact = clientWindow.getContactsPanel().getContactsList().getSelectedValue();
		
		Message message = new Message(contact, messageSent);

		contact.getMessages().add(message);

	}

	/**
	 * 
	 * @return	devolve a area de texto onde s‹o apresentadas as mensagens.
	 */
	public JTextArea getReadingTextArea() {
		return readingTextArea;
	}
	
	class ButtonActionListener implements ActionListener, KeyListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			writeMessage();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				writeMessage();
			}			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		
	}

}
