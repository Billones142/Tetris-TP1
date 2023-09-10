package com.tetris.game;

public class PieceDog extends BasePiece{

    public PieceDog() {
        super();
        Math.random();
        setMaxRotations(2);
        setMaxVariations(1);
        setRotation((int)(Math.random() * this.getMaxRotations()));
        setVariation((int)(Math.random() * this.getMaxVariations()));
    }
    
    public PieceDog(int rotation,int variation) {
        super();
        setMaxRotations(2);
        setMaxVariations(1);
        setRotation(rotation);
        setVariation(variation);

    }

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        switch (this.getRotation()) {
            case 0:
                value= matrixHorizontal[getVariation()];
                break;
        
            case 1:
                value= matrixVertical[getVariation()];
                break;
            default:
                break;
        }
        return value;
        }

    final static String[][] matrixHorizontal= {{"    ",
                                                "    ",
                                                " DD ",
                                                "DD  "
                                                },{
                                                "    ",
                                                "    ",
                                                "DD  ",
                                                " DD ",
    }
    };

    final static String[][] matrixVertical=   {{"    ",
                                                "D   ",
                                                "DD  ",
                                                " D  "
                                                },{
                                                "    ",
                                                " D  ",
                                                "DD  ",
                                                "D   "}
    };
}