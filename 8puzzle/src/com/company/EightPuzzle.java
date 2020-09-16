package com.company;

public class EightPuzzle {
    //Atr
    private static EightPuzzle instance;

    //Cons
    public EightPuzzle() {
        instance = this;
    }

    //Met
    public EightPuzzle getInstance() {
        if (instance == null) {
            instance = new EightPuzzle();
            return instance;
        } else {
            return instance;
        }
    }

    public void resolve(Node node) {

    }


}
