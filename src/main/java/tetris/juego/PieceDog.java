package tetris.juego;

public class PieceDog extends BasePiece{
    int variation;
    
    public PieceDog(int rotation,int variation) {
        super();
        setMaxRotations(2);
        setRotation(rotation);
        setVariation(variation);

    }
    
    public void setVariation(int variation) {
        this.variation = variation;
    }

    public int getVariation() {
        return variation;
    }

    @Override
    public String[] getMatrix(){
        String[] value= {"",""};

        if(getRotation()>1){
            setRotation(0);
        }

        if(getRotation()<0){
            setRotation(0);
        }

        switch (this.getRotation()) {
            case 0:
                value= matrixHorizontal[this.variation];
                break;
        
            case 1:
                value= matrixVertical[this.variation];
                break;
            default:
                break;
        }
        return value;
        }

    String[][] matrixHorizontal= {{ "    ",
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

    String[][] matrixVertical={{"    ",
                                "D   ",
                                "DD  ",
                                " D  "
                                },{
                                "    ",
                                " D  ",
                                "DD  ",
                                "D   "
                                }
    };
}
