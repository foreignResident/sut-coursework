package edu.coursework.view.panels.map.djikstra;

import edu.coursework.model.Figure;
import edu.coursework.view.panels.BasePanel;
import edu.coursework.view.panels.controls.EventRowItem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DjikstraPanel extends BasePanel {

    private JLabel title, scaleTitle;
    private JTextField scaleTextField;
    private List<DjikstraEventRowItem> eventRowItemList = new ArrayList<>();
    private ButtonGroup radioButtonGroup;
    private Button button;
    private boolean enabled = false;

    public DjikstraPanel(int width, int height, Border border) {
        super(width, height, border, new FlowLayout(FlowLayout.LEFT, 10, 5));

        radioButtonGroup = new ButtonGroup();
        title = new JLabel("Dijkstra's algorithm");
        add(title);

        DjikstraEventRowItem firstRow = new DjikstraEventRowItem("Triangle",
                new FlowLayout(FlowLayout.LEFT, 5, 3), Figure.Triangle, true);
        eventRowItemList.add(firstRow);
        add(firstRow);
        radioButtonGroup.add(firstRow.getRadioButton());

        DjikstraEventRowItem secondRow = new DjikstraEventRowItem("Rectangle",
                new FlowLayout(FlowLayout.LEFT, 5, 3), Figure.Rectangle, false);
        eventRowItemList.add(secondRow);
        add(secondRow);
        radioButtonGroup.add(secondRow.getRadioButton());

        DjikstraEventRowItem thirdRow = new DjikstraEventRowItem("Circle",
                new FlowLayout(FlowLayout.LEFT, 5, 3), Figure.Circle, false);
        eventRowItemList.add(thirdRow);
        add(thirdRow);
        radioButtonGroup.add(thirdRow.getRadioButton());

        scaleTitle = new JLabel("Scale");
        scaleTextField = new JTextField("30", 4);

        add(scaleTitle);
        add(scaleTextField);

        button = new Button("Add event");
        button.addActionListener((event) -> changeLabel());
        add(button);

        System.out.println(radioButtonGroup.getSelection().getActionCommand());

    }

    private void changeLabel() {
        enabled = !enabled;
        title.setText(enabled ? "Select place on map" : "Dijkstra's algorithm");
    }

    public JLabel getTitle() {
        return title;
    }

    public void setTitle(JLabel title) {
        this.title = title;
    }

    public List<DjikstraEventRowItem> getEventRowItemList() {
        return eventRowItemList;
    }

    public void setEventRowItemList(List<DjikstraEventRowItem> eventRowItemList) {
        this.eventRowItemList = eventRowItemList;
    }

    public ButtonGroup getRadioButtonGroup() {
        return radioButtonGroup;
    }

    public void setRadioButtonGroup(ButtonGroup radioButtonGroup) {
        this.radioButtonGroup = radioButtonGroup;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getActionCommand() {
        return this.radioButtonGroup.getSelection().getActionCommand();
    }

    public int getScale() {
        return Integer.parseInt(this.scaleTextField.getText());
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
