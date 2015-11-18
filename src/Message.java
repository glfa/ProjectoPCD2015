
public class Message {

	private String sender, messageText;
	private Contact receiver;
	private MessageState messageState;

	public Message(Contact receiver, String messageSent) {
		
		messageState = MessageState.PENDING;
		this.receiver =	receiver;
		setMessageText(messageSent);
		
	}

	/**
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * @return the receiver
	 */
	public Contact getReceiver() {
		return receiver;
	}

	/**
	 * @return the messageText
	 */
	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText + " " + messageState;
	}

	public void setMessageState(MessageState messageState) {
		this.messageState = messageState;
	}



}
