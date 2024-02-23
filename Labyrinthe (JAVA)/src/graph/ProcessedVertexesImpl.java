package graph;

import java.util.HashSet;

public class ProcessedVertexesImpl extends HashSet<VertexInterface> implements ProcessedVertexInterface {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new ProcessedVertexesImpl object.
	 */
	public ProcessedVertexesImpl() {
		super();
	}

	/**
	 * Adds a vertex to the set of processed vertices.
	 *
	 * @param vertex the vertex to be added to the set
	 */
	@Override
	public void addVertex(VertexInterface vertex) {
		this.add(vertex);
	}

	/**
	 * Checks if a vertex is already processed.
	 *
	 * @param vertex the vertex to be checked
	 * @return true if the vertex is already processed, false otherwise
	 */
	@Override
	public boolean isVertexProcessed(VertexInterface vertex) {
		return this.contains(vertex);
	}
}
