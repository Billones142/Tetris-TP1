import static org.junit.Assert.*;

import org.junit.Test;

import tetris.juego.Board;

import java.util.ArrayList;

public class test {
    @Test
    public void pruebaArray(){
        ArrayList<String> hola= new ArrayList<String>();
        for(int i= 0; i < 10 ; i++){
            hola.add(""+i);
        }

        assertEquals( hola.get(1) , "1");
        hola.remove(1);
        assertEquals( hola.get(1) , "2");
        hola.add(1, "1");
        assertEquals( hola.get(1) , "1");
        

    }
}
