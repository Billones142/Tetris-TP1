package tetris.juego;

import java.util.Random;
import java.util.ArrayList;

public class Board {
    public Board() {
        super();
        for (int i=0 ; i < 20 ; i++){
            matrix.add(matrix.size(), "          ");  //+1?
        }
    }
    private static String EMPTY_STRING= "";
    private static String SPACE_X4= "    ";
    private static char SPACE_CHAR= ' ';
    

    private ArrayList<String> matrix;
    private ArrayList<BasePiece> pieces;

    private int lastActivePieceIndex;
    private int lastActivePieceYLine;

    public String getMatrix(int index) { // accede a una fila de la matrix mediante un indice
        return matrix.get(index);
    }

    public ArrayList<String> getMatrix() {  // metodo para acceder a los metodos de ArrayList
        return this.matrix;
    }

    private ArrayList<BasePiece> getPieces(){  // metodo para acceder a los metodos de ArrayList
        return this.pieces;
    }

    private BasePiece getPieces(int index){ // accede a una pieza de la matrix mediante un indice
        return this.pieces.get(index);
    }

    private void setPieces(int index, BasePiece piece) {  //
        this.pieces.set(index, piece);
    }

    public void addPiece(BasePiece piece){
        int index= getPieces().size();
        setPieces(index, piece);;  //+1?
        lastActivePieceIndex= index;
        setPieceOnBoard(index);
    }

    private int countHeight(BasePiece piece){
        int lineCount= 0;

        for(int i= 3; i > 0 ; i--){ // contar altura de la figura
            if(piece.getMatrix()[i] != SPACE_X4){
                lineCount++;
            }
        }

        return lineCount;
    }

    private int countWidth(BasePiece piece){
        int width= 0;

        for(int j= 3; j > 0 ; j--){  // contar anchura de la figura
            for(int i= 3; i > 0 ; i--){
                if(piece.getMatrix()[j].charAt(i) != SPACE_CHAR){
                    break;
                }else{
                    width++;
                }
            }
        }

        return width;
    }

    private String changeStringRange(int beginingIndex, String originalString, String addedString){ // modifica una parte de un String agregando otro, 
        if( originalString.length() - beginingIndex < addedString.length()){
            throw new ArrayIndexOutOfBoundsException("la cadena no entra en el lugar especificado");
        }

        char[] strArray = originalString.toCharArray();

        for(int x = 0; x < addedString.length(); x++) {
            strArray[x] = addedString.charAt(x);
        }

        return String.valueOf(strArray);
    }

    private void setPieceOnBoard(int index){     //AGREGAR DESPUES: chequear que no halla colision donde se colocan las piezas
        BasePiece piece= getPieces(index);
        int height= countHeight(piece);
        int width= countWidth(piece);
        String[] pieceMatrix= piece.getMatrix();
        int randomPositionX= (int)Math.random() * (10 - width);


        for(int x= 0 ; x < width ; x++){
            for(int y= height; y > 0 ; y--){
                int randomPositionXLine= randomPositionX;
                if(pieceMatrix[y].charAt(x) != ' '){
                    randomPositionXLine++;
                }else{
                    changeStringRange(randomPositionXLine, this.getMatrix(y), pieceMatrix[y]);  // agrega la fila de la pieza activa
                }
            }
        }
        lastActivePieceYLine= height;
    }

    public void moveDownActivePiece(){
        int pieceLinePosition;
        for(int y= this.getMatrix().size(); y < 0 ; y--){
            for(int x= 0 ; x < this.getMatrix(0).length(); x++){
                char actualChar= this.getMatrix(y).charAt(x);
                if(actualChar != ' '){
                    pieceLinePosition= y;
                }
            }
        }


    }

}
