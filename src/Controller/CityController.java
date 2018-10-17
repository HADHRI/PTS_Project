package Controller;
//This is the controller

import Model.City;
import Model.Node;

import java.util.Random;


public class CityController {


    public static void setTrafficligth(City city, double randomRedProportion, double randomGreenProportion)
    {

        //Random Object
        Random random = new Random();


        int maxTrafficligth = (int) ( city.getHeight()*city.getWidth()*randomRedProportion);
        int numTrafficligth=0;

        while(numTrafficligth <maxTrafficligth) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the traffic ligth is not red
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && city.getMatrice()[randomRow][randomCol].getMy_traffic_ligth()!= Node.Traffic_ligth.GREEN) {
                city.getMatrice()[randomRow][randomCol].setMy_traffic_ligth(Node.Traffic_ligth.RED);
                numTrafficligth+=1;
            }
        }


        maxTrafficligth = (int) ( city.getHeight()*city.getWidth()*randomGreenProportion);
        numTrafficligth=0;

        while(numTrafficligth <maxTrafficligth) {

            int randomRow = random.nextInt(city.getHeight()); // To generate numbrer between 0 and height - 1
            int randomCol = random.nextInt(city.getWidth()); //  To generate numbrer between 0 and width - 1

            //if the node is not occupied and the traffic ligth is not Green
            if (!city.getMatrice()[randomRow][randomCol].isOccupied() && city.getMatrice()[randomRow][randomCol].getMy_traffic_ligth()!= Node.Traffic_ligth.RED) {
                city.getMatrice()[randomRow][randomCol].setMy_traffic_ligth(Node.Traffic_ligth.GREEN);
                numTrafficligth+=1;
            }
        }

    }

    //Just to test the method setTrafficligth
    public static void main(String[] args) {

        City city = new City (100,100);

        setTrafficligth(city, 0.1,0.2);

        int comp1=0;
        int comp2=0;

        for(int i=0;i<city.getHeight();i++)
        {
            for(int j=0;j<city.getWidth();j++)
            {
                if(city.getMatrice()[i][j].getMy_traffic_ligth()== Node.Traffic_ligth.GREEN)//or with RED
                {
                    comp1+=1;
                }

                if(city.getMatrice()[i][j].getMy_traffic_ligth()== Node.Traffic_ligth.RED)//or with RED
                {
                    comp2+=1;
                }
            }

        }
        System.out.println(comp1);
        System.out.println(comp2);

        System.out.println("HELLO WORLD !!");


    }
}
