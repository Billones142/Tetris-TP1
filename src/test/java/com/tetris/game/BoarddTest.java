package com.tetris.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/*
    Al menos 7 test

addPiece()
addPiece(BasePiece piece)
moveDownActivePiece()
turnActivePieceLeft()
turnActivePieceRight()
contarLineasCompletas()
noSpaceLeft()
 */

public class BoarddTest {
    @Test
    public void board_new_test(){
        Board board1= new Board();

        for(int i=0 ; i < 20 ; i++){
            assertEquals(board1.getMatrix(i), "          ");
        }
    }

    @Test
    public void getMatrix_test(){
        Board board = new Board();
        ArrayList<String> matrix= board.getMatrix();

        for (int i = 0; i < matrix.size(); i++) {
            assertEquals("          " , matrix.get(i));
        }
    }

    @Test
    public void addPiece_test(){
        Board board = new Board();
        BasePiece dog= new PieceDog(0,0);

        board.addPiece(dog);
        assertEquals(board.getPieces(0),dog);
    }

    @Test
    public void moveDownPiece_test(){
        Board board = new Board();

        board.addPiece(new PieceL(0,0));
        

        for (int i = 0; i < 19; i++) {
            board.moveDownActivePiece();
        }
        assertEquals(true,board.moveDownActivePiece());
    }

    @Test
    public void turnActivePieceLeftTest(){ //TODO

    }

    @Test
    public void turnActivePieceRight(){ //TODO

    }

    @Test
    public void contarLineasCompletasTest(){ //TODO

    }

    @Test
    public void noSpaceLeftTest(){ //TODO

    }
}
