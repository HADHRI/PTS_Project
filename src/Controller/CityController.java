package Controller;
//This is the controller

import Model.City;
import Model.Node;
import Ressources.IndexMinPQ;

import java.util.*;

public class CityController {
    public  Graph graph;


    public CityController(){
        graph=new Graph();
        graph.setListOfAllNodes();
        graph.setAdjacencyMatrixt();
        graph.refrechAdjacencyMatirx();

    }

/** We have then to adapt City matrix to array list
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
    } **/

    /**this function is used to generated randomly in the city traffic lights, bus, taxi, accident and speed limit*/
/**
    public static void setCity(City city,double RedProportion, double GreenProportion,double BusProportion,double TaxiProportion,double AccidentProportion)
    {
        setTrafficligth(city, RedProportion, GreenProportion);
        setBus(city, BusProportion);
        setTaxi(city,TaxiProportion);
        setAccident(city,AccidentProportion);
        setSpeedlimit(city);
    } **/


    /**this function returns a int table containing 2 lines
     first line represents the distance from the source vertix to the current vertix
     second line represents the index of the previous vertix to reach the current vertix*/


    public int [][] djikistraShortPath(int sourceIndex)
    {
        int numberOfVertices=graph.getListOfAllNodes().size();
        int pathHoldingDistances [][]=new int[2][numberOfVertices];


        boolean  visitedNodes[]=new boolean[numberOfVertices];


        // intializing source distance as 0 and others as INFINITY
        // initializing previous vertix as undifined ( I will note undifined as -1 )
        pathHoldingDistances[0][sourceIndex]=0;
        for( int i=0;i<numberOfVertices;i++)
        {
            if(i != sourceIndex)
                pathHoldingDistances[0][i]=Integer.MAX_VALUE; // Infinity
            pathHoldingDistances[1][i]=-1 ; // -1 For undifined Previous

        }
        int weHaveVisitedAllVertices=0;
        while (weHaveVisitedAllVertices<numberOfVertices-1)
        {
            //vertex  with min value
            int indexOfMinimumVertice=getMinimumDist(visitedNodes,pathHoldingDistances[0]);//return the index in the array of the minimum distance for the nodes that are not visited
            visitedNodes[indexOfMinimumVertice]=true; // mark this node as visited
            weHaveVisitedAllVertices++;

            //For each neighbour of this Min Vertice
            for (int j=0;j< numberOfVertices;j++)
            {
                //To verify that the neighbour is still not visited
                if (graph.getAdjacencyMatrix()[indexOfMinimumVertice][j]!=0 && visitedNodes[j]==false)
                {
                    int distanceBetweenTwoVertices =graph.getAdjacencyMatrix()[indexOfMinimumVertice][j];//distance between the minimum choose and it's neighbor j
                    int alt=pathHoldingDistances[0][indexOfMinimumVertice]+distanceBetweenTwoVertices;//distance between the neighbor j and the source
                    if (alt < pathHoldingDistances[0][j])//if the distance for the neighbor j is not defined
                    {
                        pathHoldingDistances[0][j]=alt;//to change the distance for the neighbor j in the array of distances
                        pathHoldingDistances[1][j]=indexOfMinimumVertice; // to put the index of previous
                    }


                }
            }


        }
        return pathHoldingDistances;

    }

    /**this function returns a int table containing 2 lines using an Index minimum priority Queue
     first line represents the distance from the source vertix to the current vertix
     second line represents the index of the previous vertix to reach the current vertix*/

