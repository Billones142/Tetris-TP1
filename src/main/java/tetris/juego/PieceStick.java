package tetris.juego;

public class PieceStick extends BasePiece{
    public PieceStick() {
        super();
    }

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        if(getState()>1){
            setState(0);
        }

        switch (this.getState()) {
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