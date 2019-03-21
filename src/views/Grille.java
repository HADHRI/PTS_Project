package views;

import javax.swing.JPanel;

public class Grille extends JPanel{

    int matrice[][];

    public Grille(int l, int L){
        matrice = new int[l][L];
        for(int i = 0; i< l; i++){
            for(int j = 0; j < L; j++){
                matrice[i][j] = 0;
            }
        }
    }

    public void addEntree(int x, int y){
        matrice[x][y] = 1;
    }

    public int[][] getMatrice() {
        return matrice;
    }


}
