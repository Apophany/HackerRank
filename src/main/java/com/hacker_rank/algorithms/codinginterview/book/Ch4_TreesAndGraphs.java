package com.hacker_rank.algorithms.codinginterview.book;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;
import com.hacker_rank.algorithms.codinginterview.book.util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Ch4_TreesAndGraphs {
    /**
     * 4.1 Given a directed graph, design an algorithm to
     * find out if there is a route between two nodes
     */
    public static boolean route_between_nodes(GraphNode start, GraphNode end) {
        final Queue<GraphNode> toSearch = new LinkedList<>();
        toSearch.add(start);

        while (!toSearch.isEmpty()) {
            final GraphNode current = toSearch.poll();
            if (current == null || current.isVisited) {
                continue;
            }
            if (current == end) {
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

    /**
     * 4.2 Minimal Tree: Given a sorted (increasing order) array
     * with unique integer elements, write an algorithm to create
     * a binary search tree with minimal height
     */
    public static TreeNode minimal_tree(int[] arr) {
        return createMinimalTree(arr, 0, arr.length - 1);
    }

    private static TreeNode createMinimalTree(int[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        final int mid = start + (end - start) / 2;
        final TreeNode node = new TreeNode(arr[mid]);
        node.left = createMinimalTree(arr, start, mid - 1);
        node.right = createMinimalTree(arr, mid + 1, end);

        return node;
    }

    public static void main(String[] args) {
        minimal_tree(new int[]{1, 2});
    }

    /**
     * 4.3 List of Depths: Given a binary tree, design an algorithm
     * which creates a linked list of all the nodes at each depth
     */
    public static List<LinkedList<TreeNode>> listOfDepths(TreeNode root) {
        final Queue<TreeNode> toSearch = new LinkedList<>();
        toSearch.offer(root);

        List<LinkedList<TreeNode>> levels = new ArrayList<>();

        LinkedList<TreeNode> currDepth = new LinkedList<>();
        while (!toSearch.isEmpty()) {
            currDepth.add(toSearch.poll());

            if (toSearch.isEmpty()) {
                levels.add(currDepth);

                for (TreeNode node : currDepth) {
                    if (node.left != null) {
                        toSearch.add(node.left);
                    }
                    if (node.right != null) {
                        toSearch.add(node.right);
                    }
                }
                currDepth = new LinkedList<>();
            }
        }

        return levels;
    }
}
