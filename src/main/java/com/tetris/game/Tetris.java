package com.tetris.game;

public class Tetris {
    public Clock clock = new Clock();
    public Board board = new Board();
    private final int cantLineasParaGanar;

    public Tetris(int lineasParaGanar) {
        super();
        cantLineasParaGanar= lineasParaGanar;
    }

    private boolean gameLost= false;


    private boolean getGameLost(){
        return gameLost;
    }

    private void setGameLost(boolean finished) {
        this.gameLost = finished;
    }

    public int getCantLineasParaGanar() {
        return cantLineasParaGanar;
    }

    public void start(){
        tick();
    }

    public String state(){
        if(board.getLineasComletadas() >= getCantLineasParaGanar()){
            return "juego ganado";
        }else if(getGameLost()){
            return "juego perdido";
        }else if(clock.getTicks() == 0){
            return "juego no iniciado";
        }
        return "juego en curso";
    }

    public void rotateLeft(){
        board.turnActivePieceLeft();
    }

    public void rotateRight(){
        board.turnActivePieceRight();
    }

    public void tick(){ 
        clock.tick();
        if (board.getLineasComletadas() < getCantLineasParaGanar()) {
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