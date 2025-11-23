/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.skillforge;

import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class InstructorInsights extends javax.swing.JFrame {
    private Map<String, Object> analytics;
    private String courseTitle;
    private JTabbedPane tabbedPane;

    public InstructorInsights(Map<String, Object> analytics, String courseTitle) {
        this.analytics = analytics;
        this.courseTitle = courseTitle;
        initComponents();
        createTabbedInterface();
        displayAnalytics();
    }

public InstructorInsights() {
    initComponents();
    createTabbedInterface();
    if (analyticsTextArea != null) {
        analyticsTextArea.setText("No analytics data available. Please select a course with student activity.");
    }
}

private void createTabbedInterface() {
    // Remove the default NetBeans layout completely
    getContentPane().removeAll();
    
    // Create tabbed pane
    tabbedPane = new JTabbedPane();
    
    // Create text analytics panel
    JPanel textPanel = createTextPanel();
    
    // Create charts panel (simplified for now)
    JPanel chartPanel = createChartPanel();
    
    // Add tabs
    tabbedPane.addTab("Text Analytics", textPanel);
    tabbedPane.addTab("Visual Charts", chartPanel);
    
    // Create a main panel to hold both tabs and close button
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(tabbedPane, BorderLayout.CENTER);
    mainPanel.add(closeBtn, BorderLayout.SOUTH);  // Add the close button
    
    // Layout
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(mainPanel, BorderLayout.CENTER);
    
    setTitle("Course Insights - " + (courseTitle != null ? courseTitle : "SkillForge"));
    pack();
    setLocationRelativeTo(null);
    setSize(700, 500);
}
private JPanel createTextPanel() {
    JPanel textPanel = new JPanel(new BorderLayout());
    textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    // Use the existing analyticsTextArea from NetBeans but reconfigure it
    analyticsTextArea.setLineWrap(true);
    analyticsTextArea.setWrapStyleWord(true);
    analyticsTextArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
    
    // Create a new scroll pane for the text area
    JScrollPane scrollPane = new JScrollPane(analyticsTextArea);
    textPanel.add(scrollPane, BorderLayout.CENTER);
    
    return textPanel;
}

    private JPanel createChartPanel() {
        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        if (analytics == null || analytics.isEmpty()) {
            JTextArea noDataLabel = new JTextArea("No chart data available.\n\n"
                    + "Charts will appear here when:\n"
                    + "- Students enroll in the course\n"
                    + "- Quizzes are attempted\n"
                    + "- Lesson completion data is available");
            noDataLabel.setEditable(false);
            noDataLabel.setOpaque(false);
            noDataLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            chartPanel.add(noDataLabel, BorderLayout.CENTER);
        } else {
            createSimpleCharts(chartPanel);
        }
        
        return chartPanel;
    }

    private void displayAnalytics() {
        if (analytics == null || analytics.isEmpty()) {
            analyticsTextArea.setText("No analytics data available for this course.\n\n"
                    + "This could be because:\n"
                    + "- No students are enrolled\n"
                    + "- No quizzes have been taken\n"
                    + "- The course is new\n\n"
                    + "Analytics will appear when students start taking quizzes.");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== COURSE ANALYTICS: ").append(courseTitle).append(" ===\n\n");
        
        // Safely get values with defaults
        int totalStudents = getIntValue("totalStudents", 0);
        int completedStudents = getIntValue("completedStudents", 0);
        double completionRate = getDoubleValue("courseCompletionRate", 0.0);
        
        sb.append("Total Students: ").append(totalStudents).append("\n");
        sb.append("Completed Students: ").append(completedStudents).append("\n");
        sb.append("Completion Rate: ").append(String.format("%.1f%%", completionRate)).append("\n\n");
        
        // Lesson quiz averages
        displayQuizAverages(sb);
        
        // Lesson completion
        displayLessonCompletion(sb, totalStudents);
        
        analyticsTextArea.setText(sb.toString());
    }
    
    private int getIntValue(String key, int defaultValue) {
        try {
            Object value = analytics.get(key);
            return value != null ? (int) value : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    private double getDoubleValue(String key, double defaultValue) {
        try {
            Object value = analytics.get(key);
            return value != null ? (double) value : defaultValue;
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
    private void displayQuizAverages(StringBuilder sb) {
        sb.append("=== LESSON QUIZ AVERAGES ===\n");
        try {
            Map<String, Double> quizAverages = (Map<String, Double>) analytics.get("lessonQuizAverages");
            if (quizAverages != null && !quizAverages.isEmpty()) {
                for (Map.Entry<String, Double> entry : quizAverages.entrySet()) {
                    sb.append("• ").append(entry.getKey()).append(": ")
                      .append(String.format("%.1f/5", entry.getValue())).append("\n");
                }
            } else {
                sb.append("No quiz data available\n");
            }
        } catch (Exception e) {
            sb.append("Error loading quiz data\n");
        }
        sb.append("\n");
    }
    
    private void displayLessonCompletion(StringBuilder sb, int totalStudents) {
        sb.append("=== LESSON COMPLETION ===\n");
        try {
            Map<String, Integer> lessonCompletion = (Map<String, Integer>) analytics.get("lessonCompletion");
            if (lessonCompletion != null && !lessonCompletion.isEmpty()) {
                for (Map.Entry<String, Integer> entry : lessonCompletion.entrySet()) {
                    double lessonCompletionRate = totalStudents > 0 ? 
                        ((double) entry.getValue() / totalStudents) * 100 : 0;
                    sb.append("• ").append(entry.getKey()).append(": ")
                      .append(entry.getValue()).append("/").append(totalStudents)
                      .append(" (").append(String.format("%.1f%%", lessonCompletionRate)).append(")\n");
                }
            } else {
                sb.append("No completion data available\n");
            }
        } catch (Exception e) {
            sb.append("Error loading completion data\n");
        }
    }
    
    private void createSimpleCharts(JPanel chartPanel) {
        Map<String, Double> quizAverages = (Map<String, Double>) analytics.get("lessonQuizAverages");
        Map<String, Integer> lessonCompletion = (Map<String, Integer>) analytics.get("lessonCompletion");
        
        JPanel chartsContainer = new JPanel();
        chartsContainer.setLayout(new BoxLayout(chartsContainer, BoxLayout.Y_AXIS));
        
        boolean hasCharts = false;
        
        // Quiz averages chart
        if (quizAverages != null && !quizAverages.isEmpty()) {
            try {
                SimpleBarChart quizChart = new SimpleBarChart(quizAverages, "Lesson Quiz Averages");
                quizChart.setBorder(BorderFactory.createTitledBorder("Quiz Scores by Lesson"));
                quizChart.setPreferredSize(new java.awt.Dimension(500, 300));
                chartsContainer.add(quizChart);
                hasCharts = true;
            } catch (Exception e) {
                JLabel errorLabel = new JLabel("Error creating quiz chart: " + e.getMessage());
                chartsContainer.add(errorLabel);
            }
        }
        
        // Lesson completion chart
        if (lessonCompletion != null && !lessonCompletion.isEmpty()) {
            try {
                // Convert to percentages for chart
                java.util.Map<String, Double> completionPercentages = new java.util.HashMap<>();
                int totalStudents = (int) analytics.getOrDefault("totalStudents", 1);
                
                for (Map.Entry<String, Integer> entry : lessonCompletion.entrySet()) {
                    double percentage = totalStudents > 0 ? 
                        ((double) entry.getValue() / totalStudents) * 100 : 0;
                    completionPercentages.put(entry.getKey(), percentage);
                }
                
                SimpleBarChart completionChart = new SimpleBarChart(completionPercentages, "Lesson Completion Rates");
                completionChart.setBorder(BorderFactory.createTitledBorder("Completion Rates by Lesson"));
                completionChart.setPreferredSize(new java.awt.Dimension(500, 300));
                chartsContainer.add(completionChart);
                hasCharts = true;
            } catch (Exception e) {
                JLabel errorLabel = new JLabel("Error creating completion chart: " + e.getMessage());
                chartsContainer.add(errorLabel);
            }
        }
        
        if (!hasCharts) {
            JLabel noChartsLabel = new JLabel("No chart data available from analytics.");
            noChartsLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            chartsContainer.add(noChartsLabel);
        }
        
        JScrollPane scrollPane = new JScrollPane(chartsContainer);
        chartPanel.add(scrollPane, BorderLayout.CENTER);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        analyticsTextArea = new javax.swing.JTextArea();
        closeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Course Analytics");

        analyticsTextArea.setEditable(false);
        analyticsTextArea.setColumns(20);
        analyticsTextArea.setLineWrap(true);
        analyticsTextArea.setRows(5);
        jScrollPane1.setViewportView(analyticsTextArea);

        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(closeBtn))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 314, Short.MAX_VALUE)
                        .addComponent(closeBtn))
                    .addComponent(jScrollPane1))
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InstructorInsights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InstructorInsights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InstructorInsights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InstructorInsights.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InstructorInsights().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea analyticsTextArea;
    private javax.swing.JButton closeBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}