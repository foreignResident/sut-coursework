package edu.coursework.view.panels.map;

import edu.coursework.model.BaseEvent;
import edu.coursework.view.panels.BasePanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class MapPanel extends BasePanel implements MouseListener {

    private List<BaseEvent> eventList = new ArrayList<>();
    private Image image;

    public MapPanel(int width, int height, Border border) {
        super(width, height, border, new FlowLayout());
        this.image = new ImageIcon("src/img/part_of_map_of_UA.png").getImage();
        addMouseListener(this);
    }

    //set eventList and draw events from them
    public void setEventList(List<BaseEvent> eventList) {
        this.eventList = eventList;
        repaint();
    }

    public void addEventToList(BaseEvent event){
        this.eventList.add(event);
        repaint();
    }

    public List<BaseEvent> getEventList() {
        return eventList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }

        //set up font for grid metrics
        g.setFont(new Font("Serif", Font.PLAIN, 14));
        //drawing grid


        for (int i = 50; i < getWidth(); i += 50) {
            //draw grid lines
            g.setColor(Color.BLACK);
            g.drawLine(i, 0, i, getHeight());
            g.drawLine(0, i, getWidth(), i);

            //draw dim containers
            g.setColor(Color.WHITE);
            g.fillRect(i - 10, 5, 20, 14);
            g.fillRect(5, i - 7, 20, 14);

            //draw dimensions in containers
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), i - 10, 17);
            g.drawString(String.valueOf(i), 5, i + 5);
        }

        if (eventList != null) {
            eventList.forEach(event -> event.draw(g));
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
