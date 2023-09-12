package com.tetris.game;

import static org.junit.Assert.assertEquals;

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
            assertEquals("0000000000", board1.getMatrix(i));
        }
    }

    @Test
    public void getMatrix_test(){
        Board board = new Board();
        ArrayList<String> matrix= board.getMatrix();

        for (int i = 0; i < matrix.size(); i++) {
            assertEquals("0000000000" , matrix.get(i));
        }
    }

    @Test
    public void addPiece_test(){
        Board board = new Board();
        BasePiece dog= new PieceDog(0,0);

        board.addPiece(dog);
        assertEquals(board.getPieces(0),dog);
        boolean soloEspaciosVacios= true;
        for (int i = 0; i < 20; i++) {
            if (board.getMatrix(i) != "          ") {
                soloEspaciosVacios= false;
            }
        }
        board.printBoard();
        assertEquals(false,soloEspaciosVacios);
        int[] activePieceLocation= board.getActivePieceLocation();
        boolean positionXIsNull= activePieceLocation[0] == -1;
        boolean positionYIsNull= activePieceLocation[1] == -1;
        assertEquals(false, positionXIsNull || positionYIsNull);
    }

    @Test
    public void moveDownPiece_test(){
        Board board = new Board();
        BasePiece pieceL= new PieceL(0,0);

        board.addPiece(pieceL);
        assertEquals(board.getPieces(0),pieceL);

        assertEquals(false,board.moveDownActivePiece());

        for (int i = 0; i < 19; i++) {
            board.moveDownActivePiece();
            board.printBoard();
            System.out.println("fin de matriz");
        }
        assertEquals(true,board.moveDownActivePiece());
    }

    @Test
    public void turnActivePieceLeftTest(){ //TODO
        Board board = new Board();

        board.addPiece();
        board.turnActivePieceLeft();
    }

    @Test
    public void turnActivePieceRight(){ //TODO
        Board board = new Board();

        board.addPiece();
        board.turnActivePieceRight();
    }

    @Test
    public void contarLineasCompletasTest(){ //TODO
        Board board = new Board();

        board.addPiece();

        for (int i = 0; i < 20; i++) {
            assertEquals(true, board.getMatrix(i) != "XXXXXXXXXX");
        }
    }

    @Test
    public void noSpaceLeftTest(){ //TODO

    }
}