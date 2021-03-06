package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;


import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.BinaryGraphReader;

import org.junit.Test;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Graph;

import java.util.Random;

public class DijkstraTest {
	
	@Test
	public void testDijkstra() throws Exception {
		
		GraphReader Reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/france.mapgr))));
		
		Graph graph = Reader.read();
		int origine;
		int destination;
		ArcInspector arcInspectorDijkstra = null;
		
		System.out.println("Test chemin le plus court en temps");
		
		arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
		
		for (int i=0;i<500;i++) 
		{
			Random random = new Random();
			origine = 0 + random.nextInt(graph.size()-0);
			destination = 0 + random.nextInt(graph.size()-0);
			while (origine == destination)
			{
				destination =0 + random.nextInt(graph.size()-0);
			}
			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);
			
			BellmanFordAlgorithm Bell = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm Dijk = new DijkstraAlgorithm(data);
			
			ShortestPathSolution expected = Bell.run();
			ShortestPathSolution solution = Dijk.run();
			if (solution.getPath()== null) 
			{
				assertEquals(expected.getPath(), solution.getPath());
				System.out.println("Pas de Solution");
			}
			else 
			{
				double coutSolution=0;
				double coutExpected=0;
				coutSolution = solution.getPath().getMinimumTravelTime();
				coutExpected = expected.getPath().getMinimumTravelTime();
				assertEquals(coutExpected, coutSolution,0.001);
				System.out.println("le co??t de la solution est " + coutSolution);
			}
		}
		//_______________________________________________________________________________________________________________________________
		
		System.out.println("Test chemin le plus court en Distance");
		
		arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
		
		for (int i=0;i<500;i++) 
		{
			Random random = new Random();
			origine = 0 + random.nextInt(graph.size()-0);
			destination = 0 + random.nextInt(graph.size()-0);
			while (origine == destination)
			{
				destination =0 + random.nextInt(graph.size()-0);
			}
			ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);
			
			BellmanFordAlgorithm Bell = new BellmanFordAlgorithm(data);
			DijkstraAlgorithm Dijk = new DijkstraAlgorithm(data);
			
			ShortestPathSolution expected = Bell.run();
			ShortestPathSolution solution = Dijk.run();
			if (solution.getPath()== null) 
			{
				assertEquals(expected.getPath(), solution.getPath());
				System.out.println("Pas de Solution");
			}
			else 
			{
				double coutSolution=0;
				double coutExpected=0;
				coutSolution = solution.getPath().getMinimumTravelTime();
				coutExpected = expected.getPath().getMinimumTravelTime();
				assertEquals(coutExpected, coutSolution,0.001);
				System.out.println("le co??t de la solution est " + coutSolution);
			}
		}
	}
}

