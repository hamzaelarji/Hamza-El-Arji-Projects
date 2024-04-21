//package telecommande_swing;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.*;

public class Telecommande extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static final String DEFAULT_HOST = "localhost";
	static final int DEFAULT_PORT = 3331;
	static String host = DEFAULT_HOST;
    static int port = DEFAULT_PORT;
	
	Client client;

	JTextArea text;
	JTextField name;
	JButton button1;
	JButton button2;
	JButton button3;
	
	AbstractAction action1;
	AbstractAction action2;
	AbstractAction action3;
	
	///
	/// Lit une requete depuis le Terminal, envoie cette requete au serveur,
	/// recupere sa reponse et l'affiche sur le Terminal.
	/// Noter que le programme bloque si le serveur ne repond pas.
	///
	public static void main(String[] args) {
		
		if (args.length >=1) host = args[0];
	    if (args.length >=2) port = Integer.parseInt(args[1]);
	    
	    new Telecommande();
	}
	
	public Telecommande() {
		client = null;
		
		button1 = new JButton("Search");
		button2 = new JButton("Play");
		button3 = new JButton("Close");
		
		text = new JTextArea(10, 50);
		JScrollPane scroll = new JScrollPane(text);
		add(scroll, BorderLayout.NORTH);
		
		name = new JTextField();
		add(name, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		JMenuBar menus = new JMenuBar();
		JMenu menuD = new JMenu("Menu");
		JToolBar barre = new JToolBar();
		
		barre.add(new ActionSearch("Search Media", this));
		barre.add(new ActionPlay("Play", this));
		barre.add(new ActionClose("Close"));
		
		menuD.add(new ActionSearch("Search", this));
		menuD.add(new ActionPlay("Play", this));
		menuD.add(new ActionClose("Close"));
		
		menus.add(menuD);
		menus.add(barre);
		setJMenuBar(menus);
		
		button1.addActionListener(new SearchListener(this));
		button2.addActionListener(new PlayListener(this));
		button3.addActionListener(new CloseListener());
		
		buttonPanel.add(button1);
		buttonPanel.add(button2);
		buttonPanel.add(button3);
		add(buttonPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Telecommande");
		pack();
		setVisible(true);
		
		try {
		      client = new Client(host, port);
		}
		catch (Exception e) {
			text.append("Client: Couldn't connect to "+host+":"+port);
		    System.exit(1);
		}
		
		text.append("Client connected to "+host+":"+port+"\n");
	}
	
	private void send(String command) {
		text.append("Request: " + command + "\n");
		String response = client.send(command);
		text.append("Response: " + response + "\n");
	}
	
	class SearchListener implements ActionListener {
		Telecommande tel;
		
		public SearchListener(Telecommande tel) {
			this.tel = tel;
		}
		
		public void actionPerformed(ActionEvent e) {
			tel.send("searchO " + tel.name.getText());
		}
	}
	
	class PlayListener implements ActionListener {
		Telecommande tel;
		
		public PlayListener(Telecommande tel) {
			this.tel = tel;
		}
		
		public void actionPerformed(ActionEvent e) {
			tel.send("play " + tel.name.getText());		
		}
	}
	
	class CloseListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);	
		}
	}
	
	class ActionSearch extends AbstractAction {
		private static final long serialVersionUID = 1L;
		Telecommande tel;
		
		public ActionSearch(String name, Telecommande tel) {
			super(name);
			this.tel = tel;
		}
		
		public void actionPerformed(ActionEvent e) {
			tel.send("searchO " + tel.name.getText());		
		}
		
	}
	
	class ActionPlay extends AbstractAction {
		private static final long serialVersionUID = 1L;
		Telecommande tel;
		
		public ActionPlay(String name, Telecommande tel) {
			super(name);
			this.tel = tel;
		}
		
		public void actionPerformed(ActionEvent e) {
			tel.send("play " + tel.name.getText());	
		}
	}
	
	class ActionClose extends AbstractAction {
		private static final long serialVersionUID = 1L;
		
		public ActionClose(String name) {
			super(name);
		}
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);	
		}
	}

}
