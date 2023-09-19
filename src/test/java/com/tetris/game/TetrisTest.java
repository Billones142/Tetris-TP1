package com.tetris.game;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;  //JUnit 5

import java.util.ArrayList;

import org.junit.Test;
//import org.junit.jupiter.api.Test;  //JUnit 5

/*
    al menos 6 test

getCantLineasParaGanar()
start()
state()
rotateLeft()
rotateRight()
tick()
*/

public class TetrisTest {
    @Test
    public void tetris_test()
    {
        Tetris t= new Tetris(3);
        assertNotNull(t);
    }

    @Test
    public void tickTest(){
        Tetris tetris= new Tetris(3);

        tetris.start();

        for (int i = 0; i < 20; i++) {
            tetris.tick();
            assertEquals(true,tetris.clock.getTicks() == i+2);
        }
        assertNotNull(tetris.board.getPieces(0));
    }

    @Test
    public void clockTest(){
        long timeAntes= System.currentTimeMillis();
        Tetris tetris = new Tetris(3);

        assertEquals(true,tetris.clock.getTimeMiliseconds() < timeAntes);
    }

    @Test
    public void getCantLineasParaGanarTest(){
        Tetris tetris = new Tetris(4);
        
        assertEquals(4,tetris.getCantLineasParaGanar());
    }

    @Test
    public void startTest(){
        Tetris tetris = new Tetris(4);

        assertEquals(0,tetris.clock.getTicks());
        tetris.start();
        assertEquals(1,tetris.clock.getTicks());

    }

    @Test
    public void stateTest(){ //TODO: estado: juego perdido, juego ganado
        Tetris tetris = new Tetris(4);

        assertEquals("juego no iniciado",tetris.state());
        
        tetris.start();
        assertEquals("juego en curso",tetris.state());
    }

    @Test
    public void rotateLeftTest(){
        Tetris tetris = new Tetris(4);

        tetris.start();

        for (int i = 0; i < 4; i++) {
            tetris.tick();
        }

        tetris.rotateLeft();
    }

    @Test
    public void rotateRightTest(){
        Tetris tetris = new Tetris(4);

        tetris.start();

        for (int i = 0; i < 4; i++) {
            tetris.tick();
        }

        tetris.rotateRight();
    }

    @Test
    public void winTest(){

    }

    @Test
    public void loseTest(){
        Tetris tetris = new Tetris(2);
        

        tetris.start();
        for (int i = 0; i < 15; i++) {
            tetris.board.addPiece(new PieceSquare(),0);
            while(tetris.board.moveDownActivePiece()){}
        }

        assertEquals("juego perdido",tetris.state());
    }

    @Test
    public void countStick(){

        ArrayList<BasePiece> pieces= new ArrayList<BasePiece>();

        for (int i = 0; i < 100; i++) {
            switch ((int)(Math.random() * 5)) {
                case 0:
                    pieces.add(new PieceDog());
                    break;
            
                case 1:
                    pieces.add(new PieceL());
                    break;
                
                case 2:
                    pieces.add(new PieceSquare());
                    break;

                case 3:
                    pieces.add(new PieceStick());
                    break;

                case 4:
                    pieces.add(new PieceT());
                    break;
                default:
                    break;
            }
        }

        int amount= (int)
                    pieces
                    .stream()
                    .filter(piece -> piece instanceof PieceStick)
                    .count();
        assertEquals(true,amount > 0);


        }
    }