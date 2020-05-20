package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;


import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.Test;

public class TestsDijkstraAStar {
	
    static Graph graph;
    static ArcInspector distance, temps;
    static ShortestPathData dataDistance, dataTemps;
    static double distanceMin, tempsMin, distanceMax, tempsMax;
    static DijkstraAlgorithm dijkstraDistance, dijkstraTemps;
    static BellmanFordAlgorithm bellmanDistance, bellmanTemps;
    static AStarAlgorithm aStarDistance, aStarTemps;
    static Path pathDijkstraDistance, pathDijkstraTemps, pathBellmanDistance, 
    		    pathBellmanTemps, pathAStarDistance, pathAStarTemps;
	static ShortestPathSolution solutionDijkstraTemps, solutionDijkstraDistance, solutionAStarTemps, 
	                            solutionAStarDistance, solutionBellmanTemps, solutionBellmanDistance;
    static String insa = "C:\\Users\\celes\\Desktop\\BEGraphes\\BEGraphes\\Carte\\insa.mapgr";
    static String carre = "C:\\Users\\celes\\Desktop\\BEGraphes\\BEGraphes\\Carte\\carre.mapgr";
    static String polynesie = "C:\\Users\\celes\\Desktop\\BEGraphes\\BEGraphes\\Carte\\french-polynesia.mapgr";
    
    public static void initDijkstraDistance(String mapName, int nodeOrigine, int nodeDest) throws IOException {
	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    distance = ArcInspectorFactory.getAllFilters().get(0);
	    //Instanciation des données
	    dataDistance = new ShortestPathData(graph, graph.get(nodeOrigine), graph.get(nodeDest), distance);
	    distanceMin = graph.get(nodeOrigine).getPoint().distanceTo(graph.get(nodeDest).getPoint());
	    
	    
	    //Instanciation des algos
	    dijkstraDistance = new DijkstraAlgorithm(dataDistance);
	    bellmanDistance = new BellmanFordAlgorithm(dataDistance);
	    
	    //Création des solutions
	    solutionBellmanDistance = bellmanDistance.run();
	    solutionDijkstraDistance = dijkstraDistance.run();
	    //Création des paths
	    pathDijkstraDistance = solutionDijkstraDistance.getPath();
	    pathBellmanDistance = solutionBellmanDistance.getPath();
	}
	
    public static void initDijkstraTemps(String mapName, int nodeOrigine, int nodeDest) throws IOException {
	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    temps = ArcInspectorFactory.getAllFilters().get(2);
	    //Instanciation des données
	    dataTemps = new ShortestPathData(graph, graph.get(nodeOrigine), graph.get(nodeDest), temps);
	    tempsMin = graph.get(nodeOrigine).getPoint().distanceTo(graph.get(nodeDest).getPoint())/(dataTemps.getGraph().getGraphInformation().getMaximumSpeed()/3.6);
	    
	    //Instanciation des algos
	    dijkstraTemps = new DijkstraAlgorithm(dataTemps);
	    bellmanTemps = new BellmanFordAlgorithm(dataTemps);
	
	    //Création des solutions
	    solutionBellmanTemps = bellmanTemps.run();
	    solutionDijkstraTemps = dijkstraTemps.run();
	    //Création des paths
	    pathDijkstraTemps = solutionDijkstraTemps.getPath();
	    pathBellmanTemps = solutionBellmanTemps.getPath();
	}
	
    public static void initAStarDistance(String mapName, int nodeOrigine, int nodeDest) throws IOException {
	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    distance = ArcInspectorFactory.getAllFilters().get(0);
	    //Instanciation des données
	    dataDistance = new ShortestPathData(graph, graph.get(nodeOrigine), graph.get(nodeDest), distance);
	    distanceMin = graph.get(nodeOrigine).getPoint().distanceTo(graph.get(nodeDest).getPoint());
	    
	    
	    //Instanciation des algos
	    bellmanDistance = new BellmanFordAlgorithm(dataDistance);
	    aStarDistance = new AStarAlgorithm(dataDistance);
	
	    //Création des solutions
	    solutionBellmanDistance = bellmanDistance.run();
	    solutionAStarDistance = aStarDistance.run();
	    //Création des paths
	    pathBellmanDistance = solutionBellmanDistance.getPath();
	    pathAStarDistance = solutionAStarDistance.getPath();
	}
    
