package uiView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImportMenuItem extends JMenuItem implements ActionListener {
	
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;                                                                                                                                                                                     @SuppressWarnings("unused")
	private final DrawingApp drawingApp ;

	/**
	 * Constructor for the import menu item
	 * @param drawingApp the DrawingApp instance to link the menu item to
	 */
	public ImportMenuItem(DrawingApp drawingApp) {
		super("Import") ; // Text of menu item
		this.drawingApp = drawingApp ;
		addActionListener(this);
	}

	/**
	 * Action performed when the import menu item is selected
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			PrintWriter sortie;
			File fichier;
			JFileChooser fileChooser = new JFileChooser(new File("data/Labyrinthe"));
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.txt", "txt"));


			if (fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
				fichier = fileChooser.getSelectedFile();

				sortie = new PrintWriter(new FileWriter(fichier.getPath(), true));
				// mazeAppFrame.getLabyrintheAppModel().newGame();

				drawingApp.getDrawMazeModel().initFromTextFile(fichier.getPath());
				//  mazeAppFrame.getLabyrintheAppModel().stateChanges();
				sortie.close();
			}

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(drawingApp,"Erreur fichier","Message d'erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

}

