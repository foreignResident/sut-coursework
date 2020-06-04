package edu.coursework.model.djikstra;

import edu.coursework.model.BaseEvent;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex> {

    private String name;
    private List<Edge> destinations = new ArrayList<>();
    private double minDistance = Double.POSITIVE_INFINITY;
    private Vertex previous;
    private BaseEvent event;

    public Vertex(String name, BaseEvent event) {
        this.name = name;
        this.event = event;
    }

    public void addDestination(Edge edge) {
        this.destinations.add(edge);
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

    public List<Edge> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Edge> destinations) {
        this.destinations = destinations;
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
