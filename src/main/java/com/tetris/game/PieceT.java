package com.tetris.game;

public class PieceT extends BasePiece{
    public PieceT() {
        super();
        Math.random();
        setMaxRotations(4);
        setRotation((int)(Math.random() * this.getMaxRotations()));
    }

    public PieceT(int rotation, int variation) {
        super();
        setMaxRotations(4);
        setRotation(rotation);
        
    };
    
    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        switch (this.getRotation()) {
            case 0:
                value= matrixUp;
                break;
        
            case 1:
                value= matrixRight;
                break;
            
            case 2:
                value= matrixDown;
                break;
            
            case 3:
                value= matrixLeft;
                break;
            default:
                break;
        }
        return value;
        }

    private final static String[] matrixUp={"    ",
                                    "    ",
                                    "TTT ",
                                    " T  "};
    
    private final static String[] matrixRight= {"    ",
                                        " T  ",
                                        "TT  ",
                                        " T  "};
    
    private final static String[] matrixDown=  {"    ",
                                        "    ",
                                        " T  ",
                                        "TTT "};
    
    private final static String[] matrixLeft=  {"    ",
                                        "T   ",
                                        "TT  ",
                                        "T   "};

    public boolean mirandoDerecha(){
        if(this.getRotation() == 1){
            return true;
        }
        return false;
    }

    public boolean mirandoIzquierda(){
        if(this.getRotation() == 3){
            return true;
        }
        return false;
    }

    public boolean mirandoArriba(){
        if(this.getRotation() == 0){
            return true;
        }
        return false;
    }

    public boolean mirandoAbajo(){
        if(this.getRotation() == 2){
            return true;
        }
        return false;
    }
}