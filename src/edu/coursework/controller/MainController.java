package edu.coursework.controller;

import edu.coursework.model.*;
import edu.coursework.utils.Dimensions;
import edu.coursework.view.panels.controls.ControlsPanel;
import edu.coursework.view.panels.controls.EventRowItem;
import edu.coursework.view.panels.map.MapPanel;

import java.util.ArrayList;
import java.util.List;

public class MainController {

    private MapPanel mapPanel;
    private ControlsPanel controlsPanel;
    private List<BaseEvent> eventList = new ArrayList<>();

    public void attachViews(MapPanel mapPanel, ControlsPanel controlsPanel) {
        this.mapPanel = mapPanel;
        this.controlsPanel = controlsPanel;
    }

    //process show event btn click
    public void onEventsShowClicked() {
        List<EventRowItem> eventRowItems = controlsPanel.getEventsPanel().getSelectedElements();
        if(controlsPanel.getEventsScalePanel().getIsFixedSize()) {
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
        mapPanel.setEventList(eventList);
        calculateAmountStatistics();
        calculateScaleStatistics();
    }

    //generate random amount of events in given scale
    private List<BaseEvent> generateEvents(int fromAmount, int toAmount, int scale, Figure figure) {
        List<BaseEvent> eventsList = new ArrayList<>();
        int eventsAmount = randomInRange(fromAmount, toAmount);
        //create events in following amount
        for(int i = 0; i < eventsAmount; i++) {
            switch(figure) {
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
        for(int i = 0; i < eventsAmount; i++) {
            switch(figure) {
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
        return (int) (Math.random()*(to-from)+1+from);
    }

    //clear map
    public void clearEvents() {
        eventList.clear();
        mapPanel.setEventList(eventList);
        controlsPanel.getAmountStatisticPanel().setStatistics(null);
        controlsPanel.getScaleStatisticPanel().setStatistics(null);
    }

    public void changeDrawingStatus(String status) {
        controlsPanel.getEventsButtonsPanel().setStatusText(status);
    }

    private void calculateAmountStatistics() {
        int[] amountStatistics = new int[3];
        //count events every type
        for(BaseEvent figure: eventList) {
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
        for(BaseEvent figure: eventList) {
            if(figure.getScale() > maxScale) {
                maxScale = figure.getScale();
            }
        }

        //calculate 30% 60% form max
        int classA = (int)Math.round(maxScale*0.3);
        int classB = (int)Math.round(maxScale*0.6);

        //find out each class class
        for(BaseEvent figure: eventList) {
            if(figure.getScale() <= classA) {
                scaleStatistics[0]++;
            } else if(figure.getScale() > classA && figure.getScale() <= classB) {
                scaleStatistics[1]++;
            } else {
                scaleStatistics[2]++;
            }
        }
        //draw
        controlsPanel.getScaleStatisticPanel().setStatistics(scaleStatistics);
    }
}
