package graph;

import java.util.ArrayList;
import java.util.HashMap;

public class ShortestPathsImpl implements ShortestPathsInterface {

	private HashMap<VertexInterface, VertexInterface> shortestPath;

	/**
	 * Constructs a new ShortestPathsImpl object with an empty shortestPath HashMap.
	 */
	public ShortestPathsImpl() {
		shortestPath = new HashMap<VertexInterface, VertexInterface>();
	}

	/**
	 * Returns the previous vertex in the shortest path from the given vertex.
	 *
	 * @param vertex the vertex whose previous vertex is to be returned
	 * @return the previous vertex in the shortest path from the given vertex
	 */
	public VertexInterface previous(VertexInterface vertex) {
		return shortestPath.get(vertex);
	}

	/**
	 * Sets the previous vertex for the given vertex in the shortest path.
	 *
	 * @param vertexpere the previous vertex in the shortest path
	 * @param vertexfils the vertex for which the previous vertex is being set
	 */
	public void setPrevious(VertexInterface vertexpere, VertexInterface vertexfils) {
		shortestPath.put(vertexfils, vertexpere);
	}

	/**
	 * Returns the shortest path from the given vertex as an ArrayList.
	 *
	 * @param vertex the vertex for which the shortest path is to be returned
	 * @return the shortest path from the given vertex as an ArrayList
	 */
	public ArrayList<VertexInterface> getShortestPath(VertexInterface vertex) {
		ArrayList<VertexInterface> shortestPath1 = new ArrayList<VertexInterface>();
		while (vertex != null) {
			shortestPath1.add(vertex);
			vertex = previous(vertex);
		}
		return shortestPath1;
	}
}
