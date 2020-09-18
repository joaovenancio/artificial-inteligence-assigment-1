package com.company;

import java.util.Stack;

public class EightPuzzle {
    //Atr
    private static EightPuzzle instance;

    //Cons
    public EightPuzzle() {
        instance = this;
    }

    //Met
    public static EightPuzzle getInstance() {
        if (instance == null) {
            instance = new EightPuzzle();
            return instance;
        } else {
            return instance;
        }
    }

    public void resolve(Node node) {

    }

    public void test () {
        Tree tree = new Tree(new int[][]{{4,7,3}, {8,2,5}, {1,6,0}});
        int[][] test = tree.getRoot().copyState(tree.getRoot().getState());

        Stack stack = tree.getRoot().generateChilds();

        //Print node structure:
        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(test[line][column]);
            }
            System.out.println();
        }

        //tree.getRoot().findNullLocation();
        int stackSize = stack.size();

        for (int i = 0; i < stackSize; i++) {
            Node node = (Node) stack.pop();
            System.out.println();
            System.out.println();

            for (int line = 0; line < 3; line++) {
                for (int column = 0; column < 3; column++) {
                    System.out.print(node.getState()[line][column]);
                }
                System.out.println();
            }

        }


    }


}
