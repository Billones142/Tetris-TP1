package com.tetris.game;

public class PieceL extends BasePiece{
    public PieceL() {
        super();
        Math.random();
        setMaxRotations(4);
        setMaxVariations(2);
        setRotation((int)(Math.random() * this.getMaxRotations()));
        setVariation((int)(Math.random() * this.getMaxVariations()));
    }

    public PieceL(int rotation,int variation) {
        super();
        setMaxRotations(4);
        setMaxVariations(2);
        setRotation(rotation);
        setVariation(variation);
    };

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        switch (this.getRotation()) {
            case 0:
                value= matrixUp[getVariation()];
                break;
        
            case 1:
                value= matrixRight[getVariation()];
                break;
            case 2:
                value= matrixDown[getVariation()];
                break;
            case 3:
                value= matrixLeft[getVariation()];
                break;
            default:
                break;
        }
        return value;
        }

    final static String[][] matrixUp= {{"    ",
                                        "L   ",
                                        "L   ",
                                        "LL  "
                                        },{
                                        "    ",
                                        " L  ",
                                        " L  ",
                                        "LL  "}};
    
    final static String[][] matrixRight=  {{"    ",
                                            "    ",
                                            "LLL ",
                                            "L   "
                                            },{
                                            "    ",
                                            "    ",
                                            "L   ",
                                            "LLL "}};
    
    final static String[][] matrixDown=   {{"    ",
                                            "LL  ",
                                            " L  ",
                                            " L  "
                                            },{
                                            "    ",
                                            "LL  ",
                                            "L   ",
                                            "L   "}};
    
    final static String[][] matrixLeft=   {{"    ",
                                            "    ",
                                            "  L ",
                                            "LLL "
                                            },{
                                            "    ",
                                            "    ",
                                            "LLL ",
                                            "  L "}};
}