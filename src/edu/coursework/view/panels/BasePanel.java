package edu.coursework.view.panels;

import edu.coursework.controller.MainController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class BasePanel extends JPanel {

    private int width;
    private int height;
    private MainController controller;

    public BasePanel(int width, int height, Border border, LayoutManager layoutManager) {
        this.width = width;
        this.height = height;
        setBorder(border);
        setLayout(layoutManager);
    }

    public BasePanel(LayoutManager layoutManager) {
        setLayout(layoutManager);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension dimension = new Dimension(width,height);
        if(dimension.height == 0) {
            //if dimension is not set - get default
            return super.getPreferredSize();
        }
        return dimension;
    }
}
