package tetris.juego;

public class PieceT extends BasePiece{
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

    String[] matrixUp= {"    ",
                        "    ",
                        "TTT ",
                        " T  "};
    
    String[] matrixRight=  {"    ",
                            " T  ",
                            "TT  ",
                            " T  "};
    
    String[] matrixDown=   {"    ",
                            "    ",
                            " T  ",
                            "TTT "};
    
    String[] matrixLeft=   {"    ",
                            "T   ",
                            "TT  ",
                            "T   "};
}