package com.hacker_rank.algorithms.codinginterview;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;
import com.hacker_rank.algorithms.codinginterview.book.util.TreeNode;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

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

    @Test
    public void test_balanced_tree() {
        final TreeNode root = constructTree();
        Assert.assertTrue(checkBalanced(root));

        //Make unbalanced
        root.left.left.left = new TreeNode();

        Assert.assertFalse(checkBalanced(root));
    }

    @Test
    public void test_valid_BST() {
        final TreeNode root = constructTree();
        Assert.assertTrue(isBinarySearchTree(root));

        final TreeNode invalidNode = new TreeNode(5);
        root.left.left.right = invalidNode;

        Assert.assertFalse(isBinarySearchTree(root));
    }

    @Test
    public void test_successor() {
        final TreeNode a_1 = new TreeNode(20);
        final TreeNode a_2 = new TreeNode(12);
        final TreeNode b_2 = new TreeNode(25);
        final TreeNode a_3 = new TreeNode(4);
        final TreeNode b_3 = new TreeNode(16);
        final TreeNode c_3 = new TreeNode(21);
        final TreeNode d_3 = new TreeNode(29);

        a_1.left = a_2;
        a_1.right = b_2;
        a_2.parent = a_1;
        b_2.parent = a_1;

        b_2.left = c_3;
        b_2.right = d_3;
        c_3.parent = b_2;
        d_3.parent = b_2;

        a_2.left = a_3;
        a_2.right = b_3;
        a_3.parent = a_2;
        b_3.parent = a_2;

        Assert.assertEquals(b_2, getSuccessor(c_3));
        Assert.assertEquals(b_3, getSuccessor(a_2));
        Assert.assertEquals(a_1, getSuccessor(b_3));
        Assert.assertEquals(c_3, getSuccessor(a_1));
        Assert.assertEquals(null, getSuccessor(d_3));
    }

    @Test
    public void test_build_order() {
        final List<Character> projects = new ArrayList<>();
        projects.add('a');
        projects.add('b');
        projects.add('c');
        projects.add('d');
        projects.add('e');
        projects.add('f');

        final List<AbstractMap.SimpleEntry<Character, Character>> dependencies = new ArrayList<>();
        dependencies.add(new AbstractMap.SimpleEntry<>('a', 'd'));
        dependencies.add(new AbstractMap.SimpleEntry<>('f', 'b'));
        dependencies.add(new AbstractMap.SimpleEntry<>('b', 'd'));
        dependencies.add(new AbstractMap.SimpleEntry<>('f', 'a'));
        dependencies.add(new AbstractMap.SimpleEntry<>('d', 'c'));

        System.out.println(createBuildOrder(projects, dependencies));
    }

    @Test
    public void test_common_ancestor() {
        final TreeNode root = new TreeNode(20);
        final TreeNode a_1 = new TreeNode(10);
        final TreeNode b_1 = new TreeNode(30);
        final TreeNode a_2 = new TreeNode(5);
        final TreeNode b_2 = new TreeNode(15);
        final TreeNode a_3 = new TreeNode(3);
        final TreeNode b_3 = new TreeNode(7);
        final TreeNode c_3 = new TreeNode(17);

        root.left = a_1;
        root.right = b_1;
        a_1.left = a_2;
        a_1.right = b_2;
        a_2.left = a_3;
        a_2.right = b_3;
        b_2.right = c_3;

        Assert.assertEquals(a_1, getFirstCommonAncestor(root, new TreeNode(5), new TreeNode(17)));
    }

    @Test
    public void test_common_ancestor_missing_node() {
        final TreeNode root = new TreeNode(3);
        final TreeNode a_1 = new TreeNode(1);
        final TreeNode b_1 = new TreeNode(5);
        final TreeNode b_2 = new TreeNode(8);

        root.left = a_1;
        root.right = b_1;
        b_1.right = b_2;

        Assert.assertNull(getFirstCommonAncestor(root, new TreeNode(5), new TreeNode(7)));
    }

    @Test
    public void test_bst_sequences() {
        final TreeNode root = new TreeNode(10);
        final TreeNode a_1 = new TreeNode(2);
        final TreeNode b_1 = new TreeNode(3);
        final TreeNode a_2 = new TreeNode(1);
        final TreeNode b_2 = new TreeNode(4);

        root.left = a_1;
        root.right = b_1;
        a_1.left = a_2;
        b_1.right = b_2;

        System.out.println(getBSTSequences(root));
    }

    @Test
    public void test_subtree() {
        final TreeNode root = constructTree();

        TreeNode subTreeRoot = new TreeNode(4);
        TreeNode subTree_a_1 = new TreeNode(3);
        TreeNode subTree_b_1 = new TreeNode(5);

        subTreeRoot.left = subTree_a_1;
        subTreeRoot.right = subTree_b_1;

        Assert.assertTrue(isSubtree(root, subTreeRoot));

        subTreeRoot.left = subTree_b_1;
        subTreeRoot.right = subTree_a_1;

        Assert.assertFalse(isSubtree(root, subTreeRoot));
    }

    @Test
    public void test_get_random_node() {
        final TreeNode root = constructTree();
        Assert.assertNotNull(getRandomNode(root, 5));
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
        fourth.left = null;
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

        fifth.parent = second;
        fourth.parent = second;
        third.parent = first;
        second.parent = first;

        return first;
    }
}
