package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
public class Progress {
    private String courseId;
    private List<LessonQuiz> lessonQuizs;
    

    public Progress(String courseId) {
        this.courseId = courseId;
        this.lessonQuizs = new ArrayList<>();
    }   

    public String getCourseId() {
        return courseId;
    }

    public List<LessonQuiz> getLessonQuizs() {
        return lessonQuizs;
    }

    public List<String> getLessonId() {
        List<String> lessonIds = new ArrayList<>();
        for (LessonQuiz lq : lessonQuizs) {
            lessonIds.add(lq.getLessonId());
        }
        return lessonIds;
    }
    
    public void addLesson (String lessonId)
    {
        for (LessonQuiz lq : lessonQuizs) {
            if (lq.getLessonId().equals(lessonId)) {
                return;
            }
        }
        lessonQuizs.add(new LessonQuiz(lessonId));
    }


    

    public static Progress fromJsonObject(org.json.JSONObject jsonObject) {
        Progress progress = new Progress(jsonObject.getString("courseId"));


        return progress;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        org.json.JSONArray lessonsArray = new JSONArray();

        jsonObject.put("lessonIds", lessonsArray);
        return jsonObject;
    }
}
