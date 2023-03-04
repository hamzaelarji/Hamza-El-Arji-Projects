package uiView;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import maze.Maze;
import uimodel.DrawMazeModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A button to set the size of the maze
 */
public class SetSizeButton extends JButton implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final DrawingApp drawingApp;

    /**
     * Creates a new SetSizeButton with the specified DrawingApp
     * @param drawingApp The DrawingApp to use
     */
    public SetSizeButton(DrawingApp drawingApp) {
        super("Set Size"); // Button's text
        this.drawingApp = drawingApp;
        addActionListener(this);
    }

    /**
     * Called when the button is clicked
     * @param e The ActionEvent that occurred
     */
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Select Maze Size");
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(150, 70);

        SpinnerModel model = new SpinnerNumberModel(
                2, //initial value
                0, //minimum value
                37, //maximum value
                1 //step
        );
        JSpinner sp = new JSpinner(model);
        sp.setBounds(50, 50, 50, 30);
        frame.add(sp);                                                                                                                                                                                                            @SuppressWarnings("unused")
        String value = model.getValue().toString();
        frame.add(label);
        frame.setSize(200, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        JButton okButton = new JButton("OK");
        okButton.setBounds(45, 90, 60, 50);
        frame.add(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                
            }
        });

        //when the up or down arrow is pressed
        sp.addChangeListener(new ChangeListener() {
        	public void stateChanged(ChangeEvent e) {
        		label.setText("Value: " + ((JSpinner) e.getSource()).getValue());
        		drawingApp.stateChanged(e);
        		int n = (int) sp.getValue();
        		Maze.setSizeSp(n);
        		drawingApp.repaint();
        		DrawMazeModel drawMazeModel = drawingApp.getDrawMazeModel();
        		drawMazeModel.setShowPath(false);

        	}
        });

        repaint();

    }
}


