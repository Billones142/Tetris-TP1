package com.tetris.game;


public class Clock {
    private int ticks;
    private long startTime;

    public int getTicks() {
        return ticks;
    }

    private void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public Long getTimeMiliseconds(){
        return startTime - System.currentTimeMillis();
    }

    public void tick(){
        setTicks(getTicks()+1);
    }
}
