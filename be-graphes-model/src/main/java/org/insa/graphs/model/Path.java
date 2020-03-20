package org.insa.graphs.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * Class representing a path between nodes in a graph.
 * </p>
 * 
 * <p>
 * A path is represented as a list of {@link Arc} with an origin and not a list
 * of {@link Node} due to the multi-graph nature (multiple arcs between two
 * nodes) of the considered graphs.
 * </p>
 *
 */
public class Path {

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the fastest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     * @deprecated Need to be implemented.
     */
    public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
        List<Arc> arcs = new ArrayList<Arc>();
        // TODO:
        return new Path(graph, arcs);
    }

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the shortest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     */
    public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	
        List<Arc> arcs = new ArrayList<Arc>();
        
        if (nodes.size() > 1) {
        
	        for (int num_node = 0; num_node < nodes.size()-1; num_node++) {
	        	
	        	//On recupere les arcs partant du node actuel
	        	List<Arc> successeurs = nodes.get(num_node).getSuccessors();
	        	
	        	//On definit un booleen pour determiner si on a deja trouve un arc entre les deux nodes
	        	boolean arc_trouve = false;
	        	
	        	for (int num_arc = 0; num_arc < successeurs.size(); num_arc++) {
	        		
	        		//On verifie que l'arc partant du node actuel arrive au suivant
	        		if (successeurs.get(num_arc).getDestination().compareTo(nodes.get(num_node+1)) == 0) {
	        			
	        			//Si cet arc arrive au node suivant, si aucun arc n'a encore ete trouve, on le choisit
	        			if (!arc_trouve) {
	        				arcs.add(successeurs.get(num_arc));
	        				arc_trouve = true;
	        			}
	        			//Si un autre arc a deja ete trouve, on le remplace si la distance du nouvel arc est plus courte
	        			else if (arcs.get(num_node).getLength() > successeurs.get(num_arc).getLength())
	        			{
	        				arcs.set(num_node, successeurs.get(num_arc));
	        			}
	        		}
	        	}
	        	
	        	//Si, en ayant parcouru tous les successeurs, on ne trouve pas le node suivant, on renvoie une exception
	        	if (!arc_trouve) {
	        		throw(new IllegalArgumentException());
	        	}
	        }
        }
        else if (nodes.size() == 1){
        	return new Path(graph, nodes.get(0));
        }
        else {
        	return new Path(graph);
        }
        
        return new Path(graph, arcs);
    }

    /**
     * Concatenate the given paths.
     * 
     * @param paths Array of paths to concatenate.
     * 
     * @return Concatenated path.
     * 
     * @throws IllegalArgumentException if the paths cannot be concatenated (IDs of
     *         map do not match, or the end of a path is not the beginning of the
     *         next).
     */
    public static Path concatenate(Path... paths) throws IllegalArgumentException {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
        }
        final String mapId = paths[0].getGraph().getMapId();
        for (int i = 1; i < paths.length; ++i) {
            if (!paths[i].getGraph().getMapId().equals(mapId)) {
                throw new IllegalArgumentException(
                        "Cannot concatenate paths from different graphs.");
            }
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        for (Path path: paths) {
            arcs.addAll(path.getArcs());
        }
        Path path = new Path(paths[0].getGraph(), arcs);
        if (!path.isValid()) {
            throw new IllegalArgumentException(
                    "Cannot concatenate paths that do not form a single path.");
        }
        return path;
    }

    // Graph containing this path.
    private final Graph graph;

    // Origin of the path
    private final Node origin;

    // List of arcs in this path.
    private final List<Arc> arcs;

    /**
     * Create an empty path corresponding to the given graph.
     * 
     * @param graph Graph containing the path.
     */
    public Path(Graph graph) {
        this.graph = graph;
        this.origin = null;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path containing a single node.
     * 
     * @param graph Graph containing the path.
     * @param node Single node of the path.
     */
    public Path(Graph graph, Node node) {
        this.graph = graph;
        this.origin = node;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path with the given list of arcs.
     * 
     * @param graph Graph containing the path.
     * @param arcs Arcs to construct the path.
     */
    public Path(Graph graph, List<Arc> arcs) {
        this.graph = graph;
        this.arcs = arcs;
        this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
    }

    /**
     * @return Graph containing the path.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return First node of the path.
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * @return Last node of the path.
     */
    public Node getDestination() {
        return arcs.get(arcs.size() - 1).getDestination();
    }

    /**
     * @return List of arcs in the path.
     */
    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    /**
     * Check if this path is empty (it does not contain any node).
     * 
     * @return true if this path is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.origin == null;
    }

    /**
     * Get the number of <b>nodes</b> in this path.
     * 
     * @return Number of nodes in this path.
     */
    public int size() {
        return isEmpty() ? 0 : 1 + this.arcs.size();
    }

    /**
     * Check if this path is valid.
     * 
     * A path is valid if any of the following is true:
     * <ul>
     * <li>it is empty;</li>
     * <li>it contains a single node (without arcs);</li>
     * <li>the first arc has for origin the origin of the path and, for two
     * consecutive arcs, the destination of the first one is the origin of the
     * second one.</li>
     * </ul>
     * 
     * @return true if the path is valid, false otherwise.
     */
    public boolean isValid() {
        boolean valid = false;
        if (this.isEmpty() || this.size() == 1) {
        	valid = true;
        }
        else {
        	//on met valid à true à la base, et on le remettra à false si un arc 
        	//n'est pas valide ou si l'origine du premier arc n'est pas l'origine du 
        	//chemin
        	valid = true; 
        	List<Arc> arcs = this.getArcs();
        	if (arcs.get(0).getOrigin().compareTo(this.getOrigin()) == 0) {
        		int num_arc = 0;
        		while (num_arc < arcs.size()-1 && valid) {
        			if (arcs.get(num_arc).getDestination().compareTo(arcs.get(num_arc+1).getOrigin()) != 0) {
        				valid = false;
        			}
        			num_arc++;
        		}
        	}
        	else {
        		valid = false;
        	}
        }
        return valid;
    }

    /**
     * Compute the length of this path (in meters).
     * 
     * @return Total length of the path (in meters).
     */
    public float getLength() {
    	float total_length = 0;
    	List<Arc> arcs = this.getArcs();
        for (int i = 0; i < arcs.size(); i++) {
        	//On incrémente pour chaque arc du chemin la distance totale
        	total_length += arcs.get(i).getLength();
        }
        return(total_length);
    }

    /**
     * Compute the time required to travel this path if moving at the given speed.
     * 
     * @param speed Speed to compute the travel time.
     * 
     * @return Time (in seconds) required to travel this path at the given speed (in
     *         kilometers-per-hour).
     */
    public double getTravelTime(double speed) {
    	float total_time = 0;
    	List<Arc> arcs = this.getArcs();
        for (int i = 0; i < arcs.size(); i++) {
        	//On incrémente pour chaque arc du chemin le temps total
        	total_time += arcs.get(i).getTravelTime(speed);
        }
        return(total_time);
    }

    /**
     * Compute the time to travel this path if moving at the maximum allowed speed
     * on every arc.
     * 
     * @return Minimum travel time to travel this path (in seconds).
     */
    public double getMinimumTravelTime() {
    	float total_time = 0;
    	List<Arc> arcs = this.getArcs();
        for (int i = 0; i < arcs.size(); i++) {
        	//On incrémente pour chaque arc du chemin le temps total
        	total_time += arcs.get(i).getMinimumTravelTime();
        }
        return(total_time);
    }

}
