package tetris.juego;

import tetris.Interfaces.IRotator;

public abstract class BasePiece implements IRotator{
    private int rotation,maxRotations;

    protected void setRotation(int state) {
        this.rotation = state;
    }

    protected int getRotation() {
        return rotation;
    }

    public String[] getMatrix() {
        String[] matrix= {"",""};
        return matrix;
    } //a definir por cada pieza

    protected void setMaxRotations(int maxRotations) {
        this.maxRotations = maxRotations-1;
    }

    protected int getMaxRotations() {
        return maxRotations;
    }

    public void rotateLeft(){
        setRotation(getRotation()-1);
        checkRotationLimit();
    }

    public void rotateRight(){
        setRotation(getRotation()+1);
        checkRotationLimit();
    }

    private void checkRotationLimit(){
        if(rotation < 0){
            setMaxRotations(-(rotation % maxRotations));
        }

        if(rotation > maxRotations){
            setMaxRotations(rotation % maxRotations);
        }
    }
}
