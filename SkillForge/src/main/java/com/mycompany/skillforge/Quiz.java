package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public Quiz(String quizId) {
        this.questions = new ArrayList<>();
        this.studentScores = new ArrayList<>();
        this.studentAttempts = 0;
        this.score = 0;
        this.passed = false;
    }

    // Getters and setters
    public String getQuizId() { 
        return QuizId; 
    }
    
    public List<Question> getQuestions() { 
        return questions; 
    }
    
    public int getStudentAttempts() { 
        return studentAttempts; 
    }
    
    public int getMaxNumberOfAttempts() { 
        return MaxNumberofAttempts; 
    }
    
    public void setQuizId(String quizId) { 
        this.QuizId = quizId; 
    }
    
    public void setQuestions(List<Question> questions) { 
        this.questions = questions; 
    }

   
    public boolean addQuestion(Question question) {
        if (questions.size() >= 5) {
            return false; 
        }
        
     /*   if (!question.isValid()) {
            return false; 
        }
            */ 
        
        questions.add(question);
        return true;
    }

   
    public boolean removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            return true;
        }
        return false;
    }

   
   /*  public boolean updateQuestion(int index, Question newQuestion) {
        if (index >= 0 && index < questions.size() && newQuestion.isValid()) {
            questions.set(index, newQuestion);
            return true;
        }
        return false;
    }
*/
    
    public void clearQuestions() {
        questions.clear();
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

    public int getMaxNumberofAttempts() {
        return MaxNumberofAttempts;
    }

    public int getScore() {
        return score;
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


    public void incrementAttempts() {
        if (studentAttempts < MaxNumberofAttempts) {
            studentAttempts++;
        }
    }


    public boolean hasAttemptsRemaining() {
        return studentAttempts < MaxNumberofAttempts;
    }

 
    public int getRemainingAttempts() {
        return MaxNumberofAttempts - studentAttempts;
    }

    // Validate that quiz has exactly 5 questions with 4 options each
   /* public boolean isValid() {
        if (questions == null || questions.size() != 5) {
            return false;
        }
        
        // Check that each question has exactly 4 options
        for (Question question : questions) {
            if (!question.isValid()) {
                return false;
            }
        }
        return true;
    }
    
    // Get detailed validation message
    public String getValidationMessage() {
        if (questions == null) {
            return "Quiz has no questions";
        }
        if (questions.size() != 5) {
            return "Quiz must have exactly 5 questions. Currently has " + questions.size();
        }
        
        // Check each question
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            if (!question.isValid()) {
                return "Question " + (i + 1) + " is invalid: " + question.getValidationMessage();
            }
        }
        return "Valid - 5 questions with 4 options each";
    }

 */
    public int getQuestionCount() {
        return questions != null ? questions.size() : 0;
    }

   
    public boolean isEmpty() {
        return questions == null || questions.isEmpty();
    }

    
    public void resetAttempts() {
        studentAttempts = 0;
        passed = false;
    }

    public void setScore(int score) {
        this.score = score;
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
