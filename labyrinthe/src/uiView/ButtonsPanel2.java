package uiView;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * The ButtonsPanel2 class represents a JPanel containing two buttons for
 * setting the size of the maze and displaying the current color.
 * 
 * This panel contains a SetSizeButton for setting the size of the maze, and a
 * ColorIndicator for displaying the current color. Both buttons are added to
 * the panel in a grid layout with 2 rows and 1 column.
 */
public class ButtonsPanel2 extends JPanel {
	

	/**
	 * The serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;                                                                                                                        @SuppressWarnings("unused")
	
	/**
	 * The SetSizeButton used to set the size of the maze.
	 */
	private final SetSizeButton setSizeButton ;                                                                                                                             @SuppressWarnings("unused")
	
	/**
	 * The ColorIndicator used to display the current color.
	 */
	private final ColorIndicator colorIndicator;
	
	/**
	 * Constructs a ButtonsPanel2 object with the given DrawingApp.
	 * 
	 * @param drawingApp the DrawingApp object to associate this panel with.
	 */
	public ButtonsPanel2(DrawingApp drawingApp) {
		setLayout(new GridLayout(2,1)) ; // 1 row, 2 columns
		add(setSizeButton = new SetSizeButton(drawingApp)) ;
		add(colorIndicator = new ColorIndicator(drawingApp)) ;
	}
	
}


