# PTS_Project

PTS PROJECT : Traffic Simulator

1) Objective

The purpose of our project is to build a pathfinding algorithm that find the “best” path between two places in a city. When we say “best”, we don’t only speak about the shortest path. To choose the best path we will consider the number of traffic lights, bus, taxi, etc.

2) The environment

For our project we choose to have a MVC environment. The model contains the structure of the city which is a matrix of nodes and the structure of the node himself. The Controller contains the CityController which is used to fill the empty city with traffic lights, bus, taxi, etc. In the CityController you can also find the graph generated from the city. The graph contains all the nodes in the city except
the buildings. Finally, the View is just a representation in the console of our city, graph and results from pathfinding algorithm.
It’s important to recall that our city is not a representation of a real city. It’s just a city that we randomly built.

3) The pathfinding algorithm

Our pathfinding algorithm need to be very efficient in space and time complexity. For that, we will adapt the A* algorithm to our environment. But first we will begin with more simple algorithms like Dijkstra or Bidirectional Search. Maybe we will combine all these algorithms. The purpose is just to have an efficient pathfinding algorithm. We will apply this algorithm with the depart, the destination, the graph in input and the best path in output.

4) Improvements

At the end of our project, if we have the time, we would like to have a dynamic pathfinding algorithm. For example, if an accident happen on the way which was the best path, then we need to find another path from our current position to the destination.
We also would like to put few users of the pathfinding algorithm in the same city at the same time.

5) Current Progress

What is already done:

- Definition of the MVC
- Implementation of the model (city and node classes)
- Implementation of the Controller (CityController and graph classes)
- Implementation of Dijkistra Algorithm
- Adaptation of Dijkistra Algorithm to our environment
- Implementation of the view classe

What we are actually doing:

- Implementation of Dijkistra Algorithm with an Indexed Min Priority Queue
- Implementation of a dynamic Dijkistra Algorithm 

What we are planning to do:

- Create a short Video that present our project
- Implementation of A* Algorithm

