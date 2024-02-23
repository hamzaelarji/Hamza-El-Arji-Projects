package maze;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JSpinner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import graph.GraphInterface;
import graph.VertexInterface;

/**
 * The `Maze` class is a representation of a maze as a graph, where each `MazeBox` is a vertex in the graph.
 * It implements the `GraphInterface` interface and provides methods to manipulate and access the graph
 * representation of the maze.
 */

public class Maze implements GraphInterface {
	
	/**
     * The size of the maze grid.
     */
	public static int size = getSize();
	
	/**
     * A 2D array of `MazeBox` objects representing the maze grid.
     */
	public MazeBox[][] hexagonalArray = new MazeBox[size][size];   // crée un tableau de dimension size contenant des MazeBox
	
	/**
     * Creates a new `Maze` object, initializing the `hexagonalArray` with `EmptyBox` objects.
     */
	public Maze() {
		for(int i = 0; i<size; i++) {
			for (int j =0; j<size; j++) {
				hexagonalArray[i][j] = new EmptyBox(i, j, this);
			}
		}
	}
	
	 /**
     * {@inheritDoc}
     */
	@Override
	public ArrayList<VertexInterface> getSuccVertex(VertexInterface vertex) {
	    if (vertex == null) {
	        throw new IllegalArgumentException("vertex cannot be null");
	    }
	    ArrayList<VertexInterface> succvertexes = new ArrayList<VertexInterface>();
	    MazeBox box = (MazeBox) vertex;
	    int i = box.getX();
	    int j = box.getY();
	    int x, y;
	    // For even columns
	    if (j % 2 == 0) {
	        // Top-left
	        x = i - 1;
	        y = j - 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	        // Top
	        x = i;
	        y = j - 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	        // Top-right
	        x = i + 1;
	        y = j;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	        // Bottom-right
	        x = i;
	        y = j + 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	        // Bottom-left
	        x = i - 1;
	        y = j + 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	        // Left
	        x = i - 1;
	        y = j;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	    }
	    // For odd columns
	    else {
	        // Top
	        x = i;
	        y = j - 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }

	        // Top-right
	        x = i + 1;
	        y = j - 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }

