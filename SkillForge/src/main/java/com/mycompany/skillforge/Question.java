package com.mycompany.skillforge;

import org.json.JSONArray;
import org.json.JSONObject;

public class Question {
private String questionText;
private String[] options;
private String correctAnswer;
public Question(String questionText, String[] options, String correctAnswer) {
    this.questionText = questionText;
    this.options = options;
    this.correctAnswer = correctAnswer;
}
public boolean isCorrect(String selectedAnswer) {
    return selectedAnswer.equals(correctAnswer);
}
public String getQuestionText() {
    return questionText;
}
public String[] getOptions() {
    return options;
}
public String getCorrectAnswer() {
    return correctAnswer;
}
 public static Question fromJsonObject(JSONObject jsonObject)
 {
        String questionText = jsonObject.getString("questionText");
        String[] options = null;
        if (jsonObject.has("options"))
        {
            JSONArray optionsArray = jsonObject.getJSONArray("options");
            options = new String[optionsArray.length()];
            for (int i = 0; i < optionsArray.length(); i++) {
                options[i] = optionsArray.getString(i);
            }
        }
        String correctAnswer = jsonObject.getString("correctAnswer");
        return new Question(questionText, options, correctAnswer);
 }

 public JSONObject toJsonObject()
 {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questionText", this.questionText);
        JSONArray optionsArray = new JSONArray();
        for (String option : this.options) {
            optionsArray.put(option);
        }
        jsonObject.put("options", optionsArray);
        jsonObject.put("correctAnswer", this.correctAnswer);
        return jsonObject;
 }

}