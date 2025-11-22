package com.mycompany.skillforge;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class AdminDashboardFrame extends javax.swing.JFrame {
    private final JsonDatabaseManager dbManager = new JsonDatabaseManager();
    private DefaultTableModel model;

    public AdminDashboardFrame() {
        initComponents();
        setTitle("Admin Dashboard");

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Course ID", "Title", "Description", "Instructor ID", "Status"});
        courseTable.setModel(model);

        loadCourses();
    }

     private void loadCourses() {
        model.setRowCount(0); 
        List<Course> courses = dbManager.getAllCourses();
        for (Course c : courses) {
            model.addRow(new Object[]{c.getCourseId(), c.getTitle(), c.getDescription(), c.getInstructorId(), c.getStatus()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        approveButton = new javax.swing.JButton();
        declineButton = new javax.swing.JButton();
        toggleButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        courseTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        approveButton.setText("APPROVE");
        approveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveButtonActionPerformed(evt);
            }
        });

        declineButton.setText("DECLINE");
        declineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                declineButtonActionPerformed(evt);
            }
        });

        toggleButton.setText("TOGGLE STATUS");
        toggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toggleButtonActionPerformed(evt);
            }
        });

        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        courseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Course Id", "Title ", "Description", "Instructor Id", "Status"
            }
        ));
        jScrollPane1.setViewportView(courseTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(approveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(declineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141)
                .addComponent(toggleButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(133, 133, 133))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveButton)
                    .addComponent(declineButton)
                    .addComponent(toggleButton))
                .addGap(61, 61, 61)
                .addComponent(refreshButton)
                .addContainerGap(378, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveButtonActionPerformed
        int row = courseTable.getSelectedRow();
        if (row != -1) {
            String courseId = (String) model.getValueAt(row, 0);
            Course c = dbManager.getCourseById(courseId);
            c.Approve();
            model.setValueAt(c.getStatus(), row, 4);
            dbManager.updateCourse(c);
        } else {
            JOptionPane.showMessageDialog(this, "Select a course first!");
        }
    }//GEN-LAST:event_approveButtonActionPerformed

    private void declineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_declineButtonActionPerformed
        int row = courseTable.getSelectedRow();
        if (row != -1) {
            String courseId = (String) model.getValueAt(row, 0);
            Course c = dbManager.getCourseById(courseId);
            c.Decline();
            model.setValueAt(c.getStatus(), row, 4);
            dbManager.updateCourse(c);
        } else {
            JOptionPane.showMessageDialog(this, "Select a course first!");
        }
    }//GEN-LAST:event_declineButtonActionPerformed

    private void toggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toggleButtonActionPerformed
        int row = courseTable.getSelectedRow();
        if (row != -1) {
            String courseId = (String) model.getValueAt(row, 0);
            Course c = dbManager.getCourseById(courseId);
            if (c.getStatus().equals("Approved")) {
                c.Decline();
            } else {
                c.Approve();
            }
            model.setValueAt(c.getStatus(), row, 4);
            dbManager.updateCourse(c);
        } else {
            JOptionPane.showMessageDialog(this, "Select a course first!");
        }
    

    }//GEN-LAST:event_toggleButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        loadCourses();
    }//GEN-LAST:event_refreshButtonActionPerformed

    public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new AdminDashboardFrame().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveButton;
    private javax.swing.JTable courseTable;
    private javax.swing.JButton declineButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton toggleButton;
    // End of variables declaration//GEN-END:variables
}
