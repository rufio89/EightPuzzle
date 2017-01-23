package com.company;

import java.util.Comparator;

/**
 * Created by ryan on 1/22/17.
 */
public class PathCostComparator implements Comparator<Node> {

    @Override
    public int compare(Node n1, Node n2){
        if(n1.getPathCost() < n2.getPathCost()) return -1;
        else if(n1.getPathCost() > n2.getPathCost()) return 1;
        return 0;
    }
}
