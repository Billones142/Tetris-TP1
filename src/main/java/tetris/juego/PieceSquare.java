package tetris.juego;

public class PieceSquare extends BasePiece{
    public PieceSquare(int rotation, int variation) {
        super();
        setMaxRotations(1);
    }
    
    public String[] getMatrix(){
        return matrix;
    }

    String[] matrix={"    ",
                    "    ",
                    "ZZ  ",
                    "ZZ  "};
}
