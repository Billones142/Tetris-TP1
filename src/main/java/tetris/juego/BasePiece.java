package tetris.juego;

import tetris.Interfaces.IRotator;
import tetris.Interfaces.PieceGetMatrix;

public abstract class BasePiece implements IRotator, PieceGetMatrix{
    private int rotation,maxRotations;
    private boolean hasCollided;

    public BasePiece() {
        super();
        setHasCollided(false);
    }

    //*************** INICIO ENCAPSULACION ***************\\

    protected void setRotation(int state) {
        this.rotation = state;
    }

    public int getRotation() {
        return rotation;
    }

    @Override
    public String[] getMatrix() {
        String[] matrix= {"",""};
        return matrix;
    } //a definir por cada pieza

    protected void setMaxRotations(int maxRotations) {
        this.maxRotations = maxRotations-1;
    }

    public int getMaxRotations() {
        return maxRotations;
    }

    protected void setHasCollided(boolean hasCollided) {
        this.hasCollided = hasCollided;
    }

    public boolean getHasCollided(){
        return hasCollided;
    }

    //*************** FIN ENCAPSULACION ***************\\

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
            setRotation(maxRotations);
        }

        if(rotation > maxRotations){
            setRotation(0);
        }
    }

    public void collided(){
        setHasCollided(true);
    }
}