package com.tetris.game;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;  //JUnit 5

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

        
        for (int i = 0; i < 20; i++) {
            tetris.tick();
            assertEquals(true,tetris.clock.getTicks() == i+1);
        }
        assertNotNull(tetris.board.getPieces());
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

        tetris.rotateLeft();
    }

    @Test
    public void rotateRightTest(){
        Tetris tetris = new Tetris(4);

        tetris.start();

        tetris.rotateRight();
    }

    @Test
    public void winTest(){

    }

    @Test
    public void loseTest(){

    }
}