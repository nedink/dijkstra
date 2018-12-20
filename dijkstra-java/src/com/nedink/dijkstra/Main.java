package com.nedink.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static List<Node> dijkstras(List<Edge> graph, Node fromNode, Node toNode) {

        // initialize unvisited nodes set
        List<Node> unvisited = new ArrayList<>();
        for (Edge edge : graph) {
            Node start = edge.start;
            Node end = edge.end;
            if (!unvisited.contains(start))
                unvisited.add(start);
            if (!unvisited.contains(end))
                unvisited.add(end);
        }

        fromNode.distFromStart = 0;

        while (unvisited.size() > 0) {
            unvisited.sort(Node::compareDistFromStart);
            Node currentNode = unvisited.get(0); // get next unvisited node with smallest distance from start node
            List<Edge> arms = new ArrayList<>();
            for (Edge edge : graph)
                if (edge.containsNode(currentNode) && unvisited.contains(edge.getOtherNode(currentNode)))
                    arms.add(edge);
            for (Edge edge : arms) {
                Node otherNode = edge.getOtherNode(currentNode);
                if (otherNode.distFromStart > currentNode.distFromStart + edge.weight) {
                    otherNode.distFromStart = currentNode.distFromStart + edge.weight;
                    otherNode.previous = currentNode;
                }
            }
            unvisited.remove(currentNode);
        }

        List<Node> shortestPath = new ArrayList<>();
        Node currentNode = toNode;
        while (currentNode != null) {
            shortestPath.add(currentNode);
            currentNode = currentNode.previous;
        }
        Collections.reverse(shortestPath);
        return shortestPath;
    }

    public static void main(String[] args) {

        List<Edge> graph = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();

        /*
         * Preset
         */
//        Node nodeA = new Node("a");
//        Node nodeB = new Node("b");
//        Node nodeC = new Node("c");
//        Node nodeD = new Node("d");
//        Node nodeE = new Node("e");
//        Node nodeF = new Node("f");
//
//        Edge edge1 = new Edge(nodeA, nodeB, 9);
//        Edge edge2 = new Edge(nodeA, nodeC, 1);
//        Edge edge3 = new Edge(nodeB, nodeC, 5);
//        Edge edge4 = new Edge(nodeB, nodeD, 3);
//        Edge edge5 = new Edge(nodeB, nodeF, 7);
//        Edge edge6 = new Edge(nodeC, nodeE, 8);
//        Edge edge7 = new Edge(nodeD, nodeE, 1);
//        Edge edge8 = new Edge(nodeD, nodeF, 8);
//        Edge edge9 = new Edge(nodeE, nodeF, 2);
//
//        nodes.add(nodeA);
//        nodes.add(nodeB);
//        nodes.add(nodeC);
//        nodes.add(nodeD);
//        nodes.add(nodeE);
//        nodes.add(nodeF);
//
//        graph.add(edge1);
//        graph.add(edge2);
//        graph.add(edge3);
//        graph.add(edge4);
//        graph.add(edge5);
//        graph.add(edge6);
//        graph.add(edge7);
//        graph.add(edge8);
//        graph.add(edge9);

        /*
         * Random
         */
        for (char i = 'a'; i < 'g'; i++) {
            nodes.add(new Node(i + ""));
        }

        List<Node> middle = nodes.subList(1, nodes.size() - 1);
        Collections.shuffle(middle);
        for (int i = 0; i < nodes.size() - 1; i++) {
            graph.add(new Edge(nodes.get(i), nodes.get(i + 1), (int) (Math.random() * 10) + 1));
        }
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(nodes);
            Node start = nodes.get(0);
            Node end = nodes.get(1);
            boolean skip = false;
            for (Edge edge : graph) {
                if ((edge.start == start && edge.end == end) || (edge.start == end && edge.end == start)) {
                    skip = true;
                    break;
                }
            }
            if (skip) continue;
            graph.add(new Edge(start, end, (int) (Math.random() * 10) + 1));
        }

        graph.sort(Edge::compareTo);
        for (Edge edge : graph) {
            System.out.println(edge.start.name + " - " + edge.weight + " - " + edge.end.name);
        }

        System.out.println("\n---\n");

        /*
         * Preset
         */
//        Node start = nodeA;
//        Node end = nodeF;

        /*
         * Random
         */
        nodes.sort(Node::compareTo);
        Node start = nodes.get(0);
        Node end = nodes.get(nodes.size() - 1);

        List<Node> shortestPath = dijkstras(graph, start, end);

        for (Node node : shortestPath) {
            System.out.println(node);
        }
    }
}