    public int [][] djikistraShortPathIndexMinPQ(int sourceIndex)
    {
        int numberOfVertices=graph.getListOfAllNodes().size();
        int pathHoldingDistances [][]=new int[2][numberOfVertices];
        IndexMinPQ<Integer> indexMPQ=new IndexMinPQ<Integer>(numberOfVertices);
        boolean  visitedNodes[]=new boolean[numberOfVertices];


        // intializing source distance as 0 and others as INFINITY
        // initializing previous vertix as undifined ( I will note undifined as -1 )
        pathHoldingDistances[0][sourceIndex]=0;
        for( int i=0;i<numberOfVertices;i++)
        {
            if(i != sourceIndex)
                pathHoldingDistances[0][i]=Integer.MAX_VALUE; // Infinity
            pathHoldingDistances[1][i]=-1 ; // -1 For undifined Previous
            indexMPQ.insert(i,pathHoldingDistances[0][i]);
        }

        while (!indexMPQ.isEmpty())//
        {

            int indexOfMinimumVertice=indexMPQ.delMin();//remove the minimum distance and return it's associated index

            visitedNodes[indexOfMinimumVertice]=true; // mark this node as visited


            //For each neighbour of this Min Vertice
            for (int j=0;j< numberOfVertices;j++)
            {
                //To verify that the neighbour is still not visited
                if (graph.getAdjacencyMatrix()[indexOfMinimumVertice][j]!=0 && visitedNodes[j]==false)
                {

                    int distanceBetweenTwoVertices =graph.getAdjacencyMatrix()[indexOfMinimumVertice][j];//distance between the minimum choose and it's neighbor j
                    int alt=pathHoldingDistances[0][indexOfMinimumVertice]+distanceBetweenTwoVertices;//distance between the neighbor j and the source
                    if (alt < pathHoldingDistances[0][j])//if the distance for the neighbor j is not defined
                    {
                        pathHoldingDistances[0][j]=alt;//to change the distance for the neighbor j in the array of distances
                        pathHoldingDistances[1][j]=indexOfMinimumVertice; // to put the index of previous

                        indexMPQ.changeKey(j,alt);//to change the distance for the neighbor j in the indexed min priority queue
                    }


                }
            }


        }
        return pathHoldingDistances;

    }

    /** this methode returns the index of vertice with minimum distance*/

    private int getMinimumDist(boolean[] vistedNodes,int[] distances)
    {
        int max=Integer.MAX_VALUE;
        int indexOfMinimum=-1;
        for (int i=0;i<distances.length;i++)
        {
            if(!vistedNodes[i] && distances[i] < max )
            {
                indexOfMinimum=i;
                max=distances[i];


            }
        }
        return  indexOfMinimum;

    }

    /** this methode returns the index of vertice with maximum distance*/

    private int getMaximumDist(boolean[] vistedNodes,int[] AdjencyRow)
    {
        int min=0;
        int indexOfMaximum=-1;
        for (int i=0;i<AdjencyRow.length-1;i++)
        {
            if(!vistedNodes[i] && AdjencyRow[i] > min )
            {
                indexOfMaximum=i;
                min=AdjencyRow[i];


            }
        }
        return  indexOfMaximum;

    }

    /** this method is when we want to run Dynamic Djikistra , means that instead of taking the static path
     of djikistra algorithm , An incident will happen ( like an accident , or bus or taxi that changes the cost
     in our graph ) , so our road could change
     positionOnStaticDjikistraPath reporesents the position in the previous Path . this position becomes
     our new source and our target stills the same . So we could have another road in the case of changing the adjacency Matrix
     * **/

