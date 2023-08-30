package tetris.juego;

public class PieceL extends BasePiece{
    public PieceL() {
        super();
        
    };
    
    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        if(getState()>1){
            setState(0);
        }

        switch (this.getState()) {
            case 0:
                value= ;
                break;
        
            case 1:
                value= ;
                break;
            default:
                break;
        }
        return value;
        }

    
}