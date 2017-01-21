package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

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
        while(!isFound){
            isFound = depthLimitedSearch(current, depth);
            depth++;
        }
    }

    public boolean depthLimitedSearch(Node current, int limit){
        boolean result = false;
        result = recursiveDLS(current,limit);
        return result;
    }

    public boolean recursiveDLS(Node current, int limit){
        boolean result = false;
        if(current.getCurrentState().isGoal()){
            printPath(current);
            return true;
        }
        else if(limit==0) return false;
        else{
            ArrayList<Node> children = current.generateSuccessors();
            for(int i=0;i<children.size();i++){
                Node child = children.get(i);
                result = recursiveDLS(child, limit-1);
                if(result) return true;

            }

        }
        return result;
    }
}
