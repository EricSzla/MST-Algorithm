# MST-Algorithm
Implementation of the Prim List - MST Algorithm for the graph loaded from a txt file. </br>
Prim List is an algorithm that finds a minimum spanning tree for a weighted undirected graph.</br>
An undirected graph means that all the edges in the graph are bidirectional. </br>
The Algorithm finds a subset of edges that includes every vertex where the total weight of all edges in the tree is minimized. 

## Diagram
The diagram of a graph that I have used to present how the Algorithm works: 
![Diagram 1](https://user-images.githubusercontent.com/15609881/27431168-1fa75ebc-577e-11e7-8a4c-3cabb322b2ac.png)


### Graph representation in .txt
The following numbers in the text file are used to represent the graph:</br>

6 9 0  ← This means:  6 = number of verices, 9 = number of edges</br>
1 2 1  </br>
1 4 5</br>
1 3 3</br>
2 3 6</br>
2 6 12</br>
3 4 6</br>
3 5 4</br>
3 6 8</br>
4 5 7</br>
5 6 3</br>

The numbers in the first column represent the vertice, the number in the second column represent vertice its connected to, and the numbers in the third column represent the weight e.g:</br>
<b>1 2 1 </b>: Means <b>A</b> connects to <b>B</b> using weight <b>1</b></br>

### Adjacency Matrix

This is a simple representation of the graph using Adjacency Matrix.
![Adjacency Matrix](https://user-images.githubusercontent.com/15609881/27431169-1fa855ec-577e-11e7-8009-0d8d8814d4d6.png)

### Adjacency Lists Diagram

This is a simple representation of the graph using Adjacency Lists Diagram. 
![ListsDiagram1](https://user-images.githubusercontent.com/15609881/27431305-a796d44c-577e-11e7-97d1-dbadcbdfa265.png)

The diagram indicates to which of other vertices each of the vertices is connected to and shows the weight associated with the edge, e.g.: A is connected to B with the weight of 1 is represented as: 
![LIstDiagram2](https://user-images.githubusercontent.com/15609881/27431308-aa08a89a-577e-11e7-8be4-c9967f79571d.png)

## MST superimposed on the graph
![MST](https://user-images.githubusercontent.com/15609881/27431511-61dcaa84-577f-11e7-87a0-dd554159bc4e.png)
