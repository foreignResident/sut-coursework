package edu.coursework.view.panels.controls;

import edu.coursework.controller.MainController;
import edu.coursework.model.Figure;
import edu.coursework.view.panels.BasePanel;

import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//panel for set up events
public class EventsPanel extends BasePanel {

    private List<EventRowItem> eventRowItemList = new ArrayList<>();

    public EventsPanel(int width, int height, Border border, MainController controller) {
        super(width, height, border, new FlowLayout(FlowLayout.LEFT));
        //create first row
        EventRowItem firstRow = new EventRowItem("Events of class \"A\"",
                new FlowLayout(FlowLayout.LEFT, 5, 3), Figure.Triangle, controller);
        eventRowItemList.add(firstRow);
        add(firstRow);

        //create second row
        EventRowItem secondRow = new EventRowItem("Events of class \"B\"",
                new FlowLayout(FlowLayout.LEFT, 5, 3), Figure.Rectangle, controller);
        eventRowItemList.add(secondRow);
        add(secondRow);

        //create third row
        EventRowItem thirdRow = new EventRowItem("Events of class \"C\"",
                new FlowLayout(FlowLayout.LEFT, 5, 3), Figure.Circle, controller);
        eventRowItemList.add(thirdRow);
        add(thirdRow);
    }

    public List<EventRowItem> getSelectedElements() {
        List<EventRowItem> selectedElements = new ArrayList<>();
        for(EventRowItem eventRowItem : eventRowItemList) {
            if(eventRowItem.getIsCheckBoxSelected()) {
                selectedElements.add(eventRowItem);
            }
        }
        return selectedElements;
    }
}
