package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
public class Progress {
    private String courseId;
    private List<String> lessonIds;

    public Progress(String courseId) {
        this.courseId = courseId;
        this.lessonIds = new ArrayList<>();
    }   

    public String getCourseId() {
        return courseId;
    }
    public List <String>  getLessonId() {
        return lessonIds;
    }
    public void addLesson (String lessonId)
    {
        if (!lessonIds.contains(lessonId)) {
            lessonIds.add(lessonId);
        }
    }

    public static Progress fromJsonObject(org.json.JSONObject jsonObject) {
        Progress progress = new Progress(jsonObject.getString("courseId"));
        org.json.JSONArray lessonsArray = jsonObject.getJSONArray("lessonIds");
        for (int i = 0; i < lessonsArray.length(); i++) {
            progress.addLesson(lessonsArray.getString(i));
        }
        return progress;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        org.json.JSONArray lessonsArray = new JSONArray();
        for (String lessonId : this.lessonIds) {
            lessonsArray.put(lessonId);
        }
        jsonObject.put("lessonIds", lessonsArray);
        return jsonObject;
    }
}
