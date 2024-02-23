package uiView ;

import javax.swing.* ;

/**
 * The MazeMenu class represents a menu for Maze related actions.
 */
public class MazeMenu extends JMenu {

	// Serial Version UID for serialization
	private static final long serialVersionUID = 1L;                                                                                                                                                                                                 @SuppressWarnings("unused")
	// Menu item to reset the maze
	private final ResetMenuItem resetMenuItem ;
	
	/**
	 * Creates a new instance of MazeMenu with the given DrawingApp.
	 *
	 * @param drawingApp the DrawingApp to associate with this menu
	 */
	public MazeMenu(DrawingApp drawingApp) {
		super("Maze") ; // Text of the menu
		add(resetMenuItem = new ResetMenuItem(drawingApp)) ; // Create and add menu items
	} 
	
}

