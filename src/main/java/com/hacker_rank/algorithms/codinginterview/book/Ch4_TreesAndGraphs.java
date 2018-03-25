package com.hacker_rank.algorithms.codinginterview.book;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;
import com.hacker_rank.algorithms.codinginterview.book.util.TreeNode;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

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

    /**
     * Check Balanced: Implement a function to check if a binary tree is balanced.
     * Defined such that the heights of the two subtrees of any node never differ
     * by more than one.
     */
    public static boolean checkBalanced(TreeNode root) {
        return root == null || getHeight(root) != Integer.MIN_VALUE;
    }

    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == Integer.MIN_VALUE) {
            return leftHeight;
        }
        if (rightHeight == Integer.MIN_VALUE) {
            return rightHeight;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return Integer.MIN_VALUE;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 4.5 Validate BST: Check if a binary tree is a binary search tree
     */
    public static boolean isBinarySearchTree(TreeNode root) {
        return isBinarySearchTree(root.left, Integer.MIN_VALUE, root.val)
                && isBinarySearchTree(root.right, root.val, Integer.MAX_VALUE);
    }

    private static boolean isBinarySearchTree(TreeNode root, int minValue, int maxValue) {
        if (root == null) {
            return true;
        }
        if (root.val > maxValue || root.val < minValue) {
            return false;
        }
        return isBinarySearchTree(root.left, minValue, root.val) && isBinarySearchTree(root.right, root.val, maxValue);
    }

    /**
     * 4.6 Successor: Write an algorithm to find the next node (in-order successor) of a given
     * node in a binary search tree. Assume each node has a link to its parent
     */
    public static TreeNode getSuccessor(TreeNode node) {
        if (node.right == null) {
            TreeNode curr = node;
            TreeNode parent = node.parent;
            while (parent != null && parent.left != curr) {
                curr = parent;
                parent = curr.parent;
            }
            return parent;
        }
        return findSuccessor(node.right);
    }

    private static TreeNode findSuccessor(TreeNode node) {
        if (node.left == null) {
            return node;
        }
        return findSuccessor(node.left);
    }

    /**
     * 4.7 Build Order: Given a list of projects and dependencies, all of a projects dependencies
     * must be built before the project is. Find a build order that will allow the projects to be
     * built.
     * <p>
     * e.g
     * Input:
     * projects: a, b, c, d, e, f
     * dependencies: (a, d), (f, b), (b, d), (f, a), (d, c)
     * Output: f, e, a, b, d, c
     */
    public static List<Character> createBuildOrder(
            List<Character> projects,
            List<SimpleEntry<Character, Character>> dependencies
    ) {
        Graph<Character> graph = constructGraph(projects, dependencies);
        return createBuildOrder(graph).stream().map(GenericGraphNode::getVal).collect(Collectors.toList());
    }

    private static Graph<Character> constructGraph(
            List<Character> projects,
            List<SimpleEntry<Character, Character>> dependencies
    ) {
        final Graph<Character> graph = new Graph<>();
        for (Character project : projects) {
            graph.getNodeOrCreate(project);
        }

        for (SimpleEntry<Character, Character> graphEntry : dependencies) {
            final Character key = graphEntry.getKey();
            final Character value = graphEntry.getValue();
            graph.addEdge(key, value);
        }

        return graph;
    }

    private static <T> List<GenericGraphNode<T>> createBuildOrder(Graph<T> graph) {
        final List<GenericGraphNode<T>> buildOrder = new ArrayList<>(graph.getNodes().size());

        addRootNodes(graph.getNodes(), buildOrder);

        //Process remaining
        int toBeProcessed = 0;
        while (toBeProcessed < graph.getNodes().size()) {
            if (toBeProcessed > buildOrder.size() - 1) {
                throw new IllegalStateException("Cannot build projects - circular dependency");
            }
            final GenericGraphNode<T> curr = buildOrder.get(toBeProcessed);

            for (GenericGraphNode<T> child : curr.getChildren()) {
                child.getParents().remove(curr);
            }

            addRootNodes(curr.getChildren(), buildOrder);
            toBeProcessed++;
        }

        return buildOrder;
    }

    private static <T> void addRootNodes(List<GenericGraphNode<T>> nodes, List<GenericGraphNode<T>> buildOrder) {
        //Add nodes with no dependencies
        for (GenericGraphNode<T> node : nodes) {
            if (node.getParents().size() == 0) {
                buildOrder.add(node);
            }
        }
    }

    private static final class Graph<T> {
        private final List<GenericGraphNode<T>> nodes = new ArrayList<>();
        private final Map<T, GenericGraphNode<T>> nodeKeyMap = new HashMap<>();

        public List<GenericGraphNode<T>> getNodes() {
            return nodes;
        }

        GenericGraphNode<T> getNodeOrCreate(T key) {
            GenericGraphNode<T> existing = nodeKeyMap.get(key);
            if (existing == null) {
                existing = new GenericGraphNode<>(key);
                nodes.add(existing);
                nodeKeyMap.put(key, existing);
            }
            return existing;
        }

        public void addEdge(T from, T to) {
            final GenericGraphNode<T> fromNode = getNodeOrCreate(from);
            final GenericGraphNode<T> toNode = getNodeOrCreate(to);

            fromNode.getChildren().add(toNode);
            toNode.getParents().add(fromNode);
        }
    }

    private static final class GenericGraphNode<T> {
        private final List<GenericGraphNode<T>> children = new ArrayList<>();
        private final List<GenericGraphNode<T>> parents = new ArrayList<>();

        private final T val;

        GenericGraphNode(T val) {
            this.val = val;
        }

        T getVal() {
            return val;
        }

        List<GenericGraphNode<T>> getParents() {
            return parents;
        }

        List<GenericGraphNode<T>> getChildren() {
            return children;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GenericGraphNode<?> that = (GenericGraphNode<?>) o;
            return Objects.equals(val, that.val);
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }

        @Override
        public String toString() {
            return "GenericGraphNode{" +
                    "val=" + val +
                    '}';
        }
    }
}
