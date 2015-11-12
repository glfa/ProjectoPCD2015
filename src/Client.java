import javax.swing.JOptionPane;


public class Client {


	private String name;
	private ClientWindow window;

	public Client() {

		settingUpClient();
		settingUpWindow();

	}

	private void settingUpWindow() {
		window = new ClientWindow(this);
	}

	private void settingUpClient() {
			name = JOptionPane.showInputDialog("Insira o seu nome:");
			while(name.isEmpty()) {
				name = JOptionPane.showInputDialog("ERRO: nenhum nome inserido. \n"
						+ "Insira o seu nome:");
			}
	}
	
	public String getName() {
		return name;
	}


	public static void main(String[] args) {
		new Client();
	
	System.out.println("oi dkjjnasdjkaksdjksdjk");
	}
	

}
