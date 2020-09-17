package com.company;

import java.util.List;

public class Node {
    //Atr
    private int[][] state;
    private Node father;
    private List<Node> childs;
    private int cost;
    private int level;

    //Cons
    public Node(int[][] state, Node father) {
        this.state = state;
        this.father = father;
    }

    //Met
    public int[][] getState() {
        return state;
    }

    public void setState(int[][] state) {
        this.state = state;
    }

    public Node getPathway() {
        return father;
    }

    public void setPathway(Node father) {
        this.father = father;
    }

    public List getChilds() {
        return childs;
    }

    public void setChilds(List childs) {
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

    public void generateChilds () {
        if (this.findNullLocation()[0] == 0 && this.findNullLocation()[1] == 0) {

        }
    }

    //0 ins the null value
    public int[] findNullLocation () {
        for (int contLine = 0; contLine < 3; contLine++) {
            for (int cont = 0; cont < 3; cont++) {
                if (this.state[contLine][cont] == 0) {
                    int[] nullLocation = new int[]{contLine, cont};
                    return nullLocation;
                }
            }
        }
        return null;
    }
}
