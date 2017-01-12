package com.company;

import java.util.*;

/**
 * Created by ryan on 1/8/17.
 */
public class BreadthFirst {
    LinkedList<Node> adj = new LinkedList<>();
    int[] initial;

    public BreadthFirst(int[] initial){
        this.initial = initial;
    }

    public boolean checkVisited(ArrayList<Node> visited, Node node){
        for(int i=0;i<visited.size();i++){
            if(visited.get(i).equals(node))return true;
        }
        return false;

    }


    public void printPath(Node item){
        Node current = item;
        Stack<Node> path = new Stack<Node>();
        while(current.getParent()!=null){
            path.push(current);
            current = current.getParent();
        }

        while(!path.isEmpty()){
            current = path.pop();
            current.getCurrentState().printCurrentState();
            System.out.println("  |  ");
            System.out.println("  |  ");
            System.out.println("  V  ");
        }
    }

    public void run(){
        Hashtable<Node, Boolean> visited = new Hashtable<Node, Boolean>();
        LinkedList<Node> queue = new LinkedList<>();
        Node current = new Node(initial);
        visited.put(current, true);
        queue.add(current);



        while(queue.size()!=0){

            current = queue.poll();
            current.getCurrentState().printCurrentState();
            if(current.getCurrentState().isGoal()){
                System.out.println("FOUND");
                current.getCurrentState().printCurrentState();
                printPath(current);
                break;
            }
            current.generateSuccessors();
            adj.addAll(current.getChildren());
            System.out.println("QUEUE: "  + queue.size());




            for(int i=0;i<adj.size();i++){
                boolean contains = visited.containsKey(adj.get(i));
                System.out.println(contains);
                if(!visited.containsKey(adj.get(i))){
                    visited.put(adj.get(i), true);
                    queue.add(adj.get(i));

                }
            }
            adj.removeAll(adj);


        }


    }
}
