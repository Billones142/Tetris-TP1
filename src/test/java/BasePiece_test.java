import static org.junit.Assert.*;

import org.junit.Test;

import tetris.juego.*;

public class BasePiece_test {
    @Test
    public void PieceDog_creation_test()
    {
        PieceDog testD= new PieceDog();
        String[] matrix={   "    ",
                            "    ",
                            " DD ",
                            "DD  "
        };
        assertArrayEquals(testD.getMatrix(), matrix );
    }

    @Test
    public void PieceL_creation_test()
    {
        PieceT testL= new PieceT();
        String[] matrix= {  "    ",
                            "L   ",
                            "L   ",
                            "LL  "};

        assertArrayEquals(testL.getMatrix(), matrix );
    }

    @Test
    public void PieceSquare_creation_test()
    {
        PieceT testZ= new PieceT();
        String[] matrix= {  "    ",
                            "    ",
                            "ZZ  ",
                            "ZZ  "};

        assertArrayEquals(testZ.getMatrix(), matrix );
    }

    @Test
    public void PieceT_creation_test()
    {
        PieceT testT= new PieceT();
        String[] matrix= {  "    ",
                            "    ",
                            " T ",
                            "TTT "};

        assertArrayEquals(testT.getMatrix(), matrix );
    }


    @Test
    public void PieceStick_creation_test()
    {
        PieceStick testS= new PieceStick();
        String[] matrix= {  "    ",
                            "    ",
                            "    ",
                            "SSSS"};

        assertArrayEquals(testS.getMatrix(), matrix );
    }
}
