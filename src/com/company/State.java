package com.company;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ryan on 1/8/17.
 */
public class State {
    private int[][] currentState;
    private int iHole;
    private int jHole;
    private int[] goalState = {1, 2, 3, 8, 0, 4, 7, 6, 5};
    private int tileMoved;

    public State(int[] state) {
        this.currentState = generateBoard(state);
        setHole();
    }

    public State(int[][] state) {
        this.currentState = state;
        setHole();
    }

    //CREATES A BOARD FROM A ONE-DIMENSIONAL ARRAY
    public int[][] generateBoard(int[] initial) {
        int dimension = (int) Math.sqrt(initial.length);
        int[][] result = new int[dimension][dimension];
        int counter = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result[i][j] = initial[counter];
                counter++;
            }
        }

        return result;
    }

    //GETS THE CURRENT BOARD STATE AS A 2-DIMENSIONAL ARRAY
    public int[][] getCurrentState() {
        int[][] current = new int[currentState.length][currentState.length];
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                current[i][j] = currentState[i][j];
            }
        }
        return current;
    }

    //SETS THE CURRENT STATE BASED ON VALUES PASSED IN. USED IN SUCCESSOR FUNCTION IN NODE CLASS
    public void setCurrentState(int i, int j, int newValue) {
        this.currentState[i][j] = newValue;
    }

    //SETS THE VALUE OF THE TILE THAT IS MOVED TO USE AS A COST FOR THE PATH-COST
    public void setTileMoved(int tile) {
        this.tileMoved = tile;
    }

    //GETTER FOR THE NUMBER OF THE TILE MOVED
    public int getTileMoved() {
        return this.tileMoved;
    }


    //PRETTY PRINT ONE-D ARRAY
    public void printCurrentState() {
        int counter = 0;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                System.out.print(currentState[i][j] + " ");
                counter++;
            }
            System.out.println();
        }
        System.out.println();
    }

    //OVERRIDE TOSTRING FOR USE IN CHECKING VISITED NODES
    @Override
    public String toString() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                sb.append(currentState[i][j] + " ");
                counter++;
            }
        }
        return sb.toString();
    }

    //OVERRIDE EQUALS FOR USE IN CHECKING VISISTED NODES
    public boolean equals(Object o) {
        if (Arrays.deepEquals(this.getCurrentState(), ((State) o).getCurrentState())) return true;
        else return false;
    }

    //OVERRIDE HASHCODE FOR USE IN CHECKING VISISTED NODES
    public int hashCode() {
        return this.toString().hashCode();
    }


    //SET THE "SPACE" ON THE BOARD
    public void setHole() {
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                if (currentState[i][j] == 0) {
                    this.iHole = i;
                    this.jHole = j;
                    break;
                }
            }
        }
    }

    //RETRIEVE THE "I" VALUE OF THE "SPACE" OR 0 VALUE ON THE BOARD
    public int getiHole() {
        return this.iHole;
    }

    //RETRIEVE THE "J" VALUE OF THE "SPACE" OR 0 VALUE ON THE BOARD
    public int getjHole() {
        return this.jHole;
    }

    //CHECK TO SEE IF THE BOARD IS A GOAL STATE
    public boolean isGoal() {
        int counter = 0;
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                if (currentState[i][j] != goalState[counter]) {
                    return false;
                }
                counter++;
            }
        }
        return true;
    }


}
