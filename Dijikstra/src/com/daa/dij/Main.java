package com.daa.dij;

public class Main {
	
	public static float[] intermediate_inputs = new float[3];//intermediate buffers
	public static void main(String[] args){
		long startTime = System.currentTimeMillis();//keeping tracking of time
		int start = 1;
		int destination =12;
		//mycode
		 float[][] input_edges={
				    {1,2,1,(float) 24.000000,(float) 22.000000},
	 				{1,3,4,(float) 0.100000,(float) 13.000000},
	 				{2,4,1,(float) 25.100000,(float) 13.000000},
	 				{2,5,1,(float) 30.000000,(float) 13.000000},
	 				{3,4,4,(float) 0.1111100,(float) 11.000000},
	 				{3,5,2,(float) 16.000000,(float) 28.000000},
	 				{4,6,4,(float) 0.100000,(float) 15.000000},
	 				{4,7,1,(float) 25.000000,(float) 15.000000},
	 				{5,6,2,(float) 19.000000,(float) 37.000000},
	 				{5,7,3,(float) 0.0476190,(float) 9.000000},
	 				{6,8,3,(float) 22.000000,(float) 1.000000},
	 				{6,9,5,(float) 23.000000,(float) 1.000000},
	 				{7,8,3,(float) 0.045455,(float) 5.000000},
	 				{7,9,5,(float) 22.00000,(float) 1.000000},
	 				{8,10,2,(float) 12.000000,(float) 28.000000},
	 				{8,11,5,(float) 28.000000,(float) 1.000000},
	 				{9,10,1,(float) 22.000000,(float) 1.000000},
	 				{9,11,1,(float) 30.000000,(float) 1.000000},
	 				{10,12,1,(float) 23.000000,(float) 1.000000},
	 				{11,12,5,(float) 28.000000,(float) 1.000000}
		 		};
		 
		float[][] input_intermediate= new float[input_edges.length][5];//from to u variance and c_square
		 
		 for(int i=0; i<input_edges.length;i++){
			 if(input_edges[i][2] == 1){
				 Determenstic_input(input_edges[i][3], input_edges[i][4] );
				 input_intermediate[i][0] = input_edges[i][0];
				 input_intermediate[i][1] = input_edges[i][1];
				 input_intermediate[i][2] = intermediate_inputs[0];
				 input_intermediate[i][3] = intermediate_inputs[1];
				 input_intermediate[i][4] = intermediate_inputs[2];
			 }
			 else if(input_edges[i][2] == 2){
				 uniform_input(input_edges[i][3], input_edges[i][4] );
				 input_intermediate[i][0] = input_edges[i][0];
				 input_intermediate[i][1] = input_edges[i][1];
				 input_intermediate[i][2] = intermediate_inputs[0];
				 input_intermediate[i][3] = intermediate_inputs[1];
				 input_intermediate[i][4] = intermediate_inputs[2];
			 }
			 else if(input_edges[i][2] == 3){
				 exponential_input(input_edges[i][3], input_edges[i][4] );
				 input_intermediate[i][0] = input_edges[i][0];
				 input_intermediate[i][1] = input_edges[i][1];
				 input_intermediate[i][2] = intermediate_inputs[0];
				 input_intermediate[i][3] = intermediate_inputs[1];
				 input_intermediate[i][4] = intermediate_inputs[2];
			 }
			 else if(input_edges[i][2] == 4){
				 shifted_Exponential_input(input_edges[i][3], input_edges[i][4] );
				 input_intermediate[i][0] = input_edges[i][0];
				 input_intermediate[i][1] = input_edges[i][1];
				 input_intermediate[i][2] = intermediate_inputs[0];
				 input_intermediate[i][3] = intermediate_inputs[1];
				 input_intermediate[i][4] = intermediate_inputs[2];
			 }
			 else if(input_edges[i][2] == 5){
				 normal_input(input_edges[i][3], input_edges[i][4] );
				 input_intermediate[i][0] = input_edges[i][0];
				 input_intermediate[i][1] = input_edges[i][1];
				 input_intermediate[i][2] = intermediate_inputs[0];
				 input_intermediate[i][3] = intermediate_inputs[1];
				 input_intermediate[i][4] = intermediate_inputs[2];
			 }
			 else if(input_edges[i][2] == 6){
				 general_input(input_edges[i][3], input_edges[i][4] );
				 input_intermediate[i][0] = input_edges[i][0];
				 input_intermediate[i][1] = input_edges[i][1];
				 input_intermediate[i][2] = intermediate_inputs[0];
				 input_intermediate[i][3] = intermediate_inputs[1];
				 input_intermediate[i][4] = intermediate_inputs[2];
			 }
			 else{
				 System.out.println("wrong input format. check your input format");
			 }
			
		 }
		 
		                      
		//mycode
		 
		// till now creating u , sigma and c_square was done. now send all intermediate results to each criteria and create the final inputs for dijiksha algorithm
		//mean value criteria
		 Edge meanvalue_edges[] = new Edge [input_intermediate.length];//input edges array for mean value criteria
		 for(int i=0; i < input_intermediate.length ; i++){
			 meanvalue_edges[i] = new Edge((int)input_intermediate[i][0], (int)input_intermediate[i][1],meanvalue_weight(input_intermediate[i][2]) );
			// meanvalue_edges[i].print_edge();//testing 
		 }
		 Graph mean_g = new Graph(meanvalue_edges);
		 mean_g.Count_Nodes_Edges();//one time call to print number of edges and nodes
		 mean_g.calculateShortestDistance(start, destination);
		 String[] path_nodes=mean_g.printResult("mean_value criteria(Expected Value): ");
		 String node_1;
		 String node_2;
		 System.out.print("participating Edges:");
		 String Inter_string_mean ="";
		 
		 
		 float mean_data = 0;
		 float optimist_data=0;
		 float pessimist_data=0;
		 float double_pessimist_data=0;
		 float stable_data=0;
		 float own_data=0;
		 for(int i=1; i< path_nodes.length-1 ; i++){
			 node_1 =path_nodes[i];
			 node_2 =path_nodes[i+1];
			 System.out.print("("+path_nodes[i]+ "," + path_nodes[i+1] +")");
			 for(int j=0; j < input_intermediate.length  ; j++){
				 if(((int)input_intermediate[j][0]==Integer.parseInt(node_1))&& ((int)input_intermediate[j][1] == Integer.parseInt(node_2))){
				 mean_data += input_intermediate[j][2] - input_intermediate[j][3];
				 optimist_data += input_intermediate[j][2] ;
				 pessimist_data += input_intermediate[j][2] + input_intermediate[j][3];;
				 double_pessimist_data += input_intermediate[j][2] + (2*input_intermediate[j][3]);
				 stable_data += input_intermediate[j][4];
				 own_data += input_intermediate[j][2] * input_intermediate[j][4];;
				 }
			 }
		 }
		 
		 Inter_string_mean = "\n"+mean_data+"    |    " +optimist_data+"    |    "+pessimist_data+"    |    "+ double_pessimist_data +"    |    "+stable_data+"    |    "+own_data+"\n";
		 
		 

		 
		 //mean value criteria 
		 
		//optimist value criteria
		 Edge optimistic_value_edges[] = new Edge [input_intermediate.length];//input edges array for mean value criteria
		 for(int i=0; i < input_intermediate.length ; i++){
			 optimistic_value_edges[i] = new Edge((int)input_intermediate[i][0], (int)input_intermediate[i][1],optimist_weight(input_intermediate[i][2], input_intermediate[i][3]) );
			 //optimistic_value_edges[i].print_edge();//testing 
		 }
		 Graph optimistic_g = new Graph(optimistic_value_edges);
		 optimistic_g.calculateShortestDistance(start, destination);
		 path_nodes=null;
		 path_nodes=optimistic_g.printResult("\n\n"+"Optimist_value criteria: ");
		//String[] path_nodes=mean_g.printResult("mean_value criteria(Expected Value): ");
		 node_1 = null;
		 node_2 = null;
		 System.out.print("participating Edges:");
		 
		 String Inter_string_opt =null;
		 mean_data = 0;
		 optimist_data=0;
		 pessimist_data=0;
		 double_pessimist_data=0;
		 stable_data=0;
		 own_data=0;
		 for(int i=1; i< path_nodes.length-1 ; i++){
			 node_1 =path_nodes[i];
			 node_2 =path_nodes[i+1];
			 System.out.print("("+path_nodes[i]+ "," + path_nodes[i+1] +")");
			 for(int j=0; j < input_intermediate.length  ; j++){
				 if(((int)input_intermediate[j][0]==Integer.parseInt(node_1))&& ((int)input_intermediate[j][1] == Integer.parseInt(node_2))){
				 mean_data += input_intermediate[j][2] - input_intermediate[j][3];
				 optimist_data += input_intermediate[j][2] ;
				 pessimist_data += input_intermediate[j][2] + input_intermediate[j][3];;
				 double_pessimist_data += input_intermediate[j][2] + (2*input_intermediate[j][3]);
				 stable_data += input_intermediate[j][4];
				 own_data += input_intermediate[j][2] * input_intermediate[j][4];;
				 }
			 }
		 }
		 
		 Inter_string_opt = "\n"+mean_data+"    |    " +optimist_data+"    |    "+pessimist_data+"    |    "+ double_pessimist_data +"    |    "+stable_data+"    |    "+own_data+"\n";
		//System.out.println(Inter_string_opt);
		//optimist value criteria 
		 
		//pessimist value criteria
		 Edge pessimist_value_edges[] = new Edge [input_intermediate.length];//input edges array for mean value criteria
		 for(int i=0; i < input_intermediate.length ; i++){
			 pessimist_value_edges[i] = new Edge((int)input_intermediate[i][0], (int)input_intermediate[i][1],pessimist_weight(input_intermediate[i][2], input_intermediate[i][3]) );
			// pessimist_value_edges[i].print_edge();//testing 
		 }
		 Graph pessimist_g = new Graph(pessimist_value_edges);
		 pessimist_g.calculateShortestDistance(start, destination);
		 path_nodes=null;
		 path_nodes=pessimist_g.printResult("\n\n"+"Pessimistic_value criteria: ");
		//String[] path_nodes=mean_g.printResult("mean_value criteria(Expected Value): ");
		 node_1 = null;
		 node_2 = null;
		 System.out.print("participating Edges:");
		 
		 String Inter_string_pess =null;
		 mean_data = 0;
		 optimist_data=0;
		 pessimist_data=0;
		 double_pessimist_data=0;
		 stable_data=0;
		 own_data=0;
		 for(int i=1; i< path_nodes.length-1 ; i++){
			 node_1 =path_nodes[i];
			 node_2 =path_nodes[i+1];
			 System.out.print("("+path_nodes[i]+ "," + path_nodes[i+1] +")");
			 for(int j=0; j < input_intermediate.length  ; j++){
				 if(((int)input_intermediate[j][0]==Integer.parseInt(node_1))&& ((int)input_intermediate[j][1] == Integer.parseInt(node_2))){
				 mean_data += input_intermediate[j][2] - input_intermediate[j][3];
				 optimist_data += input_intermediate[j][2] ;
				 pessimist_data += input_intermediate[j][2] + input_intermediate[j][3];;
				 double_pessimist_data += input_intermediate[j][2] + (2*input_intermediate[j][3]);
				 stable_data += input_intermediate[j][4];
				 own_data += input_intermediate[j][2] * input_intermediate[j][4];;
				 }
			 }
		 }
		 
		 Inter_string_pess = "\n"+mean_data+"    |    " +optimist_data+"    |    "+pessimist_data+"    |    "+ double_pessimist_data +"    |    "+stable_data+"    |    "+own_data+"\n";
		
		//pessimist value criteria 
		 
		//double pessimist value criteria
		 Edge double_pessimist_value_edges[] = new Edge [input_intermediate.length];//input edges array for mean value criteria
		 for(int i=0; i < input_intermediate.length ; i++){
			 double_pessimist_value_edges[i] = new Edge((int)input_intermediate[i][0], (int)input_intermediate[i][1],double_pessimist_weight(input_intermediate[i][2], input_intermediate[i][3]) );
	         //double_pessimist_value_edges[i].print_edge();//testing 
		 }
		 Graph double_pessimist_g = new Graph(double_pessimist_value_edges);
		     double_pessimist_g.calculateShortestDistance(start, destination);
		     path_nodes=null;
		     path_nodes=double_pessimist_g.printResult("\n\n"+"Double Pessimist_value criteria: ");
		   //String[] path_nodes=mean_g.printResult("mean_value criteria(Expected Value): ");
			 node_1 = null;
			 node_2 = null;
			 System.out.print("participating Edges:");
			 
			 String Inter_string_dob_pess =null;
			 mean_data = 0;
			 optimist_data=0;
			 pessimist_data=0;
			 double_pessimist_data=0;
			 stable_data=0;
			 own_data=0;
			 for(int i=1; i< path_nodes.length-1 ; i++){
				 node_1 =path_nodes[i];
				 node_2 =path_nodes[i+1];
				 System.out.print("("+path_nodes[i]+ "," + path_nodes[i+1] +")");
				 for(int j=0; j < input_intermediate.length  ; j++){
					 if(((int)input_intermediate[j][0]==Integer.parseInt(node_1))&& ((int)input_intermediate[j][1] == Integer.parseInt(node_2))){
					 mean_data += input_intermediate[j][2] - input_intermediate[j][3];
					 optimist_data += input_intermediate[j][2] ;
					 pessimist_data += input_intermediate[j][2] + input_intermediate[j][3];;
					 double_pessimist_data += input_intermediate[j][2] + (2*input_intermediate[j][3]);
					 stable_data += input_intermediate[j][4];
					 own_data += input_intermediate[j][2] * input_intermediate[j][4];;
					 }
				 }
			 }
			 
			 Inter_string_dob_pess = "\n"+mean_data+"    |    " +optimist_data+"    |    "+pessimist_data+"    |    "+ double_pessimist_data +"    |    "+stable_data+"    |    "+own_data+"\n";
			
		//double pessimist value criteria 
		 
		//stable value criteria
		 Edge stable_value_edges[] = new Edge [input_intermediate.length];//input edges array for mean value criteria
		 for(int i=0; i < input_intermediate.length ; i++){
			 stable_value_edges[i] = new Edge((int)input_intermediate[i][0], (int)input_intermediate[i][1],stable_weight(input_intermediate[i][4]) );
			// stable_value_edges[i].print_edge();//testing 
		 }
		 Graph stable_value_g = new Graph(stable_value_edges);
		 	stable_value_g.calculateShortestDistance(start, destination);
		 	path_nodes=null;
		 	path_nodes=stable_value_g.printResult("\n\n"+"stable_value criteria: ");
		 	node_1 = null;
			 node_2 = null;
			 System.out.print("participating Edges:");
			 
			 String Inter_string_sta =null;
			 mean_data = 0;
			 optimist_data=0;
			 pessimist_data=0;
			 double_pessimist_data=0;
			 stable_data=0;
			 own_data=0;
			 for(int i=1; i< path_nodes.length-1 ; i++){
				 node_1 =path_nodes[i];
				 node_2 =path_nodes[i+1];
				 System.out.print("("+path_nodes[i]+ "," + path_nodes[i+1] +")");
				 for(int j=0; j < input_intermediate.length  ; j++){
					 if(((int)input_intermediate[j][0]==Integer.parseInt(node_1))&& ((int)input_intermediate[j][1] == Integer.parseInt(node_2))){
					 mean_data += input_intermediate[j][2] - input_intermediate[j][3];
					 optimist_data += input_intermediate[j][2] ;
					 pessimist_data += input_intermediate[j][2] + input_intermediate[j][3];;
					 double_pessimist_data += input_intermediate[j][2] + (2*input_intermediate[j][3]);
					 stable_data += input_intermediate[j][4];
					 own_data += input_intermediate[j][2] * input_intermediate[j][4];;
					 }
				 }
			 }
			 
			 Inter_string_sta = "\n"+mean_data+"    |    " +optimist_data+"    |    "+pessimist_data+"    |    "+ double_pessimist_data +"    |    "+stable_data+"    |    "+own_data+"\n";
			
		//stable value criteria 
		 
		 
		//OWN VALUE CRITERIA
		 Edge own_value_edges[] = new Edge [input_intermediate.length];//input edges array for mean value criteria
		 for(int i=0; i < input_intermediate.length ; i++){
			 own_value_edges[i] = new Edge((int)input_intermediate[i][0], (int)input_intermediate[i][1],own_value(input_intermediate[i][2],input_intermediate[i][4]) );
			 //own_value_edges[i].print_edge();//testing 
		 }
		 Graph own_value_g = new Graph(own_value_edges);
		 own_value_g.calculateShortestDistance(start, destination);
		 path_nodes=null;
		 path_nodes=own_value_g.printResult("\n\n"+"own_value criteria: ");
		 node_1 = null;
		 node_2 = null;
		 System.out.print("participating Edges:");
		 
		 String Inter_string_own =null;
		 mean_data = 0;
		 optimist_data=0;
		 pessimist_data=0;
		 double_pessimist_data=0;
		 stable_data=0;
		 own_data=0;
		 for(int i=1; i< path_nodes.length-1 ; i++){
			 node_1 =path_nodes[i];
			 node_2 =path_nodes[i+1];
			 System.out.print("("+path_nodes[i]+ "," + path_nodes[i+1] +")");
			 for(int j=0; j < input_intermediate.length  ; j++){
				 if(((int)input_intermediate[j][0]==Integer.parseInt(node_1))&& ((int)input_intermediate[j][1] == Integer.parseInt(node_2))){
				 mean_data += input_intermediate[j][2] - input_intermediate[j][3];
				 optimist_data += input_intermediate[j][2] ;
				 pessimist_data += input_intermediate[j][2] + input_intermediate[j][3];;
				 double_pessimist_data += input_intermediate[j][2] + (2*input_intermediate[j][3]);
				 stable_data += input_intermediate[j][4];
				 own_data += input_intermediate[j][2] * input_intermediate[j][4];;
				 }
			 }
		 }
		 
		 Inter_string_own = "\n"+mean_data+"    |    " +optimist_data+"    |    "+pessimist_data+"    |    "+ double_pessimist_data +"    |    "+stable_data+"    |    "+own_data+"\n";
		
		//stable value criteria 


		System.out.println("\n=========================================================================================================");
		System.out.println("U - SD       |         U     |        U + SD      |      U + 2*SD     |      C^2      |  OWN U*C^2   |");
		System.out.println("===========================================================================================================");
		System.out.println(Inter_string_mean);
		System.out.println(Inter_string_opt);
		System.out.println(Inter_string_pess);
		System.out.println(Inter_string_dob_pess);
		System.out.println(Inter_string_sta);
		System.out.println(Inter_string_own);
		
		
		
		 long endTime = System.currentTimeMillis();
			System.out.println("Eexecution Time: "+(endTime - startTime) + " ms"); //execution time
			
	}
	
