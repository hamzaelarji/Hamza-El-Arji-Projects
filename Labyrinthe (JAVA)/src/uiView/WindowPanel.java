package uiView;


/**

The WindowPanel class represents a panel that contains the drawing panel and two buttons panels,
as well as a reference to the DrawingApp.
*/


import javax.swing.* ;
import java.awt.*;

public class WindowPanel extends JPanel {
	// Constants
	private static final long serialVersionUID = 1L;

	// Instance Variables
	private final DrawingPanel drawingPanel ;                                                                                                                                                                                              
	private final ButtonsPanel buttonsPanel ;                                                                                                                                                                                              
	private final ButtonsPanel2 buttonsPanel2;                                                                                                                                                                                              
	private final DrawingApp drawingApp;

	/**
	 * Constructs a new WindowPanel object with a reference to the given DrawingApp.
	 * Initializes and adds the DrawingPanel and the two ButtonsPanel to the WindowPanel.
	 * 
	 * @param drawingApp the DrawingApp object to which the WindowPanel belongs
	 */
	public WindowPanel(DrawingApp drawingApp) {
		this.drawingApp=drawingApp;
		setLayout(new BorderLayout());
		add(drawingPanel = new DrawingPanel(drawingApp), BorderLayout.CENTER);
		add(buttonsPanel = new ButtonsPanel(drawingApp), BorderLayout.SOUTH);
		add(buttonsPanel2 = new ButtonsPanel2(drawingApp), BorderLayout.EAST);
	}

	/**
	 * Notifies the DrawingPanel that an update has occurred and calls the repaint method.
	 */
	public void notifyUpdate() {
		drawingPanel.notifyUpdate() ;
		repaint();
	}

}
