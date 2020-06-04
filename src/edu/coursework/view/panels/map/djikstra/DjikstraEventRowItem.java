package edu.coursework.view.panels.map.djikstra;

import edu.coursework.model.Figure;
import edu.coursework.view.panels.BasePanel;

import javax.swing.*;
import java.awt.*;

public class DjikstraEventRowItem extends BasePanel {

    private JRadioButton radioButton;
    private Figure figure;

    public DjikstraEventRowItem(String eventName, LayoutManager layoutManager, Figure figure, boolean selected) {
        super(layoutManager);
        this.figure = figure;
        JLabel eventNameField = new JLabel(eventName);
        radioButton = new JRadioButton("", selected);
        radioButton.setActionCommand(eventName);

        add(eventNameField);
        add(radioButton);
    }

    public JRadioButton getRadioButton() {
        return radioButton;
    }

    public void setRadioButton(JRadioButton radioButton) {
        this.radioButton = radioButton;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }
}
