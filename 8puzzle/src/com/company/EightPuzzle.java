package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class EightPuzzle {
    //Atr
    private static EightPuzzle instance;
    private ArrayList<Node> openNodes;
    private ArrayList<Node> visitedNodes;
    private int maxFrontierSize;
    private int visitedNodesTotal = 0;

    //Cons
    public EightPuzzle() {
        instance = this;
        this.openNodes = new ArrayList<Node>();
        this.visitedNodes = new ArrayList<Node>();
        this.maxFrontierSize = 1;
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
        //Check if is an solvable matrix:
        boolean isSolvable = false;
        while (!isSolvable) {
            if (tree.getRoot().isSolvable()) {
                isSolvable = true;
            } else {
                System.out.println("Gerando nova matriz...");
                tree.setRoot(new Node(Node.randomBoard(), null));
            }
        }
        //Initialize control variables:
        boolean isObjectiveNodeFound = false;
        //Start openNodes list:
        this.openNodes.add(tree.getRoot());
        //Start the search method:
        while (!isObjectiveNodeFound) {
            //Initialize control variables:
            boolean nodeAlreadyVisited = false;
            //Get the first node
            Node auxNode;
            try {
                auxNode = this.openNodes.get(0);
            } catch (java.lang.IndexOutOfBoundsException ex) {
                auxNode = null;
            }
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
                        if (visitedNodes.size() == 181400) {
                            visitedNodesTotal = visitedNodesTotal + 181400;
                            this.visitedNodes = new ArrayList<Node>();
                            System.gc();
                        }
                        this.visitedNodes.add(auxNode);
                        Stack<Node> generatedChildren = auxNode.generateChildren();
                        int stackLenght = generatedChildren.size();
                        for (int i = 0; i < stackLenght; i++) {
                            Node popedChild = generatedChildren.pop();
                            this.openNodes.add(popedChild);
                            //System.out.println(popedChild.getLevel()); //Prints the level, for debugging
                        }
                        //Compare to find the largest frontier size:
                        if (this.maxFrontierSize < this.openNodes.size()) {
                            this.maxFrontierSize = this.openNodes.size();
                        }
                    } else { //If it is, return everything that the assigment wants:
                        isObjectiveNodeFound = true;
                        System.out.println("########################################################################");
                        System.out.println("#                               8Puzzle                                #");
                        System.out.println("########################################################################");
                        System.out.println("O total de nodos visitados: " + this.visitedNodes.size());
                        System.out.println("O total de nodos criados: " + (this.visitedNodes.size() + this.openNodes.size()));
                        System.out.println("O maior tamanho da fronteira durante a busca: " + this.maxFrontierSize);
                        System.out.println("O tamanho do caminho: " + auxNode.getLevel());
                        System.out.println("########################################################################");
                        System.out.println("Caminho percorrido:");
                        System.out.println();
                        auxNode.printPathway();
                        System.out.println("########################################################################");
                    }
                } else {
                    continue;
                }


            } else { //If there is no one
                System.out.println("########################################################################");
                System.out.println("#                               8Puzzle                                #");
                System.out.println("########################################################################");
                System.out.println("Erro: matriz inicial insolucionável.");
                System.out.println("O total de nodos visitados: " + this.visitedNodes.size());
                System.out.println("O total de nodos criados: " + (this.visitedNodes.size() + this.openNodes.size()));
                System.out.println("O maior tamanho da fronteira durante a busca: " + this.maxFrontierSize);
                System.out.println("Matriz inicial:");
                System.out.println();
                tree.getRoot().printNode();
                System.out.println("########################################################################");

                return;
            }
        }

        return;

    }

    public void resolveQ2(int[][] initialState) {
        //Re-initialize control lists:
        this.openNodes = new ArrayList<Node>();
        this.visitedNodes = new ArrayList<Node>();
        //Create new tree
        Tree tree = new Tree(initialState);
        //Check if is an solvable matrix:
        boolean isSolvable = false;
        while (!isSolvable) {
            if (tree.getRoot().isSolvable()) {
                isSolvable = true;
            } else {
                System.out.println("Gerando nova matriz...");
                tree.setRoot(new Node(Node.randomBoard(), null));
            }
        }
        //Initialize control variables:
        boolean isObjectiveNodeFound = false;
        //Start openNodes list:
        this.openNodes.add(tree.getRoot());
        //Start the search method:
        while (!isObjectiveNodeFound) {
            //Initialize control variables:
            boolean nodeAlreadyVisited = false;
            //Get the first node
            Node auxNode;
            try {
                auxNode = this.openNodes.get(0);
            } catch (java.lang.IndexOutOfBoundsException ex) {
                auxNode = null;
            }
            if (auxNode != null) {
                this.openNodes.remove(auxNode);
                //See if this node was already visited by the algorithm:
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
                        Stack<Node> generatedChildren = auxNode.generateChildren();
                        int stackLenght = generatedChildren.size();
                        for (int i = 0; i < stackLenght; i++) {
                            Node popedChild = generatedChildren.pop();
                            int poppedChildCost = popedChild.calculateHeuristic1();
                            //Sort open nodes list:
                            boolean alreadyAdded = false;
                            if (this.openNodes.size() == 0) {
                                this.openNodes.add(popedChild);
                                //System.out.println(popedChild.getCost() + "passou");
                            } else {
                                for (int auxIndex = 0; auxIndex < this.openNodes.size(); auxIndex++) {
                                    if (poppedChildCost >= this.openNodes.get(auxIndex).getCost()){
                                        this.openNodes.add(auxIndex, popedChild);
                                        alreadyAdded = true;
                                        break;
                                    }
                                }
                                if (!alreadyAdded) {
                                    this.openNodes.add(popedChild);
                                }
                            }

                            //System.out.println(popedChild.getLevel()); //Prints the level, for debugging
                        }
                        //Compare to find the largest frontier size:
                        if (this.maxFrontierSize < this.openNodes.size()) {
                            this.maxFrontierSize = this.openNodes.size();
                        }
                    } else { //If it is, return everything that the assigment wants:
                        isObjectiveNodeFound = true;
                        System.out.println("########################################################################");
                        System.out.println("#                               8Puzzle                                #");
                        System.out.println("########################################################################");
                        System.out.println("O total de nodos visitados: " + this.visitedNodes.size());
                        System.out.println("O total de nodos criados: " + (this.visitedNodes.size() + this.openNodes.size()));
                        System.out.println("O maior tamanho da fronteira durante a busca: " + this.maxFrontierSize);
                        System.out.println("O tamanho do caminho: " + auxNode.getLevel());
                        System.out.println("########################################################################");
                        System.out.println("Caminho percorrido:");
                        System.out.println();
                        auxNode.printPathway();
                        System.out.println("########################################################################");
                    }
                } else {
                    continue;
                }


            } else { //If there is no one
                System.out.println("########################################################################");
                System.out.println("#                               8Puzzle                                #");
                System.out.println("########################################################################");
                System.out.println("Erro: matriz inicial insolucionável.");
                System.out.println("O total de nodos visitados: " + this.visitedNodes.size());
                System.out.println("O total de nodos criados: " + (this.visitedNodes.size() + this.openNodes.size()));
                System.out.println("O maior tamanho da fronteira durante a busca: " + this.maxFrontierSize);
                System.out.println("Matriz inicial:");
                System.out.println();
                tree.getRoot().printNode();
                System.out.println("########################################################################");

                return;
            }
        }

        return;

    }

    public void resolveQ3(int[][] initialState) {
        //Re-initialize control lists:
        this.openNodes = new ArrayList<Node>();
        this.visitedNodes = new ArrayList<Node>();
        //Create new tree
        Tree tree = new Tree(initialState);
        //Check if is an solvable matrix:
        boolean isSolvable = false;
        while (!isSolvable) {
            if (tree.getRoot().isSolvable()) {
                isSolvable = true;
            } else {
                System.out.println("Gerando nova matriz...");
                tree.setRoot(new Node(Node.randomBoard(), null));
            }
        }
        //Initialize control variables:
        boolean isObjectiveNodeFound = false;
        //Start openNodes list:
        this.openNodes.add(tree.getRoot());
        //Start the search method:
        while (!isObjectiveNodeFound) {
            //Initialize control variables:
            boolean nodeAlreadyVisited = false;
            //Get the first node
            Node auxNode;
            try {
                auxNode = this.openNodes.get(0);
            } catch (java.lang.IndexOutOfBoundsException ex) {
                auxNode = null;
            }
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
                        Stack<Node> generatedChildren = auxNode.generateChildren();
                        int stackLenght = generatedChildren.size();
                        for (int i = 0; i < stackLenght; i++) {
                            Node popedChild = generatedChildren.pop();
                            int poppedChildCost = popedChild.calculateHeuristic2();
                            //Sort open nodes list: - problems:
                            boolean alreadyAdded = false;
                            if (this.openNodes.size() == 0) {
                                this.openNodes.add(popedChild);
                                //System.out.println(popedChild.getCost() + "passou");
                            } else {
                                for (int auxIndex = 0; auxIndex < this.openNodes.size(); auxIndex++) {
                                    if (poppedChildCost <= this.openNodes.get(auxIndex).getCost()){
                                        this.openNodes.add(auxIndex, popedChild);
                                        alreadyAdded = true;
                                        break;
                                    }
                                }
                                if (!alreadyAdded) {
                                    this.openNodes.add(popedChild);
                                }
                            }

                            //System.out.println(popedChild.getLevel()); //Prints the level, for debugging
                        }
                        //Compare to find the largest frontier size:
                        if (this.maxFrontierSize < this.openNodes.size()) {
                            this.maxFrontierSize = this.openNodes.size();
                        }
                    } else { //If it is, return everything that the assigment wants:
                        isObjectiveNodeFound = true;
                        System.out.println("########################################################################");
                        System.out.println("#                               8Puzzle                                #");
                        System.out.println("########################################################################");
                        System.out.println("O total de nodos visitados: " + this.visitedNodes.size());
                        System.out.println("O total de nodos criados: " + (this.visitedNodes.size() + this.openNodes.size()));
                        System.out.println("O maior tamanho da fronteira durante a busca: " + this.maxFrontierSize);
                        System.out.println("O tamanho do caminho: " + auxNode.getLevel());
                        System.out.println("########################################################################");
                        System.out.println("Caminho percorrido:");
                        System.out.println();
                        auxNode.printPathway();
                        System.out.println("########################################################################");
                    }
                } else {
                    continue;
                }


            } else { //If there is no one
                System.out.println("########################################################################");
                System.out.println("#                               8Puzzle                                #");
                System.out.println("########################################################################");
                System.out.println("Erro: matriz inicial insolucionável.");
                System.out.println("O total de nodos visitados: " + this.visitedNodes.size());
                System.out.println("O total de nodos criados: " + (this.visitedNodes.size() + this.openNodes.size()));
                System.out.println("O maior tamanho da fronteira durante a busca: " + this.maxFrontierSize);
                System.out.println("Matriz inicial:");
                System.out.println();
                tree.getRoot().printNode();
                System.out.println("########################################################################");

                return;
            }
        }

        return;
    }

    public void test () {
//        Tree tree = new Tree(new int[][]{{4,7,3}, {8,2,5}, {1,6,0}});
//        int[][] test = tree.getRoot().copyState(tree.getRoot().getState());
//
//        Stack stack = tree.getRoot().generateChildren();
//
//        //Print node structure:
//        for (int line = 0; line < 3; line++) {
//            for (int column = 0; column < 3; column++) {
//                System.out.print(test[line][column]);
//            }
//            System.out.println();
//        }
//
//        //tree.getRoot().findNullLocation();
//        int stackSize = stack.size();
//
//        for (int i = 0; i < stackSize; i++) {
//            Node node = (Node) stack.pop();
//            System.out.println();
//            System.out.println();
//
//            for (int line = 0; line < 3; line++) {
//                for (int column = 0; column < 3; column++) {
//                    System.out.print(node.getState()[line][column]);
//                }
//                System.out.println();
//            }
//        }
//
//        Tree tree2 = new Tree(new int[][]{{1,2,3}, {4,5,6}, {7,8,0}});
//        System.out.println();
//        System.out.println(tree.getRoot().isObjectiveNode());
//        System.out.println(tree2.getRoot().isObjectiveNode());

        //New test:
        Tree tree = new Tree(Node.testNode1);
        System.out.println(tree.getRoot().calculateHeuristic1());
        System.out.println(new Node(new int[][] {{1,2,4},{5,7,3},{6,8,0}}, null).calculateHeuristic2());

    }


}
