package com.company;

import java.util.ArrayList;

public class Tree {
    //Atr
    private Node root;

    //Cons
    public Tree(int[][] initialState) {
        this.root = new Node(initialState, null);
    }

    //Met
    public Node getRoot() {
        return root;
    }
}
