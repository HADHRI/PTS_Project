package Model;

import java.util.LinkedList;

//This is one part of the model
public class Node {

    public enum Traffic_ligth{RED,GREEN};

    //----------------------------------Attributes and Properties-------------------------------------------

    private boolean occupied;
    private boolean taxi;
    private boolean bus;
    private boolean accident;
    private int speed_limit;
    private int row;
    private int column;
    private LinkedList<Node> neighbors;
    private Traffic_ligth my_traffic_ligth;



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



    public LinkedList<Node> getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(LinkedList<Node> neighbors) {
        this.neighbors = neighbors;
    }


    public Traffic_ligth getMy_traffic_ligth() {
        return my_traffic_ligth;
    }
    public void setMy_traffic_ligth(Traffic_ligth my_traffic_ligth) {
        this.my_traffic_ligth = my_traffic_ligth;
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
    }



    //---------------------------------------------Methods---------------------------------------------------


}
