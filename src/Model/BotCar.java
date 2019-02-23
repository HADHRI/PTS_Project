package Model;

import Controller.Astar;
import views.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BotCar extends Car {

    public BotCar(int startXposition, int startYPosition, Astar astar, ArrayList<ArrayList<Button>> buttons) {
        super(startXposition, startYPosition, astar, buttons);
    }

    public void runBotCar(int startXposition, int startYPosition) {

        Random random = new Random();
        int randNumber;


    }

    @Override
    public void followAstarPath(int startX, int startY, int destX, int destY) {
        
        boolean botCarUpPosition = !(astar.getGraph().getCity().getMatrice().get(startX - 1).get(startY).isOccupied() || astar.getGraph().getCity().getMatrice().get(startX - 1).get(startY).isTree() || astar.getGraph().getCity().getMatrice().get(startX - 1).get(startY).isGrass() || astar.getGraph().getCity().getMatrice().get(startX - 1).get(startY).isWater());
        boolean botCarDownPosition = !(astar.getGraph().getCity().getMatrice().get(startX + 1).get(startY).isOccupied() || astar.getGraph().getCity().getMatrice().get(startX + 1).get(startY).isTree() || astar.getGraph().getCity().getMatrice().get(startX + 1).get(startY).isGrass() || astar.getGraph().getCity().getMatrice().get(startX + 1).get(startY).isWater());
        boolean botCarLeftPosition = !(astar.getGraph().getCity().getMatrice().get(startX).get(startY - 1).isOccupied() || astar.getGraph().getCity().getMatrice().get(startX).get(startY - 1).isTree() || astar.getGraph().getCity().getMatrice().get(startX).get(startY - 1).isGrass() || astar.getGraph().getCity().getMatrice().get(startX).get(startY - 1).isWater());
        boolean botCarRightPosition = !(astar.getGraph().getCity().getMatrice().get(startX).get(startY + 1).isOccupied() || astar.getGraph().getCity().getMatrice().get(startX).get(startY + 1).isTree() || astar.getGraph().getCity().getMatrice().get(startX).get(startY + 1).isGrass() || astar.getGraph().getCity().getMatrice().get(startX).get(startY + 1).isWater());

        // 0 Up 1 Down 2 Left 3 Right

        List<Integer> listTruePosition = new ArrayList<Integer>();
        if (botCarDownPosition) {
            listTruePosition.add(1);
        }
        if (botCarUpPosition) {
            listTruePosition.add(0);
        }
        if (botCarLeftPosition) {
            listTruePosition.add(2);
        }
        if (botCarRightPosition) {
            listTruePosition.add(3);
        }

        int nbPosition = listTruePosition.size();
        Random random = new Random();
        int randNumber = random.nextInt(nbPosition - 1);
        switch (listTruePosition.get(randNumber)) {
            case 0: // case Up

                y = y - 1;
                break;
            case 1: // case Down
                y = y + 1;
                break;
            case 2: // case Left
                x = x -1;
                break;
            case 3: // case Right
                x = x +1;
        }

    }


}
