package graph;

import java.util.ArrayList;

/**
 * Computes the shortest path between the startVertex and the endVertex in the graph, using the Dijkstra algorithm.
 * @param graph The graph on which the algorithm is applied.
 * @param minDistance An implementation of the MinDistanceInterface to keep track of the minimum distance between the startVertex and each vertex of the graph.
 * @param processedVertex An implementation of the ProcessedVertexInterface to keep track of the processed vertices.
 * @param startVertex The vertex from which the algorithm starts.
 * @param endVertex The vertex to which the shortest path is sought.
 * @param shortestPath An implementation of the ShortestPathsInterface to keep track of the shortest path between the startVertex and each vertex of the graph.
 * @return An instance of ShortestPathsInterface representing the shortest path between the startVertex and the endVertex.
 */


public class Dijkstra {
	private static ShortestPathsInterface dijkstra (GraphInterface graph, 
													MinDistanceInterface minDistance , 
													ProcessedVertexInterface processedVertex ,
													VertexInterface startVertex, 
													VertexInterface endVertex , 
													ShortestPathsInterface shortestPath){
		
		

			ArrayList<VertexInterface> arrayListVertex1 = graph.getVertex();                                 // instancie et déclare un tableau avec les sommets
			processedVertex.addVertex(startVertex);
			int infinity= Integer.MAX_VALUE;                                                                 // permet de définir la constante infini
			for (VertexInterface vertex1 :arrayListVertex1) {                                                //initialise tous les sommets différents de startvertex à +infini
				minDistance.setNewDistance(infinity, vertex1);
				shortestPath.setPrevious(startVertex, vertex1);
			}
		                                                                                                     // le premier pivot est startvertex
			minDistance.setNewDistance(0, startVertex);
			shortestPath.setPrevious(null, startVertex);
			VertexInterface pivotVertex = startVertex;
			while(!processedVertex.isVertexProcessed(endVertex)) {                                           //Tant que processedvertex ne contient pas le sommet de fin endvertex
				for (VertexInterface newVertex : graph.getSuccVertex(pivotVertex)) {
					if ((!processedVertex.isVertexProcessed(newVertex))) {
						if (minDistance.getMinDistance(pivotVertex) + 1 < minDistance.getMinDistance(newVertex)) {
							int m = minDistance.getMinDistance(pivotVertex) + 1;
							minDistance.setNewDistance(m, newVertex);
							shortestPath.setPrevious(pivotVertex, newVertex);                                // définir le nouveau pere
						}
					}
			}
				VertexInterface nextVertex = endVertex;
				for (VertexInterface vertex : graph.getVertex()) {
					if ((minDistance.getMinDistance(vertex) < minDistance.getMinDistance(nextVertex))
							&& (!processedVertex.isVertexProcessed(vertex))) {
						nextVertex = vertex;
					}
				}
				pivotVertex = nextVertex;
				processedVertex.addVertex(pivotVertex);
			}
			return shortestPath;
		}

	
	public static ShortestPathsInterface dijkstra(GraphInterface graph, VertexInterface startVertex, VertexInterface endVertex) {
		MinDistanceImpl minDistance = new MinDistanceImpl();
		ProcessedVertexesImpl processedVertex = new ProcessedVertexesImpl();
		ShortestPathsImpl shortestPaths = new ShortestPathsImpl();
		return dijkstra(graph, minDistance, processedVertex, startVertex, endVertex, shortestPaths);
	}


}

