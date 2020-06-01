package edu.coursework.model;

import java.awt.*;

public abstract class BaseEvent {

    private int scale;
    private int positionX;
    private int positionY;
    private Figure figure;

    public BaseEvent(int scale, int positionX, int positionY, Figure figure) {
        this.scale = scale;
        this.positionX = positionX;
        this.positionY = positionY;
        this.figure = figure;
    }

    public int getScale() {
        return scale;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Color getFigureColor() {
        return figure.getColor();
    }

    public Figure getFigure() {
        return figure;
    }

    public abstract void draw(Graphics g);
}
