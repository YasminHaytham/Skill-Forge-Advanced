package com.mycompany.skillforge;

public class LessonQuiz {
    private String lessonId;
    private Quiz quiz;

    public LessonQuiz(String lessonId) {
        this.lessonId = lessonId;
        this.quiz = null;
    }

    public String getLessonId() {
        return lessonId;
    }
    public Quiz getQuiz()
    {
        return quiz;
    }

    public boolean PassedQuiz()
    {
        if (quiz.isPassed())
        {
            return true;
        }
        return false;
    }
    

}
