package tetris.juego;

public class PieceL extends BasePiece{
    int variation;
    public PieceL() {
        super();
        
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

        if(getState()>1){
            setState(0);
        }

        switch (this.getState()) {
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

    String[][] matrixUp=    {{},{}};
    String[][] matrixRight= {{},{}};
    String[][] matrixDown=  {{},{}};
    String[][] matrixLeft=  {{},{}};
}