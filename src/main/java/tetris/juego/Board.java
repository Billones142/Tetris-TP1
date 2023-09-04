package tetris.juego;

import java.util.ArrayList;

public class Board {
    public Board() {
        super();
        for (int i=0 ; i < 20 ; i++){
            matrix.add(matrix.size()+1, "          ");
        }
    }


    ArrayList<String> matrix.;

    public String getMatrix(int index) {
        return matrix.get(index);
    }

    private void setMatrix(ArrayList<String> matrix) {
        this.matrix = matrix;
    }

    public void addPiece(){

    }
}
