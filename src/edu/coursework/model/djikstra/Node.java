package edu.coursework.model.djikstra;

import edu.coursework.model.BaseEvent;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node {

    private String name;

    private List<Node> shortestPath = new LinkedList<>();

    private Integer distance = Integer.MAX_VALUE;

    private BaseEvent nodeEvent;

    Map<Node, Integer> adjacentNodes = new HashMap<>();

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    public Node(String name, BaseEvent nodeEvent) {
        this.name = name;
        this.nodeEvent = nodeEvent;
    }

    public String getName() {
        return name;
    }

    public List<Node> getShortestPath() {
        return shortestPath;
    }

    public Integer getDistance() {
        return distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }

    public BaseEvent getNodeEvent() {
        return nodeEvent;
    }

    public void setNodeEvent(BaseEvent nodeEvent) {
        this.nodeEvent = nodeEvent;
    }
}
