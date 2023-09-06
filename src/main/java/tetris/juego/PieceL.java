package tetris.juego;

public class PieceL extends BasePiece{
    int variation;
    public PieceL(int rotation,int variation) {
        super();
        setMaxRotations(4);
        setRotation(rotation);
        setVariation(variation);;
    };
    
    public void setVariation(int variation) {
        this.variation = variation;
    }

    public int getVariation() {
        return variation;
    }

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        if(getRotation()>1){
            setRotation(0);
        }

        if(getRotation()>1){
            setRotation(0);
        }

        switch (this.getRotation()) {
            case 0:
                value= matrixUp[this.variation];
                break;
        
            case 1:
                value= matrixRight[this.variation];
                break;
            case 2:
                value= matrixDown[this.variation];
                break;
            case 3:
                value= matrixLeft[this.variation];
                break;
            default:
                break;
        }
        return value;
        }

    String[][] matrixUp=  {{"    ",
                            "L   ",
                            "L   ",
                            "LL  "
                            },{
                            "    ",
                            " L  ",
                            " L  ",
                            "LL  "}};
    
    String[][] matrixRight= {{
                            "    ",
                            "    ",
                            "LLL ",
                            "L   "
                            },{
                            "    ",
                            "    ",
                            "L   ",
                            "LLL "}};
    
    String[][] matrixDown={{"    ",
                            "LL  ",
                            " L  ",
                            " L  "
                            },{
                            "    ",
                            "LL  ",
                            "L   ",
                            "L   "}};
    
    String[][] matrixLeft={{"    ",
                            "    ",
                            "  L ",
                            "LLL "
                            },{
                            "    ",
                            "    ",
                            "LLL ",
                            "  L "}};

    String[][] matrixLeft=
}