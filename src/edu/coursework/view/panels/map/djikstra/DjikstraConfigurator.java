package edu.coursework.view.panels.map.djikstra;

import edu.coursework.model.BaseEvent;
import edu.coursework.model.djikstra.Edge;
import edu.coursework.model.djikstra.Vertex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DjikstraConfigurator {

    private BaseEvent firstEvent;
    private BaseEvent secondEvent;

    public static void computePaths(Vertex source) {
        source.setMinDistance(0.);
        PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
        vertexQueue.add(source);

        while (!vertexQueue.isEmpty()) {
            Vertex u = vertexQueue.poll();

            // Visit each edge exiting u
            for (Edge e : u.getDestinations()) {
                Vertex v = e.getTarget();
                double weight = e.getWeight();
                double distanceThroughU = u.getMinDistance() + weight;
                if (distanceThroughU < v.getMinDistance()) {
                    vertexQueue.remove(v);

                    v.setMinDistance(distanceThroughU);
                    v.setPrevious(u);
                    vertexQueue.add(v);
                }
            }
        }
    }

    public static List<Vertex> getShortestPathTo(Vertex target) {
        List<Vertex> path = new ArrayList<>();
        for (Vertex vertex = target; vertex != null; vertex = vertex.getPrevious())
            path.add(vertex);

        Collections.reverse(path);
        return path;
    }

}
