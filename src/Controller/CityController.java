package Controller;
//This is the controller

import Model.Car;
import Model.Node;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CityController {

    private static Graph graph;
    private Astar astar;
    private Car car;


    public  CityController(String file){
        graph=new Graph(file);
        graph.setListOfAllNodes();
        graph.setAllNeighbors();
        graph.refreshAllCosts();
        astar=new Astar(graph);

    }

    public Astar getAstar() {
        return astar;
    }

    public Graph getGraph() {
        return graph;
    }

    // We have then to adapt City matrix to array list




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


    private  int estimateDistanceHeuristic(Node n1, Node n2)
    {
        int x=Math.abs(n1.getRow() - n2.getRow());
        int y= Math.abs(n1.getColumn() - n2.getColumn());
        return x*x + y*y;
    }

    /***
     * function to know if a node (i,j) is in the path
     * @param i
     * @param j
     * @param path
     * @return true if the node (i,j) is present in the path
     */
    private boolean find(int i,int j, ArrayList<Integer>path){
        boolean test=false;

        for(int r=0;r<path.size();r++){
            if(graph.getListOfAllNodes().get(path.get(r)).getRow()==i && graph.getListOfAllNodes().get(path.get(r)).getColumn()==j){
                test=true;
            }
        }
        return test;
    }

    /***
     * function to print the city matrix with the path
     * @param path
     */
    public String printPath(ArrayList<Integer>path){
        String result = "";

        if (path == null) {
            for (int i = 0; i < graph.getCity().getHeight(); i++) {
                for (int j = 0; j < graph.getCity().getWidth(); j++) {

                    if (graph.getCity().getMatrice().get(i).get(j).isOccupied()) {
                        result += "[X]";

                    } else {
                        result += "[_]";
                    }
                }
                result += "\n";
            }
        }
        else {
            for (int i = 0; i < graph.getCity().getHeight(); i++) {
                for (int j = 0; j < graph.getCity().getWidth(); j++) {

                    if (graph.getCity().getMatrice().get(i).get(j).isOccupied()) {
                        result += "[X]";

                    } else if (find(i, j, path)) {
                        result += "[#]";
                    } else {
                        result += "[_]";
                    }
                }
                result += "\n";
            }
        }
        return result;

    }


    /**Main to test the methods*/

    public static void main(String[] args) throws InterruptedException {

        System.out.println("-------------------------------------------CITY MATRIX-------------------------------------------");
        System.out.println(" ");
        System.out.println(" ");

        Scanner scan = new Scanner(System.in);

        CityController cityController=new CityController("Manathan.txt");

        System.out.println(" ");
        cityController.graph.getCity().printCityMatrix();
        System.out.println(" ");
        System.out.println(" ");



        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println(" ");

        System.out.println(" Enter the index of your source : ");
        int source = scan.nextInt();

        System.out.println(" ");

        System.out.println(" Enter the index of your destination : ");
        int destination = scan.nextInt();

        System.out.println(" ");

        System.out.println("-------------------------------------------TESTING A STAR ALGORITHM-------------------------------------------");
        System.out.println(" ");

        Astar astar=new Astar(cityController.graph);
        ArrayList<Integer> arrayList;
        arrayList=astar.run(source,destination);
        for(int i=0;i<arrayList.size();i++)
        {
            System.out.println(arrayList.get(i));
        }

        System.out.println(" ");

    }
}