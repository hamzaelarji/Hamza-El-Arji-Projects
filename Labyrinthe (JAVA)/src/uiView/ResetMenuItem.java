package uiView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import uimodel.DrawMazeModel;

public class ResetMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;

	/**
	 * Creates a new ResetMenuItem object with the specified DrawingApp instance as its parent.
	 * Sets the text of the menu item to "Reset".
	 * Adds the instance as an action listener to handle user click events.
	 * 
	 * @param drawingApp the DrawingApp instance that will contain the menu item
	 */
	public ResetMenuItem(DrawingApp drawingApp) {
		super("Reset") ; // Text of menu item
		addActionListener(this);
		this.drawingApp = drawingApp ;
	}

	/**
	 * Handles the user's click event on the ResetMenuItem.
	 * Prompts the user with a confirmation dialog asking if they want to reset the maze.
	 * If the user confirms the reset, the maze will be cleared of any drawn walls or paths.
	 * 
	 * @param evt the ActionEvent representing the user's click event
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		DrawMazeModel drawMazeModel = drawingApp.getDrawMazeModel();
		int response = JOptionPane.showInternalOptionDialog(this, "Do you really want to reset ?",
		"Reset Maze", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

		switch (response) {
		case JOptionPane.CANCEL_OPTION :
			return;
		case JOptionPane.OK_OPTION :
			drawMazeModel.setEmptyMaze();
			break;

		case JOptionPane.NO_OPTION:
			break;
		}
	}
	
}


