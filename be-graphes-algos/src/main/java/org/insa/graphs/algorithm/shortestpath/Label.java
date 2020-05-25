package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

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
	
	public double getTotalCost() {
		return this.getCost();
	}
	
	public Node getNode() {
		return sommet_courant;
	}
	
	public Arc getFather() {
		return pere;
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
	
	protected boolean thresholdBasedFloatsComparison(double v1, double v2) 
	{
	    final double THRESHOLD = .0001;
	 
	    if (Math.abs(v1 - v2) < THRESHOLD)
	        return true;
	    else
	        return false;
	}
	
	@Override public int compareTo(Label other) {
		
		if (thresholdBasedFloatsComparison(this.getTotalCost(), other.getTotalCost())) {
			if (thresholdBasedFloatsComparison(this.getCost(), other.getCost())) {
				return 0;
			}
			else if (this.getCost() < other.getCost()) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else if (this.getTotalCost() < other.getTotalCost()) {
			return -1;
		}
		else {
			return 1;
		}
	}
}
