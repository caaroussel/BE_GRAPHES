package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;

public class Label implements Comparable<Label> {

	
	// Sommet Courant
	private Node CurrentNode;
	
	//Vrai si le sommet est marqué
	private boolean Marque;
	
	//Valeur courante du plus cours chemin origine->sommet
	protected double Cost;
	
	//Sommet précédent sur le chemin le plus court
	private Arc Father;
	
	
	public Label(Node noeud) {
		this.CurrentNode=noeud;
		this.Cost=Double.POSITIVE_INFINITY;
		this.Father=null;
		this.Marque=false;
	}

	public Node getNoeud(){
		return this.CurrentNode;
	}
	
	
	public boolean getMarque(){
		return this.Marque;
	}
	
	public Arc getFather() {
		return this.Father;
	}
	
	
	//renvoie le coût de ce label
	public double getCost() {
		return this.Cost;		
	}
	
	
	
	public void setCost(double Cost) {
		this.Cost=Cost;
	}
	
	public void setFather(Arc Father) {
		this.Father=Father;
	}
	
	public void setMarque(boolean Marque) {
		this.Marque=Marque;
	}
	
	
	
	public int compareTo(Label autre) {
		int res;
		if (this.Cost>autre.Cost) {
			res=-1;
		}
		else if (this.Cost==autre.Cost) {
			res=0;
		}
		else {
			res=1;
		}
		return res;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
