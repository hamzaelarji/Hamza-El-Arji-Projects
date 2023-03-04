package uiView;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * The DepartureBoxButton class is a subclass of JButton and represents a button that sets the
 * box type selected to "D" (departure box) and the color of the color indicator in the user interface
 * to green when clicked.
 */
public class DepartureBoxButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;

	 /**
     * Constructs a new DepartureBoxButton with the specified DrawingApp as the owner of the button.
     * 
     * @param drawingApp the DrawingApp instance that owns this button
     */
	public DepartureBoxButton(DrawingApp drawingApp) {
		super("Departure Box") ; // Button's text
		this.drawingApp = drawingApp ;
		addActionListener(this);
	}
	
	/**
     * Sets the box type selected to "D" and the color of the color indicator to green when the button is clicked.
     * 
     * @param e the ActionEvent instance representing the click event
     */
	public void actionPerformed(ActionEvent e) {
		drawingApp.getDrawMazeModel().setBoxTypeSelected("D");
		drawingApp.getDrawMazeModel().setColorPanel(Color.GREEN);
	}
}


