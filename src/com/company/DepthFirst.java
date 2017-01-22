package com.company;

import java.util.*;

/**
 * Created by ryan on 1/16/17.
 */
public class DepthFirst {

    int[] initial;
    Node init;
    Node goal;
    int totalCost = 0;

    public DepthFirst(int[] initial, int[] goal){
        this.initial = initial;
        this.init = new Node(initial);
        this.goal = new Node(goal);
    }




    public void printPath(Node item){
        Node current = item;
        Stack<Node> path = new Stack<Node>();

        while(current.getParent()!=null){
            path.push(current);
            current = current.getParent();
        }

        init.getCurrentState().printCurrentState();
        System.out.println("  |  ");
        System.out.println("  |  ");
        System.out.println("  V  ");
        while(!path.isEmpty()){
            current = path.pop();
            totalCost = totalCost + current.getPathCost();
            System.out.println("ACTION: "  +current.getAction() + ", Cost: " + current.getPathCost() + ", Total Cost:" + totalCost + ", Depth: " + current.getDepth());
            current.getCurrentState().printCurrentState();
            if(path.size()>0) {
                System.out.println("  |  ");
                System.out.println("  |  ");
                System.out.println("  V  ");
            }
        }
        System.out.println("Path Cost: " + totalCost);
    }



    //RUNS DFS ON THE TREE AS IT BUILDS
    public void run(){
        Set<State> visited = new HashSet<State>();
        LinkedList<Node> queue = new LinkedList<Node>();
        Node current = new Node(initial);
        if(current.getCurrentState().isGoal()){
            System.out.println("FOUND");
            printPath(current);
            return;
        }
        queue.addFirst(current);

        while(queue.size()!=0){

            current = queue.poll();
            visited.add(current.getCurrentState());
            //current.getCurrentState().printCurrentState();

            ArrayList<Node> children = current.generateSuccessors();
            for(int i=0;i<children.size();i++){
                Node child = children.get(i);
                boolean contains = visited.contains(child.getCurrentState());
                //System.out.println(contains);
                if(!contains){
                    if(child.getCurrentState().isGoal()){
                        System.out.println("FOUND");
                        current = child;
                        printPath(current);
                        return;
                    }
                    queue.addFirst(child);
                }
            }


            System.out.println("QUEUE: "  + queue.size());






        }


    }

}
