package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Progress {

    private String courseId;
    private List<LessonQuiz> lessonQuizs;
    private boolean Completed;
    private Certificate certificate;
     private final JsonDatabaseManager dbManager = new JsonDatabaseManager();

    public Progress(String courseId) {
        this.courseId = courseId;
        this.lessonQuizs = new ArrayList<>();
        this.Completed = false;
        this.certificate = null;
    }

    public String getCourseId() {
        return courseId;
    }

    public List<LessonQuiz> getLessonQuizs() {
        return lessonQuizs;
    }

    public List<String> getCompletedLessonIds() {
        List<String> lessonIds = new ArrayList<>();
        for (LessonQuiz lq : lessonQuizs) {
            if (lq.isCompleted()) {
                lessonIds.add(lq.getLessonId());
            }
        }
        return lessonIds;
    }
    public void checkAndGenerateCertificate(String studentId) {
    if (isCourseCompleted() && certificate == null) {
        Certificate c = new Certificate(studentId, courseId);
        this.certificate = c;
        Student student = dbManager.getStudentbyID(studentId);
        if (student != null)
        {
        dbManager.updateStudent(student);
        }
    }
}

   public boolean isCourseCompleted() {
    if (this.Completed)
    {
        return true;
    }
        Course course = dbManager.getApprovedCourseById(courseId);
        if (course == null) {
            return false;
        }
        
        List<Lesson> courseLessons = course.getLessons();
        if (courseLessons.isEmpty()) {
            return false; 
        }
        
        for (Lesson lesson : courseLessons) {
            boolean lessonCompleted = false;
            for (LessonQuiz lq : lessonQuizs) {
                if (lq.getLessonId().equals(lesson.getLessonId()) && lq.isCompleted()) {
                    lessonCompleted = true;
                    break;
                }
            }
            if (!lessonCompleted) {
                return false; 
            }
        }
        this.Completed=true;
        return true;
    }

    public void addLesson(String lessonId) {

        lessonQuizs.add(new LessonQuiz(lessonId));
    }

    public static Progress fromJsonObject(JSONObject jsonObject) {
        String courseId = jsonObject.getString("courseId");
        Progress progress = new Progress(courseId);

        if (jsonObject.has("lessonQuizs")) {
            JSONArray lessonQuizsArray = jsonObject.getJSONArray("lessonQuizs");
            for (int i = 0; i < lessonQuizsArray.length(); i++) {
                JSONObject lessonQuizJson = lessonQuizsArray.getJSONObject(i);
                LessonQuiz lessonQuiz = LessonQuiz.fromJsonObject(lessonQuizJson);
                progress.lessonQuizs.add(lessonQuiz);
            }
        }

        if (jsonObject.has("completed")) {
            progress.Completed = jsonObject.getBoolean("completed");
        }

        if (jsonObject.has("certificate") && !jsonObject.isNull("certificate")) {
            JSONObject certJson = jsonObject.getJSONObject("certificate");
            progress.certificate = Certificate.fromJsonObject(certJson);
        }

        return progress;
    }

   public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        jsonObject.put("completed", this.Completed);

        JSONArray lessonQuizsArray = new JSONArray();
        for (LessonQuiz lessonQuiz : this.lessonQuizs) {
            lessonQuizsArray.put(lessonQuiz.toJsonObject());
        }
        jsonObject.put("lessonQuizs", lessonQuizsArray);

        if (this.certificate != null) {
            jsonObject.put("certificate", this.certificate.toJsonObject());
        } else {
            jsonObject.put("certificate", JSONObject.NULL);
        }

        return jsonObject;
    }
}
