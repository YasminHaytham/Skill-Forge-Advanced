package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class Lesson {

    private String courseId;
    private String lessonId;
    private String title;
    private String content;
    private List<String> OpResources;
    private List<Question> questions;
    private java.util.Random random = new java.util.Random();

    public Lesson() {
        this.OpResources = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public Lesson(String lessonId, String title, String content, String courseId) {
        this();
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.courseId = courseId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void createQuestion(Question question) {
        this.questions.add(question);
    }

    public Quiz GenerateQuiz(Student student) {
    for (Progress p : student.getProgress()) {
            if (p.getCourseId().equals(this.courseId)) {
                for (LessonQuiz lq : p.getLessonQuizs()) {
                    if (lq.getLessonId().equals(this.lessonId)) {
                        return lq.getQuiz();
                    }
                }
            }
        }

            String quizId = String.format("%03d", random.nextInt(10000)) + this.lessonId + "_quiz";
            Quiz newQuiz = new Quiz(quizId, this.questions);
            //add quiz in student
            return newQuiz;

        }

    public List<String> getResources() {
        return OpResources;
    }

    public void setResource(List<String> resource) {
        this.OpResources = resource;
    }

    public void markAsCompleted(Student student) {
        student.addCompletedLesson(this);
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        jsonObject.put("lessonId", this.lessonId);
        jsonObject.put("title", this.title);
        jsonObject.put("content", this.content);
        jsonObject.put("OpResources", this.OpResources);
        return jsonObject;
    }

    public static Lesson fromJsonObject(JSONObject jsonObject) {
        Lesson lesson = new Lesson();
        lesson.setCourseId(jsonObject.getString("courseId"));
        lesson.setLessonId(jsonObject.getString("lessonId"));
        lesson.setTitle(jsonObject.getString("title"));
        lesson.setContent(jsonObject.getString("content"));
        List<String> resources = new ArrayList<>();
        if (jsonObject.has("OpResources")) {
            for (Object resource : jsonObject.getJSONArray("OpResources")) {
                resources.add((String) resource);
            }
        }
        lesson.OpResources = resources;
        return lesson;
    }

}
