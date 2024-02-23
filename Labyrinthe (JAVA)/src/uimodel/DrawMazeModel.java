package uimodel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import graph.Dijkstra;
import graph.ShortestPathsInterface;
import graph.VertexInterface;
import maze.ArrivalBox;
import maze.DepartureBox;
import maze.EmptyBox;
import maze.Maze;
import maze.MazeBox;
import maze.WallBox;
import uiView.DrawingApp;

/**
 *DrawMazeModel is a model for creating and handling the maze with hexagon boxes.
 *It handles the information and updates for the maze creation, modification, and visualization.
 *It also provides methods for importing, exporting, solving and getting a maze.
 */
public class DrawMazeModel {

	private List<ChangeListener> listeners = new LinkedList<ChangeListener>();                                                                                                                                                                     @SuppressWarnings("unused")
	private DrawingApp drawingApp;
	private String boxTypeSelected = null;
	public int size=Maze.getSize();
	public Hexagon[][] hexagonList;
	private boolean modified = false;
	public static boolean showPath = false;
	public int valb=0;
	Maze labyrinthe = new Maze();
	static Color colorPanel;

	/**
	 * Constructor of the DrawMazeModel class
	 * @param drawingApp the DrawingApp object that listens to the changes in the maze.
	 */
	public DrawMazeModel (DrawingApp drawingApp) {
		this.drawingApp = drawingApp;
		listeners.add(drawingApp);
		importMaze("data/start_labyrinthe.maze");
	}

	/**
	 * Gets the current maze object
	 * @return the Maze object containing the current maze.
	 */
	public Maze getLabyrinthel() {
		return this.labyrinthe;
	}

	/**
	 * Imports the maze from the given text file.
	 * @param s the path of the text file.
	 */
	public void importMaze(String s) {
		Maze labyrinthe = new Maze();
		labyrinthe.initFromTextFile(s);
		this.labyrinthe = labyrinthe;
		stateChanged();
	}

	/**
	 * Exports the current maze object to a text file.
	 * @param s the path of the text file to which the maze is saved.
	 */
	public void exportMaze(String s) {
		Maze labyrinthe = new Maze();
		labyrinthe.saveToTextFile(s);
		this.labyrinthe = labyrinthe;
		stateChanged();
	}

	/**
	 * Solves the current maze using Dijkstra's shortest path algorithm.
	 * @return an ArrayList containing the VertexInterface objects representing the shortest path.
	 */
	public ArrayList<VertexInterface> solve(){

		ShortestPathsInterface results = Dijkstra.dijkstra(labyrinthe, labyrinthe.getDeparturebox(labyrinthe), labyrinthe.getArrivalbox(labyrinthe));
		ArrayList<VertexInterface> path;
		path = (ArrayList<VertexInterface>) results.getShortestPath(labyrinthe.getArrivalbox(labyrinthe));
		System.out.println(path+"oui");
		return path;
	}

	public int pathLength(){
		int n=0;
		ShortestPathsInterface results = Dijkstra.dijkstra(labyrinthe, labyrinthe.getDeparturebox(labyrinthe), labyrinthe.getArrivalbox(labyrinthe));
		ArrayList<VertexInterface> path;
		path = (ArrayList<VertexInterface>) results.getShortestPath(labyrinthe.getArrivalbox(labyrinthe));
		for(@SuppressWarnings("unused") VertexInterface v : path) {
			n+=1;
		}
		return n;
	}
	
	/**
	 * Generates a 2D array of hexagon objects representing the current maze.
	 */
	public void generateHexagonList() {
		this.hexagonList = new Hexagon[Maze.getSize()][Maze.getSize()];
		ArrayList<VertexInterface> path= solve();
		for(int i=0; i<Maze.getSize(); i++) {
			for(int j=0; j<Maze.getSize(); j++) {
				MazeBox box = labyrinthe.getBoxes(j,i);
				double a =Math.sqrt(Maze.getSize())*6.7;
				Hexagon hexagon = new Hexagon(i, j, a, box.typeOfBox(box));
				if(showPath && (path.contains((VertexInterface)box))&& (box != labyrinthe.getBoxes(0, 0)) && (box.typeOfBox(box)!="D")&& (box.typeOfBox(box)!="A")) {
					hexagon.setIsInPath(true);	
				}
				hexagonList[i][j] = hexagon;
			}
		}

	}

	/**
	 * Returns the Hexagon list.
	 *
	 * @return the Hexagon list
	 */
	public Hexagon[][] getHexagonList() {
		return hexagonList;
	}

