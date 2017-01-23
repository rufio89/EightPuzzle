package com.company;

import java.util.*;

/**
 * Created by ryan on 1/21/17.
 */
public class IterativeDeepening {


    int[] initial;
    Node init;
    Node goal;
    int totalCost = 0;

    public IterativeDeepening(int[] initial, int[] goal){
        this.initial = initial;
        this.init = new Node(initial);
        this.goal = new Node(goal);
    }




    public void printPath(Node item, int totalVisited){
        Node current = item;
        Stack<Node> path = new Stack<Node>();

        while(current.getParent()!=null){
            path.push(current);
            current = current.getParent();
        }

//        init.getCurrentState().printCurrentState();
//        System.out.println("  |  ");
//        System.out.println("  |  ");
//        System.out.println("  V  ");
        while(!path.isEmpty()){
            current = path.pop();
            totalCost = totalCost + current.getPathCost();

            //System.out.println("ACTION: "  +current.getAction() + ", Cost: " + current.getPathCost() + ", Total Cost:" + totalCost + ", Depth: " + current.getDepth());
            //current.getCurrentState().printCurrentState();
            if(path.size()>0) {
//                System.out.println("  |  ");
//                System.out.println("  |  ");
//                System.out.println("  V  ");
            }
        }
        System.out.println("Iterative Deepening Search -> Path Cost: " + totalCost + ", Depth: " + current.getDepth()  + ", Nodes Visited: " + totalVisited );
    }

    //CHECKS TO SEE IF THE VISITED LIST CONTAINS THE BOARD YOU PASS IN
    public boolean containsBoard(Node n, LinkedList<Node> list){
        for(int i=1;i<list.size();i++){
            if(list.get(i).equals(n)) return true;
        }
        return false;
    }





    public void run(){
        int depth = 0;
        Node current = new Node(initial);

        boolean isFound = false;
        int totalVisited = 0;
        while(!isFound){
            isFound = runDFS(depth);
            depth++;
        }
    }

    //RUNS DFS ON THE TREE AS IT BUILDS
    public boolean runDFS(int depth) {
        Set<State> visited = new HashSet<State>();
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = new Node(initial);
        if (current.getCurrentState().isGoal()) {
            printPath(current, visited.size());
            return true;
        }
        queue.addFirst(current);

        if (depth == 0) {
            return false;
        }


        while (queue.size()!=0 || current.getDepth()==depth) {
            current = queue.poll();
            visited.add(current.getCurrentState());

                ArrayList<Node> children = current.generateSuccessors();
                for (int i = 0; i < children.size(); i++) {
                    Node child = children.get(i);
                    boolean contains = visited.contains(child.getCurrentState());

                    if (child.getCurrentState().isGoal()) {
                        current = child;
                        printPath(current, visited.size());
                        return true;
                    }
                    if (!contains && child.getDepth() < depth) {
                        queue.addFirst(child);
                    }
                }
            


            }


            return false;
        }

}
