package com.company;
//This is one part of the model

public class City {

    //----------------------------------Attributes and Properties-------------------------------------------

    private int width;

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    private int height;

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    private Node[][] matrice;

    public Node[][] getMatrice() {
        return matrice;
    }
    public void setMatrice(Node[][] matrice) {
        this.matrice = matrice;
    }

    //---------------------------------------------Constructor---------------------------------------------------

    public City(int nb_rows, int nb_columns){

        this.height=nb_rows;
        this.width=nb_columns;

        this.matrice=new Node[height][width];


    }


    //---------------------------------------------Methods---------------------------------------------------



}

