package graph;

import java.util.ArrayList;

public interface GraphInterface {
	public ArrayList<VertexInterface> getSuccVertex(VertexInterface vertex); // renvoie la liste des voisins 
	public ArrayList<VertexInterface> getVertex();                          // renvoie la liste des sommets	
}

