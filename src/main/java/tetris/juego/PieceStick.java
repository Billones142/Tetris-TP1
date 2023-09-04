package tetris.juego;

public class PieceStick extends BasePiece{
    public PieceStick(int rotation, int variation) {
        super();
        setMaxRotations(1);
        setRotation(rotation);
    }

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        if(getRotation()>1){
            setRotation(0);
        }

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
    
    String[] matrixHorizontal={ "    ",
                                "    ",
                                "    ",
                                "SSSS"
    };
    String[] matrixVertical={   "S   ",
                                "S   ",
                                "S   ",
                                "S   "
    };
};