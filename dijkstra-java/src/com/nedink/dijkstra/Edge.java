package com.nedink.dijkstra;

public class Edge implements Comparable<Edge> {

    Node start;
    Node end;
    int weight;

    public Edge(Node start, Node end, int weight) {
        this.start = start.name.compareTo(end.name) < 0 ? start : end;
        this.end = start.name.compareTo(end.name) < 0 ? end : start;
        this.weight = weight;
    }

    public boolean containsNode(Node node) {
        return start == node || end == node;
    }

    public Node getOtherNode(Node node) {
        return start == node ? end : end == node ? start : null;
    }

    @Override
    public int compareTo(Edge o) {
        return start.compareTo(o.start) == 0 ? end.compareTo(o.end) : start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return start + " - " + weight + " - " + end;
    }
}
