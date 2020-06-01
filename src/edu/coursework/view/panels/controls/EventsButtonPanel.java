package edu.coursework.view.panels.controls;

import edu.coursework.controller.MainController;
import edu.coursework.view.panels.BasePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class EventsButtonPanel extends BasePanel {

    private JLabel statusText;
    private JButton drawButton;
    private JButton clearButton;
    private MainController presenter;

    public EventsButtonPanel(int width, int height, Border border, MainController presenter) {
        super(width, height, border, new FlowLayout(FlowLayout.CENTER));
        this.presenter = presenter;
        statusText = new JLabel("ok");
        drawButton = new JButton("Show Events");
        drawButton.addActionListener((event) -> presenter.onEventsShowClicked());
        clearButton = new JButton("Clear Events");
        clearButton.addActionListener((event) -> presenter.clearEvents());
        createViews();
    }

    private void  createViews() {
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        firstRow.add(drawButton);
        firstRow.add(clearButton);

        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        secondRow.add(new JLabel("Drawing status: "));
        secondRow.add(statusText);

        add(firstRow);
        add(secondRow);
    }

    public void setStatusText(String status) {
        this.statusText.setText(status);
    }
}
