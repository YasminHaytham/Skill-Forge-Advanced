package com.mycompany.skillforge;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SimpleBarChart extends JPanel {
    private Map<String, Double> data;
    private String title;
    private Color barColor = new Color(70, 130, 180);
    private Color textColor = Color.BLACK;

    public SimpleBarChart(Map<String, Double> data, String title) {
        this.data = data;
        this.title = title;
        setPreferredSize(new Dimension(500, 300));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        
        // Calculate dimensions
        int margin = 60;
        int chartWidth = width - 2 * margin;
        int chartHeight = height - 2 * margin;
        
        if (data == null || data.isEmpty()) {
            g2.setColor(Color.GRAY);
            g2.drawString("No data available", width/2 - 50, height/2);
            return;
        }

        // Draw title
        g2.setColor(textColor);
        g2.setFont(new Font("SansSerif", Font.BOLD, 16));
        FontMetrics titleMetrics = g2.getFontMetrics();
        int titleWidth = titleMetrics.stringWidth(title);
        g2.drawString(title, (width - titleWidth) / 2, 30);

        // Draw axes
        g2.setColor(Color.BLACK);
        g2.drawLine(margin, margin, margin, height - margin); // y-axis
        g2.drawLine(margin, height - margin, width - margin, height - margin); // x-axis

        // Calculate bar dimensions
        int barCount = data.size();
        int barWidth = Math.max(20, chartWidth / (barCount * 2));
        int spacing = (chartWidth - (barCount * barWidth)) / (barCount + 1);

        // Find maximum value for scaling
        double maxValue = data.values().stream().max(Double::compare).orElse(100.0);
        if (maxValue == 0) maxValue = 100;

        // Draw bars and labels
        int i = 0;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            int x = margin + spacing + i * (barWidth + spacing);
            int barHeight = (int) ((entry.getValue() / maxValue) * chartHeight);
            int y = height - margin - barHeight;

            // Draw bar
            g2.setColor(barColor);
            g2.fillRect(x, y, barWidth, barHeight);
            
            // Draw bar outline
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, barWidth, barHeight);

            // Draw value on top of bar
            g2.setColor(textColor);
            g2.setFont(new Font("SansSerif", Font.PLAIN, 12));
            String valueText = String.format("%.1f", entry.getValue());
            FontMetrics valueMetrics = g2.getFontMetrics();
            int valueWidth = valueMetrics.stringWidth(valueText);
            g2.drawString(valueText, x + (barWidth - valueWidth) / 2, y - 5);

            // Draw label below bar
            String label = shortenLabel(entry.getKey());
            FontMetrics labelMetrics = g2.getFontMetrics();
            int labelWidth = labelMetrics.stringWidth(label);
            g2.drawString(label, x + (barWidth - labelWidth) / 2, height - margin + 20);

            i++;
        }

        // Draw y-axis labels
        g2.setColor(textColor);
        for (int j = 0; j <= 5; j++) {
            int y = height - margin - (j * chartHeight / 5);
            String label = String.format("%.0f", maxValue * j / 5);
            g2.drawString(label, margin - 25, y + 5);
            g2.drawLine(margin - 5, y, margin, y);
        }
    }

    private String shortenLabel(String label) {
        if (label.length() > 15) {
            return label.substring(0, 12) + "...";
        }
        return label;
    }
}