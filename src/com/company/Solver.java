package com.company;

/**
 * Created by ryan on 1/8/17.
 */
public class Solver {




    public Solver(int[] initial, int[] goal){
        BreadthFirst bfs = new BreadthFirst(initial, goal);
        bfs.run();
 //       DepthFirst dfs = new DepthFirst(initial, goal);
 //       dfs.run();

    }



}
