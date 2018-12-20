package com.nedink.dijkstra;

public class Node implements Comparable<Node> {

    String name;
    Node previous;
    int distFromStart = 1000;

    public Node(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Node o) {
        return name.compareTo(o.name);
    }

    public int compareDistFromStart(Node o) {
        return distFromStart - o.distFromStart;
    }

    @Override
    public String toString() {
        return  name + " (" + distFromStart + ", " + (previous == null ? "~" : previous.name) + ")";
    }
}