	        // Right
	        x = i + 1;
	        y = j;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }

	        // Bottom-right
	        x = i + 1;
	        y = j + 1;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }

	        // Bottom
	        x = i;
	        y = j + 1;
	        if (x >= 0 && y >= 0 && x < size && y < size&& !(box.isWallBox())) {
	        	succvertexes.add(hexagonalArray[x][y]);
	        }
	     // Bottom-left
	        x = i - 1;
	        y = j;
	        if (x >= 0 && y >= 0 && x < size && y < size && !(box.isWallBox())) {
	            succvertexes.add(hexagonalArray[x][y]);
	        }
	    }
	    return succvertexes;
	}
	
	/** je l'ai écris d'une autre maniere plus concise avec une syntaxe que l'on a pas vu en classe
	 * 
	 * public ArrayList<VertexInterface> getSuccVertex(VertexInterface vertex) {
		if (vertex == null) {
			throw new IllegalArgumentException("vertex cannot be null");
		}
		ArrayList<VertexInterface> succvertexes = new ArrayList<VertexInterface>();
		MazeBox box = (MazeBox) vertex;
		int i = box.getX();
		int j = box.getY();
		int[][] vertexes = (j % 2 == 0) ? new int[][]{{-1, -1}, {0, -1}, {+1, 0}, {0, +1}, {-1, +1}, {-1, 0}} : new int[][]{{0, -1}, {+1, -1}, {+1, 0}, {1, +1}, {0, 1}, {-1, 0}};

		for(int[] l : vertexes) {
			int x = i + l[0];
			int y = j +l[1];

			if( (x >= 0) && (y >= 0) && (x < size) && (y < size) && !(box instanceof WallBox)) {
				succvertexes.add(hexagonalArray[x][y]);
			}
		}
		return succvertexes;
	}
	 * 
	 */
	
	
	
	/**
    * Returns the size of the maze grid.
    *
    * @return the size of the maze grid
    */
	public static int getSize() {
		return size;
	}
	
	 /**
     * Sets the size of the maze grid.
     *
     * @param s the new size of the maze grid
     */
	public  void setSize(int s) {
		size = s;
	}
	
	/**
     * Sets the size of the maze grid from a JSpinner object.
     *
     * @param object the JSpinner object to get the size from
     */
	public static  void setSizeSp(int s) {
		size = s;
	}
	
	/**
     * Sets the size of the maze grid from a JSpinner object.
     *
     * @param object the JSpinner object to get the size from
     */
	public static void setSizeJspinner(Object object) {
		String s = ((JSpinner) object).getValue().toString();
		System.out.println(s);
	}
	
	/**
    * {@inheritDoc}
    */
	public final ArrayList<VertexInterface> getVertex() {               // renvoie la liste de toutes les cases et donc tous les sommets
		ArrayList<VertexInterface> allvertexes = new ArrayList<VertexInterface>();	
		for(MazeBox[] vertex : hexagonalArray){
			allvertexes.addAll(Arrays.asList(vertex));
		}
		return allvertexes;
	}
	
	/**
	 * Returns the maze box at the given coordinates.
	 *
	 * @param x The x-coordinate.
	 * @param y The y-coordinate.
	 * @return The maze box at the given coordinates.
	 */

	public MazeBox getBoxes(int x, int y) {
		return hexagonalArray[x][y];
	}
	
	/**
	 * Returns the distance between two adjacent vertices in the maze.
	 *
	 * @param src The source vertex.
	 * @param dst The destination vertex.
	 * @return The distance between the two vertices, or 0 if they are not adjacent.
	 */
	public int getDistance(VertexInterface src, VertexInterface dst) { // renvoie la distance entre deux sommets voisins

		int d=0;
		ArrayList<VertexInterface> successors = getSuccVertex(src) ;
		if(successors.contains(dst))return d+1;
		return d;
	}
	
	/**
	 * Initializes the maze from a text file.
	 *
	 * @param fileName The name of the text file.
	 */
	public final void initFromTextFile(String fileName) {
		MazeBox box = null;
		try { 
			FileReader file = new FileReader(fileName); 
			FileReader file2 = new FileReader(fileName);                    // Un flux qui se connecte au fichier texte
			BufferedReader bufferedReader = new BufferedReader(file);       // Connecte le FileReader au BufferedReader
			String line;
			char letter;
			int lineCount=0;
			while((line = bufferedReader.readLine())!= null) {
				lineCount+=1;
			}
			bufferedReader.close();
			setSize(lineCount);
			MazeBox[][] hexagonalArrayCopy = new MazeBox[size][size];
			lineCount=0;
			BufferedReader bufferedReader1 = new BufferedReader(file2);      // Connecte le FileReader au BufferedReader
			while((line = bufferedReader1.readLine())!= null) {
				for(int i=0; i<line.length(); i++) {
					letter=line.charAt(i);
					if(letter=='A') {
						box = new ArrivalBox(i,lineCount , this);
					}
					if(letter=='W') {
						box = new WallBox(i,lineCount , this);
					}
					if(letter=='D') {
						box = new DepartureBox(i,lineCount , this);
					}
					if(letter=='E') {
						box = new EmptyBox(i,lineCount , this);
					}
					hexagonalArrayCopy[i][lineCount]=box;
				}
				lineCount++;	
			}
			bufferedReader1.close(); // Ferme le flux
			this.hexagonalArray=hexagonalArrayCopy;
		}
		catch (Exception erreur) {
			erreur.printStackTrace();
		}
	}
	
	/**
	 * Saves the maze to a text file.
	 *
	 * @param fileName The name of the text file.
	 */
	public final void saveToTextFile(String fileName) {
		try {
			FileWriter file = new FileWriter(fileName) ;
			BufferedWriter bw  = new BufferedWriter(file) ; // pas besoin de le bufferiser
			int size = hexagonalArray.length ;
			MazeBox box;
			String chain ="";
			for(int i=0;i<size; i++) {
				for(int j=0; j<size; j++) {
					box=hexagonalArray[j][i];
					if(box.isArrivalBox()) {
						chain+="A";	
					}
					if(box.isDepartureBox()) {
						chain+="D";	
					}
					if(box.isEmptyBox()) {
						chain+="E";	
					}
					if(box.isWallBox()) {
						chain+="W";	
					}
				}
				chain+="\n";
			} 
			bw.write(chain);
			bw.close();
		}
		catch (Exception erreur) {
			erreur.printStackTrace();
		}

	}
	
	/**
	 * Returns the departure box of the given maze.
	 *
	 * @param maze the maze from which to get the departure box
	 * @return the departure box of the maze
	 */
	public VertexInterface getDeparturebox(Maze maze) {
		MazeBox box=hexagonalArray[0][0];
		int nbArrivalBox=0;
		for ( int i=0; i<size; i++) {
			for ( int j=0; j<size; j++) {
				if((hexagonalArray[i][j] instanceof DepartureBox)) {
					nbArrivalBox+=1;
					box= hexagonalArray[i][j];	
				}
			}
		}
		if(nbArrivalBox>2) {
			System.out.println("Erreur sur le nombre de case de départ");
		}
		return box;
	}
	
	/**
	 * Returns the arrival box of the given maze.
	 *
	 * @param maze the maze from which to get the arrival box
	 * @return the arrival box of the maze
	 */
	public  VertexInterface getArrivalbox(Maze maze) {
		MazeBox box=hexagonalArray[0][0];
		int nbArrivalBox=0;
		for ( int i=0; i<size; i++) {
			for ( int j=0; j<size; j++) {
				if((hexagonalArray[i][j] instanceof ArrivalBox)) {
					nbArrivalBox+=1;
					box= hexagonalArray[i][j];	
				}
			}
		}
		if(nbArrivalBox>2) {
			System.out.println("Erreur sur le nombre de case d'arrivée");
		}
		return box;
	}
	
	/**
	 * Sets the box at the specified location to an empty box.
	 *
	 * @param i the row index of the box to set
	 * @param j the column index of the box to set
	 * @param box the empty box to set at the specified location
	 */
	public void setEmptyBox(int i, int j, MazeBox box) {
		this.hexagonalArray[i][j] = new EmptyBox(i, j, this);
	}
	/**
	 * Sets the box at the specified location to the given box.
	 *
	 * @param i the row index of the box to set
	 * @param j the column index of the box to set
	 * @param box the box to set at the specified location
	 */
	public void setBox(int i, int j, MazeBox box) {
		this.hexagonalArray[i][j] = box;
	}
}
