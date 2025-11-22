package com.mycompany.skillforge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class Course {

    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private List<Lesson> lessons;
    private List<String> studentIDs;
    private boolean isCompleted = false;
    private String status;
    private final JsonDatabaseManager dbManager = new JsonDatabaseManager();
    private Random random = new Random();

    public Course(String courseId, String title, String description, String instructorId ) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = new ArrayList<>();
        this.studentIDs = new ArrayList<>();
        this.status = "PENDING";
    }

    public String getstatus() {
        return status;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.status = approvalStatus;
    }

    public boolean isApproved() {
        return "APPROVED".equals(status);
    }

    public boolean isPending() {
        return "PENDING".equals(status);
    }

    public boolean isRejected() {
        this.isCompleted = false;
        this.status = "Pending";
        return "REJECTED".equals(status);
    }

    public Course(String courseId, String title, String description, String instructorId, List<Lesson> lessons,
            List<String> studentIDs, boolean isCompleted, String status) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = lessons;
        this.studentIDs = studentIDs;
        this.isCompleted = isCompleted;
        this.status = status;
    }
    public String getCourseId() {
        return courseId;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    } 
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<String> getStudentIDs() {
        return studentIDs;
    }

    public void setStudentIDs(List<String> studentIDs) {
        this.studentIDs = studentIDs;
    }

    public String getStatus() {
        return status;
    }

    public void Approve() {
        this.status = "Approved";
        dbManager.updateCourse(this);
    }

    public void Decline() {
        this.status = "Declined";
        dbManager.updateCourse(this);
    }

    public List<Student> getStudentsObjects() {
    public boolean isApproved() {
    return "Approved".equals(this.status);
}

public boolean isPending() {
    return "Pending".equals(this.status);
}

public boolean isDeclined() {
    return "Declined".equals(this.status);
}
    public List <Student> getStudentsObjects() {
        List<Student> students = new ArrayList<>();
        List<Student> allStudents = dbManager.getAllStudents();
        for (String studentId : studentIDs) {
            for (Student s : allStudents) {
                if (s.getUserId().equals(studentId)) {
                    students.add(s);
                }
            }
        }
        return students;
    }
    // Methods to manage students
    // adding and removing students from the course

    public boolean isStudentEnrolled(Student student) {
        return studentIDs.contains(student.getUserId());
    }

    public void enrollStudent(Student student) {
        if (!"APPROVED".equals(status)) {
            throw new IllegalArgumentException("Cannot enroll in a course that is not approved");
        }

        if (!isStudentEnrolled(student)) {
            studentIDs.add(student.getUserId());
            student.addEnrolledCourse(this.courseId);
            dbManager.updateStudent(student);
            dbManager.updateCourse(this);
        }
    }

    public void unenrollStudent(Student student) {
        studentIDs.remove(student.getUserId());
        student.removeEnrolledCourse(this.courseId);
        dbManager.updateStudent(student);
        dbManager.updateCourse(this);
    }

    public void createLesson(String title, String content) {
        String lessonId = "L" + String.format("%04d", random.nextInt(10000));
        Lesson lesson = new Lesson(lessonId, title, content, this.courseId, null);
        lessons.add(lesson);
        dbManager.updateCourse(this);
    }

    public void removeLesson(Lesson lesson) {
        lessons.remove(lesson);
        dbManager.updateCourse(this);
    }

    public void updateLesson(Lesson lesson) {
        for (int i = 0; i < lessons.size(); i++) {
            if (lessons.get(i).getLessonId().equals(lesson.getLessonId())) {
                lessons.set(i, lesson);
                break;
            }
        }
        dbManager.updateCourse(this);
    }

    public void DeleteCourse() {
        dbManager.deleteCourse(this.courseId);
    }

    public static Course fromJsonObject(JSONObject jsonObject) {
        String courseId = jsonObject.getString("courseId");
        String title = jsonObject.getString("title");
        String description = jsonObject.getString("description");
        String instructorId = jsonObject.getString("instructorId");
        boolean isCompleted = jsonObject.getBoolean("isCompleted");
        String status = jsonObject.getString("status");

        List<Lesson> lessons = new ArrayList<>();
        if (jsonObject.has("lessons")) {
            JSONArray lessonsArray = jsonObject.getJSONArray("lessons");
            for (int i = 0; i < lessonsArray.length(); i++) {
                JSONObject lessonObject = lessonsArray.getJSONObject(i);
                Lesson lesson = Lesson.fromJsonObject(lessonObject);
                lessons.add(lesson);
            }
        }

        List<String> studentIDs = new ArrayList<>();
        if (jsonObject.has("StudentIDs")) {
            JSONArray studentsArray = jsonObject.getJSONArray("StudentIDs");
            for (int i = 0; i < studentsArray.length(); i++) {
                String studentId = studentsArray.getString(i);
                studentIDs.add(studentId);
            }

        }
        Course course = new Course(courseId, title, description, instructorId , lessons , studentIDs , isCompleted , status );
        return course;
    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", this.courseId);
        jsonObject.put("title", this.title);
        jsonObject.put("description", this.description);
        jsonObject.put("instructorId", this.instructorId);
        jsonObject.put("isCompleted",this.isCompleted);
         jsonObject.put("status",this.status);
        
        JSONArray lessonsArray = new JSONArray();
        for (Lesson lesson : this.lessons) {
            lessonsArray.put(lesson.toJsonObject());
        }
        jsonObject.put("lessons", lessonsArray);
        JSONArray studentsArray = new JSONArray();
        for (String studentId : this.studentIDs) {
            studentsArray.put(studentId);
        }
        jsonObject.put("StudentIDs", studentsArray);
        return jsonObject;
    }

    public boolean hasStudentCompletedCourse(String studentId) {
        Student student = dbManager.getStudentById(studentId);
        if (student == null)
            return false;

        // Check if student passed all lesson quizzes
        for (Lesson lesson : lessons) {
            QuizResult result = student.getQuizResult(courseId, lesson.getLessonId());
            if (result == null || !result.isPassed()) {
                return false;
            }
        }
        return true;
    }

    public double getCompletionPercentage() {
        if (studentIDs.isEmpty())
            return 0.0;

        int completedCount = 0;
        for (String studentId : studentIDs) {
            if (hasStudentCompletedCourse(studentId)) {
                completedCount++;
            }
        }

        return (double) completedCount / studentIDs.size() * 100;
    }
}
