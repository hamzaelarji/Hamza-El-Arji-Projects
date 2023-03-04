package uiView;

import javax.swing.JFrame;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mazeExceptions.NoPath;
import uimodel.DrawMazeModel;


/**
 * The main window of the maze drawing application. This class extends
 * {@link javax.swing.JFrame} and implements the {@link javax.swing.event.ChangeListener}
 * interface to listen for changes in the underlying data model.
 */
public class DrawingApp extends JFrame implements ChangeListener  {

	private static final long serialVersionUID = 1L;                                                                                                                                                 @SuppressWarnings("unused")
	private final DrawingMenuBar drawingMenuBar ;
	private final WindowPanel windowPanel ;
	private DrawMazeModel drawMazeModel;

	 /**
     * Creates a new instance of the {@code DrawingApp} class.
	 * @throws NoPath 
     */
	public DrawingApp() throws NoPath{
		super("PROJET_LABYRINTHE_HAMZA_EL_ARJI_GROUPE_1") ;          // Window title
		setJMenuBar(drawingMenuBar = new DrawingMenuBar(this)) ;
		setContentPane(windowPanel = new WindowPanel(this)) ;
		drawMazeModel= new DrawMazeModel(this) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;             // Explicit 
		pack() ;                                                     // Components sizes and positions
		setVisible(true) ;                                           //  show the window
		drawMazeModel.stateChanged();
	}

	/**
     * Returns the data model used by this application.
     *
     * @return the data model used by this application
     */
	public DrawMazeModel getDrawMazeModel() {
		return this.drawMazeModel;
	}

    /**
     * Called when the underlying data model changes. This method updates the
     * window panel to reflect the changes in the data model.
     *
     * @param e the change event
     */

	@Override
	public void stateChanged(ChangeEvent e) {
		windowPanel.notifyUpdate();

	}




} 

