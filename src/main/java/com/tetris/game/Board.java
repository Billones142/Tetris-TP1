package com.tetris.game;

import java.util.ArrayList;

public class Board {
    public Board() {
        super();
        matrix= new ArrayList<String>();
        pieces= new ArrayList<BasePiece>();
        for (int i=0 ; i < 20 ; i++){
            //matrix.add( "          ");
            matrix.add( SPACE_X10);
        }
    }

    // final: constante que no se puede modificar(inmutable)
    // static: todas las clases del mismo tipo acceden a la misma ubicacion
    private final static String EMPTY_STRING= "";
    private final static String SPACE_X4= "    ";
    private final static String X_X10= "XXXXXXXXXX";
    private final static char X_CHAR= 'x';
    private final static char MAYUS_X_CHAR= 'X';
    private final static char SPACE_CHAR= ' ';

    private final static String SPACE_STRING= "0";
    private final static String SPACE_X10= "0000000000";

    private ArrayList<String> matrix;
    private ArrayList<BasePiece> pieces;

    private int lastActivePieceYLine;
    private int lineasComletadas;
    private boolean nextToWall;

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

    public ArrayList<BasePiece> getPieces(){  // metodo para acceder a los metodos de ArrayList
        return this.pieces;
    }

    public BasePiece getPieces(int index){ // accede a una pieza de la matrix mediante un indice
        return pieces.get(index);
    }

    private void setPieces(BasePiece piece) {  //
        pieces.add(piece);;
    }

    public int getLastActivePieceIndex() {
        return getPieces().size() - 1;
    }

    public int getLastActivePieceYLine() {
        return lastActivePieceYLine;
    }

    public void setLastActivePieceYLine(int lastActivePieceYLine) {
        this.lastActivePieceYLine = lastActivePieceYLine;
    }

    private void setLineasComletadas(int lineasComletadas) {
        this.lineasComletadas = lineasComletadas;
    }

    public int getLineasComletadas() {
        return lineasComletadas;
    }

    private boolean getNextToWall(){
        return nextToWall;
    }

    public void setNextToWall(boolean nextToWall) {
        this.nextToWall = nextToWall;
    }

    //************** Fin encapsulacion **************//

    public void addPiece(){
        int randomNumber= (int)(Math.random() * 5);
        switch (randomNumber) {
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
        }
    }

    public void addPiece(BasePiece piece,int locationX){ //TODO:que no se pueda ejecutar si hay una pieza activa
        if(!noSpaceLeft()){
            setPieces(piece);
            setNextToWall(false);

            int locationY= countHeight(getPieces(getLastActivePieceIndex())) - 1;
            int[] location= {locationX, locationY};
            reWriteActivePiece(location);
        }
    }

    public void addPiece(BasePiece piece){ //TODO:que no se pueda ejecutar si hay una pieza activa
        if(!noSpaceLeft()){
            setPieces(piece);
            setNextToWall(false);

            int locationX= (int)(Math.random() * ( 10 - countWidth(getPieces(getLastActivePieceIndex() ))));
            System.out.println(locationX);
            int locationY= countHeight(getPieces(getLastActivePieceIndex())) - 1;
            int[] location= {locationX, locationY};
            reWriteActivePiece(location);
        }
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
        String[] pieceMatrix= piece.getMatrix();

        for(int x= 0; x < 4 ; x++){  // contar anchura de la figura
            for(int y= 0; y < 4 ; y++){
                if(pieceMatrix[y].charAt(x) != SPACE_CHAR){
                    width++;
                    break;
                }
            }
        }
        return width;
    }

    public String changeStringRange(int beginingIndex, String originalString, String addedString){ // modifica una parte de un String agregando otro, 
        StringBuilder original= new StringBuilder(originalString);

        original.replace(beginingIndex, addedString.length()+ beginingIndex, addedString);
        return original.toString();
    }

    private void eraseActivePiece(){
        for (int y = 0; y < getMatrix().size(); y++) {
            for (int x = 0; x < getMatrix(y).length(); x++) {
                if (getMatrix(y).charAt(x) == X_CHAR) {
                    setMatrix(y, changeStringRange(x, getMatrix(y), SPACE_STRING));
                }
            }
        }
    }

    // returns true if colided
    public boolean moveDownActivePiece(){
        BasePiece piece= getPieces(getLastActivePieceIndex());

        if(willCrash((byte)0)){  // si la pieza choca...
            piece.collided();
            for (int y = 0; y < getMatrix().size(); y++) { // cambia todos lo caracteres x por X
                for (int x = 0; x < getMatrix(y).length(); x++) {
                    if(getMatrix(y).charAt(x) == X_CHAR){
                        setMatrix(y, changeStringRange(x, getMatrix(y), EMPTY_STRING+MAYUS_X_CHAR));
                    }
                }
            }
            return true;
        }


        int[] location= getActivePieceLocation();
        location[1]++;

        reWriteActivePiece(location); // reescribir la pieza abajo

        contarLineasCompletas();
        return false;
    }
    
    public int[] getActivePieceLocation(){ //TODO: ver en caso que sea T
        int yPiecePosition= -1;
        int xPiecePosition= -1;

        for (int yIndex =  0; yIndex < getMatrix().size(); yIndex++) { // loop para ver la posicion de la pieza
            boolean undefinedY= yPiecePosition == -1;
            boolean lineContainsX= getMatrix(yIndex).trim().contains(("x"));
            if(undefinedY && lineContainsX){
                yPiecePosition= yIndex;
            }

            if (xPiecePosition == -1) {
                for (int xIndex = 0; xIndex < getMatrix(yIndex).length(); xIndex++) {
                    if(getMatrix(yIndex).charAt(xIndex) == X_CHAR){
                        xPiecePosition= xIndex;
                    }
                }
            }
        }
        
        int[] position= {xPiecePosition,yPiecePosition};
        return position;
    }

