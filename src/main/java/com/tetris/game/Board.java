package com.tetris.game;

import java.util.ArrayList;

public class Board {
    public Board() {
        super();
        matrix= new ArrayList<String>();
        pieces= new ArrayList<BasePiece>();
        for (int i=0 ; i < 20 ; i++){
            matrix.add( "          ");
        }
    }

    // final: constante que no se puede modificar(inmutable)
    // static: todas las clases del mismo tipo acceden a la misma ubicacion
    private final static String EMPTY_STRING= "";
    private final static String SPACE_STRING= " ";
    private final static String SPACE_X4= "    ";
    private final static String X_X10= "XXXXXXXXXX";
    private final static String SPACE_X10= "          ";
    private final static char SPACE_CHAR= ' ';
    private final static char X_CHAR= 'x';
    private final static char MAYUS_X_CHAR= 'X';
    

    private ArrayList<String> matrix;
    private ArrayList<BasePiece> pieces;

    private int lastActivePieceIndex;
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
        return this.pieces.get(index);
    }

    private void setPieces(BasePiece piece) {  //
        pieces.add(piece);;
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
        if(!noSpaceLeft()){
            int index= getPieces().size();
            setPieces(piece);
            setLastActivePieceIndex(index);
            setNextToWall(false);
            setNewPieceOnBoard(index);
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

    public String changeStringRange(int beginingIndex, String originalString, String addedString){ // modifica una parte de un String agregando otro, 
        StringBuilder original= new StringBuilder(originalString);

        original.replace(beginingIndex, addedString.length()+ beginingIndex, addedString);
        return original.toString();
    }

    public void setNewPieceOnBoard(int index){
        setNewPieceOnBoard(index, (int)(Math.random() * (10 - countWidth(getPieces(index)))));
    }

    private void setNewPieceOnBoard(int index, int randomPositionX){     
        BasePiece piece= getPieces(index);
        int pieceHeight= countHeight(piece);
        int pieceWidth= countWidth(piece);
        String[] pieceMatrix= piece.getMatrix();

        if(getMatrix(0).length()-getActivePieceLocation()[1] < pieceWidth){
            setNextToWall(true);
        }

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
        contarLineasCompletas();
    }

    

    public int eraseActivePiece(){
        int positionX= 0;

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
        
        return positionX;
    }

    // returns true if colided
    public boolean moveDownActivePiece(){

        BasePiece piece= getPieces(this.lastActivePieceIndex);
        String[] pieceMatrix= piece.getMatrix();
        int width= countWidth(piece);
        int height= countHeight(piece);
        int positionX;

        if(willCrash((byte)0)){
            piece.collided();
            for (int y = 0; y < getMatrix().size(); y++) {
                for (int x = 0; x < getMatrix(y).length(); x++) {
                    if(getMatrix(y).charAt(x) == X_CHAR){
                        setMatrix(y, changeStringRange(x, getMatrix(y), EMPTY_STRING+MAYUS_X_CHAR));
                    }
                }
            }
            return true;
        }

        positionX= eraseActivePiece();

        for(int y= getLastActivePieceYLine(); y < this.getMatrix().size() ;y++){ // borra todos los espacios donde esta la pieza activa

        int[] position= getActivePieceLocation();
        position[1]++;
        reWriteActivePiece(position);
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

        contarLineasCompletas();;
        return false;
    }
    
    private int[] getActivePieceLocation(){
        int yPiecePosition= -1;
        int xPiecePosition= -1;

        for (int y = getMatrix().size(); y > 0; y++) { // loop para ver la posicion de la pieza
            if(yPiecePosition == -1 && getMatrix(y).trim().contains((EMPTY_STRING+X_CHAR)) ){
                yPiecePosition= y;
            }
            for (int x = 0; x < getMatrix(y).length(); x++) {
                if (xPiecePosition == -1) {
                    xPiecePosition= x;
                }
            }
        }
        int[] position= {xPiecePosition,yPiecePosition};
        return position;
    }

    private void reWriteActivePiece(int[] location){ //se usa para reescribir la pieza en caso de una rotacion o movimiento (no chequea si hubo colision)

        String[] pieceMatrix= getPieces(lastActivePieceIndex).getMatrix();
        int pieceHeight= countHeight(getPieces(lastActivePieceIndex));
        int pieceWidth= countWidth(getPieces(lastActivePieceIndex));
        eraseActivePiece(); // hace que no esté repetida la pieza, por lo que siempre tiene que hacerce a lo ultimo

        for (int y = location[1]; y > location[1]+pieceHeight; y++) {
            for (int x = location[0]; x < location[0]+pieceWidth; x++) {
                setMatrix(y, changeStringRange(x, getMatrix(y), EMPTY_STRING+pieceMatrix[y].charAt(x)));
            }
        }
    }

    private boolean willCrash(byte rotate){ // 0: abajo, 1:rotacion izquierda, 2: rotacion derecha
        BasePiece piece= getPieces(lastActivePieceIndex);
        String[] pieceMatrix;
        int[] position= getActivePieceLocation();
        int pieceWidth;
        int pieceHeight;
        
        switch (rotate) {
            case 0:
                if(position[1] + 1 > 19){
                return true;
                }

                ArrayList<Integer> pointOfPosibleColition= new ArrayList<Integer>();
                String upperString= this.getMatrix(lastActivePieceIndex);

                String lowerString= this.getMatrix(lastActivePieceIndex+1);

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
            getPieces(lastActivePieceIndex).rotateLeft();
            if(getNextToWall()){
                int newWidth= countWidth(getPieces(lastActivePieceIndex));
                getPieces(lastActivePieceIndex).rotateLeft();
                location[0]-= newWidth - countWidth(getPieces(lastActivePieceIndex));
            }
            reWriteActivePiece(location);
        }
    }

    public void turnActivePieceRight(){
        int[] location= getActivePieceLocation();
        if (!willCrash((byte)2)) {
            getPieces(lastActivePieceIndex).rotateRight();
            if(getNextToWall()){
                int newWidth= countWidth(getPieces(lastActivePieceIndex));
                getPieces(lastActivePieceIndex).rotateLeft();
                location[0]-= newWidth - countWidth(getPieces(lastActivePieceIndex));
                getPieces(lastActivePieceIndex).rotateRight();
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
            if(getMatrix(i) == SPACE_X10){
                return true;
            }
        }
        return false;
    }
}