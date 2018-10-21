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
        this.city=new City(500,500);
        int tailleGraphMatrix= (int) (city.getHeight()*city.getWidth()*0.8);
        adjacencyMatrix=new int[tailleGraphMatrix][tailleGraphMatrix];

    }

    public  void setListOfAllNodes()
    {
        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                // IF It's not a building then we add it in out GRAPH
                if (!city.getMatrice()[i][j].isOccupied())

                    listOfAllNodes.add(city.getMatrice()[i][j]);
            }

        }
    }

    //In this methode we will store all relations between nodes ( means we will store edges )
    // At the begining we will consider our graph unweighted .
    public void setAdjacencyMatrixt(){


    }

    //Main to test
    public static void main(String []args )
    {
        Graph graph=new Graph();
        graph.setListOfAllNodes();
        // size should be number of nodes in the matrix without buildings
        System.out.println(graph.listOfAllNodes.size());
    }



}