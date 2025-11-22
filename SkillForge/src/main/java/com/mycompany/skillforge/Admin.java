package com.mycompany.skillforge;

public class Admin extends User {

    public Admin(String userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }

    public void approveCourse(Course course) {
        course.Approve();
    }

    public void declinedCourse(Course course) {
        course.Declined();
    }
    
    public void toggleStatus( Course course) {
        if (course.isApproved()) {
            course.Decline();
        } else {
           course.Approve();
        }
    }

}
