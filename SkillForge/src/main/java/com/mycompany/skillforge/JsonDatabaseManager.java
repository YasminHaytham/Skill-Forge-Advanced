package com.mycompany.skillforge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonDatabaseManager {

    private final String userFile = "users.json";
    private final String courseFile = "courses.json";

    public List<Student> getAllStudents() {
        JSONObject data = readFromFile(userFile);
        JSONArray studentsArray = data.getJSONArray("Students");
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentsArray.length(); i++) {
            JSONObject studentJson = studentsArray.getJSONObject(i);
            Student student = Student.fromJsonObject(studentJson);
            students.add(student);
        }
        return students;
    }

    public List<Instructor> getAllInstructors() {
        JSONObject data = readFromFile(userFile);
        JSONArray instructorsArray = data.getJSONArray("Instructors");
        List<Instructor> instructors = new ArrayList<>();
        for (int i = 0; i < instructorsArray.length(); i++) {
            JSONObject instructorJson = instructorsArray.getJSONObject(i);
            Instructor instructor = Instructor.fromJsonObject(instructorJson);
            instructors.add(instructor);
        }
        return instructors;
    }
     public List<Admin> getAllAdmins() {
        JSONObject data = readFromFile(userFile);
        JSONArray adminsArray = data.getJSONArray("Admins");
        List<Admin> Admins = new ArrayList<>();
        for (int i = 0; i < adminsArray.length(); i++) {
            JSONObject adminJson = adminsArray.getJSONObject(i);
            Admin admin = Admin.fromJsonObject(adminJson);
            Admins.add(admin);
        }
        return Admins;
    }

    public List<Course> getAllDeclinedCourses() {
        JSONObject data = readFromFile(courseFile);
        JSONArray coursesArray = data.getJSONArray("Courses");
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            if (course.isDeclined()) {
            courses.add(course);
            }
        }
        return courses;
    }

    public List<Course> getAllApprovedCourses() {
        JSONObject data = readFromFile(courseFile);
        JSONArray coursesArray = data.getJSONArray("Courses");
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            if (course.isApproved()) {
            courses.add(course);
            }
        }
        return courses;
    }
      public List<Course> getAllPendingCourses() {
        JSONObject data = readFromFile(courseFile);
        JSONArray coursesArray = data.getJSONArray("Courses");
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            if (course.isPending()) {
            courses.add(course);
            }
        }
        return courses;
    }
     public List<Course> getAllCourses() {
        JSONObject data = readFromFile(courseFile);
        JSONArray coursesArray = data.getJSONArray("Courses");
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < coursesArray.length(); i++) {
            JSONObject courseJson = coursesArray.getJSONObject(i);
            Course course = Course.fromJsonObject(courseJson);
            courses.add(course);
        }
        return courses;
    }
    public Student getStudentbyID(String studentId)
    {
        List<Student> students= getAllStudents();
        for (Student s : students)
        {
            if (s.getUserId().equals(studentId))
            {
                return s;
            }
        }
        return null;
    }

    public Course getCourseById (String courseId)
    {
        List <Course> courses = getAllCourses();
        for ( Course c : courses)
        {
            if ( c.getCourseId().equals(courseId))
            {
                return c;
            }
        }
        return null;
    }
    public Course getApprovedCourseById (String courseId)
    {
        List <Course> courses = getAllApprovedCourses();
        for ( Course c : courses)
        {
            if ( c.getCourseId().equals(courseId))
            {
                return c;
            }
        }
        return null;
    }

    private JSONObject readFromFile(String fileName) {
        try {
            String data = new String(Files.readAllBytes(Paths.get(fileName)));
            if (data.isEmpty()) {
                return EmptyFile(fileName);
            }
            return new JSONObject(new JSONTokener(data));
        } catch (IOException e) {
            System.out.println("Error reading: " + fileName);
            return EmptyFile(fileName);
        }

    }

    public boolean userExists(String email) {
        List<Student> students = getAllStudents();
        List<Instructor> instructors = getAllInstructors();
        for (Student s : students) {
            if (s.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        for (Instructor i : instructors) {
            if (i.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
       
    }

    public void addStudent(Student student) {
        List<Student> students = getAllStudents();
        List<Instructor> instructors = getAllInstructors();
        String newUserId = student.getUserId();
        for (Student s : students) {
            if (s.getUserId().equals(newUserId)) {
                throw new IllegalArgumentException("User with ID " + student.getUserId() + " already exists.");
            }
        }
        for (Instructor i : instructors) {
            if (i.getUserId().equals(newUserId)) {
                throw new IllegalArgumentException("User with ID " + student.getUserId() + " already exists.");
            }
        }
        students.add(student);
        saveAllStudents(students);
    }

    public void addInstructor(Instructor instructor) {
        List<Student> students = getAllStudents();
        List<Instructor> instructors = getAllInstructors();
        String newUserId = instructor.getUserId();
        for (Student s : students) {
            if (s.getUserId().equals(newUserId)) {
                throw new IllegalArgumentException("User with ID " + instructor.getUserId() + " already exists.");
            }
        }
        for (Instructor i : instructors) {
            if (i.getUserId().equals(newUserId)) {
                throw new IllegalArgumentException("User with ID " + instructor.getUserId() + " already exists.");
            }
        }
        instructors.add(instructor);
        saveAllInstructors(instructors);
    }

    public void addCourse(Course course) {
        List<Course> courses = getAllCourses();
        for (Course c : courses) {
            if (c.getCourseId().equals(course.getCourseId())) {
                throw new IllegalArgumentException("Course with ID " + course.getCourseId() + " already exists.");
            }
        }
        courses.add(course);
        saveAllCourses(courses);
    }

    public void updateStudent(Student updatedStudent) {
        List<Student> students = getAllStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getUserId().equals(updatedStudent.getUserId())) {
                students.set(i, updatedStudent);
                break;
            }
        }
        saveAllStudents(students);
    }

    public void updateInstructor(Instructor updatedInstructor) {
        List<Instructor> instructors = getAllInstructors();
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getUserId().equals(updatedInstructor.getUserId())) {
                instructors.set(i, updatedInstructor);
                break;
            }
        }
        saveAllInstructors(instructors);
    }

    public void updateCourse(Course updatedCourse) {
        List<Course> courses = getAllCourses();
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(updatedCourse.getCourseId())) {
                courses.set(i, updatedCourse);
                break;
            }
        }

        saveAllCourses(courses);
    }
    
    public void deleteStudent(String userId) {
        List<Student> students = getAllStudents();
         students.removeIf(s -> s.getUserId().equals(userId));
        List<Course> courses = getAllCourses();
        for (Course course : courses) {
            List<String> enrolledStudents = course.getStudentIDs();
            enrolledStudents.removeIf(sId -> sId.equals(userId));
        }
        saveAllCourses(courses);
        saveAllStudents(students);
    }

    public void deleteCourse(String courseId) {
        List<Course> courses = getAllCourses();
        courses.removeIf(c -> c.getCourseId().equals(courseId));
        List<Student> students = getAllStudents();
        for (Student student : students) {
            List<String> enrolledCourses = student.getEnrolledCourses();
            enrolledCourses.removeIf(cId -> cId.equals(courseId));
        }
        List<Instructor> instructors = getAllInstructors();
        for (Instructor instructor : instructors) {
            List<String> createdCourses = instructor.getCreatedCourses();
            createdCourses.removeIf(cId -> cId.equals(courseId));
        }
        saveAllStudents(students);
        saveAllInstructors(instructors);
        saveAllCourses(courses);
    }

    public void saveAllStudents(List<Student> students) {
        JSONObject data = readFromFile(userFile);
        List<JSONObject> Studentjson = new ArrayList<>();
        for (Student student : students) {
            Studentjson.add(student.toJsonObject());
        }
        data.put("Students", new JSONArray(Studentjson));
        writeToFile(userFile, data);
    }

    public void saveAllInstructors(List<Instructor> instructors) {
        JSONObject data = readFromFile(userFile);
        List<JSONObject> Instructorjson = new ArrayList<>();
        for (Instructor instructor : instructors) {
            Instructorjson.add(instructor.toJsonObject());
        }
        data.put("Instructors", new JSONArray(Instructorjson));
        writeToFile(userFile, data);
    }

    public void saveAllCourses(List<Course> courses) {
        JSONObject data = readFromFile(courseFile);
        List<JSONObject> Coursejson = new ArrayList<>();
        for (Course course : courses) {
            Coursejson.add(course.toJsonObject());
        }
        data.put("Courses", new JSONArray(Coursejson));
        writeToFile(courseFile, data);
    }

    private void writeToFile(String fileName, JSONObject data) {
        try {
            Files.write(Paths.get(fileName), data.toString(4).getBytes());
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }

    private JSONObject EmptyFile(String fileName) {
        JSONObject empFile = new JSONObject();
        if (fileName.equals(userFile)) {
            empFile.put("Instructors", new JSONArray());
            empFile.put("Students", new JSONArray());
            empFile.put("Admins",new JSONArray());
        } else if (fileName.equals(courseFile)) {
            empFile.put("Courses", new JSONArray());
        }
        return empFile;
    }
    
}
