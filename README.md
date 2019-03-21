# PTS_Project

PTS PROJECT : Traffic Simulator

Git Hub IDS : johanndesoyres, HADHRI, Junhui7

1) Objective

The purpose of our project is to build a pathfinding algorithm that find the “best” path between two places in a city. When we say “best”, we don’t only speak about the shortest path. To choose the best path we will consider the number of traffic lights, bus, taxi, etc. Then this algorithm will be use in a JAVA Swing Interface. 

2) The environment

For our project we choose to have a MVC environment. The model contains the structure of the city which is a matrix of nodes and the structure of the node himself. The Controller contains the CityController which is used to fill the empty city with traffic lights, bus, taxi, etc. In the CityController you can also find the graph generated from the city. The graph contains all the nodes in the city except the buildings. Finally, the View is just a representation in the console of our city, graph and results from pathfinding algorithm.

It’s important to recall that our city is a representation of Manathan.

3) The pathfinding algorithm

Our pathfinding algorithm need to be very efficient in space and time complexity. For that, we will adapt the A* algorithm to our environment. We will apply this algorithm with the depart, the destination, the graph in input and the best path in output.

4) Improvements

To improve our project , we will create the view of our project . So we want to display the Matrix and we want 
the user see every time the short path to take .  We want also give the possibility to user to put accidents and lights 
dynamically so we could see the change of the path . 
We will use JAVA Swing For this ( Java library for UI)

5) Demo and Presentation Videos

Presentation video : https://youtu.be/e8tmy0bDl5Q
Demo video : https://www.youtube.com/watch?v=eozzu70o9Vw&feature=youtu.be

6) Current Progress

What is already done:

- Definition of the MVC 
- Implementation of the model (city and node classes)
- Implementation of the Controller (CityController and graph classes)
- Implementation of Dijkistra Algorithm
- Adaptation of Dijkistra Algorithm to our environment
- Implementation of the view class
- Implementation of the Indexed Min Priority Queue class
- Implementation of Dijkistra Algorithm with an Indexed Min Priority Queue
- Implementation of a dynamic Dijkistra Algorithm 
- Implementation of a method to refresh the adjency matrix
- Implementation of A star Algorithm 
- Comparing the A star with Djikistra Algorithm
- Solve a problem with  Java heap space while creating a city of more than 200*200 
- Add Game view, Game manager, Car and Button classes
- Display the city with JAVA Swing
- Add function to set the destination dynamically
- Add button "add a normal car"
- Add button "add an Accident"
- Add button "delete an Accident"
- Add Traffic-light classe to change dynamically the color for all Traffic lights
- Add a panel to display a list of cars currently present in the city and to show which one is selected
- Add a panel to display an overview of the path for the selected car

What we are actually doing:

- Writing a report of our project





