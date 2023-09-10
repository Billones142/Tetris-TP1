package com.tetris.game;

import java.util.ArrayList;

public class Board {
    public Board() {
        super();
        for (int i=0 ; i < 20 ; i++){
            matrix.add( "          ");
        }
    }

    // final: constante que no se puede modificar(inmutable)
    // static: todas las clases del mismo tipo acceden a la misma ubicacion
    private final static String EMPTY_STRING= "";
    private final static String SPACE_STRING= " ";
    private final static String SPACE_X4= "    ";
    private final static char SPACE_CHAR= ' ';
    private final static char X_CHAR= 'x';
    

    private ArrayList<String> matrix= new ArrayList<String>();
    private ArrayList<BasePiece> pieces= new ArrayList<BasePiece>();

    private int lastActivePieceIndex;
    private int lastActivePieceYLine;

    //************** Inicio encapsulacion **************//

    public String getMatrix(int index) { // accede a una fila de la matrix mediante un indice
        return matrix.get(index);
    }

    public ArrayList<String> getMatrix() {  // metodo para acceder a los metodos de ArrayList
        return this.matrix;
    }

    public void setMatrix(int index, String value) {
        this.matrix.remove(index);
        this.matrix.add(index,value);
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

    public int getLastActivePieceIndex() {
        return lastActivePieceIndex;
    }

    public void setLastActivePieceIndex(int lastActivePieceIndex) {
        this.lastActivePieceIndex = lastActivePieceIndex;
    }

    public int getLastActivePieceYLine() {
        return lastActivePieceYLine;
    }

    public void setLastActivePieceYLine(int lastActivePieceYLine) {
        this.lastActivePieceYLine = lastActivePieceYLine;
    }

    //************** Fin encapsulacion **************//

    public void addPiece(){
        switch ((int)(Math.random() * 5)) {
            case 0:
                addPiece(new PieceDog());
                break;

            case 1:
                addPiece(new PieceL());
                break;

            case 2:
                addPiece(new PieceSquare());
                break;

            case 3:
                addPiece(new PieceStick());
                break;

            case 4:
                addPiece(new PieceT());
                break;

        
            default:
                break;
        }
    }

    public void addPiece(BasePiece piece){
        int index= getPieces().size();
        setPieces(index, piece);;  //+1?
        lastActivePieceIndex= index;
        setNewPieceOnBoard(index);
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

    private void setNewPieceOnBoard(int index){
        setNewPieceOnBoard(index, (int)(Math.random() * (10 - countWidth(getPieces(index)))));
    }

    private void setNewPieceOnBoard(int index, int randomPositionX){     
        BasePiece piece= getPieces(index);
        int pieceHeight= countHeight(piece);
        int pieceWidth= countWidth(piece);
        String[] pieceMatrix= piece.getMatrix();


        for(int x= 0 ; x < pieceWidth ; x++){
            for(int y= pieceHeight; y > 0 ; y--){
                int randomPositionXLine= randomPositionX;
                if(pieceMatrix[y].charAt(x) != SPACE_CHAR){
                    randomPositionXLine++;
                }else{
                    changeStringRange(randomPositionXLine, this.getMatrix(y), "x");  // agrega la fila de la pieza activa
                }
            }
        }
        setLastActivePieceIndex(pieceHeight);
    }


    private boolean hasCollided(int index){
        ArrayList<Integer> pointOfPosibleColition= new ArrayList<Integer>();
        String upperString= this.getMatrix(index);

            for(int i= 0; i < 10 ;i++){
                if(upperString.charAt(i) == X_CHAR){
                    pointOfPosibleColition.add(i);
                }
            }

        return hasCollided(index, pointOfPosibleColition);
    }

    private boolean hasCollided(int index, ArrayList<Integer> pointOfPosibleColition){
            if(index + 1 > 19){
                return true;
            }
            String upperString= this.getMatrix(index);
            String lowerString= this.getMatrix(index+1);

            for(int i= 0; i < 10 ;i++){
                if(upperString.charAt(i) == X_CHAR){
                    pointOfPosibleColition.add(i);
                }
            }

            for(int i= 0; i < pointOfPosibleColition.size(); i++){
                if(lowerString.charAt(pointOfPosibleColition.get(i)) != X_CHAR){
                    return true;
                }
            }

            return false;
    }

    // returns true if colided
    public boolean moveDownActivePiece(){  //TODO: chequeo de colision

        BasePiece piece= getPieces(this.lastActivePieceIndex);
        String[] pieceMatrix= piece.getMatrix();
        int width= countWidth(piece);
        int height= countHeight(piece);
        int positionX= 0;

        if(hasCollided(getLastActivePieceYLine())){
            piece.collided();
            return true;
        }

        for(int y= getLastActivePieceYLine(); y < this.getMatrix().size() ;y++){ // borra todos los espacios donde esta la pieza activa

            String  lineaRemplazo= new String(),
                    lineaOriginal= this.getMatrix(y);

            for(int i= 0; i < lineaOriginal.length(); i++){
                if(lineaOriginal.charAt(i) != X_CHAR){
                    lineaRemplazo.concat(EMPTY_STRING+lineaOriginal.charAt(i));
                }else{
                    lineaRemplazo.concat(SPACE_STRING);
                    if(y == this.getMatrix().size()){
                        positionX= i;
                    }
                }
            }
        }

        for(int i= 0; i < 0 ; i++){  //vuelve a colocar la pieza activa
            for(int x= 0 ; x < width ; x++){
                for(int y= height; y > 0 ; y--){
                    int randomPositionXLine= positionX;
                    if(pieceMatrix[y].charAt(x) != SPACE_CHAR){
                        randomPositionXLine++;
                    }else{
                    changeStringRange(randomPositionXLine, this.getMatrix(y), "x");  // agrega la fila de la pieza activa
                    }
                }
            }
        }

        return false;
    }
    

}