	/**
	 * Sets the maze to an empty maze and resets the state of the controller.
	 */
	public void setEmptyMaze() {
		labyrinthe.setSize(37);
		this.labyrinthe = new Maze();
		setShowPath(false);
		stateChanged();
	}

	/**
	 * Sets the box type selected by the user.
	 *
	 * @param s the box type selected
	 */
	public void setBoxTypeSelected(String s) {
		boxTypeSelected = s;
	}

	/**
	 * Counts the number of departure boxes in the maze.
	 *
	 * @return the number of departure boxes
	 */
	public int countNumberOfDeparture() {
		int count=0;
		for(int i=0; i<Maze.getSize(); i++) {
			for(int j=0; j<Maze.getSize(); j++) {
				MazeBox box = labyrinthe.getBoxes(j,i);
				if(box.typeOfBox(box)=="D") {
					count+=1;
				}
			}
		}
		return count;
	}

	/**
	 * Counts the number of arrival boxes in the maze.
	 *
	 * @return the number of arrival boxes
	 */
	public int countNumberOfArrival() {
		int count=0;
		for(int i=0; i<Maze.getSize(); i++) {
			for(int j=0; j<Maze.getSize(); j++) {
				MazeBox box = labyrinthe.getBoxes(j,i);
				if(box.typeOfBox(box)=="A") {
					count+=1;
				}
			}
		}
		return count;
	}

	/**
	 * Sets the flag to show or hide the path in the maze.
	 *
	 * @param b the flag to show or hide the path
	 */
	public void setShowPath(boolean b) {
		showPath=b;
		stateChanged();

	}

	/**
	 * Sets the color of the panel.
	 *
	 * @param color the color of the panel
	 */
	public void setColorPanel(Color color) {
		colorPanel=color;
	}

	/**
	 * Returns the color of the panel.
	 *
	 * @return the color of the panel
	 */
	public static  Color getColorPanel() {
		return colorPanel;
	}

	/**
	 * Handles the mouse click event on a hexagon by setting the appropriate box in the maze.
	 *
	 * @param e the mouse event
	 */
	public void clickHexagon(MouseEvent e) {
	    for (int i = 0; i < Maze.getSize(); i++) {
	        for (int j = 0; j < Maze.getSize(); j++) {
	            if (hexagonList[i][j].contains(e.getPoint())) {
	                if (boxTypeSelected != null) {
	                    MazeBox box;
	                    if (boxTypeSelected.equals("A")) {
	                        box = new ArrivalBox(j, i, labyrinthe);
	                    } else if (boxTypeSelected.equals("D")) {
	                        box = new DepartureBox(j, i, labyrinthe);
	                    } else if (boxTypeSelected.equals("W")) {
	                        box = new WallBox(j, i, labyrinthe);
	                    } else if (boxTypeSelected.equals("E")) {
	                        box = new EmptyBox(j, i, labyrinthe);
	                    } else {
	                        box = new EmptyBox(j, i, labyrinthe);
	                    }

	                    labyrinthe.setBox(j, i, box);
	                    stateChanged();
	                }
	            }
	        }
	    }
	}

	/**
	 * Returns the maze object for this instance of the game.
	 *
	 * @return the maze object for this game
	 */
	public Maze getMaze() {
		return labyrinthe;
	}

	/**
	 * Saves current state to a file.
	 */
	public void saveToFile() {
		System.out.println("Saved to file");
	}

	/**
	 * Adds a ChangeListener to the list of listeners to be notified of state changes.
	 * 
	 * @param listener The ChangeListener to add.
	 */
	public void addObserver(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Notifies all listeners that the state has changed and generates a new list of Hexagons.
	 */
	public void stateChanged() {
		generateHexagonList();
		ChangeEvent evt = new ChangeEvent(this);
		for (ChangeListener listener : listeners) {
			listener.stateChanged(evt);
		}
	}

	/**
	 * Checks whether the Maze has been modified.
	 * 
	 * @return true if the Maze has been modified, false otherwise.
	 */
	public final boolean isModified() {
		return modified;
	}


	/**
	 * Sets the modified flag for this instance of the game.
	 *
	 * @param modified the new value of the modified flag
	 */
	public final void setModified(boolean modified) {
		if (this.modified != modified)
			this.modified = modified;
	}

	/**
	 * Reads a maze from a text file and initializes the current maze with it.
	 *
	 * @param fileName the name of the file to read the maze from
	 */
	public final void initFromTextFile(String fileName) {
		this.importMaze(fileName);

	}

	/**
	 * Exports the current maze to a text file with the specified file name.
	 *
	 * @param fileName the name of the file to export the maze to
	 */
	public final void saveToTextFile(String fileName) {
		this.exportMaze(fileName);
	}
}


