package com.company;
import java.util.LinkedList;

//This is one part of the model
public class Node {

    enum Traffic_ligth{ROUGE,VERT};

    //----------------------------------Attributes and Properties-------------------------------------------

    private boolean occupied;

    public boolean isBus() {
        return bus;
    }
    public void setBus(boolean bus) {
        this.bus = bus;
    }

    private boolean bus;

    public boolean isOccupied() {
        return occupied;
    }
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    private boolean taxi;

    public boolean isTaxi() {
        return taxi;
    }
    public void setTaxi(boolean taxi) {
        this.taxi = taxi;
    }

    private boolean accident;

    public boolean isAccident() {
        return accident;
    }
    public void setAccident(boolean accident) {
        this.accident = accident;
    }

    private int speed_limit;

    public int getSpeed_limit() {
        return speed_limit;
    }
    public void setSpeed_limit(int speed_limit) {
        this.speed_limit = speed_limit;
    }

    private int row;

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    private int column;

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }

    private LinkedList<Node>neighbors;

    public LinkedList<Node> getNeighbors() {
        return neighbors;
    }
    public void setNeighbors(LinkedList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    private Traffic_ligth my_traffic_ligth;

    public Traffic_ligth getMy_traffic_ligth() {
        return my_traffic_ligth;
    }
    public void setMy_traffic_ligth(Traffic_ligth my_traffic_ligth) {
        this.my_traffic_ligth = my_traffic_ligth;
    }


    //---------------------------------------------Constructor---------------------------------------------------


    //This is the constructor for buildings nodes)
    public Node(int i, int j){
        this.occupied=true;
        this.bus=false;
        this.taxi=false;
        this.accident=false;
        this.speed_limit=-1;
        this.row=i;
        this.column=j;
        this.neighbors=null;
        this.my_traffic_ligth=null;
    }

    //This is the constructor for nodes
    public Node(boolean isbus, boolean istaxi,boolean isaccidents,int speed,int i, int j, Traffic_ligth value){
        this.occupied=false;
        this.bus=isbus;
        this.taxi=istaxi;
        this.accident=isaccidents;
        this.speed_limit=speed;
        this.row=i;
        this.column=j;
        this.neighbors=null;
        this.my_traffic_ligth=value;
    }

    //---------------------------------------------Methods---------------------------------------------------


}
