import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.awt.Dimension;

public class RemoteControl extends JFrame
{
	private static final long serialVersionUID = 1L;
	/** Contains input to be sent throught sockets */
	private static JTextField textField;
	/** Contains result fetched from socket */
	private static JTextArea textArea;

	private Client client;

	public RemoteControl(Client client) {
		this.client = client;
	}

	public void display() {
		// Quit the program when the window is closed
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		textArea = new JTextArea(30, 30);
		textField = new JTextField("");
		textField.setColumns(30);

		JScrollPane scrollPane = new JScrollPane(textArea);
		JToolBar bar = new JToolBar();
		JPanel panel = new JPanel();

		SearchAction searchAction = new SearchAction("Search");
		PlayAction playAction = new PlayAction("Play");
		ListAction listAction = new ListAction("ListAll");
		CloseAction closeAction = new CloseAction("Close");

		JButton searchButton = new JButton(searchAction);
		JButton playButton = new JButton(playAction);
		JButton listButton = new JButton(listAction);
		JButton closeButton = new JButton(closeAction);

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.add(new JMenuItem(searchAction));
		menu.add(new JMenuItem(playAction));
		menu.add(new JMenuItem(listAction));
		menu.add(new JMenuItem(closeAction));

		menuBar.add(menu);

		setJMenuBar(menuBar);

		searchButton.setPreferredSize(new Dimension(100, 30));
		playButton.setPreferredSize(new Dimension(100, 30));
		listButton.setPreferredSize(new Dimension(100, 30));
		closeButton.setPreferredSize(new Dimension(100, 30));

		bar.add(searchButton);
		bar.add(playButton);
		bar.add(closeButton);
		bar.add(listButton);
		bar.add(textField);

		add(bar, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.SOUTH);

		pack();
		setVisible(true);
	}

	String parseResult(String result) {
		String response = result.replace("||", "\n");
		return response;
	}

	/**
	 * Calls client to send a SEARCH request to the server and shows the result in textArea
	 */
	class SearchAction extends AbstractAction {
		private static final long serialVersionUID = 201811031717L;
		public SearchAction(String name) {
			super(name);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String response = client.send("SEARCH " + textField.getText());
			response = parseResult(response);
			textArea.append(response);
		}

	}

	/**
	 * Calls client to send a PLAY request to the server and shows the result in textArea
	 */

	class PlayAction extends AbstractAction {
		private static final long serialVersionUID = 201811031718L;
		public PlayAction(String name) {
			super(name);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String response = client.send("PLAY " + textField.getText());
			response = parseResult(response);
			textArea.append(response);
		}
	}
	/**
	* Calls client to send a LIST request to the server and shows the result in textArea
	*/

	class ListAction extends AbstractAction {
		private static final long serialVersionUID = 201811031720L;
		public ListAction(String name) {
			super(name);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			String response = client.send("LIST");
			response = parseResult(response);
			textArea.append(response);
		}
	}

	/**
	* Close Java execution
	*/

	class CloseAction extends AbstractAction {
		private static final long serialVersionUID = 201811031719L;
		public CloseAction(String name) {
			super(name);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}
	}
}
