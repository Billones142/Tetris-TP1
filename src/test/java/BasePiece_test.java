import static org.junit.Assert.*;

import org.junit.Test;

import tetris.juego.*;

public class BasePiece_test {
    @Test
    public void PieceDog_creation_test()
    {
        PieceDog testD= new PieceDog(0,0);
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
        PieceL testL= new PieceL(0,0);
        String[] matrix= {  "    ",
                            "L   ",
                            "L   ",
                            "LL  "};

        assertArrayEquals(testL.getMatrix(), matrix );
    }

    @Test
    public void PieceSquare_creation_test()
    {
        PieceSquare testZ= new PieceSquare(0,0);
        String[] matrix= {  "    ",
                            "    ",
                            "ZZ  ",
                            "ZZ  "};

        assertArrayEquals(testZ.getMatrix(), matrix );
    }

    @Test
    public void PieceT_creation_test()
    {
        PieceT testT= new PieceT(0,0);
        String[] matrix= {  "    ",
                            "    ",
                            "TTT ",
                            " T  "};

        assertArrayEquals(testT.getMatrix(), matrix );
    }


    @Test
    public void PieceStick_creation_test()
    {
        PieceStick testS= new PieceStick(0,0);
        String[] matrix= {  "    ",
                            "    ",
                            "    ",
                            "SSSS"};

        assertArrayEquals(testS.getMatrix(), matrix );
    }
}
