package Model;
//This is one part of the model

import java.util.ArrayList;
import java.util.Random;

public class City {

    //----------------------------------Attributes and Properties-------------------------------------------

    private int width;
    private int height;
    //private Node[][] matrice;
    private ArrayList<ArrayList<Node>> matrice;

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

    //---------------------------------------------Constructor---------------------------------------------------

    public City(int nbRows, int nbColumns, double proportion){

        this.height=nbRows;
        this.width=nbColumns;

    //    this.matrice=new Node[height][width];
        matrice=new ArrayList<ArrayList<Node>>();
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                matrice.add(i,new ArrayList<>());
            }

        }

        // Init All nodes with Default state
        for(int i=0;i<height;i++)
        {

            for(int j=0;j<width;j++)
            {
                Node node=new Node(i,j);
                matrice.get(i).add(j,node);
            }

        }
        // we will put 20 % of  occupied Nodes means with buildings
        int maxBuildings = (int) ( height*width*proportion);

        int numberBuildings=0;


                   //Random Object
        Random random = new Random();
      while(numberBuildings <maxBuildings) {

            int randomRow = random.nextInt(height); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(width); //  To generate numbrer between 0 and width - 1

         if (! matrice.get(randomRow).get(randomCol).isOccupied()) {

                matrice.get(randomRow).get(randomCol).setOccupied(true);
                numberBuildings+=1;
            }
        }
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

