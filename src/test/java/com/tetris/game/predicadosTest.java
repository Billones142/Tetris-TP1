package com.tetris.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.Test;

public class predicadosTest {
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

    @Test
    public void count_Piece_looking_Right(){
        ArrayList<BasePiece> pieces= new ArrayList<BasePiece>();

        for (int i = 0; i < 27; i++) {
            pieces.add(new PieceDog(0, 0)); //mirando a la derecha
        }
        for (int i = 0; i < 5; i++) {
            pieces.add(new PieceDog(1, 0));
        }
        for (int i = 0; i < 23; i++) {
            pieces.add(new PieceL(1, 0)); //mirando a la derecha
        }

        Predicate<BasePiece> piezaMirandoALaDerecha= piece -> piece.mirandoDerecha();
        Predicate<BasePiece> piezaEsPerro= piece -> piece instanceof PieceDog;

        Predicate<BasePiece> piezaEsPerroYMiraALaDerecha= piezaEsPerro.and(piezaMirandoALaDerecha);

        int countTotal= (int)pieces.stream().count();
        int countPredicate= (int)pieces.stream().filter(piezaEsPerroYMiraALaDerecha).count();

        assertEquals(55, countTotal);
        assertEquals(27, countPredicate);
    }

    @Test
    public void failTest(){
        assertEquals(true, false);
    }
}
