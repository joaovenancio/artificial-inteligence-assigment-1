package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class EightPuzzle {
    //Atr
    private static EightPuzzle instance;
    private ArrayList<Node> openNodes;
    private ArrayList<Node> visitedNodes;

    //Cons
    public EightPuzzle() {
        instance = this;
        this.openNodes = new ArrayList<Node>();
        this.visitedNodes = new ArrayList<Node>();
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

    public void resolveQ1(int[][] initialState) {
        //Re-initialize control lists:
        this.openNodes = new ArrayList<Node>();
        this.visitedNodes = new ArrayList<Node>();
        //Create new tree
        Tree tree = new Tree(initialState);
        //Initialize control variables:
        boolean isObjectiveNodeFound = false;
        //Start openNodes list:
        this.openNodes.add(tree.getRoot());
        //Start the search method:
        while (!isObjectiveNodeFound) {
            //Initialize control variables:
            boolean nodeAlreadyVisited = false;
            //Get the first node
            Node auxNode = this.openNodes.get(0);
            if (auxNode != null) {
                this.openNodes.remove(auxNode);
                //See if this node was already traveled by the algorithm:
                if (this.visitedNodes.size() > 0) {
                    for (Node visitedNode : this.visitedNodes) {
                        if (visitedNode.isSameState(auxNode.getState())) {
                            nodeAlreadyVisited = true;
                            break;
                        }
                    }
                }
                //Check if node is the objective node:
                if (!nodeAlreadyVisited) {
                    if (!(auxNode.isObjectiveNode())) {
                        this.visitedNodes.add(auxNode);
                        Stack<Node> generatedChilds = auxNode.generateChilds();
                        int stackLenght = generatedChilds.size();
                        for (int i = 0; i < stackLenght; i++) {
                            this.openNodes.add(generatedChilds.pop());
                        }
                    } else { //If it is, return everything that the assigment wants:
                        isObjectiveNodeFound = true;
                        System.out.println("Achou");
                        System.out.println(auxNode.getLevel());

                        for (int line = 0; line < 3; line++) {
                            for (int column = 0; column < 3; column++) {
                                System.out.print(auxNode.getState()[line][column]);
                            }
                            System.out.println();
                        }

                    }
                } else {
                    continue;
                }


            }
        }

        return;

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

        Tree tree2 = new Tree(new int[][]{{1,2,3}, {4,5,6}, {7,8,0}});
        System.out.println();
        System.out.println(tree.getRoot().isObjectiveNode());
        System.out.println(tree2.getRoot().isObjectiveNode());

    }


}
