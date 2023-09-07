import static org.junit.Assert.*;

import org.junit.Test;

import tetris.juego.*;

public class BasePiece_test {
    @Test
    public void PieceDog_test()
    {
        PieceDog dog1= new PieceDog(0,0);
        PieceDog dog2= new PieceDog(0,1);

        String[] matrix1={  "    ",
                            "    ",
                            " DD ",
                            "DD  "
        };
        String[] matrix2={  "    ",
                            "D   ",
                            "DD  ",
                            " D  "
        };
        String[] matrix3={  "    ",
                            "    ",
                            "DD  ",
                            " DD "
        };
        String[] matrix4={  "    ",
                            " D  ",
                            "DD  ",
                            "D   "
        };

        //variacion 1
        assertArrayEquals(dog1.getMatrix(), matrix1 );

        dog1.rotateRight();
        assertArrayEquals(dog1.getMatrix(), matrix2 );

        dog1.rotateLeft();
        assertArrayEquals(dog1.getMatrix(), matrix1 );
        
        //variacion 2
        assertArrayEquals(dog2.getMatrix(), matrix3 );

        dog2.rotateRight();
        assertArrayEquals(dog2.getMatrix(), matrix4 );

        dog2.rotateLeft();
        assertArrayEquals(dog2.getMatrix(), matrix3 );
    }

    @Test
    public void PieceL_test()
    {
        PieceL l1= new PieceL(0, 0);
        PieceL l2= new PieceL(0, 1);

        String[] matrix1= { "    ",
                            "L   ",
                            "L   ",
                            "LL  "};
        
        String[] matrix2= { "    ",
                            "    ",
                            "LLL ",
                            "L   "};

        String[] matrix3= { "    ",
                            "LL  ",
                            " L  ",
                            " L  "};

        String[] matrix4= { "    ",
                            "    ",
                            "  L ",
                            "LLL "};

        String[] matrix5= { "    ",
                            " L  ",
                            " L  ",
                            "LL  "};

        String[] matrix6= { "    ",
                            "    ",
                            "L   ",
                            "LLL "};

        String[] matrix7= { "    ",
                            "LL  ",
                            "L   ",
                            "L   "};
        
        String[] matrix8= { "    ",
                            "    ",
                            "LLL ",
                            "  L "};
        
        //variacion 1
        assertArrayEquals(l1.getMatrix(), matrix1 );

        l1.rotateRight();
        assertArrayEquals(l1.getMatrix(), matrix2 );

        l1.rotateRight();
        assertArrayEquals(l1.getMatrix(), matrix3 );

        l1.rotateRight();
        assertArrayEquals(l1.getMatrix(), matrix4 );

        l1.rotateLeft();
        assertArrayEquals(l1.getMatrix(), matrix3 );
        
        //variacion 2
        assertArrayEquals(l2.getMatrix(), matrix5 );

        l2.rotateRight();
        assertArrayEquals(l2.getMatrix(), matrix6 );

        l2.rotateRight();
        assertArrayEquals(l2.getMatrix(), matrix7 );

        l2.rotateRight();
        assertArrayEquals(l2.getMatrix(), matrix8 );

        l2.rotateLeft();
        assertArrayEquals(l2.getMatrix(), matrix7 );
    }

    @Test
    public void PieceSquare_test()
    {
        PieceSquare square= new PieceSquare(0,0);
        String[] matrix= {  "    ",
                            "    ",
                            "ZZ  ",
                            "ZZ  "};

        for(int i= 0; i < 5 ; i++){
            assertArrayEquals(square.getMatrix(), matrix );
            square.rotateRight();
        }
        
        for(int i= 0; i < 5 ; i++){
            assertArrayEquals(square.getMatrix(), matrix );
            square.rotateLeft();
        }
    }

    @Test
    public void PieceT_test()
    {
        PieceT t= new PieceT(0,0);
        String[] matrix1= { "    ",
                            "    ",
                            "TTT ",
                            " T  "};

        String[] matrix2= { "    ",
                            " T  ",
                            "TT  ",
                            " T  "};

        String[] matrix3= { "    ",
                            "    ",
                            " T  ",
                            "TTT "};

        String[] matrix4= { "    ",
                            "T   ",
                            "TT  ",
                            "T   "};

        assertArrayEquals(t.getMatrix(), matrix1 );

        t.rotateRight();
        assertArrayEquals(t.getMatrix(), matrix2 );

        t.rotateRight();
        assertArrayEquals(t.getMatrix(), matrix3 );

        t.rotateRight();
        assertArrayEquals(t.getMatrix(), matrix4 );

        t.rotateLeft();
        assertArrayEquals(t.getMatrix(), matrix3 );
    }

    @Test
    public void PieceStick_test()
    {
        PieceStick testS= new PieceStick(0);
        String[] matrix1= { "    ",
                            "    ",
                            "    ",
                            "SSSS"};

        String[] matrix2= { "S   ",
                            "S   ",
                            "S   ",
                            "S   "};

        assertArrayEquals(testS.getMatrix(), matrix1 );

        testS.rotateRight();

        assertArrayEquals(testS.getMatrix(), matrix2 );

        testS.rotateLeft();
        assertArrayEquals(testS.getMatrix(), matrix1 );
    }

    @Test
    public void rotation_range_test(){
        PieceL test= new PieceL(0,0);

        assertEquals(3,test.getMaxRotations());

        assertEquals(0,test.getRotation());
        test.rotateRight();
        assertEquals(1,test.getRotation());
        test.rotateRight();
        assertEquals(2,test.getRotation());
        test.rotateRight();
        assertEquals(3,test.getRotation());
        test.rotateRight();
        assertEquals(0,test.getRotation());
        test.rotateLeft();
        assertEquals(3,test.getRotation());
    }
}
