package com.tetris.game;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
    public void changeStringRange_test(){
        Board board = new Board();
        
        String original= "hola mundo";
        String remplazo= "javaTest";

        assertEquals("hola javaTest",board.changeStringRange(5, original, remplazo));
    }

    @Test
    public void addPiece_test(){
        Board board = new Board();

        board.addPiece(new PieceDog(0,0));
        assertNotNull(board.getPieces());
    }

    @Test
    public void moveDownPiece_test(){
        Board board = new Board();

        board.addPiece(new PieceDog(0,0));
        
        for (int i = 0; i < 20; i++) {
            System.out.println(board.getMatrix(i));
        }

        for (int i = 0; i < 19; i++) {
            board.moveDownActivePiece();
        }
        assertEquals(true,board.moveDownActivePiece());
    }
}
