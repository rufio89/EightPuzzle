package com.company;

import java.util.ArrayList;

/**
 * Created by ryan on 1/8/17.
 */
public class Node {
    private Node parent = null;
    private State currentState;
    private ArrayList<Node> children;
    private String action;
    private int depth;
    private int pathCost;
    private boolean expanded;


    public Node(Node parent, int[][] state){
        this.parent = parent;
        this.currentState = new State(state);
//        this.children = generateSuccessors();
//        this.action = action;
//        this.depth = depth;
//        this.pathCost = pathCost;
//        this.expanded = expanded;
    }

    public Node(int[] state){

        this.currentState = new State(state);
        this.children = generateSuccessors();
//        this.action = action;
//        this.depth = depth;
//        this.pathCost = pathCost;
//        this.expanded = expanded;
    }

    public Node getParent(){
        return this.parent;
    }

    public State getCurrentState(){
        return this.currentState;
    }

    public ArrayList<Node> getChildren(){
        return this.children;
    }

    public ArrayList<Node> generateSuccessors(){
        ArrayList<Node> children = new ArrayList<>();
        int[][] left = generateLeft(currentState.getCurrentState());
        int[][] right = generateRight(currentState.getCurrentState());
        int[][] up = generateUp(currentState.getCurrentState());
        int[][] down = generateDown(currentState.getCurrentState());

        if(left!=null)  children.add(new Node(this, left));
        if(right!=null)  children.add(new Node(this, right));
        if(up!=null)  children.add(new Node(this,up));
        if(down!=null)  children.add(new Node(this,down));
        this.children = children;
        return children;
    }

    public void printChildren(ArrayList<State> children){
        for(int i=0;i<children.size();i++){
            children.get(i).printCurrentState();
            System.out.println();
        }
    }

    public int[][] generateLeft(int[][] state){
        if(currentState.getjHole()!=0){
            int previous = state[currentState.getiHole()][currentState.getjHole()-1];
            state[currentState.getiHole()][currentState.getjHole()] = previous;
            state[currentState.getiHole()][currentState.getjHole()-1] = 0;
        }
        else{
            return null;
        }
        return state;
    }
    public int[][] generateRight(int[][] state){
        if(currentState.getjHole()!=2){
            int previous = state[currentState.getiHole()][currentState.getjHole()+1];
            state[currentState.getiHole()][currentState.getjHole()] = previous;
            state[currentState.getiHole()][currentState.getjHole()+1] = 0;
        }
        else{
            return null;
        }
        return state;
    }

    public int[][] generateUp(int[][] state){
        if(currentState.getiHole()!=0){
            int previous = state[currentState.getiHole()-1][currentState.getjHole()];
            state[currentState.getiHole()][currentState.getjHole()] = previous;
            state[currentState.getiHole()-1][currentState.getjHole()] = 0;
        }
        else{
            return null;
        }
        return state;
    }
    public int[][] generateDown(int[][] state){
        if(currentState.getiHole()!=2){
            int previous = state[currentState.getiHole()+1][currentState.getjHole()];
            state[currentState.getiHole()][currentState.getjHole()] = previous;
            state[currentState.getiHole()+1][currentState.getjHole()] = 0;
        }
        else{
            return null;
        }
        return state;
    }

    public boolean equals(Node n){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                if (this.currentState.getCurrentState()[i][j]!=n.currentState.getCurrentState()[i][j]) return false;
            }
        }
        return true;
    }

}
