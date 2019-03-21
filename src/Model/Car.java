package Model;

import Controller.Astar;
import views.Button;

import java.util.ArrayList;

public class Car {

    protected int id=0;
    protected int lastX=-1 ;
    protected int lastY=-1;
    protected int x=0;
    protected int y=0;
    protected int timer=0;
    protected int maxTimer=20;
    protected Astar astar;
    protected boolean astarIsRunning=false;
    protected boolean isSelectedForRunningAstar=false;
    protected boolean SelectedForShowingPath=false;


    protected ArrayList<ArrayList<Button>>buttons;



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isAstarIsRunning() {
        return astarIsRunning;
    }

    public void setAstarIsRunning(boolean astarIsRunning) {
        this.astarIsRunning = astarIsRunning;
    }

    public boolean isSelectedForRunningAstar() {
        return isSelectedForRunningAstar;
    }

    public void setSelectedForRunningAstar(boolean selectedForRunningAstar) {
        isSelectedForRunningAstar = selectedForRunningAstar;
    }

    public boolean isSelectedForShowingPath() {
        return SelectedForShowingPath;
    }

    public void setSelectedForShowingPath(boolean SelectedForShowingPath) {
        this.SelectedForShowingPath = SelectedForShowingPath;
    }

    protected ArrayList<Integer>astarPath; // this array will hold the path while running Astar Path


    public int getCost(){
        int cost = 1;
        if(lastX!=-1 && lastY!=-1)
        {

            int IndexofCar = astar.getGraph().getCity().getMatrice().get(lastX).get(lastY).getIndexofgraph();
            int IndexofNext = astar.getGraph().getCity().getMatrice().get(x).get(y).getIndexofgraph();




            for(int i =0; i<astar.getGraph().getListOfAllNodes().get(IndexofCar).getNeighbors().size(); i++){
                if(astar.getGraph().getListOfAllNodes().get(IndexofCar).getNeighbors().get(i).get(0)==IndexofNext){
                    cost=astar.getGraph().getListOfAllNodes().get(IndexofCar).getNeighbors().get(i).get(1);
                }
            }
        }

        return cost;
    }


    public Car(int startXposition,int startYPosition,Astar astar,ArrayList<ArrayList<Button>>buttons)
    {

        this.x= startXposition;
        this.y=startYPosition;
        this.astar=astar;
        this.buttons=buttons;
        astarPath=new ArrayList<>();
    }


    public void setAstarPath(ArrayList<Integer> astarPath) {
        this.astarPath = astarPath;
    }

    public ArrayList<Integer> getAstarPath() {
        return astarPath;
    }

    // this is methode is to run Astar path for the car , it takes the initial position of the car into consideration
    public void followAstarPath(int startX,int startY, int destX , int destY){



        if (!astarIsRunning)
        {

            // Running Astar and Have a Pile tha contains the A start  positions
            //  astarIsRunning=true;
            /** TODO change the corrdinates of graph to corrdinates in the node */
            int startIndex=astar.getGraph().getCity().getMatrice().get(startX).get(startY).getIndexofgraph();
            int endIndex=astar.getGraph().getCity().getMatrice().get(destX).get(destY).getIndexofgraph();

            astarPath=astar.run(startIndex,endIndex);astarPath.remove(0); // we don't want to have the start position in the array !

            System.out.println("car"+getLastX() +"  "+getLastY());

        }
        /*** if the array is not empty */
        // pop from the array if it is not empty and change the position of the car
        /** change the x and y position and also the lastx and lasty postition**/

        if (astarPath.size()!=0)
        {

            System.out.println(astarPath.get(0));
            /** Change positions of the car */
            Node specificNode=astar.getGraph().getListOfAllNodes().get(astarPath.get(0));
            Button currentButton =buttons.get(x).get(y);
            currentButton.setNumberOfCars(currentButton.getNumberOfCars()-1);
            setLastX(x);
            setLastY(y);
            setX(specificNode.getRow());
            setY(specificNode.getColumn());
            /** Increment number of cars on this button **/
            currentButton.setNumberOfCars(currentButton.getNumberOfCars()+1);
            setMaxTimer(getCost());
            astarPath.remove(0);

        }



    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Astar getAstar() {
        return astar;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public int getMaxTimer() {
        return maxTimer;
    }

    public void setMaxTimer(int maxTimer) {
        this.maxTimer = maxTimer;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}
