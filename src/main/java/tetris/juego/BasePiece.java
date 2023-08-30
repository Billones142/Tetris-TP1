package tetris.juego;

public abstract class BasePiece {
    private int state;

    public BasePiece() {
        super();
        setState(0);
    }

    protected void setState(int state) {
        this.state = state;
    }

    protected int getState() {
        return state;
    }

    public String[] getMatrix() {
        String[] matrix= {"",""};
        return matrix;
    } //a definir por cada pieza

    public void turnLeft(){
        setState(getState()-1);
    }

    public void turnRight(){
        setState(getState()+1);
    }
}