    public static void initAStarTemps(String mapName, int nodeOrigine, int nodeDest) throws IOException {
    	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    temps = ArcInspectorFactory.getAllFilters().get(2);
	    //Instanciation des données
	    dataTemps = new ShortestPathData(graph, graph.get(nodeOrigine), graph.get(nodeDest), temps);
	    tempsMin = graph.get(nodeOrigine).getPoint().distanceTo(graph.get(nodeDest).getPoint())/(dataTemps.getGraph().getGraphInformation().getMaximumSpeed()/3.6);
	    
	    
	    //Instanciation des algos
	    bellmanTemps = new BellmanFordAlgorithm(dataTemps);
	    aStarTemps = new AStarAlgorithm(dataTemps);
	
	    //Création des solutions
	    solutionBellmanTemps = bellmanTemps.run();
	    solutionAStarTemps = aStarTemps.run();
	    //Création des paths
	    pathBellmanTemps = solutionBellmanTemps.getPath();
	    pathAStarTemps = solutionAStarTemps.getPath();
	}
	
	//On ne peut pas comparer directement des floats ou des doubles
	//On définit alors une méthode de comparaison qui instaure un seuil d'égalité
	public boolean thresholdBasedFloatsComparison(double v1, double v2) 
	{
	    final double THRESHOLD = .0001;
	 
	    if (Math.abs(v1 - v2) < THRESHOLD)
	        return true;
	    else
	        return false;
	}
	
	@Test
	public void comparaisonBellmanDijkstraDistanceInsa() throws IOException {
		initDijkstraDistance(insa, 400, 900);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void comparaisonBellmanDijkstraTempsInsa() throws IOException {
		initDijkstraTemps(insa, 400, 900);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void comparaisonBellmanAStarDistanceInsa() throws IOException {
		initAStarDistance(insa, 400, 900);
		assertTrue(thresholdBasedFloatsComparison(pathAStarDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void comparaisonBellmanAStarTempsInsa() throws IOException {
		initAStarTemps(insa, 400, 900);
		assertTrue(thresholdBasedFloatsComparison(pathAStarTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void comparaisonBellmanDijkstraDistanceCarre() throws IOException {
		initDijkstraDistance(carre, 20, 15);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void comparaisonBellmanDijkstraTempsCarre() throws IOException {
		initDijkstraTemps(carre, 20, 15);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void comparaisonBellmanAStarDistanceCarre() throws IOException {
		initAStarDistance(carre, 20, 15);
		assertTrue(thresholdBasedFloatsComparison(pathAStarDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void comparaisonBellmanAStarTempsCarre() throws IOException {
		initAStarTemps(carre, 20, 15);
		assertTrue(thresholdBasedFloatsComparison(pathAStarTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void realisticTestAStar() throws IOException {
		initAStarTemps(insa, 400, 900);
		assert tempsMin < pathAStarTemps.getMinimumTravelTime();
		initAStarDistance(insa, 400, 900);
		assert distanceMin < pathAStarDistance.getLength();
	}
	@Test
	public void realisticTestDijkstra() throws IOException {
		initDijkstraTemps(insa, 400, 900);
		assert tempsMin < pathDijkstraTemps.getMinimumTravelTime();
		initDijkstraDistance(insa, 400, 900);
		assert distanceMin < pathDijkstraDistance.getLength();
	}
	
	@Test
	public void impossiblePathDijkstra() throws IOException {
		initDijkstraTemps(polynesie, 0, 24);
		assertFalse(solutionDijkstraTemps.isFeasible());
		initDijkstraDistance(polynesie, 0, 24);
		assertFalse(solutionDijkstraDistance.isFeasible());
	}
	
	@Test
	public void impossiblePathAStar() throws IOException {
		initAStarTemps(polynesie, 0, 24);
		assertFalse(solutionAStarTemps.isFeasible());
		initAStarDistance(polynesie, 0, 24);
		assertFalse(solutionAStarDistance.isFeasible());
	}
	
	@Test
	public void nullPathDijkstra() throws IOException {
		initDijkstraTemps(carre, 0, 0);
		assertEquals(pathDijkstraTemps.size(), 0);
		initDijkstraDistance(carre, 0, 0);
		assertEquals(pathDijkstraDistance.size(), 0);
	}
	@Test
	public void nullPathAStar() throws IOException {
		initAStarTemps(carre, 0, 0);
		assertEquals(pathAStarTemps.size(), 0);
		initAStarDistance(carre, 0, 0);
		assertEquals(pathAStarDistance.size(), 0);
	}
}
