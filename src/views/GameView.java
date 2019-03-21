package views;
import Controller.CityController;
import Model.Car;
import Model.TrafficLight;
import javafx.scene.layout.GridPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import static javax.imageio.ImageIO.*;

import java.awt.event.*;

import javax.swing.*;

import javax.swing.border.*;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class GameView extends Display {

    private JPanel carButtons;
    private JPanel carsName; // Contains cars with the boolean selected or not selected
    private JPanel carsPath; // contains the path that the car will take
    private JPanel cote;
    private JPanel carsInfo;
    private JScrollPane js;
    private ArrayList<Car>cars;
    private JTextArea jtextAreaCarnames;
    private JTextArea jtextAreaCarpaths;
    private JScrollPane jspCarnames;
    private JScrollPane jspCarpaths;
    private boolean computing;


    Button addNormalCar = new Button("Add Normal Car");
    Button ShowPath = new Button("Show path");
    Button addanAccident = new Button("Add an Accident");
    Button deleteAnAccident = new Button("Delete an Accident");

    public boolean isComputing(){return  isComputing();}

    public boolean setComputing(boolean computing){return this.computing=computing;}

    private  CityController cityController;

    private ArrayList<ArrayList<Button>> buttons;

    public void setCityController(CityController cityController) {

        this.cityController = cityController;
    }

    public ImageIcon addingImageToAbutton(String path){
        // read an image and add it to a button
        BufferedImage img = null;
        try {
            img = read(new File("images/"+path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = null;
        try {
            dimg = img.getScaledInstance(45, 45,
                    Image.SCALE_SMOOTH);
        }
        catch(Exception e){e.printStackTrace();}

        return new ImageIcon(dimg);

    }

    private void setJPanelandComponent()
    {


        int height=cityController.getGraph().getCity().getHeight();
        int width=cityController.getGraph().getCity().getWidth();

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(height,width));
        int nbletter=1;

        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                Random rand = new Random();
                int rand1 = rand.nextInt(2);
                Button jButton = new Button();
                jButton.setX(y);
                jButton.setY(x);

                jButton.addActionListener(new ActionListener(){

                    public void actionPerformed(ActionEvent e) {
                        for(int i=0;i<cars.size();i++)
                        {


                            // If there is car in the same position as the button
                            if (cars.get(i).getX()==jButton.getXPositionInMatrix() &&cars.get(i).getY()==jButton.getYPositionInMatrix())
                            {
                                cars.get(i).setSelectedForRunningAstar(true);

                            }
                            else {
                                // Parcourir les voitures
                                setComputing(true);

                                if (cars.get(i).isSelectedForRunningAstar() && (!cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isOccupied())) {
                                    System.out.println("Run A star");
                                    /**
                                     * rerun A star
                                     */
                                    cars.get(i).setAstarPath(null);
                                    /** change 0 and 1000 to real position of x and y in the graph **/
                                    /** use index of Graph here **/
                                    int currentPos = cars.get(i).getAstar().getGraph().getCity().getMatrice().get(cars.get(i).getX()).get(cars.get(i).getY()).getIndexofgraph();
                                    System.out.println("this is ......" + currentPos);
                                    int destPos = cars.get(i).getAstar().getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).getIndexofgraph();

                                    cars.get(i).setTimer(0);
                                    /** decrement the number of cars */
                                    if( buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).getNumberOfCars()!=0)
                                        buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setNumberOfCars( buttons.get(cars.get(i).getX()).get(  cars.get(i).getY()).getNumberOfCars()-1);


                                    cars.get(i).setAstarPath(cars.get(i).getAstar().run(currentPos, destPos));
                                    cars.get(i).getAstarPath().remove(0);
                                    cars.get(i).setSelectedForRunningAstar(false);

                                }
                                setComputing(false);
                            } }

                        /** TODO si le button add Normal car est selectionné tu l'ajoutes à cette position **/
                        // FOR add Normal car
                        if(addNormalCar.isAddingNormalCar()&& !cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isOccupied()){
                            System.out.println("Adding Normal Car in this position");
                            Car car = new Car(jButton.getXPositionInMatrix(),jButton.getYPositionInMatrix(),cityController.getAstar(),buttons);
                            car.setId(cars.size()+1);
                            jButton.setNumberOfCars(jButton.getNumberOfCars()+1);

                            cars.add( car);
                            addNormalCar.setAddingNormalCar(false);

                        }

                        // FOR add an accident
                        if(addanAccident.isAddinganAccident()&& !cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isOccupied()
                                && !cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident()){
                            System.out.println("Adding an Accident in this position");
                            cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).setAccident(true);
                            setImageWithAccident(jButton);
                            addanAccident.setAddinganAccident(false);
                        }

                        if(deleteAnAccident.isDeletinganAccident()&& !cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isOccupied()
                                && cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident()){
                            System.out.println("Deleting an Accident in this position");
                            cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).setAccident(false);
                            setImageWithAccident(jButton);
                            deleteAnAccident.setDeletinganAccident(false);
                        }

                        if(ShowPath.isShowingPath()){
                            int numbersofCars=0;

                            for(int n=0;n<cars.size();n++){
                                if(cars.get(n).getX()==jButton.getXPositionInMatrix() && cars.get(n).getY()==jButton.getYPositionInMatrix())
                                {
                                    cars.get(n).setSelectedForShowingPath(true);
                                    cars.get(n).setSelectedForRunningAstar(false);
                                    numbersofCars++;
                                }
                                else{
                                    cars.get(n).setSelectedForShowingPath(false);
                                }
                            }

                            if(numbersofCars==1){
                                showCarsPaths(jButton.getXPositionInMatrix(), jButton.getYPositionInMatrix());
                            }

                            System.out.println("Showing a path");
                            ShowPath.setShowingPath(false);

                        }


                    }




                }  );



                /** **/
                buttons.get(y).add(x,jButton);  // adding buttons to the array
                jButton.setBounds(x,y,2,2);

                jButton.setPreferredSize(new Dimension(50,50));


                if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isOccupied() && !cityController.getGraph().getCity().getMatrice().get(y).get(x).isGrass()
                        && !cityController.getGraph().getCity().getMatrice().get(y).get(x).isTree() && !cityController.getGraph().getCity().getMatrice().get(y).get(x).isWater())
                {
                    jButton.setIcon(addingImageToAbutton("Building4.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isIntersection() && cityController.getGraph().getCity().getMatrice().get(y).get(x).isTree()){
                    if(nbletter==1) {jButton.setIcon(addingImageToAbutton("C.png"));}
                    if(nbletter==2) {jButton.setIcon(addingImageToAbutton("E.png"));}
                    if(nbletter==3) {jButton.setIcon(addingImageToAbutton("N.png"));}
                    if(nbletter==4) {jButton.setIcon(addingImageToAbutton("T.png"));}
                    if(nbletter==5) {jButton.setIcon(addingImageToAbutton("R.png"));}
                    if(nbletter==6) {jButton.setIcon(addingImageToAbutton("A.png"));}
                    if(nbletter==7) {jButton.setIcon(addingImageToAbutton("L.png"));}
                    if(nbletter==8) {jButton.setIcon(addingImageToAbutton("P.png"));}
                    if(nbletter==9) {jButton.setIcon(addingImageToAbutton("A.png"));}
                    if(nbletter==10) {jButton.setIcon(addingImageToAbutton("R.png"));}
                    if(nbletter==11) {jButton.setIcon(addingImageToAbutton("K.png"));}
                    nbletter++;
                    cityController.getGraph().getCity().getMatrice().get(y).get(x).isOccupied();
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).getTrafficLight()!=null){
                    if(cityController.getGraph().getCity().getMatrice().get(y).get(x).getTrafficLight().isState() == true) {
                        jButton.setIcon(addingImageToAbutton("Green_traffic_light.png"));
                    }
                    else if (cityController.getGraph().getCity().getMatrice().get(y).get(x).getTrafficLight().isState() == false){
                        jButton.setIcon(addingImageToAbutton("Red_traffic_light.png"));
                    }
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isBus()){
                    jButton.setIcon(addingImageToAbutton("Bus.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isAccident()){
                    jButton.setIcon(addingImageToAbutton("Accident.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isTaxi()){
                    jButton.setIcon(addingImageToAbutton("Taxi.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isStop()){
                    jButton.setIcon(addingImageToAbutton("Stop.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isGrass()){
                    jButton.setIcon(addingImageToAbutton("Grass.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isTree()){
                    jButton.setIcon(addingImageToAbutton("Tree.png"));
                }
                else if(cityController.getGraph().getCity().getMatrice().get(y).get(x).isWater()){
                    jButton.setIcon(addingImageToAbutton("Water.png"));
                }
                else {
                    jButton.setIcon(addingImageToAbutton("Road.png"));
                }

                jp.add(jButton);
            }
        }

        js = new JScrollPane(jp,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w= (int)screenSize.getWidth();
        int h = (int)screenSize.getHeight();
        js.setPreferredSize(new Dimension(width/6,height/6));



        //add(jp);
        frame.add(js);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    private void  initButtonsArray()
    {
        buttons=new ArrayList<>();
        for(int i=0;i<50;i++)
        {
            for(int j=0;j<50;j++)
            {
                buttons.add(i,new ArrayList<>());
            }

        }

    }


    public GameView(String title,CityController cityController,ArrayList<Car> cars) {
        super(title);
        this.cityController=cityController;
        this.cars=cars;


        initButtonsArray();


        frame.setLayout(new FlowLayout());


        setJPanelandComponent();
        frame.setContentPane(principal);
        principal.setLayout(new BorderLayout());
        principal.add(js, BorderLayout.CENTER);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /** FOR BUttons**/
        // addNormalCar.setPreferredSize(new Dimension(150, 40));
        /** **/
        carButtons = new JPanel();

        // carButtons.setLayout(new BorderLayout());

        // 3 Buttons ( 3 lines and 1 colmun
        carButtons.setLayout(new GridLayout(4,1));
        carButtons.setBackground(Color.DARK_GRAY);
        /** Adding Buttons to Cote **/
        carButtons.add(addNormalCar);
        carButtons.add(ShowPath);
        carButtons.add(addanAccident);
        carButtons.add(deleteAnAccident);
        //principal.add(carButtons, BorderLayout.EAST);
        /** Add the panel to hold Cars **/
        carsInfo = new JPanel();
        carsInfo.setLayout(new BorderLayout());
        carsPath = new JPanel();
        carsPath.setLayout(new BorderLayout());

        cote = new JPanel();
        cote.setLayout(new BorderLayout());
        cote.setBackground(Color.blue);
        cote.add(carsInfo, BorderLayout.NORTH);
        cote.add(carsPath, BorderLayout.CENTER);
        cote.add(carButtons, BorderLayout.SOUTH);
        cote.setPreferredSize(new Dimension(width/5, height));
        carsInfo.setPreferredSize(new Dimension(width/5, height/5));
        carsPath.setPreferredSize(new Dimension(width/3, height/3));
        carButtons.setPreferredSize(new Dimension(width/6, height/6));

        /** We split carsInfo on two pannel
         * One panel contains cars in the city with a boolean selected to go
         * second panel contains the path of the car selected with the time that it takes to reach the endpoint
         * **/
        carsName = new JPanel();
        carsName.setPreferredSize(new Dimension(width/5, height/5));
        jtextAreaCarnames=new JTextArea(width/5,height/5);
        jtextAreaCarnames.setEditable(false);
        jtextAreaCarpaths=new JTextArea(width/3,height/3);
        jtextAreaCarpaths.setEditable(false);
        jtextAreaCarpaths.setBackground(Color.gray);
        carsName.add(jtextAreaCarnames);
        carsPath.add(jtextAreaCarpaths);

        jspCarnames=new JScrollPane(jtextAreaCarnames);
        jspCarnames.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspCarnames.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        jspCarpaths=new JScrollPane(jtextAreaCarpaths);
        jspCarpaths.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspCarpaths.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);


        carsName.setLayout(new GridLayout(1,1));

        carsName.add(jspCarnames);
        carsPath.add(jspCarpaths);
        carsInfo.add(carsName, BorderLayout.CENTER);


        principal.add(cote, BorderLayout.EAST);



        /** **/

        /**Action Listenner for the 4 buttons**/
        addNormalCar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addNormalCar.setAddingNormalCar(true);
            }
        });

        addanAccident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addanAccident.setAddinganAccident(true);
            }
        });

        ShowPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { ShowPath.setShowingPath(true); }
        });

        deleteAnAccident.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAnAccident.setDeletinganAccident(true);
            }
        });


    }


    public void repaint(ArrayList<Car> cars) {


        showCars(this.computing);




        /** here we iterate on cars and then we show the image in the button with the car position**/
        /** This If , is to not have a lot of refresh into the initial position of the Astar algo**/

        repaintTrafficLights();
        /** Loop to all cars */
        for (int i = 0; i < cars.size(); i++) {

            if(cars.get(i).isSelectedForShowingPath()){
                showCarsPaths(cars.get(i).getX(),cars.get(i).getY());
            }

            if (cars.get(i).getLastX() >= 0 && cars.get(i).getLastY() >= 0)

                //TODO   Intelligent method that knows the last image that was in the button
                setImage(buttons.get(cars.get(i).getLastX()).get(cars.get(i).getLastY()), cars.get(i));


            int positionRelatedX = cars.get(i).getX() - cars.get(i).getLastX();
            int positionRelatedY = cars.get(i).getY() - cars.get(i).getLastY();


            /** for initial postion of car **/
            if ((cars.get(i).getLastX()==-1 )&& (cars.get(i).getLastY()==-1)){
           // if (!cars.get(i).isAstarIsRunning()) {
                cars.get(i).setAstarIsRunning(true);
                if(cars.get(i).isSelectedForRunningAstar()){
                    buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carRselect.png"));
                }
                else {
                    buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carR.png"));
                }

            }
            if (buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).getNumberOfCars() == 0) {

                if (positionRelatedX == 1) {
                    if (positionRelatedY == 1) {
                        buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carRD.png"));
                    } else if (positionRelatedY == -1) {
                        buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carLD.png"));
                    } else if (positionRelatedY == 0) {
                        if(cars.get(i).isSelectedForRunningAstar()){
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carDselect.png"));
                        }
                        else {
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carD.png"));
                        }
                    }
                } else if (positionRelatedX == 0) {
                    if (positionRelatedY == 1) {
                        if(cars.get(i).isSelectedForRunningAstar()){
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carRselect.png"));
                        }
                        else {
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carR.png"));
                        }
                    } else if (positionRelatedY == -1) { // case Left straight
                        if(cars.get(i).isSelectedForRunningAstar()){
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carLselect.png"));
                        }
                        else {
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carL.png"));
                        }
                    }
                } else {
                    if (positionRelatedY == 1) {
                        buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carRU.png"));
                    } else if (positionRelatedY == -1) {
                        buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carLU.png"));
                    } else if (positionRelatedY == 0) {
                        if(cars.get(i).isSelectedForRunningAstar()){
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carUselect.png"));
                        }
                        else {
                            buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carU.png"));
                        }
                    } else {
                        /** For initial postion**/
                        //   buttons.get(cars.get(i).getX()).get(cars.get(i).getY()).setIcon(addingImageToAbutton("carR.png"));
                    }
                }


            }
        }




    }

    private void repaintTrafficLights() {

        for (int i=0;i<cityController.getGraph().getCity().getTrafficLights().size();i++) {
            TrafficLight trafficLight = cityController.getGraph().getCity().getTrafficLights().get(i);
            int x = trafficLight.getNode().getRow();
            int y = trafficLight.getNode().getColumn();
            /** To know if there's a car on the traffic Light  **/
            /** Loop on all cars */

            boolean carExist=false;

            for (int j= 0;j < cars.size(); j++) {


                if ((cars.get(j).getX() == buttons.get(x).get(y).getXPositionInMatrix()) && (cars.get(j).getY() == buttons.get(x).get(y).getYPositionInMatrix())) {
                    //If the car will move then we put the light to green and we restart the timer of the light
                    carExist=true;


                    if (cars.get(j).getTimer() >= cars.get(j).getMaxTimer()) {
                        trafficLight.setState(true);
                        trafficLight.setTimer(0);

                    }
                }


            }
            if(!carExist) {

                if (trafficLight.isState()) {
                    setIconAccidentOrNot(buttons.get(x).get(y), cityController.getGraph().getCity().getMatrice().get(x).get(y).isAccident(), "Green_traffic_light.png", "Red_traffic_lightAccident.jpg");

                } else {
                    setIconAccidentOrNot(buttons.get(x).get(y), cityController.getGraph().getCity().getMatrice().get(x).get(y).isAccident(), "Red_Traffic_light.png", "Green_traffic_lightAccident.jpg");

                }
            }
        }
    }


    private void setIconAccidentOrNot(Button button,boolean var,String path,String path2){

        if (var)
        {
            button.setIcon(addingImageToAbutton(path2));
        }
        else{
            button.setIcon(addingImageToAbutton(path));

        }
    }

    private void setImage(Button button,Car car)
    {

        /** USER ONLY ADDS A CAR OR AN ACCIDENT
         *  ACCIDENT + TOUT LES POSS
         *
         */
        System.out.println(button.getNumberOfCars());

        if(button.getNumberOfCars() == 0) {


            if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).getTrafficLight() != null) {
                if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).getTrafficLight().isState() == false) {
                    setIconAccidentOrNot(button, cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident(), "Red_Traffic_light.png", "Red_traffic_lightAccident.jpg");
                } else if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).getTrafficLight().isState() == true) {
                    setIconAccidentOrNot(button, cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident(), "Green_traffic_light.png", "Green_traffic_lightAccident.jpg");
                } else {
                    setIconAccidentOrNot(button, cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident(), "Traffic_light.png", "Traffic_lightAccident.png");
                }
            } else if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isBus()) {
                setIconAccidentOrNot(button, cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident(), "Bus.png", "BusAccident.png");
            } else if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isTaxi()) {
                setIconAccidentOrNot(button, cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident(), "Taxi.png", "TaxiAccident.png");
            } else if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isStop()) {
                setIconAccidentOrNot(button, cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident(), "Stop.png", "StopAccident.png");
            } else if (cityController.getGraph().getCity().getMatrice().get(car.getLastX()).get(car.getLastY()).isAccident()) {
                button.setIcon(addingImageToAbutton("Accident.png"));
            } else {

                button.setIcon(addingImageToAbutton("Road.png"));

            }
        }
    }


    private void setImageWithAccident(Button jButton)
    {

        /** USER ONLY ADDS A CAR OR AN ACCIDENT
         *  ACCIDENT + TOUT LES POSS
         *
         */

        // is it a button ?
        if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).getTrafficLight()!=null)
        {
            if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).getTrafficLight().isState()== false) {
                setIconAccidentOrNot(jButton, cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident(), "Red_Traffic_light.png", "Red_traffic_lightAccident.jpg");
            }
            else  if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).getTrafficLight().isState()== true) {
                setIconAccidentOrNot(jButton, cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident(), "Green_traffic_light.png", "Green_traffic_lightAccident.jpg");
            }
            else{
                setIconAccidentOrNot(jButton, cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident(), "Traffic_light.png", "Traffic_lightAccident.png");
            }
        }

        else if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isBus()){
            setIconAccidentOrNot(jButton,cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident(),"Bus.png","BusAccident.png");
        }
        else if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isTaxi()){
            setIconAccidentOrNot(jButton,cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident(),"Taxi.png","TaxiAccident.png");
        }
        else if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isStop()){
            setIconAccidentOrNot(jButton,cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident(),"Stop.png","StopAccident.png");
        }
        else if(cityController.getGraph().getCity().getMatrice().get(jButton.getXPositionInMatrix()).get(jButton.getYPositionInMatrix()).isAccident()){
            jButton.setIcon(addingImageToAbutton("Accident.png"));
        }
        else {
            jButton.setIcon(addingImageToAbutton("Road.png"));

        }
    }


    private void showCars(boolean computing){

        jtextAreaCarnames.setBackground(Color.gray);
        jtextAreaCarnames.setForeground(Color.white);

        jtextAreaCarnames.setText("");
        jtextAreaCarnames.setText("There is "+cars.size() +" Cars in the CITY"+"\n");


        for(int i=0;i<cars.size();i++)
        {
            if(computing==true && cars.get(i).isSelectedForRunningAstar()==true){
                jtextAreaCarnames.append(cars.get(i).getId()+"   "+cars.get(i).isSelectedForRunningAstar() +"   "+"COMPUTING PATH....\n");

            }
            else{
                jtextAreaCarnames.append(cars.get(i).getId()+"   "+cars.get(i).isSelectedForRunningAstar() +"\n");
            }
        }
    }


   private void showCarsPaths(int i, int j) {

       jtextAreaCarpaths.setBackground(Color.gray);
       jtextAreaCarpaths.setForeground(Color.white);


       jtextAreaCarpaths.setText("                                           Path Overview\n");
           jtextAreaCarpaths.append("\n");
           jtextAreaCarpaths.append("_________________________\n");
           jtextAreaCarpaths.append("| Building or Park :               [X]     |\n");
           jtextAreaCarpaths.append("| Road :                                  [_]     |\n");
           jtextAreaCarpaths.append("| Path :                                    [#]     |\n");
           jtextAreaCarpaths.append("|________________________|\n");
           jtextAreaCarpaths.append("\n");


           for (int n = 0; n < cars.size(); n++) {
               if (cars.get(n).getX() == i && cars.get(n).getY() == j) {
                   jtextAreaCarpaths.append("                                                  CAR " + cars.get(n).getId() + "\n");
                   jtextAreaCarpaths.append("\n");

                   jtextAreaCarpaths.append(cityController.printPath(cars.get(n).getAstarPath()));

               }
           }
       }

}
