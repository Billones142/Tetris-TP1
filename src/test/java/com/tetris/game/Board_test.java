package com.tetris.game;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;  //JUnit 5

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
//import org.junit.jupiter.api.Test;  //JUnit 5
import org.junit.rules.ExpectedException;

public class Board_test {
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

    /*@Test
    public void addPiece_test(){
        Board board = new Board();

        board.addPiece(new PieceDog(0,0));
        assertNotNull(board.getPieces());
    }*/

    /*@Test
    public void countHeight_test(){
        Board board = new Board();

        board.addPiece();

        for (int i = 0; i < 20; i++) {
            board.moveDownActivePiece();
        }
        assertTrue(board.moveDownActivePiece());
    }*/
}