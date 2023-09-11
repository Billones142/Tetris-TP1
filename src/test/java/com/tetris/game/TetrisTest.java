package com.tetris.game;

import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;  //JUnit 5

import org.junit.Test;
//import org.junit.jupiter.api.Test;  //JUnit 5

public class TetrisTest {
    @Test
    public void tetris_test()
    {
        Tetris t= new Tetris(3);
        assertNotNull(t);
    }

    @Test
    public void test(){
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
}