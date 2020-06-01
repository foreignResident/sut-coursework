package edu.coursework.view.panels.controls;

import edu.coursework.controller.MainController;
import edu.coursework.view.panels.BasePanel;
import edu.coursework.view.panels.statistic.StatisticPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

//right side panel
//current dimensions 300,600
public class ControlsPanel extends BasePanel {

    private JLabel amountStatistic;
    private JLabel scaleStatistic;
    //panel for create instances of each events
    private EventsPanel eventsPanel;
    //panel for set up event scale
    private EventsScalePanel eventsScalePanel;
    //controller panel with buttons
    private EventsButtonPanel eventsButtonsPanel;
    //statistic panels
    private StatisticPanel amountStatisticPanel;
    private StatisticPanel scaleStatisticPanel;

   public ControlsPanel(int width, int height, Border border, MainController controller) {
       super(width, height, border, new FlowLayout(FlowLayout.CENTER,0,10));

       //border for inner panels
       Border innerBorder = new LineBorder(new Color(0,0,0),1);

       //initialize Panels
       eventsPanel = new EventsPanel(330,120, innerBorder, controller);
       eventsScalePanel = new EventsScalePanel(330,80, innerBorder);
       eventsButtonsPanel = new EventsButtonPanel(330,80, innerBorder, controller);
       amountStatisticPanel = new StatisticPanel(330, 110, innerBorder,
               new String[]{"Class A", "Class B", "Class C"}, Color.RED, Color.BLACK);
       scaleStatisticPanel = new StatisticPanel(330, 110, innerBorder,
               new String[]{"<30%", "30-60%", ">60%"}, Color.BLUE, Color.BLACK);

       amountStatistic = new JLabel("Statistics by amount");
       scaleStatistic = new JLabel("Statistics by scale");

       //add child panels
       add(eventsPanel);
       add(eventsScalePanel);
       add(eventsButtonsPanel);
       add(amountStatistic);
       add(amountStatisticPanel);
       add(scaleStatistic);
       add(scaleStatisticPanel);
   }

    public StatisticPanel getScaleStatisticPanel() {
        return scaleStatisticPanel;
    }

    public StatisticPanel getAmountStatisticPanel() {
        return amountStatisticPanel;
    }

    public EventsPanel getEventsPanel() {
        return eventsPanel;
    }

    public EventsScalePanel getEventsScalePanel() {
        return eventsScalePanel;
    }

    public EventsButtonPanel getEventsButtonsPanel() {
        return eventsButtonsPanel;
    }
}
