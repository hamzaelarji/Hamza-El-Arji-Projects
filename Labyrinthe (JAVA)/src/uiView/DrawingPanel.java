package uiView;

import javax.swing.JPanel;

import maze.Maze;
import uimodel.Hexagon;
import java.awt.* ;


/**
 * The DrawingPanel class is a JPanel that displays the maze and is updated whenever the maze is changed.
 */
public class DrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;
	private final DrawingPanelMouseListener l;

	/**
     * Constructs a DrawingPanel object with the given DrawingApp object as its parent.
     * @param drawingApp the DrawingApp object that contains this panel
     */
	public DrawingPanel(DrawingApp drawingApp) {
		this.drawingApp = drawingApp ;
		setBackground(Color.GRAY) ;
		//setPreferredSize(new Dimension(10000,10000)) ; // for pack() instruction
		l = new DrawingPanelMouseListener(drawingApp);
		addMouseListener(l);
	}

	/**
     * Notifies the panel that it needs to be updated.
     */
	public void notifyUpdate() {
		repaint();
	}

	/**
     * Paints the maze on the panel.
     * @param g the graphics object used to paint the maze
     */
	public void paintComponent(Graphics g) {
		Hexagon[][] hexagonList = drawingApp.getDrawMazeModel().getHexagonList();
		for(int i=0; i<Maze.getSize(); i++) {
			for(int j=0; j<Maze.getSize(); j++) {
				Hexagon hexagon = hexagonList[i][j];
				hexagon.drawHexagon(g);
				/**g.drawString(
						"(" + j + "," + i + ")", 
						(int)(hexagon.getCenterX() - 11), 
						(int)(hexagon.getCenterY() + 7)
						);*/
			}
		}
	}
	
}

