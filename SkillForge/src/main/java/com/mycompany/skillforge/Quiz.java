package com.mycompany.skillforge;

import java.util.List;

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

}
