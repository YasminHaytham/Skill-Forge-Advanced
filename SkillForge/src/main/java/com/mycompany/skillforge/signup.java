package com.mycompany.skillforge;
import javax.swing.JOptionPane;
public class signup extends javax.swing.JFrame {

    public signup() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        LUsername = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        LPassword = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        usernameField = new javax.swing.JTextField();
        LUsername1 = new javax.swing.JLabel();
        SignupButton = new javax.swing.JButton();
        instructorRadio = new javax.swing.JRadioButton();
        studentRadio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LUsername.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        LUsername.setForeground(new java.awt.Color(0, 51, 153));
        LUsername.setText("Username");

        emailField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        LPassword.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        LPassword.setForeground(new java.awt.Color(0, 51, 153));
        LPassword.setText("Password");

        passwordField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        usernameField.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        LUsername1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        LUsername1.setForeground(new java.awt.Color(0, 51, 153));
        LUsername1.setText("Email");

        SignupButton.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        SignupButton.setForeground(new java.awt.Color(0, 51, 153));
        SignupButton.setText("Signup");
        SignupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignupButtonActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                        .addGap(135, 135, 135))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(instructorRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                        .addComponent(emailField)
                        .addComponent(passwordField))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SignupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(studentRadio, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LUsername)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LUsername1))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LPassword)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(studentRadio)
                    .addComponent(instructorRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SignupButton)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordFieldActionPerformed

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void SignupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignupButtonActionPerformed
         String username = usernameField.getText().trim();
    String email = emailField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    String selectedRole = null;

    if (studentRadio.isSelected()) {
        selectedRole = "student";
    } else if (instructorRadio.isSelected()) {
        selectedRole = "instructor";
    }

    if (username.isEmpty() || email.isEmpty() || password.isEmpty() || selectedRole == null) {
        JOptionPane.showMessageDialog(this,"Please fill all fields and select a role.","Error",JOptionPane.ERROR_MESSAGE);
        return;
    }

    String result = Manager.signup(username, email, password, selectedRole);

    if (result.equals("Signup successful!")) {
        JOptionPane.showMessageDialog(this,"Account created successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        new Login().setVisible(true);

    } else {
        JOptionPane.showMessageDialog(this,result,"Signup Failed",JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_SignupButtonActionPerformed

    private void instructorRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instructorRadioActionPerformed
  
    }//GEN-LAST:event_instructorRadioActionPerformed

    private void studentRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentRadioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_studentRadioActionPerformed

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LPassword;
    private javax.swing.JLabel LUsername;
    private javax.swing.JLabel LUsername1;
    private javax.swing.JButton SignupButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JRadioButton instructorRadio;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JRadioButton studentRadio;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
