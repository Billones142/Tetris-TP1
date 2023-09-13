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
        BasePiece piece= new PieceL(0,0);

        board.addPiece(piece);
        assertEquals(board.getPieces(0),piece);
        board.printBoard();

        assertNotEquals("0000000000", board.getMatrix(0));

        int[] activePieceLocation= board.getActivePieceLocation();
        boolean positionXIsNull= activePieceLocation[0] == -1;
        boolean positionYIsNull= activePieceLocation[1] == -1;
        assertEquals(false, positionXIsNull || positionYIsNull); // esta en la matriz?
    }

    @Test
    public void moveDownPiece_test(){
        Board board = new Board();
        BasePiece pieceL= new PieceL(0,0);

        board.addPiece(pieceL);
        assertEquals(board.getPieces(0),pieceL);

        assertEquals(false,board.moveDownActivePiece());

        boolean chocoAlBajar= false;
        for (int i = 0; i < 19; i++) { // se repite muchas veces para ver si choca
            if(board.moveDownActivePiece()){
                chocoAlBajar= true;
                break;
            }
            
        }

        assertEquals(true, chocoAlBajar);
    }

    @Test
    public void moveDownPieceCrash_test(){
        Board board = new Board();

        board.moveDownActivePiece();
    }

    @Test
    public void turnActivePieceLeftTest(){
        Board board = new Board();

        board.addPiece(new PieceDog(0,0));
        board.moveDownActivePiece();
        board.moveDownActivePiece();
        board.turnActivePieceLeft();
    }

    @Test
    public void turnActivePieceRight(){
        Board board = new Board();
        
        board.addPiece(new PieceDog(0,0));
        board.moveDownActivePiece();
        board.moveDownActivePiece();
        board.turnActivePieceRight();
    }

    @Test
    public void contarLineasCompletasTest(){
        Board board = new Board();

        for (int i = 0; i < 5; i++) {
            board.addPiece(new PieceSquare(),i*2);
            while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza
        }

        for (int i = 0; i < 20; i++) {
            assertEquals(true, board.getMatrix(i) != "XXXXXXXXXX");
        }

        assertEquals(2, board.getLineCount());
    }

    @Test
    public void noSpaceLeftTest(){
        Board board = new Board();


        for (int i = 0; i < 8; i++) {
            board.addPiece(new PieceSquare(0,0),0);
            while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza
        }

        assertEquals("XX00000000", board.getMatrix(4));
        assertEquals("0000000000", board.getMatrix(3));

        board.addPiece(new PieceSquare(0,0),0);
        while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza

        assertEquals(true, board.noSpaceLeft());
    }

    
    @Test
    public void pieceActiveOnBoardTest(){
        Board board = new Board();

        assertEquals(false,board.pieceActiveOnBoard());
        
        board.addPiece();
        assertEquals(true,board.pieceActiveOnBoard());
    }

    @Test
    public void pieceNextToBoardTest(){
        Board board = new Board();

        board.addPiece(new PieceStick(0),9);

        board.turnActivePieceLeft();


        board = new Board();

        board.addPiece(new PieceStick(0),9);

        board.turnActivePieceRight();
    }

    @Test
    public void giroIzquierdaGeneraChoque(){
        Board board = new Board();

        board.addPiece(new PieceSquare(), 8);
        while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza

        board.addPiece(new PieceSquare(), 8);
        while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza

        board.addPiece(new PieceDog(1,0),6);
        for(int i= 0; i < 15 ;i++){
            board.moveDownActivePiece();
        }

        assertEquals(true, board.turnActivePieceLeft()); //chequear si hubo choque
    }

    @Test
    public void giroDerechaGeneraChoque(){
        Board board = new Board();

        board.addPiece(new PieceSquare(), 8);
        while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza

        board.addPiece(new PieceSquare(), 8);
        while(!board.moveDownActivePiece()){} // baja la pieza hasta que choque con el piso o una pieza

        board.addPiece(new PieceDog(1,0),6);
        for(int i= 0; i < 15 ;i++){
            board.moveDownActivePiece();
        }

        assertEquals(true, board.turnActivePieceRight()); //chequear si hubo choque
    }
}