package com.company;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ryan on 1/8/17.
 */
public class State {
    private int[][]currentState;
    private int iHole;
    private int jHole;
    private int[] goalState = {1,2,3,8,0,4,7,6,5};
    private int tileMoved;

    public State(int[] state){
        this.currentState = generateBoard(state);
        getHole();
    }

    public State(int[][] state){
        this.currentState = state;
        getHole();
    }

    public int[][]  generateBoard(int[] initial){
        int dimension = (int)Math.sqrt(initial.length);
        int[][] result = new int[dimension][dimension];
        int counter = 0;
        for(int i=0;i<dimension;i++){
            for(int j=0;j<dimension;j++){
                result[i][j] = initial[counter];
                //System.out.print(result[i][j] + " ");
                counter++;
            }
            //System.out.println();
        }

        return result;
    }

    public int[][] getCurrentState(){
        int[][] current = new int[currentState.length][currentState.length];
        for(int i=0;i<currentState.length;i++){
            for(int j=0;j<currentState.length;j++){
                current[i][j] = currentState[i][j];
            }
        }
        return current;
    }

    public void setCurrentState(int i, int j, int newValue){
        this.currentState[i][j] = newValue;
    }

    public void setTileMoved(int tile){
        this.tileMoved = tile;
    }

    public int getTileMoved(){
        return this.tileMoved;
    }

    public void printCurrentState(){
        int counter = 0;
        for(int i=0;i<currentState.length;i++){
            for(int j=0;j<currentState.length;j++){
                System.out.print(currentState[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public String toString(){
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<currentState.length;i++){
            for(int j=0;j<currentState.length;j++){
                sb.append(currentState[i][j] + " ");
                counter++;
            }
        }
        return sb.toString();
    }


    public boolean equals(Object o) {
        if (Arrays.deepEquals(this.getCurrentState(),((State)o).getCurrentState())) return true;
        else return false;
    }

    public int hashCode(){
        return this.toString().hashCode();
    }




    public void getHole(){
        for(int i=0;i<currentState.length;i++){
            for(int j=0;j<currentState.length;j++){
                if(currentState[i][j]==0){
                    this.iHole = i;
                    this.jHole = j;
                    break;
                }
            }
        }
    }

    public int getiHole(){
        return this.iHole;
    }

    public int getjHole(){
        return this.jHole;
    }

    public boolean isGoal(){
        int counter = 0;
        for(int i=0;i<currentState.length;i++){
            for(int j=0;j<currentState.length;j++){
                    if(currentState[i][j]!=goalState[counter]) {
                        return false;
                    }
                counter++;
            }
        }
        return true;
    }



}
