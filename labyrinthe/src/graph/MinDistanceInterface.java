package graph;

public interface MinDistanceInterface {
	 int getMinDistance(VertexInterface vertex);                         // renvoie la distance minimale par à startvertex
	 void setNewDistance(int distance , VertexInterface vertex );        // mis à jour de la distance minimale
}

