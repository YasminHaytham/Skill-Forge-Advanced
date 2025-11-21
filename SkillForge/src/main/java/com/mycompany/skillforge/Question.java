package com.mycompany.skillforge;

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
}

