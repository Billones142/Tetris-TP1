import static org.junit.Assert.*;

import org.junit.Test;

import tetris.juego.Tetris;

public class Tetris_test {
    @Test
    public void tetris_test()
    {
        Tetris t= new Tetris();
        assertNotNull(t);
    }
}
