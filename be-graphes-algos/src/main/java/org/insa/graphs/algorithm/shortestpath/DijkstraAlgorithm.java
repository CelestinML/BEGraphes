package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Label;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	
    	// Retrieve the graph.
        ShortestPathData data = getInputData();
        Graph graph = data.getGraph();
        List<Node> nodes = graph.getNodes();
        final int nbNodes = graph.size();

        ArrayList<Label> labels = new ArrayList<Label>();
        //On initialise tous les labels à +infini
        for (int i = 0; i < nbNodes; i++) {
        	labels.add(new Label(nodes.get(i)));
        }
        
        int index_origine = data.getOrigin().getId();
        labels.get(index_origine).setCost(0);
        
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        tas.insert(labels.get(index_origine));
        
        int index_dest = data.getDestination().getId();
        
        while (!labels.get(index_dest).isMarked()) {
        	Label label_min = tas.deleteMin();
        	labels.get(label_min.getNode().getId()).mark();
        	List<Arc> arcs = label_min.getNode().getSuccessors();
        	for (int i = 0; i < arcs.size(); i++) {
        		int index_suiv = arcs.get(i).getDestination().getId();
        		if (!labels.get(index_suiv).isMarked())
        		{
        			if (label_min.getCost() + arcs.get(i).getMinimumTravelTime() < labels.get(index_suiv).getCost()) {
        				labels.get(index_suiv).setCost(label_min.getCost() + arcs.get(i).getMinimumTravelTime());
        				labels.get(index_suiv).setFather(arcs.get(i));
        				tas.insert(labels.get(index_suiv));
        			}
        		}
        	}
        }

        ShortestPathSolution solution = null;

        
        
        return solution;
    }

}