    public void printDynamicDjikistra(int sourceIndex,int targetIndex,int  positionOnStaticDjikistraPath) throws InterruptedException {
        int pathAndDistance[][] = djikistraShortPath(sourceIndex);

        ArrayList<Integer>staticDjikistraPath=new ArrayList<>(); //To hold the final path
        ArrayList<Integer>DynamicDjikistraPath=new ArrayList<>();

        // I will use stack to stock the PATH
        Stack<Integer> stackHoldingPath = new Stack<>();
        stackHoldingPath.push(targetIndex);
        int index = targetIndex;
        // CHECK IF THE TARGET INDEX IS REACHABLE
        if (pathAndDistance[1][targetIndex]==-1)
        {
            System.out.println("You can't reach this road from your position with car ");
        }
        else{
            for(int i=0;i<graph.getListOfAllNodes().size();i++)
            {
                // System.out.print(pathAndDistance[1][i] + "  ");

            }
            //  System.out.println(index);
            while (index != sourceIndex)
            {
                // System.out.println(index);
                index=pathAndDistance[1][index];
                stackHoldingPath.push(index);
            }
            //  System.out.println("Stack => " + stackHoldingPath);
            //Pop  the elements of the stack and put it into an Array list that contains
            while (!stackHoldingPath.isEmpty())
            {
                staticDjikistraPath.add(stackHoldingPath.pop());

            }

            /**  positionOnStaticDjikistraPath should be between 0 and the the length of the static path -1
             * Simple example : admit that static Djikistra Path is A B C D E
             *  If  positionOnStaticDjikistraPath=1 then when changing the adjacency matrix , the path will take another
             *  value that could be A ...E
             *  Hope that my example is clear
             * **/
            /**  Print the static path  **/
            System.out.println("STATIC PATH");
            printInformationAboutPath(staticDjikistraPath,sourceIndex,targetIndex);
            /** Print the static path distance **/
            System.out.println("DISTANCE OF THIS PATH IS "+pathAndDistance[0][targetIndex]);
            System.out.println("END OF STATIC PATH");
            if (positionOnStaticDjikistraPath >0 && positionOnStaticDjikistraPath <staticDjikistraPath.size()-1)
            {

                /** in this case the we have to store the newSource **/
                int newSource=staticDjikistraPath.get(positionOnStaticDjikistraPath-1);

                /**     Printing the first part of the Dynamic Path , from the source to the currentPosition  **/
                System.out.println("BEGINING OF THE DYNAMIC PATH --- ");
                for (int i=0;i<positionOnStaticDjikistraPath;i++)
                {
                    DynamicDjikistraPath.add(staticDjikistraPath.get(i));
                }
                /**   System.out.println("printing dynamic table");
                 for (int i=0;i<positionOnStaticDjikistraPath;i++)
                 {
                 System.out.println(DynamicDjikistraPath.get(i));
                 }**/


                printInformationAboutPath(DynamicDjikistraPath,sourceIndex,newSource);

                /**    changing the  adjacency Matrix **/
                graph.refrechAdjacencyMatirx();

                /** printing the continuty of the Dynamic Djikistra Path **/
                printDjikistraPath(newSource,targetIndex);



            }

        }






    }

    /** this method is used to print the shortest path between the source and the target when we use Djikistra.
     First we will used the method dijkistraShortPath to store all the paths, then we will choose the best path between all these paths.
     * **/

    public void printDjikistraPath(int sourceIndex,int targetIndex) throws InterruptedException {
        int pathAndDistance[][] = djikistraShortPath(sourceIndex);
        ArrayList<Integer>path=new ArrayList<>(); //To hold the final path

        // I will use stack to stock the PATH
        Stack<Integer> stackHoldingPath = new Stack<>();
        stackHoldingPath.push(targetIndex);
        int index = targetIndex;
        // CHECK IF THE TARGET INDEX IS REACHABLE
        if (pathAndDistance[1][targetIndex]==-1)
        {
            System.out.println("You can't reach this road from your position with car ");
        }
        else{
            for(int i=0;i<graph.getListOfAllNodes().size();i++)
            {
                /** print the distance **/
                //  System.out.print(pathAndDistance[1][i] + "  ");

            }
            //  System.out.println(index);
            while (index != sourceIndex)
            {
                // System.out.println(index);
                index=pathAndDistance[1][index];
                stackHoldingPath.push(index);


            }
            //Printing the path
            /** to see the path on the stack **/
            //  System.out.println("Stack => " + stackHoldingPath);
            while (!(stackHoldingPath.isEmpty()))
            {
                path.add(stackHoldingPath.peek());
                // System.out.println(stackHoldingPath.pop());
                stackHoldingPath.pop();

            }

            printInformationAboutPath(path,sourceIndex,targetIndex);
            System.out.println("DISTANCE OF THIS PATH IS "+pathAndDistance[0][targetIndex]);


        }



    }

    /** this method is used to print the shortest path between the source and the target when we use Djikistra.
     First we will used the method dijkistraShortPathIndexMinPQ to store all the paths, then we will choose the best path between all these paths.
     * **/

