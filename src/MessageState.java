
public enum MessageState{

SENT("✓"), DELIVERED("✓✓");

	private final String state;
	
	private MessageState(String state) {
		this.state = state;
	}

	public String getValue(){ 
		return state; 
	}
}