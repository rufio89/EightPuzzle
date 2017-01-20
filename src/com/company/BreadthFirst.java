package com.company;

import java.util.*;
import java.util.Hashtable;
/**
 * Created by ryan on 1/8/17.
 */
public class BreadthFirst{

    int[] initial;
    Node init;
    Node goal;
    int pathCost = 0;

    public BreadthFirst(int[] initial, int[] goal){
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
            System.out.println(current.getAction());
            current.getCurrentState().printCurrentState();
            if(path.size()>0) {
                System.out.println("  |  ");
                System.out.println("  |  ");
                System.out.println("  V  ");
            }
        }
        System.out.println("Path Cost: " + pathCost);
    }

    //CHECKS TO SEE IF THE VISITED LIST CONTAINS THE BOARD YOU PASS IN
    public boolean containsBoard(Node n, LinkedList<Node> list){
        for(int i=0;i<list.size();i++){
            if(list.get(i).equals(n)) return true;
        }
        return false;
    }

    public boolean frontierContains(Node n, Queue<Node> queue){
        LinkedList<Node> check = new LinkedList<>(queue);
        for(int i=1;i<check.size();i++){
            if(check.get(i).equals(n)) return true;
        }
        return false;
    }


    //RUNS BFS ON THE TREE AS IT BUILDS
    public void run(){
        LinkedList<Node> visited = new LinkedList<Node>();
        Queue<Node> queue = new LinkedList<Node>();
        Node current = new Node(initial);
        if(current.getCurrentState().isGoal()){
            System.out.println("FOUND");
            printPath(current);
            return;
        }
        queue.add(current);

        while(queue.size()!=0){

            current = queue.poll();
            visited.add(current);
            //current.getCurrentState().printCurrentState();

            ArrayList<Node> children = current.generateSuccessors();
            for(int i=0;i<children.size();i++){
                Node child = children.get(i);
                boolean contains = containsBoard(child,visited);
                boolean frontierContains = frontierContains(child,queue);
                //System.out.println("VISITED : " + contains);
                //System.out.println("FrontierQueue: " + contains);
                if(! (contains || frontierContains)){
                    if(child.getCurrentState().isGoal()){
                        System.out.println("FOUND");
                        current = child;
                        printPath(current);
                        return;
                    }
                    queue.add(child);
                }
            }


            System.out.println("QUEUE: "  + queue.size());






        }


    }
}