    public void printDjikistraPathIndexMinPQ(int sourceIndex,int targetIndex) throws InterruptedException {
        int pathAndDistance[][] = djikistraShortPathIndexMinPQ(sourceIndex);
        ArrayList<Integer>path=new ArrayList<>(); //To hold the final path

        // I will use stack to stock the PATH
        Stack<Integer> stackHoldingPath = new Stack<>();
        stackHoldingPath.push(targetIndex);
        int index = targetIndex;

        for(int i=0;i<graph.getListOfAllNodes().size();i++)
        {
            System.out.print(pathAndDistance[1][i] + "  ");

        }
        System.out.println(index);
        while (index != sourceIndex)
        {
            System.out.println(index);
            index=pathAndDistance[1][index];
            stackHoldingPath.push(index);


        }
        //Printing the path
        System.out.println("Stack => " + stackHoldingPath);
        while (!(stackHoldingPath.isEmpty()))
        {
            path.add(stackHoldingPath.peek());
            System.out.println(stackHoldingPath.pop());

        }

        printInformationAboutPath(path,sourceIndex,targetIndex);
        System.out.println("DISTANCE OF THIS PATH IS "+pathAndDistance[0][targetIndex]);


    }

    /** this method is used to print information about the movements to do if we want to follow the path between the source and the target.* **/

    private void printInformationAboutPath(ArrayList<Integer>path,int sourceIndex,int targetIndex)
    {
        //Current position
        int currentPositionRow= graph.getListOfAllNodes().get(sourceIndex).getRow();
        int currentPositionColumn= graph.getListOfAllNodes().get(sourceIndex).getColumn();

        for (int i=1;i<path.size();i++)
        {
            int nextPositionRow=graph.getListOfAllNodes().get(path.get(i)).getRow();
            int nextPositionColumn=graph.getListOfAllNodes().get(path.get(i)).getColumn();

            // next position is at RIGTH
            if(nextPositionRow==currentPositionRow && nextPositionColumn==currentPositionColumn+1)
            {
                System.out.print("MOVE RIGTH ----");

            }
            else
                // next position is LEFT
                if(nextPositionRow==currentPositionRow && nextPositionColumn==currentPositionColumn-1)
                {
                    System.out.println("MOVE LEFT  ----");
                }
                else
                    //NEXT POSITION UP
                    if(nextPositionColumn==currentPositionColumn && nextPositionRow==currentPositionRow-1 )
                    {
                        System.out.println("MOVE UP  ----");

                    }
                    else
                        //NEXT POSITION DOWNN
                        if(nextPositionColumn==currentPositionColumn && nextPositionRow==currentPositionRow +1 )
                        {
                            System.out.println("MOVE DOWN  ----");

                        }

                        else
                            // Next position UP RIGTH
                            if(nextPositionColumn==currentPositionColumn + 1 && nextPositionRow==currentPositionRow -1 )
                            {
                                System.out.println("MOVE UP RIGTH ----");

                            }
                            else
                                // Next position UP LEFT
                                if(nextPositionColumn==currentPositionColumn - 1 && nextPositionRow==currentPositionRow -1 )
                                {
                                    System.out.println("MOVE UP LEFT  ----");

                                }
                                else
                                    // next position is DOWN RIGTH
                                    if(nextPositionColumn==currentPositionColumn + 1 && nextPositionRow==currentPositionRow + 1 )
                                    {
                                        System.out.println("MOVE DOWN RIGTH  ----");

                                    }
                                    else
                                        //NEXT position is DOWN LEFT
                                        if(nextPositionColumn==currentPositionColumn - 1 && nextPositionRow==currentPositionRow + 1 )
                                        {
                                            System.out.println("MOVE DOWN LEFT  ----");

                                        }

            currentPositionColumn=nextPositionColumn;
            currentPositionRow=nextPositionRow;









        }

    }