    private void reWriteActivePiece(int[] location){ //se usa para reescribir la pieza en caso de una rotacion o movimiento (no chequea si hubo colision)

        String[] pieceMatrix= getPieces(getLastActivePieceIndex()).getMatrix();
        int pieceHeight= countHeight(getPieces(getLastActivePieceIndex()));
        int pieceWidth= countWidth(getPieces(getLastActivePieceIndex()));
        eraseActivePiece();

        for (int yIndex = 3; yIndex >= 4-pieceHeight; yIndex--) {
            for (int xIndex = 0; xIndex < pieceWidth; xIndex++) {

                char charAtPosition= pieceMatrix[yIndex].charAt(xIndex);
                boolean charAtPositionIsNotSpace= charAtPosition != SPACE_CHAR;
                if (charAtPositionIsNotSpace) {
                    int matrixXIndex= xIndex+location[0];
                    int matrixYIndex= yIndex-pieceWidth+1+location[1];//TODO: arreglar
                    setMatrix(matrixYIndex, changeStringRange(matrixXIndex, getMatrix(matrixYIndex), EMPTY_STRING+X_CHAR));
                }
                
            }
        }
    }

    private boolean willCrash(byte movement){ // 0: abajo, 1:rotacion izquierda, 2: rotacion derecha
        BasePiece piece= getPieces(getLastActivePieceIndex());
        String[] pieceMatrix;
        int[] position= getActivePieceLocation();
        int pieceWidth;
        int pieceHeight;
        
        switch (movement) {
            case 0:
                boolean tocoElPiso= position[1] >= 19;
                if(tocoElPiso){
                return true;
                }

                ArrayList<Integer> pointOfPosibleColition= new ArrayList<Integer>();
                String upperString= this.getMatrix(getLastActivePieceIndex());

                String lowerString= this.getMatrix(getLastActivePieceIndex()+1);

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

                break;

            case 1:
                piece.rotateLeft();
                pieceMatrix= piece.getMatrix();
                pieceWidth= countWidth(piece);
                pieceHeight= countHeight(piece);

                if(10-position[0] < pieceWidth){ // toca la pared si gira?
                    piece.rotateRight();
                    return true;
                }

                for (int y = position[1]; y < position[1]-pieceWidth; y++) {
                    for (int x = position[0]; x < position[0]-pieceHeight; x++) {
                        if(pieceMatrix[y-position[1]].charAt(x-position[0]) == X_CHAR && getMatrix(y).charAt(x) == MAYUS_X_CHAR){
                            return true;
                        }
                    }
                }

                piece.rotateRight();
                break;

            case 2:
                piece.rotateRight();
                pieceMatrix= piece.getMatrix();
                pieceWidth= countWidth(piece);
                pieceHeight= countHeight(piece);

                if(10-position[0] < pieceWidth){ // toca la pared si gira?
                    piece.rotateLeft();
                    return true;
                }

                for (int y = position[1]; y < position[1]-pieceWidth; y++) {
                    for (int x = position[0]; x < position[0]-pieceHeight; x++) {
                        if(pieceMatrix[y-position[1]].charAt(x-position[0]) == X_CHAR && getMatrix(y).charAt(x) == MAYUS_X_CHAR){
                            return true;
                        }
                    }
                }

                piece.rotateLeft();
                break;
        }
        return false;
    }

    public void turnActivePieceLeft(){
        int[] location= getActivePieceLocation();
        if (!willCrash((byte)1)) {
            getPieces(getLastActivePieceIndex()).rotateLeft();
            if(getNextToWall()){
                int newWidth= countWidth(getPieces(getLastActivePieceIndex()));
                getPieces(getLastActivePieceIndex()).rotateLeft();
                location[0]-= newWidth - countWidth(getPieces(getLastActivePieceIndex()));
            }
            reWriteActivePiece(location);
        }
    }

    public void turnActivePieceRight(){
        int[] location= getActivePieceLocation();
        if (!willCrash((byte)2)) {
            getPieces(getLastActivePieceIndex()).rotateRight();
            if(getNextToWall()){
                int newWidth= countWidth(getPieces(getLastActivePieceIndex()));
                getPieces(getLastActivePieceIndex()).rotateLeft();
                location[0]-= newWidth - countWidth(getPieces(getLastActivePieceIndex()));
                getPieces(getLastActivePieceIndex()).rotateRight();
            }
            reWriteActivePiece(location);
        }
    }

    public void contarLineasCompletas(){
        for (int i= 0; i < getMatrix().size() ; i++) {
            if(getMatrix(i) == X_X10){
                getMatrix().remove(i);
                getMatrix().add(0,SPACE_X10);
                setLineasComletadas(getLineasComletadas()+1);
            }
        }
    }

    public boolean noSpaceLeft() {
        for (int i = 0; i < 4; i++) {
            if(getMatrix(i) != SPACE_X10){
                return true;
            }
        }
        return false;
    }

    public void printBoard(){
        for (int i = 0; i < getMatrix().size(); i++) {
            System.out.println(getMatrix(i));
        }
    }

    public boolean pieceActiveOnBoard(){ //TODO: implementar: addPiece, MoveDowm, ...
        for (int yIndex = 0; yIndex < getMatrix().size(); yIndex++) {
            boolean hayXActivaEnLaLinea= getMatrix(yIndex).trim().contains("x");
            if(hayXActivaEnLaLinea){
                return true;
            }
        }
        return false;
    }
}