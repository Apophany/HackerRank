package com.hacker_rank.algorithms.codinginterview;

import com.hacker_rank.algorithms.codinginterview.book.util.GraphNode;
import org.junit.Assert;
import org.junit.Test;

import static com.hacker_rank.algorithms.codinginterview.book.Ch4_TreesAndGraphs.route_between_nodes;

public class Ch4_TreesAndGraphsTest {

    @Test
    public void test_route_between_nodes() {
        GraphNode toFind = new GraphNode();
        toFind.children = new GraphNode[1];
        GraphNode root = constructGraph(null);
        toFind.children[0] = root;

        Assert.assertTrue(route_between_nodes(root, toFind));
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
}
