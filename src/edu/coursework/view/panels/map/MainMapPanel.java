package edu.coursework.view.panels.map;

import edu.coursework.controller.MainController;
import edu.coursework.model.BaseEvent;
import edu.coursework.utils.Dimensions;
import edu.coursework.view.panels.BasePanel;
import edu.coursework.view.panels.controls.DjikstraPanel;
import edu.coursework.view.panels.controls.EventsButtonPanel;
import edu.coursework.view.panels.controls.EventsPanel;
import edu.coursework.view.panels.controls.EventsScalePanel;
import edu.coursework.view.panels.statistic.StatisticPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainMapPanel extends BasePanel {

    private MapPanel mapPanel;

    private DjikstraPanel djikstraPanel;

    public MainMapPanel(int width, int height, Border border) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER, 0, 10));

        //border for inner panels
        Border innerBorder = new LineBorder(new Color(0, 0, 0), 1);

        //initialize Panels
        mapPanel = new MapPanel(Dimensions.MAP_WIDTH, 650, border);
        djikstraPanel = new DjikstraPanel(700, 100, border);

        //add child panels

        add(mapPanel);
        add(djikstraPanel);


    }

    public MapPanel getMapPanel() {
        return mapPanel;
    }

    public void setMapPanel(MapPanel mapPanel) {
        this.mapPanel = mapPanel;
    }

    public DjikstraPanel getDjikstraPanel() {
        return djikstraPanel;
    }

    public void setDjikstraPanel(DjikstraPanel djikstraPanel) {
        this.djikstraPanel = djikstraPanel;
    }
}
