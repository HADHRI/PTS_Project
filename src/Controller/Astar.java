package Controller;

import Model.Node;


import java.util.*;

public class Astar {
    private Graph graph;
    private Set searched;
    private Queue unsearched;


    public Graph getGraph() {
        return graph;
    }

    public  ArrayList<Integer> printAstarPath(Node destination, Node start){

        ArrayList<Integer>aStarFinalPath=new ArrayList<>(); /** Array list to hold the final Path **/
        Node current = destination;
        Stack path = new Stack();
        path.push(destination);


        /** Enqueue all path nodes to a stack (so we can easily print in reverse order) **/
        while(current.getPrevious() != null) {
            current = current.getPrevious();
            path.push(current);
        }

        //Print out the path in the correct order
        int i = 0;
        do {
            //System.out.println(graph.getListOfAllNodes().indexOf(path.peek()));

            aStarFinalPath.add(graph.getListOfAllNodes().indexOf(path.pop()));

        }
        while(!path.isEmpty());

        System.out.println(" ");
        System.out.println(" ");
        printPath(aStarFinalPath);
        System.out.println(" ");
        System.out.println(" ");


        printInformationAboutPath(aStarFinalPath,graph.getListOfAllNodes().indexOf(start));


        System.out.println("Distance of this Path  "+destination.getDistance());

        return aStarFinalPath;



    }



    public Astar(Graph graph) {
        this.graph = graph;
    }

    /*
	 * Finds and prints shortest path from start to end using A* search
	 */
    public ArrayList<Integer> run(int  start, int end) {
        for (int i=0; i<graph.getListOfAllNodes().size();i++)
        {
            graph.getListOfAllNodes().get(i).refreshAstarAttributes();
        }
        /**
         * For All nodes on the graph , we refresh AstarAlgorithm
         */




        /** Initialize empty set and empty PriorityQueue**/
        searched = new HashSet();
        unsearched = new PriorityQueue();

        /**Set the current node to @param start**/
        Node current =  graph.getListOfAllNodes().get(start);
        //Set start node's heuristic values (g(x) and h(x))
        /** set the distance of the start node to zero and set the heuristic ( euclidien distance ) **/
        graph.getListOfAllNodes().get(start).setDistance(0.0);
        graph.getListOfAllNodes().get(start).setHeuristic(graph.getListOfAllNodes().get(end));

        /** Add @param start to the queue**/
        unsearched.add(graph.getListOfAllNodes().get(start));

        while (!unsearched.isEmpty()) {
            //Pop the PriorityQueue and set current to the top element;
            current = (Node) unsearched.poll();
            //If the current node is our target, print the path and end
            if (current.equals(graph.getListOfAllNodes().get(end))) {
                /** print path **/
                return (printAstarPath(graph.getListOfAllNodes().get(end),graph.getListOfAllNodes().get(start)));


            }
            //Move current node to the searched list.
            searched.add(current);
            updateNeighbor(graph.getListOfAllNodes().indexOf(current), end);
        }


        System.out.println("Shortest path between " + start + " and " + end + " was not found.");
        return null;
    }


    /**
     * @param curr         index if node whose neighbors are to be checked/updated.
     * @param  destination  index of node which heuristics will be calculated from (AKA distance from @param destination)
     */

    public void updateNeighbor(int curr, int destination) {

        /** distance is the current node's distance to start **/
        Double distance = graph.getListOfAllNodes().get(curr).getDistance();

        Node currentNode=graph.getListOfAllNodes().get(curr);

        for(int i=0; i<currentNode.getNeighbors().size(); i++)
        {

            int tempIndex = currentNode.getNeighbors().get(i).get(0); /** Index of the neighbor i int ListOfAllNodes*/
            int tempCost = currentNode.getNeighbors().get(i).get(1);  /** Cost to go to the neighbor i*/

            Node neighbor = graph.getListOfAllNodes().get(tempIndex);

            /** If searched already contains neighbor, no need to double check. Continue in loop **/
            if (!searched.contains(neighbor))
            {
                if (distance + tempCost < graph.getListOfAllNodes().get(tempIndex).getDistance())
                {
                    /** Shorter path has been found. Update neighboring node **/
                    neighbor.setPrevious(graph.getListOfAllNodes().get(curr));  /** We set the previous Node ! **/
                    neighbor.setDistance(distance + tempCost);
                    neighbor.setHeuristic(graph.getListOfAllNodes().get(destination));

                    /**Allow neighbor to be searched through by adding it to the unsearched queue. **/
                    unsearched.add(neighbor);
                }
            }

        }
    }

    private void printInformationAboutPath(ArrayList<Integer>path,int sourceIndex)
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
        int n=0;

        for(int i=0;i<graph.getCity().getHeight();i++)
        {
            for(int j=0;j<graph.getCity().getHeight();j++)
            {

                if(graph.getCity().getMatrice().get(i).get(j).isOccupied()){
                    System.out.print("\033[31m" + "[X]");/**print building in red*/

                }
                else if(find(i,j,path)) {
                    System.out.print("\033[30m" + "[#]");/**print the path in white*/
                    n++;
                }
                else{
                    System.out.print("\033[32m" + "[ ]");/**print roads in yellow*/
                }
            }
            System.out.println();
        }

        System.out.println("\033[30m" + " ");

    }
}














