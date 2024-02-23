package test;

import java.util.ArrayList;

import graph.*;
import maze.ArrivalBox;
import maze.DepartureBox;
import maze.EmptyBox;
import maze.Maze;
import maze.MazeBox;
import maze.WallBox;
//oui ca marche

public class Main {

	/**
	 * Main method to execute the program.
	 * 
	 * @param args command line arguments (not used in this program)
	 */
	public static void main(String[] args) {
		
		// create the maze object and initialize it from the text file
		Maze labyrinthe = new Maze();
		labyrinthe.initFromTextFile("data/empty.maze");
		
		// get the departure and arrival vertices
		VertexInterface departure = (MazeBox) labyrinthe.getDeparturebox(labyrinthe);
		VertexInterface arrival = (MazeBox) labyrinthe.getArrivalbox(labyrinthe);
		
		// find the shortest path using Dijkstra's algorithm
		ShortestPathsInterface results = Dijkstra.dijkstra(labyrinthe,departure,arrival);
		
		// get the list of vertices on the shortest path
		ArrayList<VertexInterface> path;
		path = results.getShortestPath(arrival);
		
		// initialize an empty string to represent the maze
		String s="";
		
		// check if there is a path from departure to arrival
		if(!path.contains(departure) && !path.contains(arrival)) {
			System.out.println("Impossible to resolve");
		}
		else {
			// loop through all the boxes in the maze
			for( int i=0; i<Maze.getSize(); i++) {
				for( int j=0; j<Maze.getSize(); j++) {
					
					// get the current maze box
					MazeBox box= labyrinthe.getBoxes(j, i);
					
					// check if the current box is on the shortest path and not a departure or arrival box
					if((path.contains((VertexInterface)box))&& (box != labyrinthe.getBoxes(0, 0))  && (box.typeOfBox(box)!="D")&& (box.typeOfBox(box)!="A")) { 
						s+="§ ";
					}else {
						// otherwise, add the appropriate character to the string
						if(box instanceof EmptyBox ) {
							s+=". ";
						}
						if(box instanceof DepartureBox) {
							s+="D ";
						}
						if(box instanceof ArrivalBox) {
							s+="A ";
						}
						if(box instanceof WallBox) {
							s+="W ";
						}
					}
				} 
				// add a new line character to the string to move to the next row of the maze
				s+="\n";
			}
		}
		
		// print the shortest path and the maze representation to the console
		System.out.println(path);
		System.out.println(s);
	}
}






