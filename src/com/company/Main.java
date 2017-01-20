package com.company;

public class Main {

    public static void main(String[] args) {
	    int[] initialHard = {5,6,7,4,0,8,3,2,1};
        int[] initialMedium = {2,8,1,0,4,3,7,6,5};
        int[] initialEasy = {1,3,4,8,6,2,7,0,5};
        int[] goal = {1,2,3,8,0,4,7,6,5};
        Solver s = new Solver(initialHard, goal);
    }


}
