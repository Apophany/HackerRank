package com.hacker_rank.algorithms.codinginterview.book;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;

import java.util.LinkedList;
import java.util.Queue;

public class GraphIteration {
    /**
     * DFS of a graph. Equivalent to pre order
     * binary tree search. Visits root node first,
     * followed by children left to right.
     */
    public static void depthFirstSearch(GraphNode graphNode) {
        if (graphNode == null || graphNode.isVisited) {
            return;
        }

        graphNode.isVisited = true;
        System.out.println(graphNode.val);

        for (int i = 0; i < graphNode.children.length; i++) {
            depthFirstSearch(graphNode.children[i]);
        }
    }

    /**
     * BFS of a graph. Visits the root, followed
     * by all children before exploring further
     */
    public static void breadthFirstSearch(GraphNode graphNode) {
        final Queue<GraphNode> toSearch = new LinkedList<>();
        toSearch.offer(graphNode);

        while (!toSearch.isEmpty()) {
            GraphNode node = toSearch.poll();

            graphNode.isVisited = true;
            System.out.println(graphNode.val);

            for (int i = 0; i < node.children.length; i++) {
                if (!node.children[i].isVisited) {
                    toSearch.offer(node.children[i]);
                }
            }
        }
    }
}
