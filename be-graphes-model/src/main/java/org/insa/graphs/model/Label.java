package org.insa.graphs.model;

public class Label  implements Comparable<Label> {
	private Node sommet_courant;
	private boolean marque;
	private double cout;
	private Arc pere;
	
	public Label(Node sommet_courant) {
		this.sommet_courant = sommet_courant;
		marque = false;
		cout = Double.POSITIVE_INFINITY;
		pere = null;
	}
	
	public double getCost() {
		return cout;
	}
	
	public Node getNode() {
		return sommet_courant;
	}
	
	public boolean isMarked() {
		return marque;
	}
	
	public void setFather(Arc pere) {
		
		this.pere = pere;
	}
	
	public void mark() {
		marque = true;
	}
	
	public void setCost(double cout) {
		this.cout = cout;
	}
	
	@Override public int compareTo(Label other) {
		if (this.cout < other.getCost()) {
			return -1;
		}
		else if (this.cout == other.getCost()) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
