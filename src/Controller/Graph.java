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
    public void setAdjacencyMatrixt()
    {
        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                // we have to check if neighbors are not buildings

                //checking Up
                if(i-1>=0)
                {

                }
                //cheking UP Right
                if (i-1>=0 && j+1<city.getWidth())
                {


                }

                //checking right
                if (j+1 < city.getWidth())
                {

                }
                //checking downRight
                if( i+1 <city.getHeight() && j+1<city.getWidth())
                {

                }
                //checking down
                if (i+1 <city.getHeight())
                {

                }
                //checking down Left
                if (i+1 <city.getHeight() && j-1 >= 0){

                }

                //checking left
                if (j-1 >=0){

                }

                //checking UP LEFT
                if(i-1 >=0 && j-1 >=0){

                }


            }

        }


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