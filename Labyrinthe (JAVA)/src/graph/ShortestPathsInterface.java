package graph;

import java.util.ArrayList;


public interface ShortestPathsInterface {
	VertexInterface previous(VertexInterface vertex);
	void setPrevious(VertexInterface vertexpere, VertexInterface vertexfils); // associe le sommet fils au sommet pere
	ArrayList<VertexInterface> getShortestPath(VertexInterface endVertex);
}
