package uiView;

import javax.swing.JMenuBar;

/**
 * A custom menu bar for the DrawingApp window. It contains two menus: File and Maze.
 */
public class DrawingMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;                                                                                                                                                  @SuppressWarnings("unused")
	private final FileMenu fileMenu ;                                                                                                                                                                 @SuppressWarnings("unused")
	private final MazeMenu mazeMenu ;

	/**
     * Constructs a new `DrawingMenuBar` with the specified `DrawingApp` instance.
     * Adds the `FileMenu` and `MazeMenu` to the menu bar.
     * @param drawingApp the `DrawingApp` instance to associate with the menu bar.
     */
	public DrawingMenuBar(DrawingApp drawingApp) {
		super() ;
		add(fileMenu = new FileMenu(drawingApp)) ; // Create and add menus
		add(mazeMenu = new MazeMenu(drawingApp)) ;
	} 

}

