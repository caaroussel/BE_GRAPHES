package org.insa.graphs.algorithm.shortestpath;


import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label implements Comparable<Label> {

	private double estim;
	
	public LabelStar(Node noeud, ShortestPathData data) {
		super(noeud);
		if (data.getMode()==AbstractInputData.Mode.LENGTH) {
			this.estim=Point.distance(noeud.getPoint(),data.getDestination().getPoint());
		}
		else {
			if (data.getGraph().getGraphInformation().hasMaximumSpeed())
			{
				int vitesse=data.getGraph().getGraphInformation().getMaximumSpeed(); 
				this.estim=Point.distance(noeud.getPoint(), data.getDestination().getPoint())/(vitesse*1000/3600);
			}
			else 
			{
				int vitesse=150; //On choisit 150 pour avoir une vitesse assez grande pour que l'estimation soit inférieur au veritable chemin
				this.estim=Point.distance(noeud.getPoint(), data.getDestination().getPoint())/(vitesse*1000/3600);
			}
		}
		
		
	}
	
	public double getTotalCost() {
		return this.estim+this.Cost;
	}
}
