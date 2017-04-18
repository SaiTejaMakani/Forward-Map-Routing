package com.daa.dij;

import java.util.ArrayList;

public class Graph {
	
	private Node[] nodes;
	private int noOfNodes;
	private Edge[] edges;
	private int noOfEdges;
	private int start;//added
	private int destination;//added
	
	public Graph(Edge[] edges){
		this.edges=edges;
		this.noOfNodes=calculateNoOfNodes(edges);
		this.nodes= new Node[(int) this.noOfNodes];
		
		for(int n=0; n<this.noOfNodes; n++){
			this.nodes[n]=new Node();
		}
		this.noOfEdges=edges.length;
		for(int edgeToAdd=0; edgeToAdd<this.noOfEdges; edgeToAdd++){
			this.nodes[(int) edges[edgeToAdd].getFromNodeIndex()].getEdges().add(edges[edgeToAdd]);
			this.nodes[(int) edges[edgeToAdd].getToNodeIndex()].getEdges().add(edges[edgeToAdd]);
		}
		
	}
	public void Count_Nodes_Edges(){
		System.out.println("Number Of Nodes:" + (this.noOfNodes-1));
	    System.out.println("Number Of Edges:" + this.noOfEdges + "\n");}
	private int calculateNoOfNodes(Edge[] edges) {
		
		int noOfNodes=0;
		
		for(Edge e: edges){
			if(e.getToNodeIndex()>noOfNodes)
				noOfNodes=e.getToNodeIndex();
			if(e.getFromNodeIndex()>noOfNodes)
				noOfNodes=e.getFromNodeIndex();
		}
		noOfNodes++;
		return noOfNodes;
	}

	public void calculateShortestDistance(int start, int destination){
	 this.nodes[start].setDistanceFromSource(0);//added to point the starting node
	 //this.nodes[0].setDistanceFromSource(0);
	 this.start = start;//added
	 this.destination = destination;//added
	 int nextNode=0;
	 String path ="";
	 
	 for(int i=0;i<this.nodes.length;i++){
		 ArrayList<Edge> currentNodeEdges = this.nodes[nextNode].getEdges();
		 path=this.nodes[nextNode].getPath();//imp
		 
		 for(int joinedEdge=0 ; joinedEdge < currentNodeEdges.size() ; joinedEdge++){
			 int neighbourIndex = currentNodeEdges.get(joinedEdge).getNeighbourIndex(nextNode);
			 
			 if(!this.nodes[neighbourIndex].isVisited()){
				
				 float tentative= this.nodes[nextNode].getDistanceFromSource()+currentNodeEdges.get(joinedEdge).getLength();
				 if(tentative< nodes[neighbourIndex].getDistanceFromSource()){
					 this.nodes[neighbourIndex].setPath(this.nodes[neighbourIndex].getPath() + "->" + nextNode);//imp
					 this.nodes[neighbourIndex].setPath(path + "->" + nextNode);//imp
					 nodes[neighbourIndex].setDistanceFromSource(tentative);	
				 }
			 } 
			 this.nodes[nextNode].setPath(path + "->" + nextNode);
		 }
		 	
		  	nodes[(int) nextNode].setVisited(true);
			
//		if(nextNode==destination)
//		{  
//			
//		}
	
		  	nextNode=getNodeShortestDistanced();		  	
	 }
	 
 }
 
	private int getNodeShortestDistanced(){
	 int storedNodeIndex = 0;
	 float storedDist=Integer.MAX_VALUE;
	 
	 for(int i=0;i<this.nodes.length;i++){
		 float currentDist = this.nodes[i].getDistanceFromSource();
		 
		 if(!this.nodes[i].isVisited()&& currentDist<storedDist){
			 storedDist = currentDist;
			 storedNodeIndex=i;
			
		 }
	 }
	 return storedNodeIndex;
 }
 
 	public String[] printResult(String criteria){//added
 		
 		String	output = (criteria +"from node " + start + " to node "+ destination + " is :" +nodes[destination].getDistanceFromSource()+"\tPath:"+nodes[destination].getPath());
 		System.out.println(output);
 		String[] hops_nodes= nodes[destination].getPath().split("->");
		System.out.println("no.of hops is " +((hops_nodes.length-1)-1));//test
		
		return hops_nodes;
 	}
 
	public Node[] getNodes() {
		return nodes;
	}
	public int getNoOfNodes() {
		return noOfNodes;
	}
	public Edge[] getEdges() {
		return edges;
	}
	public int getNoOfEdges() {
		return noOfEdges;
		}
}
