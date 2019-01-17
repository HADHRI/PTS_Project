package Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//This is one part of the model
public class Node implements Comparable<Node> {


    @Override
    public int compareTo(Node node) {
        return Double.compare(heuristicCost + distance, node.heuristicCost + node.distance);
    }

    public enum Traffic_ligth{RED,GREEN};

    //----------------------------------Attributes and Properties-------------------------------------------

    /** this methode is very important , because if we run Astar algorithm 2 times it will use the values
     * of the first time , that's why we have to refresh all this attributes
     */
    public void refreshAstarAttributes(){
        previous=null;
        heuristicCost=null;
        distance=Double.MAX_VALUE;

    }



    /** This attributes are for A* Algorithm**/
    private Node previous=null; /**  Previous Node to get to the cuurrent **/
    private Double heuristicCost ; /** H(x) for A* star algotithm **/
    private  Double distance=Double.MAX_VALUE ; /** represents the distance from the start to the current node

     /** F(x) = g(x) + h(x) where g(x) is the path from the source to the current node
     **/

    private ArrayList<ArrayList<Integer>> neighbors;
    private boolean occupied;
    private boolean taxi;
    private boolean bus;
    private boolean accident;
    private int speed_limit;
    private int row;
    private int column;
    private Traffic_ligth my_traffic_ligth;


    public Double getFinalCost() {
        return distance;
    }

    public Node getPrevious() {
        return previous;
    }

    public Double getDistance() {
        return distance;
    }

    public Double getHeuristicCost(){
        return heuristicCost;
    }

    public void setPrevious(Node previous)
    {
        this.previous=previous;
    }

    public void setHeuristic(Node destination) {
        this.heuristicCost  = Node.distanceFrom(this, destination);
    }
    public void setDistance(double distance)
    {
        this.distance=distance;
    }


    public boolean isBus() {
        return bus;
    }
    public void setBus(boolean bus) {
        this.bus = bus;
    }


    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }



    public boolean isTaxi() {
        return taxi;
    }
    public void setTaxi(boolean taxi) {
        this.taxi = taxi;
    }


    public boolean isAccident() {
        return accident;
    }
    public void setAccident(boolean accident) {
        this.accident = accident;
    }



    public int getSpeed_limit() {
        return speed_limit;
    }
    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }



    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }



    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }


    public Traffic_ligth getMy_traffic_ligth() {
        return my_traffic_ligth;
    }
    public void setMy_traffic_ligth(Traffic_ligth my_traffic_ligth) {
        this.my_traffic_ligth = my_traffic_ligth;
    }


    public ArrayList<ArrayList<Integer>> getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(ArrayList<ArrayList<Integer>> neighbors) {
        this.neighbors = neighbors;
    }



    //---------------------------------------------Constructor---------------------------------------------------


    //This is the constructor for buildings nodes)
    public Node(int i, int j){
        this.occupied=false;
        this.bus=false;
        this.taxi=false;
        this.accident=false;
        this.speed_limit=-1;
        this.row=i;
        this.column=j;
        this.neighbors=null;
        this.my_traffic_ligth=null;
        this.neighbors=new ArrayList<ArrayList<Integer>>();
    }



    //---------------------------------------------Methods---------------------------------------------------

    /** Euclidien Distance **/
    public static Double distanceFrom(Node n1, Node n2) {
        return Math.sqrt((n1.getRow()-n2.getRow())*(n1.getRow()-n2.getRow()) + (n1.getColumn()-n2.getColumn())*(n1.getColumn()-n2.getColumn()));
    }

    public void addNeighbors (int index, int cost)
    {
        ArrayList<Integer> holdCostAndindex=new ArrayList<>();
        holdCostAndindex.add(index);
        holdCostAndindex.add(cost);
        neighbors.add(holdCostAndindex);
    }

    public void deleteNeighbors(int index)
    {
        for(int i=0; i<neighbors.size(); i++)
        {
            if(neighbors.get(i).get(0)==index){
                neighbors.remove(i);
                break;
            }
        }
    }

}
