package Model;
//This is one part of the model

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class City {

    //----------------------------------Attributes and Properties-------------------------------------------

    private ArrayList<TrafficLight>trafficLights=new ArrayList<>();
    private static int width;
    private static int height;
    //private Node[][] matrice;
    private static ArrayList<ArrayList<Node>> matrice;

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }



    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }



    public ArrayList<ArrayList<Node>> getMatrice() {
        return matrice;
    }
    public void setMatrice(ArrayList<ArrayList<Node>> matrice) {
        this.matrice = matrice;
    }

    public static void setAccident( int maxAccident)
    {
        //Random Object
        Random random = new Random();

        int numAccident=0;


        while(numAccident<maxAccident) {

            int randomRow = random.nextInt(height); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(width); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the node does not already contain a accident
            if (!matrice.get(randomRow).get(randomCol).isOccupied() && !matrice.get(randomRow).get(randomCol).isAccident() && !matrice.get(randomRow).get(randomCol).isBus() && !matrice.get(randomRow).get(randomCol).isTaxi() && !matrice.get(randomRow).get(randomCol).isStop()
                    && !matrice.get(randomRow).get(randomCol).isTree() && !matrice.get(randomRow).get(randomCol).isWater() && !matrice.get(randomRow).get(randomCol).isGrass()) {
                matrice.get(randomRow).get(randomCol).setAccident(true);
                numAccident+=1;
            }
        }
    }


    public ArrayList<TrafficLight> getTrafficLights() {
        return trafficLights;
    }

    //---------------------------------------------Constructor---------------------------------------------------

    public City(String file)
    {
        this.width=50;
        this.height=50;

        //int [] SpeedLimitArray = setSpeedLimitsArray("speedLimits.txt");
        char[][] matrix = new char[width][height];
        int i=0;

        matrice=new ArrayList<ArrayList<Node>>();
        for(int x=0;x<height;x++)
        {
            for(int y=0;y<width;y++)
            {
                matrice.add(x,new ArrayList<>());
            }

        }


        try
        {
            BufferedReader in = new BufferedReader(new FileReader("src/"+file));	//reading files in specified directory
            String line;
            int nbStop=0;

            Random random = new Random();
            int randomStop = 0;


            while ((line = in.readLine()) != null)	//file reading
            {
                matrix[i] = line.toCharArray();
                for(int j=0;j<matrix[i].length;j++){

                    Node node=new Node(i,j);
                    matrice.get(i).add(j,node);
                    //matrice.get(i).get(j).setSpeed_limit(SpeedLimitArray[j]);

                    if(matrix[i][j]=='0'){
                        matrice.get(i).get(j).setOccupied(true);
                    }
                    else if(matrix[i][j]=='1'){

                    }
                    else if(matrix[i][j]=='2'){
                        matrice.get(i).get(j).setIntersection(true);
                        randomStop=random.nextInt(50);
                        if(nbStop<100 && randomStop>=35) {
                            matrice.get(i).get(j).setStop(true);
                            nbStop++;
                        }
                    }
                    else if(matrix[i][j]=='7'){
                        matrice.get(i).get(j).setGrass(true);
                        matrice.get(i).get(j).setOccupied(true);
                    }
                    else if(matrix[i][j]=='6'){
                        matrice.get(i).get(j).setWater(true);
                        matrice.get(i).get(j).setOccupied(true);
                    }
                    else if(matrix[i][j]=='8'){
                        matrice.get(i).get(j).setTree(true);
                        matrice.get(i).get(j).setOccupied(true);
                    }
                    else if(matrix[i][j]=='C' || matrix[i][j]=='E' || matrix[i][j]=='N' || matrix[i][j]=='T' || matrix[i][j]=='R' || matrix[i][j]=='A'
                            || matrix[i][j]=='L' || matrix[i][j]=='P' || matrix[i][j]=='A' || matrix[i][j]=='R' || matrix[i][j]=='K'){
                        matrice.get(i).get(j).setIntersection(true);
                        matrice.get(i).get(j).setTree(true);
                        matrice.get(i).get(j).setOccupied(true);
                    }
                    else if(matrix[i][j]=='X'){
                        matrice.get(i).get(j).setBus(true);
                    }
                    else if(matrix[i][j]=='Z'){
                        matrice.get(i).get(j).setTaxi(true);
                    }
                    else{
                        matrice.get(i).get(j).setIntersection(true);

                        TrafficLight trafficLight = new TrafficLight(matrice.get(i).get(j));

                        trafficLights.add(trafficLight); // Add to the array the traffic light

                        Random rand = new Random();

                        int n = rand.nextInt(50);
                        /**  Set a random TimeMax for the light**/
                        trafficLight.setTimeMax(trafficLight.getTimeMax()+n);
                        if( n  < 25 ){

                            trafficLight.setState(true);
                        }
                        else {
                            trafficLight.setState(false);
                        }
                        matrice.get(i).get(j).setTrafficLight(trafficLight);


                    }

                }
                i++;

            }

            setAccident(5);

            in.close();
        }catch( IOException ioException ) {}
    }


    //---------------------------------------------Methods---------------------------------------------------

    public  void printCityMatrix(){
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<height;j++)
            {
                if(matrice.get(i).get(j).isOccupied()){
                    System.out.print("\033[31m" + "[X]");

                }
                else{
                    System.out.print("\033[32m" + "[ ]");
                }
            }
            System.out.println();
        }

        System.out.println("\033[30m" + " ");

    }


}