	public static void Determenstic_input(float alpha,float bheta){
		float mean,variance,C_square = 0;
		intermediate_inputs[0] = 0;
		intermediate_inputs[1] = 0;
		intermediate_inputs[2] = 0;
		
		mean = alpha;
		variance = 0;
		C_square = 0;
		
		intermediate_inputs[0] = mean;
		intermediate_inputs[1] = (float) Math.sqrt(variance);
		intermediate_inputs[2] = C_square;
		
		return;
		
	}
	
	public static void uniform_input(float alpha,float bheta){
		float mean,variance,C_square = 0;
		intermediate_inputs[0] = 0;
		intermediate_inputs[1] = 0;
		intermediate_inputs[2] = 0;
		
		mean = (alpha + bheta) / 2;
		variance = ((bheta-alpha)*(bheta-alpha)) / 12;
		C_square = (((bheta - alpha)/(bheta - alpha)) * ((bheta - alpha)/(bheta - alpha))) /2;
		
		intermediate_inputs[0] = mean;
		intermediate_inputs[1] = (float) Math.sqrt(variance);;
		intermediate_inputs[2] = C_square;
		
		return;
	}
	
	public static void exponential_input(float alpha,float bheta){
		float mean,variance,C_square = 0;
		intermediate_inputs[0] = 0;
		intermediate_inputs[1] = 0;
		intermediate_inputs[2] = 0;
		
		mean = 1 /alpha;
		variance = 1 / (alpha * alpha);
		C_square = 1;
		
		intermediate_inputs[0] = mean;
		intermediate_inputs[1] = (float) Math.sqrt(variance);;
		intermediate_inputs[2] = C_square;
		
		
		return;
	}
	
