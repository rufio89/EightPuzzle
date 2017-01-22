package com.company;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ryan on 1/8/17.
 */
public class Node implements Comparable<Node>{
    private Node parent = null;
    private State currentState;
    private String action;
    private int depth;
    private int pathCost;
    private boolean expanded;


    public Node(Node parent, State state, String action, int depth, boolean expanded, int pathCost){
        this.parent = parent;
        this.currentState = state;
        this.action = action;
        this.depth = depth;
        this.expanded = expanded;
        this.pathCost = pathCost;
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
        State left = generateLeft(currentState);
        State right = generateRight(currentState);
        State up = generateUp(currentState);
        State down = generateDown(currentState);

        if(left!=null)  children.add(new Node(this, left, "left", this.depth+1, true, left.getTileMoved()));
        if(right!=null)  children.add(new Node(this, right, "right", this.depth+1, true, right.getTileMoved()));
        if(up!=null)  children.add(new Node(this,up, "up", this.depth+1, true, up.getTileMoved()));
        if(down!=null)  children.add(new Node(this,down, "down", this.depth+1, true, down.getTileMoved()));




        return children;
    }





    public State generateLeft(State state){
        State newState = new State(state.getCurrentState());
        if(newState.getjHole()!=0){
            int previous = newState.getCurrentState()[newState.getiHole()][newState.getjHole()-1];
            newState.setCurrentState(newState.getiHole(),newState.getjHole(),previous);
            newState.setCurrentState(newState.getiHole(),newState.getjHole()-1, 0);
            newState.setTileMoved(previous);

        }
        else{
            return null;
        }
        return newState;
    }
    public State generateRight(State state){
        State newState = new State(state.getCurrentState());
        if(newState.getjHole()!=2){
            int previous = newState.getCurrentState()[newState.getiHole()][newState.getjHole()+1];
            newState.setCurrentState(newState.getiHole(),newState.getjHole(),previous);
            newState.setCurrentState(newState.getiHole(),newState.getjHole()+1, 0);
            newState.setTileMoved(previous);
        }
        else{
            return null;
        }
        return newState;
    }

    public State generateUp(State state){
        State newState = new State(state.getCurrentState());
        if(newState.getiHole()!=0){
            int previous = newState.getCurrentState()[newState.getiHole()-1][newState.getjHole()];
            newState.setCurrentState(newState.getiHole(),newState.getjHole(),previous);
            newState.setCurrentState(newState.getiHole()-1,newState.getjHole(), 0);
            newState.setTileMoved(previous);
        }
        else{
            return null;
        }
        return newState;
    }
    public State generateDown(State state){
        State newState = new State(state.getCurrentState());
        if(newState.getiHole()!=2){
            int previous = newState.getCurrentState()[newState.getiHole()+1][newState.getjHole()];
            newState.setCurrentState(newState.getiHole(),newState.getjHole(),previous);
            newState.setCurrentState(newState.getiHole()+1,newState.getjHole(),0);
            newState.setTileMoved(previous);
        }
        else{
            return null;
        }
        return newState;
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

    public boolean equals(Node n){
        return this.getCurrentState().equals(n.getCurrentState());
    }

    public int compareTo(Node n){
        if(this.equals(n)) return 0;
        else if(this.getPathCost() > n.getPathCost()) return 1;
        else return -1;
    }

}
