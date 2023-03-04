package uiView;

/**

The SolveButton class represents a button that solves the maze in the drawing area.
It extends the JButton class and implements the ActionListener interface.
When clicked, the button checks if the maze has a single departure and a single arrival,
and that there is a path between the two. If so, it shows the path; otherwise, it displays an error message.
*/

import javax.swing.JButton;
import javax.swing.JOptionPane;

import mazeExceptions.NoPath;
import mazeExceptions.NumberOfDepartureOrArrival;
import uimodel.DrawMazeModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SolveButton extends JButton implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final DrawingApp drawingApp ;

	/**
	 * Constructor for SolveButton
	 * 
	 * @param drawingApp The drawing app to associate with this button
	 */
	public SolveButton(DrawingApp drawingApp) {
		super("Solve"); // Button's text
		this.drawingApp = drawingApp;
		addActionListener(this);
	}

	/**
	 * Action performed method of ActionListener
	 * 
	 * @param e The ActionEvent to be performed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DrawMazeModel drawMazeModel = drawingApp.getDrawMazeModel();

		try {
			if (drawMazeModel.countNumberOfArrival() != 1)
				throw new NumberOfDepartureOrArrival("arrival", drawMazeModel.countNumberOfArrival());
			if (drawMazeModel.countNumberOfDeparture() != 1)
				throw new NumberOfDepartureOrArrival("departure", drawMazeModel.countNumberOfDeparture());
			if (drawMazeModel.pathLength() == 2)
				throw new NoPath();
			else {
				drawMazeModel.setShowPath(true);
			}
		} catch (NumberOfDepartureOrArrival ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			drawMazeModel.setEmptyMaze();
			// ex.printStackTrace();
			drawMazeModel.setShowPath(false);
		} catch (NoPath e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			drawMazeModel.setEmptyMaze();
			e1.printStackTrace();
			drawMazeModel.setShowPath(false);
		}
	}
}
