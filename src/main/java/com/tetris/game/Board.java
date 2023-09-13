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
    private int lineCount;
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

    private void setLineCount(int lineasComletadas) {
        this.lineCount = lineasComletadas;
    }

    public int getLineCount() {
        return lineCount;
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

    public void addPiece(BasePiece piece,int locationX){
        boolean spaceLeft= !noSpaceLeft();
        boolean pieceAlreadyActive= !pieceActiveOnBoard();

        if(spaceLeft && pieceAlreadyActive){
            setPieces(piece);

            int locationY= countHeight(getPieces(getLastActivePieceIndex())) - 1;
            int[] location= {locationX, locationY};
            reWriteActivePiece(location);
        }
    }

    public void addPiece(BasePiece piece){
        if(!noSpaceLeft() && !pieceActiveOnBoard()){
            setPieces(piece);

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
        if(!pieceActiveOnBoard()){
            return true;
        }

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
            contarLineCount();
            return true;
        }

        
        int[] location= getActivePieceLocation();
        location[1]++;

        reWriteActivePiece(location); // reescribir la pieza abajo

        contarLineCount();
        return false;
        
    }
    
    public int[] getActivePieceLocation(){
        int yPiecePosition= -1;
        int xPiecePosition= -1;

        for (int yIndex =  getMatrix().size() - 1; yIndex >= 0; yIndex--) { // loop para ver la posicion y de la pieza
            boolean undefinedY= yPiecePosition == -1;
            boolean lineContainsX= getMatrix(yIndex).trim().contains(("x"));
            if(undefinedY && lineContainsX){
                yPiecePosition= yIndex;
            }
        }

        for (int xIndex = 0; xIndex < 10; xIndex++) { // loop para ver la posicion x de la pieza
            for (int yIndex = 0; yIndex < 20; yIndex++) {
                boolean positionIsX= getMatrix(yIndex).charAt(xIndex) == X_CHAR;
                boolean xPiecePositionUndefined= xPiecePosition == -1;
                if ( xPiecePositionUndefined && positionIsX) {
                    xPiecePosition= xIndex;
                }
            }
        }
        
        int[] position= {xPiecePosition,yPiecePosition};
        return position;
    }

    private void reWriteActivePiece(int[] location){ //se usa para reescribir la pieza en caso de una rotacion o movimiento (no chequea si hubo colision)

        String[] pieceMatrix= getPieces(getLastActivePieceIndex()).getMatrix();
        int pieceWidth= countWidth(getPieces(getLastActivePieceIndex()));
        eraseActivePiece();

        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 0; xIndex < pieceWidth; xIndex++) {

                char charAtPosition= pieceMatrix[yIndex].charAt(xIndex);
                boolean charAtPositionIsNotSpace= charAtPosition != SPACE_CHAR;
                if (charAtPositionIsNotSpace) {
                    int matrixXIndex= location[0] + xIndex;
                    int matrixYIndex= location[1] + (yIndex - 3);
                    setMatrix(matrixYIndex, changeStringRange(matrixXIndex, getMatrix(matrixYIndex), EMPTY_STRING+X_CHAR));
                }
                
            }
        }
    }

    private boolean willCrash(byte movement){ // 0: abajo, 1:rotacion izquierda, 2: rotacion derecha
        if(!pieceActiveOnBoard()){
            return false;
        }

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
                int positionY= getActivePieceLocation()[1];
                String upperString= this.getMatrix(positionY);

                String lowerString= this.getMatrix(positionY+1);

                for(int i= 0; i < 10 ;i++){
                    if(upperString.charAt(i) == X_CHAR){
                        pointOfPosibleColition.add(i);
                    }
                }

                for(int i= 0; i < pointOfPosibleColition.size(); i++){
                    char charAtPoint= lowerString.charAt(pointOfPosibleColition.get(i));
                    boolean sameAsX= charAtPoint == MAYUS_X_CHAR; 
                    if(sameAsX){
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

    private boolean activePieceWillTouchWall(){
        int piecePositionX= getActivePieceLocation()[0];
        int pieceWidth= countWidth(getPieces(getLastActivePieceIndex()));
        if((piecePositionX + pieceWidth) > 9){
            return true;
        }
        return false;
    }

    public void turnActivePieceLeft(){ //TODO: que no gire si esta al lado de la pared
        int[] location= getActivePieceLocation();
        if (!willCrash((byte)1)) {
            getPieces(getLastActivePieceIndex()).rotateLeft();
            if(activePieceWillTouchWall()){ // si al girar toca la pared volver a dar vuelta la pieza
                getPieces(getLastActivePieceIndex()).rotateRight();
            }else{
                reWriteActivePiece(location);
            }
        }
    }

    public void turnActivePieceRight(){ //TODO: que no gire si esta al lado de la pared
        int[] location= getActivePieceLocation();
        if (!willCrash((byte)1)) {
            getPieces(getLastActivePieceIndex()).rotateRight();
            if(activePieceWillTouchWall()){ // si al girar toca la pared volver a dar vuelta la pieza
                getPieces(getLastActivePieceIndex()).rotateLeft();
            }else{
                reWriteActivePiece(location);
            }
        }
    }

    public void contarLineCount(){
        for (int yIndex= 0; yIndex < getMatrix().size() ; yIndex++) {
            String matrixAtILine= getMatrix(yIndex);
            boolean lineComplete= true;
            for (int xIndex = 0; xIndex < getMatrix(yIndex).length(); xIndex++) {
                if(matrixAtILine.charAt(xIndex) != MAYUS_X_CHAR){
                    lineComplete= false;
                }
            }
            if(lineComplete){
                getMatrix().remove(yIndex);
                getMatrix().add(0,SPACE_X10);
                setLineCount(getLineCount()+1);
            }
        }
    }

    public boolean noSpaceLeft() {
        for (int yIndex = 0; yIndex < 4; yIndex++) {
            for (int xIndex = 0; xIndex < getMatrix(yIndex).length(); xIndex++) {
                if(getMatrix(yIndex).charAt(xIndex) != '0'){
                    return true;
                }
            }
        }

        return false;
    }

    public void printBoard(){
        for (int i = 0; i < getMatrix().size(); i++) {
            System.out.println(getMatrix(i));
        }
    }

    public boolean pieceActiveOnBoard(){
        for (int yIndex = 0; yIndex < getMatrix().size(); yIndex++) {
            boolean hayXActivaEnLaLinea= getMatrix(yIndex).trim().contains(EMPTY_STRING+X_CHAR);
            if(hayXActivaEnLaLinea){
                return true;
            }
        }
        return false;
    }
}