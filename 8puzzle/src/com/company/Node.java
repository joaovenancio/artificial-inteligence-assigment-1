package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class Node {
    //Atr
    private int[][] state;
    private Node father;
    private ArrayList<Node> childs;
    private int cost;
    private int level;
    //Pré-defined nodes:
    public static final int[][] objectiveNode = {{1,2,3},{4,5,6},{7,8,0}};
    public static final int[][] testNode1 = {{4,7,3}, {8,2,5}, {1,6,0}};
    public static final int[][] testNode2 = {{8,7,2}, {5,1,0}, {6,4,3}};

    //Cons
    public Node(int[][] state, Node father) {
        this.state = state;
        this.father = father;
        this.childs = new ArrayList<Node>();
        this.cost = 0;
        this.level = 0;
    }

    public Node(int[][] state, Node father, int level) {
        this.state = state;
        this.father = father;
        this.childs = new ArrayList<Node>();
        this.cost = 0;
        this.level = level;
    }

    //Met
    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public List getChilds() {
        return childs;
    }

    public void setChilds(ArrayList<Node> childs) {
        this.childs = childs;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public static int[][] randomBoard() {
        Integer[] numArray = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numList = new ArrayList();
        Collections.addAll(numList, numArray);
        Collections.shuffle(numList);

        int[][] board = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        int controler = 8;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[i][j] = numList.remove(controler);
                controler--;
            }
        }

        System.out.println("Matriz gerada:");
        System.out.println();
        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(board[line][column]);
            }
            System.out.println();
        }
        System.out.println();

        return board;
    }

    public static int[][] randomInput() {
        boolean valid = false;
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        while(!valid) {
            board = randomBoard();
            valid = isSolvable(board);
        }
        return board;
    }

    public Stack<Node> generateChilds () {
        int[] nullLocation = this.findNullLocation ();
        Stack<Node> result = new Stack();
        int[][] newState;
        Node newNode;

        switch (nullLocation[0]) {
            case 0:
                switch (nullLocation[1]) {
                    case 0: //Generate 2 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[0][0] = this.state[0][1];
                        newState[0][1] = 0;

                         newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[0][0] = this.state[1][0];
                        newState[1][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;

                    case 1: //Generate 3 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[0][1] = this.state[0][0];
                        newState[0][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[0][1] = this.state[1][1];
                        newState[1][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //3:
                        newState = this.copyState(this.state);
                        newState[0][1] = this.state[0][2];
                        newState[0][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;

                    case 2: //Generate 2 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[0][2] = this.state[0][1];
                        newState[0][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[0][2] = this.state[1][2];
                        newState[1][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;
                }
                break;

            case 1:
                switch (nullLocation[1]) {
                    case 0: //Generate 3 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[1][0] = this.state[0][0];
                        newState[0][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[1][0] = this.state[1][1];
                        newState[1][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //3:
                        newState = this.copyState(this.state);
                        newState[1][0] = this.state[2][0];
                        newState[2][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;

                    case 1: //Generate 4 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[1][1] = this.state[0][1];
                        newState[0][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[1][1] = this.state[1][0];
                        newState[1][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //3:
                        newState = this.copyState(this.state);
                        newState[1][1] = this.state[1][2];
                        newState[1][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //4:
                        newState = this.copyState(this.state);
                        newState[1][1] = this.state[2][1];
                        newState[2][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;

                    case 2: //Generate 3 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[1][2] = this.state[0][2];
                        newState[0][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[1][2] = this.state[1][1];
                        newState[1][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //3:
                        newState = this.copyState(this.state);
                        newState[1][2] = this.state[2][2];
                        newState[2][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;
                }
                break;

            case 2:
                switch (nullLocation[1]) {
                    case 0: //Generate 2 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[2][0] = this.state[1][0];
                        newState[1][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[2][0] = this.state[2][1];
                        newState[2][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;

                    case 1: //Generate 3 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[2][1] = this.state[2][0];
                        newState[2][0] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[2][1] = this.state[1][1];
                        newState[1][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //3:
                        newState = this.copyState(this.state);
                        newState[2][1] = this.state[2][2];
                        newState[2][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;

                    case 2: //Generate 2 nodes
                        //1:
                        newState = this.copyState(this.state);
                        newState[2][2] = this.state[1][2];
                        newState[1][2] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        //2:
                        newState = this.copyState(this.state);
                        newState[2][2] = this.state[2][1];
                        newState[2][1] = 0;

                        newNode = new Node(newState, this, this.level+1);

                        this.getChilds().add(newNode);
                        result.push(newNode);

                        break;
                }
                break;

        }

        return result;
    }

    //0 ins the null value
    public int[] findNullLocation () {
        for (int contLine = 0; contLine < 3; contLine++) {
            for (int cont = 0; cont < 3; cont++) {
                //System.out.println(this.state[contLine][cont]); //TEST
                if (this.state[contLine][cont] == 0) {
                    int[] nullLocation = new int[]{contLine, cont};
                    return nullLocation;
                }
            }
        }
        return null;
    }

    public int[][] copyState (int[][] state) {
        int[][] copiedState = new int[3][3];

        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                copiedState[line][column] = state[line][column];
            }
        }

        return copiedState;
    }

    public boolean isSameState(int[][] state) {
        boolean foundSomethingDifferent = false;

        for (int line = 0; line < 3 && !foundSomethingDifferent; line++) {
            for (int column = 0; column < 3 && !foundSomethingDifferent; column++) {
                if (!(state[line][column] == this.state[line][column])) {
                    foundSomethingDifferent = true;
                    break;
                }
            }
        }

        if (foundSomethingDifferent) {
            return false;
        } else {
            return true;
        }

    }

    public boolean isObjectiveNode () {
        if (this.isSameState(Node.objectiveNode)){
            return true;
        } else {
            return false;
        }
    }

    public void printPathway () {
        boolean haveFatherNode;
        Node auxNode = this;
        Stack<Node> nodesStack = new Stack();

        if (this.getFather() != null) {
            haveFatherNode = true;
        } else {
            haveFatherNode = false;
        }

        while (haveFatherNode) {
            //Print node:
            nodesStack.push(auxNode);
            //Get next node:
            auxNode = auxNode.getFather();
            if (auxNode.getFather() != null) {
                haveFatherNode = true;
            } else {
                haveFatherNode = false;
            }
        }
        //Put the last one in the stack:
        nodesStack.push(auxNode);
        //Print them in the correct oreder:
        while (nodesStack.size() != 0) {
            nodesStack.pop().printNode();
        }
    }

    public void printNode () {
        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                System.out.print(this.getState()[line][column]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int calculateHeuristic1 () {
        int inPosition = 8;
        int outOfPosition = 0;
        int gameNumber = 1;

        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {
                if (this.getState()[line][column] != 0) {
                    if (this.getState()[line][column] != gameNumber) {
                        outOfPosition++;
                    }
                }
                gameNumber++;
            }
        }

        int cost = inPosition-outOfPosition;
        this.setCost(cost);
        return cost;

    }

    public int calculateHeuristic2 () {
        int cost = 0;

        for (int line = 0; line < 3; line++) {
            for (int column = 0; column < 3; column++) {

                int valueLine = 0;
                int valueColumn = 0;

                switch (this.getState()[line][column]) {
                    case 1:
                        valueLine = Math.abs(0 - line);
                        valueColumn = Math.abs(0 - column);
                        break;

                    case 2:
                        valueLine = Math.abs(0 - line);
                        valueColumn = Math.abs(1 - column);
                        break;

                    case 3:
                        valueLine = Math.abs(0 - line);
                        valueColumn = Math.abs(2 - column);
                        break;

                    case 4:
                        valueLine = Math.abs(1 - line);
                        valueColumn = Math.abs(0 - column);
                        break;

                    case 5:
                        valueLine = Math.abs(1 - line);
                        valueColumn = Math.abs(1 - column);
                        break;

                    case 6:
                        valueLine = Math.abs(1 - line);
                        valueColumn = Math.abs(2 - column);
                        break;

                    case 7:
                        valueLine = Math.abs(2 - line);
                        valueColumn = Math.abs(0 - column);
                        break;

                    case 8:
                        valueLine = Math.abs(2 - line);
                        valueColumn = Math.abs(1 - column);
                        break;
                }

                cost = cost + valueLine + valueColumn;

            }
        }

        this.setCost(cost);
        return cost;

    }

    //Taken from: https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable/#:~:text=It%20is%20not%20possible%20to,odd%20in%20the%20input%20state.&text=The%20second%20example%20has%2011,their%20appearance%20in%20goal%20state.
    public boolean isSolvable () {
        int[][] arr = this.getState();
        int invCount = 0;

        for (int i = 0; i < 3 - 1; i++) {
            for (int j = i + 1; j < 3; j++) {
                // Value 0 is used for empty space
                if (arr[j][i] > 0 && arr[j][i] > arr[i][j]) {
                    invCount++;
                }
            }
        }

        if(invCount % 2 == 0) {
            System.out.println("Solucionável");
            return true;
        } else {
            System.out.println("Não solucionável");
            return false;
        }
    }

    public static boolean isSolvable (int[][] arr) {
        int invCount = 0;

        for (int i = 0; i < 3 - 1; i++) {
            for (int j = i + 1; j < 3; j++) {
                // Value 0 is used for empty space
                if (arr[j][i] > 0 && arr[j][i] > arr[i][j]) {
                    invCount++;
                }
            }
        }

        if(invCount % 2 == 0) {
            System.out.println("Solucionável");
            return true;
        } else {
            System.out.println("Não solucionável");
            return false;
        }
    }
}
