package tetris.juego;

public class PieceDog extends BasePiece{
    int variation;
    
    public PieceDog() {
        super();
        setVariation(0);

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

        if(getState()>1){
            setState(0);
        }

        switch (this.getState()) {
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

    String[][] matrixVertical={{   "    ",
                                "D   ",
                                "DD  ",
                                " D  "
                                },{
                                " D  ",
                                "DD  ",
                                "D   ",
                                "    "
                                }
    };
}