    /***
     * This method allow us to travel into the graph from the source to a random node which have no neighbors.
     * We always choose the neighbor with the maximum distance.
     * We will use this method in the A* algorithm to calculate approximate the heuristic (h).
     * @param source is the index of the Source node
     * @param graph is the graph of the city
     * @param visitedNodes is an array that indicate if a node has been already visited
     */

    public int Breadth_First_Search(int source , Graph graph,boolean [] visitedNodes )
    {
        int heuristic=0;/**  It's an approximation of the maximum distance from the source node*/
        Node SourceNode=graph.getListOfAllNodes().get(source); /** create a copy of the source node*/
        visitedNodes[source]=true; /**Just to know that the source node is alreay visited*/
        int indexOfNext=0;


        while (indexOfNext!=-1)/** While the current node has neighbors*/
        {
            Node current = SourceNode; /** The node where we are actually*/
            int indexCurrentNode=graph.getListOfAllNodes().indexOf(current);/** Index of the current node*/
            System.out.println(indexCurrentNode + " ");

            /**Thanks to the method getMaximumDist we get the index of the neighbor which has the maximum distance*/
            indexOfNext=getMaximumDist(visitedNodes,graph.getAdjacencyMatrix()[indexCurrentNode]);

            if(indexOfNext==-1){break;}/**If the node has only visited neighbors we get out of the while loop*/
            else
            {
                Node next = graph.getListOfAllNodes().get(indexOfNext);
                visitedNodes[indexOfNext] = true;/** To mark the node as visited */
                SourceNode=next;/** The neighbor we choose become the current node*/

                heuristic = heuristic + graph.getAdjacencyMatrix()[indexCurrentNode][indexOfNext];/**We update the heuristic*/
            }
        }

        return heuristic;
    }



    private  int estimateDistanceHeuristic(Node n1, Node n2)
    {
        int x=Math.abs(n1.getRow() - n2.getRow());
        int y= Math.abs(n1.getColumn() - n2.getColumn());
        return x*x + y*y;
    }































//

    /**Main to test the methods*/

    public static void main(String[] args) throws InterruptedException {

        CityController cityController=new CityController();

        System.out.println("This is the city matrix ");
        cityController.graph.getCity().printCityMatrix();
        System.out.println("-------------------------------------------");
        System.out.println(" ");

        int numberOfVertices=cityController.graph.getListOfAllNodes().size();
        boolean  visitedNodes[]=new boolean[numberOfVertices];




        /*System.out.println("this is the end of City Matrix ");
        System.out.println("this is the adjacency matrix ");
     //   cityController.graph.printAdjacencyMatrix();
        System.out.println("This is the end of adjacency matrix ");

        int pathAndDistances[][]=cityController.djikistraShortPath(0);

        System.out.println("Show paths from Source ( A) to all others ");


        for(int i=0;i<cityController.graph.getListOfAllNodes().size();i++)
        {
            System.out.print(pathAndDistances[1][i] + "  ");

        }
        System.out.println();
        System.out.println("Show distances from Source ( A) to all others ");
        for(int i=0;i<cityController.graph.getListOfAllNodes().size();i++)
        {

            System.out.print(pathAndDistances[0][i] + "  ");

        }*/

        //Printing the Path
    /*    System.out.println();
        System.out.println("Printing the path ");
       cityController.printDjikistraPath(0,19);*/


    /*    //Printing the Path
        System.out.println();
        System.out.println("Printing the path with Index Min Priority Queue");
        cityController.printDjikistraPathIndexMinPQ(0,19);
        */

        //cityController.printDynamicDjikistra(0,17,3);

        System.out.println("-------------------------------------------TESTING A STAR ALGORITHM-------------------------------------------");
        Astar astar=new Astar(cityController.graph);
     //   astar.run(cityController.graph.getListOfAllNodes().get(0),cityController.graph.getListOfAllNodes().get(10000));


        System.out.println("-------------------------------------------TESTING DJIKISTRA ALGORITHM-------------------------------------------");

        cityController.printDjikistraPath(0,10000);
        astar.run(cityController.graph.getListOfAllNodes().get(0),cityController.graph.getListOfAllNodes().get(10000));

























    }
}