package uiView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The DrawingPanelMouseListener class implements the MouseListener interface to handle mouse events on a DrawingPanel.
 * It is used to detect mouse clicks on the hexagons of the maze and notify the DrawMazeModel accordingly.
 */
public class DrawingPanelMouseListener implements MouseListener {
	
	private DrawingApp drawingApp;
	
	public DrawingPanelMouseListener(DrawingApp drawingApp) {
		this.drawingApp = drawingApp;
	}
	
	/**
	 * Responds to a mouse click event on the DrawingPanel by calling the clickHexagon method in the DrawMazeModel.
	 * @param e the MouseEvent object that describes the mouse click
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		//drawingApp.getDrawMazeModel().clickSolve(e);
		drawingApp.getDrawMazeModel().clickHexagon(e);
		
	}

	/**
	 * Empty implementation of mousePressed method.
	 * @param e the MouseEvent object that describes the mouse press
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Empty implementation of mouseReleased method.
	 * @param e the MouseEvent object that describes the mouse release
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Empty implementation of mouseEntered method.
	 * @param e the MouseEvent object that describes the mouse enter
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Empty implementation of mouseExited method.
	 * @param e the MouseEvent object that describes the mouse exit
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}






