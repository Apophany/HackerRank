package com.hacker_rank.algorithms.graphtheory;

import java.util.*;

public class RoadsAndLibraries {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        int q = in.nextInt();

        for (int a0 = 0; a0 < q; a0++) {
            int numCities = in.nextInt();
            int numRoads = in.nextInt();
            long costLib = in.nextLong();
            long costRoad = in.nextLong();

            final Graph g = new Graph();
            for (int i = 1; i <= numCities; i++) {
                Node n = new Node();
                n.value = i;
                g.nodes.put(i, n);
            }
            for (int a1 = 0; a1 < numRoads; a1++) {
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();

                Node a = g.nodes.get(city_1);
                Node b = g.nodes.get(city_2);

                a.neighbours.add(b);
                b.neighbours.add(a);
            }

            long cost;
            if (costLib > costRoad) {
                int[] itemsBuilt = traverseGraph(g);
                cost = itemsBuilt[0] * costRoad + itemsBuilt[1] * costLib;
            } else {
                cost = costLib * numCities;
            }
            System.out.println(cost);
        }
    }

    private static int[] traverseGraph(Graph g) {
        int builtRoads = 0;
        int builtLibs = 0;
        for (Map.Entry<Integer, Node> entry : g.nodes.entrySet()) {
            if (!entry.getValue().isVisited) {
                builtLibs++;
                builtRoads += traverseSubGraph(entry.getValue(), true);
            }
        }
        return new int[]{builtRoads, builtLibs};
    }

    private static int traverseSubGraph(Node n, boolean root) {
        if (n.isVisited) {
            return 0;
        }
        n.isVisited = true;

        int builtRoads = 0;
        if (!root) {
            builtRoads++;
        }
        for (Node neighbour : n.neighbours) {
            builtRoads += traverseSubGraph(neighbour, false);
        }
        return builtRoads;
    }

    public static class Graph {
        final HashMap<Integer, Node> nodes = new HashMap<>();
    }

    public static class Node {
        final Set<Node> neighbours = new HashSet<>();
        int value = -1;
        boolean isVisited = false;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Node)) {
                return false;
            }
            Node otherNode = (Node) other;
            return this.value == otherNode.value;
        }
    }
}

