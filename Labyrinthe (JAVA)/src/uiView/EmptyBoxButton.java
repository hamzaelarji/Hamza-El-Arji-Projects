package uiView;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a button for selecting the empty box type in the DrawingApp GUI.
 * When clicked, this button updates the box type selection in the DrawMazeModel
 * and changes the color panel to magenta.
 */
public class EmptyBoxButton extends JButton implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final DrawingApp drawingApp ;

    /**
     * Constructs a new EmptyBoxButton object with the specified DrawingApp object.
     *
     * @param drawingApp the DrawingApp object to associate with this button.
     */
    public EmptyBoxButton(DrawingApp drawingApp) {
        super("Empty Box") ; // Button's text
        this.drawingApp = drawingApp ;
        addActionListener(this);
    }

    /**
     * Called when this button is clicked. Updates the box type selection in the
     * associated DrawMazeModel and changes the color panel to magenta.
     *
     * @param e the ActionEvent object representing the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        drawingApp.getDrawMazeModel().setBoxTypeSelected("E");
        drawingApp.getDrawMazeModel().setColorPanel(Color.MAGENTA);
    }

}
