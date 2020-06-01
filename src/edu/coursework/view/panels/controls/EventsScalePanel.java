package edu.coursework.view.panels.controls;

import edu.coursework.view.panels.BasePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

//panel for set up event's scale
public class EventsScalePanel extends BasePanel {

    private JRadioButton isFixedSize;
    private JTextField fixedSizeText;

    private JRadioButton isInRange;
    private JTextField fromTextField;
    private JTextField toTextField;

    public EventsScalePanel(int width, int height, Border border) {
        super(width, height, border, new FlowLayout(FlowLayout.LEFT));

        isFixedSize = new JRadioButton("fixed", true);
        isFixedSize.addActionListener((event) -> {
            if(isInRange.isSelected() && isFixedSize.isSelected()) {
                //deselect inRange if fixed size selected
                isInRange.setSelected(false);
            } else if(!isInRange.isSelected() && !isFixedSize.isSelected()) {
                //avoid case when both isInRange and isFixedSize
                isFixedSize.setSelected(true);
            }
        });
        fixedSizeText = new JTextField("10",5);

        isInRange = new JRadioButton("in range", false);
        isInRange.addActionListener((event) -> {
            //deselect is fixed size if inRange selected
            if(isFixedSize.isSelected() && isInRange.isSelected()) {
                isFixedSize.setSelected(false);
            } else if(!isInRange.isSelected() && !isFixedSize.isSelected()) {
                //avoid case when both isInRange and isFixedSize
                isInRange.setSelected(true);
            }
        });
        fromTextField = new JTextField("0",3);
        toTextField = new JTextField("0",3);

        //create first row
        JPanel firstRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 3));
        //create first row content
        firstRow.add(new JLabel("Event scale"));
        firstRow.add(isFixedSize);
        firstRow.add(fixedSizeText);
        add(firstRow);

        //create second row
        JPanel secondRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 3));
        //create first row content
        secondRow.add(isInRange);
        secondRow.add(new JLabel("from"));
        secondRow.add(fromTextField);
        secondRow.add(new JLabel("to"));
        secondRow.add(toTextField);

        add(secondRow);
    }

    public boolean getIsFixedSize() {
        return isFixedSize.isSelected();
    }

    public int getFixedSize() throws NumberFormatException {
        return Integer.parseInt(fixedSizeText.getText());
    }

    public JRadioButton getIsInRange() {
        return isInRange;
    }

    public int getFromText() {
        return Integer.parseInt(fromTextField.getText());
    }

    public int getToText() {
        return Integer.parseInt(toTextField.getText());
    }
}
