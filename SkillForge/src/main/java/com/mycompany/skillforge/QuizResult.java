/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.skillforge;

/**
 *
 * @author User
 */
public class QuizResult {
    private String courseId;
    private String lessonId;
    private double score;
    private boolean passed;
    
    public QuizResult(String courseId, String lessonId, double score, boolean passed) {
        this.courseId = courseId;
        this.lessonId = lessonId;
        this.score = score;
        this.passed = passed;
    }
    
    // Getters and setters
    public double getScore() { return score; }
    public boolean isPassed() { return passed; }
    public String getCourseId() { return courseId; }
    public String getLessonId() { return lessonId; }
}