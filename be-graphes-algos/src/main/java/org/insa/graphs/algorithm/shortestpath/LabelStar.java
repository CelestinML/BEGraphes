package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Node;

//import org.insa.graphs.algorithm.shortestpath.ShortestPathData;

public class LabelStar extends Label {
	
	private Node node_dest;
	
	private ShortestPathData data;
	
	public LabelStar(Node sommet_courant, Node node_dest, ShortestPathData data) {
		super(sommet_courant);
		this.node_dest = node_dest;
		this.data = data;
	}
	
	public double getTotalCost() {
		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			return this.getCost() + this.getNode().getPoint().distanceTo(this.node_dest.getPoint());
		}
		else {
			return this.getCost() + this.getNode().getPoint().distanceTo(this.node_dest.getPoint())/this.data.getMaximumSpeed();
		}
	}
}
