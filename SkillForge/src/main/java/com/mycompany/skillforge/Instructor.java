package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class Instructor extends User {
private  List <String > CreatedCourses;
private final JsonDatabaseManager dbManager = new JsonDatabaseManager();
private Random random = new Random();
public Instructor(String userId, String role, String username, String email, String passwordHash) {
    super(userId, role, username, email, passwordHash);
    this.CreatedCourses = new ArrayList<>();
}
public List<String> getCreatedCourses() {
    return CreatedCourses;
}
public void setCreatedCourses(List<String> createdCourses) {
    CreatedCourses = createdCourses;
}

public void createCourse( String title, String description) {
    String courseId = "C" + String.format("%04d", random.nextInt(10000));
    Course course = new Course(courseId, title, description, this.getUserId());
    try {
        dbManager.addCourse(course);
         CreatedCourses.add(courseId);
        dbManager.updateInstructor(this);
    } catch (IllegalArgumentException e) {
       JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public List<Course> getCreatedCourseObjects() {
    List<Course> courses = new ArrayList<>();
    List <Course> allCourses = dbManager.getAllCourses();
    for (String courseId : CreatedCourses) {
        for (Course c : allCourses) {
            if (c.getCourseId().equals(courseId)) {
                courses.add(c);
            }
        }
    }
    return courses;
}



public static Instructor fromJsonObject(JSONObject jsonObject) {
    String userId = jsonObject.getString("userId");
    String role = jsonObject.getString("role");
    String username = jsonObject.getString("username");
    String email = jsonObject.getString("email");
    String passwordHash = jsonObject.getString("passwordHash");
    List <String> courses = new ArrayList<>();
    JSONArray coursesArray = jsonObject.getJSONArray("CreatedCourses");
    for (int i = 0; i < coursesArray.length(); i++) {
        courses.add(coursesArray.getString(i));
    }
    Instructor i = new Instructor(userId, role, username, email, passwordHash);
    i.CreatedCourses= courses;
    return i;

}
@Override
public JSONObject toJsonObject() {
    JSONObject jsonObject = super.toJsonObject();
    JSONArray coursesArray = new JSONArray();
    for (String courseId : this.CreatedCourses) {
        coursesArray.put(courseId);
    }
    jsonObject.put("CreatedCourses", coursesArray);
    return jsonObject;
}
public void CompleteCourse(Course course) {
    course.setCompleted(true);
    dbManager.updateCourse(course);
}
}
