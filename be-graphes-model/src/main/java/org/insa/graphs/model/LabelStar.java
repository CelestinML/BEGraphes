package org.insa.graphs.model;

public class LabelStar extends Label {
	
	private Node node_dest;
	
	public LabelStar(Node sommet_courant, Node node_dest) {
		super(sommet_courant);
		this.node_dest = node_dest;
	}
	
	public double getTotalCost() {
		return this.getCost() + this.getNode().getPoint().distanceTo(this.node_dest.getPoint());
	}
}
