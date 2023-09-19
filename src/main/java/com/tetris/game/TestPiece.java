package com.tetris.game;

public class TestPiece extends BasePiece{
    public TestPiece() {
        super();
        setMaxVariations(1);
        setMaxRotations(1);
        setRotation(0);
        setVariation(0);
    }

    public boolean mirandoDerecha(){
        return false;
    }

    public boolean mirandoIzquierda(){
        return false;
    }

    public boolean mirandoArriba(){
        return false;
    }

    public boolean mirandoAbajo(){
        return false;
    }
}
