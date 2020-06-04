package edu.coursework.model.djikstra;

import edu.coursework.model.BaseEvent;

public class Vertex implements Comparable<Vertex> {

    private String name;
    private Edge[] adjacencies;
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous;
    private BaseEvent event;

    public Vertex(String name, BaseEvent event) {
        this.name = name;
        this.event = event;
    }

    public String toString() {
        return name;
    }

    public int compareTo(Vertex other) {
        return Double.compare(minDistance, other.minDistance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Edge[] getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(Edge[] adjacencies) {
        this.adjacencies = adjacencies;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    public BaseEvent getEvent() {
        return event;
    }

    public void setEvent(BaseEvent event) {
        this.event = event;
    }
}
