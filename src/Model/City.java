package Model;
//This is one part of the model

import java.util.Random;

public class City {

    //----------------------------------Attributes and Properties-------------------------------------------

    private int width;
    private int height;
    private Node[][] matrice;

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



    public Node[][] getMatrice() {
        return matrice;
    }
    public void setMatrice(Node[][] matrice) {
        this.matrice = matrice;
    }

    //---------------------------------------------Constructor---------------------------------------------------

    public City(int nbRows, int nbColumns){

        this.height=nbRows;
        this.width=nbColumns;

        this.matrice=new Node[height][width];

        // Init All nodes with Default state
        for(int i=0;i<height;i++)
        {

            for(int j=0;j<width;j++)
            {
                Node node=new Node(i,j);
                matrice[i][j]=node;
              System.out.println(node.isOccupied());

            }

        }
        // we will put 20 % of  occupied Nodes means with buildings
        int maxBuildings = (int) ( height*width*0.2);

        int numberBuildings=0;


                   //Random Object
        Random random = new Random();
      while(numberBuildings <maxBuildings) {

            int randomRow = random.nextInt(height); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(width); //  To generate numbrer between 0 and width - 1

         if (!matrice[randomRow][randomCol].isOccupied()) {
                matrice[randomRow][randomCol].setOccupied(true);
                numberBuildings+=1;
            }
        }
    }


    //---------------------------------------------Methods---------------------------------------------------

    //JUST TO TEST I HAVE ADDED A MAIN HERE
    public static void main(String []args)
    {
        // TEST OF CITY WITH 100 * 100 , 20% BUILDINGS SO COMP (variable ) is 2000
        City city=new City(100,100);
        int comp=0;

        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                if(city.matrice[i][j].isOccupied())
                {
                    comp+=1;
                }
                System.out.println(city.matrice[i][j].isOccupied());

            }
            System.out.println(comp);
        }


    }



}

