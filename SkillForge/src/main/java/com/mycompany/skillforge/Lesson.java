package com.mycompany.skillforge;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class Lesson {

    private String courseId;
    private String lessonId;
    private String title;
    private String content;
    private List<String> OpResources;
    private List<Question> questions;
    private Random random = new Random();

    public Lesson() {
        this.OpResources = new ArrayList<>();
        this.questions = new ArrayList<>();
    }

    public Lesson(String lessonId, String title, String content, String courseId, Quiz quiz) {
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

    public void setQuestions ( List <Question> questions)
    {
        this.questions=questions;
    }

    public List<Question> getQuestions()
    {
        return this.questions;
    }

     public Quiz generateQuiz(Student student) {
        for (Progress progress : student.getProgress()) {
            if (progress.getCourseId().equals(this.courseId)) {
                for (LessonQuiz lessonQuiz : progress.getLessonQuizs()) {
                    if (lessonQuiz.getLessonId().equals(this.lessonId) && lessonQuiz.getQuiz() != null) {
                        return lessonQuiz.getQuiz();
                    }
                }
            }
        }

        if (this.questions != null && !this.questions.isEmpty()) {
            String quizId = "Q" + String.format("%04d", random.nextInt(10000)) + "_" + this.lessonId;
            Quiz newQuiz = new Quiz(quizId, new ArrayList<>(this.questions));
            student.addLessonQuiz(this.courseId, this.lessonId, newQuiz);
            return newQuiz;
        }
        
        return null;
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
       JSONArray resourcesArray = new JSONArray();
        for (String resource : OpResources) {
            resourcesArray.put(resource);
        }
        jsonObject.put("opResources", resourcesArray);
        JSONArray questionsArray = new JSONArray();
        for (Question question : this.questions) {
            questionsArray.put(question.toJsonObject());
        }
        jsonObject.put("questions", questionsArray);

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
          if (jsonObject.has("questions")) {
            JSONArray questionsArray = jsonObject.getJSONArray("questions");
            List<Question> questions = new ArrayList<>();
            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionJson = questionsArray.getJSONObject(i);
                Question question = Question.fromJsonObject(questionJson);
                questions.add(question);
            }
            lesson.setQuestions(questions);
        }
        return lesson;
    }

    public void setQuiz(Quiz quiz) {
       
        throw new UnsupportedOperationException("Unimplemented method 'setQuiz'");
    }

}
