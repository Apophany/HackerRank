package codinginterview;

import codinginterview.book.util.TreeNode;
import org.junit.Test;

import static codinginterview.book.TreeIteration.*;

public class TreeIterationTest {
    private final TreeNode fullTree = constructFullTree();

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

    private TreeNode constructFullTree() {
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
