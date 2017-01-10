package com.company;

/**
 * Created by ryan on 1/8/17.
 */
public class Solver {




    public Solver(int[] initial){
        BreadthFirst bfs = new BreadthFirst(initial);
        bfs.run();

    }



}
