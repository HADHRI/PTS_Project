package Controller;

import Model.Node;


import java.util.*;

public class Astar {
    private Graph graph;
    private Set searched;
    private Queue unsearched;

    public void printAstarPath(Node destination,Node start){

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

        printInformationAboutPath(aStarFinalPath,graph.getListOfAllNodes().indexOf(start));

        System.out.println("Distance of this Path  "+destination.getDistance());







    }



    public Astar(Graph graph) {
        this.graph = graph;
    }

    /*
	 * Finds and prints shortest path from start to end using A* search
	 */
    public void run(Node start, Node end) {
        /** Initialize empty set and empty PriorityQueue**/
        searched = new HashSet();
        unsearched = new PriorityQueue();

        /**Set the current node to @param start**/
        Node current = start;
        //Set start node's heuristic values (g(x) and h(x))
        /** set the distance of the start node to zero and set the heuristic ( euclidien distance ) **/
        start.setDistance(0.0);
        start.setHeuristic(end);

        /** Add @param start to the queue**/
        unsearched.add(start);

        while (!unsearched.isEmpty()) {
            //Pop the PriorityQueue and set current to the top element;
            current = (Node) unsearched.poll();
            //If the current node is our target, print the path and end
           // System.out.println(current);
            if (current.equals(end)) {
                /** print path **/
                printAstarPath(end,start);

                return;
            }
            //Move current node to the searched list.
            searched.add(current);
            updateNeighbor(current, end);
        }
        //We did not find the shortest path.

        System.out.println("Shortest path between " + start + " and " + end + " was not found.");
    }


    /**
     * @param curr        node whose neighbors are to be checked/updated.
     * @param destination node which heuristics will be calculated from (AKA distance from @param destination)
     */
    public void updateNeighbor(Node curr, Node destination) {
        //List neighbors = Graph.getNeighbors(edges, curr);
        /** distance is the current node's distance to start **/
        Double distance = curr.getDistance();

        /** First we  iterate into neighbours of the current Node **/
        int numberOfVertices = graph.getListOfAllNodes().size();
        for (int j = 1; j < numberOfVertices; j++) {
            int indexOfCurrentNode = graph.getListOfAllNodes().indexOf(curr);
            /** check if node is a neighbour of curr Node **/
            if (graph.getAdjacencyMatrix()[indexOfCurrentNode][j] != 0) {
                /** temp is the distance from current node to a neighbor
                 * we get this distance from the Adjacency Matrix
                 * **/
                int temp = graph.getAdjacencyMatrix()[indexOfCurrentNode][j];
                /** If searched already contains neighbor, no need to double check. Continue in loop **/
                Node neighbor = graph.getListOfAllNodes().get(j);
           //     System.out.println("Neighbour is "+neighbor);
                if (!searched.contains(neighbor)) {

                    if (distance + temp < neighbor.getDistance()) {
                        /** Shorter path has been found. Update neighboring node **/
                    //    System.out.println("update path ");
                        neighbor.setPrevious(curr);  /** We set the previous Node ! **/
                        neighbor.setDistance(distance + temp);
                        neighbor.setHeuristic(destination);
                        //Allow neighbor to be searched through by adding it to the unsearched queue.
                        unsearched.add(neighbor);
                    }
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
}














