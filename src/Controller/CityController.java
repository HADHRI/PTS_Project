package Controller;
//This is the controller

import Model.City;
import Model.Node;


import java.util.*;




public class CityController {
   public  Graph graph;


    public CityController(){
        graph=new Graph();
        graph.setListOfAllNodes();
        graph.setAdjacencyMatrixt();

    }


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


    //this function returns a int table containing 2 lines
    // first line represents the distance from the source vertix to the current vertix
    // second line represents the index of the previous vertix to reach the current vertix


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
        while (!weHaveVisitedAllVertices(visitedNodes))
        {
            //vertex  with min value
            int indexOfMinimumVertice=getMinimumDist(visitedNodes,pathHoldingDistances[0]);
            visitedNodes[indexOfMinimumVertice]=true; // mark this node as visited

            //For each neighbour of this Min Vertice
            for (int j=0;j< numberOfVertices;j++)
            {
                //To verify that the neighbour is still not visited
                if (graph.getAdjacencyMatrix()[indexOfMinimumVertice][j]!=0 && visitedNodes[j]==false)
                {
                  int distanceBetweenTwoVertices =graph.getAdjacencyMatrix()[indexOfMinimumVertice][j];
                    int alt=pathHoldingDistances[0][indexOfMinimumVertice]+distanceBetweenTwoVertices;
                    if (alt < pathHoldingDistances[0][j])
                    {
                        pathHoldingDistances[0][j]=alt;
                        pathHoldingDistances[1][j]=indexOfMinimumVertice; // to put the index of previous
                    }


                }
            }


        }
        return pathHoldingDistances;

    }

    // this methode returns minimum index of vertice with minimum distance
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
    //To verify if we have visited all vertices
    private boolean weHaveVisitedAllVertices(boolean[] tab){
        for (int i=0;i<tab.length;i++)
        {
            if (tab[i]==false)
                return false;
        }

        return true;
    }



    public void printDjikistraPath(int sourceIndex,int targetIndex) throws InterruptedException {
        int pathAndDistance[][] = djikistraShortPath(sourceIndex);
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















    //Just to test the set methods
    public static void main(String[] args) throws InterruptedException {

        CityController cityController=new CityController();

        System.out.println("This is the city matrix ");
        cityController.graph.getCity().printCityMatrix();










        System.out.println("this is the end of City Matrix ");
        System.out.println("this is the adjacency matrix ");
     //   cityController.graph.printAdjacencyMatrix();
        System.out.println("This is the end of adjacency matrix ");

        int pathAndDistances[][]=cityController.djikistraShortPath(7);

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

        }

        //Printing the Path
        System.out.println();
        System.out.println("Printing the path ");
       cityController.printDjikistraPath(0,12);











     /*   City city = new City (100,100);



        setCity(city,0.1,0.1,0.1,0.1,0.1);

        int compGreenligth=0;
        int compRedligth=0;
        int compBus=0;
        int compTaxi=0;
        int compAccident=0;
        int compEmptyNode=0;

        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                if(city.getMatrice()[i][j].getMy_traffic_ligth()== Node.Traffic_ligth.RED)//or with RED
                {
                    compRedligth+=1;
                }
                if(city.getMatrice()[i][j].getMy_traffic_ligth()== Node.Traffic_ligth.GREEN)//or with RED
                {
                    compGreenligth+=1;
                }
                if(city.getMatrice()[i][j].isBus())//or with RED
                {
                    compBus+=1;
                }
                if(city.getMatrice()[i][j].isTaxi())//or with RED
                {
                    compTaxi+=1;
                }
                if(city.getMatrice()[i][j].isAccident())//or with RED
                {
                    compAccident+=1;
                }
                if(!city.getMatrice()[i][j].isAccident() &&  !city.getMatrice()[i][j].isBus() && !city.getMatrice()[i][j].isTaxi() && city.getMatrice()[i][j].getMy_traffic_ligth()==null)//or with RED
                {
                    compEmptyNode+=1;
                }
            }

        }
        System.out.println(compGreenligth);
        System.out.println(compRedligth);
        System.out.println(compBus);
        System.out.println(compTaxi);
        System.out.println(compAccident);
        System.out.println(compEmptyNode);

        System.out.println("HELLO WORLD !!");*/



    }
}
