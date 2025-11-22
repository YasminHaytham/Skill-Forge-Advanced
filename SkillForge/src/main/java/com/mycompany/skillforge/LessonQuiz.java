package com.mycompany.skillforge;

import org.json.JSONObject;

public class LessonQuiz {
    private String lessonId;
    private Quiz quiz;
    private boolean Completed;

    public LessonQuiz(String lessonId) {
        this.lessonId = lessonId;
        this.quiz = null;
        this.Completed=false;
    }

    public String getLessonId() {
        return lessonId;
    }
    public Quiz getQuiz()
    {
        return quiz;
    }

    public void setQuiz (Quiz quiz)
    {
        this.quiz =quiz;
    }

    public void MarkCompletedLessonQuiz()
    {
        this.Completed=true;
    }

    public boolean isCompleted()
    {
        return Completed;
    }
     public void setCompleted(boolean completed) {
        this.Completed = completed;
    }
    
    public static LessonQuiz fromJsonObject(JSONObject jsonObject) {
        String lessonId = jsonObject.getString("lessonId");
        boolean completed = jsonObject.getBoolean("completed");
        LessonQuiz lq = new LessonQuiz(lessonId);
       lq.setCompleted(completed);
         if (jsonObject.has("quiz") && !jsonObject.isNull("quiz")) {
            JSONObject quizJson = jsonObject.getJSONObject("quiz");
            Quiz quiz = Quiz.fromJsonObject(quizJson);
            lq.setQuiz(quiz);
        }
        return lq;
    }

      public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("lessonId", this.lessonId);
        jsonObject.put("completed", this.Completed);
        
        if (this.quiz != null) {
            jsonObject.put("quiz", this.quiz.toJsonObject());
        } else {
            jsonObject.put("quiz", JSONObject.NULL);
        }
        
        return jsonObject;
    }
}





