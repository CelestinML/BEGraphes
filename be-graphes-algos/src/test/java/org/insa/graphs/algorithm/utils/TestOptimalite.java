package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestOptimalite {
	
    static Graph graph;
    static ArcInspector distance, temps;
    static ShortestPathData dataDistance, dataTemps;
    static double distanceMin, tempsMin, distanceMax, tempsMax;
    static DijkstraAlgorithm dijkstraDistance, dijkstraTemps;
    static BellmanFordAlgorithm bellmanDistance, bellmanTemps;
    static AStarAlgorithm aStarDistance, aStarTemps;
    static Path pathDijkstraDistance, pathDijkstraTemps, pathBellmanDistance, 
    		    pathBellmanTemps, pathAStarDistance, pathAStarTemps;
	
    static String insa = "C:\\Users\\celes\\Desktop\\BEGraphes\\BEGraphes\\Carte\\insa.mapgr";
    static String carre = "C:\\Users\\celes\\Desktop\\BEGraphes\\BEGraphes\\Carte\\carre.mapgr";
    
    public static void initDijkstraDistance(String mapName) throws IOException {
	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    distance = ArcInspectorFactory.getAllFilters().get(0);
	    //Instanciation des données
	    dataDistance = new ShortestPathData(graph, graph.get(0), graph.get(graph.size()-1), distance);
	    distanceMin = graph.get(0).getPoint().distanceTo(graph.get(graph.size()-1).getPoint());
	    
	    
	    //Instanciation des algos
	    dijkstraDistance = new DijkstraAlgorithm(dataDistance);
	    bellmanDistance = new BellmanFordAlgorithm(dataDistance);
	
	    //Création des paths
	    pathDijkstraDistance = dijkstraDistance.run().getPath();
	    pathBellmanDistance = bellmanDistance.run().getPath();
	}
	
    public static void initDijkstraTemps(String mapName) throws IOException {
	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    temps = ArcInspectorFactory.getAllFilters().get(2);
	    //Instanciation des données
	    dataTemps = new ShortestPathData(graph, graph.get(0), graph.get(graph.size()-1), temps);
	    tempsMin = graph.get(0).getPoint().distanceTo(graph.get(graph.size()-1).getPoint())/(dataTemps.getGraph().getGraphInformation().getMaximumSpeed()/3.6);
	    
	    //Instanciation des algos
	    dijkstraTemps = new DijkstraAlgorithm(dataTemps);
	    bellmanTemps = new BellmanFordAlgorithm(dataTemps);
	
	    //Création des paths
	    pathDijkstraTemps = dijkstraTemps.run().getPath();
	    pathBellmanTemps = bellmanTemps.run().getPath();
	}
	
    public static void initAStarDistance(String mapName) throws IOException {
	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    distance = ArcInspectorFactory.getAllFilters().get(0);
	    //Instanciation des données
	    dataDistance = new ShortestPathData(graph, graph.get(0), graph.get(graph.size()-1), distance);
	    distanceMin = graph.get(0).getPoint().distanceTo(graph.get(graph.size()-1).getPoint());
	    
	    
	    //Instanciation des algos
	    bellmanDistance = new BellmanFordAlgorithm(dataDistance);
	    aStarDistance = new AStarAlgorithm(dataDistance);
	
	    //Création des paths
	    pathBellmanDistance = bellmanDistance.run().getPath();
	    pathAStarDistance = aStarDistance.run().getPath();
	}
    
    public static void initAStarTemps(String mapName) throws IOException {
    	
	    //Création du lecteur de graph
	    final GraphReader reader = new BinaryGraphReader(
	            new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
	
	    //Lecture du graph
	    graph = reader.read();
	    reader.close();
	    
	    //Création des filtres
	    temps = ArcInspectorFactory.getAllFilters().get(2);
	    //Instanciation des données
	    dataTemps = new ShortestPathData(graph, graph.get(0), graph.get(graph.size()-1), temps);
	    tempsMin = graph.get(0).getPoint().distanceTo(graph.get(graph.size()-1).getPoint())/(dataTemps.getGraph().getGraphInformation().getMaximumSpeed()/3.6);
	    
	    
	    //Instanciation des algos
	    bellmanTemps = new BellmanFordAlgorithm(dataTemps);
	    aStarTemps = new AStarAlgorithm(dataTemps);
	
	    //Création des paths
	    pathBellmanTemps = bellmanTemps.run().getPath();
	    pathAStarTemps = aStarTemps.run().getPath();
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
	public void testDijkstraDistanceInsa() throws IOException {
		initDijkstraDistance(insa);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void testDijkstraTempsInsa() throws IOException {
		initDijkstraTemps(insa);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void testAStarDistanceInsa() throws IOException {
		initAStarDistance(insa);
		assertTrue(thresholdBasedFloatsComparison(pathAStarDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void testAStarTempsInsa() throws IOException {
		initAStarTemps(insa);
		assertTrue(thresholdBasedFloatsComparison(pathAStarTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void testDijkstraDistanceCarre() throws IOException {
		initDijkstraDistance(carre);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void testDijkstraTempsCarre() throws IOException {
		initDijkstraTemps(carre);
		assertTrue(thresholdBasedFloatsComparison(pathDijkstraTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
	
	@Test
	public void testAStarDistanceCarre() throws IOException {
		initAStarDistance(carre);
		assertTrue(thresholdBasedFloatsComparison(pathAStarDistance.getLength(), pathBellmanDistance.getLength()));
	}
	@Test
	public void testAStarTempsCarre() throws IOException {
		initAStarTemps(carre);
		assertTrue(thresholdBasedFloatsComparison(pathAStarTemps.getMinimumTravelTime(), pathBellmanTemps.getMinimumTravelTime()));
	}
}
