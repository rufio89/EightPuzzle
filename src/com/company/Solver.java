package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by ryan on 1/8/17.
 */
public class Solver {




    public Solver(int[] initial, int[] goal, String alg){
        if (alg == "bfs") {
            BreadthFirst bfs = new BreadthFirst(initial, goal);
            bfs.run();
        }

        if (alg == "dfs") {
            DepthFirst dfs = new DepthFirst(initial, goal);
            dfs.run();
        }
        if (alg == "id") {
            IterativeDeepening id = new IterativeDeepening(initial, goal);
            id.run();
        }
        if (alg == "ucs") {
            UniformCostSearch ucs = new UniformCostSearch(initial, goal);
            ucs.run();
        }


    }



}
