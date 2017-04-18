package com.daa.dij;

import java.util.ArrayList;

public class Node {
	private float distanceFromSource=Integer.MAX_VALUE;
	
	private boolean visited;
	private String path= " ";
	
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	
	public float getDistanceFromSource(){
	
		return distanceFromSource;
	}
	
	public void setDistanceFromSource(float distanceFromSource){
		this.distanceFromSource= distanceFromSource;
	}
	
	public boolean isVisited(){
		return visited;
	}
	public void setVisited(boolean visited){
		this.visited=visited;
	}
	
	

	public ArrayList<Edge> getEdges() {
		
		return edges;
	}
	
	public void setEdges(ArrayList<Edge> edges){
		this.edges=edges;
	}

	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	
}
