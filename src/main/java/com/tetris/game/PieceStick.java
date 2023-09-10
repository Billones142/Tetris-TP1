package com.tetris.game;

public class PieceStick extends BasePiece{
    public PieceStick() {
        super();
        Math.random();
        setMaxRotations(2);
        setRotation((int)(Math.random() * this.getMaxRotations()));
    }

    public PieceStick(int rotation) {
        super();
        setMaxRotations(2);
        setRotation(rotation);
    }

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        switch (this.getRotation()) {
            case 0:
                value= matrixHorizontal;
                break;
        
            case 1:
                value= matrixVertical;
                break;
            default:
                break;
        }
        return value;
        }
    
    private final static String[] matrixHorizontal={"    ",
                                            "    ",
                                            "    ",
                                            "SSSS"};

    private final static String[] matrixVertical=  {"S   ",
                                            "S   ",
                                            "S   ",
                                            "S   "};
    
};