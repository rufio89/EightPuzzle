package com.company;

public class Main {

    public static void main(String[] args) {
	    int[] initialHard = {5,6,7,4,0,8,3,2,1};
        int[] initialMedium = {2,8,1,0,4,3,7,6,5};
        int[] initialEasy = {1,3,4,8,6,2,7,0,5};

        int[] goal = {1,2,3,8,0,4,7,6,5};
        Solver s1 = new Solver(initialEasy, goal, "bfs");
        Solver s2 = new Solver(initialMedium, goal, "bfs");
        Solver s3 = new Solver(initialHard, goal, "bfs");
//        Solver s4 = new Solver(initialEasy, goal, "dfs");
//        Solver s5 = new Solver(initialMedium, goal, "dfs");
//        Solver s6 = new Solver(initialHard, goal, "dfs");
//        Solver s7 = new Solver(initialEasy, goal, "id");
//        Solver s8 = new Solver(initialMedium, goal, "id");
        //Solver s9 = new Solver(initialHard, goal, "id");
//        Solver s10 = new Solver(initialEasy, goal, "ucs");
//        Solver s11 = new Solver(initialMedium, goal, "ucs");
//        Solver s12 = new Solver(initialHard, goal, "ucs");
//        Solver s13 = new Solver(initialEasy, goal, "bestFS");
//        Solver s14 = new Solver(initialMedium, goal, "bestFS");
//        Solver s15 = new Solver(initialHard, goal, "bestFS");
        Solver s16 = new Solver(initialEasy, goal, "a*1");
        Solver s17 = new Solver(initialMedium, goal, "a*1");
        Solver s18 = new Solver(initialHard, goal, "a*1");
        Solver s19 = new Solver(initialEasy, goal, "a*2");
        Solver s20 = new Solver(initialMedium, goal, "a*2");
        Solver s21 = new Solver(initialHard, goal, "a*2");

    }


}
