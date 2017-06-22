import java.io.*;
import java.util.Scanner;

// Heap Class
class Heap
{
    // Declaring the variables
    private int[] heapArray;   // The heap array variable
    private int[] heapPos;	   // The hPos array variable
    private int[] dist;        // Distance array variable

    private int heapSize;      // The HeapSize variable
   
    // Constructor for the Heap Class
    // Takes in three parameters, the maximum Size, distance and heap position
    public Heap(int maxSize, int[] _dist, int[] _heapPos) 
    {
        // Initialize the variables
        heapSize = 0;
        heapArray = new int[maxSize + 1];
        dist = _dist;
        heapPos = _heapPos;
    }
    
    // Sift up method, which takes the heapSize as a paremeter
    public void siftUP(int element) {
			
            // Declare variable vertice and initialize is to the last element of the HeapArray
			int vertice = heapArray[element];
			
			// Dummy node at the top of the heap
			heapArray[0] = 0;
			//Distance[0] is to be the smallest value to compare
			dist[0] = Integer.MIN_VALUE;
			
            /* While loop: while the value in the distance array at position vertice (current element number we are at) is less
                than the value at distance array at element heapArr[element/2](it’s parent), keep dividing the list to insert the value at the correct place */
			while(dist[vertice] < dist[ heapArray[element / 2] ]) {
                
				 heapArray[element] = heapArray[element / 2];
				 heapPos[ heapArray[element] ] = element;
				 element = element / 2;
			}
        
            // When the while loop is over, insert the vertex to the heapArr and set the hPos array
			heapArray[element] = vertice;
			heapPos[vertice] = element;
		} // end of siftUp()
    
    // Method to check if the heap is empty, is so then return heapSize (0)
    public boolean isEmpty() {	
        return heapSize == 0;
    }
	
    // toChar method
	private char toChar(int u) {
			return(char)(u + 64);
		}
		
    // The siftDown method that takes the element as a parameter
    public void siftDown(int element) {
        // Declare the variables
        int vertice, j;
        // Initialize the variables
        vertice = heapArray[element]; //Set vertex to the element 1 in heapArray
        j = 2 * element;
        
        // While j less or equal heapSize, j = 2* element at the start
        while(j <= heapSize) {
			
            if((j + 1 <= heapSize ) && dist[ heapArray[j] ] > dist[ heapArray[j + 1] ]) {
                //j++ indicates to go to the next element
                j++;
            }
         
            // Stop if the next vertex that we are sifting down is smaller than current vertex element
            if(dist[ heapArray[j] ] >= dist[vertice]) {
                break;
            }
            //else continue on
            heapArray[element] = heapArray[j];
            element = j;
            j = element * 2;
        }
        heapArray[element] = vertice;
        heapPos[vertice] = element;
    }
    
    public int remove() 
    {   
        // Remove the element by setting it to 0 and sifting down
        int vertice = heapArray[1];
        heapPos[vertice] = 0;
        heapArray[heapSize + 1 ] = 0;  // null node goes into empty slot
        
        heapArray[1] = heapArray[heapSize--];
        siftDown(1);
        
        return vertice;
    }
    
    // Insert method
    public void insert(int j) 
    {
		//adding new vertex to the heap array to the end of the array
		//then sift up the vertex to correct place
        heapArray[++heapSize ] = j;
        siftUP(heapSize); 
    }

}

// The graph class
class Graph {
	
    // Node class
	public class Node {
		public Node next; // When called, will call the next node
		public int vert;
		public int wgt;
	}
	
	
	// Declare variables for Graph class
	private int Vertice, Edge;
	private Node[] adj;
	private Node z;
	private int[] mst;
		
		
	// Graph Constructor, takes a String as a parameter throws an input output exception if the file is not found
    public Graph(String graphFile)  throws IOException {
    	
    	// Declare variables
        int u, v;
        int e, wgt;
        Node t; // Variable of type Node (Instance of a class)
        
        // Create file reader and buffered reader
        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	    
        String splits = " +";
		String line = reader.readLine();        
        String[] parts = line.split(splits);
        System.out.println("Vertices: " + parts[0] + " Edges: " + parts[1]);
        
        Vertice = Integer.parseInt(parts[0]);
        Edge = Integer.parseInt(parts[1]);
        
        z = new Node(); 
        z.next = z;

        adj = new Node[Vertice + 1];
        
        // For loop to create nodes
        for(v = 1; v <= Vertice; ++v) 
		{
           adj[v] = new Node();               
        }

        System.out.println("\nThe edges are: ");
        
        for(e = 1; e <= Edge; ++e) {

            line = reader.readLine();
            parts = line.split(splits);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
            
            System.out.println(toChar(u) + "--(" + wgt + ")--" + toChar(v));    
            
            t = new Node();
            t.wgt = wgt;
            t.vert = v;
            t.next = adj[u].next;
            adj[u].next = t;

            t = new Node();
            t.wgt = wgt;
            t.vert = u;
            t.next = adj[v].next;
            adj[v].next = t;
        }

     }
	
