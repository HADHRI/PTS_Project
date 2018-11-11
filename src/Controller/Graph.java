package Controller;

import Model.City;
import Model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//This class will be a graph which represent our City without buildings
public class Graph {

    private City city;
    private List<Node> listOfAllNodes=new ArrayList<Node>();
    private int [][] adjacencyMatrix;

    public City getCity() {
        return city;
    }

    public List<Node> getListOfAllNodes() {
        return listOfAllNodes;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public Graph()
    {
        //Contsruct our City
        this.city=new City(5,5);
        int tailleGraphMatrix= (int) (city.getHeight()*city.getWidth()*0.8);


        adjacencyMatrix=new int[tailleGraphMatrix+1][tailleGraphMatrix+1];
        for(int i=0;i<tailleGraphMatrix;i++)
        {
            for(int j=0;j<tailleGraphMatrix;j++)
            {
                adjacencyMatrix[i][j]=0;
            }
        }

    }

    //The goal of this method is to change Dynamically the state of a Road
    // Maybe when the algorithm is looking for the ideal path , An accident will happen in a particular Road
    // So the algorithm must include this to have the ideal PATH
    public void refrechAdjacencyMatirx(){
        int tailleGraphMatrix=adjacencyMatrix[0].length;
        //Adjacency matrix is Symetric Matrix
        int indexSymetricMatrix=0;
        for(int i=0;i<tailleGraphMatrix;i++)
        {

            for(int j=indexSymetricMatrix;j<tailleGraphMatrix;j++)
            {
              if ( adjacencyMatrix[i][j]==1)
              {
                  // we generate random number between 1 and 5
                  Random random = new Random();
                  int randomNumber=random.nextInt(5) + 1;
                  adjacencyMatrix[i][j]= randomNumber;
                  //Undirected graph so
                  adjacencyMatrix[j][i]= randomNumber;


              }

            }
            indexSymetricMatrix++;

        }



    }

    public  void printAdjacencyMatrix(){
        for(int i=0;i<adjacencyMatrix.length;i++)
        {
            for(int j=0;j<adjacencyMatrix.length;j++)
            {
                System.out.print(adjacencyMatrix[i][j]+" ");
            }
            System.out.println();
        }

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
    // At the begining we will consider our graph unweighted
    public void setAdjacencyMatrixt()
    {
        for(int i=0;i<city.getHeight();i++)
        {

            for(int j=0;j<city.getWidth();j++) {

                // We check only for nodes witch are not buildings
                if (!city.getMatrice()[i][j].isOccupied()) {

                    // we have to check if neighbors are not buildings so we add them
                    //checking Up
                    if (i - 1 >= 0) {
                        if (!city.getMatrice()[i - 1][j].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i - 1][j]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);
                            adjacencyMatrix[currentIndex][index] = 1;


                        }


                    }
                    //cheking UP Right
                    if (i - 1 >= 0 && j + 1 < city.getWidth()) {
                        if (!city.getMatrice()[i - 1][j + 1].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i - 1][j + 1]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);
                            adjacencyMatrix[currentIndex][index] = 1;


                        }


                    }

                    //checking right
                    if (j + 1 < city.getWidth()) {
                        if (!city.getMatrice()[i][j + 1].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i][j + 1]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);

                            adjacencyMatrix[currentIndex][index] = 1;


                        }

                    }
                    //checking downRight
                    if (i + 1 < city.getHeight() && j + 1 < city.getWidth()) {
                        if (!city.getMatrice()[i + 1][j + 1].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i + 1][j + 1]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);
                            // System.out.println(index);
                            adjacencyMatrix[currentIndex][index] = 1;
                        } 
                        }
                    //checking down
                    if (i + 1 < city.getHeight()) {
                        if (!city.getMatrice()[i + 1][j].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i + 1][j]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);
                            //System.out.println(index);
                            adjacencyMatrix[currentIndex][index] = 1;


                        }

                    }
                    //checking down Left
                    if (i + 1 < city.getHeight() && j - 1 >= 0) {
                        if (!city.getMatrice()[i + 1][j - 1].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i + 1][j - 1]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);

                            adjacencyMatrix[currentIndex][index] = 1;


                        }

                    }

                    //checking left
                    if (j - 1 >= 0) {
                        if (!city.getMatrice()[i][j - 1].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i][j - 1]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);
                            System.out.println(index);
                            adjacencyMatrix[currentIndex][index] = 1;


                        }

                    }

                    //checking UP LEFT
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (!city.getMatrice()[i - 1][j - 1].isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice()[i - 1][j - 1]);
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice()[i][j]);
                            adjacencyMatrix[currentIndex][index] = 1;


                        }

                    }


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
       // System.out.println(graph.listOfAllNodes.size());
        //The matrix before Setting adjacency Matrix ( initialized with 0 values)
        //   graph.printAdjacencyMatrix();
        // System.out.println(graph.listOfAllNodes.indexOf(graph.city.getMatrice()[0][2]));
        // printing the city with batiments ( to visualise the city with buildings)
        graph.city.printCityMatrix();
        System.out.println("AFTER INSERTING EDGES ");
        graph.setAdjacencyMatrixt();
        graph.printAdjacencyMatrix();





    }

}