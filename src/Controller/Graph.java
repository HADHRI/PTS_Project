package Controller;

import Model.City;
import Model.Node;


import java.util.ArrayList;
import java.util.List;



//This class will be a graph which represent our City without buildings
public class Graph {

    private City city;
    private List<Node> listOfAllNodes=new ArrayList<Node>();



    public City getCity() {
        return city;
    }

    public List<Node> getListOfAllNodes() {
        return listOfAllNodes;
    }


    public Graph(String file)
    {
        //Contsruct our City
        this.city=new City(file);


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

                int indexofneighbor=currentNode.getNeighbors().get(j).get(0);

                if(listOfAllNodes.get(indexofneighbor).isTaxi()){
                    currentNode.getNeighbors().get(j).set(1,100/4);

                }
                if(listOfAllNodes.get(indexofneighbor).getTrafficLight()!=null){
                    if(listOfAllNodes.get(indexofneighbor).getTrafficLight().isState()== true ) {
                        currentNode.getNeighbors().get(j).set(1, 100/6);
                    }

                    else{
                        //todo  put the cost of the simple route
                        currentNode.getNeighbors().get(j).set(1,100/2 );
                    }
                }
                if(listOfAllNodes.get(indexofneighbor).isBus()){
                    currentNode.getNeighbors().get(j).set(1,100/3);
                }
                if(listOfAllNodes.get(indexofneighbor).isAccident()){
                    currentNode.getNeighbors().get(j).set(1,100/1);
                }
                if(listOfAllNodes.get(indexofneighbor).isStop()){
                    currentNode.getNeighbors().get(j).set(1,100/4);
                }
                if(listOfAllNodes.get(indexofneighbor).getSpeed_limit()==30){
                    // currentNode.getNeighbors().get(j).set(1,currentNode.getNeighbors().get(j).get(1)-2);

                }
                if(listOfAllNodes.get(indexofneighbor).getSpeed_limit()==50){
                    // currentNode.getNeighbors().get(j).set(1,currentNode.getNeighbors().get(j).get(1)-3);

                }
                if(listOfAllNodes.get(indexofneighbor).getSpeed_limit()==70){
                    //currentNode.getNeighbors().get(j).set(1,currentNode.getNeighbors().get(j).get(1)-4);

                }
            }

        }

    }

    /**method to set the List of all nodes*/

    public  void setListOfAllNodes()
    {
        int index=0;

        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                // IF It's not a building then we add it in out GRAPH
                if (!city.getMatrice().get(i).get(j).isOccupied()) {
                    listOfAllNodes.add(city.getMatrice().get(i).get(j));
                    city.getMatrice().get(i).get(j).setIndexofgraph(index);

                    index++;
                }
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

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 15);
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

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 15);

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

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 15);
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

                            listOfAllNodes.get(currentIndex).addNeighbors(index, 15);

                        }

                    }

                }
            }
        }

    }
}