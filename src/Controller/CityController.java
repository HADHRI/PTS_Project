package Controller;
//This is the controller

import Model.City;
import Model.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class CityController {


    public static void setTrafficligth(City city, double RedProportion, double GreenProportion)
    {

        //Random Object
        Random random = new Random();


        int maxTrafficligth = (int) ( city.getHeight()*city.getWidth()*RedProportion);
        int numTrafficligth=0;


        while(numTrafficligth <maxTrafficligth) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the traffic ligth is null
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && city.getMatrice()[randomRow][randomCol].getMy_traffic_ligth()== null) {
                city.getMatrice()[randomRow][randomCol].setMy_traffic_ligth(Node.Traffic_ligth.RED);
                numTrafficligth+=1;
            }
        }


        maxTrafficligth = (int) ( city.getHeight()*city.getWidth()*GreenProportion);
        numTrafficligth=0;

        while(numTrafficligth <maxTrafficligth) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the traffic ligth is null
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && city.getMatrice()[randomRow][randomCol].getMy_traffic_ligth()==null) {
                city.getMatrice()[randomRow][randomCol].setMy_traffic_ligth(Node.Traffic_ligth.GREEN);
                numTrafficligth+=1;
            }
        }

    }

    public static void setBus(City city, double BusProportion)
    {
        //Random Object
        Random random = new Random();


        int maxBus = (int) ( city.getHeight()*city.getWidth()*BusProportion);
        int numBus=0;


        while(numBus<maxBus) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the node does not already contain a bus
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && !city.getMatrice()[randomRow][randomCol].isBus()) {
                city.getMatrice()[randomRow][randomCol].setBus(true);
                numBus+=1;
            }
        }
    }

    public static void setTaxi(City city, double TaxiProportion)
    {
        //Random Object
        Random random = new Random();


        int maxTaxi = (int) ( city.getHeight()*city.getWidth()*TaxiProportion);
        int numTaxi=0;


        while(numTaxi<maxTaxi) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the node does not already contain a taxi
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && !city.getMatrice()[randomRow][randomCol].isTaxi()) {
                city.getMatrice()[randomRow][randomCol].setTaxi(true);
                numTaxi+=1;
            }
        }
    }

    public static void setAccident(City city, double AccidentProportion)
    {
        //Random Object
        Random random = new Random();


        int maxAccident = (int) ( city.getHeight()*city.getWidth()*AccidentProportion);
        int numAccident=0;


        while(numAccident<maxAccident) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the node does not already contain a accident
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && !city.getMatrice()[randomRow][randomCol].isAccident()) {
                city.getMatrice()[randomRow][randomCol].setAccident(true);
                numAccident+=1;
            }
        }
    }

    public static void setSpeedlimit(City city)
    {
        //Random Object
        Random random = new Random();

        for(int i=0; i<city.getHeight(); i++)
        {
            for(int j=0;j<city.getWidth(); j++)
            {
                //if the node is not a building
                if (!city.getMatrice()[i][j].isOccupied()){
                    // To generate a speed between 30 and 70
                    int randomSpeedLimit = random.nextInt(5)+3;
                    randomSpeedLimit*=10;

                    city.getMatrice()[i][j].setSpeed_limit(randomSpeedLimit);
                }

            }
        }
    }

    public static void setCity(City city,double RedProportion, double GreenProportion,double BusProportion,double TaxiProportion,double AccidentProportion)
    {
        setTrafficligth(city, RedProportion, GreenProportion);
        setBus(city, BusProportion);
        setTaxi(city,TaxiProportion);
        setAccident(city,AccidentProportion);
        setSpeedlimit(city);
    }

    /*

    function Dijkstra(Graph, source):
         for each vertex v in Graph:	// Initialization
                dist[v] := infinity	// initial distance from source to vertex v is set to infinite
                previous[v] := undefined	// Previous node in optimal path from source
                dist[source] := 0	// Distance from source to source
                Q := the set of all nodes in Graph	// all nodes in the graph are unoptimized - thus are in Q


          while Q is not empty:	// main loop
            	u := node in Q with smallest dist[ ]
                remove u from Q



   for each neighbor v of u:	// where v has not yet been removed from Q.
            11:	alt := dist[u] + dist_between(u, v)
	if alt < dist[v]	// Relax (u,v)
            13:	dist[v] := alt
	previous[v] := u



15:	return previous[ ]

*/





    public static ArrayList<Node> Dijkistra(Graph graph, Node Depart, Node Arrived)
    {

        int [] distance = new int [graph.getListOfAllNodes().size()];//Array to store all the distances from the Depart
        ArrayList <Node> previousNode = new ArrayList<Node>(graph.getListOfAllNodes().size());//List to store all the previous nodes from the current node
        int IndexDepart=graph.getListOfAllNodes().indexOf(Depart);
        int IndexArrived=graph.getListOfAllNodes().indexOf(Arrived);
        Queue<Node> unvisited = new LinkedList<Node>();//Queue to store unvisited nodes
        Node bestnode= Depart;
        int alt=0;



        for(int i=0; i<graph.getListOfAllNodes().size(); i++)//Initializatiuon
        {

                 distance[i]=-1;// initial distance from source to node i is set to infinite
                 previousNode.add(new Node(i,1));// previous nodes are undefined

                 unvisited.add(graph.getListOfAllNodes().get(i));

        }


        distance[IndexDepart]=0;

        while(unvisited.size()!=0)
        {

            unvisited.remove(bestnode);//remove  the  closest neighbor to our current location

            for(int i=0; i<graph.getNeighbors(bestnode).size(); i++)//for each neighbor of bestnode
            {

                if(unvisited.remove(graph.getNeighbors(bestnode).get(i)))//if the neighbor at index i has not been yet removed from queue
                {
                    alt=distance[graph.getListOfAllNodes().indexOf(bestnode)]+1; //alt := distance[bestnide] + dist_between(bestnode, children(i))

                    if(alt<distance[graph.getListOfAllNodes().indexOf(graph.getNeighbors(bestnode).get(i))])// if alt < distance[neighbor(i)]
                    {
                        distance[graph.getListOfAllNodes().indexOf(graph.getNeighbors(bestnode).get(i))]=alt;//distance[children(i)]=alt
                        previousNode.add(graph.getListOfAllNodes().indexOf(graph.getNeighbors(bestnode).get(i)),bestnode);
                    }

                }

            }
        }


        return previousNode;

    }

    //Just to test the set methods
    public static void main(String[] args) {

        City city = new City (100,100);
        setCity(city,0.1,0.1,0.1,0.1,0.1);

        Graph graph=new Graph();
        graph.setListOfAllNodes();
        graph.setAdjacencyMatrixt();

        System.out.println(" ");
        System.out.println(" Dijkistra path ");


        ArrayList<Node>path=Dijkistra(graph,graph.getListOfAllNodes().get(0),graph.getListOfAllNodes().get(2));

        for(int i=0; i<path.size();i++)
        {
            System.out.println(" " + path.get(i).getRow() + "  " + path.get(i).getColumn() +  " --> " );
        }

        System.out.println("HELLO WORLD !!");


    }
}
