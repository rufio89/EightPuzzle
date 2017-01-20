package com.company;

import java.util.ArrayList;

/**
 * Created by ryan on 1/8/17.
 */
public class Node {
    private Node parent = null;
    private State currentState;
    private String action;
    private int depth;
    private int pathCost;
    private boolean expanded;


    public Node(Node parent, int[][] state, String action, int depth, boolean expanded){
        this.parent = parent;
        this.currentState = new State(state);
        this.action = action;
        this.depth = depth;
        this.pathCost = 1;
        this.expanded = expanded;
    }

    public Node(int[] state){

        this.currentState = new State(state);
        this.depth = 0;

    }

    public Node getParent(){
        return this.parent;
    }

    public State getCurrentState(){
        return this.currentState;
    }


    public ArrayList<Node> generateSuccessors(){
        ArrayList<Node> children = new ArrayList<>();
        int[][] left = generateLeft(currentState.getCurrentState());
        int[][] right = generateRight(currentState.getCurrentState());
        int[][] up = generateUp(currentState.getCurrentState());
        int[][] down = generateDown(currentState.getCurrentState());

        if(left!=null)  children.add(new Node(this, left, "left", this.depth+1, true));
        if(right!=null)  children.add(new Node(this, right, "right", this.depth+1, true));
        if(up!=null)  children.add(new Node(this,up, "up", this.depth+1, true));
        if(down!=null)  children.add(new Node(this,down, "down", this.depth+1, true));




        return children;
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
    public void setAction(String action){
        this.action = action;
    }

    public String getAction(){
        return this.action;
    }

    public int getPathCost(){
        return this.pathCost;
    }

    public int getDepth(){
        return this.depth;
    }

}
