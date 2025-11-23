package com.mycompany.skillforge;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SimpleChartUtils {
    
    public static JPanel createBarChart(Map<String, Double> data, String title, String xLabel, String yLabel) {
        SimpleBarChart chart = new SimpleBarChart(data, title);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(chart, BorderLayout.CENTER);
        
        // Add labels
        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.add(new JLabel(xLabel, JLabel.CENTER), BorderLayout.SOUTH);
        JLabel yLabelComponent = new JLabel(yLabel);
        yLabelComponent.setVerticalAlignment(JLabel.CENTER);
        yLabelComponent.setHorizontalAlignment(JLabel.CENTER);
        yLabelComponent.setPreferredSize(new Dimension(20, 0));
        labelPanel.add(yLabelComponent, BorderLayout.WEST);
        
        panel.add(labelPanel, BorderLayout.SOUTH);
        return panel;
    }
}