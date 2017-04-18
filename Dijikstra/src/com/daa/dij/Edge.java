package com.daa.dij;

public class Edge {
	private int fromNodeIndex;
	private int toNodeIndex;
	private float length;
	
	public Edge(int input_intermediate,int input_intermediate2, float f){
		this.fromNodeIndex=input_intermediate;
		this.toNodeIndex=input_intermediate2;
		this.length=f;
	}
	
	public int getFromNodeIndex(){
		return fromNodeIndex;
	}
	
	public int getToNodeIndex(){
		return toNodeIndex;
	}
	
	public float getLength(){
		return length;
	}
	
	public int getNeighbourIndex(float nextNode){
		if(this.fromNodeIndex==nextNode){
			return this.toNodeIndex;
		}else{
			return this.fromNodeIndex;
		}
	}
	
	public void print_edge(){ // added for testing
		System.out.print(fromNodeIndex + "," + toNodeIndex + "," + length + "\n");
	}

}