    //Display method to display the represenation of the graph
    public void display() {
        // Declare variables
        int v;
        Node n;
        System.out.println("");
        //For loop to display adj vertices
        for(v = 1; v <= Vertice; ++v) {

            System.out.print("adj[" + toChar(v) + "]" );
            
            for(n = adj[v].next; n != null; n = n.next) {

                System.out.print(" -> | " + toChar(n.vert) + " | " + n.wgt + " |");    
            }
            
            System.out.println("");
        }
    }
	
	// Minimum Spanning Tree method
	public int[] MST_Prim(int s) {
		// Declare variables
		int v, u;
		int wgt, wgtSum = 0;
		int[] dist, parent, hPos;
		Node t;
		
        // Current heap position
		hPos = new int[Vertice + 1];
        // The parent node
		parent = new int[Vertice + 1];
		// The distance from node to node
		dist = new int[Vertice + 1];

		for(v = 1; v<= Vertice; v++) {
			
			//Set dist[i] to maximum int
			dist[v] = Integer.MAX_VALUE;
			// Initialize the parent and hPos elements to 0
			parent[v] = 0;
			hPos[v] = 0;
		}
		
		//Create a new heap with size of Vertice + 1
		Heap heap = new Heap(Vertice + 1, dist, hPos);
		//then insert the first element into the heap
		heap.insert(s);
		// Set distance to 0
		dist[s] = 0;

		while(!heap.isEmpty()) {
			v = heap.remove(); // while the heap is not empty remove from the heap
			if (parent[v] != 0 ) {
				
				System.out.println("Adding edge " + toChar(parent[v]) + " - " + dist[v] + " - "+ toChar(v));
			}

			// Calculates the sum of the Weights
			wgtSum += dist[v];
			// Prevent duplicates
			dist[v] = -dist[v];

            // While parent node has childer
			for(t = adj[v].next; t != null; t = t.next) {
	
				//if the weight of the vertice we are on is less
				// than the value in the distance array at the elemnent of the vertice we are on
				if( t.wgt < dist[t.vert] ) {
					
					//then we add the weight that is smaller than the value in the distance
					//and add the connect node to the parent array
					dist[t.vert] = t.wgt;
					parent[t.vert] = v;
					
					// If the vertex is empty, insert the next vertex
					if(hPos[t.vert] == 0) {
						heap.insert(t.vert);
						
					} else {
						heap.siftUP(hPos[t.vert]);
					}
				}
			}// end of for loop
			// Print out the weight
			System.out.println("\nWeight " + wgtSum);
			
			
		}
		//return the array parent
		return parent;
	}
	
	private char toChar(int u) {
		return(char)(u + 64);
	}
    // Show method
	public void showMST(int[] mst) {
		
		System.out.println("Minimum spanning tree parent array is: ");
		for(int v = 1; v <= Vertice; ++v) {
			
			if (mst[v] != 0) {
				
				System.out.println(toChar(v) + "" + toChar(mst[v]));
			}
		}
	}
}

// The main class
public class PrimLists {
	
    public static void main(String args[]) throws IOException {
		
		int[] mst;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Enter in Text File");
		String fname = in.nextLine();
		
		System.out.println("Enter in Starting Vertex");
		int s = in.nextInt();
		
		Graph graph = new Graph(fname);
		
		graph.display();
		
		mst = graph.MST_Prim(s);
		
		graph.showMST(mst);
		
		System.out.println("Finished");
		
	}
    
    
}
