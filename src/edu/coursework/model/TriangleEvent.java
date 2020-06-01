package edu.coursework.model;

import java.awt.*;

public class TriangleEvent extends BaseEvent {

    public TriangleEvent(int scale, int positionX, int positionY, Figure figure) {
        super(scale, positionX, positionY, figure);
    }

    @Override
    public void draw(Graphics g) {
        int radius = getScale()/2;
        int edge = (int) Math.round(radius*Math.sqrt(3));
        int halfEdge = edge/2;
        int x1,x2,x3,y1,y2,y3;

        //calculate coordinates for each vertex
        x1 = getPositionX();
        y1 = getPositionY() - radius;

        x2 = getPositionX() - halfEdge;
        y2 = getPositionY() + (int) Math.round(Math.sqrt(radius*radius - halfEdge*halfEdge));

        x3 = getPositionX() + halfEdge;
        y3 = y2;

        //draw figure
        g.setColor(getFigureColor());
        g.fillPolygon(new int[]{x1,x2,x3}, new int[]{y1,y2,y3}, 3);
    }
}
