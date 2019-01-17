package Controller;

import Model.City;
import Model.Node;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


//This class will be a graph which represent our City without buildings
public class Graph {

    private City city;
    private List<Node> listOfAllNodes=new ArrayList<Node>();
   // private int [][] adjacencyMatrix;




    public City getCity() {
        return city;
    }

    public List<Node> getListOfAllNodes() {
        return listOfAllNodes;
    }


    public Graph(int size, double proportion)
    {
        //Contsruct our City
        this.city=new City(size,size,proportion);
        int tailleGraphMatrix= (int) (city.getHeight()*city.getWidth()*(1-proportion));

    }

    /** The goal of this method is to change Dynamically all the costs
     Maybe when the algorithm is looking for the ideal path , An accident will happen in a particular Road
     So the algorithm must include this to have the ideal PATH */


    public void refreshAllCosts(){

        int NumberOfVertices=listOfAllNodes.size();

        for(int i=0;i<NumberOfVertices;i++)
        {
            int NumberOfNeighbors = listOfAllNodes.get(i).getNeighbors().size();
            Node currentNode = listOfAllNodes.get(i);

            for(int j=0;j<NumberOfNeighbors;j++)
            {
                Random random = new Random();
                int randomNumber=random.nextInt(5) + 1;
                currentNode.getNeighbors().get(j).set(1,randomNumber); /** Here we change the cost of neighbor j with a random number */
            }

        }

    }

    /**method to set the List of all nodes*/

    public  void setListOfAllNodes()
    {
        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                // IF It's not a building then we add it in out GRAPH
                if (!city.getMatrice().get(i).get(j).isOccupied())

                    listOfAllNodes.add(city.getMatrice().get(i).get(j));
            }

        }
    }

    /**In this methode we will store all relations between nodes ( means we will store edges )
     At the begining we will consider our graph unweighted*/

    public void setAllNeighbors()
    {
        for(int i=0;i<city.getHeight();i++)
        {

            for(int j=0;j<city.getWidth();j++) {

                // We check only for nodes witch are not buildings
                if (!city.getMatrice().get(i).get(j).isOccupied()) {

                    // we have to check if neighbors are not buildings so we add them
                    //checking Up
                    if (i - 1 >= 0) {
                        if (!city.getMatrice().get(i-1).get(j).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i-1).get(j));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);
                        }


                    }
                    //cheking UP Right
                    if (i - 1 >= 0 && j + 1 < city.getWidth()) {
                        if (!city.getMatrice().get(i-1).get(j+1).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i-1).get(j+1));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);

                        }


                    }

                    //checking right
                    if (j + 1 < city.getWidth()) {
                        if (!city.getMatrice().get(i).get(j+1).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j+1));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);

                        }

                    }
                    //checking downRight
                    if (i + 1 < city.getHeight() && j + 1 < city.getWidth()) {
                        if (!city.getMatrice().get(i+1).get(j+1).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i+1).get(j+1));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);

                        }
                    }
                    //checking down
                    if (i + 1 < city.getHeight()) {
                        if (!city.getMatrice().get(i+1).get(j).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i+1).get(j));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);
                        }

                    }
                    //checking down Left
                    if (i + 1 < city.getHeight() && j - 1 >= 0) {
                        if (!city.getMatrice().get(i+1).get(j-1).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i+1).get(j-1));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);
                        }

                    }

                    //checking left
                    if (j - 1 >= 0) {
                        if (!city.getMatrice().get(i).get(j-1).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j-1));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);

                        }

                    }

                    //checking UP LEFT
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (!city.getMatrice().get(i-1).get(j-1).isOccupied()) {
                            // Je recupere son index à partir de ListDesGraphNode
                            // Puis je le met dans le tableau
                            // l 'index representre a valeur de la colone
                            int index = listOfAllNodes.indexOf(city.getMatrice().get(i-1).get(j-1));
                            int currentIndex = listOfAllNodes.indexOf(city.getMatrice().get(i).get(j));

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 1);

                        }

                    }


                }
            }
        }


    }


}