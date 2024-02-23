package uiView;

import javax.swing.JFrame;
import mazeExceptions.NoPath;

/**
 * The Main class is used to start the DrawingApp application.
 */

public class Main {
	
	/**
	 * The main method creates a new instance of the DrawingApp and sets the size and visibility of the frame.
	 * @param args Command line arguments.
	 * @throws NoPath 
	 */
	public static void main(String[] args) throws NoPath{
		DrawingApp d = new DrawingApp() ;
		d.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d.setSize(1000, 790);
		d.setVisible(true);
	}
}
