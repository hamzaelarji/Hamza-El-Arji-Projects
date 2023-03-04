package uiView;


import java.awt.Graphics;
import javax.swing.JPanel;
import uimodel.DrawMazeModel;

/**
 * A JPanel used to display the current color selected in the maze drawer.
 */
public class ColorIndicator extends JPanel {
	
	private static final long serialVersionUID = 1L;                                                                                                     @SuppressWarnings("unused")
	private final DrawingApp drawingApp;

	/**
	 * Creates a new ColorIndicator object.
	 * 
	 * @param drawingApp The parent DrawingApp instance.
	 */
	public ColorIndicator(DrawingApp drawingApp) {
		this.drawingApp = drawingApp;
		//setPreferredSize(new Dimension(100, 100));
	}
	
	/**
	 * Notifies the panel to update its view.
	 */
	public void notifyForUpdate() {
		repaint();
	}
	
	/**
	 * Paints the color indicator panel with the selected color in the DrawingApp.
	 * 
	 * @param graphics The Graphics object to paint on.
	 */
	protected final void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.setColor(DrawMazeModel.getColorPanel());
		graphics.fillRoundRect(-1, 4, 80, 365, 15, 15);
	}
	
}



