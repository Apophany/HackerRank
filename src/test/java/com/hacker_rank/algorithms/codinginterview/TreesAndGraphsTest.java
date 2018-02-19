package com.hacker_rank.algorithms.codinginterview;

import org.junit.Test;

import static com.hacker_rank.algorithms.codinginterview.TreesAndGraphs.inOrder;
import static com.hacker_rank.algorithms.codinginterview.TreesAndGraphs.postOrder;
import static com.hacker_rank.algorithms.codinginterview.TreesAndGraphs.preOrder;

public class TreesAndGraphsTest {

    private final TreesAndGraphs.Node fullTree = constructFullTree();

    @Test
    public void test_inOrder_Full() {
        inOrder(fullTree);
    }

    @Test
    public void test_preOrder_Full() {
        preOrder(fullTree);
    }

    @Test
    public void test_postOrder_Full() {
        postOrder(fullTree);
    }

    private TreesAndGraphs.Node constructFullTree() {
        TreesAndGraphs.Node fifth = new TreesAndGraphs.Node();
        fifth.val = 5;

        TreesAndGraphs.Node fourth = new TreesAndGraphs.Node();
        fourth.val = 3;

        TreesAndGraphs.Node third = new TreesAndGraphs.Node();
        third.val = 7;

        TreesAndGraphs.Node second = new TreesAndGraphs.Node();
        second.val = 4;
        second.left = fourth;
        second.right = fifth;

        TreesAndGraphs.Node first = new TreesAndGraphs.Node();
        first.val = 6;
        first.left = second;
        first.right = third;

        return first;
    }
}
