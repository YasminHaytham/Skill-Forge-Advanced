package com.mycompany.skillforge;

import org.json.JSONObject;

public class Admin extends User {

    public Admin(String userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
    }

    public void approveCourse(Course course) {
        course.Approve();
    }

    public void declinedCourse(Course course) {
        course.Decline();
    }
    
    public void toggleStatus( Course course) {
        if (course.isApproved()) {
            course.Decline();
        } else {
           course.Approve();
        }
    }

      public static Admin fromJsonObject(JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String role = jsonObject.getString("role");
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String passwordHash = jsonObject.getString("passwordHash");
        return new Admin(userId, role, username, email, passwordHash);
      }

}
