package com.hacker_rank.algorithms.codinginterview;

import java.util.LinkedList;
import java.util.Queue;

public class TreesAndGraphs {

    /**
     * In order traversal of binary tree.
     * Visits the left child first, followed
     * by the root, and finally the right child.
     *
     * Results in ordered print output when the
     * binary tree is a binary search tree
     */
    public static void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.println(root.val);
        inOrder(root.right);
    }

    /**
     * Pre order traversal of binary tree.
     * Visits the root node first, followed
     * by the left child and finally the right.
     */
    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * Post order traversal of binary tree.
     * Vists the left child first, followed
     * by the right, and finally the root.
     */
    public static void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.val);
    }

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

    public static final class Node {
        public int val;

        public Node left;
        public Node right;
    }

    public static final class GraphNode {
        public int val;
        public boolean isVisited;

        public GraphNode[] children;
    }
}
