package graph;

import java.util.HashMap;

/**
 * Implementation of the MinDistanceInterface for representing the minimum distance between a given source vertex
 * and each of the other vertices in a graph. This class extends the HashMap class and maps vertices to their minimum
 * distances represented as integers.
 */
public class MinDistanceImpl extends HashMap<VertexInterface, Integer> implements MinDistanceInterface {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MinDistanceImpl with an empty mapping of vertices to distances.
     */
    public MinDistanceImpl() {
        super();
    }

    /**
     * Returns the minimum distance from the given source vertex to the specified vertex, or Integer.MAX_VALUE if the
     * distance has not been set.
     *
     * @param vertex the vertex to get the minimum distance for
     * @return the minimum distance from the source vertex to the specified vertex
     */
    @Override
    public int getMinDistance(VertexInterface vertex) {
        return this.getOrDefault(vertex, Integer.MAX_VALUE);
    }

    /**
     * Sets the minimum distance from the source vertex to the specified vertex.
     *
     * @param distance the minimum distance to set
     * @param vertex the vertex to set the minimum distance for
     */
    @Override
    public void setNewDistance(int distance, VertexInterface vertex) {
        this.put(vertex, distance);
    }
}
