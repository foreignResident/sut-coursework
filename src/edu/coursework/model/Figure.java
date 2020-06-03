package edu.coursework.model;

import java.awt.*;

public enum Figure {
    Rectangle(Color.BLUE),
    Triangle(Color.RED),
    Circle(Color.ORANGE),
    Djikstra(Color.GREEN);

    private Color color;

    Figure(Color color){
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
