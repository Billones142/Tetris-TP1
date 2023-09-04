import static org.junit.Assert.*;

import org.junit.Test;

import tetris.juego.Board;

public class Board_test {
    @Test
    public void board_new_test(){
        Board board1= new Board();

        for(int i=0 ; i < 20 ; i++){
            assertEquals(board1.getMatrix(i), "          ");
        }
    }

    @Test
    public void addPiece_test(){
        assertTrue(false);
    }
}
