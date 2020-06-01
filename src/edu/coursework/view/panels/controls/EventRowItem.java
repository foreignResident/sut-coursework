package edu.coursework.view.panels.controls;

import edu.coursework.controller.MainController;
import edu.coursework.model.Figure;
import edu.coursework.view.panels.BasePanel;

import javax.swing.*;
import java.awt.*;

//container for general events settings
public class EventRowItem extends BasePanel {

    private JCheckBox checkBox;
    private JTextField fromTextField;
    private JTextField toTextField;
    private Figure figure;
    private MainController controller;

    public EventRowItem(String eventName, LayoutManager layoutManager, Figure figure, MainController controller) {
        super(layoutManager);
        this.figure = figure;
        JLabel eventNameField = new JLabel(eventName);
        checkBox = new JCheckBox("", true);
        JLabel form = new JLabel("from");
        fromTextField = new JTextField("0",3);
        JLabel to = new JLabel("to");
        toTextField = new JTextField("10", 3);

        add(eventNameField);
        add(checkBox);
        add(form);
        add(fromTextField);
        add(to);
        add(toTextField);
    }

    public boolean getIsCheckBoxSelected() {
        return checkBox.isSelected();
    }

    public int getFromTextField() {
        return Integer.parseInt(fromTextField.getText());
    }

    public int getToTextField() throws NumberFormatException {
        return Integer.parseInt(toTextField.getText());
    }

    public Figure getFigure() {
        return figure;
    }
}
