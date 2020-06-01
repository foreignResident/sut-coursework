package edu.coursework.model;

import java.awt.*;

public class CircleEvent extends BaseEvent {

    public CircleEvent(int scale, int positionX, int positionY, Figure figure) {
        super(scale, positionX, positionY, figure);
    }


    @Override
    public void draw(Graphics g) {
        int radius = getScale();

        //calculate coordinates for each vertex
        int x1 = getPositionX() - radius;
        int y1 = getPositionY() - radius;

        //draw figure
        g.setColor(getFigureColor());
        g.fillOval(x1, y1, radius, radius);
    }
}
