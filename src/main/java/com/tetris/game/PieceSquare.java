package com.tetris.game;

public class PieceSquare extends BasePiece{
    public PieceSquare() {
        super();
        Math.random();
        setMaxRotations(1);
        setRotation((int)(Math.random() * this.getMaxRotations()));
    }

    public PieceSquare(int rotation, int variation) {
        super();
        setMaxRotations(1);
    }
    
    public String[] getMatrix(){
        return matrix;
    }

    private final static String[] matrix=  {"    ",
                                    "    ",
                                    "ZZ  ",
                                    "ZZ  "};
    
    public boolean mirandoDerecha(){
        return true;
    }
}
