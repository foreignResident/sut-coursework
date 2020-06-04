package edu.coursework.controller;

import edu.coursework.model.*;
import edu.coursework.model.djikstra.Vertex;
import edu.coursework.utils.Dimensions;
import edu.coursework.view.panels.controls.ControlsPanel;
import edu.coursework.view.panels.controls.EventRowItem;
import edu.coursework.view.panels.map.MainMapPanel;
import edu.coursework.view.panels.map.djikstra.DjikstraPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController implements MouseListener {

    private MainMapPanel mainMapPanel;
    private ControlsPanel controlsPanel;
    private List<BaseEvent> eventList = new ArrayList<>();

    private static int count = 0;

    public void attachViews(MainMapPanel mainMapPanel, ControlsPanel controlsPanel) {
        this.mainMapPanel = mainMapPanel;
        this.controlsPanel = controlsPanel;
        mainMapPanel.getMapPanel().addMouseListener(this);
    }

    //process show event btn click
    public void onEventsShowClicked() {
        List<EventRowItem> eventRowItems = controlsPanel.getEventsPanel().getSelectedElements();
        if (controlsPanel.getEventsScalePanel().getIsFixedSize()) {
            //if event have fixed scale generate it
            for (EventRowItem eventRowItem : eventRowItems) {
                eventList.addAll(generateEvents(
                        eventRowItem.getFromTextField(),
                        eventRowItem.getToTextField(),
                        controlsPanel.getEventsScalePanel().getFixedSize(),
                        eventRowItem.getFigure()));
            }
        } else {
            //event needs to generate it scale in given range
            for (EventRowItem eventRowItem : eventRowItems) {
                eventList.addAll(generateEvents(
                        eventRowItem.getFromTextField(),
                        eventRowItem.getToTextField(),
                        controlsPanel.getEventsScalePanel().getFromText(),
                        controlsPanel.getEventsScalePanel().getToText(),
                        eventRowItem.getFigure()));
            }
        }
        mainMapPanel.getMapPanel().setEventList(eventList);
        calculateAmountStatistics();
        calculateScaleStatistics();
    }

    //generate random amount of events in given scale
    private List<BaseEvent> generateEvents(int fromAmount, int toAmount, int scale, Figure figure) {
        List<BaseEvent> eventsList = new ArrayList<>();
        int eventsAmount = randomInRange(fromAmount, toAmount);
        //create events in following amount
        for (int i = 0; i < eventsAmount; i++) {
            switch (figure) {
                case Rectangle:
                    eventsList.add(new RectangleEvent(
                            scale,
                            randomInRange(0, Dimensions.MAP_WIDTH),
                            randomInRange(0, Dimensions.MAP_HEIGHT),
                            figure
                    ));
                    break;
                case Triangle:
                    eventsList.add(new TriangleEvent(
                            scale,
                            randomInRange(0, Dimensions.MAP_WIDTH),
                            randomInRange(0, Dimensions.MAP_HEIGHT),
                            figure
                    ));
                    break;
                case Circle:
                    eventsList.add(new CircleEvent(
                            scale,
                            randomInRange(0, Dimensions.MAP_WIDTH),
                            randomInRange(0, Dimensions.MAP_HEIGHT),
                            figure
                    ));
                    break;
            }
        }
        return eventsList;
    }

    //generate random amount of events in ranged scale
    private List<BaseEvent> generateEvents(int fromAmount, int toAmount, int fromScale, int toScale, Figure figure) {
        List<BaseEvent> eventsList = new ArrayList<>();
        int eventsAmount = randomInRange(fromAmount, toAmount);
        for (int i = 0; i < eventsAmount; i++) {
            switch (figure) {
                case Rectangle:
                    eventsList.add(new RectangleEvent(
                            randomInRange(fromScale, toScale),
                            randomInRange(0, Dimensions.MAP_WIDTH),
                            randomInRange(0, Dimensions.MAP_HEIGHT),
                            figure
                    ));
                    break;
                case Triangle:
                    eventsList.add(new TriangleEvent(
                            randomInRange(fromScale, toScale),
                            randomInRange(0, Dimensions.MAP_WIDTH),
                            randomInRange(0, Dimensions.MAP_HEIGHT),
                            figure
                    ));
                    break;
                case Circle:
                    eventsList.add(new CircleEvent(
                            randomInRange(fromScale, toScale),
                            randomInRange(0, Dimensions.MAP_WIDTH),
                            randomInRange(0, Dimensions.MAP_HEIGHT),
                            figure
                    ));
                    break;
            }
        }
        return eventsList;
    }

    //generate random value in given range [from;to]
    private int randomInRange(int from, int to) {
        return (int) (Math.random() * (to - from) + 1 + from);
    }

    //clear map
    public void clearEvents() {
        eventList.clear();
        mainMapPanel.getMapPanel().setEventList(eventList);
        mainMapPanel.getMapPanel().setLines(new ArrayList<>());
        mainMapPanel.getMapPanel().repaint();
        controlsPanel.getAmountStatisticPanel().setStatistics(null);
        controlsPanel.getScaleStatisticPanel().setStatistics(null);
    }

    public void changeDrawingStatus(String status) {
        controlsPanel.getEventsButtonsPanel().setStatusText(status);
    }

    private void calculateAmountStatistics() {
        int[] amountStatistics = new int[3];
        //count events every type
        for (BaseEvent figure : eventList) {
            switch (figure.getFigure()) {
                case Triangle:
                    System.out.println("Triangle added");
                    amountStatistics[0]++;
                    break;
                case Rectangle:
                    System.out.println("Rectangle added");
                    amountStatistics[1]++;
                    break;
                case Circle:
                    System.out.println("Circle added");
                    amountStatistics[2]++;
                    break;

            }
        }
        //draw
        controlsPanel.getAmountStatisticPanel().setStatistics(amountStatistics);
    }

    private void calculateScaleStatistics() {
        int[] scaleStatistics = new int[3];

        //count max scale
        int maxScale = 0;
        for (BaseEvent figure : eventList) {
            if (figure.getScale() > maxScale) {
                maxScale = figure.getScale();
            }
        }

        //calculate 30% 60% form max
        int classA = (int) Math.round(maxScale * 0.3);
        int classB = (int) Math.round(maxScale * 0.6);

        //find out each class class
        for (BaseEvent figure : eventList) {
            if (figure.getScale() <= classA) {
                scaleStatistics[0]++;
            } else if (figure.getScale() > classA && figure.getScale() <= classB) {
                scaleStatistics[1]++;
            } else {
                scaleStatistics[2]++;
            }
        }
        //draw
        controlsPanel.getScaleStatisticPanel().setStatistics(scaleStatistics);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (mainMapPanel.getDjikstraPanel().isEnabled()) {
            Point point = e.getPoint();
            BaseEvent baseEvent = null;
            DjikstraPanel djikstraPanel = mainMapPanel.getDjikstraPanel();
            switch (djikstraPanel.getActionCommand()) {
                case "Triangle":
                    baseEvent = new TriangleEvent(
                            djikstraPanel.getScale(),
                            (int) point.getX(),
                            (int) point.getY(),
                            Figure.Djikstra
                    );
                    break;
                case "Circle":
                    baseEvent = new CircleEvent(
                            djikstraPanel.getScale(),
                            (int) point.getX(),
                            (int) point.getY(),
                            Figure.Djikstra
                    );
                    break;
                case "Rectangle":
                    baseEvent = new RectangleEvent(
                            djikstraPanel.getScale(),
                            (int) point.getX(),
                            (int) point.getY(),
                            Figure.Djikstra
                    );

                    break;
            }


            mainMapPanel.getMapPanel().addEventToList(baseEvent);
            //createNode(baseEvent);

            createVertex(baseEvent);

        }
    }

    private void createVertex(BaseEvent baseEvent) {
        Vertex vertex = new Vertex(baseEvent.getFigure() + " " + baseEvent.getPositionX() + " " + baseEvent.getPositionY() + " " + count, baseEvent);
        count++;
        List<BaseEvent> eventsInArea = findNearestEvents(baseEvent);
        eventsInArea.forEach(temp -> System.out.println(temp.getFigure() + " " + temp.getPositionY() + " " + temp.getPositionX()));

        //add destinations



    }

   /* private void createNode(BaseEvent baseEvent) {


        Graph graph = new Graph();
        graph.addNode(mainNode);
        //add destinations

        graph = findDestination(mainNode, baseEvent, eventsInArea, graph);

        graph = graph.calculateShortestPathFromSource(graph, mainNode);
        for (Node temp : graph.getNodes()) {
            for (Map.Entry<Node, Integer> entry : temp.getAdjacentNodes().entrySet()) {
                System.out.println("NODE DETECT " + entry.getKey().getName() + " " + entry.getValue());
            }
        }

        mainNode.getAdjacentNodes().forEach(
                (node, integer) -> System.out.println("MAIN NODE " + mainNode.getName() + " " + node.getName() + " and between them distance is " + integer)
        );

        System.out.println("PATH ");
        for(Node temp : mainNode.getShortestPath()){
            System.out.println(temp.getName());
        }


        mainMapPanel.getMapPanel().repaint();
    }*/

    /*private Graph findDestination(Node mainNode, BaseEvent baseEvent, List<BaseEvent> eventsInArea, Graph graph) {
        for (BaseEvent temp : eventsInArea) {
            Node node = new Node(temp.getFigure() + " " + temp.getPositionX() + " " + temp.getPositionY() + " " + count, temp);
            int distance = countDestination(baseEvent.getPositionX(), baseEvent.getPositionY(),
                    temp.getPositionX(), temp.getPositionY());

            mainNode.addDestination(node, distance);
            node.addDestination(mainNode, distance);
            graph.addNode(node);

            System.out.println();
            mainMapPanel.getMapPanel().addLine(
                    new Line2D.Double(baseEvent.getPositionX(), baseEvent.getPositionY(), temp.getPositionX(), temp.getPositionY())
            );
            mainMapPanel.getMapPanel().repaint();

            List<BaseEvent> nearestEvents = findNearestEvents(temp);
            if (nearestEvents != null && nearestEvents.size() > 0) {
                List<BaseEvent> newNewTest = new ArrayList<>();
                for (BaseEvent nearestEvent : nearestEvents) {
                    for (Map.Entry<Node, Integer> entry : node.getAdjacentNodes().entrySet()) {
                        if (!nearestEvents.contains(entry.getKey().getNodeEvent())) {
                            newNewTest.add(nearestEvent);
                        }
                    }

                }
                findDestination(node, temp, newNewTest, graph);
            }

            count++;
        }

        return graph;
    }*/

    private List<BaseEvent> findNearestEvents(BaseEvent mainEvent) {
        int area = mainEvent.getScale() * 3;

        int firstX = mainEvent.getPositionX() - area;
        int secondX = mainEvent.getPositionX() + area;

        int firstY = mainEvent.getPositionY() - area;
        int secondY = mainEvent.getPositionY() + area;

        return eventList.stream()
                .filter(temp -> temp.getPositionX() >= firstX && temp.getPositionX() <= secondX)
                .filter(temp -> temp.getPositionY() >= firstY && temp.getPositionY() <= secondY)
                .collect(Collectors.toList());
    }

    private int countDestination(int x1, int y1, int x2, int y2) {
        int x = (int) (Math.pow(x2 - x1, 2));
        int y = (int) (Math.pow(y2 - y1, 2));
        return (int) Math.sqrt(x + y);
    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
