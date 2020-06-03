package edu.coursework.model;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    private Set<BaseEvent> events = new HashSet<>();

    public void addNode(BaseEvent baseEvent) {
        events.add(baseEvent);
    }

}
