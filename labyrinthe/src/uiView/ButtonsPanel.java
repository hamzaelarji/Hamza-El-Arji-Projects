package uiView;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * A JPanel that contains buttons for controlling the maze drawing app.
 * The ButtonsPanel has one row and five columns, and contains buttons for
 * selecting departure and arrival boxes, empty boxes, wall boxes, and
 * for solving the maze. When a button is clicked, it sets the selected
 * box type and the color of the color panel in the DrawingApp accordingly.
 */
public class ButtonsPanel extends JPanel {
	private static final long serialVersionUID = 1L;                                                                                                                         @SuppressWarnings("unused")
	// button for selecting arrival box type
	private final ArrivalBoxButton arrivalBox ;                                                                                                                              @SuppressWarnings("unused")
	// button for selecting departure box type
	private final DepartureBoxButton departureBox ;                                                                                                                         @SuppressWarnings("unused")
	// button for selecting empty box type
	private final EmptyBoxButton emptyBox ;                                                                                                                                                                                                                                                         @SuppressWarnings("unused")
	// button for selecting wall box type  
	private final WallBoxButton wallBox ;                                                                                                                                  @SuppressWarnings("unused")
	// button for solving the maze 
	private final SolveButton solve ;

	/**
	 * Constructs a new ButtonsPanel object with buttons for setting the type of box to draw
	 * and solving the maze.
	 * 
	 * @param drawingApp the DrawingApp object used to draw the maze
	 */
	public ButtonsPanel(DrawingApp drawingApp) {
		setLayout(new GridLayout(1,5)) ; // 1 row, 5 columns
		add(departureBox = new DepartureBoxButton(drawingApp)) ;
		add(arrivalBox = new ArrivalBoxButton(drawingApp)) ;
		add(emptyBox = new EmptyBoxButton(drawingApp)) ;
		add(wallBox = new WallBoxButton(drawingApp)) ;
		add(solve = new SolveButton(drawingApp)) ;
	}
}

