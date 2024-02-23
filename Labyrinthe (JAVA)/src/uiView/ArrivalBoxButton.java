package uiView;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a button that sets the box type to "A" (arrival box) when clicked and updates the color panel to yellow in a DrawingApp.
 */
public class ArrivalBoxButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;
	
	/**
	 * Constructs a new ArrivalBoxButton with the specified DrawingApp as its parent component.
	 *
	 * @param drawingApp the DrawingApp to which this button belongs
	 */
	public ArrivalBoxButton(DrawingApp drawingApp) {
		super("Arrival Box") ; // Button's text
		this.drawingApp = drawingApp ;
		addActionListener(this);
	}

	/**
	 * Sets the box type to "A" (arrival box) and updates the color panel to yellow in the DrawingApp associated with this button.
	 *
	 * @param e the ActionEvent that occurred (ignored)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		drawingApp.getDrawMazeModel().setBoxTypeSelected("A");
		drawingApp.getDrawMazeModel().setColorPanel(Color.YELLOW);
	}

	
}

