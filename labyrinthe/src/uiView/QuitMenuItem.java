package uiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;


public class QuitMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;

	/**
	 * Constructs a new QuitMenuItem with the specified DrawingApp.
	 *
	 * @param drawingApp the DrawingApp instance to associate with the menu item.
	 */
	public QuitMenuItem(DrawingApp drawingApp) {
		super("Quit") ; // Text of menu item
		this.drawingApp = drawingApp ;
		addActionListener(this);
	}

	/**
	 * Invoked when an action occurs. Exits the application when the QuitMenuItem is clicked.
	 *
	 * @param evt the ActionEvent that occurred
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		drawingApp.dispose();
		drawingApp.setVisible(false);
	}
	
}