	public static void shifted_Exponential_input(float alpha,float bheta){
		float mean,variance,C_square = 0;
		intermediate_inputs[0] = 0;
		intermediate_inputs[1] = 0;
		intermediate_inputs[2] = 0;
		
		mean = bheta + (1 / alpha);
		variance = 1 / (alpha * alpha);
		C_square = 1/ ((1 + bheta * alpha) * (1 + bheta * alpha));
		
		intermediate_inputs[0] = mean;
		intermediate_inputs[1] = (float) Math.sqrt(variance);;
		intermediate_inputs[2] = C_square;
		
		return;
	}
	
	public static void normal_input(float alpha,float bheta){
		float mean,variance,C_square = 0;
		intermediate_inputs[0] = 0;
		intermediate_inputs[1] = 0;
		intermediate_inputs[2] = 0;
		
		mean = alpha;
		variance = bheta;
		C_square = bheta / (alpha * alpha);
		
		intermediate_inputs[0] = mean;
		intermediate_inputs[1] = (float) Math.sqrt(variance);;
		intermediate_inputs[2] = C_square;
		
		return;
	}
	
	public static void general_input(float alpha,float bheta){
		float mean,variance,C_square = 0;
		intermediate_inputs[0] = 0;
		intermediate_inputs[1] = 0;
		intermediate_inputs[2] = 0;
		
		mean = alpha;
		variance = alpha * alpha * bheta;
		C_square = bheta ;
		
		intermediate_inputs[0] = mean;
		intermediate_inputs[1] = (float) Math.sqrt(variance);;
		intermediate_inputs[2] = C_square;
		
		return;
	}
	
	public static float meanvalue_weight(float mean){
		return mean;
	}
	
	public static float optimist_weight(float mean, float standard_deviation){
		float edge_weight = mean - standard_deviation;
		if (edge_weight > 0)
			return edge_weight;
		else 
			return Integer.MAX_VALUE;
		
	}
	
	public static float pessimist_weight(float mean, float standard_deviation){
		
		return mean + standard_deviation;
		
	}
	
	public static float double_pessimist_weight(float mean, float standard_deviation){
		
		return (mean + (2 * standard_deviation));
		
	}
	
	public static float stable_weight(float C_square){
		
		return C_square;
		
	}
	
    public static float own_value(float mean,float C_square){
		
		return (mean * C_square);
		
	}
}
