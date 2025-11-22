package com.mycompany.skillforge;

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

    public void setCompleted()
    {
        this.Completed=true;
    }

    public boolean isCompleted()
    {
        return Completed;
    }
    

}
