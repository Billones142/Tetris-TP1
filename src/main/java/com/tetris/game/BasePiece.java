package com.tetris.game;

import com.tetris.Interfaces.IGiro;
import com.tetris.Interfaces.IPieceGetMatrix;
import com.tetris.Interfaces.IRotator;

public abstract class BasePiece implements IRotator, IPieceGetMatrix, IGiro{
    private int rotation,maxRotations,variation,maxVariations;
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

    public String[] getMatrix() {
        return null;
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

    protected void setVariation(int variation) {
        this.variation = variation;
    }

    public int getVariation() {
        return variation;
    }

    protected void setMaxVariations(int maxVariations) {
        this.maxVariations = maxVariations;
    }

    public int getMaxVariations() {
        return maxVariations;
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