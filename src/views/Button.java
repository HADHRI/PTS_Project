package views;

import javax.swing.*;

public class Button extends JButton {

    private boolean astarIsOn =false;
    private boolean addingNormalCar;
    private boolean addinganAccident;
    private boolean deletinganAccident;
    private boolean showingPath;
    private int numberOfCars=0;

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    private int x;
    private int y;

    public Button(){

    }


    public Button(String name)
    {
        super(name);
    }

    public boolean isAddingNormalCar() {
        return addingNormalCar;
    }

    public void setAddingNormalCar(boolean addingNormalCar) {
        this.addingNormalCar = addingNormalCar;
    }

    public boolean isShowingPath() {
        return showingPath;
    }

    public void setShowingPath(boolean showingPath) {
        this.showingPath = showingPath;
    }

    public boolean isAddinganAccident() {
        return addinganAccident;
    }

    public void setAddinganAccident(boolean addinganAccident) {
        this.addinganAccident = addinganAccident;
    }

    public boolean isDeletinganAccident() {
        return deletinganAccident;
    }

    public void setDeletinganAccident(boolean deletinginganAccident) { this.deletinganAccident = deletinginganAccident; }

    public int getXPositionInMatrix(){
        return x;
    }
    public int getYPositionInMatrix(){
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }



    public boolean isAstarIsOn() {
        return astarIsOn;
    }

    public void setAstarIsOn(boolean astarIsOn) {
        this.astarIsOn = astarIsOn;
    }
}
