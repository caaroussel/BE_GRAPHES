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

public class DijkstraTest {

	
	//faire un version length et time sans paramatre et avec les points choisis au hasard, sans prendre origine et destination identique 
	//Graph.size pour la taille du random et launch.java
	
	@Test
	public void testDijkstra(String nameMap, int Mode, int origine, int destination) throws Exception {
		
		GraphReader Reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(nameMap))));
		
		Graph graph = Reader.read();
		
		if (Mode!=0 && Mode !=1) {
			System.out.println("Erreur d'argument");
		}
		else {
			if (origine<0 || destination<0 || origine>=(graph.size()-1) || destination>=(graph.size()-1)) {
				System.out.println("Erreur destination/origine");
			}
			else {
				ArcInspector arcInspectorDijkstra = null;
				
				if (Mode == 0) { //Time
					System.out.println("Mode Temps");
					arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(2);
				}
				else if (Mode == 1) {
					System.out.println("Mode Dsitance");
					arcInspectorDijkstra = ArcInspectorFactory.getAllFilters().get(0);
				}
				
				System.out.println("L'origine est " + origine);
				System.out.println("La destination est "+ destination);
				
				if (destination==origine) {
					System.out.println("L'origine et la destination sont un seul et même point");
					System.out.println("Le coût du trajet est de 0");
				}
				else {
					ShortestPathData data = new ShortestPathData(graph, graph.get(origine),graph.get(destination), arcInspectorDijkstra);
					
					BellmanFordAlgorithm Bell = new BellmanFordAlgorithm(data);
					DijkstraAlgorithm Dijk = new DijkstraAlgorithm(data);
					
					ShortestPathSolution expected = Bell.run();
					ShortestPathSolution solution = Dijk.run();
					
					if (solution.getPath()== null) {
						assertEquals(expected.getPath(), solution.getPath());
						System.out.println("Pas de Solution");
					}
					else {
						double coutSolution=0;
						double coutExpected=0;
						if (Mode==0) {
							coutSolution = solution.getPath().getMinimumTravelTime();
							coutExpected = expected.getPath().getMinimumTravelTime();
						}
						else if (Mode ==1) {
							coutSolution = solution.getPath().getLength();
							coutExpected = expected.getPath().getLength();
						}
						assertEquals(coutExpected, coutSolution,0.001);
						System.out.println("le coût de la solution est " + coutSolution);
					}
					
					
				}
			}
		}
	}

}
