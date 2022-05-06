package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label implements Comparable<Label> {

	private double estim;
	
	public LabelStar(Node noeud, ShortestPathData data) {
	
		if (data.getMode()==AbstractInputData.Mode.LENGTH) {
			this.estim=Point.distance(noeud.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse=Math.max( //voir comment faire quand on est en temps vu qu'on ne peut utiliser getmaximumspeed
		}
		
		
	}
	
}
