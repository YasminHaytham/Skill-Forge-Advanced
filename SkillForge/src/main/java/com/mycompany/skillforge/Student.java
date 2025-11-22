package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Student extends User {

    private List<String> EnrolledCourses;
    private List<Progress> progress;
    private final JsonDatabaseManager dbManager = new JsonDatabaseManager();

    public Student(String userId, String role, String username, String email, String passwordHash) {
        super(userId, role, username, email, passwordHash);
        this.EnrolledCourses = new ArrayList<>();
        this.progress = new ArrayList<>();
    }

    public List<Progress> getProgress() {
        return progress;
    }

    public void addEnrolledCourse(String courseId) {
        EnrolledCourses.add(courseId);
        for (Progress p : progress) {
            if (p.getCourseId().equals(courseId)) {
                return;
            }
        }
        progress.add(new Progress(courseId));

    }

    public void removeEnrolledCourse(String courseId) {
        EnrolledCourses.remove(courseId);
        for (Progress p : progress) {
            if (p.getCourseId().equals(courseId)) {
                progress.remove(p);
                break;
            }
        }
    }

    public List<String> getEnrolledCourses() {
        return EnrolledCourses;
    }

    public void addLessonProgress(String courseId, String LessonId) {
        boolean exists = false;
        for (Progress p : progress) {
            if (p.getCourseId().equals(courseId)) {
                for (LessonQuiz lq : p.getLessonQuizs()) {
                    if (lq.getLessonId().equals(LessonId)) {
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    p.addLesson(LessonId);
                }
            }
        }
        dbManager.updateStudent(this);
    }

    public void addLessonQuiz(String courseId, String LessonId, Quiz quiz) {
        for (Progress p : progress) {
            if (p.getCourseId().equals(courseId)) {
                for (LessonQuiz lq : p.getLessonQuizs()) {
                    if (lq.getLessonId().equals(LessonId)) {
                        lq.setQuiz(quiz);
                        break;
                    }
                }
            }
        }
        dbManager.updateStudent(this);
    }

    public void addCompletedLesson(Lesson lesson) {
        for (Progress p : progress) {
            if (p.getCourseId().equals(lesson.getCourseId())) {
                for (LessonQuiz lq : p.getLessonQuizs()) {
                    if (lq.getLessonId().equals(lesson.getLessonId())) {
                        lq.MarkCompletedLessonQuiz();
                    }
                }
            }
        }
        
        dbManager.updateStudent(this);
    }

    public List<Course> getEnrolledCourseObjects() {
        List<Course> courses = new ArrayList<>();
        List<Course> ApprovedCourses = dbManager.getAllApprovedCourses();
        for (String courseId : EnrolledCourses) {
            for (Course c : ApprovedCourses) {
                if (c.getCourseId().equals(courseId)) {
                    courses.add(c);
                    break;
                }
            }
        }
        return courses;
    }

    public List<Lesson> getCompletedLessons() {
        List<Lesson> completedLessons = new ArrayList<>();
        List<Course> ApprovedCourses = dbManager.getAllApprovedCourses();
        for (Progress p : progress) {
            String courseId = p.getCourseId();
            Course course = null;
            for (Course c : ApprovedCourses) {
                if (c.getCourseId().equals(courseId)) {
                    course = c;
                    break;
                }
            }
            if (course != null) {
                for (String lessonId : p.getCompletedLessonIds()) {
                    for (Lesson lesson : course.getLessons()) {
                        if (lesson.getLessonId().equals(lessonId)) {
                            completedLessons.add(lesson);
                            break;
                        }
                    }
                }
            }
        }
        return completedLessons;
    }

    public static Student fromJsonObject(JSONObject jsonObject) {
        String userId = jsonObject.getString("userId");
        String role = jsonObject.getString("role");
        String username = jsonObject.getString("username");
        String email = jsonObject.getString("email");
        String passwordHash = jsonObject.getString("passwordHash");
        List<String> courses = new ArrayList<>();
        if (jsonObject.has("EnrolledCourses")) {
            JSONArray coursesArray = jsonObject.getJSONArray("EnrolledCourses");
            for (int i = 0; i < coursesArray.length(); i++) {
                String courseId = coursesArray.getString(i);
                courses.add(courseId);
            }
        }
        List<Progress> progress = new ArrayList<>();
        if (jsonObject.has("Progress")) {
            JSONArray lessonsArray = jsonObject.getJSONArray("Progress");
            for (int i = 0; i < lessonsArray.length(); i++) {
                JSONObject progressJson = lessonsArray.getJSONObject(i);
                Progress p = Progress.fromJsonObject(progressJson);
                progress.add(p);
            }
        }
        Student student = new Student(userId, role, username, email, passwordHash);
        student.EnrolledCourses = courses;
        student.progress = progress;
        return student;
    }

    @Override
    public JSONObject toJsonObject() {
        JSONObject jsonObject = super.toJsonObject();
        JSONArray coursesArray = new JSONArray();
        for (String courseId : this.EnrolledCourses) {
            coursesArray.put(courseId);
        }
        jsonObject.put("EnrolledCourses", coursesArray);
        JSONArray progressArray = new JSONArray();
        for (Progress p : this.progress) {
            progressArray.put(p.toJsonObject());
        }
        jsonObject.put("Progress", progressArray);

        return jsonObject;
    }
        public QuizResult getQuizResult(String courseId, String lessonId) {
        // TODO: Implement actual quiz result retrieval
        return null; // placeholder
    }
    
    public boolean hasCompletedCourse(String courseId) {
        // TODO: Implement course completion check
        return false; // placeholder
    }
}
