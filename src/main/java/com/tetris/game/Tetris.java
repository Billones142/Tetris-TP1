package com.tetris.game;

public class Tetris {
    public Clock clock = new Clock();
    public Board board = new Board();
    private final int cantLineasParaGanar;

    public Tetris(int lineasParaGanar) {
        super();
        cantLineasParaGanar= lineasParaGanar;
    }

    private boolean gameStarted= false;
    private boolean gameLost= false;
    private boolean gameWinned= false;


//              Inicio encapsulamiento              //

    private boolean getGameLost(){
        return gameLost;
    }

    private void setGameLost(boolean finished) {
        this.gameLost = finished;
    }

    public void setGameWinned(boolean gameWinned) {
        this.gameWinned = gameWinned;
    }

    private boolean getGameWinned(){
        return gameWinned;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    private boolean getGameStarted(){
        return gameStarted;
    }

//              Fin encapsulamiento              //

    public int getCantLineasParaGanar() {
        return cantLineasParaGanar;
    }

    public void start(){
        setGameStarted(true);
        tick();
    }

    public String state(){
        if(board.getLineCount() >= getCantLineasParaGanar()){
            setGameWinned(true);
            return "juego ganado";
        }else if(getGameLost()){
            return "juego perdido";
        }else if(clock.getTicks() == 0){
            return "juego no iniciado";
        }
        return "juego en curso";
    }

    public boolean rotateLeft(){
        return board.turnActivePieceLeft();
    }

    public boolean rotateRight(){
        return board.turnActivePieceRight();
    }

    public void tick(){ 
        clock.tick();
        if(getGameWinned() || getGameLost() || !getGameStarted()){
            return;
        }
        if (board.getLineCount() < getCantLineasParaGanar()) {
            if(board.moveDownActivePiece()){
                if(board.noSpaceLeft()){
                    setGameLost(true);
                }else{
                    board.addPiece();
                }
            }
        }
    }
}