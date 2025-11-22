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

    public List<String> getCompletedLessonIds() {
        List<String> lessonIds = new ArrayList<>();
        for (LessonQuiz lq : lessonQuizs) {
            if (lq.isCompleted()) {
                lessonIds.add(lq.getLessonId());
            }
        }
        return lessonIds;
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

        return progress;
    }
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        
        JSONArray lessonQuizsArray = new JSONArray();
        for (LessonQuiz lessonQuiz : this.lessonQuizs) {
            lessonQuizsArray.put(lessonQuiz.toJsonObject());
        }
        jsonObject.put("lessonQuizs", lessonQuizsArray);
        
        return jsonObject;
    }
}
