package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.model.Graph;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Arc;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	
	int nbSommetVisite;
	int nbSommet;
	boolean arrivee = false;
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        // TODO:
        // donnée du graph
        Graph graph = data.getGraph();
        nbSommet=graph.size();
        nbSommetVisite=0;
        //Labels
        Label TabLab[]= new Label[nbSommet];
        
        BinaryHeap<Label> tas= new BinaryHeap<Label>();
        
        Arc[] predecessorArcs = new Arc[nbSommet];
        
        
        Label Debut = new Label(data.getOrigin());
        Debut.setCost(0);
        tas.insert(Debut);

        
        while (!tas.isEmpty() && !arrivee)
        {
        	Label CurrentSommet=tas.deleteMin();
        	CurrentSommet.setMarque(true);
        	System.out.println("Origine :");
        	System.out.println(CurrentSommet.getCost());
        	
        	if (CurrentSommet.getNoeud()== data.getDestination())
        	{
        		arrivee = true;
        	}
        	
        	
        	for (Arc arc: CurrentSommet.getNoeud().getSuccessors())
        	{
        		Node Sucesseur = arc.getDestination();
        		Label SucesseurLabel=TabLab[Sucesseur.getId()];
        		if (SucesseurLabel==null)
        		{
        			SucesseurLabel = new Label(Sucesseur);
        		}
        		
                if (!data.isAllowed(arc)) 
                {
                    continue;
                }
                
                if (!SucesseurLabel.getMarque())
                {
                	if (SucesseurLabel.getCost()>CurrentSommet.getCost() + data.getCost(arc))
                	{
                		if (SucesseurLabel.getCost()!=Double.POSITIVE_INFINITY)
                		{
                			tas.remove(SucesseurLabel);
                		}
                		
                		SucesseurLabel.setCost(CurrentSommet.getCost() + data.getCost(arc));
                    	System.out.println("Sucesseur :");
                    	System.out.println(SucesseurLabel.getCost());
                		tas.insert(SucesseurLabel);
                		predecessorArcs[arc.getDestination().getId()] = arc;
                		SucesseurLabel.setFather(arc);
                		TabLab[Sucesseur.getId()]=SucesseurLabel;
                	}
                }
 
        	}
        }
        
        
        
        if (predecessorArcs[data.getDestination().getId()] == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = predecessorArcs[data.getDestination().getId()];
            while (arc != null) {
            	System.out.println("Arc :");
            	System.out.println(arc.getOrigin().getId());
            	System.out.println(arc.getDestination().getId());
                arcs.add(arc);
                arc = predecessorArcs[arc.getOrigin().getId()];
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }
        
        return solution;
    }

}
