package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by ryan on 1/8/17.
 */
public class BreadthFirst {
    LinkedList<Node> adj = new LinkedList<>();
    int[] initial;

    public BreadthFirst(int[] initial){
        this.initial = initial;
    }


    public void run(){
        ArrayList<Node> visited = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        Node current = new Node(initial);
        visited.add(current);
        queue.add(current);
        current.getCurrentState().printCurrentState();


        while(queue.size()!=0){
            current = queue.poll();
            current.generateSuccessors();
            adj.addAll(current.getChildren());


            for(int i=0;i<adj.size();i++){
                if(!visited.contains(adj.get(i))){
                    visited.add(adj.get(i));
                    queue.add(adj.get(i));
                }
            }
        }


    }
}
