package Model;



public class TrafficLight {

    private boolean state; //green-> true  red-> false
    private int timeMax=200;
    private int timer=0;
    private Node node;

    public TrafficLight(Node node){
        this.node=node;

    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Node getNode() {
        return node;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setTimeMax(int timeMax) {
        this.timeMax = timeMax;
    }

    public int getTimer() {
        return timer;
    }

    public int getTimeMax() {
        return timeMax;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
