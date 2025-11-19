package com.mycompany.skillforge;

import javax.swing.JOptionPane;

import com.mycompany.skillforge.Manager;
import com.mycompany.skillforge.User;


public class Login extends javax.swing.JFrame {

    public Login() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LPassword = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        LUsername = new javax.swing.JLabel();
        userField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        instructorRadio = new javax.swing.JRadioButton();
        studentRadio = new javax.swing.JRadioButton();
        signupButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        LPassword.setForeground(new java.awt.Color(0, 51, 153));
        LPassword.setText(" Password");

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        LUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        LUsername.setForeground(new java.awt.Color(0, 51, 153));
        LUsername.setText("Username/Email");

        userField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        userField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userFieldActionPerformed(evt);
            }
        });

        loginButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        loginButton.setForeground(new java.awt.Color(0, 51, 153));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        instructorRadio.setText("INSTRUCTOR");
        instructorRadio.setToolTipText("");
        instructorRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instructorRadioActionPerformed(evt);
            }
        });

        studentRadio.setText("STUDENT");
        studentRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentRadioActionPerformed(evt);
            }
        });

        signupButton.setForeground(new java.awt.Color(0, 51, 153));
        signupButton.setText("Signup");
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(instructorRadio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(studentRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59))
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(signupButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(LUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(LPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(47, 47, 47)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(userField))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LUsername)
                    .addComponent(userField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LPassword)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentRadio)
                    .addComponent(instructorRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signupButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userFieldActionPerformed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
      String userInput = userField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        String selectedRole = null;

        if (studentRadio.isSelected()) {
            selectedRole = "student";
        } else if (instructorRadio.isSelected()) {
            selectedRole = "instructor";
        }

        if (userInput.isEmpty() || password.isEmpty() || selectedRole == null) {
            JOptionPane.showMessageDialog(this, "Please fill all fields and select a role.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = Manager.login(userInput, password, selectedRole);

        if (!result.equalsIgnoreCase("Login successful")) {
            JOptionPane.showMessageDialog(this, "Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else {
            JOptionPane.showMessageDialog(this, result , "Success", JOptionPane.INFORMATION_MESSAGE);
        }

        User user = Manager.getCurrentUser();
        this.dispose();

        if (user.getRole().equalsIgnoreCase("student")) {
           new  StudentDashboardFrame().setVisible(true);
        } else {
            new Insructor().setVisible(true);
        }

    }                                           

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void instructorRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructorRadioActionPerformed

    }
//GEN-LAST:event_instructorRadioActionPerformed

    private void studentRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentRadioActionPerformed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        signup y=new signup();
            y.setVisible(true);
            this.setVisible(false);
    }//GEN-LAST:event_signupButtonActionPerformed

    public static void main(String args[]) {

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Login().setVisible(true);
        }
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LPassword;
    private javax.swing.JLabel LUsername;
    private javax.swing.JRadioButton instructorRadio;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton signupButton;
    private javax.swing.JRadioButton studentRadio;
    private javax.swing.JTextField userField;
    // End of variables declaration//GEN-END:variables
}
