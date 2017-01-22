package com.company;

import java.util.*;

/**
 * Created by ryan on 1/21/17.
 */
public class UniformCostSearch {

    int[] initial;
    Node init;
    Node goal;
    int totalCost = 0;

    public UniformCostSearch(int[] initial, int[] goal){
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





    public void run(){
        Set<State> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        Node current = new Node(initial);

        queue.add(current);

        while(queue.size()!=0){

            current = queue.poll();
            if(current.getCurrentState().isGoal()){
                System.out.println("FOUND");
                printPath(current);
                return;
            }
            visited.add(current.getCurrentState());
            //current.getCurrentState().printCurrentState();


            ArrayList<Node> children = current.generateSuccessors();
            children.sort(Comparator.comparing(Node::getPathCost));
            for(int i=0;i<children.size();i++){
                Node child = children.get(i);
                boolean contains = visited.contains(child.getCurrentState());
                boolean frontierContains = queue.contains(child);
                //System.out.println("VISITED : " + contains);
                //System.out.println("Frontier Contains: " + frontierContains);
                if(! (contains || frontierContains)){
                    if(child.getCurrentState().isGoal()){
                        current = child;
                        printPath(current);
                        return;
                    }
                    queue.add(child);
                }
                else if(frontierContains){
                    queue.remove(child);
                    queue.add(child);

                }
            }

            System.out.println("QUEUE: "  + queue.size());







        }


    }
}
