package com.mycompany.skillforge;
import java.util.HashMap;
import java.util.Map;
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

public Map<String, Object> getCourseAnalytics(String courseId) {
    Map<String, Object> analytics = new HashMap<>();
    Course course = dbManager.getCourseById(courseId);
    
    if (course != null) {
        // Student completion rates
        List<Student> enrolledStudents = dbManager.getStudentsByIds(course.getStudentIDs());
        int totalStudents = enrolledStudents.size();
        int completedStudents = 0;
        
        // Quiz averages per lesson
        Map<String, Double> lessonQuizAverages = new HashMap<>();
        Map<String, Integer> lessonCompletion = new HashMap<>();
        
        for (Lesson lesson : course.getLessons()) {
            double totalScore = 0;
            int attemptCount = 0;
            int completedCount = 0;
            
            for (Student student : enrolledStudents) {
                // Check if student completed this lesson's quiz
                boolean lessonCompleted = false;
                double lessonScore = 0;
                
                // Look through student's progress to find quiz data
                for (Progress progress : student.getProgress()) {
                    if (progress.getCourseId().equals(courseId)) {
                        for (LessonQuiz lessonQuiz : progress.getLessonQuizs()) {
                            if (lessonQuiz.getLessonId().equals(lesson.getLessonId()) && lessonQuiz.isCompleted()) {
                                lessonCompleted = true;
                                completedCount++;
                                
                                // Calculate average score from studentScores
                                Quiz quiz = lessonQuiz.getQuiz();
                                if (quiz != null && !quiz.getStudentScores().isEmpty()) {
                                    for (int score : quiz.getStudentScores()) {
                                        totalScore += score;
                                        attemptCount++;
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
            
            // Calculate averages
            double averageScore = attemptCount > 0 ? totalScore / attemptCount : 0;
            double completionRate = totalStudents > 0 ? (double) completedCount / totalStudents * 100 : 0;
            
            lessonQuizAverages.put(lesson.getTitle(), averageScore);
            lessonCompletion.put(lesson.getTitle(), completedCount);
        }
        
        // Overall course completion
        for (Student student : enrolledStudents) {
            if (student.hasCompletedCourse(courseId)) {
                completedStudents++;
            }
        }
        
        double courseCompletionRate = totalStudents > 0 ? (double) completedStudents / totalStudents * 100 : 0;
        
        analytics.put("totalStudents", totalStudents);
        analytics.put("completedStudents", completedStudents);
        analytics.put("courseCompletionRate", courseCompletionRate);
        analytics.put("lessonQuizAverages", lessonQuizAverages);
        analytics.put("lessonCompletion", lessonCompletion);
    } else {
        // Return empty analytics if course not found
        analytics.put("totalStudents", 0);
        analytics.put("completedStudents", 0);
        analytics.put("courseCompletionRate", 0.0);
        analytics.put("lessonQuizAverages", new HashMap<String, Double>());
        analytics.put("lessonCompletion", new HashMap<String, Integer>());
    }
    
    return analytics;
}

    public String getCourseApprovalStatus(String courseId) {
        Course course = dbManager.getCourseById(courseId);
        return course != null ? course.getstatus() : "NOT_FOUND";
    }
public void CompleteCourse(Course course) {
    course.setCompleted(true);
    dbManager.updateCourse(course);
}
}
