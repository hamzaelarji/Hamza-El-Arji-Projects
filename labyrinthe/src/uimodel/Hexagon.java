package uimodel;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import maze.Maze;

/**
 * The `Hexagon` class represents a hexagonal cell in the maze UI.
 * It contains methods for generating and drawing the hexagon, as well as
 * for setting and getting its properties.
 */
public class Hexagon {

	  /**
     * The height of an equilateral triangle with side length 1.
     */
	private static final double H = Math.sqrt(3) / 2; // Height of an equilateral triangle with side length = 1 
	
	/**
     * The row and column coordinates of the hexagon.
     */
	private final int row;
	private final int col;
	
	/**
     * The length of one side of the hexagon.
     */
	private final double sideLength;
	
	/**
     * The type of box represented by the hexagon.
     */
	private final String boxType;
	
	/**
     * Whether the hexagon is currently in the path from the start to the end of the maze.
     */
	private boolean isInPath=false;
	
	/**
     * The `Polygon` object representing the hexagon.
     */
	private Polygon hex;

	/**
     * Creates a new `Hexagon` object with the given row, column, side length, and box type.
     *
     * @param r        the row coordinate of the hexagon
     * @param c        the column coordinate of the hexagon
     * @param a        the length of one side of the hexagon
     * @param boxType  the type of box represented by the hexagon
     */
	public Hexagon(int r, int c, double a, String boxType) {
		this.row = r;
		this.col = c;
		this.sideLength = a/Math.sqrt(Maze.getSize())*1.85;
		this.boxType=boxType;
		double cx = getCenterX();
		double cy = getCenterY();
		final int[] xs = new int[6];
		final int[] ys = new int[6];

		xs[0]= (int) (cx + 0);                                ys[0]= (int) (cy + sideLength);     
		xs[1]=(int) (cx - H * sideLength);                    ys[1]= (int) (cy + 0.5 * sideLength);
		xs[2]=(int) (cx - H * sideLength);                    ys[2]= (int) (cy - 0.5 * sideLength);
		xs[3]=(int) (cx + 0);                                 ys[3]= (int) (cy - sideLength); 
		xs[4]=(int) (cx + H * sideLength);                    ys[4]= (int) (cy - 0.5 * sideLength); 
		xs[5]=(int) (cx + H * sideLength);                    ys[5]= (int) (cy + 0.5 * sideLength); 

		this.hex = new Polygon(xs, ys, 6);
	}

	/**
     * Checks whether the given point is contained within the hexagon.
     *
     * @param p  the point to check
     * @return   true if the point is contained within the hexagon, false otherwise
     */
	public boolean contains(Point p) {
		return hex.contains(p);
	}

	 /**
     * Gets the x-coordinate of the center of the hexagon.
     *
     * @return  the x-coordinate of the center of the hexagon
     */
	public double getCenterX() {
		return 20+2 * H * sideLength * (col + (row % 2) * 0.5);
	}

	/**
     * Calculates the y-coordinate of the center of the hexagon.
     *
     * @return the y-coordinate of the center of the hexagon
     */
	public double getCenterY() {
		return 3 * sideLength / 2  * row+20;
	}
	
	 /**
     * Draws the hexagon on the given graphics context.
     *
     * @param g the graphics context to draw the hexagon on
     */
	public void drawHexagon(Graphics g) {
		g.setColor(this.getColor());
		g.fillPolygon(hex);
		g.setColor(Color.BLACK);
		g.drawPolygon(hex);
	}


	public Color getColor() {
		if((isInPath)) {
			return Color.CYAN;
		}
		if(boxType=="D") {
			return Color.GREEN;
		}
		if(boxType=="W") {
			return Color.BLACK;
		}
		if(boxType=="A") {
			return Color.YELLOW;
		}
		return Color.MAGENTA;

	}


	public void setIsInPath(boolean b) {
		this.isInPath=b;
	}

}


