/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.skillforge;

import java.util.HashSet;
import java.util.Map;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

/**
 *
 * @author User
 */
public class InstructorInsights extends javax.swing.JFrame {

    private Map<String, Object> analytics;
    private String courseTitle;
    private JTabbedPane tabbedPane;
    private JPanel chartPanel;
    private JPanel textPanel;
    
    /**
     * Creates new form InstructorInsights with analytics data
     */
    public InstructorInsights(Map<String, Object> analytics, String courseTitle) {
        this.analytics = analytics;
        this.courseTitle = courseTitle;
        initComponents();
        createTabbedInterface();
        displayAnalytics();
        displayCharts();
    }
    
    /**
     * Default constructor (keep for NetBeans compatibility)
     */
     public InstructorInsights() {
        initComponents();
        createTabbedInterface();
    }
        private void createTabbedInterface() {
        // Remove existing components
        getContentPane().removeAll();
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Create text analytics panel
        textPanel = new JPanel(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        analyticsTextArea = new javax.swing.JTextArea();
        
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel1.setText("Course Analytics - " + courseTitle);
        
        analyticsTextArea.setEditable(false);
        analyticsTextArea.setColumns(50);
        analyticsTextArea.setLineWrap(true);
        analyticsTextArea.setRows(20);
        analyticsTextArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        jScrollPane1.setViewportView(analyticsTextArea);
        
        textPanel.add(jLabel1, BorderLayout.NORTH);
        textPanel.add(jScrollPane1, BorderLayout.CENTER);
        
        // Create charts panel
        chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add tabs
        tabbedPane.addTab("Text Analytics", textPanel);
        tabbedPane.addTab("Visual Charts", chartPanel);
        
        // Add close button
        closeBtn = new javax.swing.JButton();
        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });
        
        // Layout
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        getContentPane().add(closeBtn, BorderLayout.SOUTH);
        
        pack();
    }
    
       private void displayAnalytics() {
        if (analytics == null) return;
        
        StringBuilder sb = new StringBuilder();
        sb.append("=== COURSE ANALYTICS ===\n\n");
        
        sb.append("Total Students: ").append(analytics.get("totalStudents")).append("\n");
        sb.append("Completed Students: ").append(analytics.get("completedStudents")).append("\n");
        sb.append("Completion Rate: ").append(String.format("%.1f%%", analytics.get("courseCompletionRate"))).append("\n\n");
        
        sb.append("=== LESSON QUIZ AVERAGES ===\n");
        Map<String, Double> quizAverages = (Map<String, Double>) analytics.get("lessonQuizAverages");
        if (quizAverages != null) {
            for (Map.Entry<String, Double> entry : quizAverages.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(String.format("%.1f/100", entry.getValue())).append("\n");
            }
        }
        
        sb.append("\n=== LESSON COMPLETION ===\n");
        Map<String, Integer> lessonCompletion = (Map<String, Integer>) analytics.get("lessonCompletion");
        int totalStudents = (int) analytics.get("totalStudents");
        if (lessonCompletion != null) {
            for (Map.Entry<String, Integer> entry : lessonCompletion.entrySet()) {
                double completionRate = totalStudents > 0 ? (double) entry.getValue() / totalStudents * 100 : 0;
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("/")
                  .append(totalStudents).append(" (").append(String.format("%.1f%%", completionRate)).append(")\n");
            }
        }
        
        analyticsTextArea.setText(sb.toString());
    }
    private void displayCharts() {
        if (analytics == null) return;
        
        chartPanel.removeAll();
        
        try {
            // Try to use JFreeChart if available
            createJFreeCharts();
        } catch (NoClassDefFoundError e) {
            // Fallback to simple Java2D charts
            createSimpleCharts();
        }
        
        chartPanel.revalidate();
        chartPanel.repaint();
    }
    
    private void createJFreeCharts() {
        // This will only work if JFreeChart is in classpath
        Map<String, Double> quizAverages = (Map<String, Double>) analytics.get("lessonQuizAverages");
        Map<String, Integer> lessonCompletion = (Map<String, Integer>) analytics.get("lessonCompletion");
        
        JPanel chartsContainer = new JPanel();
        chartsContainer.setLayout(new BoxLayout(chartsContainer, BoxLayout.Y_AXIS));
        
        if (quizAverages != null && !quizAverages.isEmpty()) {
            JPanel quizChart = SimpleChartUtils.createBarChart(
                quizAverages, 
                "Lesson Quiz Averages", 
                "Lessons", 
                "Average Score (%)"
            );
            quizChart.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            chartsContainer.add(quizChart);
        }
        
        if (lessonCompletion != null && !lessonCompletion.isEmpty()) {
            // Convert completion counts to percentages
            java.util.Map<String, Double> completionPercentages = new java.util.HashMap<>();
            int totalStudents = (int) analytics.get("totalStudents");
            
            for (Map.Entry<String, Integer> entry : lessonCompletion.entrySet()) {
                double percentage = totalStudents > 0 ? 
                    ((double) entry.getValue() / totalStudents) * 100 : 0;
                completionPercentages.put(entry.getKey(), percentage);
            }
            
            JPanel completionChart = SimpleChartUtils.createBarChart(
                completionPercentages,
                "Lesson Completion Rates",
                "Lessons", 
                "Completion Rate (%)"
            );
            completionChart.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
            chartsContainer.add(completionChart);
        }
        
        JScrollPane scrollPane = new JScrollPane(chartsContainer);
        chartPanel.add(scrollPane, BorderLayout.CENTER);
    }
    
    private void createSimpleCharts() {
        Map<String, Double> quizAverages = (Map<String, Double>) analytics.get("lessonQuizAverages");
        Map<String, Integer> lessonCompletion = (Map<String, Integer>) analytics.get("lessonCompletion");
        
        JPanel chartsContainer = new JPanel();
        chartsContainer.setLayout(new BoxLayout(chartsContainer, BoxLayout.Y_AXIS));
        
        if (quizAverages != null && !quizAverages.isEmpty()) {
            SimpleBarChart quizChart = new SimpleBarChart(quizAverages, "Lesson Quiz Averages");
            quizChart.setBorder(BorderFactory.createTitledBorder("Quiz Scores by Lesson"));
            quizChart.setPreferredSize(new java.awt.Dimension(500, 300));
            chartsContainer.add(quizChart);
        }
        
        if (lessonCompletion != null && !lessonCompletion.isEmpty()) {
            // Convert to percentages for chart
            java.util.Map<String, Double> completionPercentages = new java.util.HashMap<>();
            int totalStudents = (int) analytics.get("totalStudents");
            
            for (Map.Entry<String, Integer> entry : lessonCompletion.entrySet()) {
                double percentage = totalStudents > 0 ? 
                    ((double) entry.getValue() / totalStudents) * 100 : 0;
                completionPercentages.put(entry.getKey(), percentage);
            }
            
            SimpleBarChart completionChart = new SimpleBarChart(completionPercentages, "Lesson Completion Rates");
            completionChart.setBorder(BorderFactory.createTitledBorder("Completion Rates by Lesson"));
            completionChart.setPreferredSize(new java.awt.Dimension(500, 300));
            chartsContainer.add(completionChart);
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