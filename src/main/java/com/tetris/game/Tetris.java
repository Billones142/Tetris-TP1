package com.tetris.game;

public class Tetris {
    public Clock clock = new Clock();
    private Board board = new Board();
    
    

    public void start(){ //TODO
        
    }

    public void state(){ //TODO

    }

    public void rotateLeft(){ //TODO

    }

    public void rotateRight(){ //TODO

    }

    public void tick(){ 
        clock.tick();
        if(board.moveDownActivePiece()){
            board.addPiece();
        }
    }
}