package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Quiz {

    private String QuizId;
    private List<Question> questions;
    private static final int MaxNumberofAttempts = 3;
    private int score;
    private List<Integer> studentScores;
    private int studentAttempts;
    boolean passed;

    public Quiz(String QuizId, List<Question> questions) {
        this.QuizId = QuizId;
        this.questions = questions;
        this.score = 0;
        this.studentAttempts = 0;
        this.passed = false;
    }

    public Quiz(String quizId, List<Question> questions, List<Integer> studentScores, int studentAttempts,
            boolean passed) {
        QuizId = quizId;
        this.questions = questions;
        this.studentScores = studentScores;
        this.studentAttempts = studentAttempts;
        this.passed = passed;
        this.score = 0;
    }

    public String getQuizId() {
        return this.QuizId;
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public void setAllQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getStudentAttempts() {
        return studentAttempts;
    }

    public int getMaxNumberofAttempts() {
        return MaxNumberofAttempts;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Integer> getStudentScores() {
        return studentScores;
    }

    public void setStudentScores(List<Integer> studentScores) {
        this.studentScores = studentScores;
    }

    public void addStudentScore(int score) {
        this.studentScores.add(score);
    }

    public void incrementAttempts() {
        if (studentAttempts < MaxNumberofAttempts) {
            studentAttempts++;
        }
    }

    public void calculatePassStatus() {
        double passingScore = Math.ceil(questions.size() / 2.0);
        if (this.passed) {
            return;
        }
        this.passed = (this.score >= passingScore);
    }

    public void incrementScore() {
        this.score++;
        calculatePassStatus();
    }

    public boolean isPassed() {
        return passed;
    }

    public static Quiz fromJsonObject(JSONObject jsonObject) {
        String QuizId = jsonObject.getString("QuizId");
        int studentAttempts = jsonObject.getInt("studentAttemptts");
        boolean passed = jsonObject.getBoolean("passed");
        List<Question> questions = new ArrayList<>();
        if (jsonObject.has("questions")) {
            JSONArray questionsArray = jsonObject.getJSONArray("questions");
            for (int i = 0; i < questionsArray.length(); i++) {
                JSONObject questionsJson = questionsArray.getJSONObject(i);
                Question q = Question.fromJsonObject(questionsJson);
                questions.add(q);
            }
        }
        List<Integer> studentScores = new ArrayList<>();
        if (jsonObject.has("studentScores")) {
            JSONArray scoresArray = jsonObject.getJSONArray("studentScores");
            for (int i = 0; i < scoresArray.length(); i++) {
                int s = scoresArray.getInt(i);
                studentScores.add(s);
            }
        }
        Quiz quiz = new Quiz(QuizId, questions, studentScores, studentAttempts, passed);
        return quiz;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("QuizId", this.QuizId);
        jsonObject.put("studentAttempts", this.studentAttempts);
        jsonObject.put("passed", this.passed);
        JSONArray scoresArray = new JSONArray();
        for (int s : this.studentScores) {
            scoresArray.put(s);
        }
        jsonObject.put("studentScores", scoresArray);
        JSONArray questionsArray = new JSONArray();
        for (Question q : this.questions) {
            questionsArray.put(q.toJsonObject());
        }
        jsonObject.put("questions", questionsArray);
        return jsonObject;
    }

}
