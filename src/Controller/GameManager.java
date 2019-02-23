package Controller;

import Model.Car;
import Model.TrafficLight;
import views.GameView;


import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class GameManager implements Runnable {

    private ArrayList<Car>cars=new ArrayList<>();
    private GameView gameView;
    private boolean running = true;
    private Thread thread;
    private CityController cityController;
    /** TODO PUT THIS IN GAME VIEW AND HAVE LIST OF CARS FROM THE GAMEVIEW**/
//    private Car car;  // convert it to an array oF Cars

  /*  public Car getCar() {
        return car;
    }*/

    public CityController getCityController() {
        return cityController;
    }

    public GameManager() {



        this.cityController=new CityController("Manathan.txt");

          /** init Cars **/

   //    car=new Car(0,0,cityController.getAstar());
       /** **/
        gameView=new GameView("Traffic Simulator",cityController,cars);

    }

    public void refreshCarsPosition(){

        /** iterate on all cars */

        for(int i=0;i<cars.size();i++)
        {
            Car car=cars.get(i);


        if( car.getMaxTimer() <= car.getTimer())
        {
            /**TODO
             * change the position of the car with the fhaving the futur position from the A* ALGORITHM
             * and here we have to put the lastposition for this variables "lastx" and "lasty" before changing
             */

            /** Generate a random destination for the cars*/

            car.followAstarPath(car.getX(),car.getY(),car.getX(),car.getY());
            car.setTimer(0); // SET THE timer of the car to 0
        }
        int timer=car.getTimer()+1;
        car.setTimer(timer);


        }
    }



    public void init() {
        gameView.getJFrame().setVisible(true);
        gameView.setCityController(cityController);
        /** we have to do a methode that generate randomly the start position
         * and we have to control that it could be a start position for the car
         * for the moment , the start position is 0 , 0 **/


        /** here we all the Astar methode for all the cars
         * we will recall this method only if there s an accident ( dynamic behavior)
         */
       // car.getAstar().run(car.getX(),car.getY());

    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

        //Preparing the game Loop
        init();
        int fps = 20;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime(); // le temps courant

        // running=false;

        while (running) {

            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick; //sert à dire quand tu appelles les 2 methode pour update et dessiner
            lastTime = now;
            if (delta >= 1) {


                refreshTrafficLights();
                refreshCarsPosition();
                //For all the lights that change every time
                cityController.getGraph().refreshAllCosts();
                gameView.repaint(cars);



                delta--;
                //TODO : mélanger le tableau de gameObject

            }
            try {
                sleep(8, 3333);


            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

        }
    }

    private void refreshTrafficLights() {

        //LOOP TO ALL THE TRAFFIC LIGHTS
                for(int i=0;i< cityController.getGraph().getCity().getTrafficLights().size();i++)
                {
                    TrafficLight trafficLight= cityController.getGraph().getCity().getTrafficLights().get(i);
                    if (trafficLight.getTimer()>=trafficLight.getTimeMax())
                    {
                       trafficLight.setState(!trafficLight.isState()); // Switch Green to Red and Red to Green
                        trafficLight.setTimer(0);
                    }
                    else{
                        // Increment the timer
                        trafficLight.setTimer(trafficLight.getTimer()+1);

                    }


                }
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []args)
    {
        GameManager gameManager=new GameManager();

        gameManager.run();

    }










}
