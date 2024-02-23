package uiView;

/**

The WallBoxButton class represents a button that selects a wall box and sets the color of the maze box to black.
It extends the JButton class and implements the ActionListener interface.
*/

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WallBoxButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;

	/**
	 * Constructs a WallBoxButton with the specified DrawingApp.
	 * @param drawingApp The DrawingApp object that the button belongs to.
	 */
	public WallBoxButton(DrawingApp drawingApp) {
		super("Wall Box") ; // Button's text
		this.drawingApp = drawingApp ;
		addActionListener(this);
	}

	/**
	 * Sets the box type selected to "W" and the color of the maze box to black.
	 * @param e The action event that occurred.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		drawingApp.getDrawMazeModel().setBoxTypeSelected("W");
		drawingApp.getDrawMazeModel().setColorPanel(Color.BLACK);
	}
}

