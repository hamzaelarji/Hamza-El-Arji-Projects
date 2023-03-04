package uiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import uimodel.DrawMazeModel;

/**
 * Class representing a "Save" menu item in the user interface.
 * When this menu item is selected, a dialog opens allowing the user to choose the location and file name to save
 * the maze drawing model.
 */
public class SaveMenuItem extends JMenuItem implements ActionListener {

	/**
	 * Version number of the class (for serialization).
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Reference to the maze drawing application that contains the drawing model.
	 */
	private final DrawingApp drawingApp ;

	/**
	 * Constructs a SaveMenuItem with the specified DrawingApp.
	 *
	 * @param drawingApp the DrawingApp that contains the drawing model to be saved.
	 */
	public SaveMenuItem(DrawingApp drawingApp) {
		super("Save"); // Text of menu item
		this.drawingApp = drawingApp;
		addActionListener(this);
	}

	/**
	 * Handles the user's action of selecting the "Save" menu item.
	 * Shows a dialog prompting the user whether to save the maze or not.
	 * If the user chooses to save the maze, a file chooser dialog opens to allow the user to select
	 * the location and file name to save the drawing model as a text file.
	 *
	 * @param evt the ActionEvent representing the user's action.
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		DrawMazeModel drawMazeModel = drawingApp.getDrawMazeModel();
		int response = JOptionPane.showInternalOptionDialog(this, "Maze not saved. Save it ?",
				"Save Maze", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);

		switch (response) {
			case JOptionPane.CANCEL_OPTION:
				return;
			case JOptionPane.OK_OPTION:
				try {
					JFileChooser fileChooser = new JFileChooser("data/");
					File fichier;
					fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
					fileChooser.setAcceptAllFileFilterUsed(false);
					if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						fichier = fileChooser.getSelectedFile();
						String filePath=fichier.getAbsolutePath();
						if (!filePath.endsWith(".txt")) {
							filePath = filePath + ".txt";
						}
						drawMazeModel.getLabyrinthel().saveToTextFile(filePath);
					}
				} catch (Exception e1) {
					// Do nothing if an exception occurs while saving the file.
				}
		}
	}
}

