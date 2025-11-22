package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Question {
    private String questionId;
    private String questionText;
    private List<String> options;
    private String correctAnswer;
    private int points;

    public Question(String questionId, String questionText, List<String> options, String correctAnswer, int points) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.points = points;
    }

    // Getters
    public String getQuestionId() { 
        return questionId; 
    }
    
    public String getQuestionText() { 
        return questionText; 
    }
    
    public List<String> getOptions() { 
        return options; 
    }
    
    public String getCorrectAnswer() { 
        return correctAnswer; 
    }
    
    public int getPoints() { 
        return points; 
    }

    // Check if answer is correct
    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer.trim());
    }

    // Validate that question has exactly 4 options
    public boolean isValid() {
        return options != null && options.size() == 4;
    }
    
    // Get validation message
    public String getValidationMessage() {
        if (options == null) {
            return "Question has no options";
        }
        if (options.size() != 4) {
            return "Question must have exactly 4 options. Currently has " + options.size();
        }
        if (!options.contains(correctAnswer)) {
            return "Correct answer must be one of the provided options";
        }
        return "Valid";
    }

    // JSON serialization
    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questionId", this.questionId);
        jsonObject.put("questionText", this.questionText);
        jsonObject.put("correctAnswer", this.correctAnswer);
        jsonObject.put("points", this.points);
        
        JSONArray optionsArray = new JSONArray();
        for (String option : this.options) {
            optionsArray.put(option);
        }
        jsonObject.put("options", optionsArray);
        
        return jsonObject;
    }

    public static Question fromJsonObject(JSONObject jsonObject) {
        String questionId = jsonObject.getString("questionId");
        String questionText = jsonObject.getString("questionText");
        String correctAnswer = jsonObject.getString("correctAnswer");
        int points = jsonObject.getInt("points");
        
        List<String> options = new ArrayList<>();
        JSONArray optionsArray = jsonObject.getJSONArray("options");
        for (int i = 0; i < optionsArray.length(); i++) {
            options.add(optionsArray.getString(i));
        }
        
        return new Question(questionId, questionText, options, correctAnswer, points);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", questionText='" + questionText + '\'' +
                ", options=" + options +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", points=" + points +
                '}';
    }
}
