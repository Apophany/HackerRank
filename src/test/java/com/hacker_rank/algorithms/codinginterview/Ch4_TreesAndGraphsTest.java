package com.hacker_rank.algorithms.codinginterview;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;
import com.hacker_rank.algorithms.codinginterview.book.util.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.hacker_rank.algorithms.codinginterview.book.Ch4_TreesAndGraphs.*;
import static com.hacker_rank.algorithms.codinginterview.book.TreeIteration.inOrder;

public class Ch4_TreesAndGraphsTest {

    @Test
    public void test_route_between_nodes() {
        GraphNode toFind = new GraphNode();
        toFind.children = new GraphNode[1];
        GraphNode root = constructGraph(null);
        toFind.children[0] = root;

        Assert.assertTrue(route_between_nodes(root, toFind));
    }

    @Test
    public void test_minimal_tree() {
        final int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        final TreeNode root = minimal_tree(arr);

        inOrder(root);
    }

    @Test
    public void test_list_of_depths() {
        final List<LinkedList<TreeNode>> levels = listOfDepths(constructTree());
        System.out.println(levels);
    }

    private GraphNode constructGraph(GraphNode toFind) {
        GraphNode root = new GraphNode();
        root.children = new GraphNode[2];

        final GraphNode zero = new GraphNode();
        final GraphNode one = new GraphNode();
        root.children[0] = zero;
        root.children[1] = one;

        zero.children = new GraphNode[2];

        final GraphNode zero_zero = new GraphNode();
        final GraphNode zero_one = new GraphNode();
        zero.children[0] = zero_zero;
        zero.children[1] = toFind;

        return root;
    }

    private TreeNode constructTree() {
        TreeNode fifth = new TreeNode();
        fifth.val = 5;

        TreeNode fourth = new TreeNode();
        fourth.val = 3;

        TreeNode third = new TreeNode();
        third.val = 7;

        TreeNode second = new TreeNode();
        second.val = 4;
        second.left = fourth;
        second.right = fifth;

        TreeNode first = new TreeNode();
        first.val = 6;
        first.left = second;
        first.right = third;

        return first;
    }
}
