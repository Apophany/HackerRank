package com.hacker_rank.algorithms.codinginterview.book;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;

import java.util.LinkedList;
import java.util.Queue;

public class Ch4_TreesAndGraphs {
    /**
     * 4.1 Given a directed graph, design an algorithm to
     * find out if there is a route between two nodes
     */
    public static boolean route_between_nodes(GraphNode a, GraphNode b) {
        //Perform BFS from a, and look for b
        boolean aToB = search(a, b);
        boolean bToA = search(b, a);

        return aToB | bToA;
    }

    private static boolean search(GraphNode a, GraphNode b) {
        Queue<GraphNode> toSearch = new LinkedList<>();
        toSearch.add(a);

        while (!toSearch.isEmpty()) {
            GraphNode current = toSearch.poll();
            if (current.isVisited) {
                continue;
            }
            if (current == b) {
                return true;
            }
            current.isVisited = true;
            if (current.children == null) {
                continue;
            }
            for (GraphNode children : current.children) {
                if (children != null && !children.isVisited) {
                    toSearch.offer(children);
                }
            }
        }
        return false;
    }
}
