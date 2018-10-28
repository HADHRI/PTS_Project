package Controller;
//This is the controller

import Model.City;
import Model.Node;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * This algorithm is used to find the shortest path of any two points in the undirected graph
     * @param
     * @param start start point
     * @param end end point
     * @return a array of path point
     */
    public static int[] dijkstra(int [][] adjacencyMatrix,int start,int end){
        //int[][] adjacencyMatrix = graph.getAdjacencyMatrix().clone();

        int n = adjacencyMatrix.length;
        //Store the shortest path from start to other points
        int [] shortest = new int[n];
        // Mark whether the shortest path of the current vertex has been found, true means that it has been found
        boolean[] visited = new boolean[n];

        shortest[start] = 0;
        visited[start] = true;


        //List path = new ArrayList();
        String[] path = new String[n];
        for(int i = 0; i < n; i++){
            path[i] = new String(start + "--->" + i);
        }
        for(int count = 0; count != n-1; count ++)
        {

            int index = 0;
            int min = 0;
            for(int i =0; i< n ; i++)
            {
                if( !visited[i] && adjacencyMatrix[start][i] != 0)
                {
                    if(min == 0 || min > adjacencyMatrix[start][i])
                    {
                        min = adjacencyMatrix[start][i];
                        index = i;
                    }
                }
            }

            if(index == 0)
            {
                System.out.println("//the input map matrix is wrong!");

            }
            shortest[index] = min;
            visited[index] = true;

            for (int i = 0; i < n; i++)
            {
                if (!visited[i] && adjacencyMatrix[index][i] != 0)
                {
                    int distance = min + adjacencyMatrix[index][i];
                    if (adjacencyMatrix[start][i] == 0 || adjacencyMatrix[start][i] > distance)
                    {
                        adjacencyMatrix[start][i] = distance;
                        //here i dont know how to insert data in the specified position of the array and all the subsequent datas should postpone one position
                        //that's why i use String
                        path[i] = path[index] + "--->" + i;
                    }
                }
            }
        }

        //can use next line code to test this function
        //System.out.println("From point " + start + " to point "+end+" is : " +path[end] + " The shortest distance is "+shortest[end]);

        String[] str = path[end].split("--->");
        int[] paths = new int[str.length];
        for (int i=0;i<paths.length;i++) {
             paths[i] = Integer.valueOf(str[i]);
        }


        //return shortest[end];
        return  paths;
    }


    //Just to test the set methods
    public static void main(String[] args) {

        City city = new City (100,100);



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
        /*
        System.out.println(compGreenligth);
        System.out.println(compRedligth);
        System.out.println(compBus);
        System.out.println(compTaxi);
        System.out.println(compAccident);
        System.out.println(compEmptyNode);
        */

        System.out.println("HELLO WORLD !!");



        int[][] testAdjacencyMatrix = {
                { 0, 1, 1, 1, 0 },
                { 1, 0, 0, 1, 0 },
                { 1, 0, 0, 1, 1 },
                { 1, 1, 1, 0, 0 },
                { 0, 0, 1, 0, 0 } };
        int[] shortPath = dijkstra(testAdjacencyMatrix,0,4);
        for (int i:shortPath
             ) {
            System.out.print(i+"\t");
        }



    }
}
