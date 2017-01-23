package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by ryan on 1/8/17.
 */
public class Node{
    private Node parent = null;
    private State currentState;
    private String action;
    private int depth;
    private int pathCost;
    private boolean expanded;
    int[][] goal = new int[][] {{1,2,3},{8,0,4}, {7,6,5}};
    int misplacedTileCount = 0;
    int manhattanDistance = 0;


    public Node(Node parent, State state, String action, int depth, boolean expanded, int pathCost){
        this.parent = parent;
        this.currentState = state;
        this.action = action;
        this.depth = depth;
        this.expanded = expanded;
        this.pathCost = pathCost;
        setMisplacedTileCount();
        setManhattanDistance();
    }

    public Node(int[] state){

        this.currentState = new State(state);
        this.depth = 0;
        setMisplacedTileCount();
        setManhattanDistance();
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


    public void setMisplacedTileCount(){
        int wrongPosCount = 0;
        for(int i=0;i<currentState.getCurrentState().length;i++){
            for(int j=0;j<currentState.getCurrentState().length;j++){
                if(currentState.getCurrentState()[i][j] != goal[i][j]) wrongPosCount++;
            }
        }
        this.misplacedTileCount = wrongPosCount;
    }

    public int getSum(int i, int j, int x, int y){
        int sum, horizDiff, vertDiff;
        if(i>x)horizDiff = i-x;
        else horizDiff = x-i;
        if(j>y) vertDiff = j-y;
        else vertDiff = y-j;
        sum = vertDiff + horizDiff;
        return sum;
    }

    public int calculateManhattanSum(int i, int j, int value){
        int x, y, sum=0;
        if(value== 0){
            x =1;y=1;
            sum = getSum(i,j,x,y);
        }
        if(value== 1){
            x=0;y=0;
            sum = getSum(i,j,x,y);
        }
        if(value== 2){
            x=0;y=1;
            sum = getSum(i,j,x,y);
        }
        if(value== 3){
            x=0;y=2;
            sum = getSum(i,j,x,y);
        }
        if(value== 4){
            x=1;y=2;
            sum = getSum(i,j,x,y);
        }
        if(value== 5){
            x=2;y=2;
            sum = getSum(i,j,x,y);
        }
        if(value== 6){
            x=2;y=1;
            sum = getSum(i,j,x,y);
        }
        if(value== 7){
            x=2;y=0;
            sum = getSum(i,j,x,y);
        }
        if(value== 8){
            x=1;y=0;
            sum = getSum(i,j,x,y);
        }
        return sum;
    }

    public void setManhattanDistance(){
        int sum = 0;
        for(int i=0;i<currentState.getCurrentState().length;i++){
            for(int j=0;j<currentState.getCurrentState().length;j++){
                if(currentState.getCurrentState()[i][j] != goal[i][j])
                  sum += calculateManhattanSum(i,j, currentState.getCurrentState()[i][j]);
            }
        }
        this.manhattanDistance = sum;
    }

    public int getManhattanDistance(){ return this.manhattanDistance;}

    public int getMisplacedTileCount(){ return this.misplacedTileCount;}

    public int getAStar1Heuristic(){
        return this.misplacedTileCount + this.pathCost;
    }

    public int getAStar2Heuristic(){
        return this.manhattanDistance + this.pathCost;
    }
    public String getAction(){
        return this.action;
    }

    public int getPathCost(){
        return this.pathCost;
    }
    public void setPathCost(int parentPathCost){
        this.pathCost = this.pathCost + parentPathCost;
    }

    public int getDepth(){
        return this.depth;
    }




}
