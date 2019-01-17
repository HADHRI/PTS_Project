package Controller;
//This is the controller

import Model.Node;

import java.util.ArrayList;
import java.util.Scanner;

public class CityController {
    public  Graph graph;


    public CityController(int size, double proportion){
        graph=new Graph(size,proportion);
        graph.setListOfAllNodes();
        graph.setAllNeighbors();
        //graph.refreshAllCosts();

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
    private  void printPath(ArrayList<Integer>path){

        for(int i=0;i<graph.getCity().getHeight();i++)
        {
            for(int j=0;j<graph.getCity().getHeight();j++)
            {

                if(graph.getCity().getMatrice().get(i).get(j).isOccupied()){
                    System.out.print("\033[31m" + "[X]");/**print building in red*/

                }
                else if(find(i,j,path)) {
                    System.out.print("\033[30m" + "[#]");/**print the path in white*/
                }
                else{
                    System.out.print("\033[32m" + "[ ]");/**print roads in yellow*/
                }
            }
            System.out.println();
        }

        System.out.println("\033[30m" + " ");

    }

    /**Main to test the methods*/

    public static void main(String[] args) throws InterruptedException {

        System.out.println("-------------------------------------------CITY MATRIX-------------------------------------------");
        System.out.println(" ");
        System.out.println(" ");

        Scanner scan = new Scanner(System.in);

        System.out.println(" Enter the size the city matrix n x n (max 200) : ");
        int matrix_size = scan.nextInt();

        System.out.println(" ");


        CityController cityController=new CityController(matrix_size,0.2);

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
        astar.run(source,destination);

        System.out.println(" ");

    }
}