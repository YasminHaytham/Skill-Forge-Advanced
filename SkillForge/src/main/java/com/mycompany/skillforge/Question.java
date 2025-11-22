package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;
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
public Question(String questionId, String questionText2, List<String> options2, String correctAnswer2, int i) {
    //TODO Auto-generated constructor stub
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
public void setQuestionText(String questionText) {
    this.questionText = questionText;
}
public void setOptions(String[] options) {
    this.options = options;
}
public void setCorrectAnswer(String correctAnswer) {
    this.correctAnswer = correctAnswer;
}
public String getOptionAt(int index) {
    if (index >= 0 && index < options.length) {
        return options[index];
    }
    return null;
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

