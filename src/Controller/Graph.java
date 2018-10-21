package Controller;

import Model.City;
import Model.Node;

import java.util.ArrayList;
import java.util.List;

//This class will be a graph which represent our City without buildings
public class Graph {

    private City city;
    private List<Node> listOfAllNodes=new ArrayList<Node>();
    private int [][] adjacencyMatrix;


    public Graph()
    {
        //Contsruct our City
        this.city=new City(5,5);

    }

    

}