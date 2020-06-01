package edu.coursework.view.panels.statistic;

import edu.coursework.utils.Dimensions;
import edu.coursework.view.panels.BasePanel;

import javax.swing.border.Border;
import java.awt.*;

public class StatisticPanel extends BasePanel {

    private int[] statistics;
    private String[] columnsName;
    private Color columnColor;
    private Color textColor;

    public StatisticPanel(int width, int height, Border border, String[] columnsName, Color columnColor, Color textColor) {
        super(width, height, border, new FlowLayout());
        this.columnsName = columnsName;
        this.columnColor = columnColor;
        this.textColor = textColor;
    }

    public void setStatistics(int[] statistics) {
        this.statistics = statistics;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(statistics != null) {
            double step = getGridScaleStep();
            //column width
            int width = (getWidth() - Dimensions.STATISTIC_MARGIN * 4) / 3;
            int y = 0;
            int height = 0;
            int x = 0;
            for(int i = 0; i < statistics.length; i++) {
                g.setColor(columnColor);
                y = (int)Math.round(getHeight() - (step * statistics[i]));
                height = (int)(step * statistics[i]);
                //initial margin + (column width + margin)
                x = Dimensions.STATISTIC_MARGIN + (Dimensions.STATISTIC_MARGIN*i + width * i);
                g.fillRect(x, y, width, height);
                g.setFont(new Font("Serif", Font.PLAIN, 14));
                g.setColor(textColor);
                g.drawString(columnsName[i], Dimensions.STATISTIC_MARGIN+x, (getHeight()-30));
                g.drawString(String.valueOf(statistics[i]), Dimensions.STATISTIC_MARGIN+x, (getHeight()-10));
            }
        }
    }

    //calculate grid step
    private double getGridScaleStep() {
        //sum of all elements
        int maxValue = 0;
        for(int i = 0; i < statistics.length; i++) {
            if(statistics[i] > maxValue) {
                maxValue = statistics[i];
            }
        }
        return (double)(getHeight()-Dimensions.STATISTIC_MARGIN)/maxValue;
    }
}
